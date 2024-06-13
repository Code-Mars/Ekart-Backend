package com.ekart.items.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekart.items.dto.ItemDTO;
import com.ekart.items.entity.SequenceId;
import com.ekart.items.exception.EkartException;
import com.ekart.items.repository.ItemRepository;

@Service(value="userService")
@Transactional
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MongoOperations mongoOperation;
    private static final String HOSTING_SEQ_KEY = "hosting";

    @Override
    public long getNextSequenceId(String key) throws EkartException{
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
    public Long registerItem(ItemDTO itemDTO) throws EkartException{
        itemDTO.setId(getNextSequenceId(HOSTING_SEQ_KEY));
        return itemRepository.save(ItemDTO.getEntity(itemDTO)).getId();
    }
    @Override
    public ItemDTO getItem(Long id) throws EkartException{
        return ItemDTO.getDTO(itemRepository.findById(id).orElseThrow(()->new EkartException("Service.ITEM_NOT_FOUND")));
    }
    @Override
    public List<ItemDTO> getAllItems(){
        return itemRepository.findAll().stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    
    @Override
    public List<ItemDTO>searchItems(String term){
        return itemRepository.findByAllIgnoreCaseTitleContainingOrDescContainingOrCategoryContaining(term, term, term).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    @Override
    public List<ItemDTO>getItemsOnPriceLess(Double price){
        return itemRepository.findByPriceLessThanEqual(price).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    @Override
    public List<ItemDTO>getItemsOnPriceGreater(Double price){
        return itemRepository.findByPriceGreaterThanEqual(price).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    @Override
    public List<ItemDTO>getItemsOnPriceBetween(Double low, Double high){
        return itemRepository.findByPriceBetween(low, high).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    @Override
    public List<ItemDTO>getItemsOnRatings(Double rating){
        return itemRepository.findByRatingGreaterThanEqual(rating).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
    @Override
    public List<ItemDTO> getCategoryWiseItems(String term) {
        return itemRepository.findByCategory(term).stream().map(x->ItemDTO.getDTO(x)).toList();
    }
}
