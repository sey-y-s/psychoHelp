package org.psychohelp.psychohelp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.repository.CreneauRepository;
import org.psychohelp.psychohelp.service.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreneauServiceImpl implements CreneauService {

    private final CreneauRepository cp;

    @Override
    public Creneau creer(Creneau creneau) {
        if (creneau.getHeureDebut().isAfter(creneau.getHeureFin())) {
            throw new RuntimeException(
                    "L'heure de début doit etre inférieure a l'heure de fin"
            );
        }
        return cp.save(creneau);
    }

    @Override
    public List<Creneau> getAll() {
        return cp.findAll();
    }

    @Override
    public Creneau getById(Long id) {
        return cp.findById(id)
                .orElseThrow(() -> new RuntimeException("Créneau introuvable"));
    }

    @Override
    public Creneau update(Long id, Creneau creneau) {
        Creneau old = getById(id);

        old.setJours(creneau.getJours());
        old.setHeureDebut(creneau.getHeureDebut());
        old.setHeureFin(creneau.getHeureFin());

        return cp.save(old);
    }

    @Override
    public void delete(Long id) {
        cp.deleteById(id);
    }

    @Override
    public Creneau reserver(Long id) {

        Creneau cn = getById(id);

        if(cn.getStatut()){
            throw new RuntimeException(
                    "Ce creneau est deja reservé"
            );
        }
        cn.setStatut(true);
        return cp.save(cn);

    }

    @Override
    public Creneau annuler(Long id) {
        Creneau cn = getById(id);
        cn.setStatut(false);
        return cp.save(cn);
    }

    @Override
    public Creneau confirmer(Long id) {
        Creneau cn = getById(id);
        if(!cn.getStatut()){
            throw new RuntimeException(
                    "Imposible de confirmer un creneau non reservé"
            );
        }
        return cn;
    }

    @Override
    public List<Creneau> getDisponibles() {
        return cp.findByStatutFalse();
    }

    @Override
    public List<Creneau> getDisponiblesByPsychologueId(Integer psychologueId) {
        return cp.findByPsychologueIdAndStatutFalse(psychologueId);
    }
}
