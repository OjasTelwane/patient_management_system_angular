export interface ILocation {
  id?: string;
  country?: string;
  countryCode?: string;
  region?: string;
  conState?: string;
  stateCode?: string;
  district?: string;
  city?: string;
  area?: string;
  pincode?: string;
}

export class Location implements ILocation {
  constructor(
    public id?: string,
    public country?: string,
    public countryCode?: string,
    public region?: string,
    public conState?: string,
    public stateCode?: string,
    public district?: string,
    public city?: string,
    public area?: string,
    public pincode?: string
  ) {}
}
