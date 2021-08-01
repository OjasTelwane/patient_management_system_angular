import { Moment } from 'moment';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { DiscountType } from 'app/shared/model/enumerations/discount-type.model';
import { HealingLevel } from 'app/shared/model/enumerations/healing-level.model';

export interface IPatient {
  id?: string;
  formNumber?: string;
  centerId?: string;
  centerName?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  displayName?: string;
  dateOfBirth?: Moment;
  gender?: Gender;
  age?: number;
  address?: string;
  area?: string;
  city?: string;
  district?: string;
  conState?: string;
  stateCode?: string;
  region?: string;
  country?: string;
  countryCode?: string;
  pincode?: string;
  photo1ContentType?: string;
  photo1?: any;
  photo2ContentType?: string;
  photo2?: any;
  mobileNumber?: string;
  contactNo?: string;
  education?: string;
  bloodGroup?: string;
  email?: string;
  regDate?: Moment;
  profession?: string;
  marriageStatus?: string;
  expired?: boolean;
  languageName?: string;
  vegNonVeg?: boolean;
  referedBy?: string;
  referdByName?: string;
  exerciseRegularly?: boolean;
  addiction?: boolean;
  hoursSleep?: number;
  wakeRested?: boolean;
  waterIntake?: number;
  hoursWork?: number;
  shiftWork?: boolean;
  levelOfStress?: string;
  patientCondition?: string;
  currentMedication?: string;
  chronologicalHistory?: string;
  symptomaticChanges?: string;
  levelOfDiscomfort?: number;
  feesDiscount?: boolean;
  feesDiscountGivenBy?: string;
  discountType?: DiscountType;
  discount?: number;
  allowEmail?: boolean;
  allowSMS?: boolean;
  allowWhatsApp?: boolean;
  allowPromotions?: boolean;
  iAgree?: boolean;
  healingLevel?: HealingLevel;
  allowLogin?: boolean;
  password?: string;
  voided?: boolean;
  userId?: string;
}

export class Patient implements IPatient {
  constructor(
    public id?: string,
    public formNumber?: string,
    public centerId?: string,
    public centerName?: string,
    public firstName?: string,
    public middleName?: string,
    public lastName?: string,
    public displayName?: string,
    public dateOfBirth?: Moment,
    public gender?: Gender,
    public age?: number,
    public address?: string,
    public area?: string,
    public city?: string,
    public district?: string,
    public conState?: string,
    public stateCode?: string,
    public region?: string,
    public country?: string,
    public countryCode?: string,
    public pincode?: string,
    public photo1ContentType?: string,
    public photo1?: any,
    public photo2ContentType?: string,
    public photo2?: any,
    public mobileNumber?: string,
    public contactNo?: string,
    public education?: string,
    public bloodGroup?: string,
    public email?: string,
    public regDate?: Moment,
    public profession?: string,
    public marriageStatus?: string,
    public expired?: boolean,
    public languageName?: string,
    public vegNonVeg?: boolean,
    public referedBy?: string,
    public referdByName?: string,
    public exerciseRegularly?: boolean,
    public addiction?: boolean,
    public hoursSleep?: number,
    public wakeRested?: boolean,
    public waterIntake?: number,
    public hoursWork?: number,
    public shiftWork?: boolean,
    public levelOfStress?: string,
    public patientCondition?: string,
    public currentMedication?: string,
    public chronologicalHistory?: string,
    public symptomaticChanges?: string,
    public levelOfDiscomfort?: number,
    public feesDiscount?: boolean,
    public feesDiscountGivenBy?: string,
    public discountType?: DiscountType,
    public discount?: number,
    public allowEmail?: boolean,
    public allowSMS?: boolean,
    public allowWhatsApp?: boolean,
    public allowPromotions?: boolean,
    public iAgree?: boolean,
    public healingLevel?: HealingLevel,
    public allowLogin?: boolean,
    public password?: string,
    public voided?: boolean,
    public userId?: string
  ) {
    this.expired = this.expired || false;
    this.vegNonVeg = this.vegNonVeg || false;
    this.exerciseRegularly = this.exerciseRegularly || false;
    this.addiction = this.addiction || false;
    this.wakeRested = this.wakeRested || false;
    this.shiftWork = this.shiftWork || false;
    this.feesDiscount = this.feesDiscount || false;
    this.allowEmail = this.allowEmail || false;
    this.allowSMS = this.allowSMS || false;
    this.allowWhatsApp = this.allowWhatsApp || false;
    this.allowPromotions = this.allowPromotions || false;
    this.iAgree = this.iAgree || false;
    this.allowLogin = this.allowLogin || false;
    this.voided = this.voided || false;
  }
}
