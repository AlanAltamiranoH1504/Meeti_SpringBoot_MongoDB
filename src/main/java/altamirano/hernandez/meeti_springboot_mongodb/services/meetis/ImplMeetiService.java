package altamirano.hernandez.meeti_springboot_mongodb.services.meetis;

import altamirano.hernandez.meeti_springboot_mongodb.models.Meeti;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IMeetiRepository;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IMeetiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplMeetiService implements IMeetiService {
    @Autowired
    private IMeetiRepository iMeetiRepository;

    @Override
    public List<Meeti> findAll() {
        try {
            List<Meeti> meetis = (List<Meeti>) iMeetiRepository.findAll();
            return meetis;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Meeti> findAllByUserId(String id) {
        try {
            List<Meeti> meetisByUserId = iMeetiRepository.findAllByUserId(id);
            return meetisByUserId;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Meeti> findById(String id) {
        try {
            Optional<Meeti> meeti = iMeetiRepository.findById(id);
            return meeti;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Meeti meeti) {
        try {
            iMeetiRepository.save(meeti);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            iMeetiRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
