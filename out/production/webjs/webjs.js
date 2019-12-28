if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'webjs'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'webjs'.");
}
var webjs = function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Pair = Kotlin.kotlin.Pair;
  var Unit = Kotlin.kotlin.Unit;
  var toList = Kotlin.kotlin.collections.toList_us0mfu$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var json = Kotlin.kotlin.js.json_pyyo18$;
  var throwCCE = Kotlin.throwCCE;
  var clear = Kotlin.kotlin.dom.clear_asww5s$;
  var appendElement = Kotlin.kotlin.dom.appendElement_ldvnw0$;
  var createElement = Kotlin.kotlin.dom.createElement_7cgwi1$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var HashMap = Kotlin.kotlin.collections.HashMap;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var addClass = Kotlin.kotlin.dom.addClass_hhb33f$;
  var equals = Kotlin.equals;
  var removeClass = Kotlin.kotlin.dom.removeClass_hhb33f$;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var HashMap_init = Kotlin.kotlin.collections.HashMap_init_q3lmfv$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var Map = Kotlin.kotlin.collections.Map;
  SearchCategories.prototype = Object.create(Enum.prototype);
  SearchCategories.prototype.constructor = SearchCategories;
  function EventDTO(organizerId, org_name, org_email, org_addressId, org_country, org_zip, org_city, org_street, org_house, eventId, title, description, genere, artistNames, occurrences) {
    this.organizerId = organizerId;
    this.org_name = org_name;
    this.org_email = org_email;
    this.org_addressId = org_addressId;
    this.org_country = org_country;
    this.org_zip = org_zip;
    this.org_city = org_city;
    this.org_street = org_street;
    this.org_house = org_house;
    this.eventId = eventId;
    this.title = title;
    this.description = description;
    this.genere = genere;
    this.artistNames = artistNames;
    this.occurrences = occurrences;
  }
  EventDTO.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EventDTO',
    interfaces: []
  };
  EventDTO.prototype.component1 = function () {
    return this.organizerId;
  };
  EventDTO.prototype.component2 = function () {
    return this.org_name;
  };
  EventDTO.prototype.component3 = function () {
    return this.org_email;
  };
  EventDTO.prototype.component4 = function () {
    return this.org_addressId;
  };
  EventDTO.prototype.component5 = function () {
    return this.org_country;
  };
  EventDTO.prototype.component6 = function () {
    return this.org_zip;
  };
  EventDTO.prototype.component7 = function () {
    return this.org_city;
  };
  EventDTO.prototype.component8 = function () {
    return this.org_street;
  };
  EventDTO.prototype.component9 = function () {
    return this.org_house;
  };
  EventDTO.prototype.component10 = function () {
    return this.eventId;
  };
  EventDTO.prototype.component11 = function () {
    return this.title;
  };
  EventDTO.prototype.component12 = function () {
    return this.description;
  };
  EventDTO.prototype.component13 = function () {
    return this.genere;
  };
  EventDTO.prototype.component14 = function () {
    return this.artistNames;
  };
  EventDTO.prototype.component15 = function () {
    return this.occurrences;
  };
  EventDTO.prototype.copy_wssk1p$ = function (organizerId, org_name, org_email, org_addressId, org_country, org_zip, org_city, org_street, org_house, eventId, title, description, genere, artistNames, occurrences) {
    return new EventDTO(organizerId === void 0 ? this.organizerId : organizerId, org_name === void 0 ? this.org_name : org_name, org_email === void 0 ? this.org_email : org_email, org_addressId === void 0 ? this.org_addressId : org_addressId, org_country === void 0 ? this.org_country : org_country, org_zip === void 0 ? this.org_zip : org_zip, org_city === void 0 ? this.org_city : org_city, org_street === void 0 ? this.org_street : org_street, org_house === void 0 ? this.org_house : org_house, eventId === void 0 ? this.eventId : eventId, title === void 0 ? this.title : title, description === void 0 ? this.description : description, genere === void 0 ? this.genere : genere, artistNames === void 0 ? this.artistNames : artistNames, occurrences === void 0 ? this.occurrences : occurrences);
  };
  EventDTO.prototype.toString = function () {
    return 'EventDTO(organizerId=' + Kotlin.toString(this.organizerId) + (', org_name=' + Kotlin.toString(this.org_name)) + (', org_email=' + Kotlin.toString(this.org_email)) + (', org_addressId=' + Kotlin.toString(this.org_addressId)) + (', org_country=' + Kotlin.toString(this.org_country)) + (', org_zip=' + Kotlin.toString(this.org_zip)) + (', org_city=' + Kotlin.toString(this.org_city)) + (', org_street=' + Kotlin.toString(this.org_street)) + (', org_house=' + Kotlin.toString(this.org_house)) + (', eventId=' + Kotlin.toString(this.eventId)) + (', title=' + Kotlin.toString(this.title)) + (', description=' + Kotlin.toString(this.description)) + (', genere=' + Kotlin.toString(this.genere)) + (', artistNames=' + Kotlin.toString(this.artistNames)) + (', occurrences=' + Kotlin.toString(this.occurrences)) + ')';
  };
  EventDTO.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.organizerId) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_name) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_email) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_addressId) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_country) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_zip) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_city) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_street) | 0;
    result = result * 31 + Kotlin.hashCode(this.org_house) | 0;
    result = result * 31 + Kotlin.hashCode(this.eventId) | 0;
    result = result * 31 + Kotlin.hashCode(this.title) | 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    result = result * 31 + Kotlin.hashCode(this.genere) | 0;
    result = result * 31 + Kotlin.hashCode(this.artistNames) | 0;
    result = result * 31 + Kotlin.hashCode(this.occurrences) | 0;
    return result;
  };
  EventDTO.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.organizerId, other.organizerId) && Kotlin.equals(this.org_name, other.org_name) && Kotlin.equals(this.org_email, other.org_email) && Kotlin.equals(this.org_addressId, other.org_addressId) && Kotlin.equals(this.org_country, other.org_country) && Kotlin.equals(this.org_zip, other.org_zip) && Kotlin.equals(this.org_city, other.org_city) && Kotlin.equals(this.org_street, other.org_street) && Kotlin.equals(this.org_house, other.org_house) && Kotlin.equals(this.eventId, other.eventId) && Kotlin.equals(this.title, other.title) && Kotlin.equals(this.description, other.description) && Kotlin.equals(this.genere, other.genere) && Kotlin.equals(this.artistNames, other.artistNames) && Kotlin.equals(this.occurrences, other.occurrences)))));
  };
  function Occurrence(occurrenceId, date, time, priceCategories, addressId, country, zip, city, street, house, categoryCalcDataDTO) {
    this.occurrenceId = occurrenceId;
    this.date = date;
    this.time = time;
    this.priceCategories = priceCategories;
    this.addressId = addressId;
    this.country = country;
    this.zip = zip;
    this.city = city;
    this.street = street;
    this.house = house;
    this.categoryCalcDataDTO = categoryCalcDataDTO;
  }
  Occurrence.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Occurrence',
    interfaces: []
  };
  Occurrence.prototype.component1 = function () {
    return this.occurrenceId;
  };
  Occurrence.prototype.component2 = function () {
    return this.date;
  };
  Occurrence.prototype.component3 = function () {
    return this.time;
  };
  Occurrence.prototype.component4 = function () {
    return this.priceCategories;
  };
  Occurrence.prototype.component5 = function () {
    return this.addressId;
  };
  Occurrence.prototype.component6 = function () {
    return this.country;
  };
  Occurrence.prototype.component7 = function () {
    return this.zip;
  };
  Occurrence.prototype.component8 = function () {
    return this.city;
  };
  Occurrence.prototype.component9 = function () {
    return this.street;
  };
  Occurrence.prototype.component10 = function () {
    return this.house;
  };
  Occurrence.prototype.component11 = function () {
    return this.categoryCalcDataDTO;
  };
  Occurrence.prototype.copy_nw540u$ = function (occurrenceId, date, time, priceCategories, addressId, country, zip, city, street, house, categoryCalcDataDTO) {
    return new Occurrence(occurrenceId === void 0 ? this.occurrenceId : occurrenceId, date === void 0 ? this.date : date, time === void 0 ? this.time : time, priceCategories === void 0 ? this.priceCategories : priceCategories, addressId === void 0 ? this.addressId : addressId, country === void 0 ? this.country : country, zip === void 0 ? this.zip : zip, city === void 0 ? this.city : city, street === void 0 ? this.street : street, house === void 0 ? this.house : house, categoryCalcDataDTO === void 0 ? this.categoryCalcDataDTO : categoryCalcDataDTO);
  };
  Occurrence.prototype.toString = function () {
    return 'Occurrence(occurrenceId=' + Kotlin.toString(this.occurrenceId) + (', date=' + Kotlin.toString(this.date)) + (', time=' + Kotlin.toString(this.time)) + (', priceCategories=' + Kotlin.toString(this.priceCategories)) + (', addressId=' + Kotlin.toString(this.addressId)) + (', country=' + Kotlin.toString(this.country)) + (', zip=' + Kotlin.toString(this.zip)) + (', city=' + Kotlin.toString(this.city)) + (', street=' + Kotlin.toString(this.street)) + (', house=' + Kotlin.toString(this.house)) + (', categoryCalcDataDTO=' + Kotlin.toString(this.categoryCalcDataDTO)) + ')';
  };
  Occurrence.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.occurrenceId) | 0;
    result = result * 31 + Kotlin.hashCode(this.date) | 0;
    result = result * 31 + Kotlin.hashCode(this.time) | 0;
    result = result * 31 + Kotlin.hashCode(this.priceCategories) | 0;
    result = result * 31 + Kotlin.hashCode(this.addressId) | 0;
    result = result * 31 + Kotlin.hashCode(this.country) | 0;
    result = result * 31 + Kotlin.hashCode(this.zip) | 0;
    result = result * 31 + Kotlin.hashCode(this.city) | 0;
    result = result * 31 + Kotlin.hashCode(this.street) | 0;
    result = result * 31 + Kotlin.hashCode(this.house) | 0;
    result = result * 31 + Kotlin.hashCode(this.categoryCalcDataDTO) | 0;
    return result;
  };
  Occurrence.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.occurrenceId, other.occurrenceId) && Kotlin.equals(this.date, other.date) && Kotlin.equals(this.time, other.time) && Kotlin.equals(this.priceCategories, other.priceCategories) && Kotlin.equals(this.addressId, other.addressId) && Kotlin.equals(this.country, other.country) && Kotlin.equals(this.zip, other.zip) && Kotlin.equals(this.city, other.city) && Kotlin.equals(this.street, other.street) && Kotlin.equals(this.house, other.house) && Kotlin.equals(this.categoryCalcDataDTO, other.categoryCalcDataDTO)))));
  };
  function Date_0(year, month, dayOfMonth, monthValue, chronology, dayOfWeek, era, dayOfYear, leapYear) {
    this.year = year;
    this.month = month;
    this.dayOfMonth = dayOfMonth;
    this.monthValue = monthValue;
    this.chronology = chronology;
    this.dayOfWeek = dayOfWeek;
    this.era = era;
    this.dayOfYear = dayOfYear;
    this.leapYear = leapYear;
  }
  Date_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Date',
    interfaces: []
  };
  Date_0.prototype.component1 = function () {
    return this.year;
  };
  Date_0.prototype.component2 = function () {
    return this.month;
  };
  Date_0.prototype.component3 = function () {
    return this.dayOfMonth;
  };
  Date_0.prototype.component4 = function () {
    return this.monthValue;
  };
  Date_0.prototype.component5 = function () {
    return this.chronology;
  };
  Date_0.prototype.component6 = function () {
    return this.dayOfWeek;
  };
  Date_0.prototype.component7 = function () {
    return this.era;
  };
  Date_0.prototype.component8 = function () {
    return this.dayOfYear;
  };
  Date_0.prototype.component9 = function () {
    return this.leapYear;
  };
  Date_0.prototype.copy_jydofn$ = function (year, month, dayOfMonth, monthValue, chronology, dayOfWeek, era, dayOfYear, leapYear) {
    return new Date_0(year === void 0 ? this.year : year, month === void 0 ? this.month : month, dayOfMonth === void 0 ? this.dayOfMonth : dayOfMonth, monthValue === void 0 ? this.monthValue : monthValue, chronology === void 0 ? this.chronology : chronology, dayOfWeek === void 0 ? this.dayOfWeek : dayOfWeek, era === void 0 ? this.era : era, dayOfYear === void 0 ? this.dayOfYear : dayOfYear, leapYear === void 0 ? this.leapYear : leapYear);
  };
  Date_0.prototype.toString = function () {
    return 'Date(year=' + Kotlin.toString(this.year) + (', month=' + Kotlin.toString(this.month)) + (', dayOfMonth=' + Kotlin.toString(this.dayOfMonth)) + (', monthValue=' + Kotlin.toString(this.monthValue)) + (', chronology=' + Kotlin.toString(this.chronology)) + (', dayOfWeek=' + Kotlin.toString(this.dayOfWeek)) + (', era=' + Kotlin.toString(this.era)) + (', dayOfYear=' + Kotlin.toString(this.dayOfYear)) + (', leapYear=' + Kotlin.toString(this.leapYear)) + ')';
  };
  Date_0.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.year) | 0;
    result = result * 31 + Kotlin.hashCode(this.month) | 0;
    result = result * 31 + Kotlin.hashCode(this.dayOfMonth) | 0;
    result = result * 31 + Kotlin.hashCode(this.monthValue) | 0;
    result = result * 31 + Kotlin.hashCode(this.chronology) | 0;
    result = result * 31 + Kotlin.hashCode(this.dayOfWeek) | 0;
    result = result * 31 + Kotlin.hashCode(this.era) | 0;
    result = result * 31 + Kotlin.hashCode(this.dayOfYear) | 0;
    result = result * 31 + Kotlin.hashCode(this.leapYear) | 0;
    return result;
  };
  Date_0.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.year, other.year) && Kotlin.equals(this.month, other.month) && Kotlin.equals(this.dayOfMonth, other.dayOfMonth) && Kotlin.equals(this.monthValue, other.monthValue) && Kotlin.equals(this.chronology, other.chronology) && Kotlin.equals(this.dayOfWeek, other.dayOfWeek) && Kotlin.equals(this.era, other.era) && Kotlin.equals(this.dayOfYear, other.dayOfYear) && Kotlin.equals(this.leapYear, other.leapYear)))));
  };
  function Chronology(id, calendarType) {
    this.id = id;
    this.calendarType = calendarType;
  }
  Chronology.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Chronology',
    interfaces: []
  };
  Chronology.prototype.component1 = function () {
    return this.id;
  };
  Chronology.prototype.component2 = function () {
    return this.calendarType;
  };
  Chronology.prototype.copy_puj7f4$ = function (id, calendarType) {
    return new Chronology(id === void 0 ? this.id : id, calendarType === void 0 ? this.calendarType : calendarType);
  };
  Chronology.prototype.toString = function () {
    return 'Chronology(id=' + Kotlin.toString(this.id) + (', calendarType=' + Kotlin.toString(this.calendarType)) + ')';
  };
  Chronology.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    result = result * 31 + Kotlin.hashCode(this.calendarType) | 0;
    return result;
  };
  Chronology.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.id, other.id) && Kotlin.equals(this.calendarType, other.calendarType)))));
  };
  function Time(hour, minute, second, nano) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    this.nano = nano;
  }
  Time.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Time',
    interfaces: []
  };
  Time.prototype.component1 = function () {
    return this.hour;
  };
  Time.prototype.component2 = function () {
    return this.minute;
  };
  Time.prototype.component3 = function () {
    return this.second;
  };
  Time.prototype.component4 = function () {
    return this.nano;
  };
  Time.prototype.copy_tjonv8$ = function (hour, minute, second, nano) {
    return new Time(hour === void 0 ? this.hour : hour, minute === void 0 ? this.minute : minute, second === void 0 ? this.second : second, nano === void 0 ? this.nano : nano);
  };
  Time.prototype.toString = function () {
    return 'Time(hour=' + Kotlin.toString(this.hour) + (', minute=' + Kotlin.toString(this.minute)) + (', second=' + Kotlin.toString(this.second)) + (', nano=' + Kotlin.toString(this.nano)) + ')';
  };
  Time.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.hour) | 0;
    result = result * 31 + Kotlin.hashCode(this.minute) | 0;
    result = result * 31 + Kotlin.hashCode(this.second) | 0;
    result = result * 31 + Kotlin.hashCode(this.nano) | 0;
    return result;
  };
  Time.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.hour, other.hour) && Kotlin.equals(this.minute, other.minute) && Kotlin.equals(this.second, other.second) && Kotlin.equals(this.nano, other.nano)))));
  };
  function PriceCategory(type, eventCategoryId, categoryName, priceInCents, totalTickets, usedTickets) {
    this.type = type;
    this.eventCategoryId = eventCategoryId;
    this.categoryName = categoryName;
    this.priceInCents = priceInCents;
    this.totalTickets = totalTickets;
    this.usedTickets = usedTickets;
  }
  PriceCategory.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PriceCategory',
    interfaces: []
  };
  PriceCategory.prototype.component1 = function () {
    return this.type;
  };
  PriceCategory.prototype.component2 = function () {
    return this.eventCategoryId;
  };
  PriceCategory.prototype.component3 = function () {
    return this.categoryName;
  };
  PriceCategory.prototype.component4 = function () {
    return this.priceInCents;
  };
  PriceCategory.prototype.component5 = function () {
    return this.totalTickets;
  };
  PriceCategory.prototype.component6 = function () {
    return this.usedTickets;
  };
  PriceCategory.prototype.copy_n8pm5o$ = function (type, eventCategoryId, categoryName, priceInCents, totalTickets, usedTickets) {
    return new PriceCategory(type === void 0 ? this.type : type, eventCategoryId === void 0 ? this.eventCategoryId : eventCategoryId, categoryName === void 0 ? this.categoryName : categoryName, priceInCents === void 0 ? this.priceInCents : priceInCents, totalTickets === void 0 ? this.totalTickets : totalTickets, usedTickets === void 0 ? this.usedTickets : usedTickets);
  };
  PriceCategory.prototype.toString = function () {
    return 'PriceCategory(type=' + Kotlin.toString(this.type) + (', eventCategoryId=' + Kotlin.toString(this.eventCategoryId)) + (', categoryName=' + Kotlin.toString(this.categoryName)) + (', priceInCents=' + Kotlin.toString(this.priceInCents)) + (', totalTickets=' + Kotlin.toString(this.totalTickets)) + (', usedTickets=' + Kotlin.toString(this.usedTickets)) + ')';
  };
  PriceCategory.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.type) | 0;
    result = result * 31 + Kotlin.hashCode(this.eventCategoryId) | 0;
    result = result * 31 + Kotlin.hashCode(this.categoryName) | 0;
    result = result * 31 + Kotlin.hashCode(this.priceInCents) | 0;
    result = result * 31 + Kotlin.hashCode(this.totalTickets) | 0;
    result = result * 31 + Kotlin.hashCode(this.usedTickets) | 0;
    return result;
  };
  PriceCategory.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.type, other.type) && Kotlin.equals(this.eventCategoryId, other.eventCategoryId) && Kotlin.equals(this.categoryName, other.categoryName) && Kotlin.equals(this.priceInCents, other.priceInCents) && Kotlin.equals(this.totalTickets, other.totalTickets) && Kotlin.equals(this.usedTickets, other.usedTickets)))));
  };
  function CategoryCalcDataDTO(priceRangeString, minPrice, maxPrice, ticketTypes) {
    this.priceRangeString = priceRangeString;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.ticketTypes = ticketTypes;
  }
  CategoryCalcDataDTO.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CategoryCalcDataDTO',
    interfaces: []
  };
  CategoryCalcDataDTO.prototype.component1 = function () {
    return this.priceRangeString;
  };
  CategoryCalcDataDTO.prototype.component2 = function () {
    return this.minPrice;
  };
  CategoryCalcDataDTO.prototype.component3 = function () {
    return this.maxPrice;
  };
  CategoryCalcDataDTO.prototype.component4 = function () {
    return this.ticketTypes;
  };
  CategoryCalcDataDTO.prototype.copy_6qfks0$ = function (priceRangeString, minPrice, maxPrice, ticketTypes) {
    return new CategoryCalcDataDTO(priceRangeString === void 0 ? this.priceRangeString : priceRangeString, minPrice === void 0 ? this.minPrice : minPrice, maxPrice === void 0 ? this.maxPrice : maxPrice, ticketTypes === void 0 ? this.ticketTypes : ticketTypes);
  };
  CategoryCalcDataDTO.prototype.toString = function () {
    return 'CategoryCalcDataDTO(priceRangeString=' + Kotlin.toString(this.priceRangeString) + (', minPrice=' + Kotlin.toString(this.minPrice)) + (', maxPrice=' + Kotlin.toString(this.maxPrice)) + (', ticketTypes=' + Kotlin.toString(this.ticketTypes)) + ')';
  };
  CategoryCalcDataDTO.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.priceRangeString) | 0;
    result = result * 31 + Kotlin.hashCode(this.minPrice) | 0;
    result = result * 31 + Kotlin.hashCode(this.maxPrice) | 0;
    result = result * 31 + Kotlin.hashCode(this.ticketTypes) | 0;
    return result;
  };
  CategoryCalcDataDTO.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.priceRangeString, other.priceRangeString) && Kotlin.equals(this.minPrice, other.minPrice) && Kotlin.equals(this.maxPrice, other.maxPrice) && Kotlin.equals(this.ticketTypes, other.ticketTypes)))));
  };
  function Handler() {
    Handler_instance = this;
    this.testURL_0 = 'http://localhost:8080';
    this.searchURL_0 = 'http://localhost:8080/events/findByQueryString?queryString=';
    this.spinners_0 = HashMap_init();
    this.latestSearchQuery = '';
  }
  function Handler$getSearchResults$lambda$lambda$lambda(closure$resolve, closure$res) {
    return function (it) {
      closure$resolve(new Pair(it, closure$res.status));
      return Unit;
    };
  }
  function Handler$getSearchResults$lambda$lambda(closure$text, closure$res) {
    return function (resolve, reject) {
      closure$text.then(Handler$getSearchResults$lambda$lambda$lambda(resolve, closure$res)).catch(reject);
      return Unit;
    };
  }
  function Handler$getSearchResults$lambda(res) {
    var text = res.text();
    return new Promise(Handler$getSearchResults$lambda$lambda(text, res));
  }
  function Handler$getSearchResults$lambda_0(it) {
    var tmp$;
    switch (it.second) {
      case 200:
        tmp$ = toList(JSON.parse(it.first));
        break;
      case 204:
        tmp$ = emptyList();
        break;
      case 500:
        throw Exception_init('Server exception occurred: ' + it.second);
      default:throw Exception_init('Server exception occurred: ' + it.second);
    }
    return tmp$;
  }
  function Handler$getSearchResults$lambda_1(trow) {
    console.log('Caught: ' + trow);
    throw trow;
  }
  Handler.prototype.getSearchResults_0 = function (searchQuery) {
    return window.fetch(this.searchURL_0 + searchQuery).then(Handler$getSearchResults$lambda).then(Handler$getSearchResults$lambda_0).catch(Handler$getSearchResults$lambda_1);
  };
  Handler.prototype.bookTickets_0 = function (tickets) {
    if (!tickets.isEmpty()) {
      var first_0 = first(tickets);
      var tmp$ = window;
      var tmp$_0 = this.testURL_0 + '/events/' + first_0.event.eventId + '/occurrences/' + first_0.occurrence.occurrenceId + '/categories/' + first_0.category.eventCategoryId + '/book';
      var $receiver = json([]);
      $receiver['Content-Type'] = 'application/json';
      var $receiver_0 = json([]);
      $receiver_0['clientData'] = "I'm posted by client";
      var body = JSON.stringify($receiver_0);
      var referrer;
      var referrerPolicy;
      var mode;
      var credentials;
      var cache;
      var redirect;
      var integrity;
      var keepalive;
      var window_0;
      referrer = undefined;
      referrerPolicy = undefined;
      mode = undefined;
      credentials = undefined;
      cache = undefined;
      redirect = undefined;
      integrity = undefined;
      keepalive = undefined;
      window_0 = undefined;
      var o = {};
      o['method'] = 'POST';
      o['headers'] = $receiver;
      o['body'] = body;
      o['referrer'] = referrer;
      o['referrerPolicy'] = referrerPolicy;
      o['mode'] = mode;
      o['credentials'] = credentials;
      o['cache'] = cache;
      o['redirect'] = redirect;
      o['integrity'] = integrity;
      o['keepalive'] = keepalive;
      o['window'] = window_0;
      tmp$.fetch(tmp$_0, o);
    }
  };
  function Handler$populateContainer$lambda($receiver) {
    $receiver.textContent = 'No results found';
    return Unit;
  }
  Handler.prototype.populateContainer_0 = function (eventDTOs) {
    var tmp$, tmp$_0;
    var container = Kotlin.isType(tmp$ = document.getElementById('container'), HTMLElement) ? tmp$ : throwCCE();
    clear(container);
    if (!eventDTOs.isEmpty()) {
      tmp$_0 = eventDTOs.iterator();
      while (tmp$_0.hasNext()) {
        var eventDTO = tmp$_0.next();
        container.append(this.newEventDiv_0(eventDTO), document.createElement('hr'));
      }
      OnPageAlert_getInstance().showInfo_61zpoe$('Found ' + eventDTOs.size + ' events');
    }
     else {
      appendElement(container, 'h3', Handler$populateContainer$lambda);
      OnPageAlert_getInstance().showInfo_61zpoe$('No events found');
    }
  };
  function Handler$newEventDiv$lambda$lambda$lambda$lambda(this$Handler) {
    return function ($receiver) {
      $receiver.append(this$Handler.occHeader_0());
      return Unit;
    };
  }
  function Handler$newEventDiv$lambda$lambda$lambda(this$Handler, closure$eventDTO) {
    return function ($receiver) {
      $receiver.className = 'occ-table';
      var header = createElement(document, 'thead', Handler$newEventDiv$lambda$lambda$lambda$lambda(this$Handler));
      var body = document.createElement('tbody');
      var occurrences = closure$eventDTO.occurrences;
      for (var i = 0; i !== occurrences.length; ++i) {
        console.dir(occurrences[i]);
        body.appendChild(this$Handler.occRow_0(closure$eventDTO, occurrences[i]));
      }
      $receiver.appendChild(header);
      $receiver.appendChild(body);
      return Unit;
    };
  }
  function Handler$newEventDiv$lambda$lambda(this$Handler, closure$eventDTO) {
    return function ($receiver) {
      $receiver.className = 'occ-container';
      appendElement($receiver, 'table', Handler$newEventDiv$lambda$lambda$lambda(this$Handler, closure$eventDTO));
      return Unit;
    };
  }
  function Handler$newEventDiv$lambda(this$Handler, closure$eventDTO) {
    return function ($receiver) {
      $receiver.className = 'event-element-container';
      var titleL = this$Handler.label_0('Title', 'title');
      var title = this$Handler.span_0(closure$eventDTO.title, 'title');
      var genreL = this$Handler.label_0('Genre', 'genre');
      var genre = this$Handler.span_0(closure$eventDTO.genere, 'genre');
      var occL = this$Handler.label_0('Occurrences', 'occurrences');
      var occContainer = createElement(document, 'div', Handler$newEventDiv$lambda$lambda(this$Handler, closure$eventDTO));
      $receiver.append(titleL, title, this$Handler.br_0(), genreL, genre, this$Handler.br_0(), occL, this$Handler.br_0(), occContainer);
      return Unit;
    };
  }
  Handler.prototype.newEventDiv_0 = function (eventDTO) {
    return createElement(document, 'div', Handler$newEventDiv$lambda(this, eventDTO));
  };
  function Handler$occHeader$lambda$lambda$lambda(closure$name) {
    return function ($receiver) {
      $receiver.textContent = closure$name;
      return Unit;
    };
  }
  function Handler$occHeader$lambda$lambda(closure$names) {
    return function ($receiver) {
      var tmp$;
      tmp$ = closure$names.iterator();
      while (tmp$.hasNext()) {
        var name = tmp$.next();
        $receiver.append(createElement(document, 'th', Handler$occHeader$lambda$lambda$lambda(name)));
      }
      return Unit;
    };
  }
  function Handler$occHeader$lambda(closure$names) {
    return function ($receiver) {
      $receiver.className = 'occ-table-header';
      $receiver.append(createElement(document, 'tr', Handler$occHeader$lambda$lambda(closure$names)));
      return Unit;
    };
  }
  Handler.prototype.occHeader_0 = function () {
    var names = listOf(['Date', 'Time', 'Location', 'Tickets', 'Type', 'Price', 'Action']);
    return createElement(document, 'th', Handler$occHeader$lambda(names));
  };
  function Handler$occRow$lambda$lambda$lambda(closure$event, closure$occurrence, this$Handler) {
    return function (it) {
      this$Handler.showDetail_0(closure$event, closure$occurrence);
      return Unit;
    };
  }
  function Handler$occRow$lambda(closure$occurrence, this$Handler, closure$event) {
    return function ($receiver) {
      $receiver.className = 'occ-row';
      var tmp$ = this$Handler.td_0(this$Handler.span_0(this$Handler.toDateString_0(closure$occurrence.date), 'occ-row-col-date'));
      var tmp$_0 = this$Handler.td_0(this$Handler.span_0(this$Handler.toTimeString_0(closure$occurrence.time), 'occ-row-col-time'));
      var tmp$_1 = this$Handler.td_0(this$Handler.span_0(this$Handler.toLocationString_0(closure$occurrence), 'occ-row-col-location'));
      var tmp$_2 = this$Handler.td_0(this$Handler.span_0('TODO', 'occ-row-col-tickets'));
      var tmp$_3 = this$Handler.td_0(this$Handler.span_0(closure$occurrence.categoryCalcDataDTO.ticketTypes, 'occ-row-col-type'));
      var tmp$_4 = this$Handler.td_0(this$Handler.span_0(closure$occurrence.categoryCalcDataDTO.priceRangeString, 'occ-row-col-price'));
      var tmp$_5 = this$Handler;
      var $receiver_0 = document.createElement('button');
      var closure$event_0 = closure$event;
      var closure$occurrence_0 = closure$occurrence;
      var this$Handler_0 = this$Handler;
      $receiver_0.textContent = 'Show Details';
      $receiver_0.addEventListener('click', Handler$occRow$lambda$lambda$lambda(closure$event_0, closure$occurrence_0, this$Handler_0));
      $receiver.append(tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5.td_0($receiver_0));
      return Unit;
    };
  }
  Handler.prototype.occRow_0 = function (event, occurrence) {
    console.log('exec');
    return createElement(document, 'tr', Handler$occRow$lambda(occurrence, this, event));
  };
  Handler.prototype.showDetail_0 = function (event, occurrence) {
    console.log('Detail for ' + event.eventId + ' - Occ: ' + occurrence.occurrenceId);
    var container = ensureNotNull(document.getElementById('container'));
    clear(container);
    container.append(this.detailView_0(event, occurrence));
    this.updateTotal_0();
  };
  function Handler$detailView$lambda$lambda($receiver) {
    $receiver.id = 'detail-total-view';
    return Unit;
  }
  function Handler$detailView$lambda(closure$event, closure$occurrence, this$Handler) {
    return function ($receiver) {
      var tmp$;
      $receiver.className = 'detail-div-root';
      $receiver.append(this$Handler.detailViewInfo_0(closure$event, closure$occurrence));
      var pair = this$Handler.detailViewCategories_0(closure$occurrence);
      $receiver.append(pair.first);
      this$Handler.spinners_0 = Kotlin.isType(tmp$ = pair.second, HashMap) ? tmp$ : throwCCE();
      appendElement($receiver, 'div', Handler$detailView$lambda$lambda);
      $receiver.append(this$Handler.detailViewActions_0(closure$event, closure$occurrence));
      return Unit;
    };
  }
  Handler.prototype.detailView_0 = function (event, occurrence) {
    return createElement(document, 'div', Handler$detailView$lambda(event, occurrence, this));
  };
  function Handler$detailViewActions$lambda$lambda$lambda(closure$event, closure$occurrence, this$Handler) {
    return function (it) {
      var count = this$Handler.addCurrentToCart_0(closure$event, closure$occurrence);
      if (count > 0) {
        OnPageAlert_getInstance().showInfo_61zpoe$('Added ' + count + ' items to cart.');
        openCart();
      }
      return Unit;
    };
  }
  function Handler$detailViewActions$lambda$lambda(closure$event, closure$occurrence, this$Handler) {
    return function ($receiver) {
      var tmp$;
      var btn = Kotlin.isType(tmp$ = $receiver, HTMLButtonElement) ? tmp$ : throwCCE();
      btn.textContent = 'Buy';
      btn.addEventListener('click', Handler$detailViewActions$lambda$lambda$lambda(closure$event, closure$occurrence, this$Handler));
      return Unit;
    };
  }
  function Handler$detailViewActions$lambda(closure$event, closure$occurrence, this$Handler) {
    return function ($receiver) {
      appendElement($receiver, 'button', Handler$detailViewActions$lambda$lambda(closure$event, closure$occurrence, this$Handler));
      return Unit;
    };
  }
  Handler.prototype.detailViewActions_0 = function (event, occurrence) {
    return createElement(document, 'div', Handler$detailViewActions$lambda(event, occurrence, this));
  };
  Handler.prototype.addCurrentToCart_0 = function (event, occurrence) {
    var tmp$;
    var count = 0;
    tmp$ = this.spinners_0.entries.iterator();
    while (tmp$.hasNext()) {
      var spinner = tmp$.next();
      var amount = toInt(spinner.key.value);
      for (var i = 0; i < amount; i++) {
        Cart_getInstance().add_jpnhl5$(new LocalTicket(event, occurrence, spinner.value));
        count = count + 1 | 0;
      }
    }
    return count;
  };
  function Handler$detailViewCategories$lambda$lambda$lambda$lambda(this$Handler) {
    return function (f) {
      this$Handler.updateTotal_0();
      return Unit;
    };
  }
  function Handler$detailViewCategories$lambda$lambda$lambda(closure$it, this$Handler, closure$map) {
    return function ($receiver) {
      var tmp$;
      var spinner = Kotlin.isType(tmp$ = document.createElement('input'), HTMLInputElement) ? tmp$ : throwCCE();
      spinner.type = 'number';
      spinner.min = '0';
      spinner.max = (closure$it.totalTickets - closure$it.usedTickets | 0).toString();
      spinner.step = '1';
      spinner.value = '0';
      spinner.onkeypress = Handler$detailViewCategories$lambda$lambda$lambda$lambda(this$Handler);
      var $receiver_0 = closure$map;
      var value = closure$it;
      $receiver_0.put_xwzc9p$(spinner, value);
      $receiver.append(this$Handler.span_0(closure$it.categoryName, 'cat-name'), this$Handler.span_0(this$Handler.toDisplayPrice_0(closure$it), 'cat-price'), this$Handler.span_0(this$Handler.ticketRange_0(closure$it), 'cat-ticket-range'), spinner);
      return Unit;
    };
  }
  function Handler$detailViewCategories$lambda(closure$occurrence, this$Handler, closure$map) {
    return function ($receiver) {
      var $receiver_0 = closure$occurrence.priceCategories;
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver_0.length; ++tmp$) {
        var element = $receiver_0[tmp$];
        appendElement($receiver, 'div', Handler$detailViewCategories$lambda$lambda$lambda(element, this$Handler, closure$map));
      }
      return Unit;
    };
  }
  Handler.prototype.detailViewCategories_0 = function (occurrence) {
    var map = LinkedHashMap_init();
    var createElement_0 = createElement(document, 'div', Handler$detailViewCategories$lambda(occurrence, this, map));
    return new Pair(createElement_0, map);
  };
  Handler.prototype.updateTotal_0 = function () {
    var tmp$;
    var totalPrice = 0;
    var ticketAmount = 0;
    tmp$ = this.spinners_0.entries.iterator();
    while (tmp$.hasNext()) {
      var spinner = tmp$.next();
      var tickets = toInt(spinner.key.value);
      ticketAmount = ticketAmount + tickets | 0;
      totalPrice = totalPrice + Kotlin.imul(tickets, spinner.value.priceInCents) | 0;
    }
    var totalView = ensureNotNull(document.getElementById('detail-total-view'));
    clear(totalView);
    totalView.append(this.label_0('Total', 'cat-total-price-label'), this.span_0((totalPrice / 100.0).toString() + '\u20AC', 'cat-total-price'), this.label_0('Amount', 'cat-total-amount-label'), this.span_0(ticketAmount.toString(), 'cat-total-amount'));
  };
  function Handler$detailViewInfo$lambda($receiver) {
    return Unit;
  }
  Handler.prototype.detailViewInfo_0 = function (event, occurrence) {
    return createElement(document, 'div', Handler$detailViewInfo$lambda);
  };
  function Handler$td$lambda(closure$child) {
    return function ($receiver) {
      $receiver.append(closure$child);
      return Unit;
    };
  }
  Handler.prototype.td_0 = function (child) {
    return createElement(document, 'td', Handler$td$lambda(child));
  };
  function Handler$span$lambda(closure$cssExt, closure$text) {
    return function ($receiver) {
      $receiver.className = 'event-element-' + closure$cssExt;
      $receiver.textContent = closure$text;
      return Unit;
    };
  }
  Handler.prototype.span_0 = function (text, cssExt) {
    return createElement(document, 'span', Handler$span$lambda(cssExt, text));
  };
  function Handler$label$lambda(closure$cssExt, closure$text) {
    return function ($receiver) {
      $receiver.className = 'event-element-' + closure$cssExt + '-label';
      $receiver.textContent = closure$text + ': ';
      return Unit;
    };
  }
  Handler.prototype.label_0 = function (text, cssExt) {
    return createElement(document, 'span', Handler$label$lambda(cssExt, text));
  };
  Handler.prototype.br_0 = function () {
    return document.createElement('br');
  };
  Handler.prototype.toLocationString_0 = function ($receiver) {
    return $receiver.country + ', ' + $receiver.city;
  };
  Handler.prototype.toDisplayPrice_0 = function ($receiver) {
    return ($receiver.priceInCents / 100.0).toString() + '\u20AC';
  };
  Handler.prototype.ticketRange_0 = function ($receiver) {
    return $receiver.usedTickets.toString() + ' / ' + $receiver.totalTickets;
  };
  Handler.prototype.toDateString_0 = function ($receiver) {
    return $receiver.year.toString() + '-' + $receiver.month + '-' + $receiver.dayOfMonth;
  };
  Handler.prototype.toTimeString_0 = function ($receiver) {
    return $receiver.hour.toString() + ':' + $receiver.minute;
  };
  function Handler$updateCart$lambda(closure$first, this$Handler, closure$item) {
    return function ($receiver) {
      $receiver.className = 'cart-row';
      $receiver.append(this$Handler.span_0(closure$first.event.title, 'cart-evt-title'), this$Handler.span_0(this$Handler.toDateString_0(closure$first.occurrence.date) + ':' + this$Handler.toTimeString_0(closure$first.occurrence.time), 'cart-date-time'), this$Handler.span_0(closure$first.category.categoryName, 'cart-cat-name'), this$Handler.span_0((closure$first.category.priceInCents / 100.0).toString(), 'cart-cat-price'), this$Handler.span_0(closure$item.size.toString(), 'cart-cat-amount-tickets'));
      return Unit;
    };
  }
  function Handler$updateCart$lambda$lambda(this$Handler) {
    return function (it) {
      var $receiver = Cart_getInstance().asList();
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        this$Handler.bookTickets_0(element);
      }
      return Unit;
    };
  }
  function Handler$updateCart$lambda_0(this$Handler) {
    return function ($receiver) {
      $receiver.addEventListener('click', Handler$updateCart$lambda$lambda(this$Handler));
      return Unit;
    };
  }
  function Handler$updateCart$lambda_1($receiver) {
    $receiver.textContent = 'Your Cart is Empty';
    return Unit;
  }
  Handler.prototype.updateCart = function () {
    var tmp$;
    var cartContainer = ensureNotNull(document.getElementById('cart'));
    clear(cartContainer);
    var asList = Cart_getInstance().asList();
    if (!asList.isEmpty()) {
      tmp$ = asList.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        if (!item.isEmpty()) {
          var first_0 = first(item);
          appendElement(cartContainer, 'div', Handler$updateCart$lambda(first_0, this, item));
        }
      }
      appendElement(cartContainer, 'button', Handler$updateCart$lambda_0(this));
    }
     else {
      appendElement(cartContainer, 'h3', Handler$updateCart$lambda_1);
    }
  };
  function Handler$updateBrowser$lambda(this$Handler) {
    return function (it) {
      this$Handler.populateContainer_0(it);
      return !it.isEmpty();
    };
  }
  function Handler$updateBrowser$lambda_0(it) {
    throw it;
  }
  Handler.prototype.updateBrowser = function () {
    return this.getSearchResults_0(this.latestSearchQuery).then(Handler$updateBrowser$lambda(this)).catch(Handler$updateBrowser$lambda_0);
  };
  Handler.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Handler',
    interfaces: []
  };
  var Handler_instance = null;
  function Handler_getInstance() {
    if (Handler_instance === null) {
      new Handler();
    }
    return Handler_instance;
  }
  function openCart() {
    Handler_getInstance().updateCart();
    openTab('cart');
  }
  function openBrowser$lambda(it) {
    if (it) {
      openTab('container');
    }
    return Unit;
  }
  function openBrowser$lambda_0(it) {
    OnPageAlert_getInstance().showErr_61zpoe$('Could not query search results - ' + ensureNotNull(it.message));
    return Unit;
  }
  function openBrowser() {
    Handler_getInstance().updateBrowser().then(openBrowser$lambda).catch(openBrowser$lambda_0);
  }
  function openTab(id) {
    var tmp$;
    var elementsByClassName = document.getElementsByClassName('tabcontent');
    tmp$ = elementsByClassName.length;
    for (var i = 0; i < tmp$; i++) {
      var element = elementsByClassName[i];
      addClass(ensureNotNull(element), ['none']);
      if (equals(element.id, id)) {
        removeClass(element, ['none']);
      }
    }
  }
  function main$lambda(it) {
    openTab('search');
    return Unit;
  }
  function main$lambda_0(it) {
    openBrowser();
    return Unit;
  }
  function main$lambda_1(it) {
    openCart();
    return Unit;
  }
  function main$lambda_2(it) {
    generateSearchQuery();
    openBrowser();
    return Unit;
  }
  function main$lambda_3(it) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var searchEvtTitle = Kotlin.isType(tmp$ = document.getElementById('event-title'), HTMLInputElement) ? tmp$ : throwCCE();
    var withFromCB = Kotlin.isType(tmp$_0 = document.getElementById('withFrom'), HTMLInputElement) ? tmp$_0 : throwCCE();
    var fromDatePick = Kotlin.isType(tmp$_1 = document.getElementById('fromDate'), HTMLInputElement) ? tmp$_1 : throwCCE();
    var withTillCB = Kotlin.isType(tmp$_2 = document.getElementById('withTill'), HTMLInputElement) ? tmp$_2 : throwCCE();
    var tillDatePicker = Kotlin.isType(tmp$_3 = document.getElementById('tillDate'), HTMLInputElement) ? tmp$_3 : throwCCE();
    var genreIn = Kotlin.isType(tmp$_4 = document.getElementById('genre'), HTMLInputElement) ? tmp$_4 : throwCCE();
    var artistIn = Kotlin.isType(tmp$_5 = document.getElementById('artist'), HTMLInputElement) ? tmp$_5 : throwCCE();
    var locationIn = Kotlin.isType(tmp$_6 = document.getElementById('location'), HTMLInputElement) ? tmp$_6 : throwCCE();
    searchEvtTitle.value = '';
    withFromCB.checked = false;
    withTillCB.checked = false;
    fromDatePick.value = '';
    tillDatePicker.value = '';
    genreIn.value = '';
    artistIn.value = '';
    locationIn.value = '';
    return Unit;
  }
  function main() {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3;
    (Kotlin.isType(tmp$ = document.getElementById('searchTabBtn'), HTMLButtonElement) ? tmp$ : throwCCE()).addEventListener('click', main$lambda);
    (Kotlin.isType(tmp$_0 = document.getElementById('containerBtn'), HTMLButtonElement) ? tmp$_0 : throwCCE()).addEventListener('click', main$lambda_0);
    (Kotlin.isType(tmp$_1 = document.getElementById('cartBtn'), HTMLButtonElement) ? tmp$_1 : throwCCE()).addEventListener('click', main$lambda_1);
    openTab('search');
    (Kotlin.isType(tmp$_2 = document.getElementById('searchBtn'), HTMLButtonElement) ? tmp$_2 : throwCCE()).addEventListener('click', main$lambda_2);
    var resetSearchButton = Kotlin.isType(tmp$_3 = document.getElementById('resetBtn'), HTMLButtonElement) ? tmp$_3 : throwCCE();
    resetSearchButton.addEventListener('click', main$lambda_3);
  }
  function append($receiver, s) {
    return $receiver + s;
  }
  function generateSearchQuery() {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var searchEvtTitle = Kotlin.isType(tmp$ = document.getElementById('event-title'), HTMLInputElement) ? tmp$ : throwCCE();
    var withFromCB = Kotlin.isType(tmp$_0 = document.getElementById('withFrom'), HTMLInputElement) ? tmp$_0 : throwCCE();
    var fromDatePick = Kotlin.isType(tmp$_1 = document.getElementById('fromDate'), HTMLInputElement) ? tmp$_1 : throwCCE();
    var withTillCB = Kotlin.isType(tmp$_2 = document.getElementById('withTill'), HTMLInputElement) ? tmp$_2 : throwCCE();
    var tillDatePicker = Kotlin.isType(tmp$_3 = document.getElementById('tillDate'), HTMLInputElement) ? tmp$_3 : throwCCE();
    var genreIn = Kotlin.isType(tmp$_4 = document.getElementById('genre'), HTMLInputElement) ? tmp$_4 : throwCCE();
    var artistIn = Kotlin.isType(tmp$_5 = document.getElementById('artist'), HTMLInputElement) ? tmp$_5 : throwCCE();
    var locationIn = Kotlin.isType(tmp$_6 = document.getElementById('location'), HTMLInputElement) ? tmp$_6 : throwCCE();
    var sb = '';
    if (withFromCB.checked) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$DATE_FROM_getInstance().getIdf()), '="'), fromDatePick.value), '"');
    }
    if (withTillCB.checked) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$DATE_UNTIL_getInstance().getIdf()), '="'), tillDatePicker.value), '"');
    }
    if (searchEvtTitle.value.length > 0) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$EVENT_NAME_getInstance().getIdf()), '="'), searchEvtTitle.value), '"');
    }
    if (artistIn.value.length > 0) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$ARTIST_NAME_getInstance().getIdf()), '="'), artistIn.value), '"');
    }
    if (genreIn.value.length > 0) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$GENRE_getInstance().getIdf()), '="'), genreIn.value), '"');
    }
    if (locationIn.value.length > 0) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$LOCATION_getInstance().getIdf()), '="'), locationIn.value), '"');
    }
    Handler_getInstance().latestSearchQuery = sb;
  }
  function SearchCategories(name, ordinal, catIdf) {
    Enum.call(this);
    this.catIdf_58ysqt$_0 = catIdf;
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function SearchCategories_initFields() {
    SearchCategories_initFields = function () {
    };
    SearchCategories$DATE_FROM_instance = new SearchCategories('DATE_FROM', 0, 'from');
    SearchCategories$DATE_UNTIL_instance = new SearchCategories('DATE_UNTIL', 1, 'until');
    SearchCategories$EVENT_NAME_instance = new SearchCategories('EVENT_NAME', 2, 'title');
    SearchCategories$ARTIST_NAME_instance = new SearchCategories('ARTIST_NAME', 3, 'name');
    SearchCategories$GENRE_instance = new SearchCategories('GENRE', 4, 'genre');
    SearchCategories$LOCATION_instance = new SearchCategories('LOCATION', 5, 'location');
  }
  var SearchCategories$DATE_FROM_instance;
  function SearchCategories$DATE_FROM_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$DATE_FROM_instance;
  }
  var SearchCategories$DATE_UNTIL_instance;
  function SearchCategories$DATE_UNTIL_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$DATE_UNTIL_instance;
  }
  var SearchCategories$EVENT_NAME_instance;
  function SearchCategories$EVENT_NAME_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$EVENT_NAME_instance;
  }
  var SearchCategories$ARTIST_NAME_instance;
  function SearchCategories$ARTIST_NAME_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$ARTIST_NAME_instance;
  }
  var SearchCategories$GENRE_instance;
  function SearchCategories$GENRE_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$GENRE_instance;
  }
  var SearchCategories$LOCATION_instance;
  function SearchCategories$LOCATION_getInstance() {
    SearchCategories_initFields();
    return SearchCategories$LOCATION_instance;
  }
  SearchCategories.prototype.getIdf = function () {
    return this.catIdf_58ysqt$_0;
  };
  SearchCategories.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SearchCategories',
    interfaces: [Enum]
  };
  function SearchCategories$values() {
    return [SearchCategories$DATE_FROM_getInstance(), SearchCategories$DATE_UNTIL_getInstance(), SearchCategories$EVENT_NAME_getInstance(), SearchCategories$ARTIST_NAME_getInstance(), SearchCategories$GENRE_getInstance(), SearchCategories$LOCATION_getInstance()];
  }
  SearchCategories.values = SearchCategories$values;
  function SearchCategories$valueOf(name) {
    switch (name) {
      case 'DATE_FROM':
        return SearchCategories$DATE_FROM_getInstance();
      case 'DATE_UNTIL':
        return SearchCategories$DATE_UNTIL_getInstance();
      case 'EVENT_NAME':
        return SearchCategories$EVENT_NAME_getInstance();
      case 'ARTIST_NAME':
        return SearchCategories$ARTIST_NAME_getInstance();
      case 'GENRE':
        return SearchCategories$GENRE_getInstance();
      case 'LOCATION':
        return SearchCategories$LOCATION_getInstance();
      default:throwISE('No enum constant SearchCategories.' + name);
    }
  }
  SearchCategories.valueOf_61zpoe$ = SearchCategories$valueOf;
  function Cart() {
    Cart_instance = this;
    this.items_0 = LinkedHashMap_init();
  }
  Cart.prototype.asList = function () {
    var tmp$;
    var outerList = ArrayList_init();
    tmp$ = this.items_0.entries.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      outerList.add_11rb$(item.value);
    }
    return outerList;
  };
  Cart.prototype.add_jpnhl5$ = function (localTicket) {
    var tmp$;
    var $receiver = this.items_0;
    var key = localTicket.category.eventCategoryId;
    var tmp$_0;
    if ((Kotlin.isType(tmp$_0 = $receiver, Map) ? tmp$_0 : throwCCE()).containsKey_11rb$(key)) {
      (tmp$ = this.items_0.get_11rb$(localTicket.category.eventCategoryId)) != null ? tmp$.add_11rb$(localTicket) : null;
    }
     else {
      var $receiver_0 = this.items_0;
      var key_0 = localTicket.category.eventCategoryId;
      var value = mutableListOf([localTicket]);
      $receiver_0.put_xwzc9p$(key_0, value);
    }
  };
  Cart.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Cart',
    interfaces: []
  };
  var Cart_instance = null;
  function Cart_getInstance() {
    if (Cart_instance === null) {
      new Cart();
    }
    return Cart_instance;
  }
  function LocalTicket(event, occurrence, category) {
    this.event = event;
    this.occurrence = occurrence;
    this.category = category;
  }
  LocalTicket.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LocalTicket',
    interfaces: []
  };
  LocalTicket.prototype.component1 = function () {
    return this.event;
  };
  LocalTicket.prototype.component2 = function () {
    return this.occurrence;
  };
  LocalTicket.prototype.component3 = function () {
    return this.category;
  };
  LocalTicket.prototype.copy_j48prh$ = function (event, occurrence, category) {
    return new LocalTicket(event === void 0 ? this.event : event, occurrence === void 0 ? this.occurrence : occurrence, category === void 0 ? this.category : category);
  };
  LocalTicket.prototype.toString = function () {
    return 'LocalTicket(event=' + Kotlin.toString(this.event) + (', occurrence=' + Kotlin.toString(this.occurrence)) + (', category=' + Kotlin.toString(this.category)) + ')';
  };
  LocalTicket.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.event) | 0;
    result = result * 31 + Kotlin.hashCode(this.occurrence) | 0;
    result = result * 31 + Kotlin.hashCode(this.category) | 0;
    return result;
  };
  LocalTicket.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.event, other.event) && Kotlin.equals(this.occurrence, other.occurrence) && Kotlin.equals(this.category, other.category)))));
  };
  function TicketPayload(amount, bookingInfo, rowseats) {
    this.amount = amount;
    this.bookingInfo = bookingInfo;
    this.rowseats = rowseats;
  }
  TicketPayload.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TicketPayload',
    interfaces: []
  };
  TicketPayload.prototype.component1 = function () {
    return this.amount;
  };
  TicketPayload.prototype.component2 = function () {
    return this.bookingInfo;
  };
  TicketPayload.prototype.component3 = function () {
    return this.rowseats;
  };
  TicketPayload.prototype.copy_ugxpvp$ = function (amount, bookingInfo, rowseats) {
    return new TicketPayload(amount === void 0 ? this.amount : amount, bookingInfo === void 0 ? this.bookingInfo : bookingInfo, rowseats === void 0 ? this.rowseats : rowseats);
  };
  TicketPayload.prototype.toString = function () {
    return 'TicketPayload(amount=' + Kotlin.toString(this.amount) + (', bookingInfo=' + Kotlin.toString(this.bookingInfo)) + (', rowseats=' + Kotlin.toString(this.rowseats)) + ')';
  };
  TicketPayload.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.amount) | 0;
    result = result * 31 + Kotlin.hashCode(this.bookingInfo) | 0;
    result = result * 31 + Kotlin.hashCode(this.rowseats) | 0;
    return result;
  };
  TicketPayload.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.amount, other.amount) && Kotlin.equals(this.bookingInfo, other.bookingInfo) && Kotlin.equals(this.rowseats, other.rowseats)))));
  };
  function BookingInfo(cardNr, city, country, house, nameF, nameL, street, zip) {
    this.cardNr = cardNr;
    this.city = city;
    this.country = country;
    this.house = house;
    this.nameF = nameF;
    this.nameL = nameL;
    this.street = street;
    this.zip = zip;
  }
  BookingInfo.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BookingInfo',
    interfaces: []
  };
  BookingInfo.prototype.component1 = function () {
    return this.cardNr;
  };
  BookingInfo.prototype.component2 = function () {
    return this.city;
  };
  BookingInfo.prototype.component3 = function () {
    return this.country;
  };
  BookingInfo.prototype.component4 = function () {
    return this.house;
  };
  BookingInfo.prototype.component5 = function () {
    return this.nameF;
  };
  BookingInfo.prototype.component6 = function () {
    return this.nameL;
  };
  BookingInfo.prototype.component7 = function () {
    return this.street;
  };
  BookingInfo.prototype.component8 = function () {
    return this.zip;
  };
  BookingInfo.prototype.copy_jj5peo$ = function (cardNr, city, country, house, nameF, nameL, street, zip) {
    return new BookingInfo(cardNr === void 0 ? this.cardNr : cardNr, city === void 0 ? this.city : city, country === void 0 ? this.country : country, house === void 0 ? this.house : house, nameF === void 0 ? this.nameF : nameF, nameL === void 0 ? this.nameL : nameL, street === void 0 ? this.street : street, zip === void 0 ? this.zip : zip);
  };
  BookingInfo.prototype.toString = function () {
    return 'BookingInfo(cardNr=' + Kotlin.toString(this.cardNr) + (', city=' + Kotlin.toString(this.city)) + (', country=' + Kotlin.toString(this.country)) + (', house=' + Kotlin.toString(this.house)) + (', nameF=' + Kotlin.toString(this.nameF)) + (', nameL=' + Kotlin.toString(this.nameL)) + (', street=' + Kotlin.toString(this.street)) + (', zip=' + Kotlin.toString(this.zip)) + ')';
  };
  BookingInfo.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.cardNr) | 0;
    result = result * 31 + Kotlin.hashCode(this.city) | 0;
    result = result * 31 + Kotlin.hashCode(this.country) | 0;
    result = result * 31 + Kotlin.hashCode(this.house) | 0;
    result = result * 31 + Kotlin.hashCode(this.nameF) | 0;
    result = result * 31 + Kotlin.hashCode(this.nameL) | 0;
    result = result * 31 + Kotlin.hashCode(this.street) | 0;
    result = result * 31 + Kotlin.hashCode(this.zip) | 0;
    return result;
  };
  BookingInfo.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.cardNr, other.cardNr) && Kotlin.equals(this.city, other.city) && Kotlin.equals(this.country, other.country) && Kotlin.equals(this.house, other.house) && Kotlin.equals(this.nameF, other.nameF) && Kotlin.equals(this.nameL, other.nameL) && Kotlin.equals(this.street, other.street) && Kotlin.equals(this.zip, other.zip)))));
  };
  function OnPageAlert() {
    OnPageAlert_instance = this;
  }
  function OnPageAlert$showInfo$lambda(closure$element) {
    return function () {
      return addClass(closure$element, ['none']);
    };
  }
  OnPageAlert.prototype.showInfo_61zpoe$ = function (text) {
    var element = ensureNotNull(document.getElementById('alertBox'));
    element.textContent = text;
    removeClass(element, ['alertErr', 'alertSuc', 'none']);
    addClass(element, ['alertInfo']);
    this.addCloseBtn_0(element);
    window.setTimeout(OnPageAlert$showInfo$lambda(element), 3000);
  };
  OnPageAlert.prototype.showErr_61zpoe$ = function (text) {
    var element = ensureNotNull(document.getElementById('alertBox'));
    element.textContent = text;
    removeClass(element, ['alertInfo', 'alertSuc', 'none']);
    addClass(element, ['alertErr']);
    this.addCloseBtn_0(element);
  };
  function OnPageAlert$showSuc$lambda(closure$element) {
    return function () {
      return addClass(closure$element, ['none']);
    };
  }
  OnPageAlert.prototype.showSuc_61zpoe$ = function (text) {
    var element = ensureNotNull(document.getElementById('alertBox'));
    element.textContent = text;
    removeClass(element, ['alertInfo', 'alertErr', 'none']);
    addClass(element, ['alertSuc']);
    this.addCloseBtn_0(element);
    window.setTimeout(OnPageAlert$showSuc$lambda(element), 3000);
  };
  function OnPageAlert$addCloseBtn$lambda$lambda(closure$element) {
    return function (it) {
      addClass(closure$element, ['none']);
      return Unit;
    };
  }
  function OnPageAlert$addCloseBtn$lambda(closure$element) {
    return function ($receiver) {
      addClass($receiver, ['closebtn']);
      $receiver.addEventListener('click', OnPageAlert$addCloseBtn$lambda$lambda(closure$element));
      $receiver.textContent = '\xD7';
      return Unit;
    };
  }
  OnPageAlert.prototype.addCloseBtn_0 = function (element) {
    appendElement(element, 'span', OnPageAlert$addCloseBtn$lambda(element));
  };
  OnPageAlert.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'OnPageAlert',
    interfaces: []
  };
  var OnPageAlert_instance = null;
  function OnPageAlert_getInstance() {
    if (OnPageAlert_instance === null) {
      new OnPageAlert();
    }
    return OnPageAlert_instance;
  }
  _.EventDTO = EventDTO;
  _.Occurrence = Occurrence;
  _.Date = Date_0;
  _.Chronology = Chronology;
  _.Time = Time;
  _.PriceCategory = PriceCategory;
  _.CategoryCalcDataDTO = CategoryCalcDataDTO;
  Object.defineProperty(_, 'Handler', {
    get: Handler_getInstance
  });
  _.openCart = openCart;
  _.openBrowser = openBrowser;
  _.openTab_61zpoe$ = openTab;
  _.main = main;
  _.append_rjktp$ = append;
  _.generateSearchQuery = generateSearchQuery;
  Object.defineProperty(SearchCategories, 'DATE_FROM', {
    get: SearchCategories$DATE_FROM_getInstance
  });
  Object.defineProperty(SearchCategories, 'DATE_UNTIL', {
    get: SearchCategories$DATE_UNTIL_getInstance
  });
  Object.defineProperty(SearchCategories, 'EVENT_NAME', {
    get: SearchCategories$EVENT_NAME_getInstance
  });
  Object.defineProperty(SearchCategories, 'ARTIST_NAME', {
    get: SearchCategories$ARTIST_NAME_getInstance
  });
  Object.defineProperty(SearchCategories, 'GENRE', {
    get: SearchCategories$GENRE_getInstance
  });
  Object.defineProperty(SearchCategories, 'LOCATION', {
    get: SearchCategories$LOCATION_getInstance
  });
  _.SearchCategories = SearchCategories;
  Object.defineProperty(_, 'Cart', {
    get: Cart_getInstance
  });
  _.LocalTicket = LocalTicket;
  _.TicketPayload = TicketPayload;
  _.BookingInfo = BookingInfo;
  Object.defineProperty(_, 'OnPageAlert', {
    get: OnPageAlert_getInstance
  });
  main();
  Kotlin.defineModule('webjs', _);
  return _;
}(typeof webjs === 'undefined' ? {} : webjs, kotlin);
