package Presentacion.Controlador;

//OK: Datos correctos
//DI: Datos incorrectos
//RE: Datos repetidos
//NE: No encontrado
//OA: Otro activo

public class Eventos {

//-------------------------------------TALLER--------------------------------------
	public static final int TALLER =  100;
	
//------------------------------------ESPECIALIDAD----------------------------------------
	// 700
	public static final int ESPECIALIDAD = 101;
	
	public static final int ALTA_ESPECIALIDAD = 102;
	public static final int RES_ALTA_ESPECIALIDAD_OK = 401;
	public static final int RES_ALTA_ESPECIALIDAD_DI = 402;
	public static final int RES_ALTA_ESPECIALIDAD_RE = 403;
	
	public static final int BAJA_ESPECIALIDAD = 103;
	public static final int RES_BAJA_ESPECIALIDAD_OK = 404;
	public static final int RES_BAJA_ESPECIALIDAD_DI = 405;
	public static final int RES_BAJA_ESPECIALIDAD_NE = 406;
	public static final int RES_BAJA_ESPECIALIDAD_OA = 407;
	
	public static final int MOSTRAR_ESPECIALIDAD = 104;
	public static final int RES_MOSTRAR_ESPECIALIDAD_OK = 408;
	public static final int RES_MOSTRAR_ESPECIALIDAD_DI = 409;
	public static final int RES_MOSTRAR_ESPECIALIDAD_NE = 410;
	
	public static final int LISTAR_ESPECIALIDAD = 105;
	public static final int RES_LISTAR_ESPECIALIDAD_OK = 411;
	public static final int RES_LISTAR_ESPECIALIDAD_NE = 412;
	
	public static final int MODIFICAR_ESPECIALIDAD = 106;
	public static final int RES_MODIFICAR_ESPECIALIDAD_OK = 413;
	public static final int RES_MODIFICAR_ESPECIALIDAD_DI = 414;
	public static final int RES_MODIFICAR_ESPECIALIDAD_NE = 415;
	public static final int RES_MODIFICAR_ESPECIALIDAD_RE = 416;
	
// -----------------------------------PROVEEDOR-------------------------------------------------
	//800
	public static final int PROVEEDOR = 107;
	
	public static final int ALTA_PROVEEDOR= 108;
	public static final int RES_ALTA_PROVEEDOR_OK = 417;
	public static final int RES_ALTA_PROVEEDOR_DI = 418;
	public static final int RES_ALTA_PROVEEDOR_RE = 419;
	
	public static final int BAJA_PROVEEDOR= 109;
	public static final int RES_BAJA_PROVEEDOR_OK = 420;
	public static final int RES_BAJA_PROVEEDOR_DI = 421;
	public static final int RES_BAJA_PROVEEDOR_NE = 422;
	public static final int RES_BAJA_PROVEEDOR_OA = 423;
	
	public static final int MOSTRAR_PROVEEDOR = 110;
	public static final int RES_MOSTRAR_PROVEEDOR_OK = 424;
	public static final int RES_MOSTRAR_PROVEEDOR_DI = 425;
	public static final int RES_MOSTRAR_PROVEEDOR_NE = 426;
	
	public static final int LISTAR_PROVEEDOR = 111;
	public static final int RES_LISTAR_PROVEEDOR_OK = 427;
	public static final int RES_LISTAR_PROVEEDOR_NE = 428;
	
	public static final int MODIFICAR_PROVEEDOR = 112;
	public static final int RES_MODIFICAR_PROVEEDOR_OK = 429;
	public static final int RES_MODIFICAR_PROVEEDOR_DI = 430;
	public static final int RES_MODIFICAR_PROVEEDOR_NE = 431;
	public static final int RES_MODIFICAR_PROVEEDOR_RE = 432;

// ----------------------------------CLIENTE---------------------------------------------------	
	//900
	public static final int CLIENTE = 113;
	
	public static final int ALTA_CLIENTE = 114;
	public static final int RES_ALTA_CLIENTE_OK = 433;
	public static final int RES_ALTA_CLIENTE_DI = 434;
	public static final int RES_ALTA_CLIENTE_RE = 435;
	
	public static final int BAJA_CLIENTE = 115;
	public static final int RES_BAJA_CLIENTE_OK = 436;
	public static final int RES_BAJA_CLIENTE_DI = 437;
	public static final int RES_BAJA_CLIENTE_NE = 438;
	public static final int RES_BAJA_CLIENTE_OA = 439;
	
	public static final int MOSTRAR_CLIENTE = 116;
	public static final int RES_MOSTRAR_CLIENTE_OK = 440;
	public static final int RES_MOSTRAR_CLIENTE_DI = 441;
	public static final int RES_MOSTRAR_CLIENTE_NE = 442;
	
	public static final int LISTAR_CLIENTE = 117;
	public static final int RES_LISTAR_CLIENTE_OK = 443;
	public static final int RES_LISTAR_CLIENTE_NE = 444;
	
	public static final int MODIFICAR_CLIENTE = 131;
	public static final int RES_MODIFICAR_CLIENTE_OK = 481;
	public static final int RES_MODIFICAR_CLIENTE_DI = 482;
	public static final int RES_MODIFICAR_CLIENTE_NE = 483;
	public static final int RES_MODIFICAR_CLIENTE_RE = 484;
	
// ----------------------------------MECANICO----------------------------------------------------	
	//1000
	public static final int MECANICO = 118;
	
	public static final int ALTA_MECANICO = 119;
	public static final int RES_ALTA_MECANICO_OK = 445;
	public static final int RES_ALTA_MECANICO_DI = 446;
	public static final int RES_ALTA_MECANICO_RE = 447;
	public static final int RES_ALTA_MECANICO_IE = 700;
	
	public static final int BAJA_MECANICO = 120;
	public static final int RES_BAJA_MECANICO_OK = 448;
	public static final int RES_BAJA_MECANICO_DI = 449;
	public static final int RES_BAJA_MECANICO_NE = 450;
	public static final int RES_BAJA_MECANICO_OA = 451;
	
	public static final int MOSTRAR_MECANICO = 121;
	public static final int RES_MOSTRAR_MECANICO_OK = 452;
	public static final int RES_MOSTRAR_MECANICO_DI = 453;
	public static final int RES_MOSTRAR_MECANICO_NE = 454;
	
	public static final int LISTAR_MECANICO = 122;
	public static final int RES_LISTAR_MECANICO_OK = 455;
	public static final int RES_LISTAR_MECANICO_NE = 456;
	
	public static final int MODIFICAR_MECANICO = 123;
	public static final int RES_MODIFICAR_MECANICO_OK = 457;
	public static final int RES_MODIFICAR_MECANICO_DI = 458;
	public static final int RES_MODIFICAR_MECANICO_NE = 459;
	public static final int RES_MODIFICAR_MECANICO_RE = 461;
	public static final int RES_MODIFICAR_MECANICO_IE = 701;
	
	public static final int MOSTRAR_MECANICO_ESPECIALIDAD = 810;
	public static final int RES_MOSTRAR_MECANICO_ESPECIALIDAD_OK = 811;
	public static final int RES_MOSTRAR_MECANICO_ESPECIALIDAD_DI = 812;
	public static final int RES_MOSTRAR_MECANICO_ESPECIALIDAD_NE = 813;
	public static final int RES_MOSTRAR_MECANICO_ESPECIALIDAD_IE = 814;

// ---------------------------------COMPONENTE---------------------------------------------------
	//1100
	public static final int COMPONENTE = 124;
	
	public static final int ALTA_COMPONENTE = 125;
	public static final int RES_ALTA_COMPONENTE_OK = 462;
	public static final int RES_ALTA_COMPONENTE_DI = 463;
	public static final int RES_ALTA_COMPONENTE_RE = 464;
	public static final int RES_ALTA_COMPONENTE_IE = 702;
	
	public static final int BAJA_COMPONENTE = 126;
	public static final int RES_BAJA_COMPONENTE_OK = 465;
	public static final int RES_BAJA_COMPONENTE_DI = 466;
	public static final int RES_BAJA_COMPONENTE_NE = 467;
	public static final int RES_BAJA_COMPONENTE_OA = 468;
	
	public static final int MOSTRAR_COMPONENTE = 127;
	public static final int RES_MOSTRAR_COMPONENTE_OK = 469;
	public static final int RES_MOSTRAR_COMPONENTE_DI = 470;
	public static final int RES_MOSTRAR_COMPONENTE_NE = 471;
	
	public static final int LISTAR_COMPONENTE = 128;
	public static final int RES_LISTAR_COMPONENTE_OK = 472;
	public static final int RES_LISTAR_COMPONENTE_NE = 473;
	
	public static final int MODIFICAR_COMPONENTE = 129;
	public static final int RES_MODIFICAR_COMPONENTE_OK = 474;
	public static final int RES_MODIFICAR_COMPONENTE_DI = 475;
	public static final int RES_MODIFICAR_COMPONENTE_NE = 476;
	public static final int RES_MODIFICAR_COMPONENTE_RE = 477;
	public static final int RES_MODIFICAR_COMPONENTE_IE = 703;
	
	public static final int MOSTRAR_COMPONENTE_PROVEEDOR = 130;
	public static final int RES_MOSTRAR_COMPONENTE_PROVEEDOR_OK = 478;
	public static final int RES_MOSTRAR_COMPONENTE_PROVEEDOR_DI = 479;
	public static final int RES_MOSTRAR_COMPONENTE_PROVEEDOR_NE = 480;
	
	
// ----------------------------------VEHICULO----------------------------------------------
	//1200
	public static final int VEHICULO = 132;
	
	public static final int ALTA_VEHICULO = 133;
	public static final int RES_ALTA_VEHICULO_OK = 485;
	public static final int RES_ALTA_VEHICULO_DI = 486;
	public static final int RES_ALTA_VEHICULO_RE = 487;
	public static final int RES_ALTA_VEHICULO_CNE = 488;
	public static final int RES_ALTA_VEHICULO_IE = 705;
	
	public static final int BAJA_VEHICULO = 134;
	public static final int RES_BAJA_VEHICULO_OK = 489;
	public static final int RES_BAJA_VEHICULO_DI = 490;
	public static final int RES_BAJA_VEHICULO_NE = 491;
	public static final int RES_BAJA_VEHICULO_OA = 492;
	
	public static final int MOSTRAR_VEHICULO = 135;
	public static final int RES_MOSTRAR_VEHICULO_OK = 493;
	public static final int RES_MOSTRAR_VEHICULO_DI = 494;
	public static final int RES_MOSTRAR_VEHICULO_NE = 495;
	
	public static final int MOSTRAR_VEHICULO_CLIENTE = 136;
	public static final int RES_MOSTRAR_VEHICULO_CLIENTE_OK = 496;
	public static final int RES_MOSTRAR_VEHICULO_CLIENTE_DI = 497;
	public static final int RES_MOSTRAR_VEHICULO_CLIENTE_NE = 498;
	
	public static final int LISTAR_VEHICULO = 137;
	public static final int RES_LISTAR_VEHICULO_OK = 499;
	public static final int RES_LISTAR_VEHICULO_NE = 500;
	
	public static final int MODIFICAR_VEHICULO = 138;
	public static final int RES_MODIFICAR_VEHICULO_OK = 501;
	public static final int RES_MODIFICAR_VEHICULO_DI = 502;
	public static final int RES_MODIFICAR_VEHICULO_NE = 503;
	public static final int RES_MODIFICAR_VEHICULO_RE = 504;
	public static final int RES_MODIFICAR_VEHICULO_IE = 707;
	
	
// --------------------------------REPARACION--------------------------------------------
	//1300
	public static final int REPARACION = 139;
	
	public static final int ALTA_REPARACION = 140;
	public static final int RES_ALTA_REPARACION_OK = 600;
	public static final int RES_ALTA_REPARACION_DI = 601;
	public static final int RES_ALTA_REPARACION_RE = 602;
	public static final int RES_ALTA_REPARACION_IE = 708;

	public static final int BAJA_REPARACION = 141;
	public static final int RES_BAJA_REPARACION_OK = 603;
	public static final int RES_BAJA_REPARACION_DI = 604;
	public static final int RES_BAJA_REPARACION_NE = 605;
	
	public static final int MOSTRAR_REPARACION = 142;
	public static final int RES_MOSTRAR_REPARACION_OK = 608;
	public static final int RES_MOSTRAR_REPARACION_DI = 609;
	public static final int RES_MOSTRAR_REPARACION_NE = 610;
	
	public static final int LISTAR_REPARACION = 143;
	public static final int RES_LISTAR_REPARACION_OK = 611;
	public static final int RES_LISTAR_REPARACION_NE = 612;
	
	public static final int MODIFICAR_REPARACION = 144;
	public static final int RES_MODIFICAR_REPARACION_OK = 613;
	public static final int RES_MODIFICAR_REPARACION_DI = 614;
	public static final int RES_MODIFICAR_REPARACION_NE = 615;
	public static final int RES_MODIFICAR_REPARACION_IE = 709;
	
	public static final int ANYADIR_COMPONENTE_REPARACION = 145;
	public static final int RES_ANYADIR_COMPONENTE_REPARACION_OK = 617;
	public static final int RES_ANYADIR_COMPONENTE_REPARACION_NC = 618;
	public static final int RES_ANYADIR_COMPONENTE_REPARACION_NR = 619;
	public static final int RES_ANYADIR_COMPONENTE_REPARACION_DI = 620;
	public static final int RES_ANYADIR_COMPONENTE_REPARACION_RE = 650;
	
	public static final int ANYADIR_MECANICO_REPARACION = 146;
	public static final int RES_ANYADIR_MECANICO_REPARACION_OK = 621;
	public static final int RES_ANYADIR_MECANICO_REPARACION_NM = 622;
	public static final int RES_ANYADIR_MECANICO_REPARACION_NR = 623;
	public static final int RES_ANYADIR_MECANICO_REPARACION_DI = 624;
	public static final int RES_ANYADIR_MECANICO_REPARACION_RE = 651;
	
	public static final int BORRAR_COMPONENTE_REPARACION = 147;
	public static final int RES_BORRAR_COMPONENTE_REPARACION_OK = 625;
	public static final int RES_BORRAR_COMPONENTE_REPARACION_NC = 626;
	public static final int RES_BORRAR_COMPONENTE_REPARACION_NR = 627;
	public static final int RES_BORRAR_COMPONENTE_REPARACION_DI = 628;
	
	public static final int BORRAR_MECANICO_REPARACION = 148;
	public static final int RES_BORRAR_MECANICO_REPARACION_OK = 629;
	public static final int RES_BORRAR_MECANICO_REPARACION_NM = 630;
	public static final int RES_BORRAR_MECANICO_REPARACION_NR = 631;
	public static final int RES_BORRAR_MECANICO_REPARACION_DI = 632;
	
	public static final int MODIFICAR_COMPONENTE_REPARACION = 149;
	public static final int RES_MODIFICAR_COMPONENTE_REPARACION_OK = 633;
	public static final int RES_MODIFICAR_COMPONENTE_REPARACION_NC = 634;
	public static final int RES_MODIFICAR_COMPONENTE_REPARACION_NR = 635;
	public static final int RES_MODIFICAR_COMPONENTE_REPARACION_DI = 636;
	
	public static final int MODIFICAR_MECANICO_REPARACION = 150;
	public static final int RES_MODIFICAR_MECANICO_REPARACION_OK = 637;
	public static final int RES_MODIFICAR_MECANICO_REPARACION_NM = 638;
	public static final int RES_MODIFICAR_MECANICO_REPARACION_NR = 639;
	public static final int RES_MODIFICAR_MECANICO_REPARACION_DI = 640;

	public static final int MOSTRAR_REPARACION_VEHICULO = 151;
	public static final int RES_MOSTRAR_REPARACION_VEHICULO_OK = 641;
	public static final int RES_MOSTRAR_REPARACION_VEHICULO_DI = 642;
	public static final int RES_MOSTRAR_REPARACION_VEHICULO_NE = 643;
	
	//-------------------------------------EXCEPCION SQL--------------------------------------
	public static final int EXCEPCION_SQL = 4000;
}