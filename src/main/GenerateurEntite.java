package main;

import Entites.Entite;
import object.BottesHermes;
import object.BouclierBasique;
import object.BouclierDiamant;
import object.BouclierFer;
import object.BouclierRenforce;
import object.CapeInvisible;
import object.CarteMonde;
import object.Chaussure;
import object.Cle;
import object.CleSpeciale;
import object.Coeur;
import object.Coffre;
import object.EclairZeus;
import object.EgideAthena;
import object.Emeraude;
import object.EpeeLegendaire;
import object.EpeeLourde;
import object.EpeeNormale;
import object.Fer;
import object.Gourdin;
import object.HacheEnPierre;
import object.LameOmbre;
import object.Lance;
import object.Lanterne;
import object.Lit;
import object.Mana;
import object.MarteauThor;
import object.OrbeMagique;
import object.Pieces;
import object.PiedsNu;
import object.Pioche;
import object.Poings;
import object.Porte;
import object.PorteFer;
import object.PorteSpeciale;
import object.PotionSoin;
import object.Sceptre;

public class GenerateurEntite {
    

    Ecran ecran;

    public GenerateurEntite(Ecran ecran) {
        this.ecran = ecran;
    }

        public Entite getObject(String nom, int type) {
        Entite obj = null;
        switch(nom) {
            case HacheEnPierre.objnom:
                obj = new HacheEnPierre(ecran);
                break;
            case EpeeNormale.objnom:
                obj = new EpeeNormale(ecran);
                break;
            case Cle.objnom:
                obj = new Cle(ecran);
                break;
            case BouclierBasique.objnom:
                obj = new BouclierBasique(ecran);
                break;
            case BouclierRenforce.objnom:
                obj = new BouclierRenforce(ecran);
                break;
            case Chaussure.objnom:
                obj = new Chaussure(ecran);
                break;
            case Lanterne.objnom:
                obj = new Lanterne(ecran);
                break;
            case PotionSoin.objnom:
                obj = new PotionSoin(ecran);
                break;
            case Lit.objnom:
                obj = new Lit(ecran);
                break;
            case Porte.objnom:
                obj = new Porte(ecran);
                break;
            case Coffre.objnom:
                obj = new Coffre(ecran);
                break;
            case Pieces.objnom:
                obj = new Pieces(ecran, type);
                break;
            case Coeur.objnom:
                obj = new Coeur(ecran);
                break;
            case Mana.objnom:
                obj = new Mana(ecran);
                break;
            case Pioche.objnom:
                obj = new Pioche(ecran);
                break;
            case PorteFer.objnom:
                obj = new PorteFer(ecran);
                break;
            case EpeeLegendaire.objnom:
                obj = new EpeeLegendaire(ecran);
            case BouclierFer.objnom:
                obj = new BouclierFer(ecran);
                break;
            case BouclierDiamant.objnom:
                obj = new BouclierDiamant(ecran);
                break;
            case Poings.objnom:
                obj = new Poings(ecran);
                break;
            case PiedsNu.objnom:
                obj = new PiedsNu(ecran);
                break;
            case Lance.objnom:
                obj = new Lance(ecran);
                break;
            case Gourdin.objnom:
                obj = new Gourdin(ecran);
                break;
            case EpeeLourde.objnom:
                obj = new EpeeLourde(ecran);
                break;
            case BottesHermes.objnom:
                obj = new BottesHermes(ecran);
                break;
            case Emeraude.objnom:
                obj = new Emeraude(ecran);
                break;
            case Sceptre.objnom:
                obj = new Sceptre(ecran);
                break;
            case PorteSpeciale.objnom:
                obj = new PorteSpeciale(ecran);
                break;
            case CleSpeciale.objnom:
                obj = new CleSpeciale(ecran);
                break;
            case Fer.objnom:
                obj = new Fer(ecran);
                break;
            case CarteMonde.objnom:
                obj = new CarteMonde(ecran);
                break;
            case EgideAthena.objnom:
                obj = new EgideAthena(ecran);
                break;
            case LameOmbre.objnom:
                obj = new LameOmbre(ecran);
                break;
            case OrbeMagique.objnom:
                obj = new OrbeMagique(ecran);
                break;
            case MarteauThor.objnom:
                obj = new MarteauThor(ecran);
                break;
            case EclairZeus.objnom:
                obj = new EclairZeus(ecran);
                break;
            case CapeInvisible.objnom:
                obj = new CapeInvisible(ecran);
                break;
        }
        return obj;
    }
}
