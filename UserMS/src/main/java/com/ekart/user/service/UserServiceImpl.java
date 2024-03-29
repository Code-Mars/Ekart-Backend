package com.ekart.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ekart.user.dto.LoginDTO;
import com.ekart.user.dto.UserDTO;
import com.ekart.user.entity.SequenceId;
import com.ekart.user.entity.User;
import com.ekart.user.exception.EkartException;
import com.ekart.user.repository.UserRepository;


@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOperation;
    private static final String HOSTING_SEQ_KEY = "hosting";
    private static final String USER_NOT_FOUND="Service.USER_NOT_FOUND";
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
    public Long registerUser(UserDTO userDTO) throws EkartException {
        if(userRepository.findByMobile(userDTO.getMobile()).isPresent())throw new EkartException("Service.USER_FOUND");
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent())throw new EkartException("Service.USER_FOUND");
        userDTO.setId(getNextSequenceId(HOSTING_SEQ_KEY));
        User user=UserDTO.getEntity(userDTO);
        return userRepository.save(user).getId();
        
    }
    @Override
    public Long loginUser(LoginDTO loginDTO) throws EkartException{
        User user=null;
        if(loginDTO.getEmail()!=null)user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new EkartException(USER_NOT_FOUND));
        else user=userRepository.findByMobile(loginDTO.getMobile()).orElseThrow(()->new EkartException(USER_NOT_FOUND));
        if(!loginDTO.getPassword().equals(user.getPassword()))throw new EkartException("Service.INVALID_CREDENTIALS");
        return user.getId();
    }
    @Override
    public void resetPassword(LoginDTO dto) throws EkartException {
        User user = userRepository.findByMobileAndEmail(dto.getMobile(), dto.getEmail()).orElseThrow(()->new EkartException("Service.INVALID_MOBILE_OR_EMAIL"));
        user.setPassword(dto.getPassword());
        userRepository.save(user);
    }
    
    @Override
    public UserDTO getUser(Long id) throws EkartException {
        User user = userRepository.findById(id).orElseThrow(()->new EkartException(USER_NOT_FOUND));
        return UserDTO.entityToDTO(user);
    }
    @Override
    public Long getUserId(String email) throws EkartException {
        User user=userRepository.findByEmail(email).orElseThrow(()->new EkartException(USER_NOT_FOUND));
        return user.getId();
    }
    
}
