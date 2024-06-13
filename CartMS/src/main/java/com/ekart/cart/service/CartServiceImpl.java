package com.ekart.cart.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekart.cart.dto.CartDTO;
import com.ekart.cart.dto.Status;
import com.ekart.cart.entity.Cart;
import com.ekart.cart.entity.SequenceId;
import com.ekart.cart.exception.EkartException;
import com.ekart.cart.repository.CartRepository;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MongoOperations mongoOperation;
    private static final String HOSTING_SEQ_KEY = "hosting";

    @Override
    public long getNextSequenceId(String key) throws EkartException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
        if (seqId == null) {
            throw new EkartException("Unable to get sequence id for key : " + key);
        }
        return seqId.getSeq();
    }

    @Override
    public Integer addItem(CartDTO item) throws EkartException {
        Optional<Cart> opt = cartRepository.findByUserIdAndItemIdAndStatus(item.getUserId(), item.getItemId(),
                Status.ADDED);
        if (opt.isPresent()) {
            Cart cart = opt.get();
            cart.setQuantity(cart.getQuantity() + item.getQuantity());
            cartRepository.save(cart);
            return 0;
        }
        item.setId(this.getNextSequenceId(HOSTING_SEQ_KEY));
        item.setStatus(Status.ORDERED);
        item.setDate(LocalDate.now());
        cartRepository.save(CartDTO.toEntity(item));
        return 1;
    }

    @Override
    public void updateItem(CartDTO item) throws EkartException {
        Cart cart = cartRepository.findById(item.getId())
                .orElseThrow(() -> new EkartException("Service.ITEM_NOT_FOUND"));
        cart.setQuantity(item.getQuantity());
        cart.setStatus(item.getStatus());
        cartRepository.save(cart);
    }

    @Override
    public void deleteItem(Long id) throws EkartException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EkartException("Service.ITEM_NOT_FOUND"));
        if(!cart.getStatus().equals(Status.ADDED))throw new EkartException("Service.ITEM_CANT_DELETE");
        cartRepository.deleteById(id);
    }

    @Override
    public void cancelOrder(Long id) throws EkartException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EkartException("Service.ITEM_NOT_FOUND"));
        if(!cart.getStatus().equals(Status.ORDERED))throw new EkartException("Service.ITEM_CANT_CANCEL");
        cart.setStatus(Status.CANCELED);
        cartRepository.save(cart);
    }

    @Override
    public void orderItems(List<Long> items) throws EkartException {
        items.stream().forEach((id) -> {
            Cart cart = cartRepository.findById(id).get();
            cart.setStatus(Status.ORDERED);
            cartRepository.save(cart);
        });
    }

    @Override
    public List<CartDTO> getOrders(Long userId, Status status) {
        return cartRepository.findByUserIdAndStatus(userId, status).stream().map((x) -> CartDTO.toDTO(x)).toList();
    }
}
