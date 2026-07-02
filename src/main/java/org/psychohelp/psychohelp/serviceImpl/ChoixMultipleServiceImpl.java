package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.ChoixMultiplesDTO;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.repository.ChoixMultipleRepository;
import org.psychohelp.psychohelp.service.ChoixMultipleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChoixMultipleServiceImpl implements ChoixMultipleService {



    private final ChoixMultipleRepository choixRepository;



    @Override
    public List<ChoixMultiple> getAllChoix(){

        return choixRepository.findAll();

    }



    @Override
    public Optional<ChoixMultiple> getChoixById(int id){

        return choixRepository.findById(id) ;

    }



    @Override
    public ChoixMultiple saveChoix(ChoixMultiplesDTO choixMultiplesDTO){

        ChoixMultiple choixMultiple = new ChoixMultiple();

        choixMultiple.setChoix(choixMultiplesDTO.getChoix());
        choixMultiple.setScore(choixMultiplesDTO.getScore());

        return choixRepository.save(choixMultiple);

    }



    @Override
    public ChoixMultiple updateChoix(
            int id,
            ChoixMultiplesDTO choixMultiplesDTO){


        ChoixMultiple ancienChoix =
                choixRepository.findById(id)
                        .orElseThrow(()->  new RuntimeException("Choix non trouvé"));


        ancienChoix.setChoix(
                choixMultiplesDTO.getChoix()
        );


        ancienChoix.setScore(
                choixMultiplesDTO.getScore()
        );


        return choixRepository.save(ancienChoix);


    }



    @Override
    public void deleteChoix(int id){

        choixRepository.deleteById(id);

    }

}
