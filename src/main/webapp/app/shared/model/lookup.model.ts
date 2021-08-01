export interface ILookup {
  id?: string;
  lookup?: string;
  lookupType?: string;
  lookupOrder?: number;
  lookupNotes?: string;
  voided?: boolean;
}

export class Lookup implements ILookup {
  constructor(
    public id?: string,
    public lookup?: string,
    public lookupType?: string,
    public lookupOrder?: number,
    public lookupNotes?: string,
    public voided?: boolean
  ) {
    this.voided = this.voided || false;
  }
}
