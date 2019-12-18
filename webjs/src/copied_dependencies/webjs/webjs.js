if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'webjs'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'webjs'.");
}
var webjs = function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Unit = Kotlin.kotlin.Unit;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  function EventDTO(name) {
    this.name = name;
  }
  EventDTO.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EventDTO',
    interfaces: []
  };
  EventDTO.prototype.component1 = function () {
    return this.name;
  };
  EventDTO.prototype.copy_61zpoe$ = function (name) {
    return new EventDTO(name === void 0 ? this.name : name);
  };
  EventDTO.prototype.toString = function () {
    return 'EventDTO(name=' + Kotlin.toString(this.name) + ')';
  };
  EventDTO.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    return result;
  };
  EventDTO.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.name, other.name))));
  };
  function Handler() {
    this.testURL = 'http://localhost:8080';
    this.searchURL = this.testURL + '/events/findByQueryString?queryString=';
  }
  function Handler$getSearchResults$lambda$lambda(it) {
    console.log(it);
    return Unit;
  }
  function Handler$getSearchResults$lambda(res) {
    return res.text().then(Handler$getSearchResults$lambda$lambda);
  }
  Handler.prototype.getSearchResults = function () {
    var searchQuery = '-title="spon"';
    window.fetch(this.searchURL + searchQuery).then(Handler$getSearchResults$lambda);
    return emptyList();
  };
  Handler.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Handler',
    interfaces: []
  };
  function main(args) {
    var handler = new Handler();
    handler.getSearchResults();
  }
  _.EventDTO = EventDTO;
  _.Handler = Handler;
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('webjs', _);
  return _;
}(typeof webjs === 'undefined' ? {} : webjs, kotlin);
