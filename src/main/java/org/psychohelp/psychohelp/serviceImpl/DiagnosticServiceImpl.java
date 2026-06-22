package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.service.DiagnosticService;

public class DiagnosticServiceImpl implements DiagnosticService {
    @Override
    public String genererDiagnosticScientifique(String nomTest, int score) {
        String nomMaj = nomTest.toUpperCase();

        if (nomMaj.contains("PHQ-9")) {
            if (score <= 4)
                return "Score : " + score + "/27 (Dépression minimale ou absente). Statut sain.";

            if (score <= 9)
                return "Score : " + score + "/27 (Dépression légère). Vigilance requise.";

            if (score <= 14)
                return "Score : " + score + "/27 (Dépression modérée). Consultation recommandée.";

            if (score <= 19)
                return "Score : " + score + "/27 (Dépression modérément sévère). Suivi thérapeutique fortement conseillé.";

            return "Score : " + score + "/27 (Dépression sévère). Détresse critique. Prise en charge requise d'urgence.";
        }

        if (nomMaj.contains("GAD-7")) {
            if (score <= 4)
                return "Score : " + score + "/21 (Anxiété minimale). Dans les normes.";

            if (score <= 9)
                return "Score : " + score + "/21 (Anxiété légère). Inquiétudes diffuses décelées.";

            if (score <= 14)
                return "Score : " + score + "/21 (Anxiété modérée). Impact quotidien visible. Une consultation est utile.";

            return "Score : " + score + "/21 (Anxiété sévère). Niveau d'anxiété invalidant. Soutien psychologique impératif.";
        }

        return "Score global : " + score + " points. Bilan enregistré dans le dossier du patient.";
    }
    }

