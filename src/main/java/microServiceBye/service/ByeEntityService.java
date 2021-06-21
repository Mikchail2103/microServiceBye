package microServiceBye.service;

import microServiceBye.model.ByeEntity;
import microServiceBye.repository.ByeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ByeEntityService {
    private ByeEntityRepository byeEntityRepository;

    @Autowired
    public void setByeEntityRepository(ByeEntityRepository byeEntityRepository){
        this.byeEntityRepository = byeEntityRepository;
    }

    @Transactional
    public void saveBye(ByeEntity byeEntity){
        byeEntityRepository.save(byeEntity);
    }

    @Transactional
    public ByeEntity getByIdName(String id) {
        return byeEntityRepository.getByIdName(id);
    }
}
