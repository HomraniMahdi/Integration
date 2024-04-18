export class Offre {
  idOffre?: number;
    description: string = '';
    title: string = '';
    periode: Date = new Date();  
    constructor(
      idOffre?: number,
      description: string = '',
      title: string = '',
      periode: Date = new Date(),
    ) {
      this.idOffre = idOffre;
      this.description = description;
      this.title = title;
      this.periode = periode;
    }
  }
  