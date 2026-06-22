package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.repository.ChoixMultipleRepository;
import org.psychohelp.psychohelp.service.ChoixMultipleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ChoixMultipleServiceImpl implements ChoixMultipleService {



    private final ChoixMultipleRepository choixRepository;



    @Override
    public List<ChoixMultiple> getAllChoix(){

        return choixRepository.findAll();

    }



    @Override
    public ChoixMultiple getChoixById(int id){

        return choixRepository.findById(id)
                .orElse(null);

    }



    @Override
    public ChoixMultiple saveChoix(ChoixMultiple choix){

        return choixRepository.save(choix);

    }



    @Override
    public ChoixMultiple updateChoix(
            int id,
            ChoixMultiple choix){


        ChoixMultiple ancienChoix =
                choixRepository.findById(id)
                        .orElse(null);


        if(ancienChoix != null){

            ancienChoix.setChoix(
                    choix.getChoix()
            );


            ancienChoix.setScore(
                    choix.getScore()
            );


            return choixRepository.save(ancienChoix);

        }


        return null;

    }



    @Override
    public void deleteChoix(int id){

        choixRepository.deleteById(id);

    }

}
