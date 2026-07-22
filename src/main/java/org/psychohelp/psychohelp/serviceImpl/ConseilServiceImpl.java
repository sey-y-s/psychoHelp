package org.psychohelp.psychohelp.serviceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.ConseilDtoForPyschologue;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;
import org.psychohelp.psychohelp.repository.UtilisateurRepository;
import org.psychohelp.psychohelp.service.NotificationService;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.service.ConseilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConseilServiceImpl implements ConseilService {
@Autowired
    private ConseilRepository conseilRepository;
    private final NotificationService notificationService;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Conseil creer(Conseil conseil) {
        Conseil saved = conseilRepository.save(conseil);
        Utilisateur admin = utilisateurRepository.findByRole(RoleEnum.ADMIN)
                .orElseThrow(() ->
                        new RuntimeException("Administrateur introuvable."));

        notificationService.envoyer(
                admin,
                "Nouveau conseil",
                conseil.getPsychologue().nomComplet()
                        + " a soumis un nouveau conseil.",
                TypeNotificationEnum.CONSEIL
        );

        return saved;
    }

    @Override
    public List<Conseil> listeConseil() {
        return conseilRepository.findAll();

//        return conseilRepository.trouverTousAvecPsychologue()
//                .stream()
//                .map(conseil -> new ConseilAfficheDto(
//                        conseil.getTitre(),
//                        conseil.getDescription(),
//                        conseil.getAuteur(),
//                        conseil.getPsychologue().nomComplet()
//                ))
//                .toList();
    }

    @Override
    public Conseil conseilParId(Integer id) {
        return conseilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseil non trouvé avec l'id : " + id));
    }

    @Override
    public Conseil modifier(int id, Conseil utl) {
        Conseil lastUtl = conseilParId(id);
        //lastUtl.setId(utl.getId());
        lastUtl.setTitre(utl.getTitre());
        lastUtl.setDescription(utl.getDescription());
        lastUtl.setStatus(utl.getStatus());

        lastUtl.setDatePublication(utl.getDatePublication());
        lastUtl.setAuteur(utl.getAuteur());

        conseilRepository.save(lastUtl);
        return utl;
    }

    @Override
    public void supConseil(Integer id) {
        conseilRepository.deleteById(id);
    }


    @Override
    public List<Conseil> listConseilParStatus(StatusConseilEnum status) {

//        return conseilRepository.trouverParStatutAvecPsychologue(status)
//                .stream()
//                .map(conseil -> new ConseilAfficheDto(
//                        conseil.getTitre(),
//                        conseil.getDescription(),
//                        conseil.getAuteur(),
//                        conseil.getPsychologue().nomComplet()
//                ))
//                .toList();
        return conseilRepository.findByStatus(status);
    }

    @Override
    public List<ConseilDtoForPyschologue> ConseilsByPyschologueId(HttpSession session) {
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        return  conseilRepository.ConseilsByPyschologueId(utilisateur.getId()).stream().map(
                conseil -> new ConseilDtoForPyschologue(conseil.getId(),conseil.getTitre(),conseil.getDescription(),
                        conseil.getStatus(),conseil.getDatePublication())
        ).toList();
    }


}