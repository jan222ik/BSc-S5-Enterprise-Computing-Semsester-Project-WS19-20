if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kotlinweb'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlinweb'.");
}
var kotlinweb = function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Pair = Kotlin.kotlin.Pair;
  var Unit = Kotlin.kotlin.Unit;
  var toList = Kotlin.kotlin.collections.toList_us0mfu$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var first = Kotlin.kotlin.collections.first_2p1efm$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var json = Kotlin.kotlin.js.json_pyyo18$;
  var throwCCE = Kotlin.throwCCE;
  var clear = Kotlin.kotlin.dom.clear_asww5s$;
  var appendElement = Kotlin.kotlin.dom.appendElement_ldvnw0$;
  var createElement = Kotlin.kotlin.dom.createElement_7cgwi1$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var HashMap = Kotlin.kotlin.collections.HashMap;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var NumberFormatException = Kotlin.kotlin.NumberFormatException;
  var joinToString = Kotlin.kotlin.collections.joinToString_cgipc5$;
  var equals = Kotlin.equals;
  var toString = Kotlin.toString;
  var joinToString_0 = Kotlin.kotlin.collections.joinToString_fmv235$;
  var toCollection = Kotlin.kotlin.collections.toCollection_5cfyqp$;
  var Any = Object;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var addClass = Kotlin.kotlin.dom.addClass_hhb33f$;
  var removeClass = Kotlin.kotlin.dom.removeClass_hhb33f$;
  var split = Kotlin.kotlin.text.split_o64adg$;
  var reversed = Kotlin.kotlin.collections.reversed_7wnvza$;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var HashMap_init = Kotlin.kotlin.collections.HashMap_init_q3lmfv$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var NoSuchElementException_init = Kotlin.kotlin.NoSuchElementException;
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
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
  function PriceCategory(type, eventCategoryId, categoryName, priceInCents, totalTickets, usedTickets, rows) {
    this.type = type;
    this.eventCategoryId = eventCategoryId;
    this.categoryName = categoryName;
    this.priceInCents = priceInCents;
    this.totalTickets = totalTickets;
    this.usedTickets = usedTickets;
    this.rows = rows;
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
  PriceCategory.prototype.component7 = function () {
    return this.rows;
  };
  PriceCategory.prototype.copy_vpunrg$ = function (type, eventCategoryId, categoryName, priceInCents, totalTickets, usedTickets, rows) {
    return new PriceCategory(type === void 0 ? this.type : type, eventCategoryId === void 0 ? this.eventCategoryId : eventCategoryId, categoryName === void 0 ? this.categoryName : categoryName, priceInCents === void 0 ? this.priceInCents : priceInCents, totalTickets === void 0 ? this.totalTickets : totalTickets, usedTickets === void 0 ? this.usedTickets : usedTickets, rows === void 0 ? this.rows : rows);
  };
  PriceCategory.prototype.toString = function () {
    return 'PriceCategory(type=' + Kotlin.toString(this.type) + (', eventCategoryId=' + Kotlin.toString(this.eventCategoryId)) + (', categoryName=' + Kotlin.toString(this.categoryName)) + (', priceInCents=' + Kotlin.toString(this.priceInCents)) + (', totalTickets=' + Kotlin.toString(this.totalTickets)) + (', usedTickets=' + Kotlin.toString(this.usedTickets)) + (', rows=' + Kotlin.toString(this.rows)) + ')';
  };
  PriceCategory.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.type) | 0;
    result = result * 31 + Kotlin.hashCode(this.eventCategoryId) | 0;
    result = result * 31 + Kotlin.hashCode(this.categoryName) | 0;
    result = result * 31 + Kotlin.hashCode(this.priceInCents) | 0;
    result = result * 31 + Kotlin.hashCode(this.totalTickets) | 0;
    result = result * 31 + Kotlin.hashCode(this.usedTickets) | 0;
    result = result * 31 + Kotlin.hashCode(this.rows) | 0;
    return result;
  };
  PriceCategory.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.type, other.type) && Kotlin.equals(this.eventCategoryId, other.eventCategoryId) && Kotlin.equals(this.categoryName, other.categoryName) && Kotlin.equals(this.priceInCents, other.priceInCents) && Kotlin.equals(this.totalTickets, other.totalTickets) && Kotlin.equals(this.usedTickets, other.usedTickets) && Kotlin.equals(this.rows, other.rows)))));
  };
  function Row(rowId, rowIdf, seats) {
    this.rowId = rowId;
    this.rowIdf = rowIdf;
    this.seats = seats;
  }
  Row.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Row',
    interfaces: []
  };
  Row.prototype.component1 = function () {
    return this.rowId;
  };
  Row.prototype.component2 = function () {
    return this.rowIdf;
  };
  Row.prototype.component3 = function () {
    return this.seats;
  };
  Row.prototype.copy_ed885b$ = function (rowId, rowIdf, seats) {
    return new Row(rowId === void 0 ? this.rowId : rowId, rowIdf === void 0 ? this.rowIdf : rowIdf, seats === void 0 ? this.seats : seats);
  };
  Row.prototype.toString = function () {
    return 'Row(rowId=' + Kotlin.toString(this.rowId) + (', rowIdf=' + Kotlin.toString(this.rowIdf)) + (', seats=' + Kotlin.toString(this.seats)) + ')';
  };
  Row.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rowId) | 0;
    result = result * 31 + Kotlin.hashCode(this.rowIdf) | 0;
    result = result * 31 + Kotlin.hashCode(this.seats) | 0;
    return result;
  };
  Row.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.rowId, other.rowId) && Kotlin.equals(this.rowIdf, other.rowIdf) && Kotlin.equals(this.seats, other.seats)))));
  };
  function Seat(free, seatId, isFree, seatIdf) {
    this.free = free;
    this.seatId = seatId;
    this.isFree = isFree;
    this.seatIdf = seatIdf;
  }
  Seat.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Seat',
    interfaces: []
  };
  Seat.prototype.component1 = function () {
    return this.free;
  };
  Seat.prototype.component2 = function () {
    return this.seatId;
  };
  Seat.prototype.component3 = function () {
    return this.isFree;
  };
  Seat.prototype.component4 = function () {
    return this.seatIdf;
  };
  Seat.prototype.copy_5msc62$ = function (free, seatId, isFree, seatIdf) {
    return new Seat(free === void 0 ? this.free : free, seatId === void 0 ? this.seatId : seatId, isFree === void 0 ? this.isFree : isFree, seatIdf === void 0 ? this.seatIdf : seatIdf);
  };
  Seat.prototype.toString = function () {
    return 'Seat(free=' + Kotlin.toString(this.free) + (', seatId=' + Kotlin.toString(this.seatId)) + (', isFree=' + Kotlin.toString(this.isFree)) + (', seatIdf=' + Kotlin.toString(this.seatIdf)) + ')';
  };
  Seat.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.free) | 0;
    result = result * 31 + Kotlin.hashCode(this.seatId) | 0;
    result = result * 31 + Kotlin.hashCode(this.isFree) | 0;
    result = result * 31 + Kotlin.hashCode(this.seatIdf) | 0;
    return result;
  };
  Seat.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.free, other.free) && Kotlin.equals(this.seatId, other.seatId) && Kotlin.equals(this.isFree, other.isFree) && Kotlin.equals(this.seatIdf, other.seatIdf)))));
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
    this.searchURL_0 = '/events/findByQueryString?queryString=';
    this.spinners_0 = HashMap_init();
    this.latestSearchQuery = '';
    this.latestBookingInfo_0 = null;
    this.currentDetailedSeats = LinkedHashMap_init();
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
  function Handler$bookTickets$lambda$lambda$lambda(closure$resolve, closure$res) {
    return function (it) {
      closure$resolve(new Pair(it, closure$res.status));
      return Unit;
    };
  }
  function Handler$bookTickets$lambda$lambda(closure$text, closure$res) {
    return function (resolve, reject) {
      closure$text.then(Handler$bookTickets$lambda$lambda$lambda(resolve, closure$res)).catch(reject);
      return Unit;
    };
  }
  function Handler$bookTickets$lambda(closure$first) {
    return function (res) {
      var text = res.text();
      console.info('Response for events/' + closure$first.event.eventId + '/occurrences/' + closure$first.occurrence.occurrenceId + '/categories/' + closure$first.category.eventCategoryId);
      return new Promise(Handler$bookTickets$lambda$lambda(text, res));
    };
  }
  function Handler$bookTickets$lambda_0(it) {
    var tmp$;
    switch (it.second) {
      case 200:
      case 409:
        tmp$ = toList(JSON.parse(it.first));
        break;
      case 500:
        throw Exception_init('Server exception occurred: ' + it.second);
      default:throw Exception_init('Server exception occurred: ' + it.second);
    }
    return tmp$;
  }
  function Handler$bookTickets$lambda_1(trow) {
    console.log('Caught: ' + trow);
    throw trow;
  }
  function Handler$bookTickets$lambda_2(resolve, f) {
    resolve(emptyList());
    return Unit;
  }
  Handler.prototype.bookTickets_0 = function (tickets) {
    if (!tickets.isEmpty()) {
      var first_0 = first(tickets);
      var rowSeats = ArrayList_init();
      if (first_0.row != null) {
        var tmp$;
        tmp$ = tickets.iterator();
        while (tmp$.hasNext()) {
          var element = tmp$.next();
          rowSeats.add_11rb$(new RowSeat(ensureNotNull(element.row).rowId, ensureNotNull(element.seat).seatId));
        }
      }
      console.info('Request for events/' + first_0.event.eventId + '/occurrences/' + first_0.occurrence.occurrenceId + '/categories/' + first_0.category.eventCategoryId);
      var tmp$_0 = window;
      var tmp$_1 = '/events/' + first_0.event.eventId + '/occurrences/' + first_0.occurrence.occurrenceId + '/categories/' + first_0.category.eventCategoryId + '/book';
      var $receiver = json([]);
      $receiver['Content-Type'] = 'application/json';
      var body = JSON.stringify(new TicketPayload(tickets.size, ensureNotNull(this.latestBookingInfo_0), copyToArray(rowSeats)));
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
      return tmp$_0.fetch(tmp$_1, o).then(Handler$bookTickets$lambda(first_0)).then(Handler$bookTickets$lambda_0).catch(Handler$bookTickets$lambda_1);
    }
    else {
      return new Promise(Handler$bookTickets$lambda_2);
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
    var names = listOf(['Date', 'Time', 'Location', 'Type', 'Price', 'Action']);
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
      var tmp$_2 = this$Handler.td_0(this$Handler.span_0(closure$occurrence.categoryCalcDataDTO.ticketTypes, 'occ-row-col-type'));
      var tmp$_3 = this$Handler.td_0(this$Handler.span_0(closure$occurrence.categoryCalcDataDTO.priceRangeString, 'occ-row-col-price'));
      var tmp$_4 = this$Handler;
      var $receiver_0 = document.createElement('button');
      var closure$event_0 = closure$event;
      var closure$occurrence_0 = closure$occurrence;
      var this$Handler_0 = this$Handler;
      $receiver_0.textContent = 'Show Details';
      $receiver_0.addEventListener('click', Handler$occRow$lambda$lambda$lambda(closure$event_0, closure$occurrence_0, this$Handler_0));
      $receiver.append(tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4.td_0($receiver_0));
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
      btn.textContent = 'Add to cart';
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
    var tmp$, tmp$_0;
    var count = {v: 0};
    tmp$ = this.spinners_0.entries.iterator();
    while (tmp$.hasNext()) {
      var spinner = tmp$.next();
      var amount = toInt(spinner.key.value);
      if (spinner.value.rows == null) {
        if (amount <= (spinner.value.totalTickets - spinner.value.usedTickets | 0)) {
          for (var i = 0; i < amount; i++) {
            Cart_getInstance().add_jpnhl5$(new LocalTicket(event, occurrence, spinner.value));
            count.v = count.v + 1 | 0;
          }
        }
        else {
          spinner.key.value = spinner.key.max;
        }
      }
      else {
        console.log('Adding seats to cart');
        if ((tmp$_0 = this.currentDetailedSeats.get_11rb$(spinner.value.eventCategoryId)) != null) {
          var tmp$_1;
          tmp$_1 = tmp$_0.iterator();
          loop_label: while (tmp$_1.hasNext()) {
            var element = tmp$_1.next();
            var $receiver = spinner.value.rows;
            var first$result;
            first$break: do {
              var tmp$_2;
              loop_label_0: for (tmp$_2 = 0; tmp$_2 !== $receiver.length; ++tmp$_2) {
                var element_0 = $receiver[tmp$_2];
                var $receiver_0 = element_0.seats;
                var any$result;
                any$break: do {
                  var tmp$_3;
                  for (tmp$_3 = 0; tmp$_3 !== $receiver_0.length; ++tmp$_3) {
                    var element_1 = $receiver_0[tmp$_3];
                    if (element_1.seatId === element.seatId) {
                      any$result = true;
                      break any$break;
                    }
                  }
                  any$result = false;
                }
                while (false);
                if (any$result) {
                  first$result = element_0;
                  break first$break;
                }
              }
              throw new NoSuchElementException_init('Array contains no element matching the predicate.');
            }
            while (false);
            var first = first$result;
            Cart_getInstance().add_jpnhl5$(new LocalTicket(event, occurrence, spinner.value, first, element));
            count.v = count.v + 1 | 0;
          }
        }
      }
    }
    return count.v;
  };
  function Handler$detailViewCategories$lambda(closure$occurrence, closure$map, this$Handler) {
    return function ($receiver) {
      var $receiver_0 = closure$occurrence.priceCategories;
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver_0.length; ++tmp$) {
        var element = $receiver_0[tmp$];
        var closure$map_0 = closure$map;
        var this$Handler_0 = this$Handler;
        var tmp$_0;
        console.dir(element);
        if (element.rows != null) {
          tmp$_0 = this$Handler_0.seatSeats_0(element, closure$map_0);
        }
        else {
          tmp$_0 = this$Handler_0.freeSeats_0(element, closure$map_0);
        }
        $receiver.append(tmp$_0);
      }
      return Unit;
    };
  }
  Handler.prototype.detailViewCategories_0 = function (occurrence) {
    var map = LinkedHashMap_init();
    this.currentDetailedSeats = LinkedHashMap_init();
    var createElement_0 = createElement(document, 'div', Handler$detailViewCategories$lambda(occurrence, map, this));
    return new Pair(createElement_0, map);
  };
  function Handler$seatSeats$lambda$lambda($receiver) {
    var tmp$;
    Kotlin.isType(tmp$ = $receiver, HTMLInputElement) ? tmp$ : throwCCE();
    $receiver.type = 'hidden';
    $receiver.value = '0';
    return Unit;
  }
  function Handler$seatSeats$lambda$lambda$lambda$lambda$lambda$lambda$lambda(closure$marked, this$, closure$hiddenSpinner, this$Handler, closure$cat, closure$it) {
    return function (f) {
      var tmp$, tmp$_0;
      if (closure$marked.v) {
        closure$marked.v = false;
        this$.classList.remove('marked');
        closure$hiddenSpinner.value = (toInt(closure$hiddenSpinner.value) - 1 | 0).toString();
        (tmp$ = this$Handler.currentDetailedSeats.get_11rb$(closure$cat.eventCategoryId)) != null ? tmp$.remove_11rb$(closure$it) : null;
      }
      else {
        this$.classList.add('marked');
        closure$marked.v = true;
        closure$hiddenSpinner.value = (toInt(closure$hiddenSpinner.value) + 1 | 0).toString();
        (tmp$_0 = this$Handler.currentDetailedSeats.get_11rb$(closure$cat.eventCategoryId)) != null ? tmp$_0.add_11rb$(closure$it) : null;
      }
      this$Handler.updateTotal_0();
      return Unit;
    };
  }
  function Handler$seatSeats$lambda$lambda$lambda$lambda$lambda$lambda(closure$it, this$Handler, closure$cat, closure$hiddenSpinner) {
    return function ($receiver) {
      $receiver.textContent = closure$it.seatIdf;
      var marked = {v: !closure$it.isFree};
      var tmp$ = this$Handler.currentDetailedSeats;
      var tmp$_0 = closure$cat.eventCategoryId;
      var value = ArrayList_init();
      tmp$.put_xwzc9p$(tmp$_0, value);
      if (!marked.v) {
        $receiver.addEventListener('click', Handler$seatSeats$lambda$lambda$lambda$lambda$lambda$lambda$lambda(marked, $receiver, closure$hiddenSpinner, this$Handler, closure$cat, closure$it));
      }
      else {
        $receiver.classList.add('blocked');
      }
      return Unit;
    };
  }
  function Handler$seatSeats$lambda$lambda$lambda$lambda(closure$it, this$Handler, closure$cat, closure$hiddenSpinner) {
    return function ($receiver) {
      $receiver.append(this$Handler.label_0(closure$it.rowIdf));
      var $receiver_0 = closure$it.seats;
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver_0.length; ++tmp$) {
        var element = $receiver_0[tmp$];
        appendElement($receiver, 'button', Handler$seatSeats$lambda$lambda$lambda$lambda$lambda$lambda(element, this$Handler, closure$cat, closure$hiddenSpinner));
      }
      return Unit;
    };
  }
  function Handler$seatSeats$lambda$lambda_0(closure$cat, this$Handler, closure$hiddenSpinner) {
    return function ($receiver) {
      var $receiver_0 = closure$cat.rows;
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver_0.length; ++tmp$) {
        var element = $receiver_0[tmp$];
        appendElement($receiver, 'div', Handler$seatSeats$lambda$lambda$lambda$lambda(element, this$Handler, closure$cat, closure$hiddenSpinner));
      }
      return Unit;
    };
  }
  function Handler$seatSeats$lambda(closure$cat, closure$map, this$Handler) {
    return function ($receiver) {
      var tmp$;
      var hiddenSpinner = Kotlin.isType(tmp$ = createElement(document, 'input', Handler$seatSeats$lambda$lambda), HTMLInputElement) ? tmp$ : throwCCE();
      $receiver.append(hiddenSpinner);
      var $receiver_0 = closure$map;
      var value = closure$cat;
      $receiver_0.put_xwzc9p$(hiddenSpinner, value);
      appendElement($receiver, 'div', Handler$seatSeats$lambda$lambda_0(closure$cat, this$Handler, hiddenSpinner));
      return Unit;
    };
  }
  Handler.prototype.seatSeats_0 = function (cat, map) {
    return createElement(document, 'div', Handler$seatSeats$lambda(cat, map, this));
  };
  function Handler$freeSeats$lambda$lambda(closure$spinner, closure$cat, this$Handler) {
    return function (f) {
      try {
        var toInt_0 = toInt(closure$spinner.value);
        if (toInt_0 > (closure$cat.totalTickets - closure$cat.usedTickets | 0)) {
          closure$spinner.value = closure$spinner.max;
        }
        return this$Handler.updateTotal_0(), Unit;
      }
      catch (e) {
        if (Kotlin.isType(e, NumberFormatException)) {
          return replace(closure$spinner.value, '\\D', '');
        }
        else
          throw e;
      }
    };
  }
  function Handler$freeSeats$lambda(closure$cat, this$Handler, closure$map) {
    return function ($receiver) {
      var tmp$;
      var spinner = Kotlin.isType(tmp$ = document.createElement('input'), HTMLInputElement) ? tmp$ : throwCCE();
      spinner.type = 'number';
      spinner.min = '0';
      spinner.max = (closure$cat.totalTickets - closure$cat.usedTickets | 0).toString();
      spinner.step = '1';
      spinner.value = '0';
      spinner.oninput = Handler$freeSeats$lambda$lambda(spinner, closure$cat, this$Handler);
      var $receiver_0 = closure$map;
      var value = closure$cat;
      $receiver_0.put_xwzc9p$(spinner, value);
      $receiver.append(this$Handler.span_0(closure$cat.categoryName, 'cat-name'), this$Handler.span_0(this$Handler.toDisplayPrice_0(closure$cat), 'cat-price'), this$Handler.span_0(this$Handler.ticketRange_0(closure$cat), 'cat-ticket-range'), spinner);
      return Unit;
    };
  }
  Handler.prototype.freeSeats_0 = function (cat, map) {
    return createElement(document, 'div', Handler$freeSeats$lambda(cat, this, map));
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
  function Handler$detailViewInfo$lambda$lambda($receiver) {
    $receiver.textContent = 'More Information';
    return Unit;
  }
  function Handler$detailViewInfo$lambda$lambda_0(this$Handler, closure$event, closure$occurrence) {
    return function ($receiver) {
      $receiver.append(this$Handler.li_0([this$Handler.label_0('Title'), this$Handler.span_0(closure$event.title)]), this$Handler.li_0([this$Handler.label_0('Date'), this$Handler.span_0(this$Handler.toDateString_0(closure$occurrence.date)), this$Handler.span_0(this$Handler.toTimeString_0(closure$occurrence.time))]), this$Handler.li_0([this$Handler.label_0('Location'), this$Handler.span_0(this$Handler.toLocationString_0(closure$occurrence))]), this$Handler.li_0([this$Handler.label_0('Description'), this$Handler.span_0(closure$event.description)]), this$Handler.li_0([this$Handler.label_0('Genre'), this$Handler.span_0(closure$event.genere)]), this$Handler.li_0([this$Handler.label_0('Artists'), this$Handler.span_0(joinToString(closure$event.artistNames, ', '))]));
      return Unit;
    };
  }
  function Handler$detailViewInfo$lambda(this$Handler, closure$event, closure$occurrence) {
    return function ($receiver) {
      appendElement($receiver, 'h3', Handler$detailViewInfo$lambda$lambda);
      appendElement($receiver, 'ul', Handler$detailViewInfo$lambda$lambda_0(this$Handler, closure$event, closure$occurrence));
      return Unit;
    };
  }
  Handler.prototype.detailViewInfo_0 = function (event, occurrence) {
    return createElement(document, 'div', Handler$detailViewInfo$lambda(this, event, occurrence));
  };
  function Handler$li$lambda(closure$child) {
    return function ($receiver) {
      var tmp$, tmp$_0;
      tmp$ = closure$child;
      for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
        var c = tmp$[tmp$_0];
        $receiver.append(c);
      }
      return Unit;
    };
  }
  Handler.prototype.li_0 = function (child) {
    return createElement(document, 'li', Handler$li$lambda(child));
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
    if (cssExt === void 0)
      cssExt = '';
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
    if (cssExt === void 0)
      cssExt = '';
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
    var tmp$, tmp$_0;
    tmp$ = $receiver.usedTickets;
    if (tmp$ == null || equals(tmp$, undefined))
      tmp$_0 = '';
    else
      tmp$_0 = $receiver.usedTickets.toString() + ' / ' + $receiver.totalTickets;
    return tmp$_0;
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
      this$Handler.showPaymentInfo_0();
      return Unit;
    };
  }
  function Handler$updateCart$lambda_0(this$Handler) {
    return function ($receiver) {
      $receiver.textContent = 'Buy';
      $receiver.className = 'cart-buy';
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
  Handler.prototype.showPaymentInfo_0 = function () {
    openTab('paymentInfo');
    this.fillLatestPaymentInfo_0();
  };
  function Handler$showResult$lambda$lambda$lambda$lambda(closure$mutableEntry, closure$count, this$Handler) {
    return function ($receiver) {
      var $receiver_0 = closure$mutableEntry.value;
      var tmp$, tmp$_0;
      var index = 0;
      tmp$ = $receiver_0.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        var closure$count_0 = closure$count;
        var this$Handler_0 = this$Handler;
        var index_0 = checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0));
        closure$count_0.v = closure$count_0.v + 1 | 0;
        $receiver.append(this$Handler_0.span_0((index_0 + 1 | 0).toString() + ':' + '\t' + '\t', ''), this$Handler_0.span_0('ID: ' + toString(item.tranactionId), ''), this$Handler_0.br_0());
      }
      return Unit;
    };
  }
  function Handler$showResult$lambda$lambda$lambda(closure$index, this$Handler, closure$mutableEntry, closure$count) {
    return function ($receiver) {
      $receiver.append(this$Handler.span_0('#' + closure$index, ''), this$Handler.span_0(first(closure$mutableEntry.key).event.title, ''), this$Handler.span_0(this$Handler.toDateString_0(first(closure$mutableEntry.key).occurrence.date) + ' - ' + this$Handler.toTimeString_0(first(closure$mutableEntry.key).occurrence.time), ''), this$Handler.span_0(first(closure$mutableEntry.key).category.categoryName, ''), this$Handler.br_0(), this$Handler.label_0('Transaction Ids', ''), this$Handler.br_0());
      appendElement($receiver, 'div', Handler$showResult$lambda$lambda$lambda$lambda(closure$mutableEntry, closure$count, this$Handler));
      return Unit;
    };
  }
  function Handler$showResult$lambda$lambda$lambda$lambda_0(closure$mutableEntry, this$Handler) {
    return function ($receiver) {
      var $receiver_0 = closure$mutableEntry.value;
      $receiver.append(this$Handler.span_0('Failed due to: ' + toString($receiver_0.errMsg), ''));
      return Unit;
    };
  }
  function Handler$showResult$lambda$lambda$lambda_0(closure$index, this$Handler, closure$mutableEntry) {
    return function ($receiver) {
      $receiver.append(this$Handler.span_0('#' + closure$index, ''), this$Handler.span_0(first(closure$mutableEntry.key).event.title, ''), this$Handler.span_0(this$Handler.toDateString_0(first(closure$mutableEntry.key).occurrence.date) + ' - ' + this$Handler.toTimeString_0(first(closure$mutableEntry.key).occurrence.time), ''), this$Handler.span_0(first(closure$mutableEntry.key).category.categoryName, ''), this$Handler.br_0());
      appendElement($receiver, 'div', Handler$showResult$lambda$lambda$lambda$lambda_0(closure$mutableEntry, this$Handler));
      return Unit;
    };
  }
  Handler.prototype.showResult_0 = function (bookedTickets, errors) {
    var tmp$, tmp$_0;
    openTab('res');
    var $receiver = Kotlin.isType(tmp$ = document.getElementById('suc'), HTMLDivElement) ? tmp$ : throwCCE();
    var count = {v: 0};
    clear($receiver);
    var tmp$_1, tmp$_0_0;
    var index = 0;
    tmp$_1 = bookedTickets.entries.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      appendElement($receiver, 'div', Handler$showResult$lambda$lambda$lambda(checkIndexOverflow((tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0)), this, item, count));
    }
    if (count.v > 0) {
      OnPageAlert_getInstance().showSuc_61zpoe$('Booked ' + count.v + ' tickets.');
    }
    var $receiver_0 = Kotlin.isType(tmp$_0 = document.getElementById('err'), HTMLDivElement) ? tmp$_0 : throwCCE();
    clear($receiver_0);
    var tmp$_2, tmp$_0_1;
    var index_0 = 0;
    tmp$_2 = errors.entries.iterator();
    while (tmp$_2.hasNext()) {
      var item_0 = tmp$_2.next();
      appendElement($receiver_0, 'div', Handler$showResult$lambda$lambda$lambda_0(checkIndexOverflow((tmp$_0_1 = index_0, index_0 = tmp$_0_1 + 1 | 0, tmp$_0_1)), this, item_0));
    }
  };
  Handler.prototype.fillLatestPaymentInfo_0 = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11, tmp$_12, tmp$_13, tmp$_14;
    var cardInput = Kotlin.isType(tmp$ = document.getElementById('cardNumber'), HTMLInputElement) ? tmp$ : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      cardInput.value = toString((tmp$_0 = this.latestBookingInfo_0) != null ? tmp$_0.cardNr : null);
    }
    var cityInput = Kotlin.isType(tmp$_1 = document.getElementById('city'), HTMLInputElement) ? tmp$_1 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      cityInput.value = toString((tmp$_2 = this.latestBookingInfo_0) != null ? tmp$_2.city : null);
    }
    var countryInput = Kotlin.isType(tmp$_3 = document.getElementById('country'), HTMLInputElement) ? tmp$_3 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      countryInput.value = toString((tmp$_4 = this.latestBookingInfo_0) != null ? tmp$_4.country : null);
    }
    var houseInput = Kotlin.isType(tmp$_5 = document.getElementById('house'), HTMLInputElement) ? tmp$_5 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      houseInput.value = toString((tmp$_6 = this.latestBookingInfo_0) != null ? tmp$_6.house : null);
    }
    var nameFInput = Kotlin.isType(tmp$_7 = document.getElementById('nameF'), HTMLInputElement) ? tmp$_7 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      nameFInput.value = toString((tmp$_8 = this.latestBookingInfo_0) != null ? tmp$_8.nameF : null);
    }
    var nameLInput = Kotlin.isType(tmp$_9 = document.getElementById('nameL'), HTMLInputElement) ? tmp$_9 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      nameLInput.value = toString((tmp$_10 = this.latestBookingInfo_0) != null ? tmp$_10.nameL : null);
    }
    var streetInput = Kotlin.isType(tmp$_11 = document.getElementById('street'), HTMLInputElement) ? tmp$_11 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      streetInput.value = toString((tmp$_12 = this.latestBookingInfo_0) != null ? tmp$_12.street : null);
    }
    var zipInput = Kotlin.isType(tmp$_13 = document.getElementById('zip'), HTMLInputElement) ? tmp$_13 : throwCCE();
    if (this.latestBookingInfo_0 != null) {
      zipInput.value = toString((tmp$_14 = this.latestBookingInfo_0) != null ? tmp$_14.zip : null);
    }
  };
  Handler.prototype.updateLatestPaymentInfo_0 = function () {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6;
    var cardInput = Kotlin.isType(tmp$ = document.getElementById('cardNumber'), HTMLInputElement) ? tmp$ : throwCCE();
    var cardNumber = cardInput.value;
    var cityInput = Kotlin.isType(tmp$_0 = document.getElementById('city'), HTMLInputElement) ? tmp$_0 : throwCCE();
    var city = cityInput.value;
    var countryInput = Kotlin.isType(tmp$_1 = document.getElementById('country'), HTMLInputElement) ? tmp$_1 : throwCCE();
    var country = countryInput.value;
    var houseInput = Kotlin.isType(tmp$_2 = document.getElementById('house'), HTMLInputElement) ? tmp$_2 : throwCCE();
    var house = houseInput.value;
    var nameFInput = Kotlin.isType(tmp$_3 = document.getElementById('nameF'), HTMLInputElement) ? tmp$_3 : throwCCE();
    var nameF = nameFInput.value;
    var nameLInput = Kotlin.isType(tmp$_4 = document.getElementById('nameL'), HTMLInputElement) ? tmp$_4 : throwCCE();
    var nameL = nameLInput.value;
    var streetInput = Kotlin.isType(tmp$_5 = document.getElementById('street'), HTMLInputElement) ? tmp$_5 : throwCCE();
    var street = streetInput.value;
    var zipInput = Kotlin.isType(tmp$_6 = document.getElementById('zip'), HTMLInputElement) ? tmp$_6 : throwCCE();
    var zip = zipInput.value;
    var emptyFields = '';
    var empty = this.isEmpty_0([new Pair(cardNumber, 'Card Number'), new Pair(city, 'City Name'), new Pair(nameF, 'First Name'), new Pair(nameL, 'Last Name'), new Pair(street, 'Street Name'), new Pair(zip, 'Zip code')]);
    try {
      emptyFields = joinToString_0(empty, ', ', 'The field(s) for ', ' may not be empty');
      if (cardNumber.length > 0) {
        var bookingInfo = new BookingInfo(toInt(cardNumber), city, country, house, nameF, nameL, street, zip);
        if (!empty.isEmpty()) {
          OnPageAlert_getInstance().showErr_61zpoe$(emptyFields);
        }
        else {
          this.latestBookingInfo_0 = bookingInfo;
          OnPageAlert_getInstance().clear();
        }
      }
      else {
        if (!empty.isEmpty()) {
          OnPageAlert_getInstance().showErr_61zpoe$(emptyFields);
        }
      }
    }
    catch (e) {
      if (Kotlin.isType(e, NumberFormatException)) {
        if (!empty.isEmpty()) {
          OnPageAlert_getInstance().showErr_61zpoe$('Card Number is not a number and ' + emptyFields);
        }
        else {
          OnPageAlert_getInstance().showErr_61zpoe$('Card Number is not a number');
        }
      }
      else
        throw e;
    }
    console.dir('latestBookingInfo = ' + toString(this.latestBookingInfo_0));
  };
  Handler.prototype.isEmpty_0 = function (arr) {
    var destination = ArrayList_init_0(arr.length);
    var tmp$;
    for (tmp$ = 0; tmp$ !== arr.length; ++tmp$) {
      var item = arr[tmp$];
      destination.add_11rb$(item.first.length > 0 ? '' : item.second);
    }
    var destination_0 = ArrayList_init();
    var tmp$_0;
    tmp$_0 = destination.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      if (element.length > 0)
        destination_0.add_11rb$(element);
    }
    return toCollection(destination_0, ArrayList_init());
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
  function Handler$confBtnSetup$lambda(it) {
    console.info('Removed listener');
    return Unit;
  }
  function Handler$confBtnSetup$lambda$lambda$lambda$lambda(closure$it, closure$bookedTickets, closure$errors, closure$resolvesNeeded, closure$asList, closure$resolve, closure$index) {
    return function (list) {
      var tmp$;
      if (list.size === closure$it.size) {
        console.info('Same length ' + list.size + ' == ' + closure$it.size + '"');
        var bool = true;
        var tmp$_0;
        tmp$_0 = list.iterator();
        while (tmp$_0.hasNext()) {
          var element = tmp$_0.next();
          console.info(element.errMsg);
        }
        if (bool) {
          var $receiver = closure$bookedTickets;
          var key = closure$it;
          $receiver.put_xwzc9p$(key, list);
        }
        else {
          var $receiver_0 = closure$errors;
          var key_0 = closure$it;
          var value = first(list);
          $receiver_0.put_xwzc9p$(key_0, value);
        }
      }
      else {
        console.info('Dif len: ' + list.size + ' != ' + closure$it.size);
        var $receiver_1 = closure$errors;
        var key_1 = closure$it;
        var value_0 = first(list);
        $receiver_1.put_xwzc9p$(key_1, value_0);
      }
      tmp$ = closure$resolvesNeeded.v;
      closure$resolvesNeeded.v = tmp$ + 1 | 0;
      if (closure$resolvesNeeded.v === closure$asList.size) {
        closure$resolve(new Any());
      }
      console.info('Resolved ' + closure$index);
      return Unit;
    };
  }
  function Handler$confBtnSetup$lambda$lambda$lambda$lambda_0(throwable) {
    OnPageAlert_getInstance().showErr_61zpoe$('Exception occurred: ' + toString(throwable.message));
    return Unit;
  }
  function Handler$confBtnSetup$lambda$lambda(closure$asList, this$Handler, closure$bookedTickets, closure$errors) {
    return function (resolve, f) {
      var resolvesNeeded = {v: 0};
      var $receiver = closure$asList;
      var tmp$, tmp$_0;
      var index = 0;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var item = tmp$.next();
        var this$Handler_0 = this$Handler;
        var closure$bookedTickets_0 = closure$bookedTickets;
        var closure$errors_0 = closure$errors;
        var closure$asList_0 = closure$asList;
        var index_0 = checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0));
        console.info(index_0);
        console.dir(item);
        this$Handler_0.bookTickets_0(item).then(Handler$confBtnSetup$lambda$lambda$lambda$lambda(item, closure$bookedTickets_0, closure$errors_0, resolvesNeeded, closure$asList_0, resolve, index_0)).catch(Handler$confBtnSetup$lambda$lambda$lambda$lambda_0);
      }
      return Unit;
    };
  }
  function Handler$confBtnSetup$lambda$lambda_0(closure$bookedTickets, this$Handler, closure$errors) {
    return function (it) {
      console.log('Now');
      var tmp$;
      tmp$ = closure$bookedTickets.keys.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var tmp$_0;
        tmp$_0 = element.iterator();
        while (tmp$_0.hasNext()) {
          var element_0 = tmp$_0.next();
          Cart_getInstance().remove_jpnhl5$(element_0);
        }
      }
      if (Cart_getInstance().isEmpty()) {
        this$Handler.showResult_0(closure$bookedTickets, LinkedHashMap_init());
      }
      else {
        this$Handler.showResult_0(closure$bookedTickets, closure$errors);
      }
      return Unit;
    };
  }
  function Handler$confBtnSetup$lambda_0(this$Handler) {
    return function (it) {
      console.info('ConfirmBtn pressed');
      this$Handler.updateLatestPaymentInfo_0();
      var bookedTickets = LinkedHashMap_init();
      var errors = LinkedHashMap_init();
      var asList = Cart_getInstance().asList();
      var promise = new Promise(Handler$confBtnSetup$lambda$lambda(asList, this$Handler, bookedTickets, errors));
      promise.then(Handler$confBtnSetup$lambda$lambda_0(bookedTickets, this$Handler, errors));
      return Unit;
    };
  }
  Handler.prototype.confBtnSetup = function () {
    var tmp$;
    var htmlButtonElement = Kotlin.isType(tmp$ = document.getElementById('confirmBtn'), HTMLButtonElement) ? tmp$ : throwCCE();
    htmlButtonElement.removeEventListener('click', Handler$confBtnSetup$lambda);
    htmlButtonElement.addEventListener('click', Handler$confBtnSetup$lambda_0(this));
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
    Handler_getInstance().confBtnSetup();
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
    var tmp$_7 = withFromCB.checked;
    if (tmp$_7) {
      tmp$_7 = fromDatePick.value.length > 0;
    }
    if (tmp$_7) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$DATE_FROM_getInstance().getIdf()), '="'), toSearchDate(fromDatePick.value)), '"');
    }
    var tmp$_8 = withTillCB.checked;
    if (tmp$_8) {
      tmp$_8 = tillDatePicker.value.length > 0;
    }
    if (tmp$_8) {
      sb = append(append(append(append(append(sb, '-'), SearchCategories$DATE_UNTIL_getInstance().getIdf()), '="'), toSearchDate(tillDatePicker.value)), '"');
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
  function toSearchDate($receiver) {
    return joinToString_0(reversed(split($receiver, Kotlin.charArrayOf(45))), '.');
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
  Cart.prototype.remove_jpnhl5$ = function (item) {
    this.items_0.remove_11rb$(item.category.eventCategoryId);
  };
  Cart.prototype.isEmpty = function () {
    return this.items_0.isEmpty();
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
  function LocalTicket(event, occurrence, category, row, seat) {
    if (row === void 0)
      row = null;
    if (seat === void 0)
      seat = null;
    this.event = event;
    this.occurrence = occurrence;
    this.category = category;
    this.row = row;
    this.seat = seat;
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
  LocalTicket.prototype.component4 = function () {
    return this.row;
  };
  LocalTicket.prototype.component5 = function () {
    return this.seat;
  };
  LocalTicket.prototype.copy_vgp1ho$ = function (event, occurrence, category, row, seat) {
    return new LocalTicket(event === void 0 ? this.event : event, occurrence === void 0 ? this.occurrence : occurrence, category === void 0 ? this.category : category, row === void 0 ? this.row : row, seat === void 0 ? this.seat : seat);
  };
  LocalTicket.prototype.toString = function () {
    return 'LocalTicket(event=' + Kotlin.toString(this.event) + (', occurrence=' + Kotlin.toString(this.occurrence)) + (', category=' + Kotlin.toString(this.category)) + (', row=' + Kotlin.toString(this.row)) + (', seat=' + Kotlin.toString(this.seat)) + ')';
  };
  LocalTicket.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.event) | 0;
    result = result * 31 + Kotlin.hashCode(this.occurrence) | 0;
    result = result * 31 + Kotlin.hashCode(this.category) | 0;
    result = result * 31 + Kotlin.hashCode(this.row) | 0;
    result = result * 31 + Kotlin.hashCode(this.seat) | 0;
    return result;
  };
  LocalTicket.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.event, other.event) && Kotlin.equals(this.occurrence, other.occurrence) && Kotlin.equals(this.category, other.category) && Kotlin.equals(this.row, other.row) && Kotlin.equals(this.seat, other.seat)))));
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
  TicketPayload.prototype.copy_gc983k$ = function (amount, bookingInfo, rowseats) {
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
  function RowSeat(rowID, seatID) {
    this.rowID = rowID;
    this.seatID = seatID;
  }
  RowSeat.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RowSeat',
    interfaces: []
  };
  RowSeat.prototype.component1 = function () {
    return this.rowID;
  };
  RowSeat.prototype.component2 = function () {
    return this.seatID;
  };
  RowSeat.prototype.copy_vux9f0$ = function (rowID, seatID) {
    return new RowSeat(rowID === void 0 ? this.rowID : rowID, seatID === void 0 ? this.seatID : seatID);
  };
  RowSeat.prototype.toString = function () {
    return 'RowSeat(rowID=' + Kotlin.toString(this.rowID) + (', seatID=' + Kotlin.toString(this.seatID)) + ')';
  };
  RowSeat.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.rowID) | 0;
    result = result * 31 + Kotlin.hashCode(this.seatID) | 0;
    return result;
  };
  RowSeat.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.rowID, other.rowID) && Kotlin.equals(this.seatID, other.seatID)))));
  };
  function BookingResponse(tranactionId, errMsg) {
    this.tranactionId = tranactionId;
    this.errMsg = errMsg;
  }
  BookingResponse.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BookingResponse',
    interfaces: []
  };
  BookingResponse.prototype.component1 = function () {
    return this.tranactionId;
  };
  BookingResponse.prototype.component2 = function () {
    return this.errMsg;
  };
  BookingResponse.prototype.copy_rkkr90$ = function (tranactionId, errMsg) {
    return new BookingResponse(tranactionId === void 0 ? this.tranactionId : tranactionId, errMsg === void 0 ? this.errMsg : errMsg);
  };
  BookingResponse.prototype.toString = function () {
    return 'BookingResponse(tranactionId=' + Kotlin.toString(this.tranactionId) + (', errMsg=' + Kotlin.toString(this.errMsg)) + ')';
  };
  BookingResponse.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.tranactionId) | 0;
    result = result * 31 + Kotlin.hashCode(this.errMsg) | 0;
    return result;
  };
  BookingResponse.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.tranactionId, other.tranactionId) && Kotlin.equals(this.errMsg, other.errMsg)))));
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
  OnPageAlert.prototype.clear = function () {
    var element = ensureNotNull(document.getElementById('alertBox'));
    element.textContent = '';
    addClass(element, ['none']);
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
  _.Row = Row;
  _.Seat = Seat;
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
  _.toSearchDate_pdl1vz$ = toSearchDate;
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
  _.RowSeat = RowSeat;
  _.BookingResponse = BookingResponse;
  _.BookingInfo = BookingInfo;
  Object.defineProperty(_, 'OnPageAlert', {
    get: OnPageAlert_getInstance
  });
  main();
  Kotlin.defineModule('kotlinweb', _);
  return _;
}(typeof kotlinweb === 'undefined' ? {} : kotlinweb, kotlin);
