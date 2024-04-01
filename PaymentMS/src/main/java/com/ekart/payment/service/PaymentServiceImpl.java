package com.ekart.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekart.payment.dto.PaymentDTO;
import com.ekart.payment.entity.SequenceId;
import com.ekart.payment.exception.EkartException;
import com.ekart.payment.repository.PaymentRepository;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
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
    public Long pay(PaymentDTO paymentDTO) throws EkartException{
        paymentDTO.setId(getNextSequenceId(HOSTING_SEQ_KEY));
        return paymentRepository.save(PaymentDTO.toEntity(paymentDTO)).getId();
    }
}
