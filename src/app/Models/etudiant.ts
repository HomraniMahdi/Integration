// etudiant.model.ts
export class Etudiant {
  id: number;
  cin: number;
  nom: string;
  prenom: string;
  numero: number;
  email: string;
 
  conventionValidee: boolean;
  demandeStageEffectuee: boolean; // Nouveau champ pour indiquer si la demande de stage a été effectuée
  convention:any[];
  constructor(
    id: number = 0,
    cin: number = 0,
    nom: string = '',
    prenom: string = '',
    numero: number = 0,
    email: string = '',

    demandeStageEffectuee: boolean = false, // Initialisez-le à false par défaut
    convention:any,
    conventionValidee: boolean = false,
    ) {
    this.id = id;
    this.cin = cin;
    this.nom = nom;
    this.prenom = prenom;
    this.numero = numero;
    this.email = email;

    this.demandeStageEffectuee = demandeStageEffectuee; // Affectez-le au constructeur
    this.convention=convention;
    this.conventionValidee = conventionValidee;
  }
}
