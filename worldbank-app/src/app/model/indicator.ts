import { IndicatorData } from "./indicatordata";

export interface Indicator{
  indicator:IndicatorData;
  countryiso3code:string;
  date: string;
  unit:string;
  obs_status:string;
  value:string;
  decimal:number;
}