--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2023-02-20 20:59:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 24577)
-- Name: aduana; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aduana (
    idaduana integer NOT NULL,
    nombre text NOT NULL,
    telefono text NOT NULL,
    direccion text NOT NULL,
    sigla text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.aduana OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24583)
-- Name: caja_detalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.caja_detalle (
    idcaja_detalle integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    descripcion text NOT NULL,
    estado text NOT NULL,
    monto_liquidacion_credito numeric(20,0) NOT NULL,
    monto_recibo_pago numeric(20,0) NOT NULL,
    monto_gasto numeric(20,0) NOT NULL,
    monto_vale numeric(20,0) NOT NULL,
    fk_idusuario integer NOT NULL,
    fk_idvale integer NOT NULL,
    fk_idgasto integer NOT NULL,
    fk_idliquidacion_final integer NOT NULL,
    fk_recibo_pago_tercero integer NOT NULL
);


ALTER TABLE public.caja_detalle OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24588)
-- Name: comprobante_liquidacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comprobante_liquidacion (
    idcomprobante_liquidacion integer NOT NULL,
    descripcion text NOT NULL,
    por_iva numeric(5,0) NOT NULL,
    tipo_iva text NOT NULL,
    nro_despacho boolean NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.comprobante_liquidacion OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24594)
-- Name: credito_tercero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credito_tercero (
    idcredito_tercero integer NOT NULL,
    fecha_emision timestamp without time zone NOT NULL,
    descripcion text NOT NULL,
    estado text NOT NULL,
    monto_contado numeric(20,0) NOT NULL,
    monto_credito numeric(20,0) NOT NULL,
    tabla_origen text NOT NULL,
    vence boolean NOT NULL,
    fecha_vence timestamp without time zone NOT NULL,
    fk_idgrupo_credito_tercero integer NOT NULL,
    fk_idsaldo_credito_tercero integer NOT NULL,
    fk_idrecibo_pago_tercero integer NOT NULL,
    fk_idliquidacion_final integer NOT NULL
);


ALTER TABLE public.credito_tercero OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24599)
-- Name: despacho_zona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.despacho_zona (
    iddespacho_zona integer NOT NULL,
    nombre text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.despacho_zona OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24605)
-- Name: encomienta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.encomienta (
    idencomienta integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    numero numeric(20,0) NOT NULL,
    destinatario text NOT NULL,
    telefono text NOT NULL,
    direccion_destino text NOT NULL,
    forma_entrega text NOT NULL,
    forma_pago text NOT NULL,
    detalle_paquete text NOT NULL,
    monto numeric(15,0) NOT NULL,
    fk_idtercero_ciudad integer NOT NULL,
    fk_idliquidacion_proforma integer NOT NULL,
    fk_idtercero integer NOT NULL
);


ALTER TABLE public.encomienta OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24610)
-- Name: factura_nro_proforma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_nro_proforma (
    idfactura_nro_proforma integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    nro_factura text NOT NULL,
    monto numeric(20,0) NOT NULL,
    imagen text NOT NULL,
    guarani_unidad numeric(10,2) NOT NULL,
    fk_idliquidacion_proforma integer NOT NULL,
    fk_idmoneda_cambio integer NOT NULL
);


ALTER TABLE public.factura_nro_proforma OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24615)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    idfuncionario integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    nombre text NOT NULL,
    cedula text NOT NULL,
    telefono text NOT NULL,
    direccion text NOT NULL,
    cargo text NOT NULL,
    salario numeric(15,0) NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24621)
-- Name: gasto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gasto (
    idgasto integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    fecha date NOT NULL,
    descripcion text NOT NULL,
    monto_gasto numeric(15,0) NOT NULL,
    estado text NOT NULL,
    fk_idgasto_tipo integer NOT NULL,
    fk_idusuario integer NOT NULL
);


ALTER TABLE public.gasto OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24626)
-- Name: gasto_tipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gasto_tipo (
    idgasto_tipo integer NOT NULL,
    nombre text NOT NULL,
    activar boolean NOT NULL
);


ALTER TABLE public.gasto_tipo OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24631)
-- Name: grupo_credito_tercero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupo_credito_tercero (
    idgrupo_credito_tercero integer NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone NOT NULL,
    estado text NOT NULL,
    fk_idtercero integer NOT NULL
);


ALTER TABLE public.grupo_credito_tercero OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24636)
-- Name: honorario_despacho; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.honorario_despacho (
    idhonorario_despacho integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    monto numeric(20,0) NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.honorario_despacho OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 24640)
-- Name: incoterms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.incoterms (
    idincoterms integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    descripcion text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.incoterms OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24646)
-- Name: item_comprobante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_comprobante (
    iditem_comprobante integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    descripcion text NOT NULL,
    monto numeric(20,0) NOT NULL,
    referencia text NOT NULL,
    imagen text NOT NULL,
    fk_idliquidacion_proforma integer NOT NULL,
    fk_idtipo_comprobante integer NOT NULL,
    tipo_comprobante text
);


ALTER TABLE public.item_comprobante OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 24651)
-- Name: item_liquidacion_final; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_liquidacion_final (
    iditem_liquidacion_final integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    orden integer NOT NULL,
    descripcion text NOT NULL,
    comprobante_nro text NOT NULL,
    total_guarani numeric(20,0) NOT NULL,
    sin_iva numeric(20,0) NOT NULL,
    solo_iva numeric(20,0) NOT NULL,
    fk_idliquidacion_final integer NOT NULL,
    fk_idcomprobante_liquidacion integer NOT NULL
);


ALTER TABLE public.item_liquidacion_final OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24656)
-- Name: item_mercaderia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_mercaderia (
    iditem_mercaderia integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    nombre text NOT NULL,
    fk_idliquidacion_proforma integer NOT NULL,
    fk_idmercaderia integer NOT NULL
);


ALTER TABLE public.item_mercaderia OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 24661)
-- Name: item_pais_ciudad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pais_ciudad (
    iditem_pais_ciudad integer NOT NULL,
    fk_idtercero_pais integer NOT NULL,
    fk_idtercero_ciudad integer NOT NULL
);


ALTER TABLE public.item_pais_ciudad OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24664)
-- Name: item_pre_liquidacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pre_liquidacion (
    iditem_pre_liquidacion integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    orden integer NOT NULL,
    descripcion text NOT NULL,
    comprobante_nro text NOT NULL,
    total_guarani numeric(20,0) NOT NULL,
    sin_iva numeric(20,0) NOT NULL,
    solo_iva numeric(20,0) NOT NULL,
    fk_idpre_liquidacion integer NOT NULL,
    fk_idcomprobante_liquidacion integer NOT NULL
);


ALTER TABLE public.item_pre_liquidacion OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24669)
-- Name: item_tipo_registro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_tipo_registro (
    iditem_tipo_registro integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    estado text NOT NULL,
    fecha_estado date NOT NULL,
    nro_habilitacion text NOT NULL,
    fecha_habilitacion date NOT NULL,
    fecha_vencimiento date NOT NULL,
    imagen text NOT NULL,
    fk_idtipo_registro integer NOT NULL,
    fk_idtipo_dependencia integer NOT NULL,
    fk_idtipo_institucion integer NOT NULL,
    fk_idtercero integer NOT NULL
);


ALTER TABLE public.item_tipo_registro OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 24674)
-- Name: item_usuario_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_usuario_rol (
    iditem_usuario_rol integer NOT NULL,
    estado boolean NOT NULL,
    fk_idusuario_rol integer NOT NULL,
    fk_idusuario_evento integer NOT NULL
);


ALTER TABLE public.item_usuario_rol OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 24677)
-- Name: liquidacion_final; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liquidacion_final (
    idliquidacion_final integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    fecha_pagado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    fecha_despacho date NOT NULL,
    despacho_numero text NOT NULL,
    tipo_liquidacion text NOT NULL,
    estado text NOT NULL,
    observacion text NOT NULL,
    contenedor_nro text NOT NULL,
    contenedor_tipo text NOT NULL,
    via_transporte text NOT NULL,
    transporte_condicion text NOT NULL,
    monto_imponible numeric(20,0) NOT NULL,
    monto_ajuste_incluir numeric(20,0) NOT NULL,
    monto_factura numeric(20,2) NOT NULL,
    monto_flete numeric(15,2) NOT NULL,
    monto_seguro numeric(15,2) NOT NULL,
    monto_cif numeric(20,2) NOT NULL,
    monto_total_despacho numeric(20,0) NOT NULL,
    monto_adelanto numeric(20,0) NOT NULL,
    monto_pagar numeric(20,0) NOT NULL,
    tasa_cambio_aduana numeric(10,2) NOT NULL,
    tasa_cambio_mercado numeric(10,2) NOT NULL,
    monto_pagado numeric(20,0) NOT NULL,
    tipo_tasa_cambio text NOT NULL,
    factura_numero text NOT NULL,
    monto_letra text NOT NULL,
    fk_idtipo_comprobante integer NOT NULL,
    fk_idtercero_ciudad integer NOT NULL,
    fk_idaduana integer NOT NULL,
    fk_iddespacho_zona integer NOT NULL,
    fk_idtransporte_empresa integer NOT NULL,
    fk_idtercero_importador integer NOT NULL,
    fk_idtercero_exportador integer NOT NULL,
    fk_idmoneda_cambio integer NOT NULL,
    fk_idregimen integer NOT NULL,
    fk_idincoterms integer NOT NULL,
    otro_nombre text DEFAULT 'otro'::text,
    otro_monto numeric(20,0) DEFAULT 0,
    fk_idtercero_despachante integer DEFAULT 1 NOT NULL,
    fk_idrecibo_pago_tercero integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.liquidacion_final OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 24686)
-- Name: liquidacion_proforma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liquidacion_proforma (
    idliquidacion_proforma integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    fecha_despacho date NOT NULL,
    contenedor_nro text NOT NULL,
    contenedor_tipo text NOT NULL,
    via_transporte text NOT NULL,
    monto_factura numeric(20,0) NOT NULL,
    monto_sin_comprobante numeric(20,0) NOT NULL,
    monto_con_comprobante numeric(20,0) NOT NULL,
    tasa_cambio_aduana numeric(10,2) NOT NULL,
    tasa_cambio_mercado numeric(10,2) NOT NULL,
    estado text NOT NULL,
    fk_idtercero_importador integer NOT NULL,
    fk_idtercero_despachante integer NOT NULL,
    fk_idtercero_colaborador integer NOT NULL,
    fk_idtercero_transportadora integer NOT NULL,
    fk_idaduana integer NOT NULL,
    observacion text,
    tipo_tasa_cambio text,
    tipo_liquidacion text,
    fk_idhonorario_despacho integer,
    monto_boleto_despachante numeric(20,0),
    monto_honorario_despachante numeric(20,0),
    fk_idmoneda_cambio integer
);


ALTER TABLE public.liquidacion_proforma OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 24691)
-- Name: moneda_cambio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.moneda_cambio (
    idmoneda_cambio integer NOT NULL,
    moneda text NOT NULL,
    guarani_unidad_aduana numeric(10,2) NOT NULL,
    guarani_unidad_mercado numeric(10,2) NOT NULL,
    sigla text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.moneda_cambio OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 24697)
-- Name: pre_item_liquidacion_final; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pre_item_liquidacion_final (
    idpre_item_liquidacion_final integer NOT NULL,
    orden integer NOT NULL,
    fk_idcomprobante_liquidacion integer NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.pre_item_liquidacion_final OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 24701)
-- Name: pre_liquidacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pre_liquidacion (
    idpre_liquidacion integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    fecha_factura date NOT NULL,
    fecha_llegada date NOT NULL,
    numero_factura text NOT NULL,
    numero_invoice text NOT NULL,
    monto_factura numeric(20,2) NOT NULL,
    descripcion text NOT NULL,
    observacion text NOT NULL,
    tipo_liquidacion text NOT NULL,
    estado text NOT NULL,
    fk_idtercero_importador integer NOT NULL,
    fk_idtercero_exportador integer NOT NULL
);


ALTER TABLE public.pre_liquidacion OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 24706)
-- Name: recibo_pago_tercero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recibo_pago_tercero (
    idrecibo_pago_tercero integer NOT NULL,
    fecha_emision timestamp without time zone NOT NULL,
    descripcion text NOT NULL,
    monto_recibo_pago numeric(20,0) NOT NULL,
    monto_letra text NOT NULL,
    estado text NOT NULL,
    fk_idtercero integer NOT NULL,
    fk_idusuario integer NOT NULL
);


ALTER TABLE public.recibo_pago_tercero OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 24711)
-- Name: regimen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.regimen (
    idregimen integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    descripcion text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.regimen OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 24717)
-- Name: saldo_credito_tercero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saldo_credito_tercero (
    idsaldo_credito_tercero integer NOT NULL,
    fecha_emision timestamp without time zone NOT NULL,
    descripcion text NOT NULL,
    monto_saldo_credito numeric(20,0) NOT NULL,
    monto_letra text NOT NULL,
    estado text NOT NULL,
    fk_idtercero integer NOT NULL,
    fk_idusuario integer NOT NULL
);


ALTER TABLE public.saldo_credito_tercero OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 24722)
-- Name: tercero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tercero (
    idtercero integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    nombre text NOT NULL,
    ruc text NOT NULL,
    telefono text NOT NULL,
    direccion text NOT NULL,
    representante_nombre text NOT NULL,
    representante_cedula text NOT NULL,
    importador boolean NOT NULL,
    despachante boolean NOT NULL,
    colaborador boolean NOT NULL,
    proveedor boolean NOT NULL,
    transportadora boolean NOT NULL,
    fk_idtercero_pais integer NOT NULL,
    fk_idtercero_ciudad integer NOT NULL,
    saldo_credito numeric(20,0) DEFAULT 0,
    fk_idtercero_rubro integer DEFAULT 0 NOT NULL,
    exportador boolean DEFAULT false NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tercero OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 24731)
-- Name: tercero_ciudad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tercero_ciudad (
    idtercero_ciudad integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tercero_ciudad OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 24737)
-- Name: tercero_pais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tercero_pais (
    idtercero_pais integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tercero_pais OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 24743)
-- Name: tercero_rubro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tercero_rubro (
    idtercero_rubro integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    descripcion text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tercero_rubro OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 24749)
-- Name: tipo_comprobante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_comprobante (
    idtipo_comprobante integer NOT NULL,
    descripcion text NOT NULL,
    con_comprobante boolean NOT NULL,
    sin_comprobante boolean NOT NULL,
    boleta_despachante boolean NOT NULL,
    mercaderia boolean,
    tipo_factura boolean,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tipo_comprobante OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 24755)
-- Name: tipo_dependencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_dependencia (
    idtipo_dependencia integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.tipo_dependencia OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 24760)
-- Name: tipo_institucion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_institucion (
    idtipo_institucion integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.tipo_institucion OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 24765)
-- Name: tipo_registro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_registro (
    idtipo_registro integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.tipo_registro OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 24770)
-- Name: tipo_tasa_cambio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_tasa_cambio (
    idtipo_tasa_cambio integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    dolar_gua_aduana numeric(10,2) NOT NULL,
    dolar_gua_mercado numeric(10,2) NOT NULL,
    real_gua_aduana numeric(10,2) NOT NULL,
    real_gua_mercado numeric(10,2) NOT NULL
);


ALTER TABLE public.tipo_tasa_cambio OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 24775)
-- Name: transporte_empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transporte_empresa (
    idtransporte_empresa integer NOT NULL,
    nombre text NOT NULL,
    sigla text NOT NULL,
    direccion text NOT NULL,
    telefono text NOT NULL,
    eliminado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.transporte_empresa OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 24781)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    idusuario integer NOT NULL,
    usuario text NOT NULL,
    senha text NOT NULL,
    nombre text NOT NULL,
    fk_idusuario_rol integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 24786)
-- Name: usuario_evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_evento (
    idusuario_evento integer NOT NULL,
    fecha_creado date NOT NULL,
    cod_evento text NOT NULL,
    descripcion text NOT NULL,
    mensaje_error text NOT NULL,
    fk_idusuario_formulario integer NOT NULL,
    fk_idusuario_tipo_evento integer NOT NULL
);


ALTER TABLE public.usuario_evento OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 24791)
-- Name: usuario_formulario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_formulario (
    idusuario_formulario integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.usuario_formulario OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 24796)
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_rol (
    idusuario_rol integer NOT NULL,
    fecha_creacion date NOT NULL,
    nombre text NOT NULL,
    descripcion text NOT NULL
);


ALTER TABLE public.usuario_rol OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 24801)
-- Name: usuario_tipo_evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_tipo_evento (
    idusuario_tipo_evento integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.usuario_tipo_evento OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 24806)
-- Name: vale; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vale (
    idvale integer NOT NULL,
    fecha_creado timestamp without time zone NOT NULL,
    creado_por text NOT NULL,
    descripcion text NOT NULL,
    monto_vale numeric(15,0) NOT NULL,
    monto_letra text NOT NULL,
    estado text NOT NULL,
    fk_idfuncionario integer NOT NULL,
    fk_idusuario integer NOT NULL
);


ALTER TABLE public.vale OWNER TO postgres;

--
-- TOC entry 3596 (class 0 OID 24577)
-- Dependencies: 214
-- Data for Name: aduana; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aduana (idaduana, nombre, telefono, direccion, sigla, eliminado) FROM stdin;
1	CENTRAL	00	ASUNCION	001	f
2	AEROP. SILVIO PETTIROSSI	555	LUQUE	002	f
3	JOSÉ FALCÓN	000	PDTE. HAYES	003	f
4	VILLETA	00	VILLETA - CENTRAL	004	f
5	PUENTE - CDE	00	PUENTE DE LA AMISTAD - CDE	005	f
6	CONCEPCIÓN	00	PUERTO DE CONCEPCIÓN	006	f
7	ENCARNACIÓN	00	ENCARNACIÓN 	007	f
8	PILAR	00	PILAR - ÑEEMBUCÚ	008	f
9	SALTO DEL GUAIRÁ	00	SALTO DEL GUAIRÁ - CANINDEYÚ	009	f
10	PEDRO JUAN CABALLERO	00	PEDRO J. CABALLERO - AMAMBAY	010	f
11	NO POSEE REGISTRO	00	SIN REGISTRO	011	f
12	ITÁ ENRAMADA	00	LAMBARÉ - CENTRAL	012	f
13	AEROPUERTO GUARANÍ	00	MINGA GUAZÚ - ALTO PARANÁ	013	f
14	NO POSEE REGISTRO	00	SIN REGISTRO	014	f
15	PAKSA	00	Bº SAJONIA - ASUNCIÓN	015	f
16	MARISCAL ESTIGARRIBIA	00	MCAL. ESTIGARRIBIA - CHACO	016	f
17	CAACUPEMÍ	00	CEBALLOS CUÉ - CENTRAL	017	f
18	TERMINAL DE CARGAS KM 12	00	KM 12 CDAD. DEL ESTE.	018	f
19	TERPORT	00	PTO TERPORT - SAN ANTONIO - CENTRAL	019	f
20	SANTA HELENA (BRASIL)	00	SANTA HELENA - BRASIL	020	f
22	ZONA FRANCA TRANS TRADE	00	KM 10  CDAD. DEL ESTE	022	f
21	ZONA FRANCA GLOBAL	00	KM 11 - CDAD. DEL ESTE	021	f
23	CAMPESTRE	00	KM 10, CDAD. DEL ESTE	023	f
24	PUERTOS Y ESTIBAJE FENIX	00	MARIANO R. ALONSO - CENTRAL	024	f
25	CHACOÍ	00	PUERTO FALCÓN - PDTE. HAYES	025	f
26	GICAL	00	RUTA TRANSCHACO - M.R.A. - CENTRAL	026	f
27	CEREGRAL SAECA	00	FERNANDO DE LA MORA - CENTRAL	027	f
28	CARMELO PERALTA	00	CARMELO PERALTA - ALTO PARAGUAY	028	f
29	CODESA	00	AEROP. SILVIO PETTIROSSI - LUQUE	029	f
30	SOLUCIÓN LOGÍSTICA	00	MARIANO ROQUE ALONSO - CENTRAL	030	f
31	EMPEDRIL S.A.	00	MARIANO R. ALONSO - CENTRAL	031	f
32	PUERTO SEGURO FLUVIAL S.A.	00	VILLETA - CENTRAL	032	f
33	LOGISTIC GROUP 	00	LAMBARÉ - CENTRAL	033	f
34	ALGESA SAN JOSÉ	00	SAN JUAN - ENCARNACIÓN	034	f
35	NANAWA	00	JOSÉ FALCÓN - PDTE. HAYES	035	f
36	ZOFRAMAQ	00	CDAD. PDTE. FRANCO - ALTO PARANÁ	036	f
37	PUERTOS Y ESTIBAJES 	-	-	-	f
38	-	-	--	-	f
39	TERPORT VILLETA 	-	-	-	f
\.


--
-- TOC entry 3597 (class 0 OID 24583)
-- Dependencies: 215
-- Data for Name: caja_detalle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.caja_detalle (idcaja_detalle, fecha_creado, creado_por, descripcion, estado, monto_liquidacion_credito, monto_recibo_pago, monto_gasto, monto_vale, fk_idusuario, fk_idvale, fk_idgasto, fk_idliquidacion_final, fk_recibo_pago_tercero) FROM stdin;
5	2022-02-17 13:41:59.318	5-Angel Rios 	RECIBO TERCERO: DESPACHO ADUANERO PARA: DIGNO ALFREDO TALAVERA ROJAS	EMITIDO	0	900000	0	0	5	0	0	0	1
4	2022-02-17 13:37:21.752	5-Angel Rios 	DESPACHO: 22005IC04000981N	ANULADO	91287196	0	0	0	5	0	0	4	0
6	2022-04-27 14:14:21.188	5-Angel Rios 	DESPACHO: 22005IC04008320X	EMITIDO	78656671	0	0	0	5	0	0	5	0
3	2022-02-17 12:03:18.458	1-Digno Alfredo	DESPACHO: 6467456	ANULADO	10	0	0	0	1	0	0	3	0
7	2022-04-27 15:11:59.704	5-Angel Rios 	DESPACHO: 22005IM04000192R	EMITIDO	4474051	0	0	0	5	0	0	6	0
8	2022-04-27 15:41:48.777	5-Angel Rios 	DESPACHO: 22005IC04008254Y	ANULADO	18880559	0	0	0	5	0	0	7	0
10	2022-04-27 17:27:30.672	4-Augusto Montelos	VALE PARA:ROMINNA OLAZAR MENDIETA \nADELANTO	EMITIDO	0	0	0	1000000	4	2	0	0	0
11	2022-04-27 17:28:07.616	4-Augusto Montelos	VALE PARA:ANGEL RIOS \nADELANTO	EMITIDO	0	0	0	2100000	4	3	0	0	0
12	2022-04-27 17:33:46.024	4-Augusto Montelos	VALE PARA:GUILLERMO RODRIGO VEGA AYALA \nADELANTO	EMITIDO	0	0	0	140000	4	4	0	0	0
13	2022-04-27 17:35:41.176	4-Augusto Montelos	VALE PARA:GUILLERMO RODRIGO VEGA AYALA \nADELANTO	EMITIDO	0	0	0	5000000	4	5	0	0	0
14	2022-04-27 17:35:53.401	4-Augusto Montelos	VALE PARA:GUILLERMO RODRIGO VEGA AYALA \nADELANTO	EMITIDO	0	0	0	5000000	4	6	0	0	0
16	2022-04-28 14:09:39.004	5-Angel Rios 	DESPACHO: 22005IC04008374R	ANULADO	2968111	0	0	0	5	0	0	9	0
17	2022-04-28 14:10:36.664	5-Angel Rios 	DESPACHO: 22005IC04008374R	EMITIDO	2968111	0	0	0	5	0	0	10	0
9	2022-04-27 15:43:48.034	5-Angel Rios 	DESPACHO: 22005IC04008254Y	ANULADO	4218371	0	0	0	5	0	0	8	0
18	2022-04-28 14:14:45.762	5-Angel Rios 	DESPACHO: 22005IC04008254Y	EMITIDO	4218371	0	0	0	5	0	0	11	0
19	2022-04-29 08:40:14.97	4-Augusto Montelos	VALE PARA:GUILLERMO RODRIGO VEGA AYALA \nADELANTO	EMITIDO	0	0	0	1000000	4	7	0	0	0
20	2022-05-03 12:24:40.247	5-Angel Rios 	GASTO: 001-002-013516	ANULADO	0	0	150000	0	5	0	3	0	0
23	2022-05-03 12:32:36.49	5-Angel Rios 	GASTO: 005-004-0042134\t	ANULADO	0	0	120000	0	5	0	6	0	0
24	2022-05-03 12:33:16.53	5-Angel Rios 	GASTO: 005-004-0042134	EMITIDO	0	0	120000	0	5	0	7	0	0
22	2022-05-03 12:26:02.631	5-Angel Rios 	GASTO: 001-002-05454	ANULADO	0	0	150000	0	5	0	5	0	0
15	2022-04-28 12:40:09.51	4-Augusto Montelos	GASTO: NINGUNA	ANULADO	0	0	100000	0	4	0	2	0	0
2	2022-02-17 10:41:20.073	1-Digno Alfredo	GASTO: NINGUNA	ANULADO	0	0	0	0	1	0	1	0	0
21	2022-05-03 12:25:31.655	5-Angel Rios 	GASTO: 001-002-05564645	ANULADO	0	0	1500000	0	5	0	4	0	0
25	2022-05-03 12:34:18.51	5-Angel Rios 	GASTO: 005-005-0069280	EMITIDO	0	0	100000	0	5	0	8	0	0
26	2022-05-03 12:34:57.391	5-Angel Rios 	GASTO: 012-004-0013521	EMITIDO	0	0	100000	0	5	0	9	0	0
27	2022-05-03 12:35:24.582	5-Angel Rios 	GASTO: 033-003-0003863	EMITIDO	0	0	400000	0	5	0	10	0	0
28	2022-05-03 12:37:56.55	5-Angel Rios 	GASTO: 004-002-0000172	EMITIDO	0	0	15000	0	5	0	11	0	0
29	2022-05-03 12:38:28.694	5-Angel Rios 	GASTO: 007-002-0253494	EMITIDO	0	0	22000	0	5	0	12	0	0
30	2022-05-03 12:38:56.078	5-Angel Rios 	GASTO: 007-001-0267270	EMITIDO	0	0	22000	0	5	0	13	0	0
31	2022-05-03 12:39:33.705	5-Angel Rios 	GASTO: 01184\t	EMITIDO	0	0	60000	0	5	0	14	0	0
32	2022-05-03 12:40:04.912	5-Angel Rios 	GASTO: 002-039-0002053	EMITIDO	0	0	25000	0	5	0	15	0	0
33	2022-05-03 12:41:29.385	5-Angel Rios 	GASTO: 001-004-4575607 BANCARD SA COMISION	EMITIDO	0	0	2499	0	5	0	16	0	0
34	2022-05-03 12:42:01.997	5-Angel Rios 	GASTO: 001-001-0018818	EMITIDO	0	0	310500	0	5	0	17	0	0
35	2022-05-03 12:42:26.926	5-Angel Rios 	GASTO: 001-001-0018819	EMITIDO	0	0	448500	0	5	0	18	0	0
37	2022-05-03 12:48:02.969	5-Angel Rios 	GASTO: 001-001-0158601 - EMBUDO PARA VEHICULO \t	EMITIDO	0	0	42000	0	5	0	20	0	0
38	2022-05-03 16:00:55.67	5-Angel Rios 	DESPACHO: 22009IC04000587T	ANULADO	21066909	0	0	0	5	0	0	12	0
39	2022-05-03 16:05:24.607	5-Angel Rios 	DESPACHO: 22009IC04000587T	ANULADO	8769670	0	0	0	5	0	0	13	0
40	2022-05-03 16:08:34.149	5-Angel Rios 	DESPACHO: 22009IC04000587T	EMITIDO	8769670	0	0	0	5	0	0	14	0
41	2022-05-04 12:37:18.968	5-Angel Rios 	DESPACHO: 22005IC04008737U	EMITIDO	82375784	0	0	0	5	0	0	15	0
42	2022-05-04 12:48:43.71	4-Augusto Montelos	VALE PARA:GUILLERMO RODRIGO VEGA AYALA \nADELANTO EN FECHA 04-05-22	EMITIDO	0	0	0	1500000	4	8	0	0	0
1	2022-02-17 10:40:27.292	1-Digno Alfredo	VALE: ADELANTO	ANULADO	0	0	0	0	1	1	0	0	0
43	2022-05-04 12:59:56.574	5-Angel Rios 	DESPACHO: 22005IC04008739W	EMITIDO	41390090	0	0	0	5	0	0	16	0
44	2022-05-06 09:27:58.658	5-Angel Rios 	GASTO: 001-001-0000502/03/04	EMITIDO	0	0	12867000	0	5	0	21	0	0
45	2022-05-06 09:29:20.173	5-Angel Rios 	GASTO: 68705 / PAGO DE CUOTA DE MOTOCICLETA CUOTA 21 Y 22 DE 24	ANULADO	0	0	988000	0	5	0	22	0	0
46	2022-05-06 09:30:18.88	5-Angel Rios 	GASTO: 68705 PAGO DE COUTA DIESA; CUOTA 21/22 DE 24	EMITIDO	0	0	998000	0	5	0	23	0	0
47	2022-05-06 09:31:10.292	5-Angel Rios 	GASTO: FACT N 001-011-0001996 PAGO DE SEGURO VEHICULAR 	EMITIDO	0	0	370000	0	5	0	24	0	0
48	2022-05-06 09:33:37.991	5-Angel Rios 	GASTO: 0556 / PAGO DE ALQUILER 	EMITIDO	0	0	6210000	0	5	0	25	0	0
49	2022-05-06 09:35:29.392	5-Angel Rios 	GASTO: 500.000 AYUDA CDAP\t\n200.000 PROCESO MERCOFER \n500.000 VIATICO ASUNCION\n411.000 COMBUSTIBLE \n553.212 PAGINA WEB 	EMITIDO	0	0	2164212	0	5	0	26	0	0
50	2022-05-06 09:36:25.008	5-Angel Rios 	GASTO: 004-011-0026722 ARTICULOS DE OFICINA (AZUCAR, CAFÉ, LEHCE EN POLVO)	EMITIDO	0	0	85500	0	5	0	27	0	0
51	2022-05-06 09:37:02.512	5-Angel Rios 	GASTO: 004-010-0027270 ARTICULOS DE LIMPIEZA PARA OFICINA \t	EMITIDO	0	0	174650	0	5	0	28	0	0
52	2022-05-06 09:37:55.79	5-Angel Rios 	GASTO: 001-189-1326827 PAGO FACTURA COPACO 	ANULADO	0	0	116105	0	5	0	29	0	0
53	2022-05-06 09:38:46.079	5-Angel Rios 	GASTO: 001-189-1326827 PAGO FACTURA COPACO 	EMITIDO	0	0	116105	0	5	0	30	0	0
54	2022-05-06 11:47:18.292	5-Angel Rios 	DESPACHO: 22030IC04005329M	EMITIDO	3634143	0	0	0	5	0	0	17	0
55	2022-05-06 13:40:40.982	5-Angel Rios 	DESPACHO: 22002IC04008988C	ANULADO	26259209	0	0	0	5	0	0	18	0
56	2022-05-06 13:47:59.585	5-Angel Rios 	DESPACHO: 22002IC04008988C	ANULADO	26259209	0	0	0	5	0	0	19	0
57	2022-05-06 13:50:02.765	5-Angel Rios 	DESPACHO: 22002IC04008988C	ANULADO	26979209	0	0	0	5	0	0	20	0
59	2022-05-06 14:17:52.634	5-Angel Rios 	DESPACHO: 22005IC04008880T	EMITIDO	25233643	0	0	0	5	0	0	22	0
60	2022-05-10 15:42:14.63	5-Angel Rios 	DESPACHO: 22023EC01001717E	EMITIDO	3257319	0	0	0	5	0	0	23	0
61	2022-05-12 14:39:43.863	5-Angel Rios 	DESPACHO: 22023EC01001719G	EMITIDO	3606748	0	0	0	5	0	0	24	0
62	2022-05-13 16:37:44.083	5-Angel Rios 	DESPACHO: 22005IC04009558W	EMITIDO	31522584	0	0	0	5	0	0	25	0
36	2022-05-03 12:47:04.438	5-Angel Rios 	GASTO: 005-001-0089861 COMBUSTIBLE - 100.000\n028-001-0007706 COMBUSTIBLE - 510.243\nVARIAS FACTURAS PEAJE - 100.000	ANULADO	0	0	710243	0	5	0	19	0	0
63	2022-05-16 10:44:10.672	5-Angel Rios 	GASTO: 005-001-0089861 COMBUSTIBLE - 100.000\n028-001-0007706 COMBUSTIBLE - 510.243\nVARIAS FACTURAS PEAJE - 100.000\nCASO MERCOFER	EMITIDO	0	0	710243	0	5	0	31	0	0
64	2022-05-16 10:44:50.425	5-Angel Rios 	GASTO: 005-001-0089861 COMBUSTIBLE - 100.000\n028-001-0007706 COMBUSTIBLE - 510.243\nVARIAS FACTURAS PEAJE - 100.000\nCASO MERCOFER 	EMITIDO	0	0	710243	0	5	0	31	0	0
65	2022-05-17 12:09:16.578	5-Angel Rios 	DESPACHO: 22005IC04009800M	ANULADO	3425068	0	0	0	5	0	0	26	0
66	2022-05-17 12:14:51.694	5-Angel Rios 	DESPACHO: 22005IC04009800M	ANULADO	12158822	0	0	0	5	0	0	26	0
67	2022-05-17 12:51:36.339	1-Digno Alfredo	DESPACHO: 22005IC04000981N	ANULADO	91287196	0	0	0	1	0	0	26	0
68	2022-05-17 12:53:06.804	1-Digno Alfredo	DESPACHO: 6467456	ANULADO	10	0	0	0	1	0	0	26	0
69	2022-05-17 13:34:35.925	1-Digno Alfredo	DESPACHO: 6467456	ANULADO	115604850	0	0	0	1	0	0	27	0
70	2022-05-17 13:37:22.12	1-Digno Alfredo	DESPACHO: ytryrtyru	ANULADO	50	0	0	0	1	0	0	28	0
71	2022-05-17 13:48:28.107	5-Angel Rios 	DESPACHO: 22005IC04009800M	EMITIDO	3425068	0	0	0	5	0	0	29	0
72	2022-05-18 09:42:34.656	5-Angel Rios 	DESPACHO: 22029IC04001134K	ANULADO	5210830	0	0	0	5	0	0	30	0
73	2022-05-18 11:08:03.25	5-Angel Rios 	DESPACHO: 22029IC04001134K	ANULADO	8355637	0	0	0	5	0	0	31	0
74	2022-05-18 11:19:45.342	5-Angel Rios 	DESPACHO: 22029IC04001134K	EMITIDO	8355637	0	0	0	5	0	0	32	0
75	2022-05-24 09:46:24.741	5-Angel Rios 	DESPACHO: 22005IC04010324F	EMITIDO	71196509	0	0	0	5	0	0	33	0
76	2022-05-24 09:57:11.382	5-Angel Rios 	DESPACHO: 22005IC04010326H	EMITIDO	69005861	0	0	0	5	0	0	34	0
77	2022-05-24 11:33:54.027	5-Angel Rios 	DESPACHO: 22005IM04000231L	EMITIDO	2775452	0	0	0	5	0	0	35	0
78	2022-05-24 12:06:26.009	5-Angel Rios 	DESPACHO: 22005IC04010303C	ANULADO	15350999	0	0	0	5	0	0	36	0
79	2022-05-24 12:09:33.011	5-Angel Rios 	DESPACHO: 22005IC04010303C	EMITIDO	15350999	0	0	0	5	0	0	37	0
81	2022-05-26 12:03:23.579	5-Angel Rios 	DESPACHO: 22005IC04010686Z	EMITIDO	5419760	0	0	0	5	0	0	39	0
82	2022-05-26 13:42:51.626	5-Angel Rios 	DESPACHO: 22023EC01002080V	EMITIDO	3559798	0	0	0	5	0	0	40	0
83	2022-05-30 15:11:44.788	5-Angel Rios 	DESPACHO: 22038IC04005438V	ANULADO	5544846	0	0	0	5	0	0	41	0
84	2022-05-30 15:14:35.621	5-Angel Rios 	DESPACHO: 22038IC04005438V	EMITIDO	5534846	0	0	0	5	0	0	42	0
80	2022-05-26 11:08:33.579	5-Angel Rios 	DESPACHO: 22005IC04010454J	ANULADO	23206735	0	0	0	5	0	0	38	0
85	2022-05-31 09:57:57.888	5-Angel Rios 	DESPACHO: 22005IC04010983Z	EMITIDO	44967611	0	0	0	5	0	0	43	0
86	2022-05-31 10:21:46.428	5-Angel Rios 	DESPACHO: 22005IC04011037H	ANULADO	5273189	0	0	0	5	0	0	44	0
87	2022-05-31 10:24:19.986	5-Angel Rios 	DESPACHO: 22005IC04011037H	EMITIDO	5273189	0	0	0	5	0	0	45	0
88	2022-05-31 10:54:14.175	5-Angel Rios 	DESPACHO: 22005IC04011020W	EMITIDO	3169620	0	0	0	5	0	0	46	0
89	2022-06-02 14:40:56.129	5-Angel Rios 	DESPACHO: 22002IM04005115Y	ANULADO	2451150	0	0	0	5	0	0	47	0
90	2022-06-02 14:51:36.506	5-Angel Rios 	DESPACHO: 22002IM04005115Y	EMITIDO	3405510	0	0	0	5	0	0	48	0
91	2022-06-02 16:47:01.184	5-Angel Rios 	DESPACHO: 22038IC04005516S	ANULADO	8048261	0	0	0	5	0	0	49	0
92	2022-06-03 12:18:34.573	5-Angel Rios 	DESPACHO: 22038IC04005516S	EMITIDO	8048261	0	0	0	5	0	0	50	0
93	2022-06-03 14:58:46.233	5-Angel Rios 	DESPACHO: 22005IC04011310B	EMITIDO	48787259	0	0	0	5	0	0	51	0
94	2022-06-03 15:23:24.323	5-Angel Rios 	DESPACHO: 22038IC04005516S	EMITIDO	12048261	0	0	0	5	0	0	52	0
95	2022-06-06 09:41:45.612	5-Angel Rios 	DESPACHO: 22005IC04011379Z	EMITIDO	19673967	0	0	0	5	0	0	53	0
96	2022-06-06 10:14:36.383	5-Angel Rios 	DESPACHO: 22005IM04000258U	EMITIDO	2966759	0	0	0	5	0	0	54	0
97	2022-06-06 16:12:55.34	5-Angel Rios 	DESPACHO: 22038IC04005564V	ANULADO	8018318	0	0	0	5	0	0	55	0
98	2022-06-06 16:23:07.228	5-Angel Rios 	DESPACHO: 22038IC04005564V	EMITIDO	8018318	0	0	0	5	0	0	56	0
99	2022-06-07 10:17:55.397	5-Angel Rios 	GASTO: 0003290\t\t20.000\n020-001-0024843 \t22.000\n0002549\t\t20.000\n020-001-0024710\t22.000\n0000996\t\t20.000\n0000342\t\t40.000\n004-002-0000724\t20.000\n	EMITIDO	0	0	164000	0	5	0	31	0	0
100	2022-06-07 10:35:05.348	5-Angel Rios 	GASTO: 010-004-0000238\t100.000\n003-009-0051339\t150.000\n001-003-0000114\t100.000\n003-009-0052098\t400.000\n003-009-0054070\t100.000	EMITIDO	0	0	850000	0	5	0	32	0	0
101	2022-06-07 10:39:04.852	5-Angel Rios 	GASTO: SIN COMP. ALQUILER \t\t6.156.000\n001-009-0148787 TIGO\t\t400.000\n001-011-0001996 SEGURO \t\t370.000\n001-001-0000505 SALARIO\t\t9.900.000\n0089069 DOS CUOTAS DIESA\t\t988.000	EMITIDO	0	0	17814000	0	5	0	33	0	0
102	2022-06-07 10:41:37.115	5-Angel Rios 	GASTO: 001-001-0010085 RECARGA EXTIN\t110.000\n001-003-0004386 HOSPEDAJE\t\t757.000\n006-027-0002235 CARPETAS \t\t57.000\n	EMITIDO	0	0	924200	0	5	0	34	0	0
103	2022-06-07 10:45:23.13	5-Angel Rios 	GASTO: SIN COMPROBANTE \t\t2.968.000\nSIN COMPROBANTE \t\t150.000\n004-010-0037505 ART LIMPIEZA\t145.700\n\n	EMITIDO	0	0	3263700	0	5	0	35	0	0
104	2022-06-07 11:30:25.421	5-Angel Rios 	DESPACHO: 22005IC04011473L	ANULADO	11351027	0	0	0	5	0	0	57	0
105	2022-06-07 14:43:00.561	5-Angel Rios 	DESPACHO: 22005IC04011473L	EMITIDO	11351027	0	0	0	5	0	0	58	0
106	2022-06-08 14:51:50.668	5-Angel Rios 	DESPACHO: 22005IC04011603G	ANULADO	7593701	0	0	0	5	0	0	59	0
107	2022-06-08 14:52:47.103	5-Angel Rios 	DESPACHO: 22005IC04011603G	ANULADO	7428701	0	0	0	5	0	0	60	0
108	2022-06-08 15:14:21.177	5-Angel Rios 	DESPACHO: 22005IC04011603G	ANULADO	7318701	0	0	0	5	0	0	61	0
109	2022-06-08 15:26:57.23	5-Angel Rios 	DESPACHO: 22005IC04011603G	EMITIDO	7318701	0	0	0	5	0	0	62	0
58	2022-05-06 13:53:06.862	5-Angel Rios 	DESPACHO: 22002IC04008988C	ANULADO	26979209	0	0	0	5	0	0	21	0
110	2022-06-08 16:16:18.633	5-Angel Rios 	DESPACHO: 22005IC04011601E	ANULADO	7841217	0	0	0	5	0	0	63	0
111	2022-06-08 17:15:37.953	5-Angel Rios 	DESPACHO: 22005IC04011601E	ANULADO	7485659	0	0	0	5	0	0	66	0
112	2022-06-08 17:23:51.329	5-Angel Rios 	DESPACHO: 22005IC04011601E	ANULADO	7485659	0	0	0	5	0	0	67	0
113	2022-06-10 08:53:16.765	5-Angel Rios 	DESPACHO: 22005IC04011601E	EMITIDO	8112016	0	0	0	5	0	0	68	0
114	2022-06-10 09:19:58.168	5-Angel Rios 	DESPACHO: 22029IC04000273@	ANULADO	20272686	0	0	0	5	0	0	70	0
115	2022-06-14 11:46:57.964	5-Angel Rios 	DESPACHO: 22002IC04008988C	EMITIDO	29314336	0	0	0	5	0	0	74	0
116	2022-06-14 13:22:29.774	5-Angel Rios 	DESPACHO: 22005IC04011785R	EMITIDO	18178700	0	0	0	5	0	0	75	0
117	2022-06-14 13:48:15.63	5-Angel Rios 	DESPACHO: 22005IC04011790N	EMITIDO	37396589	0	0	0	5	0	0	76	0
119	2022-06-17 09:34:20.972	5-Angel Rios 	DESPACHO: 22023EC01002423W	ANULADO	3169021	0	0	0	5	0	0	81	0
118	2022-06-17 09:13:11.724	5-Angel Rios 	DESPACHO: 22023EC01002422V	ANULADO	3432977	0	0	0	5	0	0	80	0
120	2022-06-17 10:03:37.425	5-Angel Rios 	DESPACHO: 22005IC04012075K	ANULADO	50664265	0	0	0	5	0	0	82	0
121	2022-06-17 10:06:24.375	5-Angel Rios 	DESPACHO: 22023EC01002423W	ANULADO	3169021	0	0	0	5	0	0	83	0
124	2022-06-17 11:17:57.454	5-Angel Rios 	DESPACHO: 22023EC01002423W	EMITIDO	3169021	0	0	0	5	0	0	86	0
122	2022-06-17 10:11:05.337	5-Angel Rios 	DESPACHO: 22023EC01002422V	ANULADO	3432977	0	0	0	5	0	0	84	0
123	2022-06-17 10:31:37.326	5-Angel Rios 	DESPACHO: 22005IC04012075K	ANULADO	11285801	0	0	0	5	0	0	85	0
125	2022-06-17 11:20:02.788	5-Angel Rios 	DESPACHO: 22023EC01002422V	EMITIDO	3432977	0	0	0	5	0	0	87	0
126	2022-06-17 11:23:25.464	5-Angel Rios 	DESPACHO: 22005IC04012075K	EMITIDO	11285801	0	0	0	5	0	0	88	0
127	2022-06-20 09:45:22.829	5-Angel Rios 	DESPACHO: 22005IC04012187Y	EMITIDO	102719449	0	0	0	5	0	0	89	0
128	2022-06-20 14:30:49.446	5-Angel Rios 	DESPACHO: 22024IC04003398T	EMITIDO	11155509	0	0	0	5	0	0	90	0
129	2022-06-21 11:12:24.31	5-Angel Rios 	DESPACHO: 22005IC04012075K	EMITIDO	11285801	0	0	0	5	0	0	91	0
131	2022-06-29 10:36:48.469	5-Angel Rios 	DESPACHO: 22005IC04012780N	ANULADO	13325442	0	0	0	5	0	0	93	0
132	2022-06-29 11:20:26.287	5-Angel Rios 	DESPACHO: 22005IC04012849T	ANULADO	15510652	0	0	0	5	0	0	94	0
135	2022-06-29 13:22:52.28	5-Angel Rios 	DESPACHO: 22005IC04012849T	EMITIDO	15510652	0	0	0	5	0	0	97	0
133	2022-06-29 11:43:16.9	5-Angel Rios 	DESPACHO: 22005IC04012780N	ANULADO	12225442	0	0	0	5	0	0	95	0
136	2022-06-29 13:26:43.249	5-Angel Rios 	DESPACHO: 22005IC04012780N	EMITIDO	12225442	0	0	0	5	0	0	98	0
130	2022-06-29 09:19:11.293	5-Angel Rios 	DESPACHO: 22005IC04012892R	ANULADO	6069360	0	0	0	5	0	0	92	0
134	2022-06-29 13:19:55.09	5-Angel Rios 	DESPACHO: 22031IC04004017G	ANULADO	7580637	0	0	0	5	0	0	96	0
138	2022-06-29 13:41:43.342	5-Angel Rios 	DESPACHO: 22031IC04004017G	EMITIDO	6358255	0	0	0	5	0	0	100	0
137	2022-06-29 13:27:46.987	5-Angel Rios 	DESPACHO: 22005IC04012892R	ANULADO	6069360	0	0	0	5	0	0	99	0
140	2022-07-04 14:00:11.807	5-Angel Rios 	DESPACHO: 22005IC04012892R	EMITIDO	6069360	0	0	0	5	0	0	102	0
141	2022-07-04 14:40:01.869	5-Angel Rios 	DESPACHO: 22005IC04013105F	EMITIDO	11076041	0	0	0	5	0	0	103	0
142	2022-07-04 15:14:56.386	5-Angel Rios 	DESPACHO: 22002IC04013323E	ANULADO	21643699	0	0	0	5	0	0	104	0
143	2022-07-04 15:15:46.054	5-Angel Rios 	DESPACHO: 22002IC04013323E	EMITIDO	6914095	0	0	0	5	0	0	105	0
139	2022-06-30 14:13:38.517	5-Angel Rios 	DESPACHO: 22018EC01001795Y	ANULADO	3649949	0	0	0	5	0	0	101	0
144	2022-07-04 15:21:07.58	5-Angel Rios 	DESPACHO: 22018EC01001795Y	ANULADO	3649949	0	0	0	5	0	0	106	0
145	2022-07-04 15:23:16.863	5-Angel Rios 	DESPACHO: 22018EC01001795Y	EMITIDO	3649949	0	0	0	5	0	0	107	0
146	2022-07-05 13:59:06.325	5-Angel Rios 	DESPACHO: 22018EC01001851H	EMITIDO	3600911	0	0	0	5	0	0	108	0
147	2022-07-05 15:31:50.246	5-Angel Rios 	DESPACHO: 22005IC04013396R	ANULADO	127092489	0	0	0	5	0	0	109	0
148	2022-07-05 16:25:06.739	5-Angel Rios 	DESPACHO: 22005IC04013396R	EMITIDO	14260669	0	0	0	5	0	0	110	0
150	2022-07-06 13:49:54.977	5-Angel Rios 	DESPACHO: 22005IC04013407K	ANULADO	13931647	0	0	0	5	0	0	112	0
152	2022-07-06 14:47:22.342	5-Angel Rios 	DESPACHO: 22005IC04013496S	EMITIDO	9343935	0	0	0	5	0	0	114	0
151	2022-07-06 14:14:21.685	5-Angel Rios 	DESPACHO: 22005IC04013407K	ANULADO	5314992	0	0	0	5	0	0	113	0
153	2022-07-06 14:48:43.243	5-Angel Rios 	DESPACHO: 22005IC04013407K	ANULADO	5314992	0	0	0	5	0	0	115	0
149	2022-07-06 13:26:53.18	5-Angel Rios 	DESPACHO: 22005IC04013270X	ANULADO	16136806	0	0	0	5	0	0	111	0
155	2022-07-06 14:50:49.066	5-Angel Rios 	DESPACHO: 22005IC04013270X	EMITIDO	16136806	0	0	0	5	0	0	117	0
154	2022-07-06 14:49:35.174	5-Angel Rios 	DESPACHO: 22005IC04013407K	ANULADO	5314992	0	0	0	5	0	0	116	0
156	2022-07-06 14:52:55.566	5-Angel Rios 	DESPACHO: 22005IC04013407K	EMITIDO	5314992	0	0	0	5	0	0	118	0
157	2022-07-06 15:54:57.843	5-Angel Rios 	DESPACHO: 22002IM04006471U	ANULADO	3020299	0	0	0	5	0	0	119	0
158	2022-07-06 16:02:26.868	5-Angel Rios 	DESPACHO: 22002IM04006471U	EMITIDO	3020299	0	0	0	5	0	0	120	0
159	2022-07-08 12:53:02.381	5-Angel Rios 	DESPACHO: 22032IC04004226J	ANULADO	13987597	0	0	0	5	0	0	121	0
160	2022-07-08 12:59:38.763	5-Angel Rios 	DESPACHO: 22032IC04004226J	EMITIDO	13987597	0	0	0	5	0	0	122	0
161	2022-07-08 15:27:23.357	5-Angel Rios 	DESPACHO: 22005IC04013676S	EMITIDO	89124914	0	0	0	5	0	0	123	0
162	2022-07-08 15:54:41.867	5-Angel Rios 	DESPACHO: 22005IM04000310J	EMITIDO	4920585	0	0	0	5	0	0	124	0
163	2022-07-08 16:06:52.623	5-Angel Rios 	DESPACHO: 22005IC04013669U	EMITIDO	10013534	0	0	0	5	0	0	125	0
164	2022-07-12 10:07:31.077	5-Angel Rios 	DESPACHO: 22005IC04013794T	ANULADO	6512318	0	0	0	5	0	0	126	0
165	2022-07-12 13:11:58.317	5-Angel Rios 	DESPACHO: 22005IC04013794T	EMITIDO	6512318	0	0	0	5	0	0	127	0
166	2022-07-13 10:22:25.613	5-Angel Rios 	DESPACHO: 22005IC04013997B	EMITIDO	9379939	0	0	0	5	0	0	128	0
167	2022-07-13 10:35:10.72	5-Angel Rios 	GASTO: CARGA DE COMBUSTIBLE P/ GOL - 345.005\nCARGA DE COMBSUTIBLE YOYI - 100.000\nARTICULOS DE LIMPIEZA - 123.750\n	EMITIDO	0	0	568775	0	5	0	36	0	0
168	2022-07-13 10:38:04.922	5-Angel Rios 	GASTO: 02/06/22\tREFRINAR\n02/06/22\tPULVIPAR\n06/06/22\tATC\n06/06/22\tFRANCIS\n06/06/22\tFRANCIS\n09/06/22\tPULVIPAR\n15/06/22\tPULVIPAR\n22/06/22\tPULVIPAR 	EMITIDO	0	0	165000	0	5	0	37	0	0
169	2022-07-13 10:40:17.609	5-Angel Rios 	GASTO: 003-009-0000047\t50.000\n001-004-0001915\t100.000\n003-009-0053255\t100.000\n	EMITIDO	0	0	250000	0	5	0	38	0	0
170	2022-07-13 10:45:23.553	5-Angel Rios 	GASTO: 001-009-0560664\t205.000\n001-001-0000718\t495.000\n001-068-2230904\t245.820\n001-189-1699691\t115.865\n001-011-0001996\t370.000\n001-001-0000506\t12.867.067\nALQUILER \t\t6.183.000	EMITIDO	0	0	20531572	0	5	0	39	0	0
227	2022-09-06 14:11:27.462	5-Angel Rios 	DESPACHO: 22005IC04018041J	EMITIDO	3652874	0	0	0	5	0	0	178	0
171	2022-07-13 10:54:25.497	5-Angel Rios 	GASTO: 006-025-0005963\t102.000 HOJAS OFICIO\n001-001-0002077\t720.000 MAPA DEL MUNDO\n001-001-0000613\t2.500.000 MONICA ESPIN\n001-001-0024677\t160.000 REP. GOL\n001-001-0024674\t2.290.000 REP. GOL\n001-003-0110559\t796.500 CUBIERTAS GOL\n001-001-0024731\t400.000 REP. GOL\n001-001-0078867\t40.000 REC EXTI\n001-001-0019115\t848.700 MANT IMPRESORA\n001-002-0001073\t1.200.000 REP GOL\n001-001-0002261\t1.180.000 CUADROS OFI\n\n	EMITIDO	0	0	8149200	0	5	0	40	0	0
172	2022-07-14 12:40:47.366	5-Angel Rios 	DESPACHO: 22009IC04000909R	ANULADO	4592576	0	0	0	5	0	0	130	0
174	2022-07-14 14:14:32.243	5-Angel Rios 	DESPACHO: 22005IC04014217K	EMITIDO	9361922	0	0	0	5	0	0	132	0
175	2022-07-18 16:12:42.343	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	114900930	0	0	0	5	0	0	133	0
176	2022-07-18 16:16:34.294	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	122202651	0	0	0	5	0	0	134	0
177	2022-07-18 16:18:54.886	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	122202651	0	0	0	5	0	0	135	0
179	2022-07-19 08:59:42.995	5-Angel Rios 	DESPACHO: 22005IC04014373N	EMITIDO	4712468	0	0	0	5	0	0	137	0
178	2022-07-18 16:28:10.267	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	122702651	0	0	0	5	0	0	136	0
180	2022-07-19 09:23:01.038	5-Angel Rios 	DESPACHO: 22005IC04014423J	ANULADO	15723426	0	0	0	5	0	0	138	0
182	2022-07-19 09:55:11.57	5-Angel Rios 	DESPACHO: 22005IC04014423J	EMITIDO	15723426	0	0	0	5	0	0	140	0
181	2022-07-19 09:53:57.129	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	122035395	0	0	0	5	0	0	139	0
183	2022-07-19 10:25:27.543	5-Angel Rios 	DESPACHO: 22017IC04003694U	ANULADO	122447751	0	0	0	5	0	0	141	0
184	2022-07-19 12:18:07.89	5-Angel Rios 	DESPACHO: 22017IC04003694U	EMITIDO	121347751	0	0	0	5	0	0	142	0
185	2022-07-19 13:32:01.968	5-Angel Rios 	DESPACHO: 22002IRE4002998K	EMITIDO	4501349	0	0	0	5	0	0	143	0
173	2022-07-14 13:42:43.659	5-Angel Rios 	DESPACHO: 22009IC04000909R	ANULADO	4807376	0	0	0	5	0	0	131	0
186	2022-07-19 15:51:41.445	5-Angel Rios 	DESPACHO: 22009IC04000909R	EMITIDO	4807876	0	0	0	5	0	0	144	0
189	2022-07-29 12:45:12.263	5-Angel Rios 	DESPACHO: 22005IC04014982T	EMITIDO	8418043	0	0	0	5	0	0	147	0
187	2022-07-28 12:46:32.673	5-Angel Rios 	DESPACHO: 22005IC04014759V	ANULADO	67794564	0	0	0	5	0	0	145	0
188	2022-07-29 11:26:32.531	5-Angel Rios 	DESPACHO: 22005IC0401568P	ANULADO	111066101	0	0	0	5	0	0	146	0
192	2022-07-29 15:24:09.874	5-Angel Rios 	DESPACHO: 22002IC04015355L	ANULADO	7650454	0	0	0	5	0	0	150	0
193	2022-07-29 16:11:06.709	5-Angel Rios 	DESPACHO: 22002IC04015355L	EMITIDO	7353454	0	0	0	5	0	0	151	0
190	2022-07-29 12:55:20.768	5-Angel Rios 	DESPACHO: 22005IC04014759V	ANULADO	11286206	0	0	0	5	0	0	148	0
194	2022-07-29 16:12:00.879	5-Angel Rios 	DESPACHO: 22005IC04014759V	EMITIDO	11286206	0	0	0	5	0	0	152	0
191	2022-07-29 13:16:01.015	5-Angel Rios 	DESPACHO: 22005IC0401568P	ANULADO	11428284	0	0	0	5	0	0	149	0
195	2022-07-29 16:17:03.765	5-Angel Rios 	DESPACHO: 22005IC0401568P	EMITIDO	11428284	0	0	0	5	0	0	153	0
196	2022-08-01 15:49:59.456	5-Angel Rios 	DESPACHO: 22005IC04015268R	ANULADO	7599580	0	0	0	5	0	0	154	0
197	2022-08-01 16:35:47.806	5-Angel Rios 	DESPACHO: 22005IC04015268R	EMITIDO	7599580	0	0	0	5	0	0	155	0
198	2022-08-02 13:47:33.091	5-Angel Rios 	DESPACHO: 22018EC01002272F	EMITIDO	3617958	0	0	0	5	0	0	156	0
199	2022-08-03 09:17:53.332	5-Angel Rios 	DESPACHO: 22005IC04015515M	EMITIDO	10305694	0	0	0	5	0	0	157	0
200	2022-08-05 14:59:21.518	5-Angel Rios 	DESPACHO: 22030IC04009498A	ANULADO	133964660	0	0	0	5	0	0	158	0
201	2022-08-05 15:02:49.195	5-Angel Rios 	DESPACHO: 22030IC04009498A	ANULADO	7149936	0	0	0	5	0	0	159	0
202	2022-08-06 14:51:21.727	5-Angel Rios 	DESPACHO: 22030IC04009498A	EMITIDO	7149936	0	0	0	5	0	0	160	0
203	2022-08-08 10:16:57.874	5-Angel Rios 	GASTO: 001-001-0029756\t50.000\n003-009-0004870\t50.271\n005-004-0055067\t100.000\n017-003-0023455\t100.000\n	EMITIDO	0	0	300271	0	5	0	41	0	0
204	2022-08-08 10:23:10.466	5-Angel Rios 	GASTO: 001-001-0025516\t140.000 IMPRENTA\n006-010-0005960\t139.650 ART OFICINA\n001-001-0003545\t950.000 ARANCEL	EMITIDO	0	0	1229650	0	5	0	42	0	0
205	2022-08-08 10:25:39.235	5-Angel Rios 	GASTO: 0002767\t20.000\n0004456\t20.000\n0004474\t20.000\n0004599\t20.000\n0002603\t20.000\n0004663\t20.000\n0000818\t20.000\n0000816\t20.000\n	EMITIDO	0	0	180000	0	5	0	43	0	0
206	2022-08-08 10:27:58.025	5-Angel Rios 	GASTO: 001-189-0871449\t41.147 COPACO\n001-009-0148987\t195.000 ANDE\n710\t\t6.201.000 ALQUILER\n	ANULADO	0	0	6437147	0	5	0	44	0	0
207	2022-08-08 10:30:20.299	5-Angel Rios 	GASTO: 001-189-0871449\t41.147 COPACO\n001-009-0148987\t195.000 ANDE\n710\t\t6.201.000 ALQUILER\n001-001-0000507\t11.000.000 SALARIO	EMITIDO	0	0	17437147	0	5	0	45	0	0
208	2022-08-08 10:33:40.374	5-Angel Rios 	GASTO: DIFE SALARIO\t\t2.672.333\nINFOR MAQUILA\t314.495\nREMEDIOS\t\t18.200\nART OFIC\t\t300.000\n	EMITIDO	0	0	3305208	0	5	0	46	0	0
210	2022-08-09 10:21:12.9	5-Angel Rios 	VALE PARA:ANGEL RIOS \nVALE SALARIO MES DE AGOSTO	ANULADO	0	0	0	100000	5	9	0	0	0
211	2022-08-10 14:12:41.816	5-Angel Rios 	DESPACHO: 22002IC0416590N	EMITIDO	3304569	0	0	0	5	0	0	162	0
213	2022-08-22 14:31:33.439	5-Angel Rios 	DESPACHO: 22005IC04017008L	EMITIDO	2351920	0	0	0	5	0	0	164	0
214	2022-08-22 14:43:18.315	5-Angel Rios 	DESPACHO: 22005IC04017002F	EMITIDO	9503004	0	0	0	5	0	0	165	0
212	2022-08-22 14:18:39.534	5-Angel Rios 	DESPACHO: 22005IC04016964V	ANULADO	12905104	0	0	0	5	0	0	163	0
216	2022-08-22 15:34:32.593	5-Angel Rios 	DESPACHO: 22005IC04016964V	EMITIDO	12905104	0	0	0	5	0	0	167	0
215	2022-08-22 14:57:54.373	5-Angel Rios 	DESPACHO: 22002IC04017190K	ANULADO	1812571	0	0	0	5	0	0	166	0
217	2022-08-22 16:00:31.094	5-Angel Rios 	DESPACHO: 22002IC04017190K	EMITIDO	4125571	0	0	0	5	0	0	168	0
218	2022-08-24 09:56:27.358	5-Angel Rios 	DESPACHO: 22005IC04017095R	EMITIDO	3618974	0	0	0	5	0	0	169	0
219	2022-08-25 13:23:34.959	5-Angel Rios 	DESPACHO: 22018EC01002629L	EMITIDO	3494800	0	0	0	5	0	0	170	0
220	2022-08-26 11:40:56.54	5-Angel Rios 	DESPACHO: 22005IC04017324M	EMITIDO	9710896	0	0	0	5	0	0	171	0
222	2022-08-30 14:59:13.626	5-Angel Rios 	DESPACHO: 22005IC04017683U	EMITIDO	5735036	0	0	0	5	0	0	173	0
221	2022-08-30 14:15:15.803	5-Angel Rios 	DESPACHO: 22005IC04017699E	ANULADO	81207582	0	0	0	5	0	0	172	0
223	2022-08-31 09:29:49.543	5-Angel Rios 	DESPACHO: 22005IC04017699E	EMITIDO	7017174	0	0	0	5	0	0	174	0
209	2022-08-09 09:09:44.249	5-Angel Rios 	DESPACHO: 22002IC04016233H	ANULADO	10197155	0	0	0	5	0	0	161	0
225	2022-09-06 13:39:54.395	5-Angel Rios 	DESPACHO: 22005IC04017962U	EMITIDO	10062702	0	0	0	5	0	0	176	0
226	2022-09-06 13:54:17.224	5-Angel Rios 	DESPACHO: 22005IM04000461Z	EMITIDO	1386294	0	0	0	5	0	0	177	0
228	2022-09-06 15:06:26.53	5-Angel Rios 	DESPACHO: 22002IC04018438Z	EMITIDO	6531657	0	0	0	5	0	0	179	0
229	2022-09-06 15:24:21.468	5-Angel Rios 	DESPACHO: 22002IRE4003771A	EMITIDO	1906366	0	0	0	5	0	0	180	0
230	2022-09-07 16:24:16.478	5-Angel Rios 	DESPACHO: 22005IC04018181Y	EMITIDO	2048008	0	0	0	5	0	0	181	0
231	2022-09-07 16:34:35.816	5-Angel Rios 	DESPACHO: 22005IC04018180N	EMITIDO	3165373	0	0	0	5	0	0	182	0
232	2022-09-08 15:55:39.412	5-Angel Rios 	DESPACHO: 22005IC04018451Y	ANULADO	35832292	0	0	0	5	0	0	183	0
234	2022-09-12 11:14:16.03	5-Angel Rios 	DESPACHO: 22005IC04018593V	EMITIDO	4560285	0	0	0	5	0	0	185	0
235	2022-09-12 15:24:03.695	5-Angel Rios 	DESPACHO: 22005IC04018505Y	EMITIDO	3335814	0	0	0	5	0	0	186	0
236	2022-09-12 15:34:36.937	5-Angel Rios 	DESPACHO: 22005IC04018596B	EMITIDO	3737587	0	0	0	5	0	0	187	0
237	2022-09-12 15:45:45.206	5-Angel Rios 	DESPACHO: 22005IC04018600K	EMITIDO	9130723	0	0	0	5	0	0	188	0
238	2022-09-13 08:32:11.01	5-Angel Rios 	DESPACHO: 22018EC1002796Z	EMITIDO	3526258	0	0	0	5	0	0	189	0
239	2022-09-14 11:48:20.508	5-Angel Rios 	DESPACHO: 22005IC04018820Y	EMITIDO	7813857	0	0	0	5	0	0	190	0
240	2022-09-15 10:46:13.776	5-Angel Rios 	GASTO: 004-002-0003760\t20.000\n004-002-0003738\t20.000\n004-002-0003469\t40.000\n002-032-0000474\t30.000\n002-032-0000514\t30.000\n	EMITIDO	0	0	140000	0	5	0	47	0	0
241	2022-09-15 10:47:14.432	5-Angel Rios 	GASTO: NINGUNA	EMITIDO	0	0	341000	0	5	0	48	0	0
242	2022-09-15 10:48:19.465	5-Angel Rios 	GASTO: NINGUNA	ANULADO	0	0	500	0	5	0	49	0	0
243	2022-09-15 10:49:02.515	5-Angel Rios 	GASTO: NINGUNA	EMITIDO	0	0	550000	0	5	0	50	0	0
244	2022-09-15 10:50:32.283	5-Angel Rios 	GASTO: NINGUNA	EMITIDO	0	0	16089720	0	5	0	51	0	0
245	2022-09-15 10:51:27.68	5-Angel Rios 	GASTO: NINGUNA	EMITIDO	0	0	4932250	0	5	0	52	0	0
248	2022-09-20 14:22:38.544	5-Angel Rios 	DESPACHO: 22005IM04000489D	EMITIDO	1672083	0	0	0	5	0	0	193	0
247	2022-09-20 14:08:53.189	5-Angel Rios 	DESPACHO: 22005IC04019195U	ANULADO	9034557	0	0	0	5	0	0	192	0
249	2022-09-20 14:23:44.683	5-Angel Rios 	DESPACHO: 22005IC04019195U	EMITIDO	9034557	0	0	0	5	0	0	194	0
250	2022-09-26 08:36:48.618	5-Angel Rios 	DESPACHO: 22005IC04019675A	ANULADO	12889292	0	0	0	5	0	0	195	0
251	2022-09-26 08:42:12.024	5-Angel Rios 	DESPACHO: 22005IC04019675A	EMITIDO	12889292	0	0	0	5	0	0	196	0
252	2022-09-27 12:44:53.946	5-Angel Rios 	DESPACHO: 22005IM04000503N	EMITIDO	4064297	0	0	0	5	0	0	197	0
253	2022-09-29 11:16:43.19	5-Angel Rios 	DESPACHO: 22002IC04020237G	EMITIDO	24246178	0	0	0	5	0	0	198	0
255	2022-10-05 10:37:19.936	5-Angel Rios 	DESPACHO: 22005IC04020283K	EMITIDO	2189279	0	0	0	5	0	0	200	0
254	2022-10-05 09:45:25.658	5-Angel Rios 	DESPACHO: 22005IC04020280H	ANULADO	10159668	0	0	0	5	0	0	199	0
256	2022-10-05 10:38:20.796	5-Angel Rios 	DESPACHO: 22005IC04020280H	EMITIDO	10159668	0	0	0	5	0	0	201	0
257	2022-10-05 11:43:06.242	5-Angel Rios 	DESPACHO: 22032IC04006239P	ANULADO	9739265	0	0	0	5	0	0	202	0
258	2022-10-06 11:13:25.57	5-Angel Rios 	DESPACHO: 22032IC04006239P	EMITIDO	9739265	0	0	0	5	0	0	203	0
259	2022-10-06 16:51:44.217	5-Angel Rios 	DESPACHO: 22005IC04020555M	ANULADO	9636890	0	0	0	5	0	0	204	0
260	2022-10-07 14:11:43.703	5-Angel Rios 	DESPACHO: 22005IC04020555M	EMITIDO	9636890	0	0	0	5	0	0	205	0
261	2022-10-07 14:16:35.065	5-Angel Rios 	DESPACHO: 22018EC01003111V	EMITIDO	3947329	0	0	0	5	0	0	206	0
262	2022-10-10 11:05:39.88	5-Angel Rios 	DESPACHO: 22030IC04012492L	EMITIDO	10105659	0	0	0	5	0	0	207	0
263	2022-10-10 11:59:51.509	5-Angel Rios 	DESPACHO: 22005IC04020815L	EMITIDO	9239789	0	0	0	5	0	0	208	0
264	2022-10-10 12:32:40.55	5-Angel Rios 	DESPACHO: 22005IM04000530N	EMITIDO	2291134	0	0	0	5	0	0	209	0
265	2022-10-10 13:07:38.344	5-Angel Rios 	DESPACHO: 22005IT02001944F	EMITIDO	8632068	0	0	0	5	0	0	210	0
266	2022-10-10 13:54:54.481	5-Angel Rios 	DESPACHO: 22018EC01003167J	EMITIDO	3766097	0	0	0	5	0	0	211	0
233	2022-09-08 16:01:54.213	5-Angel Rios 	DESPACHO: 22005IC04018451Y	ANULADO	9302205	0	0	0	5	0	0	184	0
267	2022-10-10 15:58:53.66	5-Angel Rios 	DESPACHO: 22005IC04018451Y	EMITIDO	9299110	0	0	0	5	0	0	212	0
224	2022-09-06 13:21:07.433	5-Angel Rios 	DESPACHO: 22018EC01002739N	ANULADO	5944695	0	0	0	5	0	0	175	0
246	2022-09-16 14:22:05.715	5-Angel Rios 	DESPACHO: 22038IC04007766E	ANULADO	8297367	0	0	0	5	0	0	191	0
270	2022-10-14 11:10:08.888	5-Angel Rios 	DESPACHO: 22018EC01003244F	EMITIDO	3394639	0	0	0	5	0	0	218	0
271	2022-10-14 15:44:01.535	5-Angel Rios 	DESPACHO: 22005IC04021313F	EMITIDO	9182294	0	0	0	5	0	0	219	0
272	2022-10-14 15:55:13.962	5-Angel Rios 	DESPACHO: 22002IM04009915D	EMITIDO	4093625	0	0	0	5	0	0	220	0
273	2022-10-17 08:43:24.107	5-Angel Rios 	DESPACHO: 22030IC04012801P	ANULADO	11577853	0	0	0	5	0	0	221	0
274	2022-10-17 09:35:27.597	5-Angel Rios 	DESPACHO: 22030IC04012801P	EMITIDO	11452318	0	0	0	5	0	0	222	0
275	2022-10-17 10:38:32.857	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8272060	0	0	0	5	0	0	223	0
276	2022-10-17 15:49:59.639	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8478238	0	0	0	5	0	0	224	0
277	2022-10-17 15:57:47.802	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8478238	0	0	0	5	0	0	225	0
279	2022-10-18 09:56:11.257	5-Angel Rios 	DESPACHO: 22005IC04021310C	ANULADO	8963252	0	0	0	5	0	0	227	0
280	2022-10-18 10:24:53.512	5-Angel Rios 	DESPACHO: 22005IC04021442X	ANULADO	7529797	0	0	0	5	0	0	228	0
278	2022-10-17 15:59:23.22	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8478238	0	0	0	5	0	0	226	0
268	2022-10-10 16:03:18.85	5-Angel Rios 	DESPACHO: 22018EC01002739N	ANULADO	5944695	0	0	0	5	0	0	213	0
281	2022-10-18 10:46:08.987	5-Angel Rios 	DESPACHO: 22005IT02001996M	EMITIDO	14933027	0	0	0	5	0	0	229	0
269	2022-10-14 10:58:47.488	5-Angel Rios 	DESPACHO: 22005IC04020978V	ANULADO	11565424	0	0	0	5	0	0	217	0
282	2022-10-18 10:53:38.935	5-Angel Rios 	DESPACHO: 22005IC04020978V	ANULADO	6565424	0	0	0	5	0	0	230	0
283	2022-10-18 13:04:43.657	5-Angel Rios 	DESPACHO: 22005IC04020978V	EMITIDO	6565424	0	0	0	5	0	0	231	0
284	2022-10-18 13:20:55.861	5-Angel Rios 	DESPACHO: 22005IC04021310C	EMITIDO	8963252	0	0	0	5	0	0	232	0
285	2022-10-18 13:29:31.182	5-Angel Rios 	DESPACHO: 22005IC04021442X	EMITIDO	7639797	0	0	0	5	0	0	233	0
287	2022-10-19 13:49:23.156	5-Angel Rios 	DESPACHO: 22005IC04021655Y	EMITIDO	6183312	0	0	0	5	0	0	235	0
286	2022-10-19 13:11:58.257	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	4103298	0	0	0	5	0	0	234	0
288	2022-10-19 13:58:41.454	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	236	0
289	2022-10-19 13:59:25.134	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	237	0
290	2022-10-19 13:59:56.688	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	238	0
291	2022-10-19 14:00:32.859	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	239	0
292	2022-10-19 14:01:00.507	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	240	0
293	2022-10-19 14:01:22.961	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	241	0
294	2022-10-19 14:01:49.046	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3730298	0	0	0	5	0	0	242	0
295	2022-10-19 14:11:01.763	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3487298	0	0	0	5	0	0	243	0
296	2022-10-19 15:09:05.872	5-Angel Rios 	DESPACHO: 22005IT02002020Y	ANULADO	3487298	0	0	0	5	0	0	244	0
297	2022-10-19 15:14:23.491	5-Angel Rios 	DESPACHO: 22005IT02002020Y	EMITIDO	3487298	0	0	0	5	0	0	245	0
298	2022-10-20 08:39:01.002	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8675368	0	0	0	5	0	0	246	0
299	2022-10-20 08:43:05.399	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8675368	0	0	0	5	0	0	247	0
300	2022-10-20 08:44:14.566	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	9278238	0	0	0	5	0	0	248	0
301	2022-10-20 08:46:11.58	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	9278238	0	0	0	5	0	0	249	0
302	2022-10-20 08:50:03.879	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	9278238	0	0	0	5	0	0	250	0
303	2022-10-20 08:56:39.953	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	9278238	0	0	0	5	0	0	251	0
304	2022-10-20 11:51:18.621	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8478238	0	0	0	5	0	0	252	0
305	2022-10-20 12:11:13.817	5-Angel Rios 	DESPACHO: 22002IC04021343F	ANULADO	8478238	0	0	0	5	0	0	253	0
306	2022-10-20 12:12:45.449	5-Angel Rios 	DESPACHO: 22002IC04021343F	EMITIDO	8478238	0	0	0	5	0	0	254	0
307	2022-10-24 14:13:29.468	5-Angel Rios 	DESPACHO: 22030IC04013106E	EMITIDO	4062093	0	0	0	5	0	0	255	0
308	2022-10-25 10:53:38.542	5-Angel Rios 	DESPACHO: 22005IC04021979A	EMITIDO	8166299	0	0	0	5	0	0	256	0
309	2022-10-28 15:54:20.407	5-Angel Rios 	DESPACHO: 22005IC04022428N	ANULADO	9023717	0	0	0	5	0	0	257	0
310	2022-10-28 16:17:41.969	5-Angel Rios 	DESPACHO: 22005IC04022428N	ANULADO	9233717	0	0	0	5	0	0	258	0
311	2022-10-28 16:20:25.208	5-Angel Rios 	DESPACHO: 22005IC04022428N	ANULADO	9258117	0	0	0	5	0	0	259	0
312	2022-10-28 16:59:01.52	5-Angel Rios 	DESPACHO: 22005IC04022428N	ANULADO	10358117	0	0	0	5	0	0	260	0
313	2022-10-31 13:40:20.389	5-Angel Rios 	DESPACHO: 22005IC04022428N	EMITIDO	10358117	0	0	0	5	0	0	261	0
314	2022-10-31 16:43:45.52	5-Angel Rios 	DESPACHO: 22005IC04022593Z	ANULADO	5678666	0	0	0	5	0	0	262	0
316	2022-11-01 08:22:54.365	5-Angel Rios 	DESPACHO: 22005IC04022341H	ANULADO	8911522	0	0	0	5	0	0	264	0
319	2022-11-01 10:56:58.717	5-Angel Rios 	DESPACHO: 22005IC04022341H	EMITIDO	8911522	0	0	0	5	0	0	267	0
317	2022-11-01 08:40:46.561	5-Angel Rios 	DESPACHO: 22005IC04022399U	ANULADO	1743489	0	0	0	5	0	0	265	0
320	2022-11-01 10:57:54.801	5-Angel Rios 	DESPACHO: 22005IC04022399U	EMITIDO	1743489	0	0	0	5	0	0	268	0
318	2022-11-01 08:52:31.044	5-Angel Rios 	DESPACHO: 22018EC01003410A	ANULADO	3798548	0	0	0	5	0	0	266	0
321	2022-11-01 10:58:57.5	5-Angel Rios 	DESPACHO: 22018EC01003410A	EMITIDO	3798548	0	0	0	5	0	0	269	0
322	2022-11-01 15:07:47.245	5-Angel Rios 	DESPACHO: 22005IC04022651L	ANULADO	3703830	0	0	0	5	0	0	270	0
324	2022-11-01 15:31:58.082	5-Angel Rios 	DESPACHO: 22005IC04022651L	EMITIDO	3703830	0	0	0	5	0	0	272	0
323	2022-11-01 15:20:16.408	5-Angel Rios 	DESPACHO: 22005IT02002128A	ANULADO	1876436	0	0	0	5	0	0	271	0
325	2022-11-02 07:28:49.092	5-Angel Rios 	DESPACHO: 22005IT02002128A	EMITIDO	1877436	0	0	0	5	0	0	273	0
315	2022-10-31 16:59:18.864	5-Angel Rios 	DESPACHO: 22005IC04022593Z	ANULADO	4450640	0	0	0	5	0	0	263	0
326	2022-11-02 15:26:31.961	5-Angel Rios 	DESPACHO: 22005IC04022593Z	EMITIDO	4450640	0	0	0	5	0	0	274	0
327	2022-11-07 10:06:58.266	5-Angel Rios 	DESPACHO: 22018EC01003478Y	EMITIDO	3735922	0	0	0	5	0	0	275	0
328	2022-11-08 16:24:49.05	5-Angel Rios 	DESPACHO: 22002IC04022934M	EMITIDO	6797381	0	0	0	5	0	0	276	0
331	2022-11-11 13:06:07.98	5-Angel Rios 	DESPACHO: 22005IC04023521X	EMITIDO	6963937	0	0	0	5	0	0	279	0
329	2022-11-10 08:56:23.63	5-Angel Rios 	DESPACHO: 22002IC04022711F	ANULADO	13083809	0	0	0	5	0	0	277	0
332	2022-11-14 11:17:16.448	5-Angel Rios 	DESPACHO: 22002IC04022711F	EMITIDO	12878809	0	0	0	5	0	0	280	0
330	2022-11-10 09:15:48.692	5-Angel Rios 	DESPACHO: 22002IC04022713H	ANULADO	15354402	0	0	0	5	0	0	278	0
333	2022-11-14 11:22:14.054	5-Angel Rios 	DESPACHO: 22002IC04022713H	EMITIDO	15139402	0	0	0	5	0	0	281	0
334	2022-11-14 15:37:03.379	5-Angel Rios 	DESPACHO: 22005IM04000596C	EMITIDO	1601235	0	0	0	5	0	0	282	0
335	2022-11-18 14:59:05.513	5-Angel Rios 	DESPACHO: 22005IT02002225V	EMITIDO	7483591	0	0	0	5	0	0	283	0
336	2022-11-18 15:15:05.04	5-Angel Rios 	DESPACHO: 22005IT02002254A	EMITIDO	5901693	0	0	0	5	0	0	284	0
337	2022-11-21 11:29:05.296	5-Angel Rios 	DESPACHO: 22002IC04023965R	EMITIDO	3440903	0	0	0	5	0	0	286	0
338	2022-11-25 13:29:10.132	5-Angel Rios 	DESPACHO: 22038IC04009378F	ANULADO	9787069	0	0	0	5	0	0	287	0
339	2022-11-25 14:01:47.881	5-Angel Rios 	DESPACHO: 22038IC04009378F	ANULADO	9787069	0	0	0	5	0	0	288	0
340	2022-11-29 09:10:02.792	5-Angel Rios 	DESPACHO: 22038IC04009378F	EMITIDO	9927069	0	0	0	5	0	0	289	0
341	2022-11-29 09:41:38.482	5-Angel Rios 	DESPACHO: 22018EC01003699T	EMITIDO	2058525	0	0	0	5	0	0	290	0
342	2022-11-29 09:47:12.216	5-Angel Rios 	DESPACHO: 22018EM01001393S	EMITIDO	3828031	0	0	0	5	0	0	291	0
344	2022-12-01 10:02:56.373	5-Angel Rios 	DESPACHO: 22018EC01002739N	EMITIDO	5944695	0	0	0	5	0	0	293	0
345	2022-12-01 10:05:55.442	5-Angel Rios 	DESPACHO: 22005IC04018451Y	EMITIDO	9299110	0	0	0	5	0	0	294	0
346	2022-12-05 11:28:08.632	5-Angel Rios 	DESPACHO: 22005IC04025243L	ANULADO	8090206	0	0	0	5	0	0	295	0
347	2022-12-05 11:49:48.436	5-Angel Rios 	DESPACHO: 22005IC04025243L	EMITIDO	8090206	0	0	0	5	0	0	296	0
348	2022-12-09 09:18:23.476	5-Angel Rios 	DESPACHO: 22005IT02002407A	ANULADO	6075222	0	0	0	5	0	0	298	0
349	2022-12-09 09:28:20.989	5-Angel Rios 	DESPACHO: 22005IT02002407A	EMITIDO	6075222	0	0	0	5	0	0	299	0
343	2022-11-29 12:20:07.403	5-Angel Rios 	DESPACHO: 22013IC04000584L	ANULADO	22655867	0	0	0	5	0	0	292	0
351	2022-12-12 14:38:33.938	5-Angel Rios 	DESPACHO: 22013IC04000584L	ANULADO	19987014	0	0	0	5	0	0	301	0
350	2022-12-12 14:17:47.679	5-Angel Rios 	DESPACHO: 22032IC04007922P	ANULADO	15453736	0	0	0	5	0	0	300	0
352	2022-12-12 14:55:19.917	5-Angel Rios 	DESPACHO: 22032IC04007922P	EMITIDO	15834532	0	0	0	5	0	0	302	0
355	2022-12-14 14:40:13.748	5-Angel Rios 	DESPACHO: 22005IC04026060J	EMITIDO	7645886	0	0	0	5	0	0	308	0
353	2022-12-14 13:19:48.503	5-Angel Rios 	DESPACHO: 22002IM04012189A	ANULADO	594969	0	0	0	5	0	0	306	0
356	2022-12-14 14:53:56.319	5-Angel Rios 	DESPACHO: 22002IM04012189A	EMITIDO	1671888	0	0	0	5	0	0	309	0
354	2022-12-14 14:20:14.813	5-Angel Rios 	DESPACHO: 22005IC04025979E	ANULADO	29729343	0	0	0	5	0	0	307	0
357	2022-12-16 08:52:50.014	5-Angel Rios 	DESPACHO: 22005IC04025979E	EMITIDO	5607309	0	0	0	5	0	0	310	0
358	2022-12-20 09:31:05.473	5-Angel Rios 	DESPACHO: 22005IC04026605Y	ANULADO	3793411	0	0	0	5	0	0	311	0
359	2022-12-20 09:32:30.506	5-Angel Rios 	DESPACHO: 22005IC04026605Y	ANULADO	3861965	0	0	0	5	0	0	312	0
360	2022-12-20 09:51:51.056	5-Angel Rios 	DESPACHO: 22005IC04026605Y	EMITIDO	3661965	0	0	0	5	0	0	313	0
363	2022-12-20 16:22:27.26	5-Angel Rios 	DESPACHO: 22005IM04000690U	EMITIDO	1492516	0	0	0	5	0	0	316	0
364	2022-12-22 08:56:48.272	5-Angel Rios 	DESPACHO: 22005IT04026628T	ANULADO	11002391	0	0	0	5	0	0	317	0
365	2022-12-27 07:48:46.853	5-Angel Rios 	DESPACHO: 22005IT04026628T	EMITIDO	11002391	0	0	0	5	0	0	318	0
370	2023-01-04 15:54:27.928	5-Angel Rios 	DESPACHO: 23005IT02000003Y	EMITIDO	609168	0	0	0	5	0	0	323	0
368	2023-01-04 14:08:09.385	5-Angel Rios 	DESPACHO: 23005IC04000047H	ANULADO	3191657	0	0	0	5	0	0	321	0
371	2023-01-04 15:56:07.64	5-Angel Rios 	DESPACHO: 23005IC04000047H	EMITIDO	3191657	0	0	0	5	0	0	324	0
367	2023-01-04 09:40:09.549	5-Angel Rios 	DESPACHO: 22030IC04016462M	ANULADO	4864051	0	0	0	5	0	0	320	0
372	2023-01-05 14:19:22.534	5-Angel Rios 	DESPACHO: 22030IC04016462M	EMITIDO	4864051	0	0	0	5	0	0	325	0
366	2023-01-02 16:42:26.076	5-Angel Rios 	DESPACHO: 22005IC04027254P	ANULADO	4535724	0	0	0	5	0	0	319	0
373	2023-01-09 08:53:02.222	5-Angel Rios 	DESPACHO: 22005IC04027254P	EMITIDO	4535724	0	0	0	5	0	0	326	0
374	2023-01-09 09:25:27.737	5-Angel Rios 	DESPACHO: 22002IC04026356Y	EMITIDO	4484544	0	0	0	5	0	0	327	0
376	2023-01-09 11:23:26.547	5-Angel Rios 	DESPACHO: 22005IC04027294T	EMITIDO	7837236	0	0	0	5	0	0	329	0
362	2022-12-20 10:59:30.13	5-Angel Rios 	DESPACHO: 22018EM01001449U	ANULADO	2727934	0	0	0	5	0	0	315	0
377	2023-01-10 10:13:22.123	5-Angel Rios 	DESPACHO: 22018EM01001449U	EMITIDO	2727934	0	0	0	5	0	0	330	0
361	2022-12-20 10:52:28.562	5-Angel Rios 	DESPACHO: 22018EC01003938P	ANULADO	2626454	0	0	0	5	0	0	314	0
378	2023-01-10 10:14:10.914	5-Angel Rios 	DESPACHO: 22018EC01003938P	EMITIDO	2626454	0	0	0	5	0	0	331	0
379	2023-01-10 10:27:04.253	5-Angel Rios 	DESPACHO: 22005IC04027188V	ANULADO	112256036	0	0	0	5	0	0	332	0
380	2023-01-10 10:27:43.396	5-Angel Rios 	DESPACHO: 22005IC04027188V	EMITIDO	9673509	0	0	0	5	0	0	333	0
381	2023-01-10 10:53:36.205	5-Angel Rios 	DESPACHO: 22018EC01004012W	EMITIDO	3486671	0	0	0	5	0	0	334	0
375	2023-01-09 10:01:16.188	5-Angel Rios 	DESPACHO: 23005IT0402000003Y	ANULADO	3577890	0	0	0	5	0	0	328	0
382	2023-01-10 11:04:14.647	5-Angel Rios 	DESPACHO: 23005IT0402000003Y	EMITIDO	3577890	0	0	0	5	0	0	335	0
383	2023-01-18 09:09:08.325	5-Angel Rios 	DESPACHO: 22002IC04026356Y	EMITIDO	4484524	0	0	0	5	0	0	336	0
369	2023-01-04 14:43:28.437	5-Angel Rios 	DESPACHO: 23005IC04000095K	ANULADO	3584330	0	0	0	5	0	0	322	0
384	2023-01-18 09:24:44.777	5-Angel Rios 	DESPACHO: 23005IC04000095K	EMITIDO	3584330	0	0	0	5	0	0	337	0
386	2023-01-18 11:42:15.937	5-Angel Rios 	DESPACHO: 23005IC04000679S	EMITIDO	10536537	0	0	0	5	0	0	341	0
385	2023-01-18 11:26:55.909	5-Angel Rios 	DESPACHO: 23005IC04000726L	ANULADO	8240877	0	0	0	5	0	0	340	0
387	2023-01-18 11:43:43.823	5-Angel Rios 	DESPACHO: 23005IC04000726L	EMITIDO	8240877	0	0	0	5	0	0	342	0
388	2023-01-18 15:20:39.703	5-Angel Rios 	DESPACHO: 23002IC04000811D	EMITIDO	3463378	0	0	0	5	0	0	343	0
389	2023-01-18 15:54:58.107	5-Angel Rios 	DESPACHO: 23030IM04000026M	EMITIDO	4502252	0	0	0	5	0	0	344	0
390	2023-01-19 10:19:52.155	5-Angel Rios 	DESPACHO: 23015IC04000328K	EMITIDO	60312863	0	0	0	5	0	0	345	0
392	2023-01-27 08:41:25.664	5-Angel Rios 	DESPACHO: 23005IC04001270G	EMITIDO	10332875	0	0	0	5	0	0	347	0
393	2023-01-27 08:59:02.621	5-Angel Rios 	DESPACHO: 23005IC04001533X	EMITIDO	3250075	0	0	0	5	0	0	348	0
391	2023-01-26 11:08:46.435	5-Angel Rios 	DESPACHO: 23030IC04000797R	ANULADO	250000	0	0	0	5	0	0	346	0
394	2023-01-30 11:38:28.021	5-Angel Rios 	DESPACHO: 23030IC04000797R	EMITIDO	7909410	0	0	0	5	0	0	349	0
395	2023-01-30 14:11:02.19	5-Angel Rios 	DESPACHO: 23038IC04000560N	ANULADO	10212213	0	0	0	5	0	0	350	0
396	2023-01-30 14:37:18.245	5-Angel Rios 	DESPACHO: 23038IC04000560N	EMITIDO	10212213	0	0	0	5	0	0	351	0
397	2023-02-08 10:28:38.211	5-Angel Rios 	DESPACHO: 23005IC04002151F	EMITIDO	3866011	0	0	0	5	0	0	352	0
398	2023-02-08 10:48:56.231	5-Angel Rios 	DESPACHO: 23005IC04002211C	EMITIDO	4180454	0	0	0	5	0	0	353	0
399	2023-02-08 11:16:50.538	5-Angel Rios 	DESPACHO: 23005IC04002142F	ANULADO	9038600	0	0	0	5	0	0	354	0
401	2023-02-08 11:41:36.159	5-Angel Rios 	DESPACHO: 23005IC04002142F	EMITIDO	9038600	0	0	0	5	0	0	356	0
400	2023-02-08 11:40:30.063	5-Angel Rios 	DESPACHO: 23005IM04000055Z	ANULADO	1	0	0	0	5	0	0	355	0
403	2023-02-08 15:18:12.874	5-Angel Rios 	DESPACHO: 23005IC04002430F	EMITIDO	9647531	0	0	0	5	0	0	358	0
402	2023-02-08 15:05:31.21	5-Angel Rios 	DESPACHO: 23005IM04000055Z	ANULADO	1818227	0	0	0	5	0	0	357	0
404	2023-02-08 15:45:52.733	5-Angel Rios 	DESPACHO: 23005IM04000055Z	EMITIDO	1363226	0	0	0	5	0	0	359	0
405	2023-02-14 13:32:45.883	5-Angel Rios 	DESPACHO: 23030IC04001776P	ANULADO	3787160	0	0	0	5	0	0	360	0
406	2023-02-14 13:56:26.27	5-Angel Rios 	DESPACHO: 23030IC04001776P	EMITIDO	3787160	0	0	0	5	0	0	361	0
407	2023-02-16 10:40:28.164	5-Angel Rios 	DESPACHO: 23005IM04000064Z	EMITIDO	1397765	0	0	0	5	0	0	362	0
408	2023-02-16 10:58:34.275	5-Angel Rios 	DESPACHO: 23005IC04002726N	EMITIDO	13032119	0	0	0	5	0	0	363	0
409	2023-02-16 11:15:57.137	5-Angel Rios 	DESPACHO: 23005IC04002603H	EMITIDO	7712073	0	0	0	5	0	0	364	0
410	2023-02-16 11:41:17.936	5-Angel Rios 	DESPACHO: 23005IC04002879W	EMITIDO	7322775	0	0	0	5	0	0	365	0
411	2023-02-16 14:19:17.717	5-Angel Rios 	DESPACHO: 23005IT02000322S	ANULADO	7653523	0	0	0	5	0	0	366	0
412	2023-02-16 14:35:34.085	5-Angel Rios 	DESPACHO: 23005IT02000322S	EMITIDO	7653523	0	0	0	5	0	0	367	0
413	2023-02-17 09:57:09.172	5-Angel Rios 	GASTO: 001-002-0026662\t750.000 FLETE\n001-001-0053336\t65.000 TORNILLO Y ANGULO P MESA\n001-001-0053337\t18.000 DESTORNILLADOR P MAQUINA\n002-001-0002812\t45.600 BOLSO DE CINTURA\n007-002-0269453\t22.000 ENCOMIENDA\n007-002-0269825\t22.000 ENCOMIENDA\n003-009-0032498\t40.078 COMBUS\n001-009-02577403\t129.000 INTERNET \n001-001-0014670\t340.000 004 SEG GOL \n001-001-0014670\t370.000 005 SEG GOL \n0003462725\t\t1.300.000 SILLA AUGUSTO\nS/C\t\t6.552.000 ALQUILER \nSALARIO\t\t6.000.000 AUGUSTO \nSALARIO\t\t7.939.567 MATIA FACT \n	EMITIDO	0	0	23593245	0	5	0	53	0	0
415	2023-02-17 10:01:06.754	5-Angel Rios 	GASTO: 004-003-0009688\t148.000 ART D LIMPIEZA\n00000632\t\t450.000 CARTA PRESENTACION INGLES ROYAL\nVALE\t\t100.000 COMBUSTIBLE YOYI \nVALE\t\t75.000 PARA PLANTAS\nRECIBO \t\t660.000 RICHARD OJEDA GESTIONES MAQUILA 	EMITIDO	0	0	1433000	0	5	0	55	0	0
414	2023-02-17 10:00:36.178	5-Angel Rios 	GASTO: 004-003-0009688\t148.000 ART D LIMPIEZA\n00000632\t\t450.000 CARTA PRESENTACION INGLES ROYAL\nVALE\t\t100.000 COMBUSTIBLE YOYI \nVALE\t\t75.000 PARA PLANTAS\nRECIBO \t\t660.000 RICHARD OJEDA GESTIONES MAQUILA 	ANULADO	0	0	1433000	0	5	0	54	0	0
\.


--
-- TOC entry 3598 (class 0 OID 24588)
-- Dependencies: 216
-- Data for Name: comprobante_liquidacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comprobante_liquidacion (idcomprobante_liquidacion, descripcion, por_iva, tipo_iva, nro_despacho, eliminado) FROM stdin;
1	SIN DEFINIR	10	SIN_IVA	t	f
2	DERECHO ADUANERO	10	SIN_IVA	t	f
3	INDI	10	SIN_IVA	t	f
4	I.S.C.	10	SIN_IVA	t	f
5	SERVICIO DE VALORACION	10	SIN_IVA	t	f
6	MULTAS VARIAS	10	SIN_IVA	t	f
7	IRACIS GENERAL 700	10	SIN_IVA	t	f
8	I.V.A.	10	SOLO_IVA	t	f
9	CANON INFORMATICO	10	SIN_IVA	f	f
10	CDAP/ANNP	10	SIN_Y_SOLO_IVA	f	f
11	SERVICIOS SOFIA / ANNP	10	SIN_Y_SOLO_IVA	f	f
12	FOTOCOPIAS	10	SIN_Y_SOLO_IVA	f	f
13	APERTURA AG. TRANSP.	10	SIN_Y_SOLO_IVA	f	f
14	TASA PORTUARIA	10	SIN_Y_SOLO_IVA	f	f
16	HON. DESP. S/ LEY 220/93	10	SIN_Y_SOLO_IVA	f	f
18	TASA MIC LPI CONFECCIONES	10	SIN_IVA	f	f
19	AGILIZACION MIC	10	SIN_IVA	f	f
21	REPOSICION DE GASTOS ADM	10	SIN_IVA	f	f
20	OTROS	10	SIN_IVA	f	f
22	MIPYMES MIC	10	SIN_Y_SOLO_IVA	f	f
23	APORTE VUE	10	SIN_Y_SOLO_IVA	f	f
24	TASA INTER. ADUAN.	10	SIN_IVA	f	f
25	IVA TURISMO	10	SIN_Y_SOLO_IVA	f	f
26	I.V.A. TURISMO 	10	SOLO_IVA	t	f
27	CDAP PAGOS	10	SIN_Y_SOLO_IVA	f	f
28	VISACION MRE	10	SIN_IVA	f	f
15	VISACION CONSUL	10	SIN_IVA	f	f
31	CDAP DINAC	10	SIN_Y_SOLO_IVA	f	f
30	TASA UIP CO	10	SIN_Y_SOLO_IVA	f	f
29	TASA MIC CO	10	SIN_Y_SOLO_IVA	f	f
32	LICENCIA PREV MIC 	10	SIN_IVA	f	f
33	ARANC CONS FACTURA 	10	SIN_IVA	t	f
34	MOPC	10	SIN_IVA	f	f
35	TASA INT. ADUANERA 	10	SIN_IVA	t	f
36	TASA INT. ANNP	10	SIN_IVA	f	f
38	IRE GENERAL 700	10	SIN_IVA	t	f
39	FLETE INTERNO 	10	SIN_Y_SOLO_IVA	f	f
40	GASTOS BANCARIOS	10	SIN_IVA	f	f
41	REGISTRO DE FIRMA 22-23	10	SIN_IVA	f	f
42	MUESTRAS AEROP. 1 	10	SIN_IVA	f	f
43	RETIRO DE GUÍA DHL	10	SIN_Y_SOLO_IVA	f	f
44	TASA MIC LPI LAMPARAS	10	SIN_IVA	f	f
45	DECRETO ANNP	10	SIN_IVA	f	f
37	TASA DINAVISA	10	SIN_Y_SOLO_IVA	f	f
46	TASA MADES	10	SIN_IVA	f	f
47	INS. IMP. SIDERURGICOS	10	SIN_IVA	f	f
48	TASA IMP. MATERIA PR	10	SIN_IVA	f	f
49	TASA INTN	10	SIN_Y_SOLO_IVA	f	f
50	ROYAL SEGUROS	10	SIN_Y_SOLO_IVA	f	f
17	GASTO ADMIN.	10	SIN_IVA	f	f
51	ARANC. CONS. FACTURA 	10	SIN_IVA	t	f
\.


--
-- TOC entry 3599 (class 0 OID 24594)
-- Dependencies: 217
-- Data for Name: credito_tercero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credito_tercero (idcredito_tercero, fecha_emision, descripcion, estado, monto_contado, monto_credito, tabla_origen, vence, fecha_vence, fk_idgrupo_credito_tercero, fk_idsaldo_credito_tercero, fk_idrecibo_pago_tercero, fk_idliquidacion_final) FROM stdin;
1	2022-01-18 14:18:24.456	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	1	1	0	0
2	2022-01-18 14:18:27.549	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	2	2	0	0
3	2022-01-18 14:18:30.507	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	3	3	0	0
4	2022-01-18 14:18:33.262	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	4	4	0	0
5	2022-01-18 14:18:35.937	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	5	5	0	0
6	2022-01-18 14:18:39.158	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	6	6	0	0
7	2022-01-18 14:18:42.442	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	7	7	0	0
8	2022-01-18 14:18:45.124	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	8	8	0	0
9	2022-01-18 14:18:48.021	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	9	9	0	0
10	2022-01-18 14:18:50.757	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	10	10	0	0
11	2022-01-18 14:18:53.413	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	11	11	0	0
12	2022-01-18 14:18:56.081	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	12	12	0	0
13	2022-01-18 14:18:59.363	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:18:00	13	13	0	0
14	2022-01-18 14:19:02.655	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	14	14	0	0
15	2022-01-18 14:19:05.625	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	15	15	0	0
16	2022-01-18 14:19:08.314	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	16	16	0	0
17	2022-01-18 14:19:12.096	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	17	17	0	0
18	2022-01-18 14:19:15.418	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	18	18	0	0
19	2022-01-18 14:19:18.231	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	19	19	0	0
20	2022-01-18 14:19:21.553	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-01-18 14:19:00	20	20	0	0
21	2022-01-18 16:52:17.317	Nro: 21002IC04027163K	EMITIDO	0	464300	LIQUIDACION	t	2022-01-18 16:52:00	2	0	0	1
22	2022-02-04 16:33:40.119	Nro: 32423sdfs	EMITIDO	0	500000	LIQUIDACION	t	2022-02-04 16:33:00	2	0	0	2
24	2022-02-17 13:28:02.228	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-02-17 13:28:00	21	21	0	0
26	2022-02-17 13:41:59.228	DESPACHO ADUANERO PARA: DIGNO ALFREDO TALAVERA ROJAS	EMITIDO	900000	0	RECIBO	f	2022-02-17 13:41:00	2	0	1	0
27	2022-02-17 13:41:59.304	SALDO DEL CIERRE ANTERIOR	EMITIDO	0	64310	RECIBO	f	2022-02-17 13:41:00	22	22	0	0
25	2022-02-17 13:37:24.126	Nro: 22005IC04000981N	ANULADO	0	91287196	LIQUIDACION	t	2022-02-17 13:37:00	3	0	0	4
28	2022-04-27 12:42:14.012	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-27 12:42:00	23	23	0	0
29	2022-04-27 12:49:49.747	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-27 12:49:00	24	24	0	0
30	2022-04-27 13:57:32.081	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-27 13:57:00	25	25	0	0
31	2022-04-27 14:14:21.195	Nro: 22005IC04008320X	EMITIDO	0	78656671	LIQUIDACIÓN	t	2022-04-27 14:14:00	3	0	0	5
23	2022-02-17 12:03:20.173	Nro: 6467456	ANULADO	0	10	LIQUIDACION	t	2022-02-17 12:03:00	2	0	0	3
32	2022-04-27 15:11:59.714	Nro: 22005IM04000192R	EMITIDO	0	4474051	LIQUIDACIÓN	t	2022-04-27 15:11:00	3	0	0	6
33	2022-04-27 15:19:00.853	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-27 15:18:00	26	26	0	0
34	2022-04-27 15:20:01.636	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-27 15:20:00	27	27	0	0
35	2022-04-27 15:41:48.789	Nro: 22005IC04008254Y	ANULADO	0	18880559	LIQUIDACIÓN	t	2022-04-27 15:41:00	8	0	0	7
37	2022-04-28 13:49:27.132	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-04-28 13:49:00	28	28	0	0
38	2022-04-28 14:09:39.009	Nro: 22005IC04008374R	ANULADO	0	2968111	LIQUIDACIÓN	t	2022-04-28 14:09:00	8	0	0	9
39	2022-04-28 14:10:36.669	Nro: 22005IC04008374R	EMITIDO	0	2968111	LIQUIDACIÓN	t	2022-04-28 14:10:00	8	0	0	10
36	2022-04-27 15:43:48.041	Nro: 22005IC04008254Y	ANULADO	0	4218371	LIQUIDACIÓN	t	2022-04-27 15:43:00	8	0	0	8
40	2022-04-28 14:14:45.768	Nro: 22005IC04008254Y	EMITIDO	0	4218371	LIQUIDACIÓN	t	2022-04-28 14:14:00	8	0	0	11
41	2022-05-03 15:33:54.575	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-03 15:33:00	29	29	0	0
42	2022-05-03 16:00:55.675	Nro: 22009IC04000587T	ANULADO	0	21066909	LIQUIDACIÓN	t	2022-05-03 16:00:00	8	0	0	12
43	2022-05-03 16:05:24.614	Nro: 22009IC04000587T	ANULADO	0	8769670	LIQUIDACIÓN	t	2022-05-03 16:05:00	8	0	0	13
44	2022-05-03 16:08:34.16	Nro: 22009IC04000587T	EMITIDO	0	8769670	LIQUIDACIÓN	t	2022-05-03 16:08:00	8	0	0	14
45	2022-05-04 12:37:18.974	Nro: 22005IC04008737U	EMITIDO	0	82375784	LIQUIDACIÓN	t	2022-05-04 12:37:00	3	0	0	15
46	2022-05-04 12:45:08.859	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-04 12:45:00	30	30	0	0
47	2022-05-04 12:59:56.579	Nro: 22005IC04008739W	EMITIDO	0	41390090	LIQUIDACIÓN	t	2022-05-04 12:59:00	3	0	0	16
48	2022-05-06 11:38:41.641	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 11:38:00	31	31	0	0
49	2022-05-06 11:47:18.299	Nro: 22030IC04005329M	EMITIDO	0	3634143	LIQUIDACIÓN	t	2022-05-06 11:47:00	23	0	0	17
50	2022-05-06 12:14:08.113	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 12:14:00	32	32	0	0
51	2022-05-06 12:14:45.945	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 12:14:00	33	33	0	0
52	2022-05-06 13:40:40.987	Nro: 22002IC04008988C	ANULADO	0	26259209	LIQUIDACIÓN	t	2022-05-06 13:40:00	3	0	0	18
53	2022-05-06 13:47:59.606	Nro: 22002IC04008988C	ANULADO	0	26259209	LIQUIDACIÓN	t	2022-05-06 13:47:00	3	0	0	19
54	2022-05-06 13:50:02.772	Nro: 22002IC04008988C	ANULADO	0	26979209	LIQUIDACIÓN	t	2022-05-06 13:50:00	3	0	0	20
56	2022-05-06 13:59:34.356	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 13:59:00	34	34	0	0
57	2022-05-06 14:00:05.949	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 14:00:00	35	35	0	0
58	2022-05-06 14:01:02.779	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-06 14:01:00	36	36	0	0
59	2022-05-06 14:17:52.639	Nro: 22005IC04008880T	EMITIDO	0	25233643	LIQUIDACIÓN	t	2022-05-06 14:17:00	35	0	0	22
60	2022-05-10 15:31:57.62	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-10 15:31:00	37	37	0	0
61	2022-05-10 15:42:14.637	Nro: 22023EC01001717E	EMITIDO	0	3257319	LIQUIDACIÓN	t	2022-05-10 15:42:00	15	0	0	23
62	2022-05-12 14:35:15.942	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-12 14:35:00	38	38	0	0
63	2022-05-12 14:39:43.869	Nro: 22023EC01001719G	EMITIDO	0	3606748	LIQUIDACIÓN	t	2022-05-12 14:39:00	15	0	0	24
64	2022-05-13 16:37:44.099	Nro: 22005IC04009558W	EMITIDO	0	31522584	LIQUIDACIÓN	t	2022-05-13 16:37:00	3	0	0	25
65	2022-05-17 11:56:27.841	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-17 11:56:00	39	39	0	0
66	2022-05-17 12:09:16.585	Nro: 22005IC04009800M	ANULADO	0	3425068	LIQUIDACIÓN	t	2022-05-17 12:09:00	8	0	0	26
67	2022-05-17 12:14:51.701	Nro: 22005IC04009800M	ANULADO	0	12158822	LIQUIDACIÓN	t	2022-05-17 12:14:00	8	0	0	26
68	2022-05-17 12:51:36.346	Nro: 22005IC04000981N	ANULADO	0	91287196	LIQUIDACIÓN	t	2022-05-17 12:51:00	3	0	0	26
69	2022-05-17 12:53:06.813	Nro: 6467456	ANULADO	0	10	LIQUIDACIÓN	t	2022-05-17 12:53:00	22	0	0	26
70	2022-05-17 13:34:35.931	Nro: 6467456	ANULADO	0	115604850	LIQUIDACIÓN	t	2022-05-17 13:34:00	22	0	0	27
71	2022-05-17 13:37:22.125	Nro: ytryrtyru	ANULADO	0	50	LIQUIDACIÓN	t	2022-05-17 13:37:00	21	0	0	28
72	2022-05-17 13:48:28.133	Nro: 22005IC04009800M	EMITIDO	0	3425068	LIQUIDACIÓN	t	2022-05-17 13:48:00	8	0	0	29
73	2022-05-18 09:16:17.152	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-18 09:16:00	40	40	0	0
74	2022-05-18 09:40:39.185	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-18 09:40:00	41	41	0	0
75	2022-05-18 09:42:34.665	Nro: 22029IC04001134K	ANULADO	0	5210830	LIQUIDACIÓN	t	2022-05-18 09:42:00	40	0	0	30
76	2022-05-18 11:08:03.254	Nro: 22029IC04001134K	ANULADO	0	8355637	LIQUIDACIÓN	t	2022-05-18 11:08:00	40	0	0	31
77	2022-05-18 11:19:45.348	Nro: 22029IC04001134K	EMITIDO	0	8355637	LIQUIDACIÓN	t	2022-05-18 11:19:00	40	0	0	32
78	2022-05-24 09:38:20.429	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-24 09:38:00	42	42	0	0
79	2022-05-24 09:46:24.746	Nro: 22005IC04010324F	EMITIDO	0	71196509	LIQUIDACIÓN	t	2022-05-24 09:46:00	3	0	0	33
80	2022-05-24 09:57:11.388	Nro: 22005IC04010326H	EMITIDO	0	69005861	LIQUIDACIÓN	t	2022-05-24 09:57:00	3	0	0	34
81	2022-05-24 11:33:54.042	Nro: 22005IM04000231L	EMITIDO	0	2775452	LIQUIDACIÓN	t	2022-05-24 11:33:00	3	0	0	35
82	2022-05-24 11:51:22.631	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-24 11:51:00	43	43	0	0
83	2022-05-24 12:06:26.014	Nro: 22005IC04010303C	ANULADO	0	15350999	LIQUIDACIÓN	t	2022-05-24 12:06:00	15	0	0	36
84	2022-05-24 12:09:33.018	Nro: 22005IC04010303C	EMITIDO	0	15350999	LIQUIDACIÓN	t	2022-05-24 12:09:00	15	0	0	37
85	2022-05-26 10:53:05.468	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-26 10:53:00	44	44	0	0
87	2022-05-26 11:31:43.584	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-26 11:31:00	45	45	0	0
88	2022-05-26 12:03:23.598	Nro: 22005IC04010686Z	EMITIDO	0	5419760	LIQUIDACIÓN	t	2022-05-26 12:03:00	15	0	0	39
89	2022-05-26 12:17:20.387	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-26 12:17:00	46	46	0	0
90	2022-05-26 12:17:59.253	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-26 12:17:00	47	47	0	0
91	2022-05-26 13:42:51.632	Nro: 22023EC01002080V	EMITIDO	0	3559798	LIQUIDACIÓN	t	2022-05-26 13:42:00	47	0	0	40
92	2022-05-30 10:32:42.849	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-30 10:32:00	48	48	0	0
93	2022-05-30 15:11:44.788	Nro: 22038IC04005438V	ANULADO	0	5544846	LIQUIDACIÓN	t	2022-05-30 15:11:00	23	0	0	41
94	2022-05-30 15:14:35.636	Nro: 22038IC04005438V	EMITIDO	0	5534846	LIQUIDACIÓN	t	2022-05-30 15:14:00	23	0	0	42
86	2022-05-26 11:08:33.579	Nro: 22005IC04010454J	ANULADO	0	23206735	LIQUIDACIÓN	t	2022-05-26 11:08:00	3	0	0	38
95	2022-05-31 09:30:52.867	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-31 09:30:00	49	49	0	0
96	2022-05-31 09:57:57.895	Nro: 22005IC04010983Z	EMITIDO	0	44967611	LIQUIDACIÓN	t	2022-05-31 09:57:00	3	0	0	43
97	2022-05-31 10:21:46.435	Nro: 22005IC04011037H	ANULADO	0	5273189	LIQUIDACIÓN	t	2022-05-31 10:21:00	8	0	0	44
98	2022-05-31 10:24:19.994	Nro: 22005IC04011037H	EMITIDO	0	5273189	LIQUIDACIÓN	t	2022-05-31 10:24:00	8	0	0	45
99	2022-05-31 10:32:55.239	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-05-31 10:32:00	50	50	0	0
100	2022-05-31 10:54:14.18	Nro: 22005IC04011020W	EMITIDO	0	3169620	LIQUIDACIÓN	t	2022-05-31 10:54:00	15	0	0	46
101	2022-06-02 14:22:43.578	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-02 14:22:00	51	51	0	0
102	2022-06-02 14:24:23.09	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-02 14:24:00	52	52	0	0
103	2022-06-02 14:40:56.136	Nro: 22002IM04005115Y	ANULADO	0	2451150	LIQUIDACIÓN	t	2022-06-02 14:40:00	51	0	0	47
104	2022-06-02 14:51:36.519	Nro: 22002IM04005115Y	EMITIDO	0	3405510	LIQUIDACIÓN	t	2022-06-02 14:51:00	51	0	0	48
105	2022-06-02 16:31:37.073	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-02 16:31:00	53	53	0	0
106	2022-06-02 16:47:01.2	Nro: 22038IC04005516S	ANULADO	0	8048261	LIQUIDACIÓN	t	2022-06-02 16:46:00	23	0	0	49
107	2022-06-03 12:18:34.579	Nro: 22038IC04005516S	EMITIDO	0	8048261	LIQUIDACIÓN	t	2022-06-03 12:18:00	23	0	0	50
108	2022-06-03 14:58:46.242	Nro: 22005IC04011310B	EMITIDO	0	48787259	LIQUIDACIÓN	t	2022-06-03 14:58:00	3	0	0	51
109	2022-06-03 15:23:24.325	Nro: 22038IC04005516S	EMITIDO	0	12048261	LIQUIDACIÓN	t	2022-06-03 15:23:00	23	0	0	52
110	2022-06-06 08:47:56.729	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-06 08:47:00	54	54	0	0
111	2022-06-06 09:41:45.618	Nro: 22005IC04011379Z	EMITIDO	0	19673967	LIQUIDACIÓN	t	2022-06-06 09:41:00	15	0	0	53
112	2022-06-06 10:14:36.383	Nro: 22005IM04000258U	EMITIDO	0	2966759	LIQUIDACIÓN	t	2022-06-06 10:14:00	3	0	0	54
113	2022-06-06 16:02:59.769	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-06 16:02:00	55	55	0	0
114	2022-06-06 16:12:55.35	Nro: 22038IC04005564V	ANULADO	0	8018318	LIQUIDACIÓN	t	2022-06-06 16:12:00	23	0	0	55
115	2022-06-06 16:23:07.236	Nro: 22038IC04005564V	EMITIDO	0	8018318	LIQUIDACIÓN	t	2022-06-06 16:23:00	23	0	0	56
116	2022-06-07 11:30:25.456	Nro: 22005IC04011473L	ANULADO	0	11351027	LIQUIDACIÓN	t	2022-06-07 11:30:00	15	0	0	57
117	2022-06-07 14:43:00.564	Nro: 22005IC04011473L	EMITIDO	0	11351027	LIQUIDACIÓN	t	2022-06-07 14:42:00	15	0	0	58
118	2022-06-08 14:25:43.563	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-08 14:25:00	56	56	0	0
119	2022-06-08 14:51:50.674	Nro: 22005IC04011603G	ANULADO	0	7593701	LIQUIDACIÓN	t	2022-06-08 14:51:00	3	0	0	59
120	2022-06-08 14:52:47.111	Nro: 22005IC04011603G	ANULADO	0	7428701	LIQUIDACIÓN	t	2022-06-08 14:52:00	3	0	0	60
121	2022-06-08 15:14:21.185	Nro: 22005IC04011603G	ANULADO	0	7318701	LIQUIDACIÓN	t	2022-06-08 15:14:00	3	0	0	61
122	2022-06-08 15:26:57.237	Nro: 22005IC04011603G	EMITIDO	0	7318701	LIQUIDACIÓN	t	2022-06-08 15:26:00	3	0	0	62
123	2022-06-08 15:59:50.719	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-08 15:59:00	57	57	0	0
124	2022-06-08 16:00:25.508	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-08 16:00:00	58	58	0	0
126	2022-06-08 16:48:47.271	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-08 16:48:00	59	59	0	0
125	2022-06-08 16:16:18.639	Nro: 22005IC04011601E	ANULADO	0	7841217	LIQUIDACIÓN	t	2022-06-08 16:16:00	57	0	0	63
127	2022-06-08 17:15:37.961	Nro: 22005IC04011601E	ANULADO	0	7485659	LIQUIDACIÓN	t	2022-06-08 17:15:00	57	0	0	66
128	2022-06-08 17:23:51.333	Nro: 22005IC04011601E	ANULADO	0	7485659	LIQUIDACIÓN	t	2022-06-08 17:23:00	57	0	0	67
129	2022-06-10 08:53:16.771	Nro: 22005IC04011601E	EMITIDO	0	8112016	LIQUIDACIÓN	t	2022-06-10 08:53:00	57	0	0	68
130	2022-06-10 09:19:58.172	Nro: 22029IC04000273@	ANULADO	0	20272686	LIQUIDACIÓN	t	2022-06-10 09:19:00	51	0	0	70
55	2022-05-06 13:53:06.868	Nro: 22002IC04008988C	ANULADO	0	26979209	LIQUIDACIÓN	t	2022-05-06 13:53:00	3	0	0	21
131	2022-06-14 11:46:57.97	Nro: 22002IC04008988C	EMITIDO	0	29314336	LIQUIDACIÓN	t	2022-06-14 11:46:00	3	0	0	74
132	2022-06-14 13:01:59.769	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-14 13:01:00	60	60	0	0
133	2022-06-14 13:22:29.779	Nro: 22005IC04011785R	EMITIDO	0	18178700	LIQUIDACIÓN	t	2022-06-14 13:22:00	35	0	0	75
134	2022-06-14 13:48:15.636	Nro: 22005IC04011790N	EMITIDO	0	37396589	LIQUIDACIÓN	t	2022-06-14 13:48:00	3	0	0	76
135	2022-06-15 13:35:54.342	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-15 13:35:00	61	61	0	0
137	2022-06-17 09:19:44.857	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-17 09:19:00	62	62	0	0
138	2022-06-17 09:20:31.219	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-17 09:20:00	63	63	0	0
139	2022-06-17 09:34:20.977	Nro: 22023EC01002423W	ANULADO	0	3169021	LIQUIDACIÓN	t	2022-06-17 09:34:00	63	0	0	81
136	2022-06-17 09:13:11.732	Nro: 22023EC01002422V	ANULADO	0	3432977	LIQUIDACIÓN	t	2022-06-17 09:13:00	47	0	0	80
140	2022-06-17 10:03:37.43	Nro: 22005IC04012075K	ANULADO	0	50664265	LIQUIDACIÓN	t	2022-06-17 10:03:00	15	0	0	82
141	2022-06-17 10:06:24.38	Nro: 22023EC01002423W	ANULADO	0	3169021	LIQUIDACIÓN	t	2022-06-17 10:06:00	63	0	0	83
144	2022-06-17 11:17:57.458	Nro: 22023EC01002423W	EMITIDO	0	3169021	LIQUIDACIÓN	t	2022-06-17 11:17:00	63	0	0	86
142	2022-06-17 10:11:05.342	Nro: 22023EC01002422V	ANULADO	0	3432977	LIQUIDACIÓN	t	2022-06-17 10:11:00	47	0	0	84
143	2022-06-17 10:31:37.339	Nro: 22005IC04012075K	ANULADO	0	11285801	LIQUIDACIÓN	t	2022-06-17 10:31:00	15	0	0	85
145	2022-06-17 11:20:02.794	Nro: 22023EC01002422V	EMITIDO	0	3432977	LIQUIDACIÓN	t	2022-06-17 11:20:00	47	0	0	87
146	2022-06-17 11:23:25.467	Nro: 22005IC04012075K	EMITIDO	0	11285801	LIQUIDACIÓN	t	2022-06-17 11:23:00	15	0	0	88
147	2022-06-20 09:45:22.837	Nro: 22005IC04012187Y	EMITIDO	0	102719449	LIQUIDACIÓN	t	2022-06-20 09:45:00	3	0	0	89
148	2022-06-20 14:10:09.534	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-20 14:10:00	64	64	0	0
149	2022-06-20 14:30:49.451	Nro: 22024IC04003398T	EMITIDO	0	11155509	LIQUIDACIÓN	t	2022-06-20 14:30:00	23	0	0	90
150	2022-06-21 11:12:24.326	Nro: 22005IC04012075K	EMITIDO	0	11285801	LIQUIDACIÓN	t	2022-06-21 11:12:00	15	0	0	91
152	2022-06-29 09:37:38.794	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-29 09:37:00	65	65	0	0
153	2022-06-29 10:36:48.475	Nro: 22005IC04012780N	ANULADO	0	13325442	LIQUIDACIÓN	t	2022-06-29 10:36:00	15	0	0	93
156	2022-06-29 12:59:54.725	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-06-29 12:59:00	66	66	0	0
154	2022-06-29 11:20:26.298	Nro: 22005IC04012849T	ANULADO	0	15510652	LIQUIDACIÓN	t	2022-06-29 11:20:00	15	0	0	94
158	2022-06-29 13:22:52.286	Nro: 22005IC04012849T	EMITIDO	0	15510652	LIQUIDACIÓN	t	2022-06-29 13:22:00	15	0	0	97
155	2022-06-29 11:43:16.909	Nro: 22005IC04012780N	ANULADO	0	12225442	LIQUIDACIÓN	t	2022-06-29 11:43:00	15	0	0	95
159	2022-06-29 13:26:43.253	Nro: 22005IC04012780N	EMITIDO	0	12225442	LIQUIDACIÓN	t	2022-06-29 13:26:00	15	0	0	98
151	2022-06-29 09:19:11.3	Nro: 22005IC04012892R	ANULADO	0	6069360	LIQUIDACIÓN	t	2022-06-29 09:19:00	15	0	0	92
157	2022-06-29 13:19:55.098	Nro: 22031IC04004017G	ANULADO	0	7580637	LIQUIDACIÓN	t	2022-06-29 13:19:00	51	0	0	96
161	2022-06-29 13:41:43.348	Nro: 22031IC04004017G	EMITIDO	0	6358255	LIQUIDACIÓN	t	2022-06-29 13:41:00	51	0	0	100
160	2022-06-29 13:27:46.991	Nro: 22005IC04012892R	ANULADO	0	6069360	LIQUIDACIÓN	t	2022-06-29 13:27:00	15	0	0	99
163	2022-07-04 14:00:11.815	Nro: 22005IC04012892R	EMITIDO	0	6069360	LIQUIDACIÓN	t	2022-07-04 14:00:00	15	0	0	102
164	2022-07-04 14:14:56.297	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-04 14:14:00	67	67	0	0
165	2022-07-04 14:40:01.877	Nro: 22005IC04013105F	EMITIDO	0	11076041	LIQUIDACIÓN	t	2022-07-04 14:40:00	15	0	0	103
166	2022-07-04 14:54:04.55	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-04 14:54:00	68	68	0	0
167	2022-07-04 15:14:56.4	Nro: 22002IC04013323E	ANULADO	0	21643699	LIQUIDACIÓN	t	2022-07-04 15:14:00	15	0	0	104
168	2022-07-04 15:15:46.062	Nro: 22002IC04013323E	EMITIDO	0	6914095	LIQUIDACIÓN	t	2022-07-04 15:15:00	15	0	0	105
162	2022-06-30 14:13:38.525	Nro: 22018EC01001795Y	ANULADO	0	3649949	LIQUIDACIÓN	t	2022-06-30 14:13:00	47	0	0	101
169	2022-07-04 15:21:07.586	Nro: 22018EC01001795Y	ANULADO	0	3649949	LIQUIDACIÓN	t	2022-07-04 15:21:00	47	0	0	106
170	2022-07-04 15:23:16.871	Nro: 22018EC01001795Y	EMITIDO	0	3649949	LIQUIDACIÓN	t	2022-07-04 15:23:00	47	0	0	107
171	2022-07-05 13:59:06.331	Nro: 22018EC01001851H	EMITIDO	0	3600911	LIQUIDACIÓN	t	2022-07-05 13:59:00	63	0	0	108
172	2022-07-05 14:45:10.825	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-05 14:45:00	69	69	0	0
173	2022-07-05 14:45:44.725	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-05 14:45:00	70	70	0	0
174	2022-07-05 15:31:50.252	Nro: 22005IC04013396R	ANULADO	0	127092489	LIQUIDACIÓN	t	2022-07-05 15:31:00	69	0	0	109
175	2022-07-05 16:25:06.744	Nro: 22005IC04013396R	EMITIDO	0	14260669	LIQUIDACIÓN	t	2022-07-05 16:25:00	69	0	0	110
176	2022-07-06 12:35:32.13	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-06 12:35:00	71	71	0	0
178	2022-07-06 13:32:41.567	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-06 13:32:00	72	72	0	0
179	2022-07-06 13:49:54.982	Nro: 22005IC04013407K	ANULADO	0	13931647	LIQUIDACIÓN	t	2022-07-06 13:49:00	15	0	0	112
181	2022-07-06 14:17:24.192	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-06 14:17:00	73	73	0	0
182	2022-07-06 14:47:22.348	Nro: 22005IC04013496S	EMITIDO	0	9343935	LIQUIDACIÓN	t	2022-07-06 14:47:00	15	0	0	114
180	2022-07-06 14:14:21.69	Nro: 22005IC04013407K	ANULADO	0	5314992	LIQUIDACIÓN	t	2022-07-06 14:14:00	15	0	0	113
183	2022-07-06 14:48:43.255	Nro: 22005IC04013407K	ANULADO	0	5314992	LIQUIDACIÓN	t	2022-07-06 14:48:00	15	0	0	115
177	2022-07-06 13:26:53.188	Nro: 22005IC04013270X	ANULADO	0	16136806	LIQUIDACIÓN	t	2022-07-06 13:26:00	15	0	0	111
185	2022-07-06 14:50:49.195	Nro: 22005IC04013270X	EMITIDO	0	16136806	LIQUIDACIÓN	t	2022-07-06 14:50:00	15	0	0	117
184	2022-07-06 14:49:35.234	Nro: 22005IC04013407K	ANULADO	0	5314992	LIQUIDACIÓN	t	2022-07-06 14:49:00	15	0	0	116
186	2022-07-06 14:52:55.571	Nro: 22005IC04013407K	EMITIDO	0	5314992	LIQUIDACIÓN	t	2022-07-06 14:52:00	15	0	0	118
187	2022-07-06 15:26:51.895	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-06 15:26:00	74	74	0	0
188	2022-07-06 15:54:57.849	Nro: 22002IM04006471U	ANULADO	0	3020299	LIQUIDACIÓN	t	2022-07-06 15:54:00	51	0	0	119
189	2022-07-06 16:02:26.878	Nro: 22002IM04006471U	EMITIDO	0	3020299	LIQUIDACIÓN	t	2022-07-06 16:02:00	51	0	0	120
190	2022-07-08 11:44:10.609	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-08 11:44:00	75	75	0	0
191	2022-07-08 12:53:02.391	Nro: 22032IC04004226J	ANULADO	0	13987597	LIQUIDACIÓN	t	2022-07-08 12:53:00	12	0	0	121
192	2022-07-08 12:59:38.769	Nro: 22032IC04004226J	EMITIDO	0	13987597	LIQUIDACIÓN	t	2022-07-08 12:59:00	12	0	0	122
193	2022-07-08 15:27:23.382	Nro: 22005IC04013676S	EMITIDO	0	89124914	LIQUIDACIÓN	t	2022-07-08 15:27:00	3	0	0	123
194	2022-07-08 15:54:41.875	Nro: 22005IM04000310J	EMITIDO	0	4920585	LIQUIDACIÓN	t	2022-07-08 15:54:00	3	0	0	124
195	2022-07-08 15:56:15.755	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-08 15:56:00	76	76	0	0
196	2022-07-08 16:06:52.629	Nro: 22005IC04013669U	EMITIDO	0	10013534	LIQUIDACIÓN	t	2022-07-08 16:06:00	3	0	0	125
197	2022-07-12 10:07:31.083	Nro: 22005IC04013794T	ANULADO	0	6512318	LIQUIDACIÓN	t	2022-07-12 10:07:00	15	0	0	126
198	2022-07-12 13:11:58.322	Nro: 22005IC04013794T	EMITIDO	0	6512318	LIQUIDACIÓN	t	2022-07-12 13:11:00	15	0	0	127
199	2022-07-13 10:14:53.771	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-13 10:14:00	77	77	0	0
200	2022-07-13 10:22:25.62	Nro: 22005IC04013997B	EMITIDO	0	9379939	LIQUIDACIÓN	t	2022-07-13 10:22:00	3	0	0	128
201	2022-07-13 14:48:31.377	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-13 14:48:00	78	78	0	0
202	2022-07-14 12:08:01.563	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-14 12:08:00	79	79	0	0
203	2022-07-14 12:40:47.376	Nro: 22009IC04000909R	ANULADO	0	4592576	LIQUIDACIÓN	t	2022-07-14 12:40:00	8	0	0	130
205	2022-07-14 14:14:32.264	Nro: 22005IC04014217K	EMITIDO	0	9361922	LIQUIDACIÓN	t	2022-07-14 14:14:00	3	0	0	132
206	2022-07-18 16:12:42.35	Nro: 22017IC04003694U	ANULADO	0	114900930	LIQUIDACIÓN	t	2022-07-18 16:12:00	20	0	0	133
207	2022-07-18 16:16:34.305	Nro: 22017IC04003694U	ANULADO	0	122202651	LIQUIDACIÓN	t	2022-07-18 16:16:00	20	0	0	134
208	2022-07-18 16:18:54.894	Nro: 22017IC04003694U	ANULADO	0	122202651	LIQUIDACIÓN	t	2022-07-18 16:18:00	20	0	0	135
210	2022-07-19 08:59:43.004	Nro: 22005IC04014373N	EMITIDO	0	4712468	LIQUIDACIÓN	t	2022-07-19 08:59:00	15	0	0	137
209	2022-07-18 16:28:10.304	Nro: 22017IC04003694U	ANULADO	0	122702651	LIQUIDACIÓN	t	2022-07-18 16:28:00	20	0	0	136
211	2022-07-19 09:23:01.043	Nro: 22005IC04014423J	ANULADO	0	15723426	LIQUIDACIÓN	t	2022-07-19 09:23:00	15	0	0	138
213	2022-07-19 09:55:11.578	Nro: 22005IC04014423J	EMITIDO	0	15723426	LIQUIDACIÓN	t	2022-07-19 09:55:00	15	0	0	140
212	2022-07-19 09:53:57.136	Nro: 22017IC04003694U	ANULADO	0	122035395	LIQUIDACIÓN	t	2022-07-19 09:53:00	20	0	0	139
214	2022-07-19 10:25:27.547	Nro: 22017IC04003694U	ANULADO	0	122447751	LIQUIDACIÓN	t	2022-07-19 10:25:00	20	0	0	141
215	2022-07-19 12:18:07.903	Nro: 22017IC04003694U	EMITIDO	0	121347751	LIQUIDACIÓN	t	2022-07-19 12:18:00	20	0	0	142
216	2022-07-19 12:47:35.687	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-19 12:47:00	80	80	0	0
217	2022-07-19 13:32:01.973	Nro: 22002IRE4002998K	EMITIDO	0	4501349	LIQUIDACIÓN	t	2022-07-19 13:32:00	20	0	0	143
204	2022-07-14 13:42:43.664	Nro: 22009IC04000909R	ANULADO	0	4807376	LIQUIDACIÓN	t	2022-07-14 13:42:00	8	0	0	131
218	2022-07-19 15:51:41.449	Nro: 22009IC04000909R	EMITIDO	0	4807876	LIQUIDACIÓN	t	2022-07-19 15:51:00	8	0	0	144
221	2022-07-29 12:45:12.263	Nro: 22005IC04014982T	EMITIDO	0	8418043	LIQUIDACIÓN	t	2022-07-29 12:45:00	3	0	0	147
219	2022-07-28 12:46:32.673	Nro: 22005IC04014759V	ANULADO	0	67794564	LIQUIDACIÓN	t	2022-07-28 12:46:00	69	0	0	145
220	2022-07-29 11:26:32.531	Nro: 22005IC0401568P	ANULADO	0	111066101	LIQUIDACIÓN	t	2022-07-29 11:26:00	3	0	0	146
224	2022-07-29 15:12:22.872	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-07-29 15:12:00	81	81	0	0
225	2022-07-29 15:24:09.879	Nro: 22002IC04015355L	ANULADO	0	7650454	LIQUIDACIÓN	t	2022-07-29 15:24:00	35	0	0	150
226	2022-07-29 16:11:06.713	Nro: 22002IC04015355L	EMITIDO	0	7353454	LIQUIDACIÓN	t	2022-07-29 16:11:00	35	0	0	151
222	2022-07-29 12:55:20.768	Nro: 22005IC04014759V	ANULADO	0	11286206	LIQUIDACIÓN	t	2022-07-29 12:55:00	69	0	0	148
227	2022-07-29 16:12:00.884	Nro: 22005IC04014759V	EMITIDO	0	11286206	LIQUIDACIÓN	t	2022-07-29 16:11:00	69	0	0	152
223	2022-07-29 13:16:01.02	Nro: 22005IC0401568P	ANULADO	0	11428284	LIQUIDACIÓN	t	2022-07-29 13:16:00	3	0	0	149
228	2022-07-29 16:17:03.769	Nro: 22005IC0401568P	EMITIDO	0	11428284	LIQUIDACIÓN	t	2022-07-29 16:17:00	3	0	0	153
229	2022-08-01 15:38:21.404	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-08-01 15:38:00	82	82	0	0
230	2022-08-01 15:49:59.456	Nro: 22005IC04015268R	ANULADO	0	7599580	LIQUIDACIÓN	t	2022-08-01 15:49:00	3	0	0	154
231	2022-08-01 16:35:47.828	Nro: 22005IC04015268R	EMITIDO	0	7599580	LIQUIDACIÓN	t	2022-08-01 16:35:00	3	0	0	155
232	2022-08-02 13:47:33.097	Nro: 22018EC01002272F	EMITIDO	0	3617958	LIQUIDACIÓN	t	2022-08-02 13:47:00	63	0	0	156
233	2022-08-03 09:17:53.34	Nro: 22005IC04015515M	EMITIDO	0	10305694	LIQUIDACIÓN	t	2022-08-03 09:17:00	3	0	0	157
234	2022-08-05 14:34:52.367	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-08-05 14:34:00	83	83	0	0
235	2022-08-05 14:59:21.527	Nro: 22030IC04009498A	ANULADO	0	133964660	LIQUIDACIÓN	t	2022-08-05 14:59:00	23	0	0	158
236	2022-08-05 15:02:49.203	Nro: 22030IC04009498A	ANULADO	0	7149936	LIQUIDACIÓN	t	2022-08-05 15:02:00	23	0	0	159
237	2022-08-06 14:51:21.727	Nro: 22030IC04009498A	EMITIDO	0	7149936	LIQUIDACIÓN	t	2022-08-06 14:51:00	23	0	0	160
238	2022-08-09 08:49:49.421	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-08-09 08:49:00	84	84	0	0
240	2022-08-10 14:12:41.827	Nro: 22002IC0416590N	EMITIDO	0	3304569	LIQUIDACIÓN	t	2022-08-10 14:12:00	35	0	0	162
242	2022-08-22 14:31:33.448	Nro: 22005IC04017008L	EMITIDO	0	2351920	LIQUIDACIÓN	t	2022-08-22 14:31:00	3	0	0	164
243	2022-08-22 14:43:18.321	Nro: 22005IC04017002F	EMITIDO	0	9503004	LIQUIDACIÓN	t	2022-08-22 14:43:00	3	0	0	165
241	2022-08-22 14:18:39.541	Nro: 22005IC04016964V	ANULADO	0	12905104	LIQUIDACIÓN	t	2022-08-22 14:18:00	69	0	0	163
245	2022-08-22 15:34:32.601	Nro: 22005IC04016964V	EMITIDO	0	12905104	LIQUIDACIÓN	t	2022-08-22 15:34:00	69	0	0	167
244	2022-08-22 14:57:54.38	Nro: 22002IC04017190K	ANULADO	0	1812571	LIQUIDACIÓN	t	2022-08-22 14:57:00	35	0	0	166
246	2022-08-22 16:00:31.098	Nro: 22002IC04017190K	EMITIDO	0	4125571	LIQUIDACIÓN	t	2022-08-22 16:00:00	35	0	0	168
247	2022-08-24 09:24:59.159	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-08-24 09:24:00	85	85	0	0
248	2022-08-24 09:56:27.374	Nro: 22005IC04017095R	EMITIDO	0	3618974	LIQUIDACIÓN	t	2022-08-24 09:56:00	15	0	0	169
249	2022-08-25 13:23:34.965	Nro: 22018EC01002629L	EMITIDO	0	3494800	LIQUIDACIÓN	t	2022-08-25 13:23:00	47	0	0	170
250	2022-08-26 11:40:56.545	Nro: 22005IC04017324M	EMITIDO	0	9710896	LIQUIDACIÓN	t	2022-08-26 11:40:00	3	0	0	171
251	2022-08-26 16:34:03.621	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-08-26 16:34:00	86	86	0	0
253	2022-08-30 14:59:13.635	Nro: 22005IC04017683U	EMITIDO	0	5735036	LIQUIDACIÓN	t	2022-08-30 14:59:00	15	0	0	173
252	2022-08-30 14:15:15.81	Nro: 22005IC04017699E	ANULADO	0	81207582	LIQUIDACIÓN	t	2022-08-30 14:15:00	3	0	0	172
254	2022-08-31 09:29:49.543	Nro: 22005IC04017699E	EMITIDO	0	7017174	LIQUIDACIÓN	t	2022-08-31 09:29:00	3	0	0	174
239	2022-08-09 09:09:44.249	Nro: 22002IC04016233H	ANULADO	0	10197155	LIQUIDACIÓN	t	2022-08-09 09:09:00	35	0	0	161
256	2022-09-06 13:39:54.407	Nro: 22005IC04017962U	EMITIDO	0	10062702	LIQUIDACIÓN	t	2022-09-06 13:39:00	3	0	0	176
257	2022-09-06 13:54:17.231	Nro: 22005IM04000461Z	EMITIDO	0	1386294	LIQUIDACIÓN	t	2022-09-06 13:54:00	3	0	0	177
258	2022-09-06 13:59:12.134	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-06 13:59:00	87	87	0	0
259	2022-09-06 14:11:27.478	Nro: 22005IC04018041J	EMITIDO	0	3652874	LIQUIDACIÓN	t	2022-09-06 14:11:00	15	0	0	178
260	2022-09-06 15:06:26.535	Nro: 22002IC04018438Z	EMITIDO	0	6531657	LIQUIDACIÓN	t	2022-09-06 15:06:00	35	0	0	179
261	2022-09-06 15:24:21.473	Nro: 22002IRE4003771A	EMITIDO	0	1906366	LIQUIDACIÓN	t	2022-09-06 15:24:00	35	0	0	180
262	2022-09-07 16:06:02.704	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-07 16:06:00	88	88	0	0
263	2022-09-07 16:24:16.484	Nro: 22005IC04018181Y	EMITIDO	0	2048008	LIQUIDACIÓN	t	2022-09-07 16:24:00	8	0	0	181
264	2022-09-07 16:25:44.106	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-07 16:25:00	89	89	0	0
265	2022-09-07 16:34:35.823	Nro: 22005IC04018180N	EMITIDO	0	3165373	LIQUIDACIÓN	t	2022-09-07 16:34:00	8	0	0	182
266	2022-09-08 15:55:39.421	Nro: 22005IC04018451Y	ANULADO	0	35832292	LIQUIDACIÓN	t	2022-09-08 15:55:00	15	0	0	183
268	2022-09-12 11:01:43.962	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-12 11:01:00	90	90	0	0
269	2022-09-12 11:14:16.035	Nro: 22005IC04018593V	EMITIDO	0	4560285	LIQUIDACIÓN	t	2022-09-12 11:14:00	35	0	0	185
270	2022-09-12 15:13:51.775	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-12 15:13:00	91	91	0	0
271	2022-09-12 15:24:03.701	Nro: 22005IC04018505Y	EMITIDO	0	3335814	LIQUIDACIÓN	t	2022-09-12 15:24:00	3	0	0	186
272	2022-09-12 15:34:36.943	Nro: 22005IC04018596B	EMITIDO	0	3737587	LIQUIDACIÓN	t	2022-09-12 15:34:00	3	0	0	187
273	2022-09-12 15:45:45.211	Nro: 22005IC04018600K	EMITIDO	0	9130723	LIQUIDACIÓN	t	2022-09-12 15:45:00	3	0	0	188
274	2022-09-13 08:32:11.01	Nro: 22018EC1002796Z	EMITIDO	0	3526258	LIQUIDACIÓN	t	2022-09-13 08:32:00	47	0	0	189
275	2022-09-14 11:48:20.515	Nro: 22005IC04018820Y	EMITIDO	0	7813857	LIQUIDACIÓN	t	2022-09-14 11:48:00	15	0	0	190
278	2022-09-20 14:22:38.551	Nro: 22005IM04000489D	EMITIDO	0	1672083	LIQUIDACIÓN	t	2022-09-20 14:22:00	3	0	0	193
277	2022-09-20 14:08:53.199	Nro: 22005IC04019195U	ANULADO	0	9034557	LIQUIDACIÓN	t	2022-09-20 14:08:00	3	0	0	192
279	2022-09-20 14:23:44.725	Nro: 22005IC04019195U	EMITIDO	0	9034557	LIQUIDACIÓN	t	2022-09-20 14:23:00	3	0	0	194
280	2022-09-26 08:36:48.633	Nro: 22005IC04019675A	ANULADO	0	12889292	LIQUIDACIÓN	t	2022-09-26 08:36:00	15	0	0	195
281	2022-09-26 08:42:12.04	Nro: 22005IC04019675A	EMITIDO	0	12889292	LIQUIDACIÓN	t	2022-09-26 08:42:00	15	0	0	196
282	2022-09-27 12:44:53.956	Nro: 22005IM04000503N	EMITIDO	0	4064297	LIQUIDACIÓN	t	2022-09-27 12:44:00	3	0	0	197
283	2022-09-29 10:58:24.273	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-09-29 10:58:00	92	92	0	0
284	2022-09-29 11:16:43.194	Nro: 22002IC04020237G	EMITIDO	0	24246178	LIQUIDACIÓN	t	2022-09-29 11:16:00	20	0	0	198
286	2022-10-05 10:37:19.941	Nro: 22005IC04020283K	EMITIDO	0	2189279	LIQUIDACIÓN	t	2022-10-05 10:37:00	3	0	0	200
285	2022-10-05 09:45:25.664	Nro: 22005IC04020280H	ANULADO	0	10159668	LIQUIDACIÓN	t	2022-10-05 09:45:00	3	0	0	199
287	2022-10-05 10:38:20.808	Nro: 22005IC04020280H	EMITIDO	0	10159668	LIQUIDACIÓN	t	2022-10-05 10:38:00	3	0	0	201
288	2022-10-05 11:29:12.176	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-05 11:29:00	93	93	0	0
289	2022-10-05 11:43:06.249	Nro: 22032IC04006239P	ANULADO	0	9739265	LIQUIDACIÓN	t	2022-10-05 11:43:00	23	0	0	202
290	2022-10-06 11:13:25.576	Nro: 22032IC04006239P	EMITIDO	0	9739265	LIQUIDACIÓN	t	2022-10-06 11:13:00	23	0	0	203
291	2022-10-06 16:51:44.222	Nro: 22005IC04020555M	ANULADO	0	9636890	LIQUIDACIÓN	t	2022-10-06 16:51:00	15	0	0	204
292	2022-10-07 14:11:43.711	Nro: 22005IC04020555M	EMITIDO	0	9636890	LIQUIDACIÓN	t	2022-10-07 14:11:00	15	0	0	205
293	2022-10-07 14:16:35.112	Nro: 22018EC01003111V	EMITIDO	0	3947329	LIQUIDACIÓN	t	2022-10-07 14:16:00	47	0	0	206
294	2022-10-10 11:05:39.892	Nro: 22030IC04012492L	EMITIDO	0	10105659	LIQUIDACIÓN	t	2022-10-10 11:05:00	23	0	0	207
295	2022-10-10 11:59:51.514	Nro: 22005IC04020815L	EMITIDO	0	9239789	LIQUIDACIÓN	t	2022-10-10 11:59:00	3	0	0	208
296	2022-10-10 12:32:40.556	Nro: 22005IM04000530N	EMITIDO	0	2291134	LIQUIDACIÓN	t	2022-10-10 12:32:00	3	0	0	209
297	2022-10-10 12:39:42.253	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-10 12:39:00	94	94	0	0
298	2022-10-10 13:07:38.351	Nro: 22005IT02001944F	EMITIDO	0	8632068	LIQUIDACIÓN	t	2022-10-10 13:07:00	15	0	0	210
299	2022-10-10 13:54:54.486	Nro: 22018EC01003167J	EMITIDO	0	3766097	LIQUIDACIÓN	t	2022-10-10 13:54:00	47	0	0	211
267	2022-09-08 16:01:54.218	Nro: 22005IC04018451Y	ANULADO	0	9302205	LIQUIDACIÓN	t	2022-09-08 16:01:00	15	0	0	184
300	2022-10-10 15:58:53.664	Nro: 22005IC04018451Y	EMITIDO	0	9299110	LIQUIDACIÓN	t	2022-10-10 15:58:00	15	0	0	212
255	2022-09-06 13:21:07.44	Nro: 22018EC01002739N	ANULADO	0	5944695	LIQUIDACIÓN	t	2022-09-06 13:21:00	47	0	0	175
302	2022-10-11 11:23:16.373	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-11 11:23:00	95	95	0	0
303	2022-10-11 11:23:51.988	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-11 11:23:00	96	96	0	0
276	2022-09-16 14:22:05.723	Nro: 22038IC04007766E	ANULADO	0	8297367	LIQUIDACIÓN	t	2022-09-16 14:22:00	23	0	0	191
305	2022-10-14 11:10:08.893	Nro: 22018EC01003244F	EMITIDO	0	3394639	LIQUIDACIÓN	t	2022-10-14 11:10:00	47	0	0	218
306	2022-10-14 15:44:01.54	Nro: 22005IC04021313F	EMITIDO	0	9182294	LIQUIDACIÓN	t	2022-10-14 15:44:00	3	0	0	219
307	2022-10-14 15:55:13.967	Nro: 22002IM04009915D	EMITIDO	0	4093625	LIQUIDACIÓN	t	2022-10-14 15:55:00	35	0	0	220
308	2022-10-17 08:43:24.114	Nro: 22030IC04012801P	ANULADO	0	11577853	LIQUIDACIÓN	t	2022-10-17 08:43:00	23	0	0	221
309	2022-10-17 09:35:27.608	Nro: 22030IC04012801P	EMITIDO	0	11452318	LIQUIDACIÓN	t	2022-10-17 09:35:00	23	0	0	222
310	2022-10-17 10:28:28.067	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-17 10:28:00	97	97	0	0
311	2022-10-17 10:29:12.664	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-17 10:29:00	98	98	0	0
312	2022-10-17 10:38:32.865	Nro: 22002IC04021343F	ANULADO	0	8272060	LIQUIDACIÓN	t	2022-10-17 10:38:00	97	0	0	223
313	2022-10-17 15:49:59.644	Nro: 22002IC04021343F	ANULADO	0	8478238	LIQUIDACIÓN	t	2022-10-17 15:49:00	97	0	0	224
314	2022-10-17 15:57:47.809	Nro: 22002IC04021343F	ANULADO	0	8478238	LIQUIDACIÓN	t	2022-10-17 15:57:00	97	0	0	225
318	2022-10-18 10:46:08.995	Nro: 22005IT02001996M	EMITIDO	0	14933027	LIQUIDACIÓN	t	2022-10-18 10:46:00	15	0	0	229
304	2022-10-14 10:58:47.494	Nro: 22005IC04020978V	ANULADO	0	11565424	LIQUIDACIÓN	t	2022-10-14 10:58:00	15	0	0	217
319	2022-10-18 10:53:38.942	Nro: 22005IC04020978V	ANULADO	0	6565424	LIQUIDACIÓN	t	2022-10-18 10:53:00	15	0	0	230
320	2022-10-18 13:04:43.664	Nro: 22005IC04020978V	EMITIDO	0	6565424	LIQUIDACIÓN	t	2022-10-18 13:04:00	15	0	0	231
316	2022-10-18 09:56:11.262	Nro: 22005IC04021310C	ANULADO	0	8963252	LIQUIDACIÓN	t	2022-10-18 09:56:00	3	0	0	227
321	2022-10-18 13:20:55.865	Nro: 22005IC04021310C	EMITIDO	0	8963252	LIQUIDACIÓN	t	2022-10-18 13:20:00	3	0	0	232
317	2022-10-18 10:24:53.518	Nro: 22005IC04021442X	ANULADO	0	7529797	LIQUIDACIÓN	t	2022-10-18 10:24:00	3	0	0	228
322	2022-10-18 13:29:31.194	Nro: 22005IC04021442X	EMITIDO	0	7639797	LIQUIDACIÓN	t	2022-10-18 13:29:00	3	0	0	233
301	2022-10-10 16:03:18.855	Nro: 22018EC01002739N	ANULADO	0	5944695	LIQUIDACIÓN	t	2022-10-10 16:03:00	47	0	0	213
324	2022-10-19 13:49:23.163	Nro: 22005IC04021655Y	EMITIDO	0	6183312	LIQUIDACIÓN	t	2022-10-19 13:49:00	15	0	0	235
323	2022-10-19 13:11:58.277	Nro: 22005IT02002020Y	ANULADO	0	4103298	LIQUIDACIÓN	t	2022-10-19 13:11:00	15	0	0	234
325	2022-10-19 13:58:41.464	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 13:58:00	15	0	0	236
326	2022-10-19 13:59:25.142	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 13:59:00	15	0	0	237
327	2022-10-19 13:59:56.698	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 13:59:00	15	0	0	238
328	2022-10-19 14:00:32.868	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 14:00:00	15	0	0	239
329	2022-10-19 14:01:00.515	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 14:00:00	15	0	0	240
330	2022-10-19 14:01:22.971	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 14:01:00	15	0	0	241
331	2022-10-19 14:01:49.054	Nro: 22005IT02002020Y	ANULADO	0	3730298	LIQUIDACIÓN	t	2022-10-19 14:01:00	15	0	0	242
332	2022-10-19 14:11:01.77	Nro: 22005IT02002020Y	ANULADO	0	3487298	LIQUIDACIÓN	t	2022-10-19 14:11:00	15	0	0	243
333	2022-10-19 15:09:05.878	Nro: 22005IT02002020Y	ANULADO	0	3487298	LIQUIDACIÓN	t	2022-10-19 15:09:00	15	0	0	244
334	2022-10-19 15:14:23.497	Nro: 22005IT02002020Y	EMITIDO	0	3487298	LIQUIDACIÓN	t	2022-10-19 15:14:00	15	0	0	245
315	2022-10-17 15:59:23.224	Nro: 22002IC04021343F	ANULADO	0	8478238	LIQUIDACIÓN	t	2022-10-17 15:59:00	97	0	0	226
335	2022-10-20 08:39:01.007	Nro: 22002IC04021343F	ANULADO	0	8675368	LIQUIDACIÓN	t	2022-10-20 08:38:00	97	0	0	246
336	2022-10-20 08:43:05.406	Nro: 22002IC04021343F	ANULADO	0	8675368	LIQUIDACIÓN	t	2022-10-20 08:43:00	97	0	0	247
337	2022-10-20 08:44:14.572	Nro: 22002IC04021343F	ANULADO	0	9278238	LIQUIDACIÓN	t	2022-10-20 08:44:00	97	0	0	248
338	2022-10-20 08:46:11.585	Nro: 22002IC04021343F	ANULADO	0	9278238	LIQUIDACIÓN	t	2022-10-20 08:46:00	97	0	0	249
339	2022-10-20 08:50:03.884	Nro: 22002IC04021343F	ANULADO	0	9278238	LIQUIDACIÓN	t	2022-10-20 08:50:00	97	0	0	250
340	2022-10-20 08:56:39.957	Nro: 22002IC04021343F	ANULADO	0	9278238	LIQUIDACIÓN	t	2022-10-20 08:56:00	97	0	0	251
341	2022-10-20 11:51:18.626	Nro: 22002IC04021343F	ANULADO	0	8478238	LIQUIDACIÓN	t	2022-10-20 11:51:00	97	0	0	252
342	2022-10-20 12:11:13.824	Nro: 22002IC04021343F	ANULADO	0	8478238	LIQUIDACIÓN	t	2022-10-20 12:11:00	97	0	0	253
343	2022-10-20 12:12:45.453	Nro: 22002IC04021343F	EMITIDO	0	8478238	LIQUIDACIÓN	t	2022-10-20 12:12:00	97	0	0	254
344	2022-10-24 10:46:11.42	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-24 10:46:00	99	99	0	0
345	2022-10-24 14:13:29.476	Nro: 22030IC04013106E	EMITIDO	0	4062093	LIQUIDACIÓN	t	2022-10-24 14:13:00	23	0	0	255
346	2022-10-25 10:53:38.549	Nro: 22005IC04021979A	EMITIDO	0	8166299	LIQUIDACIÓN	t	2022-10-25 10:53:00	15	0	0	256
347	2022-10-28 15:31:19.014	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-28 15:31:00	100	100	0	0
348	2022-10-28 15:32:12.79	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-10-28 15:32:00	101	101	0	0
349	2022-10-28 15:54:20.407	Nro: 22005IC04022428N	ANULADO	0	9023717	LIQUIDACIÓN	t	2022-10-28 15:54:00	100	0	0	257
350	2022-10-28 16:17:41.969	Nro: 22005IC04022428N	ANULADO	0	9233717	LIQUIDACIÓN	t	2022-10-28 16:17:00	100	0	0	258
351	2022-10-28 16:20:25.223	Nro: 22005IC04022428N	ANULADO	0	9258117	LIQUIDACIÓN	t	2022-10-28 16:20:00	100	0	0	259
352	2022-10-28 16:59:01.52	Nro: 22005IC04022428N	ANULADO	0	10358117	LIQUIDACIÓN	t	2022-10-28 16:59:00	100	0	0	260
353	2022-10-31 13:40:20.389	Nro: 22005IC04022428N	EMITIDO	0	10358117	LIQUIDACIÓN	t	2022-10-31 13:40:00	100	0	0	261
354	2022-10-31 16:43:45.529	Nro: 22005IC04022593Z	ANULADO	0	5678666	LIQUIDACIÓN	t	2022-10-31 16:43:00	57	0	0	262
357	2022-11-01 08:35:50.459	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-01 08:35:00	102	102	0	0
359	2022-11-01 08:47:34.337	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-01 08:47:00	103	103	0	0
356	2022-11-01 08:22:54.365	Nro: 22005IC04022341H	ANULADO	0	8911522	LIQUIDACIÓN	t	2022-11-01 08:22:00	15	0	0	264
361	2022-11-01 10:56:58.723	Nro: 22005IC04022341H	EMITIDO	0	8911522	LIQUIDACIÓN	t	2022-11-01 10:56:00	15	0	0	267
358	2022-11-01 08:40:46.561	Nro: 22005IC04022399U	ANULADO	0	1743489	LIQUIDACIÓN	t	2022-11-01 08:40:00	15	0	0	265
362	2022-11-01 10:57:54.807	Nro: 22005IC04022399U	EMITIDO	0	1743489	LIQUIDACIÓN	t	2022-11-01 10:57:00	15	0	0	268
360	2022-11-01 08:52:31.06	Nro: 22018EC01003410A	ANULADO	0	3798548	LIQUIDACIÓN	t	2022-11-01 08:52:00	103	0	0	266
363	2022-11-01 10:58:57.512	Nro: 22018EC01003410A	EMITIDO	0	3798548	LIQUIDACIÓN	t	2022-11-01 10:58:00	103	0	0	269
364	2022-11-01 15:07:47.251	Nro: 22005IC04022651L	ANULADO	0	3703830	LIQUIDACIÓN	t	2022-11-01 15:07:00	35	0	0	270
366	2022-11-01 15:31:58.089	Nro: 22005IC04022651L	EMITIDO	0	3703830	LIQUIDACIÓN	t	2022-11-01 15:31:00	35	0	0	272
365	2022-11-01 15:20:16.413	Nro: 22005IT02002128A	ANULADO	0	1876436	LIQUIDACIÓN	t	2022-11-01 15:20:00	15	0	0	271
367	2022-11-02 07:28:49.092	Nro: 22005IT02002128A	EMITIDO	0	1877436	LIQUIDACIÓN	t	2022-11-02 07:28:00	15	0	0	273
355	2022-10-31 16:59:18.87	Nro: 22005IC04022593Z	ANULADO	0	4450640	LIQUIDACIÓN	t	2022-10-31 16:59:00	57	0	0	263
368	2022-11-02 15:26:31.968	Nro: 22005IC04022593Z	EMITIDO	0	4450640	LIQUIDACIÓN	t	2022-11-02 15:26:00	57	0	0	274
369	2022-11-07 10:06:58.266	Nro: 22018EC01003478Y	EMITIDO	0	3735922	LIQUIDACIÓN	t	2022-11-07 10:06:00	63	0	0	275
370	2022-11-08 16:24:49.058	Nro: 22002IC04022934M	EMITIDO	0	6797381	LIQUIDACIÓN	t	2022-11-08 16:24:00	35	0	0	276
371	2022-11-10 08:42:26.665	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-10 08:42:00	104	104	0	0
374	2022-11-11 12:59:07.526	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-11 12:59:00	105	105	0	0
375	2022-11-11 13:06:07.996	Nro: 22005IC04023521X	EMITIDO	0	6963937	LIQUIDACIÓN	t	2022-11-11 13:06:00	8	0	0	279
372	2022-11-10 08:56:23.634	Nro: 22002IC04022711F	ANULADO	0	13083809	LIQUIDACIÓN	t	2022-11-10 08:56:00	20	0	0	277
376	2022-11-14 11:17:16.457	Nro: 22002IC04022711F	EMITIDO	0	12878809	LIQUIDACIÓN	t	2022-11-14 11:17:00	20	0	0	280
373	2022-11-10 09:15:48.696	Nro: 22002IC04022713H	ANULADO	0	15354402	LIQUIDACIÓN	t	2022-11-10 09:15:00	20	0	0	278
377	2022-11-14 11:22:14.07	Nro: 22002IC04022713H	EMITIDO	0	15139402	LIQUIDACIÓN	t	2022-11-14 11:22:00	20	0	0	281
378	2022-11-14 15:37:03.385	Nro: 22005IM04000596C	EMITIDO	0	1601235	LIQUIDACIÓN	t	2022-11-14 15:37:00	3	0	0	282
379	2022-11-18 14:59:05.521	Nro: 22005IT02002225V	EMITIDO	0	7483591	LIQUIDACIÓN	t	2022-11-18 14:59:00	15	0	0	283
380	2022-11-18 15:15:05.053	Nro: 22005IT02002254A	EMITIDO	0	5901693	LIQUIDACIÓN	t	2022-11-18 15:15:00	15	0	0	284
381	2022-11-21 11:29:05.31	Nro: 22002IC04023965R	EMITIDO	0	3440903	LIQUIDACIÓN	t	2022-11-21 11:29:00	35	0	0	286
382	2022-11-25 12:57:23.94	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-25 12:57:00	106	106	0	0
383	2022-11-25 13:29:10.14	Nro: 22038IC04009378F	ANULADO	0	9787069	LIQUIDACIÓN	t	2022-11-25 13:29:00	23	0	0	287
384	2022-11-25 14:01:47.883	Nro: 22038IC04009378F	ANULADO	0	9787069	LIQUIDACIÓN	t	2022-11-25 14:01:00	23	0	0	288
385	2022-11-29 09:10:02.8	Nro: 22038IC04009378F	EMITIDO	0	9927069	LIQUIDACIÓN	t	2022-11-29 09:10:00	23	0	0	289
386	2022-11-29 09:41:38.491	Nro: 22018EC01003699T	EMITIDO	0	2058525	LIQUIDACIÓN	t	2022-11-29 09:41:00	63	0	0	290
387	2022-11-29 09:47:12.223	Nro: 22018EM01001393S	EMITIDO	0	3828031	LIQUIDACIÓN	t	2022-11-29 09:47:00	63	0	0	291
388	2022-11-29 12:08:12.726	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-29 12:08:00	107	107	0	0
389	2022-11-29 12:09:00.272	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-11-29 12:08:00	108	108	0	0
391	2022-12-01 10:02:56.384	Nro: 22018EC01002739N	EMITIDO	0	5944695	LIQUIDACIÓN	t	2022-12-01 10:02:00	47	0	0	293
392	2022-12-01 10:05:55.453	Nro: 22005IC04018451Y	EMITIDO	0	9299110	LIQUIDACIÓN	t	2022-12-01 10:05:00	15	0	0	294
393	2022-12-05 11:02:48.261	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-05 11:02:00	109	109	0	0
394	2022-12-05 11:28:08.639	Nro: 22005IC04025243L	ANULADO	0	8090206	LIQUIDACIÓN	t	2022-12-05 11:28:00	109	0	0	295
395	2022-12-05 11:49:48.441	Nro: 22005IC04025243L	EMITIDO	0	8090206	LIQUIDACIÓN	t	2022-12-05 11:49:00	109	0	0	296
396	2022-12-09 09:18:23.484	Nro: 22005IT02002407A	ANULADO	0	6075222	LIQUIDACIÓN	t	2022-12-09 09:18:00	15	0	0	298
397	2022-12-09 09:28:20.993	Nro: 22005IT02002407A	EMITIDO	0	6075222	LIQUIDACIÓN	t	2022-12-09 09:28:00	15	0	0	299
398	2022-12-12 14:06:28.093	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-12 14:06:00	110	110	0	0
399	2022-12-12 14:07:05.5	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-12 14:07:00	111	111	0	0
390	2022-11-29 12:20:07.409	Nro: 22013IC04000584L	ANULADO	0	22655867	LIQUIDACIÓN	t	2022-11-29 12:20:00	95	0	0	292
401	2022-12-12 14:38:33.947	Nro: 22013IC04000584L	ANULADO	0	19987014	LIQUIDACIÓN	t	2022-12-12 14:38:00	95	0	0	301
400	2022-12-12 14:17:47.695	Nro: 22032IC04007922P	ANULADO	0	15453736	LIQUIDACIÓN	t	2022-12-12 14:17:00	110	0	0	300
402	2022-12-12 14:55:19.924	Nro: 22032IC04007922P	EMITIDO	0	15834532	LIQUIDACIÓN	t	2022-12-12 14:55:00	110	0	0	302
403	2022-12-13 09:43:25.052	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-13 09:43:00	112	112	0	0
404	2022-12-13 09:43:48.279	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-13 09:43:00	113	113	0	0
405	2022-12-14 13:11:02.064	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2022-12-14 13:11:00	114	114	0	0
408	2022-12-14 14:40:13.748	Nro: 22005IC04026060J	EMITIDO	0	7645886	LIQUIDACIÓN	t	2022-12-14 14:40:00	3	0	0	308
406	2022-12-14 13:19:48.519	Nro: 22002IM04012189A	ANULADO	0	594969	LIQUIDACIÓN	t	2022-12-14 13:19:00	109	0	0	306
409	2022-12-14 14:53:56.319	Nro: 22002IM04012189A	EMITIDO	0	1671888	LIQUIDACIÓN	t	2022-12-14 14:53:00	109	0	0	309
407	2022-12-14 14:20:14.828	Nro: 22005IC04025979E	ANULADO	0	29729343	LIQUIDACIÓN	t	2022-12-14 14:20:00	3	0	0	307
410	2022-12-16 08:52:50.014	Nro: 22005IC04025979E	EMITIDO	0	5607309	LIQUIDACIÓN	t	2022-12-16 08:52:00	3	0	0	310
411	2022-12-20 09:31:05.485	Nro: 22005IC04026605Y	ANULADO	0	3793411	LIQUIDACIÓN	t	2022-12-20 09:31:00	57	0	0	311
412	2022-12-20 09:32:30.52	Nro: 22005IC04026605Y	ANULADO	0	3861965	LIQUIDACIÓN	t	2022-12-20 09:32:00	57	0	0	312
413	2022-12-20 09:51:51.064	Nro: 22005IC04026605Y	EMITIDO	0	3661965	LIQUIDACIÓN	t	2022-12-20 09:51:00	57	0	0	313
416	2022-12-20 16:22:27.267	Nro: 22005IM04000690U	EMITIDO	0	1492516	LIQUIDACIÓN	t	2022-12-20 16:22:00	3	0	0	316
417	2022-12-22 08:56:48.288	Nro: 22005IT04026628T	ANULADO	0	11002391	LIQUIDACIÓN	t	2022-12-22 08:56:00	3	0	0	317
418	2022-12-27 07:48:46.86	Nro: 22005IT04026628T	EMITIDO	0	11002391	LIQUIDACIÓN	t	2022-12-27 07:48:00	3	0	0	318
420	2023-01-03 14:08:24.609	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-03 14:08:00	115	115	0	0
423	2023-01-04 14:11:16.712	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-04 14:11:00	116	116	0	0
425	2023-01-04 15:54:27.938	Nro: 23005IT02000003Y	EMITIDO	0	609168	LIQUIDACIÓN	t	2023-01-04 15:54:00	15	0	0	323
422	2023-01-04 14:08:09.389	Nro: 23005IC04000047H	ANULADO	0	3191657	LIQUIDACIÓN	t	2023-01-04 14:08:00	8	0	0	321
426	2023-01-04 15:56:07.645	Nro: 23005IC04000047H	EMITIDO	0	3191657	LIQUIDACIÓN	t	2023-01-04 15:56:00	8	0	0	324
421	2023-01-04 09:40:09.562	Nro: 22030IC04016462M	ANULADO	0	4864051	LIQUIDACIÓN	t	2023-01-04 09:40:00	23	0	0	320
427	2023-01-05 14:19:22.556	Nro: 22030IC04016462M	EMITIDO	0	4864051	LIQUIDACIÓN	t	2023-01-05 14:19:00	23	0	0	325
419	2023-01-02 16:42:26.084	Nro: 22005IC04027254P	ANULADO	0	4535724	LIQUIDACIÓN	t	2023-01-02 16:42:00	8	0	0	319
428	2023-01-09 08:53:02.235	Nro: 22005IC04027254P	EMITIDO	0	4535724	LIQUIDACIÓN	t	2023-01-09 08:53:00	8	0	0	326
429	2023-01-09 09:25:27.747	Nro: 22002IC04026356Y	EMITIDO	0	4484544	LIQUIDACIÓN	t	2023-01-09 09:25:00	35	0	0	327
431	2023-01-09 11:23:26.552	Nro: 22005IC04027294T	EMITIDO	0	7837236	LIQUIDACIÓN	t	2023-01-09 11:23:00	15	0	0	329
415	2022-12-20 10:59:30.137	Nro: 22018EM01001449U	ANULADO	0	2727934	LIQUIDACIÓN	t	2022-12-20 10:59:00	63	0	0	315
432	2023-01-10 10:13:22.133	Nro: 22018EM01001449U	EMITIDO	0	2727934	LIQUIDACIÓN	t	2023-01-10 10:13:00	63	0	0	330
414	2022-12-20 10:52:28.57	Nro: 22018EC01003938P	ANULADO	0	2626454	LIQUIDACIÓN	t	2022-12-20 10:52:00	63	0	0	314
433	2023-01-10 10:14:10.923	Nro: 22018EC01003938P	EMITIDO	0	2626454	LIQUIDACIÓN	t	2023-01-10 10:14:00	63	0	0	331
434	2023-01-10 10:27:04.26	Nro: 22005IC04027188V	ANULADO	0	112256036	LIQUIDACIÓN	t	2023-01-10 10:27:00	15	0	0	332
435	2023-01-10 10:27:43.401	Nro: 22005IC04027188V	EMITIDO	0	9673509	LIQUIDACIÓN	t	2023-01-10 10:27:00	15	0	0	333
436	2023-01-10 10:53:36.21	Nro: 22018EC01004012W	EMITIDO	0	3486671	LIQUIDACIÓN	t	2023-01-10 10:53:00	63	0	0	334
430	2023-01-09 10:01:16.195	Nro: 23005IT0402000003Y	ANULADO	0	3577890	LIQUIDACIÓN	t	2023-01-09 10:01:00	15	0	0	328
437	2023-01-10 11:04:14.67	Nro: 23005IT0402000003Y	EMITIDO	0	3577890	LIQUIDACIÓN	t	2023-01-10 11:04:00	15	0	0	335
438	2023-01-18 09:09:08.332	Nro: 22002IC04026356Y	EMITIDO	0	4484524	LIQUIDACIÓN	t	2023-01-18 09:09:00	35	0	0	336
424	2023-01-04 14:43:28.437	Nro: 23005IC04000095K	ANULADO	0	3584330	LIQUIDACIÓN	t	2023-01-04 14:43:00	109	0	0	322
439	2023-01-18 09:24:44.783	Nro: 23005IC04000095K	EMITIDO	0	3584330	LIQUIDACIÓN	t	2023-01-18 09:24:00	109	0	0	337
440	2023-01-18 10:56:32.024	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-18 10:56:00	117	117	0	0
442	2023-01-18 11:42:15.949	Nro: 23005IC04000679S	EMITIDO	0	10536537	LIQUIDACIÓN	t	2023-01-18 11:42:00	3	0	0	341
441	2023-01-18 11:26:55.922	Nro: 23005IC04000726L	ANULADO	0	8240877	LIQUIDACIÓN	t	2023-01-18 11:26:00	109	0	0	340
443	2023-01-18 11:43:43.828	Nro: 23005IC04000726L	EMITIDO	0	8240877	LIQUIDACIÓN	t	2023-01-18 11:43:00	109	0	0	342
444	2023-01-18 14:53:34.517	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-18 14:53:00	118	118	0	0
445	2023-01-18 15:20:39.706	Nro: 23002IC04000811D	EMITIDO	0	3463378	LIQUIDACIÓN	t	2023-01-18 15:20:00	109	0	0	343
446	2023-01-18 15:54:58.111	Nro: 23030IM04000026M	EMITIDO	0	4502252	LIQUIDACIÓN	t	2023-01-18 15:54:00	110	0	0	344
447	2023-01-19 10:19:52.163	Nro: 23015IC04000328K	EMITIDO	0	60312863	LIQUIDACIÓN	t	2023-01-19 10:19:00	51	0	0	345
448	2023-01-24 14:29:58.957	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-24 14:29:00	119	119	0	0
450	2023-01-26 11:45:17.233	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-01-26 11:45:00	120	120	0	0
451	2023-01-27 08:41:25.664	Nro: 23005IC04001270G	EMITIDO	0	10332875	LIQUIDACIÓN	t	2023-01-27 08:41:00	3	0	0	347
452	2023-01-27 08:59:02.637	Nro: 23005IC04001533X	EMITIDO	0	3250075	LIQUIDACIÓN	t	2023-01-27 08:59:00	8	0	0	348
449	2023-01-26 11:08:46.445	Nro: 23030IC04000797R	ANULADO	0	250000	LIQUIDACIÓN	t	2023-01-26 11:08:00	3	0	0	346
453	2023-01-30 11:38:28.028	Nro: 23030IC04000797R	EMITIDO	0	7909410	LIQUIDACIÓN	t	2023-01-30 11:38:00	3	0	0	349
454	2023-01-30 14:11:02.198	Nro: 23038IC04000560N	ANULADO	0	10212213	LIQUIDACIÓN	t	2023-01-30 14:11:00	23	0	0	350
455	2023-01-30 14:37:18.253	Nro: 23038IC04000560N	EMITIDO	0	10212213	LIQUIDACIÓN	t	2023-01-30 14:37:00	23	0	0	351
456	2023-02-08 10:28:38.222	Nro: 23005IC04002151F	EMITIDO	0	3866011	LIQUIDACIÓN	t	2023-02-08 10:28:00	3	0	0	352
457	2023-02-08 10:48:56.236	Nro: 23005IC04002211C	EMITIDO	0	4180454	LIQUIDACIÓN	t	2023-02-08 10:48:00	109	0	0	353
458	2023-02-08 11:16:50.545	Nro: 23005IC04002142F	ANULADO	0	9038600	LIQUIDACIÓN	t	2023-02-08 11:16:00	95	0	0	354
460	2023-02-08 11:41:25.961	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-02-08 11:41:00	121	121	0	0
461	2023-02-08 11:41:36.165	Nro: 23005IC04002142F	EMITIDO	0	9038600	LIQUIDACIÓN	t	2023-02-08 11:41:00	121	0	0	356
459	2023-02-08 11:40:30.069	Nro: 23005IM04000055Z	ANULADO	0	1	LIQUIDACIÓN	t	2023-02-08 11:40:00	3	0	0	355
463	2023-02-08 15:09:20.68	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-02-08 15:09:00	122	122	0	0
464	2023-02-08 15:18:12.876	Nro: 23005IC04002430F	EMITIDO	0	9647531	LIQUIDACIÓN	t	2023-02-08 15:18:00	3	0	0	358
462	2023-02-08 15:05:31.216	Nro: 23005IM04000055Z	ANULADO	0	1818227	LIQUIDACIÓN	t	2023-02-08 15:05:00	3	0	0	357
465	2023-02-08 15:45:52.736	Nro: 23005IM04000055Z	EMITIDO	0	1363226	LIQUIDACIÓN	t	2023-02-08 15:45:00	3	0	0	359
466	2023-02-14 13:26:55.335	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-02-14 13:26:00	123	123	0	0
467	2023-02-14 13:32:45.889	Nro: 23030IC04001776P	ANULADO	0	3787160	LIQUIDACIÓN	t	2023-02-14 13:32:00	23	0	0	360
468	2023-02-14 13:56:26.28	Nro: 23030IC04001776P	EMITIDO	0	3787160	LIQUIDACIÓN	t	2023-02-14 13:56:00	23	0	0	361
469	2023-02-16 10:40:28.168	Nro: 23005IM04000064Z	EMITIDO	0	1397765	LIQUIDACIÓN	t	2023-02-16 10:40:00	3	0	0	362
470	2023-02-16 10:58:34.282	Nro: 23005IC04002726N	EMITIDO	0	13032119	LIQUIDACIÓN	t	2023-02-16 10:58:00	3	0	0	363
471	2023-02-16 11:15:57.145	Nro: 23005IC04002603H	EMITIDO	0	7712073	LIQUIDACIÓN	t	2023-02-16 11:15:00	3	0	0	364
472	2023-02-16 11:41:17.94	Nro: 23005IC04002879W	EMITIDO	0	7322775	LIQUIDACIÓN	t	2023-02-16 11:41:00	3	0	0	365
473	2023-02-16 14:04:04.598	CREDITO DE CLIENTE DE INICIO	EMITIDO	0	0	CLIENTE	f	2023-02-16 14:04:00	124	124	0	0
474	2023-02-16 14:19:17.725	Nro: 23005IT02000322S	ANULADO	0	7653523	LIQUIDACIÓN	t	2023-02-16 14:19:00	15	0	0	366
475	2023-02-16 14:35:34.093	Nro: 23005IT02000322S	EMITIDO	0	7653523	LIQUIDACIÓN	t	2023-02-16 14:35:00	15	0	0	367
\.


--
-- TOC entry 3600 (class 0 OID 24599)
-- Dependencies: 218
-- Data for Name: despacho_zona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.despacho_zona (iddespacho_zona, nombre, eliminado) FROM stdin;
2	MERCOSUR (INTRAZONA)	f
1	ACUERDO MERCO-CHILE	f
3	EXTRAZONA	f
\.


--
-- TOC entry 3601 (class 0 OID 24605)
-- Dependencies: 219
-- Data for Name: encomienta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.encomienta (idencomienta, fecha_creado, numero, destinatario, telefono, direccion_destino, forma_entrega, forma_pago, detalle_paquete, monto, fk_idtercero_ciudad, fk_idliquidacion_proforma, fk_idtercero) FROM stdin;
\.


--
-- TOC entry 3602 (class 0 OID 24610)
-- Dependencies: 220
-- Data for Name: factura_nro_proforma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura_nro_proforma (idfactura_nro_proforma, fecha_creacion, creado_por, nro_factura, monto, imagen, guarani_unidad, fk_idliquidacion_proforma, fk_idmoneda_cambio) FROM stdin;
2	2021-11-22 22:38:55.442	DIGNO	gyyjgyjg	1500	SIN-IMAGEN	6800.00	3	1
3	2021-11-22 22:38:55.448	DIGNO	yfjfyjj	2000	SIN-IMAGEN	6800.00	3	1
4	2021-11-22 22:38:55.465	DIGNO	ghfjfyjf	2500	SIN-IMAGEN	6800.00	3	1
5	2021-11-22 22:38:55.468	DIGNO	fghfghf	3200	SIN-IMAGEN	6800.00	3	1
6	2021-11-22 22:47:07.706	DIGNO	fhfghfgh	1500	SIN-IMAGEN	6800.00	1	1
7	2021-11-22 22:47:07.709	DIGNO	ftfthftj	1800	SIN-IMAGEN	6800.00	1	1
8	2021-11-22 22:47:07.712	DIGNO	ftydthfch	2100	SIN-IMAGEN	6800.00	1	1
9	2021-11-23 21:54:56.294	DIGNO	434534	505000	SIN-IMAGEN	7100.00	2	3
10	2021-11-23 21:54:56.297	DIGNO	343453453	450000	SIN-IMAGEN	7100.00	2	3
11	2021-11-23 21:54:56.3	DIGNO	535353	600000	SIN-IMAGEN	7100.00	2	3
1	2021-11-22 22:32:06.59	DIGNO	iuouio	15000	SIN-IMAGEN	6800.00	1	1
12	2021-11-23 23:55:09.224	DIGNO	dfasf32	15000	SIN-IMAGEN	6800.00	3	1
13	2021-11-24 22:15:40.499	DIGNO	afafaf	5000	SIN-IMAGEN	6850.00	4	1
14	2021-11-24 22:15:40.502	DIGNO	dadfasf	6000	SIN-IMAGEN	6850.00	4	1
15	2021-11-24 23:01:55.788	DIGNO	51641dsdg	45000	SIN-IMAGEN	6850.00	5	1
16	2021-11-24 23:01:55.792	DIGNO	ergegr516	50000	SIN-IMAGEN	6850.00	5	1
17	2021-11-25 00:21:16.494	DIGNO	rfergerg551	15000	SIN-IMAGEN	1120.00	6	3
18	2021-11-25 20:37:40.72	DIGNO	52516	15000	SIN-IMAGEN	6850.00	7	1
19	2021-11-25 20:46:08.687	DIGNO	ghfgh	1500	SIN-IMAGEN	6850.00	8	1
20	2021-11-25 20:46:08.692	DIGNO	fhfh55	1600	SIN-IMAGEN	6850.00	8	1
21	2021-11-25 20:46:08.692	DIGNO	ghjfh524	1550	SIN-IMAGEN	6850.00	8	1
22	2021-11-25 20:46:08.697	DIGNO	fhdh5424	1800	SIN-IMAGEN	6850.00	8	1
23	2021-11-25 20:46:08.701	DIGNO	gcdhd434	1750	SIN-IMAGEN	6850.00	8	1
24	2021-11-27 22:16:06.593	DIGNO	ghfgh	1500	SIN-IMAGEN	6850.00	9	1
25	2021-11-27 22:16:06.598	DIGNO	fhfh55	1600	SIN-IMAGEN	6850.00	9	1
26	2021-11-27 22:16:06.603	DIGNO	ghjfh524	1550	SIN-IMAGEN	6850.00	9	1
27	2021-11-27 22:16:06.608	DIGNO	fhdh5424	1800	SIN-IMAGEN	6850.00	9	1
28	2021-12-01 00:42:14.091	DIGNO	sas515	15000	SIN-IMAGEN	6850.00	10	1
\.


--
-- TOC entry 3603 (class 0 OID 24615)
-- Dependencies: 221
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (idfuncionario, fecha_creado, creado_por, nombre, cedula, telefono, direccion, cargo, salario, eliminado) FROM stdin;
1	2022-04-27 17:10:57.128	4-Augusto Montelos	GUILLERMO RODRIGO VEGA AYALA	SD	SD	SD	SD	2500000	f
2	2022-04-27 17:15:48.639	4-Augusto Montelos	VICENTE MARTINEZ GAMARRA	SD	SD	SD	SD	2500000	f
3	2022-04-27 17:16:33.716	4-Augusto Montelos	ROMINNA OLAZAR MENDIETA	SD	SD	SD	SD	2000000	f
4	2022-04-27 17:19:54.265	4-Augusto Montelos	ANGEL RIOS	SD	SD	SD	SD	2500000	f
5	2022-04-27 17:25:55.904	4-Augusto Montelos	DON PAPI	SD	SD	SD	SD	768000	f
\.


--
-- TOC entry 3604 (class 0 OID 24621)
-- Dependencies: 222
-- Data for Name: gasto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gasto (idgasto, fecha_creado, creado_por, fecha, descripcion, monto_gasto, estado, fk_idgasto_tipo, fk_idusuario) FROM stdin;
3	2022-05-03 12:24:39.116	5-Angel Rios 	2022-05-03	001-002-013516	150000	ANULADO	2	5
6	2022-05-03 12:32:24.316	5-Angel Rios 	2022-05-03	005-004-0042134\t	120000	ANULADO	2	5
7	2022-05-03 12:33:15.672	5-Angel Rios 	2022-04-07	005-004-0042134	120000	EMITIDO	2	5
5	2022-05-03 12:26:01.419	5-Angel Rios 	2022-04-15	001-002-05454	150000	ANULADO	2	5
2	2022-04-28 12:40:07.446	4-Augusto Montelos	2022-04-28	NINGUNA	100000	ANULADO	1	4
1	2022-02-17 10:41:18.615	1-Digno Alfredo	2022-02-17	NINGUNA	0	ANULADO	1	1
4	2022-05-03 12:25:30.859	5-Angel Rios 	2022-04-15	001-002-05564645	1500000	ANULADO	2	5
8	2022-05-03 12:34:17.514	5-Angel Rios 	2022-04-11	005-005-0069280	100000	EMITIDO	2	5
9	2022-05-03 12:34:56.434	5-Angel Rios 	2022-04-19	012-004-0013521	100000	EMITIDO	2	5
10	2022-05-03 12:35:23.842	5-Angel Rios 	2022-04-26	033-003-0003863	400000	EMITIDO	2	5
11	2022-05-03 12:37:55.618	5-Angel Rios 	2022-04-07	004-002-0000172	15000	EMITIDO	3	5
12	2022-05-03 12:38:27.522	5-Angel Rios 	2022-04-19	007-002-0253494	22000	EMITIDO	3	5
13	2022-05-03 12:38:54.946	5-Angel Rios 	2022-04-20	007-001-0267270	22000	EMITIDO	3	5
14	2022-05-03 12:39:32.666	5-Angel Rios 	2022-04-26	01184\t	60000	EMITIDO	3	5
15	2022-05-03 12:40:04.282	5-Angel Rios 	2022-04-26	002-039-0002053	25000	EMITIDO	3	5
16	2022-05-03 12:41:28.508	5-Angel Rios 	2022-04-20	001-004-4575607 BANCARD SA COMISION	2499	EMITIDO	8	5
17	2022-05-03 12:42:01.174	5-Angel Rios 	2022-04-27	001-001-0018818	310500	EMITIDO	8	5
18	2022-05-03 12:42:26.266	5-Angel Rios 	2022-04-27	001-001-0018819	448500	EMITIDO	8	5
20	2022-05-03 12:48:02.066	5-Angel Rios 	2022-04-01	001-001-0158601 - EMBUDO PARA VEHICULO \t	42000	EMITIDO	8	5
21	2022-05-06 09:27:57.429	5-Angel Rios 	2022-04-05	001-001-0000502/03/04	12867000	EMITIDO	6	5
22	2022-05-06 09:29:15.7	5-Angel Rios 	2022-05-06	68705 / PAGO DE CUOTA DE MOTOCICLETA CUOTA 21 Y 22 DE 24	988000	ANULADO	6	5
23	2022-05-06 09:30:18.044	5-Angel Rios 	2022-04-11	68705 PAGO DE COUTA DIESA; CUOTA 21/22 DE 24	998000	EMITIDO	6	5
24	2022-05-06 09:31:09.196	5-Angel Rios 	2022-04-26	FACT N 001-011-0001996 PAGO DE SEGURO VEHICULAR 	370000	EMITIDO	6	5
25	2022-05-06 09:33:36.636	5-Angel Rios 	2022-04-05	0556 / PAGO DE ALQUILER 	6210000	EMITIDO	6	5
26	2022-05-06 09:35:27.892	5-Angel Rios 	2022-04-06	500.000 AYUDA CDAP\t\n200.000 PROCESO MERCOFER \n500.000 VIATICO ASUNCION\n411.000 COMBUSTIBLE \n553.212 PAGINA WEB 	2164212	EMITIDO	7	5
27	2022-05-06 09:36:23.991	5-Angel Rios 	2022-04-20	004-011-0026722 ARTICULOS DE OFICINA (AZUCAR, CAFÉ, LEHCE EN POLVO)	85500	EMITIDO	7	5
28	2022-05-06 09:37:01.483	5-Angel Rios 	2022-04-28	004-010-0027270 ARTICULOS DE LIMPIEZA PARA OFICINA \t	174650	EMITIDO	7	5
29	2022-05-06 09:37:48.54	5-Angel Rios 	2022-05-06	001-189-1326827 PAGO FACTURA COPACO 	116105	ANULADO	7	5
30	2022-05-06 09:38:45.028	5-Angel Rios 	2022-04-20	001-189-1326827 PAGO FACTURA COPACO 	116105	EMITIDO	7	5
19	2022-05-03 12:47:03.442	5-Angel Rios 	2022-04-04	005-001-0089861 COMBUSTIBLE - 100.000\n028-001-0007706 COMBUSTIBLE - 510.243\nVARIAS FACTURAS PEAJE - 100.000	710243	ANULADO	4	5
31	2022-06-07 10:17:54.328	5-Angel Rios 	2022-05-01	0003290\t\t20.000\n020-001-0024843 \t22.000\n0002549\t\t20.000\n020-001-0024710\t22.000\n0000996\t\t20.000\n0000342\t\t40.000\n004-002-0000724\t20.000\n	164000	EMITIDO	3	5
32	2022-06-07 10:35:04.126	5-Angel Rios 	2022-05-02	010-004-0000238\t100.000\n003-009-0051339\t150.000\n001-003-0000114\t100.000\n003-009-0052098\t400.000\n003-009-0054070\t100.000	850000	EMITIDO	2	5
33	2022-06-07 10:39:03.991	5-Angel Rios 	2022-05-03	SIN COMP. ALQUILER \t\t6.156.000\n001-009-0148787 TIGO\t\t400.000\n001-011-0001996 SEGURO \t\t370.000\n001-001-0000505 SALARIO\t\t9.900.000\n0089069 DOS CUOTAS DIESA\t\t988.000	17814000	EMITIDO	6	5
34	2022-06-07 10:41:36.241	5-Angel Rios 	2022-05-04	001-001-0010085 RECARGA EXTIN\t110.000\n001-003-0004386 HOSPEDAJE\t\t757.000\n006-027-0002235 CARPETAS \t\t57.000\n	924200	EMITIDO	8	5
35	2022-06-07 10:45:21.023	5-Angel Rios 	2022-05-05	SIN COMPROBANTE \t\t2.968.000\nSIN COMPROBANTE \t\t150.000\n004-010-0037505 ART LIMPIEZA\t145.700\n\n	3263700	EMITIDO	7	5
36	2022-07-13 10:35:08.949	5-Angel Rios 	2022-06-30	CARGA DE COMBUSTIBLE P/ GOL - 345.005\nCARGA DE COMBSUTIBLE YOYI - 100.000\nARTICULOS DE LIMPIEZA - 123.750\n	568775	EMITIDO	7	5
37	2022-07-13 10:38:04.261	5-Angel Rios 	2022-06-30	02/06/22\tREFRINAR\n02/06/22\tPULVIPAR\n06/06/22\tATC\n06/06/22\tFRANCIS\n06/06/22\tFRANCIS\n09/06/22\tPULVIPAR\n15/06/22\tPULVIPAR\n22/06/22\tPULVIPAR 	165000	EMITIDO	3	5
38	2022-07-13 10:40:16.837	5-Angel Rios 	2022-06-30	003-009-0000047\t50.000\n001-004-0001915\t100.000\n003-009-0053255\t100.000\n	250000	EMITIDO	2	5
39	2022-07-13 10:45:22.492	5-Angel Rios 	2022-06-30	001-009-0560664\t205.000\n001-001-0000718\t495.000\n001-068-2230904\t245.820\n001-189-1699691\t115.865\n001-011-0001996\t370.000\n001-001-0000506\t12.867.067\nALQUILER \t\t6.183.000	20531572	EMITIDO	6	5
40	2022-07-13 10:54:24.757	5-Angel Rios 	2022-06-30	006-025-0005963\t102.000 HOJAS OFICIO\n001-001-0002077\t720.000 MAPA DEL MUNDO\n001-001-0000613\t2.500.000 MONICA ESPIN\n001-001-0024677\t160.000 REP. GOL\n001-001-0024674\t2.290.000 REP. GOL\n001-003-0110559\t796.500 CUBIERTAS GOL\n001-001-0024731\t400.000 REP. GOL\n001-001-0078867\t40.000 REC EXTI\n001-001-0019115\t848.700 MANT IMPRESORA\n001-002-0001073\t1.200.000 REP GOL\n001-001-0002261\t1.180.000 CUADROS OFI\n\n	8149200	EMITIDO	8	5
41	2022-08-08 10:16:57.146	5-Angel Rios 	2022-07-30	001-001-0029756\t50.000\n003-009-0004870\t50.271\n005-004-0055067\t100.000\n017-003-0023455\t100.000\n	300271	EMITIDO	2	5
42	2022-08-08 10:23:09.589	5-Angel Rios 	2022-07-30	001-001-0025516\t140.000 IMPRENTA\n006-010-0005960\t139.650 ART OFICINA\n001-001-0003545\t950.000 ARANCEL	1229650	EMITIDO	8	5
43	2022-08-08 10:25:38.558	5-Angel Rios 	2022-07-30	0002767\t20.000\n0004456\t20.000\n0004474\t20.000\n0004599\t20.000\n0002603\t20.000\n0004663\t20.000\n0000818\t20.000\n0000816\t20.000\n	180000	EMITIDO	3	5
44	2022-08-08 10:27:57.132	5-Angel Rios 	2022-07-30	001-189-0871449\t41.147 COPACO\n001-009-0148987\t195.000 ANDE\n710\t\t6.201.000 ALQUILER\n	6437147	ANULADO	6	5
45	2022-08-08 10:30:18.855	5-Angel Rios 	2022-07-30	001-189-0871449\t41.147 COPACO\n001-009-0148987\t195.000 ANDE\n710\t\t6.201.000 ALQUILER\n001-001-0000507\t11.000.000 SALARIO	17437147	EMITIDO	6	5
46	2022-08-08 10:33:39.734	5-Angel Rios 	2022-07-30	DIFE SALARIO\t\t2.672.333\nINFOR MAQUILA\t314.495\nREMEDIOS\t\t18.200\nART OFIC\t\t300.000\n	3305208	EMITIDO	7	5
47	2022-09-15 10:46:12.956	5-Angel Rios 	2022-08-30	004-002-0003760\t20.000\n004-002-0003738\t20.000\n004-002-0003469\t40.000\n002-032-0000474\t30.000\n002-032-0000514\t30.000\n	140000	EMITIDO	3	5
48	2022-09-15 10:47:13.666	5-Angel Rios 	2022-08-30	NINGUNA	341000	EMITIDO	4	5
49	2022-09-15 10:48:18.626	5-Angel Rios 	2022-08-30	NINGUNA	500	ANULADO	2	5
50	2022-09-15 10:49:01.541	5-Angel Rios 	2022-08-30	NINGUNA	550000	EMITIDO	2	5
51	2022-09-15 10:50:31.651	5-Angel Rios 	2022-08-30	NINGUNA	16089720	EMITIDO	6	5
52	2022-09-15 10:51:26.93	5-Angel Rios 	2022-08-30	NINGUNA	4932250	EMITIDO	7	5
53	2023-02-17 09:57:08.076	5-Angel Rios 	2023-01-17	001-002-0026662\t750.000 FLETE\n001-001-0053336\t65.000 TORNILLO Y ANGULO P MESA\n001-001-0053337\t18.000 DESTORNILLADOR P MAQUINA\n002-001-0002812\t45.600 BOLSO DE CINTURA\n007-002-0269453\t22.000 ENCOMIENDA\n007-002-0269825\t22.000 ENCOMIENDA\n003-009-0032498\t40.078 COMBUS\n001-009-02577403\t129.000 INTERNET \n001-001-0014670\t340.000 004 SEG GOL \n001-001-0014670\t370.000 005 SEG GOL \n0003462725\t\t1.300.000 SILLA AUGUSTO\nS/C\t\t6.552.000 ALQUILER \nSALARIO\t\t6.000.000 AUGUSTO \nSALARIO\t\t7.939.567 MATIA FACT \n	23593245	EMITIDO	6	5
55	2023-02-17 10:01:05.915	5-Angel Rios 	2023-01-17	004-003-0009688\t148.000 ART D LIMPIEZA\n00000632\t\t450.000 CARTA PRESENTACION INGLES ROYAL\nVALE\t\t100.000 COMBUSTIBLE YOYI \nVALE\t\t75.000 PARA PLANTAS\nRECIBO \t\t660.000 RICHARD OJEDA GESTIONES MAQUILA 	1433000	EMITIDO	7	5
54	2023-02-17 10:00:30.793	5-Angel Rios 	2023-02-17	004-003-0009688\t148.000 ART D LIMPIEZA\n00000632\t\t450.000 CARTA PRESENTACION INGLES ROYAL\nVALE\t\t100.000 COMBUSTIBLE YOYI \nVALE\t\t75.000 PARA PLANTAS\nRECIBO \t\t660.000 RICHARD OJEDA GESTIONES MAQUILA 	1433000	ANULADO	7	5
\.


--
-- TOC entry 3605 (class 0 OID 24626)
-- Dependencies: 223
-- Data for Name: gasto_tipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gasto_tipo (idgasto_tipo, nombre, activar) FROM stdin;
1	sin definir	t
2	GASTOS EN COMBUSTIBLE 	t
3	GASTOS EN ENCOMIENDA 	t
4	GASTOS DE VIAJES CASUALES	t
5	GASTOS EN ARTICULOS DE OFICINA 	t
6	GASTOS MENSUALES	t
7	GASTOS SIN COMPROBANTES 	t
8	GASTOS CASUALES	t
\.


--
-- TOC entry 3606 (class 0 OID 24631)
-- Dependencies: 224
-- Data for Name: grupo_credito_tercero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupo_credito_tercero (idgrupo_credito_tercero, fecha_inicio, fecha_fin, estado, fk_idtercero) FROM stdin;
1	2022-01-18 14:18:24.44	2022-01-18 14:18:24.44	ABIERTO	1
3	2022-01-18 14:18:30.491	2022-01-18 14:18:30.491	ABIERTO	3
4	2022-01-18 14:18:33.262	2022-01-18 14:18:33.262	ABIERTO	4
5	2022-01-18 14:18:35.937	2022-01-18 14:18:35.937	ABIERTO	5
6	2022-01-18 14:18:39.158	2022-01-18 14:18:39.158	ABIERTO	6
7	2022-01-18 14:18:42.437	2022-01-18 14:18:42.437	ABIERTO	7
8	2022-01-18 14:18:45.115	2022-01-18 14:18:45.115	ABIERTO	8
9	2022-01-18 14:18:48.021	2022-01-18 14:18:48.021	ABIERTO	9
10	2022-01-18 14:18:50.757	2022-01-18 14:18:50.757	ABIERTO	10
11	2022-01-18 14:18:53.397	2022-01-18 14:18:53.397	ABIERTO	11
12	2022-01-18 14:18:56.065	2022-01-18 14:18:56.065	ABIERTO	12
13	2022-01-18 14:18:59.363	2022-01-18 14:18:59.363	ABIERTO	13
14	2022-01-18 14:19:02.655	2022-01-18 14:19:02.655	ABIERTO	14
15	2022-01-18 14:19:05.625	2022-01-18 14:19:05.625	ABIERTO	15
16	2022-01-18 14:19:08.314	2022-01-18 14:19:08.314	ABIERTO	16
17	2022-01-18 14:19:12.081	2022-01-18 14:19:12.081	ABIERTO	17
18	2022-01-18 14:19:15.418	2022-01-18 14:19:15.418	ABIERTO	18
19	2022-01-18 14:19:18.215	2022-01-18 14:19:18.215	ABIERTO	19
20	2022-01-18 14:19:21.551	2022-01-18 14:19:21.551	ABIERTO	20
21	2022-02-17 13:28:02.224	2022-02-17 13:28:02.224	ABIERTO	21
2	2022-01-18 14:18:27.545	2022-02-17 13:41:59.251	CERRADO	2
22	2022-02-17 13:41:59.278	2022-02-17 13:41:59.278	ABIERTO	2
23	2022-04-27 12:42:14.006	2022-04-27 12:42:14.006	ABIERTO	22
24	2022-04-27 12:49:49.743	2022-04-27 12:49:49.743	ABIERTO	23
25	2022-04-27 13:57:32.072	2022-04-27 13:57:32.072	ABIERTO	24
26	2022-04-27 15:19:00.848	2022-04-27 15:19:00.848	ABIERTO	25
27	2022-04-27 15:20:01.628	2022-04-27 15:20:01.628	ABIERTO	26
28	2022-04-28 13:49:27.128	2022-04-28 13:49:27.128	ABIERTO	27
29	2022-05-03 15:33:54.57	2022-05-03 15:33:54.57	ABIERTO	28
30	2022-05-04 12:45:08.856	2022-05-04 12:45:08.856	ABIERTO	29
31	2022-05-06 11:38:41.637	2022-05-06 11:38:41.637	ABIERTO	30
32	2022-05-06 12:14:08.109	2022-05-06 12:14:08.109	ABIERTO	31
33	2022-05-06 12:14:45.941	2022-05-06 12:14:45.941	ABIERTO	32
34	2022-05-06 13:59:34.352	2022-05-06 13:59:34.353	ABIERTO	33
35	2022-05-06 14:00:05.942	2022-05-06 14:00:05.942	ABIERTO	34
36	2022-05-06 14:01:02.773	2022-05-06 14:01:02.773	ABIERTO	35
37	2022-05-10 15:31:57.62	2022-05-10 15:31:57.62	ABIERTO	36
38	2022-05-12 14:35:15.922	2022-05-12 14:35:15.922	ABIERTO	37
39	2022-05-17 11:56:27.837	2022-05-17 11:56:27.837	ABIERTO	38
40	2022-05-18 09:16:17.146	2022-05-18 09:16:17.146	ABIERTO	39
41	2022-05-18 09:40:39.18	2022-05-18 09:40:39.18	ABIERTO	40
42	2022-05-24 09:38:20.418	2022-05-24 09:38:20.418	ABIERTO	41
43	2022-05-24 11:51:22.625	2022-05-24 11:51:22.625	ABIERTO	42
44	2022-05-26 10:53:05.463	2022-05-26 10:53:05.463	ABIERTO	43
45	2022-05-26 11:31:43.584	2022-05-26 11:31:43.584	ABIERTO	44
46	2022-05-26 12:17:20.385	2022-05-26 12:17:20.385	ABIERTO	45
47	2022-05-26 12:17:59.248	2022-05-26 12:17:59.248	ABIERTO	46
48	2022-05-30 10:32:42.846	2022-05-30 10:32:42.846	ABIERTO	47
49	2022-05-31 09:30:52.858	2022-05-31 09:30:52.858	ABIERTO	48
50	2022-05-31 10:32:55.233	2022-05-31 10:32:55.233	ABIERTO	49
51	2022-06-02 14:22:43.571	2022-06-02 14:22:43.571	ABIERTO	50
52	2022-06-02 14:24:23.085	2022-06-02 14:24:23.085	ABIERTO	51
53	2022-06-02 16:31:37.072	2022-06-02 16:31:37.072	ABIERTO	52
54	2022-06-06 08:47:56.729	2022-06-06 08:47:56.729	ABIERTO	53
55	2022-06-06 16:02:59.757	2022-06-06 16:02:59.757	ABIERTO	54
56	2022-06-08 14:25:43.554	2022-06-08 14:25:43.554	ABIERTO	55
57	2022-06-08 15:59:50.713	2022-06-08 15:59:50.713	ABIERTO	56
58	2022-06-08 16:00:25.502	2022-06-08 16:00:25.502	ABIERTO	57
59	2022-06-08 16:48:47.268	2022-06-08 16:48:47.268	ABIERTO	58
60	2022-06-14 13:01:59.763	2022-06-14 13:01:59.763	ABIERTO	59
61	2022-06-15 13:35:54.338	2022-06-15 13:35:54.338	ABIERTO	60
62	2022-06-17 09:19:44.852	2022-06-17 09:19:44.852	ABIERTO	61
63	2022-06-17 09:20:31.214	2022-06-17 09:20:31.214	ABIERTO	62
64	2022-06-20 14:10:09.528	2022-06-20 14:10:09.528	ABIERTO	63
65	2022-06-29 09:37:38.789	2022-06-29 09:37:38.789	ABIERTO	64
66	2022-06-29 12:59:54.719	2022-06-29 12:59:54.719	ABIERTO	65
67	2022-07-04 14:14:56.292	2022-07-04 14:14:56.292	ABIERTO	66
68	2022-07-04 14:54:04.548	2022-07-04 14:54:04.548	ABIERTO	67
69	2022-07-05 14:45:10.813	2022-07-05 14:45:10.813	ABIERTO	68
70	2022-07-05 14:45:44.717	2022-07-05 14:45:44.717	ABIERTO	69
71	2022-07-06 12:35:32.124	2022-07-06 12:35:32.124	ABIERTO	70
72	2022-07-06 13:32:41.562	2022-07-06 13:32:41.562	ABIERTO	71
73	2022-07-06 14:17:24.188	2022-07-06 14:17:24.188	ABIERTO	72
74	2022-07-06 15:26:51.858	2022-07-06 15:26:51.858	ABIERTO	73
75	2022-07-08 11:44:10.604	2022-07-08 11:44:10.604	ABIERTO	74
76	2022-07-08 15:56:15.75	2022-07-08 15:56:15.75	ABIERTO	75
77	2022-07-13 10:14:53.766	2022-07-13 10:14:53.766	ABIERTO	76
78	2022-07-13 14:48:31.368	2022-07-13 14:48:31.368	ABIERTO	77
79	2022-07-14 12:08:01.556	2022-07-14 12:08:01.556	ABIERTO	78
80	2022-07-19 12:47:35.682	2022-07-19 12:47:35.682	ABIERTO	79
81	2022-07-29 15:12:22.865	2022-07-29 15:12:22.865	ABIERTO	80
82	2022-08-01 15:38:21.404	2022-08-01 15:38:21.404	ABIERTO	81
83	2022-08-05 14:34:52.36	2022-08-05 14:34:52.36	ABIERTO	82
84	2022-08-09 08:49:49.419	2022-08-09 08:49:49.419	ABIERTO	83
85	2022-08-24 09:24:59.159	2022-08-24 09:24:59.159	ABIERTO	84
86	2022-08-26 16:34:03.615	2022-08-26 16:34:03.615	ABIERTO	85
87	2022-09-06 13:59:12.124	2022-09-06 13:59:12.124	ABIERTO	86
88	2022-09-07 16:06:02.696	2022-09-07 16:06:02.696	ABIERTO	87
89	2022-09-07 16:25:44.1	2022-09-07 16:25:44.1	ABIERTO	88
90	2022-09-12 11:01:43.903	2022-09-12 11:01:43.903	ABIERTO	89
91	2022-09-12 15:13:51.77	2022-09-12 15:13:51.77	ABIERTO	90
92	2022-09-29 10:58:24.263	2022-09-29 10:58:24.263	ABIERTO	91
93	2022-10-05 11:29:12.171	2022-10-05 11:29:12.171	ABIERTO	92
94	2022-10-10 12:39:42.249	2022-10-10 12:39:42.249	ABIERTO	93
95	2022-10-11 11:23:16.368	2022-10-11 11:23:16.368	ABIERTO	94
96	2022-10-11 11:23:51.981	2022-10-11 11:23:51.981	ABIERTO	95
97	2022-10-17 10:28:28.06	2022-10-17 10:28:28.06	ABIERTO	96
98	2022-10-17 10:29:12.658	2022-10-17 10:29:12.658	ABIERTO	97
99	2022-10-24 10:46:11.415	2022-10-24 10:46:11.416	ABIERTO	98
100	2022-10-28 15:31:19.014	2022-10-28 15:31:19.014	ABIERTO	99
101	2022-10-28 15:32:12.787	2022-10-28 15:32:12.787	ABIERTO	100
102	2022-11-01 08:35:50.455	2022-11-01 08:35:50.455	ABIERTO	101
103	2022-11-01 08:47:34.333	2022-11-01 08:47:34.333	ABIERTO	102
104	2022-11-10 08:42:26.66	2022-11-10 08:42:26.66	ABIERTO	103
105	2022-11-11 12:59:07.526	2022-11-11 12:59:07.526	ABIERTO	104
106	2022-11-25 12:57:23.936	2022-11-25 12:57:23.936	ABIERTO	105
107	2022-11-29 12:08:12.709	2022-11-29 12:08:12.709	ABIERTO	106
108	2022-11-29 12:09:00.241	2022-11-29 12:09:00.241	ABIERTO	107
109	2022-12-05 11:02:48.252	2022-12-05 11:02:48.252	ABIERTO	108
110	2022-12-12 14:06:28.083	2022-12-12 14:06:28.083	ABIERTO	109
111	2022-12-12 14:07:05.497	2022-12-12 14:07:05.497	ABIERTO	110
112	2022-12-13 09:43:25.046	2022-12-13 09:43:25.046	ABIERTO	111
113	2022-12-13 09:43:48.252	2022-12-13 09:43:48.252	ABIERTO	112
114	2022-12-14 13:11:02.061	2022-12-14 13:11:02.061	ABIERTO	113
115	2023-01-03 14:08:24.604	2023-01-03 14:08:24.604	ABIERTO	114
116	2023-01-04 14:11:16.707	2023-01-04 14:11:16.707	ABIERTO	115
117	2023-01-18 10:56:31.94	2023-01-18 10:56:31.94	ABIERTO	116
118	2023-01-18 14:53:34.513	2023-01-18 14:53:34.513	ABIERTO	117
119	2023-01-24 14:29:58.949	2023-01-24 14:29:58.949	ABIERTO	118
120	2023-01-26 11:45:17.229	2023-01-26 11:45:17.229	ABIERTO	119
121	2023-02-08 11:41:25.956	2023-02-08 11:41:25.956	ABIERTO	120
122	2023-02-08 15:09:20.676	2023-02-08 15:09:20.676	ABIERTO	121
123	2023-02-14 13:26:55.33	2023-02-14 13:26:55.33	ABIERTO	122
124	2023-02-16 14:04:04.593	2023-02-16 14:04:04.593	ABIERTO	123
\.


--
-- TOC entry 3607 (class 0 OID 24636)
-- Dependencies: 225
-- Data for Name: honorario_despacho; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.honorario_despacho (idhonorario_despacho, fecha_creado, monto, eliminado) FROM stdin;
1	2021-11-18 00:13:21.099	2085000	f
2	2021-12-06 11:41:04.375	5000000	f
\.


--
-- TOC entry 3608 (class 0 OID 24640)
-- Dependencies: 226
-- Data for Name: incoterms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.incoterms (idincoterms, nombre, sigla, descripcion, eliminado) FROM stdin;
1	SIN DEFINIR	SIN	ninguna	f
2	Afuera de fábrica o de almacén	EXW	Este incoterm establece que el vendedor ha cumplido su \nobligación de entrega al poner los productos \nafuera de su taller, fábrica o sitio de almacenamiento\n para que los retire el comprador.	f
3	Free Carrier	FCA	El vendedor debe transportar la mercancía en las condiciones óptimas para la exportación en un punto acordado y se debe encargar de toda la gestión y costes del despacho de aduanas .	f
4	Carriage Paid To	CPT	En español se podría traducir como “Mercancía pagada hasta”, el vendedor será responsable de la mercancía hasta el almacén del transportista pero pagará los costes de transporte hasta el punto de destino dónde se encuentra el comprador.	f
5	Carriage and Insurance Paid To	CIP	el vendedor es responsable de la mercancía hasta llegar a la empresa de transporte	f
6	Delivered at Terminal	DAT	el vendedor es responsable de la carga y paga sus costes hasta el país de destino.	f
7	Delivered at Place	DAP	El DAP incoterm se puede traducir como “Entregado en el lugar” y es un DAT pero, que en vez de que el punto de entrega acordado sea una terminal o almacén, es cualquier otro punto acordado dentro del país de destino con el comprador.	f
8	Delivery Duty Paid	DDP	el vendedor se encarga y paga absolutamente todo	f
9	Free Alongside Ship	FAS	Este Incoterm se podría traducir como “Gratis hasta estar al lado del barco” el vendedor es responsable de dejar la mercancía en el muelle junto al buque.\n\n	f
10	Free on Board	FOB	El FOB o como sería con la traducción “Gratis hasta estar a bordo del buque” significa claramente eso mismo, si anteriormente en un FAS el vendedor dejaba la carga junto al buque, en un FOB es directamente hasta el buque.	f
11	Cost and Freight	CFR 	El CFR se puede traducir como “Coste y transporte” y es exactamente eso, el vendedor se encarga de la mercancía hasta el puerto de destino que el comprador quiera.	f
12	Cost Insurance and Freight	CIF	Este incoterm se puede traducir directamente como “Costas de seguro y transporte”.	f
13	-\t	DDU	-	f
14	-	-	-	f
\.


--
-- TOC entry 3609 (class 0 OID 24646)
-- Dependencies: 227
-- Data for Name: item_comprobante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_comprobante (iditem_comprobante, fecha_creado, creado_por, descripcion, monto, referencia, imagen, fk_idliquidacion_proforma, fk_idtipo_comprobante, tipo_comprobante) FROM stdin;
1	2021-11-22 22:47:07.679	DIGNO	IMPUESTO-(2)	52000	SIN-REF.	SIN-IMAGEN	1	2	CON_COMPROBANTE
2	2021-11-22 22:47:07.684	DIGNO	IVA DESPACHANTE-(3)	42000	SIN-REF.	SIN-IMAGEN	1	3	CON_COMPROBANTE
3	2021-11-22 22:47:07.687	DIGNO	MOTOKEIRO-(15)	80000	SIN-REF.	SIN-IMAGEN	1	15	SIN_COMPROBANTE
4	2021-11-22 22:47:07.69	DIGNO	GASTOS SECCIONES-(16)	82000	SIN-REF.	SIN-IMAGEN	1	16	SIN_COMPROBANTE
5	2021-11-22 22:47:07.692	DIGNO	PREDECLARACION-(24)	60000	SIN-REF.	SIN-IMAGEN	1	24	BOLETA_DESPACHO
6	2021-11-22 22:47:07.696	DIGNO	PERMISOS-(25)	62000	SIN-REF.	SIN-IMAGEN	1	25	BOLETA_DESPACHO
7	2021-11-22 22:47:07.699	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	1	31	MERCADERIA
8	2021-11-23 21:54:56.228	DIGNO	IMPUESTO-(2)	10000	SIN-REF.	SIN-IMAGEN	2	2	CON_COMPROBANTE
9	2021-11-23 21:54:56.232	DIGNO	IVA DESPACHANTE-(3)	10000	SIN-REF.	SIN-IMAGEN	2	3	CON_COMPROBANTE
10	2021-11-23 21:54:56.236	DIGNO	CANON INFORMATICO-(4)	10000	SIN-REF.	SIN-IMAGEN	2	4	CON_COMPROBANTE
11	2021-11-23 21:54:56.239	DIGNO	REAJUSTES-(5)	10000	SIN-REF.	SIN-IMAGEN	2	5	CON_COMPROBANTE
12	2021-11-23 21:54:56.242	DIGNO	MOTOKEIRO-(15)	10000	SIN-REF.	SIN-IMAGEN	2	15	SIN_COMPROBANTE
13	2021-11-23 21:54:56.245	DIGNO	GASTOS SECCIONES-(16)	10000	SIN-REF.	SIN-IMAGEN	2	16	SIN_COMPROBANTE
14	2021-11-23 21:54:56.248	DIGNO	HONORARIOS AUXILIAR-(17)	10000	SIN-REF.	SIN-IMAGEN	2	17	SIN_COMPROBANTE
15	2021-11-23 21:54:56.252	DIGNO	CARTA DE COMPROMISO-(18)	10000	SIN-REF.	SIN-IMAGEN	2	18	SIN_COMPROBANTE
16	2021-11-23 21:54:56.255	DIGNO	PREDECLARACION-(24)	10000	SIN-REF.	SIN-IMAGEN	2	24	BOLETA_DESPACHO
17	2021-11-23 21:54:56.258	DIGNO	PERMISOS-(25)	10000	SIN-REF.	SIN-IMAGEN	2	25	BOLETA_DESPACHO
18	2021-11-23 21:54:56.261	DIGNO	DESPACHANTE-(26)	10000	SIN-REF.	SIN-IMAGEN	2	26	BOLETA_DESPACHO
19	2021-11-23 21:54:56.263	DIGNO	COMISION-(27)	10000	SIN-REF.	SIN-IMAGEN	2	27	BOLETA_DESPACHO
20	2021-11-23 21:54:56.265	DIGNO	SIN DEFINIR-(1)	0	SIN-REF	SIN-IMAGEN	2	1	MERCADERIA
21	2021-11-23 21:54:56.269	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	2	31	MERCADERIA
22	2021-11-23 23:55:09.191	DIGNO	IMPUESTO-(2)	10000	SIN-REF.	SIN-IMAGEN	3	2	CON_COMPROBANTE
23	2021-11-23 23:55:09.194	DIGNO	MOTOKEIRO-(15)	10000	SIN-REF.	SIN-IMAGEN	3	15	SIN_COMPROBANTE
24	2021-11-23 23:55:09.197	DIGNO	PREDECLARACION-(24)	10000	SIN-REF.	SIN-IMAGEN	3	24	BOLETA_DESPACHO
25	2021-11-23 23:55:09.2	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	3	31	MERCADERIA
26	2021-11-24 22:15:40.478	DIGNO	IVA DESPACHANTE-(3)	22000	SIN-REF.	SIN-IMAGEN	4	3	CON_COMPROBANTE
27	2021-11-24 22:15:40.481	DIGNO	MOTOKEIRO-(15)	20000	SIN-REF.	SIN-IMAGEN	4	15	SIN_COMPROBANTE
28	2021-11-24 22:15:40.483	DIGNO	PREDECLARACION-(24)	15000	SIN-REF.	SIN-IMAGEN	4	24	BOLETA_DESPACHO
29	2021-11-24 22:15:40.485	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	4	31	MERCADERIA
30	2021-11-24 23:01:55.768	DIGNO	IMPUESTO-(2)	11000	SIN-REF.	SIN-IMAGEN	5	2	CON_COMPROBANTE
31	2021-11-24 23:01:55.773	DIGNO	MOTOKEIRO-(15)	12000	SIN-REF.	SIN-IMAGEN	5	15	SIN_COMPROBANTE
32	2021-11-24 23:01:55.776	DIGNO	PREDECLARACION-(24)	13000	SIN-REF.	SIN-IMAGEN	5	24	BOLETA_DESPACHO
33	2021-11-24 23:01:55.779	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	5	31	MERCADERIA
34	2021-11-25 00:21:16.468	DIGNO	CANON INFORMATICO-(4)	220000	SIN-REF.	SIN-IMAGEN	6	4	CON_COMPROBANTE
35	2021-11-25 00:21:16.468	DIGNO	GASTOS SECCIONES-(16)	150000	SIN-REF.	SIN-IMAGEN	6	16	SIN_COMPROBANTE
36	2021-11-25 00:21:16.478	DIGNO	PERMISOS-(25)	95000	SIN-REF.	SIN-IMAGEN	6	25	BOLETA_DESPACHO
37	2021-11-25 00:21:16.478	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	6	31	MERCADERIA
38	2021-11-25 20:37:40.694	DIGNO	IMPUESTO-(2)	11000	SIN-REF.	SIN-IMAGEN	7	2	CON_COMPROBANTE
39	2021-11-25 20:37:40.698	DIGNO	GASTOS SECCIONES-(16)	50000	SIN-REF.	SIN-IMAGEN	7	16	SIN_COMPROBANTE
40	2021-11-25 20:37:40.701	DIGNO	PREDECLARACION-(24)	20000	SIN-REF.	SIN-IMAGEN	7	24	BOLETA_DESPACHO
41	2021-11-25 20:37:40.705	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	7	31	MERCADERIA
42	2021-11-25 20:46:08.506	DIGNO	IMPUESTO-(2)	10000	SIN-REF.	SIN-IMAGEN	8	2	CON_COMPROBANTE
43	2021-11-25 20:46:08.508	DIGNO	IVA DESPACHANTE-(3)	10000	SIN-REF.	SIN-IMAGEN	8	3	CON_COMPROBANTE
44	2021-11-25 20:46:08.51	DIGNO	CANON INFORMATICO-(4)	10000	SIN-REF.	SIN-IMAGEN	8	4	CON_COMPROBANTE
45	2021-11-25 20:46:08.516	DIGNO	REAJUSTES-(5)	10000	SIN-REF.	SIN-IMAGEN	8	5	CON_COMPROBANTE
46	2021-11-25 20:46:08.519	DIGNO	TASA AERO-PORTUARIA-(6)	10000	SIN-REF.	SIN-IMAGEN	8	6	CON_COMPROBANTE
47	2021-11-25 20:46:08.526	DIGNO	VISACION-(7)	10000	SIN-REF.	SIN-IMAGEN	8	7	CON_COMPROBANTE
48	2021-11-25 20:46:08.53	DIGNO	PERMISO MUNICIPAL-(8)	10000	SIN-REF.	SIN-IMAGEN	8	8	CON_COMPROBANTE
49	2021-11-25 20:46:08.534	DIGNO	APERTURA-(9)	10000	SIN-REF.	SIN-IMAGEN	8	9	CON_COMPROBANTE
50	2021-11-25 20:46:08.536	DIGNO	TRASLADO-(10)	10000	SIN-REF.	SIN-IMAGEN	8	10	CON_COMPROBANTE
51	2021-11-25 20:46:08.538	DIGNO	PERMISO MIC-(11)	10000	SIN-REF.	SIN-IMAGEN	8	11	CON_COMPROBANTE
52	2021-11-25 20:46:08.54	DIGNO	CANJE GUIA AEREA-(12)	10000	SIN-REF.	SIN-IMAGEN	8	12	CON_COMPROBANTE
53	2021-11-25 20:46:08.543	DIGNO	FOTOCOPIAS-(13)	10000	SIN-REF.	SIN-IMAGEN	8	13	CON_COMPROBANTE
54	2021-11-25 20:46:08.551	DIGNO	FLETE-(14)	10000	SIN-REF.	SIN-IMAGEN	8	14	CON_COMPROBANTE
55	2021-11-25 20:46:08.556	DIGNO	MOTOKEIRO-(15)	20000	SIN-REF.	SIN-IMAGEN	8	15	SIN_COMPROBANTE
56	2021-11-25 20:46:08.556	DIGNO	GASTOS SECCIONES-(16)	10000	SIN-REF.	SIN-IMAGEN	8	16	SIN_COMPROBANTE
57	2021-11-25 20:46:08.561	DIGNO	HONORARIOS AUXILIAR-(17)	20000	SIN-REF.	SIN-IMAGEN	8	17	SIN_COMPROBANTE
58	2021-11-25 20:46:08.561	DIGNO	CARTA DE COMPROMISO-(18)	20000	SIN-REF.	SIN-IMAGEN	8	18	SIN_COMPROBANTE
59	2021-11-25 20:46:08.566	DIGNO	AGILIZACION-(19)	15000	SIN-REF.	SIN-IMAGEN	8	19	SIN_COMPROBANTE
60	2021-11-25 20:46:08.571	DIGNO	DESPACHANTE-(20)	15000	SIN-REF.	SIN-IMAGEN	8	20	SIN_COMPROBANTE
61	2021-11-25 20:46:08.571	DIGNO	ALMUERZO-(21)	20000	SIN-REF.	SIN-IMAGEN	8	21	SIN_COMPROBANTE
62	2021-11-25 20:46:08.571	DIGNO	IMPORTADOR-(22)	25000	SIN-REF.	SIN-IMAGEN	8	22	SIN_COMPROBANTE
63	2021-11-25 20:46:08.576	DIGNO	TOTAL GASTOS SECCIONES-(23)	15000	SIN-REF.	SIN-IMAGEN	8	23	SIN_COMPROBANTE
64	2021-11-25 20:46:08.611	DIGNO	PREDECLARACION-(24)	20000	SIN-REF.	SIN-IMAGEN	8	24	BOLETA_DESPACHO
65	2021-11-25 20:46:08.611	DIGNO	PERMISOS-(25)	20000	SIN-REF.	SIN-IMAGEN	8	25	BOLETA_DESPACHO
66	2021-11-25 20:46:08.616	DIGNO	DESPACHANTE-(26)	20000	SIN-REF.	SIN-IMAGEN	8	26	BOLETA_DESPACHO
67	2021-11-25 20:46:08.616	DIGNO	COMISION-(27)	22000	SIN-REF.	SIN-IMAGEN	8	27	BOLETA_DESPACHO
68	2021-11-25 20:46:08.621	DIGNO	GASTOS VARIOS-(28)	21000	SIN-REF.	SIN-IMAGEN	8	28	BOLETA_DESPACHO
69	2021-11-25 20:46:08.656	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	8	31	MERCADERIA
70	2021-11-25 20:46:08.677	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	8	31	MERCADERIA
71	2021-11-25 20:46:08.682	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	8	31	MERCADERIA
72	2021-11-25 20:46:08.682	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	8	31	MERCADERIA
73	2021-11-25 20:46:08.687	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	8	31	MERCADERIA
74	2021-11-27 22:16:06.491	DIGNO	IMPUESTO-(2)	10000	SIN-REF.	SIN-IMAGEN	9	2	CON_COMPROBANTE
75	2021-11-27 22:16:06.524	DIGNO	IVA DESPACHANTE-(3)	10000	SIN-REF.	SIN-IMAGEN	9	3	CON_COMPROBANTE
76	2021-11-27 22:16:06.524	DIGNO	CANON INFORMATICO-(4)	10000	SIN-REF.	SIN-IMAGEN	9	4	CON_COMPROBANTE
77	2021-11-27 22:16:06.524	DIGNO	REAJUSTES-(5)	10000	SIN-REF.	SIN-IMAGEN	9	5	CON_COMPROBANTE
78	2021-11-27 22:16:06.533	DIGNO	TASA AERO-PORTUARIA-(6)	10000	SIN-REF.	SIN-IMAGEN	9	6	CON_COMPROBANTE
79	2021-11-27 22:16:06.533	DIGNO	VISACION-(7)	10000	SIN-REF.	SIN-IMAGEN	9	7	CON_COMPROBANTE
80	2021-11-27 22:16:06.533	DIGNO	PERMISO MUNICIPAL-(8)	10000	SIN-REF.	SIN-IMAGEN	9	8	CON_COMPROBANTE
81	2021-11-27 22:16:06.538	DIGNO	APERTURA-(9)	10000	SIN-REF.	SIN-IMAGEN	9	9	CON_COMPROBANTE
82	2021-11-27 22:16:06.538	DIGNO	TRASLADO-(10)	10000	SIN-REF.	SIN-IMAGEN	9	10	CON_COMPROBANTE
83	2021-11-27 22:16:06.543	DIGNO	PERMISO MIC-(11)	10000	SIN-REF.	SIN-IMAGEN	9	11	CON_COMPROBANTE
84	2021-11-27 22:16:06.543	DIGNO	CANJE GUIA AEREA-(12)	10000	SIN-REF.	SIN-IMAGEN	9	12	CON_COMPROBANTE
85	2021-11-27 22:16:06.548	DIGNO	FOTOCOPIAS-(13)	10000	SIN-REF.	SIN-IMAGEN	9	13	CON_COMPROBANTE
86	2021-11-27 22:16:06.548	DIGNO	FLETE-(14)	10000	SIN-REF.	SIN-IMAGEN	9	14	CON_COMPROBANTE
87	2021-11-27 22:16:06.553	DIGNO	MOTOKEIRO-(15)	20000	SIN-REF.	SIN-IMAGEN	9	15	SIN_COMPROBANTE
88	2021-11-27 22:16:06.553	DIGNO	GASTOS SECCIONES-(16)	10000	SIN-REF.	SIN-IMAGEN	9	16	SIN_COMPROBANTE
89	2021-11-27 22:16:06.558	DIGNO	HONORARIOS AUXILIAR-(17)	20000	SIN-REF.	SIN-IMAGEN	9	17	SIN_COMPROBANTE
90	2021-11-27 22:16:06.558	DIGNO	CARTA DE COMPROMISO-(18)	20000	SIN-REF.	SIN-IMAGEN	9	18	SIN_COMPROBANTE
91	2021-11-27 22:16:06.558	DIGNO	AGILIZACION-(19)	15000	SIN-REF.	SIN-IMAGEN	9	19	SIN_COMPROBANTE
92	2021-11-27 22:16:06.563	DIGNO	DESPACHANTE-(20)	15000	SIN-REF.	SIN-IMAGEN	9	20	SIN_COMPROBANTE
93	2021-11-27 22:16:06.563	DIGNO	ALMUERZO-(21)	20000	SIN-REF.	SIN-IMAGEN	9	21	SIN_COMPROBANTE
94	2021-11-27 22:16:06.573	DIGNO	PREDECLARACION-(24)	20000	SIN-REF.	SIN-IMAGEN	9	24	BOLETA_DESPACHO
95	2021-11-27 22:16:06.573	DIGNO	PERMISOS-(25)	20000	SIN-REF.	SIN-IMAGEN	9	25	BOLETA_DESPACHO
96	2021-11-27 22:16:06.578	DIGNO	DESPACHANTE-(26)	20000	SIN-REF.	SIN-IMAGEN	9	26	BOLETA_DESPACHO
97	2021-11-27 22:16:06.578	DIGNO	COMISION-(27)	22000	SIN-REF.	SIN-IMAGEN	9	27	BOLETA_DESPACHO
98	2021-11-27 22:16:06.583	DIGNO	GASTOS VARIOS-(28)	21000	SIN-REF.	SIN-IMAGEN	9	28	BOLETA_DESPACHO
99	2021-11-27 22:16:06.588	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	9	31	MERCADERIA
100	2021-11-27 22:16:06.588	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	9	31	MERCADERIA
101	2021-11-27 22:16:06.593	DIGNO	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	9	31	MERCADERIA
102	2021-12-01 00:42:14.044	1-Digno Alfredo	IVA DESPACHANTE-(3)	15000	SIN-REF.	SIN-IMAGEN	10	3	CON_COMPROBANTE
103	2021-12-01 00:42:14.06	1-Digno Alfredo	MOTOKEIRO-(15)	15000	SIN-REF.	SIN-IMAGEN	10	15	SIN_COMPROBANTE
104	2021-12-01 00:42:14.076	1-Digno Alfredo	PERMISOS-(25)	15000	SIN-REF.	SIN-IMAGEN	10	25	BOLETA_DESPACHO
105	2021-12-01 00:42:14.076	1-Digno Alfredo	INSUMO AGRICOLA-(31)	0	SIN-REF	SIN-IMAGEN	10	31	MERCADERIA
\.


--
-- TOC entry 3610 (class 0 OID 24651)
-- Dependencies: 228
-- Data for Name: item_liquidacion_final; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_liquidacion_final (iditem_liquidacion_final, fecha_creado, creado_por, orden, descripcion, comprobante_nro, total_guarani, sin_iva, solo_iva, fk_idliquidacion_final, fk_idcomprobante_liquidacion) FROM stdin;
1	2022-01-18 16:52:17.309	3-ADMINISTRADOR	1	SERVICIO DE VALORACION	21002IC04027163K	256693	0	0	1	34
2	2022-01-18 16:52:17.317	3-ADMINISTRADOR	2	IRE GENERAL 700	21002IC04027163K	207607	0	0	1	36
3	2022-02-04 16:33:39.911	1-Digno Alfredo	1	DERECHO ADUANERO	32423sdfs	0	0	0	2	2
4	2022-02-04 16:33:39.916	1-Digno Alfredo	2	INDI	32423sdfs	0	0	0	2	3
5	2022-02-04 16:33:39.932	1-Digno Alfredo	3	I.S.C.	32423sdfs	0	0	0	2	4
6	2022-02-04 16:33:39.994	1-Digno Alfredo	4	SERVICIO DE VALORACION	32423sdfs	0	0	0	2	5
7	2022-02-04 16:33:40.01	1-Digno Alfredo	5	MULTAS VARIAS	32423sdfs	0	0	0	2	6
8	2022-02-04 16:33:40.01	1-Digno Alfredo	6	IRACIS GENERAL 700	32423sdfs	0	0	0	2	7
9	2022-02-04 16:33:40.025	1-Digno Alfredo	7	I.V.A.	32423sdfs	0	0	0	2	8
10	2022-02-04 16:33:40.025	1-Digno Alfredo	8	CANON INFORMATICO	x	0	0	0	2	9
11	2022-02-04 16:33:40.041	1-Digno Alfredo	9	CDAP/ANNP	x	0	0	0	2	10
12	2022-02-04 16:33:40.041	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	x	0	0	0	2	11
13	2022-02-04 16:33:40.057	1-Digno Alfredo	11	FOTOCOPIAS	x	0	0	0	2	12
14	2022-02-04 16:33:40.057	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	2	13
15	2022-02-04 16:33:40.072	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	2	14
16	2022-02-04 16:33:40.072	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	2	15
17	2022-02-04 16:33:40.088	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	500000	454545	45454	2	16
18	2022-02-04 16:33:40.088	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	2	17
19	2022-02-04 16:33:40.104	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	2	18
20	2022-02-04 16:33:40.119	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	2	19
21	2022-02-04 16:33:40.119	1-Digno Alfredo	19	OTROS	x	0	0	0	2	20
22	2022-02-17 12:03:18.316	1-Digno Alfredo	1	DERECHO ADUANERO	6467456	0	0	0	3	2
23	2022-02-17 12:03:18.324	1-Digno Alfredo	2	INDI	6467456	0	0	0	3	3
24	2022-02-17 12:03:18.33	1-Digno Alfredo	3	I.S.C.	6467456	0	0	0	3	4
25	2022-02-17 12:03:18.339	1-Digno Alfredo	4	SERVICIO DE VALORACION	6467456	0	0	0	3	5
26	2022-02-17 12:03:18.35	1-Digno Alfredo	5	MULTAS VARIAS	6467456	0	0	0	3	6
27	2022-02-17 12:03:18.356	1-Digno Alfredo	6	IRACIS GENERAL 700	6467456	0	0	0	3	7
28	2022-02-17 12:03:18.363	1-Digno Alfredo	7	I.V.A.	6467456	0	0	0	3	8
29	2022-02-17 12:03:18.369	1-Digno Alfredo	8	CANON INFORMATICO	x	0	0	0	3	9
30	2022-02-17 12:03:18.379	1-Digno Alfredo	9	CDAP/ANNP	x	0	0	0	3	10
31	2022-02-17 12:03:18.386	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	x	0	0	0	3	11
32	2022-02-17 12:03:18.391	1-Digno Alfredo	11	FOTOCOPIAS	x	0	0	0	3	12
33	2022-02-17 12:03:18.397	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	3	13
34	2022-02-17 12:03:18.403	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	3	14
35	2022-02-17 12:03:18.413	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	3	15
36	2022-02-17 12:03:18.424	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	10	9	0	3	16
37	2022-02-17 12:03:18.431	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	3	17
38	2022-02-17 12:03:18.439	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	3	18
39	2022-02-17 12:03:18.447	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	3	19
40	2022-02-17 12:03:18.453	1-Digno Alfredo	19	OTROS	x	0	0	0	3	20
41	2022-02-17 13:37:21.558	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04000981N	16534	16534	0	4	2
42	2022-02-17 13:37:21.574	5-Angel Rios 	2	INDI	22005IC04000981N	26950	26950	0	4	3
43	2022-02-17 13:37:21.59	5-Angel Rios 	3	I.S.C.	22005IC04000981N	0	0	0	4	4
44	2022-02-17 13:37:21.59	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04000981N	4143889	4143889	0	4	5
45	2022-02-17 13:37:21.59	5-Angel Rios 	5	MULTAS VARIAS	22005IC04000981N	0	0	0	4	6
46	2022-02-17 13:37:21.605	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04000981N	3333399	3333399	0	4	7
47	2022-02-17 13:37:21.621	5-Angel Rios 	7	I.V.A.	22005IC04000981N	83334996	0	83334996	4	8
48	2022-02-17 13:37:21.637	5-Angel Rios 	8	CANON INFORMATICO	x	220127	220127	0	4	9
49	2022-02-17 13:37:21.652	5-Angel Rios 	9	CDAP/ANNP	001-025-0044385	6978	6343	634	4	10
50	2022-02-17 13:37:21.668	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0175735	94323	85748	8574	4	11
51	2022-02-17 13:37:21.668	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017172	110000	100000	10000	4	12
52	2022-02-17 13:37:21.683	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	4	13
53	2022-02-17 13:37:21.69	5-Angel Rios 	13	TASA PORTUARIA	x	0	0	0	4	14
54	2022-02-17 13:37:21.69	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	4	15
55	2022-02-17 13:37:21.706	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	0	0	0	4	16
56	2022-02-17 13:37:21.721	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	4	17
57	2022-02-17 13:37:21.721	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	4	18
58	2022-02-17 13:37:21.737	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	4	19
59	2022-02-17 13:37:21.752	5-Angel Rios 	19	OTROS	x	0	0	0	4	20
60	2022-04-27 14:14:21.057	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008320X	0	0	0	5	2
61	2022-04-27 14:14:21.062	5-Angel Rios 	2	INDI	22005IC04008320X	26950	26950	0	5	3
62	2022-04-27 14:14:21.076	5-Angel Rios 	3	I.S.C.	22005IC04008320X	0	0	0	5	4
63	2022-04-27 14:14:21.081	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008320X	3168096	3168096	0	5	5
64	2022-04-27 14:14:21.089	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008320X	0	0	0	5	6
65	2022-04-27 14:14:21.094	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008320X	2548798	2548798	0	5	7
66	2022-04-27 14:14:21.103	5-Angel Rios 	7	I.V.A.	22005IC04008320X	63719942	0	63719942	5	8
67	2022-04-27 14:14:21.109	5-Angel Rios 	8	CANON INFORMATICO	3576574	220127	220127	0	5	9
68	2022-04-27 14:14:21.115	5-Angel Rios 	9	CDAP/ANNP	001-025-0096979	6821	6200	620	5	10
69	2022-04-27 14:14:21.122	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0044753	75489	68626	6862	5	11
70	2022-04-27 14:14:21.131	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017605	70000	63636	6363	5	12
71	2022-04-27 14:14:21.138	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	5	13
72	2022-04-27 14:14:21.144	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106388	3019546	2745041	274504	5	14
73	2022-04-27 14:14:21.149	5-Angel Rios 	14	VISACION DCTOS	2874616/17/18/19/20	723600	723600	0	5	15
423	2022-05-10 15:42:14.584	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	23	15
74	2022-04-27 14:14:21.155	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001961	5077302	4615729	461572	5	16
75	2022-04-27 14:14:21.161	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	5	17
76	2022-04-27 14:14:21.166	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	5	18
77	2022-04-27 14:14:21.173	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	5	19
78	2022-04-27 14:14:21.18	5-Angel Rios 	19	OTROS	x	0	0	0	5	20
79	2022-04-27 15:11:59.523	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000192R	1177987	1177987	0	6	2
80	2022-04-27 15:11:59.535	5-Angel Rios 	2	INDI	22005IM04000192R	14325	14325	0	6	3
81	2022-04-27 15:11:59.541	5-Angel Rios 	3	ARANC CONS FACTURA	22005IM04000192R	102321	102321	0	6	4
82	2022-04-27 15:11:59.546	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IM04000192R	58899	58899	0	6	5
83	2022-04-27 15:11:59.553	5-Angel Rios 	5	MULTAS VARIAS	22005IM04000192R	0	0	0	6	6
84	2022-04-27 15:11:59.58	5-Angel Rios 	6	IRACIS GENERAL 700	22005IM04000192R	52943	52943	0	6	7
85	2022-04-27 15:11:59.593	5-Angel Rios 	7	I.V.A.	22005IM04000192R	1323574	0	1323574	6	8
86	2022-04-27 15:11:59.607	5-Angel Rios 	8	CANON INFORMATICO	3576625	44025	44025	0	6	9
87	2022-04-27 15:11:59.625	5-Angel Rios 	9	CDAP/ANNP	001-025-0096977	6821	6200	620	6	10
88	2022-04-27 15:11:59.638	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0044752	1697	1542	154	6	11
89	2022-04-27 15:11:59.644	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017604	70000	63636	6363	6	12
90	2022-04-27 15:11:59.649	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	6	13
91	2022-04-27 15:11:59.655	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106387	67869	61699	6169	6	14
92	2022-04-27 15:11:59.662	5-Angel Rios 	14	VISACION DCTOS	2874618	112590	112590	0	6	15
93	2022-04-27 15:11:59.671	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001962	1441000	1310000	131000	6	16
94	2022-04-27 15:11:59.677	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	6	17
95	2022-04-27 15:11:59.683	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	6	18
96	2022-04-27 15:11:59.689	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	6	19
97	2022-04-27 15:11:59.697	5-Angel Rios 	19	OTROS	x	0	0	0	6	20
98	2022-04-27 15:41:48.632	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008254Y	0	0	0	7	2
99	2022-04-27 15:41:48.64	5-Angel Rios 	2	INDI	22005IC04008254Y	26950	26950	0	7	3
100	2022-04-27 15:41:48.645	5-Angel Rios 	3	I.S.C.	22005IC04008254Y	0	0	0	7	4
101	2022-04-27 15:41:48.651	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008254Y	666198	666198	0	7	5
102	2022-04-27 15:41:48.656	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008254Y	0	0	0	7	6
103	2022-04-27 15:41:48.663	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008254Y	537271	537271	0	7	7
104	2022-04-27 15:41:48.671	5-Angel Rios 	7	I.V.A.	22005IC04008254Y	13431769	0	13431769	7	8
105	2022-04-27 15:41:48.677	5-Angel Rios 	8	CANON INFORMATICO	3580195	220127	220127	0	7	9
106	2022-04-27 15:41:48.693	5-Angel Rios 	9	CDAP/ANNP	001-025-0096525	6821	6200	620	7	10
107	2022-04-27 15:41:48.7	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0044302	19551	17773	1777	7	11
108	2022-04-27 15:41:48.705	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017606	70000	63636	6363	7	12
109	2022-04-27 15:41:48.713	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	7	13
110	2022-04-27 15:41:48.723	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106214	782054	710958	71095	7	14
111	2022-04-27 15:41:48.733	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	7	15
112	2022-04-27 15:41:48.739	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001963	3119818	2836198	283619	7	16
113	2022-04-27 15:41:48.746	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	7	17
114	2022-04-27 15:41:48.751	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	7	18
115	2022-04-27 15:41:48.759	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	7	19
116	2022-04-27 15:41:48.767	5-Angel Rios 	19	OTROS	x	0	0	0	7	20
117	2022-04-27 15:43:47.545	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008254Y	0	0	0	8	2
118	2022-04-27 15:43:47.573	5-Angel Rios 	2	INDI	22005IC04008254Y	26950	26950	0	8	3
119	2022-04-27 15:43:47.631	5-Angel Rios 	3	I.S.C.	22005IC04008254Y	0	0	0	8	4
120	2022-04-27 15:43:47.722	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008254Y	666198	666198	0	8	5
121	2022-04-27 15:43:47.885	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008254Y	0	0	0	8	6
122	2022-04-27 15:43:47.952	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008254Y	537271	537271	0	8	7
123	2022-04-27 15:43:47.959	5-Angel Rios 	7	I.V.A.	22005IC04008254Y	13431769	0	13431769	8	8
124	2022-04-27 15:43:47.963	5-Angel Rios 	8	CANON INFORMATICO	3580195	220127	220127	0	8	9
125	2022-04-27 15:43:47.969	5-Angel Rios 	9	CDAP/ANNP	001-025-0096525	6821	6200	620	8	10
126	2022-04-27 15:43:47.975	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0044302	19551	17773	1777	8	11
127	2022-04-27 15:43:47.985	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017606	70000	63636	6363	8	12
128	2022-04-27 15:43:47.993	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	8	13
129	2022-04-27 15:43:47.998	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106214	782054	710958	71095	8	14
130	2022-04-27 15:43:48.003	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	8	15
131	2022-04-27 15:43:48.01	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001963	3119818	2836198	283619	8	16
132	2022-04-27 15:43:48.015	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	8	17
133	2022-04-27 15:43:48.019	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	8	18
134	2022-04-27 15:43:48.025	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	8	19
135	2022-04-27 15:43:48.029	5-Angel Rios 	19	OTROS	x	0	0	0	8	20
136	2022-04-28 14:09:38.888	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008374R	951469	951469	0	9	2
137	2022-04-28 14:09:38.895	5-Angel Rios 	2	INDI	22005IC04008374R	26950	26950	0	9	3
138	2022-04-28 14:09:38.9	5-Angel Rios 	3	I.S.C.	22005IC04008374R	372286	372286	0	9	4
139	2022-04-28 14:09:38.905	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008374R	357678	357678	0	9	5
140	2022-04-28 14:09:38.91	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008374R	0	0	0	9	6
141	2022-04-28 14:09:38.915	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008374R	294517	294517	0	9	7
142	2022-04-28 14:09:38.925	5-Angel Rios 	7	I.V.A.	22005IC04008374R	7362911	0	7362911	9	8
143	2022-04-28 14:09:38.93	5-Angel Rios 	8	CANON INFORMATICO	3580195	220127	220127	0	9	9
144	2022-04-28 14:09:38.936	5-Angel Rios 	9	CDAP/ANNP	001-025-0097406	6841	6219	621	9	10
145	2022-04-28 14:09:38.944	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0045187	10601	9637	963	9	11
146	2022-04-28 14:09:38.949	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017618	170000	154545	15454	9	12
147	2022-04-28 14:09:38.954	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	9	13
148	2022-04-28 14:09:38.959	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106636	424027	385479	38547	9	14
149	2022-04-28 14:09:38.965	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	9	15
150	2022-04-28 14:09:38.97	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001964	2136515	1942286	194228	9	16
151	2022-04-28 14:09:38.979	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	9	17
152	2022-04-28 14:09:38.987	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	9	18
153	2022-04-28 14:09:38.993	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	9	19
154	2022-04-28 14:09:38.998	5-Angel Rios 	19	OTROS	x	0	0	0	9	20
155	2022-04-28 14:10:36.547	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008374R	951469	951469	0	10	2
156	2022-04-28 14:10:36.557	5-Angel Rios 	2	INDI	22005IC04008374R	26950	26950	0	10	3
157	2022-04-28 14:10:36.562	5-Angel Rios 	3	I.S.C.	22005IC04008374R	372286	372286	0	10	4
158	2022-04-28 14:10:36.568	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008374R	357678	357678	0	10	5
159	2022-04-28 14:10:36.577	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008374R	0	0	0	10	6
160	2022-04-28 14:10:36.582	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008374R	294517	294517	0	10	7
161	2022-04-28 14:10:36.587	5-Angel Rios 	7	I.V.A.	22005IC04008374R	7362911	0	7362911	10	8
162	2022-04-28 14:10:36.596	5-Angel Rios 	8	CANON INFORMATICO	3580195	220127	220127	0	10	9
163	2022-04-28 14:10:36.601	5-Angel Rios 	9	CDAP/ANNP	001-025-0097406	6841	6219	621	10	10
164	2022-04-28 14:10:36.607	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0045187	10601	9637	963	10	11
165	2022-04-28 14:10:36.611	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017618	170000	154545	15454	10	12
166	2022-04-28 14:10:36.621	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	10	13
167	2022-04-28 14:10:36.627	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106636	424027	385479	38547	10	14
168	2022-04-28 14:10:36.632	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	10	15
169	2022-04-28 14:10:36.64	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001964	2136515	1942286	194228	10	16
170	2022-04-28 14:10:36.644	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	10	17
171	2022-04-28 14:10:36.649	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	10	18
172	2022-04-28 14:10:36.654	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	10	19
173	2022-04-28 14:10:36.659	5-Angel Rios 	19	OTROS	x	0	0	0	10	20
174	2022-04-28 14:14:45.63	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008254Y	0	0	0	11	2
175	2022-04-28 14:14:45.636	5-Angel Rios 	2	INDI	22005IC04008254Y	26950	26950	0	11	3
176	2022-04-28 14:14:45.644	5-Angel Rios 	3	I.S.C.	22005IC04008254Y	0	0	0	11	4
177	2022-04-28 14:14:45.65	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008254Y	666198	666198	0	11	5
178	2022-04-28 14:14:45.656	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008254Y	0	0	0	11	6
179	2022-04-28 14:14:45.661	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008254Y	537271	537271	0	11	7
180	2022-04-28 14:14:45.667	5-Angel Rios 	7	I.V.A.	22005IC04008254Y	13431769	0	13431769	11	8
181	2022-04-28 14:14:45.678	5-Angel Rios 	8	CANON INFORMATICO	3580195	220127	220127	0	11	9
182	2022-04-28 14:14:45.686	5-Angel Rios 	9	CDAP/ANNP	001-025-0096525	6821	6200	620	11	10
183	2022-04-28 14:14:45.692	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0044302	19551	17773	1777	11	11
184	2022-04-28 14:14:45.699	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017606	70000	63636	6363	11	12
185	2022-04-28 14:14:45.706	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	11	13
186	2022-04-28 14:14:45.721	5-Angel Rios 	13	TASA PORTUARIA	002-007-0106214	782054	710958	71095	11	14
187	2022-04-28 14:14:45.726	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	11	15
188	2022-04-28 14:14:45.731	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001963	3119818	2836198	283619	11	16
189	2022-04-28 14:14:45.738	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	11	17
190	2022-04-28 14:14:45.744	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	11	18
191	2022-04-28 14:14:45.75	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	11	19
192	2022-04-28 14:14:45.756	5-Angel Rios 	19	OTROS	x	0	0	0	11	20
193	2022-05-03 16:00:55.564	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000587T	0	0	0	12	2
194	2022-05-03 16:00:55.57	5-Angel Rios 	2	INDI	22009IC04000587T	45501	45501	0	12	3
195	2022-05-03 16:00:55.576	5-Angel Rios 	3	I.S.C.	22009IC04000587T	0	0	0	12	4
196	2022-05-03 16:00:55.58	5-Angel Rios 	4	SERVICIO DE VALORACION	22009IC04000587T	584176	584176	0	12	5
197	2022-05-03 16:00:55.586	5-Angel Rios 	5	MULTAS VARIAS	22009IC04000587T	0	0	0	12	6
198	2022-05-03 16:00:55.591	5-Angel Rios 	6	IRACIS GENERAL 700	22009IC04000587T	472458	472458	0	12	7
199	2022-05-03 16:00:55.596	5-Angel Rios 	7	I.V.A.	22009IC04000587T	11811461	0	11811461	12	8
200	2022-05-03 16:00:55.605	5-Angel Rios 	8	CANON INFORMATICO	22009IC04000587T	220127	220127	0	12	9
201	2022-05-03 16:00:55.609	5-Angel Rios 	9	CDAP/ANNP	001-024-0095024	10000	9090	909	12	10
202	2022-05-03 16:00:55.614	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	12	11
203	2022-05-03 16:00:55.619	5-Angel Rios 	11	FOTOCOPIAS	016-003-0000347	70000	63636	6363	12	12
204	2022-05-03 16:00:55.625	5-Angel Rios 	12	APERTURA AG. TRANSP.	001-001-0004349	350000	318181	31818	12	13
205	2022-05-03 16:00:55.629	5-Angel Rios 	13	TASA PORTUARIA	009-005-0013356	844993	768175	76817	12	14
206	2022-05-03 16:00:55.637	5-Angel Rios 	14	VISACION DCTOS	275722/23/24/25	964600	964600	0	12	15
207	2022-05-03 16:00:55.642	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001965	4393593	3994175	399417	12	16
208	2022-05-03 16:00:55.65	5-Angel Rios 	16	GASTO ADMIN.	565	1300000	1300000	0	12	17
209	2022-05-03 16:00:55.655	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	12	18
210	2022-05-03 16:00:55.66	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	12	19
211	2022-05-03 16:00:55.665	5-Angel Rios 	19	OTROS	x	0	0	0	12	20
212	2022-05-03 16:05:24.499	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000587T	0	0	0	13	2
213	2022-05-03 16:05:24.505	5-Angel Rios 	2	INDI	22009IC04000587T	45501	45501	0	13	3
214	2022-05-03 16:05:24.512	5-Angel Rios 	3	I.S.C.	22009IC04000587T	0	0	0	13	4
215	2022-05-03 16:05:24.516	5-Angel Rios 	4	SERVICIO DE VALORACION	22009IC04000587T	584176	584176	0	13	5
216	2022-05-03 16:05:24.522	5-Angel Rios 	5	MULTAS VARIAS	22009IC04000587T	0	0	0	13	6
217	2022-05-03 16:05:24.527	5-Angel Rios 	6	IRACIS GENERAL 700	22009IC04000587T	472458	472458	0	13	7
218	2022-05-03 16:05:24.532	5-Angel Rios 	7	I.V.A.	22009IC04000587T	11811461	0	11811461	13	8
219	2022-05-03 16:05:24.539	5-Angel Rios 	8	CANON INFORMATICO	22009IC04000587T	220127	220127	0	13	9
220	2022-05-03 16:05:24.546	5-Angel Rios 	9	CDAP/ANNP	001-024-0095024	10000	9090	909	13	10
221	2022-05-03 16:05:24.55	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	13	11
222	2022-05-03 16:05:24.559	5-Angel Rios 	11	FOTOCOPIAS	016-003-0000347	70000	63636	6363	13	12
223	2022-05-03 16:05:24.565	5-Angel Rios 	12	APERTURA AG. TRANSP.	001-001-0004349	350000	318181	31818	13	13
224	2022-05-03 16:05:24.57	5-Angel Rios 	13	TASA PORTUARIA	009-005-0013356	844993	768175	76817	13	14
225	2022-05-03 16:05:24.576	5-Angel Rios 	14	VISACION DCTOS	275722/23/24/25	964600	964600	0	13	15
226	2022-05-03 16:05:24.58	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001965	4393593	3994175	399417	13	16
227	2022-05-03 16:05:24.587	5-Angel Rios 	16	GASTO ADMIN.	565	1300000	1300000	0	13	17
228	2022-05-03 16:05:24.592	5-Angel Rios 	17	LICENCIA PREVIA ALAMBRES	1010566	616357	616357	0	13	18
229	2022-05-03 16:05:24.597	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	13	19
230	2022-05-03 16:05:24.602	5-Angel Rios 	19	OTROS	x	0	0	0	13	20
231	2022-05-03 16:08:34.045	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000587T	0	0	0	14	2
232	2022-05-03 16:08:34.05	5-Angel Rios 	2	INDI	22009IC04000587T	45501	45501	0	14	3
233	2022-05-03 16:08:34.058	5-Angel Rios 	3	I.S.C.	22009IC04000587T	0	0	0	14	4
234	2022-05-03 16:08:34.063	5-Angel Rios 	4	SERVICIO DE VALORACION	22009IC04000587T	584176	584176	0	14	5
235	2022-05-03 16:08:34.067	5-Angel Rios 	5	MULTAS VARIAS	22009IC04000587T	0	0	0	14	6
236	2022-05-03 16:08:34.072	5-Angel Rios 	6	IRACIS GENERAL 700	22009IC04000587T	472458	472458	0	14	7
237	2022-05-03 16:08:34.077	5-Angel Rios 	7	I.V.A.	22009IC04000587T	11811461	0	11811461	14	8
238	2022-05-03 16:08:34.083	5-Angel Rios 	8	CANON INFORMATICO	22009IC04000587T	220127	220127	0	14	9
239	2022-05-03 16:08:34.089	5-Angel Rios 	9	CDAP/ANNP	001-024-0095024	10000	9090	909	14	10
240	2022-05-03 16:08:34.094	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	14	11
241	2022-05-03 16:08:34.102	5-Angel Rios 	11	FOTOCOPIAS	016-003-0000347	70000	63636	6363	14	12
242	2022-05-03 16:08:34.108	5-Angel Rios 	12	APERTURA AG. TRANSP.	001-001-0004349	350000	318181	31818	14	13
243	2022-05-03 16:08:34.113	5-Angel Rios 	13	TASA PORTUARIA	009-005-0013356	844993	768175	76817	14	14
244	2022-05-03 16:08:34.117	5-Angel Rios 	14	VISACION DCTOS	275722/23/24/25	964600	964600	0	14	15
245	2022-05-03 16:08:34.123	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001965	4393593	3994175	399417	14	16
246	2022-05-03 16:08:34.128	5-Angel Rios 	16	GASTO ADMIN.	565	1300000	1300000	0	14	17
247	2022-05-03 16:08:34.133	5-Angel Rios 	17	LICENCIA PREVIA ALAMBRES	1010566	616357	616357	0	14	18
248	2022-05-03 16:08:34.139	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	14	19
249	2022-05-03 16:08:34.144	5-Angel Rios 	19	OTROS	x	0	0	0	14	20
250	2022-05-04 12:37:18.804	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008737U	0	0	0	15	2
251	2022-05-04 12:37:18.826	5-Angel Rios 	2	INDI	22005IC04008737U	26951	26951	0	15	3
252	2022-05-04 12:37:18.83	5-Angel Rios 	3	I.S.C.	22005IC04008737U	0	0	0	15	4
253	2022-05-04 12:37:18.837	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008737U	3314640	3314640	0	15	5
254	2022-05-04 12:37:18.842	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008737U	0	0	0	15	6
255	2022-05-04 12:37:18.849	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008737U	2666620	2666620	0	15	7
256	2022-05-04 12:37:18.855	5-Angel Rios 	7	I.V.A.	22005IC04008737U	66665460	0	66665460	15	8
257	2022-05-04 12:37:18.864	5-Angel Rios 	8	CANON INFORMATICO	3623590	220127	220127	0	15	9
258	2022-05-04 12:37:18.87	5-Angel Rios 	9	CDAP/ANNP	001-025-0099951	6825	6204	620	15	10
259	2022-05-04 12:37:18.886	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0047731	80229	72935	7293	15	11
260	2022-05-04 12:37:18.89	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017641	80000	72727	7272	15	12
261	2022-05-04 12:37:18.898	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	15	13
262	2022-05-04 12:37:18.91	5-Angel Rios 	13	TASA PORTUARIA	002-007-0000367	3209144	2917403	291740	15	14
263	2022-05-04 12:37:18.915	5-Angel Rios 	14	VISACION DCTOS	2877145/46/47/48/49	955625	955625	0	15	15
264	2022-05-04 12:37:18.932	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001966	5150163	4681966	468196	15	16
265	2022-05-04 12:37:18.938	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	15	17
266	2022-05-04 12:37:18.943	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	15	18
267	2022-05-04 12:37:18.95	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	15	19
268	2022-05-04 12:37:18.955	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	15	19
269	2022-05-04 12:37:18.96	5-Angel Rios 	19	OTROS	x	0	0	0	15	20
270	2022-05-04 12:59:56.454	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008739W	0	0	0	16	2
271	2022-05-04 12:59:56.461	5-Angel Rios 	2	INDI	22005IC04008739W	26950	26950	0	16	3
272	2022-05-04 12:59:56.468	5-Angel Rios 	3	I.S.C.	22005IC04008739W	0	0	0	16	4
273	2022-05-04 12:59:56.473	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008739W	1556734	1556734	0	16	5
274	2022-05-04 12:59:56.477	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008739W	0	0	0	16	6
275	2022-05-04 12:59:56.484	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008739W	1253262	1253262	0	16	7
276	2022-05-04 12:59:56.492	5-Angel Rios 	7	I.V.A.	22005IC04008739W	31331543	0	31331543	16	8
277	2022-05-04 12:59:56.497	5-Angel Rios 	8	CANON INFORMATICO	3623374	220127	220127	0	16	9
278	2022-05-04 12:59:56.502	5-Angel Rios 	9	CDAP/ANNP	001-025-0099952	6825	6204	620	16	10
279	2022-05-04 12:59:56.507	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0047730	43318	39380	3938	16	11
280	2022-05-04 12:59:56.512	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017642	80000	72727	7272	16	12
281	2022-05-04 12:59:56.52	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	16	13
282	2022-05-04 12:59:56.525	5-Angel Rios 	13	TASA PORTUARIA	002-007-0000366	1732739	1575217	157521	16	14
283	2022-05-04 12:59:56.531	5-Angel Rios 	14	VISACION DCTOS	2877167/68/69/70/71	965265	965265	0	16	15
284	2022-05-04 12:59:56.537	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001968	4173327	3793933	379393	16	16
285	2022-05-04 12:59:56.542	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	16	17
286	2022-05-04 12:59:56.549	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	16	18
287	2022-05-04 12:59:56.554	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	16	19
288	2022-05-04 12:59:56.559	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	16	19
289	2022-05-04 12:59:56.569	5-Angel Rios 	19	OTROS	x	0	0	0	16	20
290	2022-05-06 11:47:18.18	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04005329M	0	0	0	17	2
291	2022-05-06 11:47:18.187	5-Angel Rios 	2	INDI	22030IC04005329M	26313	26313	0	17	3
292	2022-05-06 11:47:18.192	5-Angel Rios 	3	I.S.C.	22030IC04005329M	0	0	0	17	4
293	2022-05-06 11:47:18.198	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04005329M	383832	383832	0	17	5
294	2022-05-06 11:47:18.202	5-Angel Rios 	5	MULTAS VARIAS	22030IC04005329M	0	0	0	17	6
295	2022-05-06 11:47:18.207	5-Angel Rios 	6	IRACIS GENERAL 700	22030IC04005329M	310209	310209	0	17	7
296	2022-05-06 11:47:18.214	5-Angel Rios 	7	I.V.A.	22030IC04005329M	7755233	0	7755233	17	8
297	2022-05-06 11:47:18.219	5-Angel Rios 	8	CANON INFORMATICO	3665289	225127	225127	0	17	9
298	2022-05-06 11:47:18.223	5-Angel Rios 	9	CDAP/ANNP	x	0	0	0	17	10
299	2022-05-06 11:47:18.229	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	17	11
300	2022-05-06 11:47:18.235	5-Angel Rios 	11	FOTOCOPIAS	020-002-0008412	45150	41045	4104	17	12
301	2022-05-06 11:47:18.24	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	17	13
302	2022-05-06 11:47:18.246	5-Angel Rios 	13	TASA PORTUARIA	001-004-09765	1203323	1093930	109393	17	14
303	2022-05-06 11:47:18.252	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	17	15
304	2022-05-06 11:47:18.257	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001969	1500543	1364130	136413	17	16
305	2022-05-06 11:47:18.263	5-Angel Rios 	16	GASTO ADMIN.	001-001-0001969	660000	660000	0	17	17
306	2022-05-06 11:47:18.268	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	17	18
307	2022-05-06 11:47:18.272	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	17	19
308	2022-05-06 11:47:18.277	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	17	19
309	2022-05-06 11:47:18.287	5-Angel Rios 	19	OTROS	x	0	0	0	17	20
310	2022-05-06 13:40:40.866	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04008988C	9676083	9676083	0	18	2
311	2022-05-06 13:40:40.871	5-Angel Rios 	2	INDI	22002IC04008988C	30818	30818	0	18	3
312	2022-05-06 13:40:40.877	5-Angel Rios 	3	I.S.C.	22002IC04008988C	33597	33597	0	18	4
313	2022-05-06 13:40:40.883	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04008988C	434404	434404	0	18	5
314	2022-05-06 13:40:40.889	5-Angel Rios 	5	MULTAS VARIAS	22002IC04008988C	0	0	0	18	6
315	2022-05-06 13:40:40.894	5-Angel Rios 	6	IRACIS GENERAL 700	22002IC04008988C	389984	389984	0	18	7
316	2022-05-06 13:40:40.899	5-Angel Rios 	7	I.V.A.	22002IC04008988C	9749596	0	9749596	18	8
317	2022-05-06 13:40:40.905	5-Angel Rios 	8	CANON INFORMATICO	22002IC04008988C	220127	220127	0	18	9
318	2022-05-06 13:40:40.913	5-Angel Rios 	9	CDAP/ANNP	001-021-0035872	20524	18658	1865	18	10
319	2022-05-06 13:40:40.919	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	18	11
320	2022-05-06 13:40:40.924	5-Angel Rios 	11	FOTOCOPIAS	004-003-0023052	49000	44545	4454	18	12
321	2022-05-06 13:40:40.93	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	18	13
322	2022-05-06 13:40:40.934	5-Angel Rios 	13	TASA PORTUARIA	001-034-0031505	2404270	2185700	218570	18	14
323	2022-05-06 13:40:40.94	5-Angel Rios 	14	VISACION DCTOS	1013723	440255	440255	0	18	15
324	2022-05-06 13:40:40.945	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001970	2810551	2555046	255504	18	16
325	2022-05-06 13:40:40.95	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	18	17
326	2022-05-06 13:40:40.955	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	18	18
327	2022-05-06 13:40:40.964	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	18	19
328	2022-05-06 13:40:40.97	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	18	19
329	2022-05-06 13:40:40.977	5-Angel Rios 	19	OTROS	x	0	0	0	18	20
330	2022-05-06 13:47:59.093	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04008988C	9676083	9676083	0	19	2
331	2022-05-06 13:47:59.11	5-Angel Rios 	2	INDI	22002IC04008988C	30818	30818	0	19	3
332	2022-05-06 13:47:59.178	5-Angel Rios 	3	I.S.C.	22002IC04008988C	33597	33597	0	19	4
333	2022-05-06 13:47:59.217	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04008988C	434404	434404	0	19	5
334	2022-05-06 13:47:59.235	5-Angel Rios 	5	MULTAS VARIAS	22002IC04008988C	0	0	0	19	6
335	2022-05-06 13:47:59.26	5-Angel Rios 	6	IRACIS GENERAL 700	22002IC04008988C	389984	389984	0	19	7
336	2022-05-06 13:47:59.279	5-Angel Rios 	7	I.V.A.	22002IC04008988C	9749596	0	9749596	19	8
337	2022-05-06 13:47:59.301	5-Angel Rios 	8	CANON INFORMATICO	22002IC04008988C	220127	220127	0	19	9
338	2022-05-06 13:47:59.324	5-Angel Rios 	9	CDAP/ANNP	001-021-0035872	20524	18658	1865	19	10
339	2022-05-06 13:47:59.344	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	19	11
340	2022-05-06 13:47:59.37	5-Angel Rios 	11	FOTOCOPIAS	004-003-0023052	49000	44545	4454	19	12
341	2022-05-06 13:47:59.4	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	19	13
342	2022-05-06 13:47:59.417	5-Angel Rios 	13	TASA PORTUARIA	001-034-0031505	2404270	2185700	218570	19	14
343	2022-05-06 13:47:59.438	5-Angel Rios 	14	VISACION DCTOS	1013723	440255	440255	0	19	15
344	2022-05-06 13:47:59.458	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001970	2810551	2555046	255504	19	16
345	2022-05-06 13:47:59.481	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	19	17
346	2022-05-06 13:47:59.503	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	19	18
347	2022-05-06 13:47:59.525	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	19	19
348	2022-05-06 13:47:59.548	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	19	19
349	2022-05-06 13:47:59.568	5-Angel Rios 	19	OTROS	x	0	0	0	19	20
350	2022-05-06 13:50:02.63	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04008988C	9676083	9676083	0	20	2
351	2022-05-06 13:50:02.64	5-Angel Rios 	2	INDI	22002IC04008988C	30818	30818	0	20	3
352	2022-05-06 13:50:02.645	5-Angel Rios 	3	I.S.C.	22002IC04008988C	33597	33597	0	20	4
2346	2022-07-18 16:16:34.21	5-Angel Rios 	13	CDAP PAGOS	x	10000	9090	909	134	27
353	2022-05-06 13:50:02.65	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04008988C	434404	434404	0	20	5
354	2022-05-06 13:50:02.655	5-Angel Rios 	5	MULTAS VARIAS	22002IC04008988C	0	0	0	20	6
355	2022-05-06 13:50:02.661	5-Angel Rios 	6	IRACIS GENERAL 700	22002IC04008988C	389984	389984	0	20	7
356	2022-05-06 13:50:02.667	5-Angel Rios 	7	I.V.A.	22002IC04008988C	9749596	0	9749596	20	8
357	2022-05-06 13:50:02.672	5-Angel Rios 	8	CANON INFORMATICO	22002IC04008988C	220127	220127	0	20	9
358	2022-05-06 13:50:02.677	5-Angel Rios 	9	CDAP/ANNP	001-021-0035872	20524	18658	1865	20	10
359	2022-05-06 13:50:02.682	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	20	11
360	2022-05-06 13:50:02.691	5-Angel Rios 	11	FOTOCOPIAS	004-003-0023052	49000	44545	4454	20	12
361	2022-05-06 13:50:02.698	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	20	13
362	2022-05-06 13:50:02.705	5-Angel Rios 	13	TASA PORTUARIA	001-034-0031505	2404270	2185700	218570	20	14
363	2022-05-06 13:50:02.714	5-Angel Rios 	14	VISACION DCTOS	1013723	440255	440255	0	20	15
364	2022-05-06 13:50:02.72	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001970	2810551	2555046	255504	20	16
365	2022-05-06 13:50:02.728	5-Angel Rios 	16	GASTO ADMIN.	566	720000	720000	0	20	17
366	2022-05-06 13:50:02.733	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	20	18
367	2022-05-06 13:50:02.74	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	20	19
368	2022-05-06 13:50:02.748	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	20	19
369	2022-05-06 13:50:02.756	5-Angel Rios 	19	OTROS	x	0	0	0	20	20
370	2022-05-06 13:53:06.163	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04008988C	9676083	9676083	0	21	2
371	2022-05-06 13:53:06.184	5-Angel Rios 	2	INDI	22002IC04008988C	30818	30818	0	21	3
372	2022-05-06 13:53:06.204	5-Angel Rios 	3	I.S.C.	22002IC04008988C	33597	33597	0	21	4
373	2022-05-06 13:53:06.249	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04008988C	434404	434404	0	21	5
374	2022-05-06 13:53:06.282	5-Angel Rios 	5	MULTAS VARIAS	22002IC04008988C	0	0	0	21	6
375	2022-05-06 13:53:06.408	5-Angel Rios 	6	IRACIS GENERAL 700	22002IC04008988C	389984	389984	0	21	7
376	2022-05-06 13:53:06.478	5-Angel Rios 	7	I.V.A.	22002IC04008988C	9749596	0	9749596	21	8
377	2022-05-06 13:53:06.507	5-Angel Rios 	8	CANON INFORMATICO	22002IC04008988C	220127	220127	0	21	9
378	2022-05-06 13:53:06.531	5-Angel Rios 	9	CDAP/ANNP	001-021-0035872	20524	18658	1865	21	10
379	2022-05-06 13:53:06.548	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	21	11
380	2022-05-06 13:53:06.636	5-Angel Rios 	11	FOTOCOPIAS	004-003-0023052	49000	44545	4454	21	12
381	2022-05-06 13:53:06.69	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	21	13
382	2022-05-06 13:53:06.725	5-Angel Rios 	13	TASA PORTUARIA	001-034-0031505	2404270	2185700	218570	21	14
383	2022-05-06 13:53:06.746	5-Angel Rios 	14	VISACION DCTOS	1013723	440255	440255	0	21	15
384	2022-05-06 13:53:06.823	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001970	2810551	2555046	255504	21	16
385	2022-05-06 13:53:06.829	5-Angel Rios 	16	GASTO ADMIN.	566	720000	720000	0	21	17
386	2022-05-06 13:53:06.834	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	21	18
387	2022-05-06 13:53:06.84	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	21	19
388	2022-05-06 13:53:06.846	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	21	19
389	2022-05-06 13:53:06.855	5-Angel Rios 	19	OTROS	x	0	0	0	21	20
390	2022-05-06 14:17:52.512	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04008880T	0	0	0	22	2
391	2022-05-06 14:17:52.518	5-Angel Rios 	2	INDI	22005IC04008880T	26950	26950	0	22	3
392	2022-05-06 14:17:52.525	5-Angel Rios 	3	I.S.C.	22005IC04008880T	0	0	0	22	4
393	2022-05-06 14:17:52.532	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04008880T	898572	898572	0	22	5
394	2022-05-06 14:17:52.538	5-Angel Rios 	5	MULTAS VARIAS	22005IC04008880T	0	0	0	22	6
395	2022-05-06 14:17:52.544	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04008880T	724100	724100	0	22	7
396	2022-05-06 14:17:52.551	5-Angel Rios 	7	I.V.A.	22005IC04008880T	18102501	0	18102501	22	8
397	2022-05-06 14:17:52.557	5-Angel Rios 	8	CANON INFORMATICO	3661203	220127	220127	0	22	9
398	2022-05-06 14:17:52.564	5-Angel Rios 	9	CDAP/ANNP	001-025-0100827	6834	6212	621	22	10
399	2022-05-06 14:17:52.569	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0048609	24417	22197	2219	22	11
400	2022-05-06 14:17:52.575	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017652	60000	54545	5454	22	12
401	2022-05-06 14:17:52.581	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	22	13
402	2022-05-06 14:17:52.586	5-Angel Rios 	13	TASA PORTUARIA	002-007-0000732	976672	887883	88788	22	14
403	2022-05-06 14:17:52.591	5-Angel Rios 	14	VISACION DCTOS	2880018/19/20	583800	583800	0	22	15
404	2022-05-06 14:17:52.598	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001973	3169415	2881286	288128	22	16
405	2022-05-06 14:17:52.603	5-Angel Rios 	16	VISACION POR VUI .	1014997	440255	440255	0	22	17
406	2022-05-06 14:17:52.61	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	22	18
407	2022-05-06 14:17:52.616	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	22	19
408	2022-05-06 14:17:52.621	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	22	19
409	2022-05-06 14:17:52.628	5-Angel Rios 	19	OTROS	x	0	0	0	22	20
410	2022-05-10 15:42:14.514	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01001717E	0	0	0	23	2
411	2022-05-10 15:42:14.514	5-Angel Rios 	2	INDI	22023EC01001717E	0	0	0	23	3
412	2022-05-10 15:42:14.514	5-Angel Rios 	3	I.S.C.	22023EC01001717E	0	0	0	23	4
413	2022-05-10 15:42:14.53	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01001717E	0	0	0	23	5
414	2022-05-10 15:42:14.537	5-Angel Rios 	5	MULTAS VARIAS	22023EC01001717E	0	0	0	23	6
415	2022-05-10 15:42:14.537	5-Angel Rios 	6	IRACIS GENERAL 700	22023EC01001717E	0	0	0	23	7
416	2022-05-10 15:42:14.537	5-Angel Rios 	7	I.V.A.	22023EC01001717E	0	0	0	23	8
417	2022-05-10 15:42:14.552	5-Angel Rios 	8	CANON INFORMATICO	22023EC01001717E	220127	220127	0	23	9
418	2022-05-10 15:42:14.552	5-Angel Rios 	9	CDAP/ANNP	x	0	0	0	23	10
419	2022-05-10 15:42:14.552	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	23	11
420	2022-05-10 15:42:14.568	5-Angel Rios 	11	FOTOCOPIAS	001-001-0022408	50000	45454	4545	23	12
421	2022-05-10 15:42:14.568	5-Angel Rios 	12	APERTURA AG. TRANSP.	X	0	0	0	23	13
422	2022-05-10 15:42:14.584	5-Angel Rios 	13	TASA PORTUARIA	001-001-0121551	66000	60000	6000	23	14
424	2022-05-10 15:42:14.584	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001974	2736290	2487536	248753	23	16
425	2022-05-10 15:42:14.599	5-Angel Rios 	16	TASA MIC	1820888	92451	92451	0	23	17
426	2022-05-10 15:42:14.599	5-Angel Rios 	17	TASA UIP	1820889	92451	92451	0	23	18
427	2022-05-10 15:42:14.599	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	23	19
428	2022-05-10 15:42:14.615	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	23	19
429	2022-05-10 15:42:14.615	5-Angel Rios 	19	OTROS	x	0	0	0	23	20
430	2022-05-12 14:39:43.731	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01001719G	0	0	0	24	2
431	2022-05-12 14:39:43.739	5-Angel Rios 	2	INDI	22023EC01001719G	0	0	0	24	3
432	2022-05-12 14:39:43.749	5-Angel Rios 	3	I.S.C.	22023EC01001719G	0	0	0	24	4
433	2022-05-12 14:39:43.755	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01001719G	0	0	0	24	5
434	2022-05-12 14:39:43.76	5-Angel Rios 	5	MULTAS VARIAS	22023EC01001719G	0	0	0	24	6
435	2022-05-12 14:39:43.767	5-Angel Rios 	6	IRACIS GENERAL 700	22023EC01001719G	0	0	0	24	7
436	2022-05-12 14:39:43.772	5-Angel Rios 	7	I.V.A.	22023EC01001719G	0	0	0	24	8
437	2022-05-12 14:39:43.777	5-Angel Rios 	8	CANON INFORMATICO	22023EC01001719G	220127	220127	0	24	9
438	2022-05-12 14:39:43.786	5-Angel Rios 	9	CDAP/ANNP	x	0	0	0	24	10
439	2022-05-12 14:39:43.791	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	24	11
440	2022-05-12 14:39:43.799	5-Angel Rios 	11	FOTOCOPIAS	001-001-0022448	50000	45454	4545	24	12
441	2022-05-12 14:39:43.802	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	24	13
442	2022-05-12 14:39:43.811	5-Angel Rios 	13	TASA PORTUARIA	001-001-0121688	66000	60000	6000	24	14
443	2022-05-12 14:39:43.815	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	24	15
444	2022-05-12 14:39:43.824	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001975	3090119	2809199	280919	24	16
445	2022-05-12 14:39:43.83	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	24	17
446	2022-05-12 14:39:43.836	5-Angel Rios 	17	TASA MIC 	1823118	92451	92451	0	24	18
447	2022-05-12 14:39:43.842	5-Angel Rios 	17	TASA UIP	002-002-0006834	88051	88051	0	24	19
448	2022-05-12 14:39:43.846	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	24	19
449	2022-05-12 14:39:43.857	5-Angel Rios 	19	OTROS	x	0	0	0	24	20
450	2022-05-13 16:37:43.942	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04009558W	0	0	0	25	2
451	2022-05-13 16:37:43.958	5-Angel Rios 	2	INDI	22005IC04009558W	26600	26600	0	25	3
452	2022-05-13 16:37:43.958	5-Angel Rios 	3	I.S.C.	22005IC04009558W	0	0	0	25	4
453	2022-05-13 16:37:43.974	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04009558W	1168691	1168691	0	25	5
454	2022-05-13 16:37:43.974	5-Angel Rios 	5	MULTAS VARIAS	22005IC04009558W	0	0	0	25	6
455	2022-05-13 16:37:43.974	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04009558W	941253	941253	0	25	7
456	2022-05-13 16:37:43.989	5-Angel Rios 	7	I.V.A.	22005IC04009558W	23531365	0	23531365	25	8
457	2022-05-13 16:37:43.989	5-Angel Rios 	8	CANON INFORMATICO	3832796	220127	220127	0	25	9
458	2022-05-13 16:37:44.005	5-Angel Rios 	9	CDAP/ANNP	001-025-0105507	6882	6256	625	25	10
459	2022-05-13 16:37:44.005	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0053296	31789	28899	2889	25	11
460	2022-05-13 16:37:44.005	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017694	110000	100000	10000	25	12
461	2022-05-13 16:37:44.02	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	25	13
462	2022-05-13 16:37:44.02	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003089	1271575	1155977	115597	25	14
463	2022-05-13 16:37:44.036	5-Angel Rios 	14	VISACION DCTOS	2884270/71/72/73/74	990325	990325	0	25	15
464	2022-05-13 16:37:44.036	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001976	3223977	2930888	293088	25	16
465	2022-05-13 16:37:44.036	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	25	17
466	2022-05-13 16:37:44.052	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	25	18
467	2022-05-13 16:37:44.052	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	25	19
468	2022-05-13 16:37:44.067	5-Angel Rios 	18	AGILIZACION MIC	x	0	0	0	25	19
469	2022-05-13 16:37:44.067	5-Angel Rios 	19	OTROS	x	0	0	0	25	20
470	2022-05-17 12:09:16.456	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04009800M	198949	198949	0	26	2
471	2022-05-17 12:09:16.463	5-Angel Rios 	2	INDI	22005IC04009800M	26950	26950	0	26	3
472	2022-05-17 12:09:16.469	5-Angel Rios 	3	I.S.C.	22005IC04009800M	94220	94220	0	26	4
473	2022-05-17 12:09:16.475	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04009800M	380767	380767	0	26	5
474	2022-05-17 12:09:16.481	5-Angel Rios 	5	MULTAS VARIAS	22005IC04009800M	0	0	0	26	6
475	2022-05-17 12:09:16.489	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04009800M	308956	308956	0	26	7
476	2022-05-17 12:09:16.527	5-Angel Rios 	7	I.V.A.	22005IC04009800M	7723912	0	7723912	26	8
477	2022-05-17 12:09:16.532	5-Angel Rios 	8	CANON INFORMATICO	3911308	220127	220127	0	26	9
478	2022-05-17 12:09:16.537	5-Angel Rios 	9	CDAP/ANNP	001-025-0107229	6878	6252	625	26	10
479	2022-05-17 12:09:16.541	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0055015	11236	10214	1021	26	11
480	2022-05-17 12:09:16.548	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017708	110000	100000	10000	26	12
481	2022-05-17 12:09:16.552	5-Angel Rios 	12	APERTURA AG. TRANSP.	X	0	0	0	26	13
482	2022-05-17 12:09:16.556	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003873	449423	408566	40856	26	14
483	2022-05-17 12:09:16.56	5-Angel Rios 	14	VISACION DCTOS	1022790	440255	440255	0	26	15
484	2022-05-17 12:09:16.566	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001977	2177149	1979226	197922	26	16
485	2022-05-17 12:09:16.57	5-Angel Rios 	16	GASTO ADMIN.	001-024-0102815	10000	10000	0	26	17
486	2022-05-17 12:09:16.574	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	26	18
487	2022-05-17 12:14:51.607	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04009800M	198949	198949	0	26	2
488	2022-05-17 12:14:51.612	5-Angel Rios 	2	INDI	22005IC04009800M	26950	26950	0	26	3
489	2022-05-17 12:14:51.618	5-Angel Rios 	3	I.S.C.	22005IC04009800M	94220	94220	0	26	4
490	2022-05-17 12:14:51.622	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04009800M	380767	380767	0	26	5
491	2022-05-17 12:14:51.626	5-Angel Rios 	5	MULTAS VARIAS	22005IC04009800M	0	0	0	26	6
492	2022-05-17 12:14:51.629	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04009800M	308956	308956	0	26	7
493	2022-05-17 12:14:51.634	5-Angel Rios 	7	I.V.A.	22005IC04009800M	7723912	0	7723912	26	8
494	2022-05-17 12:14:51.638	5-Angel Rios 	8	CANON INFORMATICO	3911308	220127	220127	0	26	9
495	2022-05-17 12:14:51.642	5-Angel Rios 	9	CDAP/ANNP	001-025-0107229	6878	6252	625	26	10
496	2022-05-17 12:14:51.645	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0055015	11236	10214	1021	26	11
497	2022-05-17 12:14:51.652	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017708	110000	100000	10000	26	12
498	2022-05-17 12:14:51.656	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	26	13
499	2022-05-17 12:14:51.66	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003873	449423	408566	40856	26	14
500	2022-05-17 12:14:51.664	5-Angel Rios 	14	VISACION DCTOS	1022790	440255	440255	0	26	15
501	2022-05-17 12:14:51.676	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001977	2177149	1979226	197922	26	16
502	2022-05-17 12:14:51.681	5-Angel Rios 	16	CDAP PAGOS	001-024-0102815	10000	10000	0	26	17
503	2022-05-17 12:14:51.686	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	26	18
504	2022-05-17 12:14:51.691	5-Angel Rios 	19	OTROS	x	0	0	0	26	20
505	2022-05-17 12:51:36.219	1-Digno Alfredo	1	DERECHO ADUANERO	22005IC04000981N	16534	16534	0	26	2
506	2022-05-17 12:51:36.227	1-Digno Alfredo	2	INDI	22005IC04000981N	26950	26950	0	26	3
507	2022-05-17 12:51:36.233	1-Digno Alfredo	3	I.S.C.	22005IC04000981N	0	0	0	26	4
508	2022-05-17 12:51:36.239	1-Digno Alfredo	4	SERVICIO DE VALORACION	22005IC04000981N	4143889	4143889	0	26	5
509	2022-05-17 12:51:36.244	1-Digno Alfredo	5	MULTAS VARIAS	22005IC04000981N	0	0	0	26	6
510	2022-05-17 12:51:36.254	1-Digno Alfredo	6	IRACIS GENERAL 700	22005IC04000981N	3333399	3333399	0	26	7
511	2022-05-17 12:51:36.258	1-Digno Alfredo	7	I.V.A.	22005IC04000981N	83334996	0	83334996	26	8
512	2022-05-17 12:51:36.263	1-Digno Alfredo	8	CANON INFORMATICO	x	220127	220127	0	26	9
513	2022-05-17 12:51:36.27	1-Digno Alfredo	9	CDAP/ANNP	001-025-0044385	6978	6343	634	26	10
514	2022-05-17 12:51:36.279	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	001-004-0175735	94323	85748	8574	26	11
515	2022-05-17 12:51:36.288	1-Digno Alfredo	11	FOTOCOPIAS	001-002-0017172	110000	100000	10000	26	12
516	2022-05-17 12:51:36.296	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	26	13
517	2022-05-17 12:51:36.303	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	26	14
518	2022-05-17 12:51:36.307	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	26	15
519	2022-05-17 12:51:36.312	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	0	0	0	26	16
520	2022-05-17 12:51:36.316	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	26	17
521	2022-05-17 12:51:36.322	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	26	18
522	2022-05-17 12:51:36.328	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	26	19
523	2022-05-17 12:51:36.333	1-Digno Alfredo	19	OTROS	x	0	0	0	26	20
524	2022-05-17 12:53:06.688	1-Digno Alfredo	1	DERECHO ADUANERO	6467456	0	0	0	26	2
525	2022-05-17 12:53:06.702	1-Digno Alfredo	2	INDI	6467456	0	0	0	26	3
526	2022-05-17 12:53:06.711	1-Digno Alfredo	3	I.S.C.	6467456	0	0	0	26	4
527	2022-05-17 12:53:06.715	1-Digno Alfredo	4	SERVICIO DE VALORACION	6467456	0	0	0	26	5
528	2022-05-17 12:53:06.723	1-Digno Alfredo	5	MULTAS VARIAS	6467456	0	0	0	26	6
529	2022-05-17 12:53:06.73	1-Digno Alfredo	6	IRACIS GENERAL 700	6467456	0	0	0	26	7
530	2022-05-17 12:53:06.735	1-Digno Alfredo	7	I.V.A.	6467456	0	0	0	26	8
531	2022-05-17 12:53:06.74	1-Digno Alfredo	8	CANON INFORMATICO	x	0	0	0	26	9
532	2022-05-17 12:53:06.744	1-Digno Alfredo	9	CDAP/ANNP	x	0	0	0	26	10
533	2022-05-17 12:53:06.75	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	x	0	0	0	26	11
534	2022-05-17 12:53:06.756	1-Digno Alfredo	11	FOTOCOPIAS	x	0	0	0	26	12
535	2022-05-17 12:53:06.76	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	26	13
536	2022-05-17 12:53:06.767	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	26	14
537	2022-05-17 12:53:06.771	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	26	15
538	2022-05-17 12:53:06.776	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	10	9	0	26	16
539	2022-05-17 12:53:06.781	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	26	17
540	2022-05-17 12:53:06.785	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	26	18
541	2022-05-17 12:53:06.79	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	26	19
542	2022-05-17 12:53:06.801	1-Digno Alfredo	19	OTROS	x	0	0	0	26	20
543	2022-05-17 13:34:35.567	1-Digno Alfredo	1	DERECHO ADUANERO	22005IC04009800M	198949	198949	0	27	2
544	2022-05-17 13:34:35.576	1-Digno Alfredo	1	DERECHO ADUANERO	22005IC04000981N	16534	16534	0	27	2
545	2022-05-17 13:34:35.584	1-Digno Alfredo	1	DERECHO ADUANERO	6467456	0	0	0	27	2
546	2022-05-17 13:34:35.591	1-Digno Alfredo	1	DERECHO ADUANERO	22005IC04009800M	198949	198949	0	27	2
547	2022-05-17 13:34:35.596	1-Digno Alfredo	2	INDI	22005IC04009800M	26950	26950	0	27	3
548	2022-05-17 13:34:35.601	1-Digno Alfredo	2	INDI	22005IC04009800M	26950	26950	0	27	3
549	2022-05-17 13:34:35.607	1-Digno Alfredo	2	INDI	22005IC04000981N	26950	26950	0	27	3
550	2022-05-17 13:34:35.611	1-Digno Alfredo	2	INDI	6467456	0	0	0	27	3
551	2022-05-17 13:34:35.615	1-Digno Alfredo	3	I.S.C.	22005IC04000981N	0	0	0	27	4
552	2022-05-17 13:34:35.618	1-Digno Alfredo	3	I.S.C.	22005IC04009800M	94220	94220	0	27	4
553	2022-05-17 13:34:35.622	1-Digno Alfredo	3	I.S.C.	22005IC04009800M	94220	94220	0	27	4
554	2022-05-17 13:34:35.626	1-Digno Alfredo	3	I.S.C.	6467456	0	0	0	27	4
555	2022-05-17 13:34:35.63	1-Digno Alfredo	4	SERVICIO DE VALORACION	22005IC04000981N	4143889	4143889	0	27	5
556	2022-05-17 13:34:35.636	1-Digno Alfredo	4	SERVICIO DE VALORACION	6467456	0	0	0	27	5
557	2022-05-17 13:34:35.639	1-Digno Alfredo	4	SERVICIO DE VALORACION	22005IC04009800M	380767	380767	0	27	5
558	2022-05-17 13:34:35.646	1-Digno Alfredo	4	SERVICIO DE VALORACION	22005IC04009800M	380767	380767	0	27	5
559	2022-05-17 13:34:35.65	1-Digno Alfredo	5	MULTAS VARIAS	22005IC04000981N	0	0	0	27	6
560	2022-05-17 13:34:35.654	1-Digno Alfredo	5	MULTAS VARIAS	22005IC04009800M	0	0	0	27	6
561	2022-05-17 13:34:35.658	1-Digno Alfredo	5	MULTAS VARIAS	6467456	0	0	0	27	6
562	2022-05-17 13:34:35.671	1-Digno Alfredo	5	MULTAS VARIAS	22005IC04009800M	0	0	0	27	6
563	2022-05-17 13:34:35.675	1-Digno Alfredo	6	IRACIS GENERAL 700	22005IC04000981N	3333399	3333399	0	27	7
564	2022-05-17 13:34:35.68	1-Digno Alfredo	6	IRACIS GENERAL 700	6467456	0	0	0	27	7
565	2022-05-17 13:34:35.685	1-Digno Alfredo	6	IRACIS GENERAL 700	22005IC04009800M	308956	308956	0	27	7
566	2022-05-17 13:34:35.691	1-Digno Alfredo	6	IRACIS GENERAL 700	22005IC04009800M	308956	308956	0	27	7
567	2022-05-17 13:34:35.695	1-Digno Alfredo	7	I.V.A.	22005IC04009800M	7723912	0	7723912	27	8
568	2022-05-17 13:34:35.714	1-Digno Alfredo	7	I.V.A.	6467456	0	0	0	27	8
569	2022-05-17 13:34:35.718	1-Digno Alfredo	7	I.V.A.	22005IC04000981N	83334996	0	83334996	27	8
570	2022-05-17 13:34:35.721	1-Digno Alfredo	7	I.V.A.	22005IC04009800M	7723912	0	7723912	27	8
571	2022-05-17 13:34:35.725	1-Digno Alfredo	8	CANON INFORMATICO	3911308	220127	220127	0	27	9
572	2022-05-17 13:34:35.729	1-Digno Alfredo	8	CANON INFORMATICO	x	0	0	0	27	9
573	2022-05-17 13:34:35.733	1-Digno Alfredo	8	CANON INFORMATICO	3911308	220127	220127	0	27	9
574	2022-05-17 13:34:35.736	1-Digno Alfredo	8	CANON INFORMATICO	x	220127	220127	0	27	9
575	2022-05-17 13:34:35.74	1-Digno Alfredo	9	CDAP/ANNP	001-025-0044385	6978	6343	634	27	10
576	2022-05-17 13:34:35.743	1-Digno Alfredo	9	CDAP/ANNP	001-025-0107229	6878	6252	625	27	10
577	2022-05-17 13:34:35.748	1-Digno Alfredo	9	CDAP/ANNP	x	0	0	0	27	10
578	2022-05-17 13:34:35.753	1-Digno Alfredo	9	CDAP/ANNP	001-025-0107229	6878	6252	625	27	10
579	2022-05-17 13:34:35.756	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	x	0	0	0	27	11
580	2022-05-17 13:34:35.76	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	001-004-0055015	11236	10214	1021	27	11
581	2022-05-17 13:34:35.769	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	001-004-0055015	11236	10214	1021	27	11
582	2022-05-17 13:34:35.774	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	001-004-0175735	94323	85748	8574	27	11
583	2022-05-17 13:34:35.779	1-Digno Alfredo	11	FOTOCOPIAS	x	0	0	0	27	12
584	2022-05-17 13:34:35.783	1-Digno Alfredo	11	FOTOCOPIAS	001-002-0017172	110000	100000	10000	27	12
585	2022-05-17 13:34:35.793	1-Digno Alfredo	11	FOTOCOPIAS	001-002-0017708	110000	100000	10000	27	12
586	2022-05-17 13:34:35.797	1-Digno Alfredo	11	FOTOCOPIAS	001-002-0017708	110000	100000	10000	27	12
587	2022-05-17 13:34:35.801	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	27	13
588	2022-05-17 13:34:35.804	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	27	13
589	2022-05-17 13:34:35.808	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	27	13
590	2022-05-17 13:34:35.811	1-Digno Alfredo	12	APERTURA AG. TRANSP.	X	0	0	0	27	13
591	2022-05-17 13:34:35.815	1-Digno Alfredo	13	TASA PORTUARIA	002-007-0003873	449423	408566	40856	27	14
592	2022-05-17 13:34:35.819	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	27	14
593	2022-05-17 13:34:35.822	1-Digno Alfredo	13	TASA PORTUARIA	002-007-0003873	449423	408566	40856	27	14
594	2022-05-17 13:34:35.826	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	27	14
595	2022-05-17 13:34:35.829	1-Digno Alfredo	14	VISACION DCTOS	1022790	440255	440255	0	27	15
596	2022-05-17 13:34:35.833	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	27	15
597	2022-05-17 13:34:35.837	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	27	15
598	2022-05-17 13:34:35.841	1-Digno Alfredo	14	VISACION DCTOS	1022790	440255	440255	0	27	15
599	2022-05-17 13:34:35.844	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	001-001-0001977	2177149	1979226	197922	27	16
600	2022-05-17 13:34:35.848	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	001-001-0001977	2177149	1979226	197922	27	16
601	2022-05-17 13:34:35.853	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	0	0	0	27	16
602	2022-05-17 13:34:35.857	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	10	9	0	27	16
603	2022-05-17 13:34:35.861	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	27	17
604	2022-05-17 13:34:35.864	1-Digno Alfredo	16	GASTO ADMIN.	001-024-0102815	10000	10000	0	27	17
605	2022-05-17 13:34:35.874	1-Digno Alfredo	16	CDAP PAGOS	001-024-0102815	10000	10000	0	27	17
606	2022-05-17 13:34:35.877	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	27	17
607	2022-05-17 13:34:35.882	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	27	18
608	2022-05-17 13:34:35.896	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	27	18
609	2022-05-17 13:34:35.9	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	27	18
610	2022-05-17 13:34:35.903	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	27	18
611	2022-05-17 13:34:35.907	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	27	19
612	2022-05-17 13:34:35.91	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	27	19
613	2022-05-17 13:34:35.914	1-Digno Alfredo	19	OTROS	x	0	0	0	27	20
614	2022-05-17 13:34:35.918	1-Digno Alfredo	19	OTROS	x	0	0	0	27	20
615	2022-05-17 13:34:35.922	1-Digno Alfredo	19	OTROS	x	0	0	0	27	20
616	2022-05-17 13:37:22	1-Digno Alfredo	1	DERECHO ADUANERO	ytryrtyru	50	50	0	28	2
617	2022-05-17 13:37:22.011	1-Digno Alfredo	2	INDI	ytryrtyru	0	0	0	28	3
618	2022-05-17 13:37:22.017	1-Digno Alfredo	3	I.S.C.	ytryrtyru	0	0	0	28	4
619	2022-05-17 13:37:22.022	1-Digno Alfredo	3	I.S.C.	ytryrtyru	0	0	0	28	4
620	2022-05-17 13:37:22.025	1-Digno Alfredo	4	SERVICIO DE VALORACION	ytryrtyru	0	0	0	28	5
621	2022-05-17 13:37:22.029	1-Digno Alfredo	5	MULTAS VARIAS	ytryrtyru	0	0	0	28	6
622	2022-05-17 13:37:22.033	1-Digno Alfredo	6	IRACIS GENERAL 700	ytryrtyru	0	0	0	28	7
623	2022-05-17 13:37:22.036	1-Digno Alfredo	7	I.V.A.	ytryrtyru	0	0	0	28	8
624	2022-05-17 13:37:22.04	1-Digno Alfredo	8	CANON INFORMATICO	x	0	0	0	28	9
625	2022-05-17 13:37:22.044	1-Digno Alfredo	9	OTROS	x	0	0	0	28	20
626	2022-05-17 13:37:22.05	1-Digno Alfredo	9	CDAP/ANNP	x	0	0	0	28	10
627	2022-05-17 13:37:22.054	1-Digno Alfredo	10	SERVICIOS SOFIA / ANNP	x	0	0	0	28	11
628	2022-05-17 13:37:22.058	1-Digno Alfredo	11	FOTOCOPIAS	x	0	0	0	28	12
629	2022-05-17 13:37:22.07	1-Digno Alfredo	12	APERTURA AG. TRANSP.	x	0	0	0	28	13
630	2022-05-17 13:37:22.078	1-Digno Alfredo	13	TASA PORTUARIA	x	0	0	0	28	14
631	2022-05-17 13:37:22.083	1-Digno Alfredo	14	VISACION DCTOS	x	0	0	0	28	15
632	2022-05-17 13:37:22.092	1-Digno Alfredo	15	HON. DESP. S/ LEY 220/93	x	0	0	0	28	16
633	2022-05-17 13:37:22.101	1-Digno Alfredo	16	GASTO ADMIN.	x	0	0	0	28	17
634	2022-05-17 13:37:22.105	1-Digno Alfredo	17	TASA MIC LPI CONFECCIONES	x	0	0	0	28	18
635	2022-05-17 13:37:22.108	1-Digno Alfredo	17	AGILIZACION MIC	x	0	0	0	28	19
636	2022-05-17 13:37:22.112	1-Digno Alfredo	18	AGILIZACION MIC	x	0	0	0	28	19
637	2022-05-17 13:37:22.116	1-Digno Alfredo	19	OTROS	x	0	0	0	28	20
638	2022-05-17 13:48:27.927	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04009800M	198949	198949	0	29	2
639	2022-05-17 13:48:27.941	5-Angel Rios 	2	INDI	22005IC04009800M	26950	26950	0	29	3
640	2022-05-17 13:48:27.947	5-Angel Rios 	3	I.S.C.	22005IC04009800M	94220	94220	0	29	4
641	2022-05-17 13:48:27.953	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04009800M	380767	380767	0	29	5
642	2022-05-17 13:48:27.957	5-Angel Rios 	5	MULTAS VARIAS	22005IC04009800M	0	0	0	29	6
643	2022-05-17 13:48:27.972	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04009800M	308956	308956	0	29	7
644	2022-05-17 13:48:27.979	5-Angel Rios 	7	I.V.A.	22005IC04009800M	7723912	0	7723912	29	8
645	2022-05-17 13:48:27.998	5-Angel Rios 	8	CANON INFORMATICO	3911308	220127	220127	0	29	9
646	2022-05-17 13:48:28.015	5-Angel Rios 	9	CDAP/ANNP	001-025-0107229	6878	6252	625	29	10
647	2022-05-17 13:48:28.02	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0055015	11236	10214	1021	29	11
648	2022-05-17 13:48:28.029	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017708	110000	100000	10000	29	12
649	2022-05-17 13:48:28.035	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	29	13
650	2022-05-17 13:48:28.044	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003873	449423	408566	40856	29	14
651	2022-05-17 13:48:28.059	5-Angel Rios 	14	VISACION DCTOS	1022790	440255	440255	0	29	15
652	2022-05-17 13:48:28.067	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001977	2177149	1979226	197922	29	16
653	2022-05-17 13:48:28.075	5-Angel Rios 	16	CDAP PAGOS	001-024-0102815	10000	10000	0	29	17
654	2022-05-17 13:48:28.082	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	29	18
655	2022-05-17 13:48:28.091	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	29	19
656	2022-05-17 13:48:28.098	5-Angel Rios 	19	OTROS	x	0	0	0	29	20
657	2022-05-18 09:42:34.545	5-Angel Rios 	1	DERECHO ADUANERO	22031IC04000112@	0	0	0	30	2
658	2022-05-18 09:42:34.551	5-Angel Rios 	2	INDI	22031IC04000112@	61636	61636	0	30	3
659	2022-05-18 09:42:34.557	5-Angel Rios 	3	I.S.C.	22031IC04000112@	0	0	0	30	4
660	2022-05-18 09:42:34.563	5-Angel Rios 	4	SERVICIO DE VALORACION	22031IC04000112@	521858	521858	0	30	5
661	2022-05-18 09:42:34.568	5-Angel Rios 	5	MULTAS VARIAS	22031IC04000112@	0	0	0	30	6
662	2022-05-18 09:42:34.575	5-Angel Rios 	6	IRACIS GENERAL 700	22031IC04000112@	423342	423342	0	30	7
663	2022-05-18 09:42:34.583	5-Angel Rios 	7	I.V.A.	22031IC04000112@	10583547	0	10583547	30	8
664	2022-05-18 09:42:34.587	5-Angel Rios 	8	CANON INFORMATICO	3923112	225127	225127	0	30	9
665	2022-05-18 09:42:34.593	5-Angel Rios 	9	CDAP/ANNP	x	0	0	0	30	10
666	2022-05-18 09:42:34.598	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	30	11
667	2022-05-18 09:42:34.605	5-Angel Rios 	11	FOTOCOPIAS	x	50050	45500	4550	30	12
668	2022-05-18 09:42:34.613	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	30	13
669	2022-05-18 09:42:34.62	5-Angel Rios 	13	TASA PORTUARIA	x	2589950	2354500	235450	30	14
670	2022-05-18 09:42:34.63	5-Angel Rios 	14	VISACION DCTOS	x	880510	880510	0	30	15
671	2022-05-18 09:42:34.634	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	2071857	1883506	188350	30	16
672	2022-05-18 09:42:34.638	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	30	17
673	2022-05-18 09:42:34.644	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	30	18
674	2022-05-18 09:42:34.648	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	30	19
675	2022-05-18 09:42:34.653	5-Angel Rios 	19	OTROS	x	0	0	0	30	20
676	2022-05-18 11:08:03.152	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04001134K	0	0	0	31	2
677	2022-05-18 11:08:03.159	5-Angel Rios 	2	INDI	22029IC04001134K	61636	61636	0	31	3
678	2022-05-18 11:08:03.164	5-Angel Rios 	3	I.S.C.	22029IC04001134K	0	0	0	31	4
679	2022-05-18 11:08:03.169	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04001134K	533899	533899	0	31	5
680	2022-05-18 11:08:03.176	5-Angel Rios 	5	TASA INTERVENCION ADUANERA	22029IC04001134K	342912	342912	0	31	6
681	2022-05-18 11:08:03.18	5-Angel Rios 	6	IRACIS GENERAL 700	22029IC04001134K	433024	433024	0	31	7
682	2022-05-18 11:08:03.187	5-Angel Rios 	7	I.V.A.	22029IC04001134K	10825576	0	10825576	31	8
683	2022-05-18 11:08:03.194	5-Angel Rios 	8	CANON INFORMATICO	3923112	225127	225127	0	31	9
684	2022-05-18 11:08:03.198	5-Angel Rios 	9	CDAP/ANNP	001-024-0103412	10000	9090	909	31	10
685	2022-05-18 11:08:03.208	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	31	11
686	2022-05-18 11:08:03.213	5-Angel Rios 	11	FOTOCOPIAS	001-002-0006893	50050	45500	4550	31	12
687	2022-05-18 11:08:03.217	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	31	13
688	2022-05-18 11:08:03.22	5-Angel Rios 	13	TASA PORTUARIA	001-001-0011368	2589950	2354500	235450	31	14
689	2022-05-18 11:08:03.226	5-Angel Rios 	14	VISACION DCTOS	466840	880510	880510	0	31	15
690	2022-05-18 11:08:03.23	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001979	2600000	2363636	236363	31	16
691	2022-05-18 11:08:03.234	5-Angel Rios 	16	GASTO ADMIN.	572	2000000	2000000	0	31	17
692	2022-05-18 11:08:03.238	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	31	18
693	2022-05-18 11:08:03.243	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	31	19
694	2022-05-18 11:08:03.246	5-Angel Rios 	19	OTROS	x	0	0	0	31	20
695	2022-05-18 11:19:45.249	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04001134K	0	0	0	32	2
696	2022-05-18 11:19:45.254	5-Angel Rios 	2	INDI	22029IC04001134K	61636	61636	0	32	3
697	2022-05-18 11:19:45.26	5-Angel Rios 	3	I.S.C.	22029IC04001134K	0	0	0	32	4
698	2022-05-18 11:19:45.264	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04001134K	533899	533899	0	32	5
699	2022-05-18 11:19:45.271	5-Angel Rios 	5	TASA INTERVENCION ADUANERA	22029IC04001134K	342912	342912	0	32	6
700	2022-05-18 11:19:45.278	5-Angel Rios 	6	IRACIS GENERAL 700	22029IC04001134K	433024	433024	0	32	7
701	2022-05-18 11:19:45.281	5-Angel Rios 	7	I.V.A.	22029IC04001134K	10825576	0	10825576	32	8
702	2022-05-18 11:19:45.286	5-Angel Rios 	8	CANON INFORMATICO	3923112	225127	225127	0	32	9
703	2022-05-18 11:19:45.292	5-Angel Rios 	9	CDAP/ANNP	001-024-0103412	10000	9090	909	32	10
704	2022-05-18 11:19:45.296	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	32	11
705	2022-05-18 11:19:45.299	5-Angel Rios 	11	FOTOCOPIAS	001-002-0006893	50050	45500	4550	32	12
706	2022-05-18 11:19:45.303	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	32	13
707	2022-05-18 11:19:45.309	5-Angel Rios 	13	TASA PORTUARIA	001-001-0011368	2589950	2354500	235450	32	14
708	2022-05-18 11:19:45.313	5-Angel Rios 	14	VISACION DCTOS	466840	880510	880510	0	32	15
709	2022-05-18 11:19:45.318	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001979	2600000	2363636	236363	32	16
710	2022-05-18 11:19:45.323	5-Angel Rios 	16	GASTO ADMIN.	572	2000000	2000000	0	32	17
711	2022-05-18 11:19:45.329	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	32	18
712	2022-05-18 11:19:45.333	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	32	19
713	2022-05-18 11:19:45.337	5-Angel Rios 	19	OTROS	x	0	0	0	32	20
714	2022-05-24 09:46:24.519	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010324F	694322	694322	0	33	2
715	2022-05-24 09:46:24.53	5-Angel Rios 	2	INDI	22005IC04010324F	26950	26950	0	33	3
716	2022-05-24 09:46:24.535	5-Angel Rios 	3	I.S.C.	22005IC04010324F	0	0	0	33	4
717	2022-05-24 09:46:24.549	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010324F	2800415	2800415	0	33	5
718	2022-05-24 09:46:24.556	5-Angel Rios 	5	MULTAS VARIAS	22005IC04010324F	0	0	0	33	6
719	2022-05-24 09:46:24.593	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010324F	2255959	2255959	0	33	7
720	2022-05-24 09:46:24.597	5-Angel Rios 	7	I.V.A.	22005IC04010324F	56398991	0	56398991	33	8
721	2022-05-24 09:46:24.611	5-Angel Rios 	8	CANON INFORMATICO	3937233	220127	220127	0	33	9
722	2022-05-24 09:46:24.615	5-Angel Rios 	9	CDAP/ANNP	001-025-0110658	6834	6212	621	33	10
723	2022-05-24 09:46:24.621	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0058448	68085	61895	6189	33	11
724	2022-05-24 09:46:24.628	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017745	140000	127272	12727	33	12
725	2022-05-24 09:46:24.633	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	33	13
726	2022-05-24 09:46:24.64	5-Angel Rios 	13	TASA PORTUARIA	002-007-0005803	2723417	2475833	247583	33	14
727	2022-05-24 09:46:24.645	5-Angel Rios 	14	VISACION DCTOS	2893966/67/68/69/70	990375	990375	0	33	15
728	2022-05-24 09:46:24.649	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001980	4871034	4428212	442821	33	16
729	2022-05-24 09:46:24.655	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	33	17
730	2022-05-24 09:46:24.662	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	33	18
731	2022-05-24 09:46:24.726	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	33	19
732	2022-05-24 09:46:24.733	5-Angel Rios 	19	OTROS	x	0	0	0	33	20
733	2022-05-24 09:57:11.265	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010326H	0	0	0	34	2
734	2022-05-24 09:57:11.276	5-Angel Rios 	2	INDI	22005IC04010326H	26950	26950	0	34	3
735	2022-05-24 09:57:11.281	5-Angel Rios 	3	I.S.C.	22005IC04010326H	0	0	0	34	4
736	2022-05-24 09:57:11.285	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010326H	2751143	2751143	0	34	5
737	2022-05-24 09:57:11.29	5-Angel Rios 	5	MULTAS VARIAS	22005IC04010326H	0	0	0	34	6
738	2022-05-24 09:57:11.294	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010326H	2213567	2213567	0	34	7
739	2022-05-24 09:57:11.298	5-Angel Rios 	7	I.V.A.	22005IC04010326H	55339164	0	55339164	34	8
740	2022-05-24 09:57:11.303	5-Angel Rios 	8	CANON INFORMATICO	3937241	220127	220127	0	34	9
741	2022-05-24 09:57:11.31	5-Angel Rios 	9	CDAP/ANNP	001-025-0110659	6834	6212	621	34	10
742	2022-05-24 09:57:11.314	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0058446	66887	60806	6080	34	11
743	2022-05-24 09:57:11.318	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017744	70000	63636	6363	34	12
744	2022-05-24 09:57:11.325	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	34	13
745	2022-05-24 09:57:11.337	5-Angel Rios 	13	TASA PORTUARIA	002-007-0005802	2675498	2432270	243227	34	14
746	2022-05-24 09:57:11.345	5-Angel Rios 	14	VISACION DCTOS	02894886/87/88/89	792300	792300	0	34	15
747	2022-05-24 09:57:11.353	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001981	4843391	4403082	440308	34	16
748	2022-05-24 09:57:11.359	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	34	17
749	2022-05-24 09:57:11.363	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	34	18
750	2022-05-24 09:57:11.371	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	34	19
751	2022-05-24 09:57:11.379	5-Angel Rios 	19	OTROS	x	0	0	0	34	20
752	2022-05-24 11:33:53.733	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000231L	72691	72691	0	35	2
753	2022-05-24 11:33:53.752	5-Angel Rios 	2	INDI	22005IM04000231L	14368	14368	0	35	3
754	2022-05-24 11:33:53.772	5-Angel Rios 	3	I.S.C.	22005IM04000231L	0	0	0	35	4
755	2022-05-24 11:33:53.798	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IM04000231L	38220	38220	0	35	5
756	2022-05-24 11:33:53.805	5-Angel Rios 	5	MULTAS VARIAS	22005IM04000231L	0	0	0	35	6
757	2022-05-24 11:33:53.814	5-Angel Rios 	6	IRACIS GENERAL 700	22005IM04000231L	31898	31898	0	35	7
758	2022-05-24 11:33:53.821	5-Angel Rios 	7	I.V.A.	22005IM04000231L	797475	0	797475	35	8
759	2022-05-24 11:33:53.834	5-Angel Rios 	8	CANON INFORMATICO	3937253	44025	44025	0	35	9
760	2022-05-24 11:33:53.844	5-Angel Rios 	9	CDAP/ANNP	001-025-0110660	6834	6212	621	35	10
761	2022-05-24 11:33:53.855	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0058450	1128	1025	102	35	11
762	2022-05-24 11:33:53.902	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017710	100000	90909	9090	35	12
763	2022-05-24 11:33:53.92	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	35	13
764	2022-05-24 11:33:53.937	5-Angel Rios 	13	TASA PORTUARIA	002-007-0005804	45123	41020	4102	35	14
765	2022-05-24 11:33:53.95	5-Angel Rios 	14	VISACION DCTOS	2893977/78	237690	237690	0	35	15
766	2022-05-24 11:33:53.957	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001982	1386000	1260000	126000	35	16
767	2022-05-24 11:33:53.976	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	35	17
768	2022-05-24 11:33:53.985	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	35	18
769	2022-05-24 11:33:54.002	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	35	19
770	2022-05-24 11:33:54.009	5-Angel Rios 	19	OTROS	x	0	0	0	35	20
771	2022-05-24 12:06:25.894	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010303C	0	0	0	36	2
772	2022-05-24 12:06:25.903	5-Angel Rios 	2	INDI	22005IC04010303C	14368	14368	0	36	3
773	2022-05-24 12:06:25.908	5-Angel Rios 	3	I.S.C.	22005IC04010303C	0	0	0	36	4
774	2022-05-24 12:06:25.912	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010303C	2629572	2629572	0	36	5
775	2022-05-24 12:06:25.918	5-Angel Rios 	5	TASA INTERV. ADUANERA	22005IC04010303C	342093	342093	0	36	6
776	2022-05-24 12:06:25.922	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010303C	2115054	2115054	0	36	7
777	2022-05-24 12:06:25.926	5-Angel Rios 	7	I.V.A.	22005IC04010303C	52876354	0	52876354	36	8
778	2022-05-24 12:06:25.93	5-Angel Rios 	8	CANON INFORMATICO	3937260	220127	220127	0	36	9
779	2022-05-24 12:06:25.935	5-Angel Rios 	9	CDAP/ANNP	001-025-0110304	6841	6219	621	36	10
780	2022-05-24 12:06:25.94	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0058090	68711	62464	6246	36	11
781	2022-05-24 12:06:25.949	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017743	60000	54545	5454	36	12
782	2022-05-24 12:06:25.958	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	36	13
783	2022-05-24 12:06:25.962	5-Angel Rios 	13	TASA PORTUARIA	002-007-0005579	2748455	2498595	249859	36	14
784	2022-05-24 12:06:25.968	5-Angel Rios 	14	VISACION DCTOS	1027132	440255	440255	0	36	15
785	2022-05-24 12:06:25.978	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001983	10634517	9667742	966774	36	16
786	2022-05-24 12:06:25.985	5-Angel Rios 	16	CDAP PAGOS	001-024-0105891	10000	10000	0	36	17
787	2022-05-24 12:06:25.992	5-Angel Rios 	17	HON. ASESORAMIENTO	001-001-0001984	220000	220000	0	36	18
788	2022-05-24 12:06:25.997	5-Angel Rios 	17	REPOSICION DE GASTOS 	573	600000	600000	0	36	19
789	2022-05-24 12:06:26.005	5-Angel Rios 	19	OTROS	x	0	0	0	36	20
790	2022-05-24 12:09:32.911	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010303C	0	0	0	37	2
791	2022-05-24 12:09:32.915	5-Angel Rios 	2	INDI	22005IC04010303C	14368	14368	0	37	3
792	2022-05-24 12:09:32.92	5-Angel Rios 	3	I.S.C.	22005IC04010303C	0	0	0	37	4
793	2022-05-24 12:09:32.925	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010303C	2629572	2629572	0	37	5
794	2022-05-24 12:09:32.932	5-Angel Rios 	5	TASA INTERV. ADUANERA	22005IC04010303C	342093	342093	0	37	6
795	2022-05-24 12:09:32.939	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010303C	2115054	2115054	0	37	7
796	2022-05-24 12:09:32.946	5-Angel Rios 	7	I.V.A.	22005IC04010303C	52876354	0	52876354	37	8
797	2022-05-24 12:09:32.951	5-Angel Rios 	8	CANON INFORMATICO	3937260	220127	220127	0	37	9
798	2022-05-24 12:09:32.958	5-Angel Rios 	9	CDAP/ANNP	001-025-0110304	6841	6219	621	37	10
799	2022-05-24 12:09:32.962	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0058090	68711	62464	6246	37	11
800	2022-05-24 12:09:32.969	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017743	60000	54545	5454	37	12
801	2022-05-24 12:09:32.973	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	37	13
802	2022-05-24 12:09:32.978	5-Angel Rios 	13	TASA PORTUARIA	002-007-0005579	2748455	2498595	249859	37	14
803	2022-05-24 12:09:32.982	5-Angel Rios 	14	VISACION DCTOS	1027132	440255	440255	0	37	15
804	2022-05-24 12:09:32.989	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001983	10634517	9667742	966774	37	16
805	2022-05-24 12:09:32.993	5-Angel Rios 	16	CDAP PAGOS	001-024-0105891	10000	10000	0	37	17
806	2022-05-24 12:09:32.997	5-Angel Rios 	17	HON. ASESORAMIENTO	001-001-0001984	220000	220000	0	37	18
807	2022-05-24 12:09:33.003	5-Angel Rios 	17	REPOSICION DE GASTOS 	573	600000	600000	0	37	19
808	2022-05-24 12:09:33.007	5-Angel Rios 	19	OTROS	x	0	0	0	37	20
809	2022-05-26 11:08:33.294	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010454J	0	0	0	38	2
810	2022-05-26 11:08:33.298	5-Angel Rios 	2	INDI	22005IC04010454J	26950	26950	0	38	3
811	2022-05-26 11:08:33.298	5-Angel Rios 	3	I.S.C.	22005IC04010454J	0	0	0	38	4
812	2022-05-26 11:08:33.314	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010454J	847852	847852	0	38	5
813	2022-05-26 11:08:33.314	5-Angel Rios 	5	MULTAS VARIAS	22005IC04010454J	0	0	0	38	6
814	2022-05-26 11:08:33.329	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010454J	683321	683321	0	38	7
815	2022-05-26 11:08:33.392	5-Angel Rios 	7	I.V.A.	22005IC04010454J	17083020	0	17083020	38	8
816	2022-05-26 11:08:33.423	5-Angel Rios 	8	CANON INFORMATICO	3945870	220127	220127	0	38	9
817	2022-05-26 11:08:33.486	5-Angel Rios 	9	CDAP/ANNP	001-025-0111338	6834	6212	621	38	10
818	2022-05-26 11:08:33.501	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0059131	22915	20831	2083	38	11
819	2022-05-26 11:08:33.517	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000146	80000	72727	7272	38	12
820	2022-05-26 11:08:33.517	5-Angel Rios 	12	APERTURA AG. TRANSP.	001-001-0014734	175000	159090	15909	38	13
821	2022-05-26 11:08:33.533	5-Angel Rios 	13	TASA PORTUARIA	002-007-0006079	916585	833259	83325	38	14
822	2022-05-26 11:08:33.533	5-Angel Rios 	14	VISACION DCTOS	2893740/41	220140	220140	0	38	15
823	2022-05-26 11:08:33.548	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001985	2923991	2658173	265817	38	16
824	2022-05-26 11:08:33.548	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	38	17
825	2022-05-26 11:08:33.548	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	38	18
826	2022-05-26 11:08:33.564	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	38	19
827	2022-05-26 11:08:33.564	5-Angel Rios 	19	OTROS	x	0	0	0	38	20
828	2022-05-26 12:03:23.399	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010686Z	0	0	0	39	2
829	2022-05-26 12:03:23.404	5-Angel Rios 	2	INDI	22005IC04010686Z	26950	26950	0	39	3
830	2022-05-26 12:03:23.406	5-Angel Rios 	3	I.S.C.	22005IC04010686Z	0	0	0	39	4
831	2022-05-26 12:03:23.413	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010686Z	1213569	1213569	0	39	5
832	2022-05-26 12:03:23.417	5-Angel Rios 	5	MULTAS VARIAS	22005IC04010686Z	0	0	0	39	6
833	2022-05-26 12:03:23.421	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010686Z	977358	977358	0	39	7
834	2022-05-26 12:03:23.429	5-Angel Rios 	7	I.V.A.	22005IC04010686Z	24433938	0	24433938	39	8
835	2022-05-26 12:03:23.434	5-Angel Rios 	8	CANON INFORMATICO	3953236	220127	220127	0	39	9
836	2022-05-26 12:03:23.445	5-Angel Rios 	9	CDAP/ANNP	001-025-0112843	6862	6238	623	39	10
837	2022-05-26 12:03:23.447	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0060629	33074	30067	3006	39	11
838	2022-05-26 12:03:23.471	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000160	60000	54545	5454	39	12
839	2022-05-26 12:03:23.484	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	39	13
840	2022-05-26 12:03:23.497	5-Angel Rios 	13	TASA PORTUARIA	002-007-0006974	1322960	1202690	120269	39	14
841	2022-05-26 12:03:23.514	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	39	15
842	2022-05-26 12:03:23.524	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001986	3721926	3383569	338356	39	16
843	2022-05-26 12:03:23.534	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	39	17
844	2022-05-26 12:03:23.547	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	39	18
845	2022-05-26 12:03:23.561	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	39	19
846	2022-05-26 12:03:23.571	5-Angel Rios 	19	OTROS	x	0	0	0	39	20
847	2022-05-26 13:42:51.519	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002080V	0	0	0	40	2
848	2022-05-26 13:42:51.525	5-Angel Rios 	2	INDI	22023EC01002080V	0	0	0	40	3
849	2022-05-26 13:42:51.532	5-Angel Rios 	3	I.S.C.	22023EC01002080V	0	0	0	40	4
850	2022-05-26 13:42:51.543	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002080V	0	0	0	40	5
851	2022-05-26 13:42:51.549	5-Angel Rios 	5	MULTAS VARIAS	22023EC01002080V	0	0	0	40	6
852	2022-05-26 13:42:51.554	5-Angel Rios 	6	IRACIS GENERAL 700	22023EC01002080V	0	0	0	40	7
853	2022-05-26 13:42:51.558	5-Angel Rios 	7	I.V.A.	22023EC01002080V	0	0	0	40	8
854	2022-05-26 13:42:51.567	5-Angel Rios 	8	CANON INFORMATICO	3949757	220127	220127	0	40	9
855	2022-05-26 13:42:51.572	5-Angel Rios 	9	CDAP/ANNP	x	0	0	0	40	10
856	2022-05-26 13:42:51.576	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	40	11
857	2022-05-26 13:42:51.58	5-Angel Rios 	11	FOTOCOPIAS	001-001-0022653	50000	45454	4545	40	12
858	2022-05-26 13:42:51.585	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	40	13
859	2022-05-26 13:42:51.59	5-Angel Rios 	13	TASA PORTUARIA	001-001-01222698	66000	60000	6000	40	14
860	2022-05-26 13:42:51.594	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	40	15
861	2022-05-26 13:42:51.597	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001987	3043169	2766517	276651	40	16
862	2022-05-26 13:42:51.603	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	40	17
863	2022-05-26 13:42:51.607	5-Angel Rios 	17	TASA MIC 	1837133	92451	92451	0	40	18
864	2022-05-26 13:42:51.616	5-Angel Rios 	17	TASA UIP	002-002-0007251	88051	88051	0	40	19
865	2022-05-26 13:42:51.622	5-Angel Rios 	19	OTROS	x	0	0	0	40	20
866	2022-05-30 15:11:44.495	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005438V	13843238	13843238	0	41	2
867	2022-05-30 15:11:44.572	5-Angel Rios 	2	INDI	22038IC04005438V	14406	14406	0	41	3
868	2022-05-30 15:11:44.588	5-Angel Rios 	3	I.S.C.	22038IC04005438V	2459377	2459377	0	41	4
869	2022-05-30 15:11:44.588	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005438V	1153603	1153603	0	41	5
870	2022-05-30 15:11:44.603	5-Angel Rios 	5	TASA INTERV. ADUANERA	22038IC04005438V	343003	343003	0	41	6
871	2022-05-30 15:11:44.619	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005438V	993588	993588	0	41	7
872	2022-05-30 15:11:44.634	5-Angel Rios 	7	I.V.A.	22038IC04005438V	24839706	0	24839706	41	8
873	2022-05-30 15:11:44.641	5-Angel Rios 	8	CANON INFORMATICO	22038IC04005438V	225127	225127	0	41	9
874	2022-05-30 15:11:44.641	5-Angel Rios 	9	CDAP/ANNP	001-024-0108608	10000	9090	909	41	10
875	2022-05-30 15:11:44.657	5-Angel Rios 	10	CDAP PAGOS	001-024-0108609	10000	9090	909	41	11
876	2022-05-30 15:11:44.672	5-Angel Rios 	11	FOTOCOPIAS	002-002-0010191	45150	41045	4104	41	12
877	2022-05-30 15:11:44.672	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	41	13
878	2022-05-30 15:11:44.688	5-Angel Rios 	13	TASA PORTUARIA	002-005-0002883	686070	623700	62370	41	14
879	2022-05-30 15:11:44.703	5-Angel Rios 	14	VISACION DCTOS	1031336	880510	880510	0	41	15
880	2022-05-30 15:11:44.719	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001988	2973963	2703602	270360	41	16
881	2022-05-30 15:11:44.735	5-Angel Rios 	16	GASTO ADMIN.	001-001-0001989	660000	660000	0	41	17
882	2022-05-30 15:11:44.741	5-Angel Rios 	17	MOPC 	3957635	54026	54026	0	41	18
883	2022-05-30 15:11:44.757	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	41	19
884	2022-05-30 15:11:44.772	5-Angel Rios 	19	OTROS	x	0	0	0	41	20
885	2022-05-30 15:14:35.511	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005438V	13843238	13843238	0	42	2
886	2022-05-30 15:14:35.511	5-Angel Rios 	2	INDI	22038IC04005438V	14406	14406	0	42	3
887	2022-05-30 15:14:35.511	5-Angel Rios 	3	I.S.C.	22038IC04005438V	2459377	2459377	0	42	4
888	2022-05-30 15:14:35.511	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005438V	1153603	1153603	0	42	5
889	2022-05-30 15:14:35.527	5-Angel Rios 	5	TASA INTERV. ADUANERA	22038IC04005438V	343003	343003	0	42	6
890	2022-05-30 15:14:35.527	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005438V	993588	993588	0	42	7
891	2022-05-30 15:14:35.543	5-Angel Rios 	7	I.V.A.	22038IC04005438V	24839706	0	24839706	42	8
892	2022-05-30 15:14:35.543	5-Angel Rios 	8	CANON INFORMATICO	22038IC04005438V	225127	225127	0	42	9
893	2022-05-30 15:14:35.558	5-Angel Rios 	9	CDAP/ANNP	001-024-0108608	10000	9090	909	42	10
894	2022-05-30 15:14:35.558	5-Angel Rios 	10	CDAP PAGOS	001-024-0108609	10000	9090	909	42	11
895	2022-05-30 15:14:35.558	5-Angel Rios 	11	FOTOCOPIAS	002-002-0010191	45150	41045	4104	42	12
896	2022-05-30 15:14:35.574	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	42	13
897	2022-05-30 15:14:35.574	5-Angel Rios 	13	TASA PORTUARIA	002-005-0002883	686070	623700	62370	42	14
898	2022-05-30 15:14:35.589	5-Angel Rios 	14	VISACION DCTOS	1031336	880510	880510	0	42	15
899	2022-05-30 15:14:35.589	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001988	2973963	2703602	270360	42	16
900	2022-05-30 15:14:35.589	5-Angel Rios 	16	GASTO ADMIN.	001-001-0001989	660000	660000	0	42	17
901	2022-05-30 15:14:35.589	5-Angel Rios 	17	MOPC 	3957635	44026	44026	0	42	18
902	2022-05-30 15:14:35.605	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	42	19
903	2022-05-30 15:14:35.621	5-Angel Rios 	19	OTROS	x	0	0	0	42	20
904	2022-05-31 09:57:57.779	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04010983Z	0	0	0	43	2
905	2022-05-31 09:57:57.785	5-Angel Rios 	2	INDI	22005IC04010983Z	26950	26950	0	43	3
906	2022-05-31 09:57:57.792	5-Angel Rios 	3	I.S.C.	22005IC04010983Z	0	0	0	43	4
907	2022-05-31 09:57:57.799	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04010983Z	1710075	1710075	0	43	5
908	2022-05-31 09:57:57.813	5-Angel Rios 	5	MULTAS VARIAS	22005IC04010983Z	0	0	0	43	6
909	2022-05-31 09:57:57.822	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04010983Z	1376547	1376547	0	43	7
910	2022-05-31 09:57:57.828	5-Angel Rios 	7	I.V.A.	22005IC04010983Z	34413680	0	34413680	43	8
911	2022-05-31 09:57:57.832	5-Angel Rios 	8	CANON INFORMATICO	3968367	220127	220127	0	43	9
912	2022-05-31 09:57:57.836	5-Angel Rios 	9	CDAP/ANNP	001-025-0114819	6868	6243	624	43	10
913	2022-05-31 09:57:57.841	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0062620	46110	41918	4191	43	11
914	2022-05-31 09:57:57.845	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000186	90000	81818	8181	43	12
915	2022-05-31 09:57:57.85	5-Angel Rios 	12	TASA PORTUARIA 	002-007-0008072	1844403	1676730	167673	43	13
916	2022-05-31 09:57:57.853	5-Angel Rios 	13	TASA  PORTUARIA DIF	002-007-0008394	49500	45000	4500	43	14
917	2022-05-31 09:57:57.861	5-Angel Rios 	14	VISACION DCTOS	x2898601/02/03/04/05	924000	924000	0	43	15
918	2022-05-31 09:57:57.865	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001990	4259351	3872137	387213	43	16
919	2022-05-31 09:57:57.869	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	43	17
920	2022-05-31 09:57:57.873	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	43	18
921	2022-05-31 09:57:57.879	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	43	19
922	2022-05-31 09:57:57.884	5-Angel Rios 	19	OTROS	x	0	0	0	43	20
923	2022-05-31 10:21:46.336	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011037H	0	0	0	44	2
924	2022-05-31 10:21:46.343	5-Angel Rios 	2	INDI	22005IC04011037H	26951	26951	0	44	3
925	2022-05-31 10:21:46.347	5-Angel Rios 	3	I.S.C.	22005IC04011037H	0	0	0	44	4
926	2022-05-31 10:21:46.351	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011037H	645994	645994	0	44	5
927	2022-05-31 10:21:46.358	5-Angel Rios 	5	MULTAS VARIAS	22005IC04011037H	0	0	0	44	6
928	2022-05-31 10:21:46.362	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011037H	521028	521028	0	44	7
929	2022-05-31 10:21:46.366	5-Angel Rios 	7	I.V.A.	22005IC04011037H	13025674	0	13025674	44	8
930	2022-05-31 10:21:46.37	5-Angel Rios 	8	CANON INFORMATICO	3985967	220127	220127	0	44	9
931	2022-05-31 10:21:46.375	5-Angel Rios 	9	CDAP/ANNP	001-025-0115275	6862	6238	623	44	10
932	2022-05-31 10:21:46.379	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0063060	20233	18393	1839	44	11
933	2022-05-31 10:21:46.386	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000185	90000	81818	8181	44	12
934	2022-05-31 10:21:46.39	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	44	13
935	2022-05-31 10:21:46.395	5-Angel Rios 	13	TASA PORTUARIA	002-007-0008308	809329	735753	73575	44	14
936	2022-05-31 10:21:46.399	5-Angel Rios 	14	VISACION DCTOS	1031063	440255	440255	0	44	15
937	2022-05-31 10:21:46.404	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001991	3521093	3200993	320099	44	16
938	2022-05-31 10:21:46.41	5-Angel Rios 	16	CDAP PAGOS	001-024-0109487	10000	10000	0	44	17
939	2022-05-31 10:21:46.418	5-Angel Rios 	17	TASA LPF MAPES	3985688	155290	155290	0	44	18
940	2022-05-31 10:21:46.422	5-Angel Rios 	19	OTROS	x	0	0	0	44	20
941	2022-05-31 10:24:19.888	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011037H	0	0	0	45	2
942	2022-05-31 10:24:19.894	5-Angel Rios 	2	INDI	22005IC04011037H	26951	26951	0	45	3
943	2022-05-31 10:24:19.898	5-Angel Rios 	3	I.S.C.	22005IC04011037H	0	0	0	45	4
944	2022-05-31 10:24:19.902	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011037H	645994	645994	0	45	5
945	2022-05-31 10:24:19.907	5-Angel Rios 	5	MULTAS VARIAS	22005IC04011037H	0	0	0	45	6
946	2022-05-31 10:24:19.911	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011037H	521028	521028	0	45	7
947	2022-05-31 10:24:19.918	5-Angel Rios 	7	I.V.A.	22005IC04011037H	13025674	0	13025674	45	8
948	2022-05-31 10:24:19.922	5-Angel Rios 	8	CANON INFORMATICO	3985967	220127	220127	0	45	9
949	2022-05-31 10:24:19.928	5-Angel Rios 	9	CDAP/ANNP	001-025-0115275	6862	6238	623	45	10
950	2022-05-31 10:24:19.931	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0063060	20233	18393	1839	45	11
951	2022-05-31 10:24:19.944	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000185	90000	81818	8181	45	12
952	2022-05-31 10:24:19.948	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	45	13
953	2022-05-31 10:24:19.952	5-Angel Rios 	13	TASA PORTUARIA	002-007-0008308	809329	735753	73575	45	14
954	2022-05-31 10:24:19.961	5-Angel Rios 	14	VISACION DCTOS	1031063	440255	440255	0	45	15
955	2022-05-31 10:24:19.965	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001991	3521093	3200993	320099	45	16
956	2022-05-31 10:24:19.969	5-Angel Rios 	16	CDAP PAGOS	001-024-0109487	10000	10000	0	45	17
957	2022-05-31 10:24:19.974	5-Angel Rios 	17	TASA LPF MAPES	3985688	155290	155290	0	45	18
958	2022-05-31 10:24:19.979	5-Angel Rios 	19	OTROS	x	0	0	0	45	20
959	2022-05-31 10:54:14.069	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011020W	0	0	0	46	2
960	2022-05-31 10:54:14.077	5-Angel Rios 	2	INDI	22005IC04011020W	26949	26949	0	46	3
961	2022-05-31 10:54:14.084	5-Angel Rios 	3	I.S.C.	22005IC04011020W	0	0	0	46	4
962	2022-05-31 10:54:14.089	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011020W	170342	170342	0	46	5
963	2022-05-31 10:54:14.093	5-Angel Rios 	5	MULTAS VARIAS	22005IC04011020W	0	0	0	46	6
964	2022-05-31 10:54:14.103	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011020W	138602	138602	0	46	7
965	2022-05-31 10:54:14.109	5-Angel Rios 	7	I.V.A.	22005IC04011020W	3465058	0	3465058	46	8
966	2022-05-31 10:54:14.114	5-Angel Rios 	8	CANON INFORMATICO	3981502	220127	220127	0	46	9
967	2022-05-31 10:54:14.119	5-Angel Rios 	9	CDAP/ANNP	001-025-0115141	6862	6238	623	46	10
968	2022-05-31 10:54:14.128	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0062926	6282	5710	571	46	11
969	2022-05-31 10:54:14.132	5-Angel Rios 	11	FOTOCOPIAS	001-003-0000184	90000	81818	8181	46	12
970	2022-05-31 10:54:14.141	5-Angel Rios 	12	APERTURA AG. TRANSP.	001-001-0014890	175000	81818	8182	46	13
971	2022-05-31 10:54:14.145	5-Angel Rios 	13	TASA PORTUARIA	002-007-0008244	251263	228420	22842	46	14
972	2022-05-31 10:54:14.148	5-Angel Rios 	14	VISACION DCTOS CO	1032088	440255	440255	0	46	15
973	2022-05-31 10:54:14.152	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001992	1751801	1592546	159254	46	16
974	2022-05-31 10:54:14.156	5-Angel Rios 	16	CDAP PAGOS	001-024-0109513	10000	10000	0	46	17
975	2022-05-31 10:54:14.161	5-Angel Rios 	17	VISACION DCTOS CONSUL	2896561/62	221760	221760	0	46	18
976	2022-05-31 10:54:14.165	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	46	19
977	2022-05-31 10:54:14.169	5-Angel Rios 	19	OTROS	x	0	0	0	46	20
978	2022-06-02 14:40:56.014	5-Angel Rios 	1	DERECHO ADUANERO	22002IM04005115Y	0	0	0	47	2
979	2022-06-02 14:40:56.019	5-Angel Rios 	2	INDI	22002IM04005115Y	0	0	0	47	3
980	2022-06-02 14:40:56.027	5-Angel Rios 	3	I.S.C.	22002IM04005115Y	122115	122115	0	47	4
981	2022-06-02 14:40:56.035	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IM04005115Y	60240	60240	0	47	5
982	2022-06-02 14:40:56.043	5-Angel Rios 	5	MULTAS VARIAS	22002IM04005115Y	0	0	0	47	6
983	2022-06-02 14:40:56.049	5-Angel Rios 	6	IRACIS GENERAL 700	22002IM04005115Y	49334	49334	0	47	7
984	2022-06-02 14:40:56.058	5-Angel Rios 	7	I.V.A.	22002IM04005115Y	123336	0	123336	47	8
985	2022-06-02 14:40:56.067	5-Angel Rios 	8	CANON INFORMATICO	3987994	49025	49025	0	47	9
986	2022-06-02 14:40:56.072	5-Angel Rios 	9	CDAP DINAC	001-021-0041360	20668	18789	1878	47	10
987	2022-06-02 14:40:56.077	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	47	11
988	2022-06-02 14:40:56.082	5-Angel Rios 	11	FOTOCOPIAS	004-003-0025311	42000	38181	3818	47	12
989	2022-06-02 14:40:56.089	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	47	13
990	2022-06-02 14:40:56.095	5-Angel Rios 	13	TASA PORTUARIA	001-034-0037279	524382	476710	47671	47	14
991	2022-06-02 14:40:56.099	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	47	15
992	2022-06-02 14:40:56.103	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	750000	681818	68181	47	16
993	2022-06-02 14:40:56.109	5-Angel Rios 	16	GASTO ADMIN.	586	689048	689048	0	47	17
994	2022-06-02 14:40:56.114	5-Angel Rios 	17	CORRECCION DE GUIA 	587	375000	375000	0	47	18
995	2022-06-02 14:40:56.118	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	47	19
996	2022-06-02 14:40:56.122	5-Angel Rios 	19	OTROS	x	0	0	0	47	20
997	2022-06-02 14:51:36.414	5-Angel Rios 	1	DERECHO ADUANERO	22002IM04005115Y	0	0	0	48	2
998	2022-06-02 14:51:36.418	5-Angel Rios 	2	INDI	22002IM04005115Y	0	0	0	48	3
999	2022-06-02 14:51:36.422	5-Angel Rios 	3	I.S.C.	22002IM04005115Y	122115	122115	0	48	4
1000	2022-06-02 14:51:36.428	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IM04005115Y	60240	60240	0	48	5
1001	2022-06-02 14:51:36.434	5-Angel Rios 	5	048-MULTAS VARIAS/EXPEDIENTE	22002IM04005115Y	879360	879360	0	48	6
1002	2022-06-02 14:51:36.438	5-Angel Rios 	6	IRACIS GENERAL 700	3963343	49334	49334	0	48	7
1003	2022-06-02 14:51:36.444	5-Angel Rios 	7	I.V.A. TURISMO	22002IM04005115Y	123336	0	123336	48	8
1004	2022-06-02 14:51:36.448	5-Angel Rios 	8	CANON INFORMATICO	3987994	49025	49025	0	48	9
1005	2022-06-02 14:51:36.453	5-Angel Rios 	9	CDAP DINAC	001-021-0041360	20668	18789	1878	48	10
1006	2022-06-02 14:51:36.456	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	48	11
1007	2022-06-02 14:51:36.462	5-Angel Rios 	11	FOTOCOPIAS	004-003-0025311	42000	38181	3818	48	12
1008	2022-06-02 14:51:36.466	5-Angel Rios 	12		x	0	0	0	48	13
1009	2022-06-02 14:51:36.471	5-Angel Rios 	13	TASA AERO-PORTUARIA	001-034-0037279	524382	476710	47671	48	14
1010	2022-06-02 14:51:36.48	5-Angel Rios 	14	VISACION DCTOS	x	0	0	0	48	15
1011	2022-06-02 14:51:36.484	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	825000	750000	75000	48	16
1012	2022-06-02 14:51:36.492	5-Angel Rios 	16	GASTO ADMIN.	586	689048	689048	0	48	17
1013	2022-06-02 14:51:36.497	5-Angel Rios 	17	CORRECCION DE GUIA 	587	375000	375000	0	48	18
1014	2022-06-02 14:51:36.501	5-Angel Rios 	19	OTROS	x	0	0	0	48	20
1015	2022-06-02 16:47:01.077	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005516S	41116192	41116192	0	49	2
1016	2022-06-02 16:47:01.084	5-Angel Rios 	2	INDI	22038IC04005516S	14403	14403	0	49	3
1017	2022-06-02 16:47:01.084	5-Angel Rios 	3	I.S.C.	22038IC04005516S	0	0	0	49	4
1018	2022-06-02 16:47:01.084	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005516S	1784217	1784217	0	49	5
1019	2022-06-02 16:47:01.099	5-Angel Rios 	5	TASA INTERV ADUANERA	22038IC04005516S	342930	342930	0	49	6
1020	2022-06-02 16:47:01.099	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005516S	1599856	1599856	0	49	7
1021	2022-06-02 16:47:01.115	5-Angel Rios 	7	I.V.A.	22038IC04005516S	39996403	0	39996403	49	8
1022	2022-06-02 16:47:01.115	5-Angel Rios 	8	CANON INFORMATICO	22038IC04005516S	225127	225127	0	49	9
1023	2022-06-02 16:47:01.131	5-Angel Rios 	9	CDAP/ANNP 1013089	001-024-0097577	10000	9090	909	49	10
1024	2022-06-02 16:47:01.131	5-Angel Rios 	10	CDAP PAGOS 1010283	001-024-0094880	10000	9090	909	49	11
1025	2022-06-02 16:47:01.146	5-Angel Rios 	11	FOTOCOPIAS	002-002-0010288	42000	38181	3818	49	12
1026	2022-06-02 16:47:01.146	5-Angel Rios 	12	CDAP PAGOS	001-024-0110945	10000	9090	909	49	13
1027	2022-06-02 16:47:01.146	5-Angel Rios 	13	TASA PORTUARIA	002-005-0002883	1152658	1047870	104787	49	14
1028	2022-06-02 16:47:01.162	5-Angel Rios 	14	VISACION DCTOS	453788	880510	880510	0	49	15
1029	2022-06-02 16:47:01.162	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001994	4257583	3870530	387053	49	16
1030	2022-06-02 16:47:01.178	5-Angel Rios 	16	GASTO ADMIN.	558	660000	660000	0	49	17
1031	2022-06-02 16:47:01.178	5-Angel Rios 	17	INFORME INTN	X	140000	140000	0	49	18
1032	2022-06-02 16:47:01.184	5-Angel Rios 	18	MOPC 	477875	44026	44026	0	49	19
1033	2022-06-02 16:47:01.184	5-Angel Rios 	19	LICENCIA PREVIA	457866	616357	560324	56032	49	20
1034	2022-06-03 12:18:34.453	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005516S	41116192	41116192	0	50	2
1035	2022-06-03 12:18:34.461	5-Angel Rios 	2	INDI	22038IC04005516S	14403	14403	0	50	3
1036	2022-06-03 12:18:34.468	5-Angel Rios 	3	I.S.C.	22038IC04005516S	0	0	0	50	4
1037	2022-06-03 12:18:34.475	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005516S	1784217	1784217	0	50	5
1038	2022-06-03 12:18:34.48	5-Angel Rios 	5	TASA INTERV ADUANERA	22038IC04005516S	342930	342930	0	50	6
1039	2022-06-03 12:18:34.486	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005516S	1599856	1599856	0	50	7
1040	2022-06-03 12:18:34.497	5-Angel Rios 	7	I.V.A.	22038IC04005516S	39996403	0	39996403	50	8
1041	2022-06-03 12:18:34.505	5-Angel Rios 	8	CANON INFORMATICO	22038IC04005516S	225127	225127	0	50	9
1042	2022-06-03 12:18:34.509	5-Angel Rios 	9	CDAP/ANNP 1013089	001-024-0097577	10000	9090	909	50	10
1043	2022-06-03 12:18:34.516	5-Angel Rios 	10	CDAP PAGOS 1010283	001-024-0094880	10000	9090	909	50	11
1044	2022-06-03 12:18:34.523	5-Angel Rios 	11	FOTOCOPIAS	002-002-0010288	42000	38181	3818	50	12
1045	2022-06-03 12:18:34.527	5-Angel Rios 	12	CDAP PAGOS	001-024-0110945	10000	9090	909	50	13
1046	2022-06-03 12:18:34.532	5-Angel Rios 	13	TASA PORTUARIA	002-005-0002883	1152658	1047870	104787	50	14
1047	2022-06-03 12:18:34.54	5-Angel Rios 	14	VISACION DCTOS	453788	880510	880510	0	50	15
1048	2022-06-03 12:18:34.548	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001994	4257583	3870530	387053	50	16
1049	2022-06-03 12:18:34.555	5-Angel Rios 	16	GASTO ADMIN.	001-001-0001995	660000	660000	0	50	17
1050	2022-06-03 12:18:34.559	5-Angel Rios 	17	INFORME INTN	X	140000	140000	0	50	18
1051	2022-06-03 12:18:34.563	5-Angel Rios 	18	MOPC 	477875	44026	44026	0	50	19
1052	2022-06-03 12:18:34.569	5-Angel Rios 	19	LICENCIA PREVIA	457866	616357	560324	56032	50	20
1053	2022-06-03 14:58:46.13	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011310B	337089	337089	0	51	2
1054	2022-06-03 14:58:46.138	5-Angel Rios 	2	INDI	22005IC04011310B	26948	26948	0	51	3
1055	2022-06-03 14:58:46.142	5-Angel Rios 	3	I.S.C.	22005IC04011310B	0	0	0	51	4
1056	2022-06-03 14:58:46.15	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011310B	1875112	1875112	0	51	5
1057	2022-06-03 14:58:46.155	5-Angel Rios 	5	MULTAS VARIAS	22005IC04011310B	0	0	0	51	6
1058	2022-06-03 14:58:46.159	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011310B	1510586	1510586	0	51	7
1059	2022-06-03 14:58:46.162	5-Angel Rios 	7	I.V.A.	22005IC04011310B	37764646	0	37764646	51	8
1060	2022-06-03 14:58:46.168	5-Angel Rios 	8	CANON INFORMATICO	4010569	220127	220127	0	51	9
1061	2022-06-03 14:58:46.172	5-Angel Rios 	9	CDAP/ANNP	001-025-0117456	6854	6230	623	51	10
1062	2022-06-03 14:58:46.178	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0065250	44965	40877	4087	51	11
1063	2022-06-03 14:58:46.184	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017760	100000	90909	9090	51	12
1064	2022-06-03 14:58:46.188	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	51	13
1065	2022-06-03 14:58:46.192	5-Angel Rios 	13	TASA PORTUARIA	002-007-0009326	1798594	1635085	163508	51	14
1066	2022-06-03 14:58:46.196	5-Angel Rios 	14	VISACION DCTOS	2901918/19/20/21	750400	750400	0	51	15
1067	2022-06-03 14:58:46.203	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001996	4351938	3956307	395630	51	16
1068	2022-06-03 14:58:46.21	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	51	17
1069	2022-06-03 14:58:46.214	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	51	18
1070	2022-06-03 14:58:46.219	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	51	19
1071	2022-06-03 14:58:46.225	5-Angel Rios 	19	OTROS	x	0	0	0	51	20
1072	2022-06-03 14:58:46.229	5-Angel Rios 	20	OTROS	x	0	0	0	51	20
1073	2022-06-03 15:23:24.209	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005516S	41116192	41116192	0	52	2
1074	2022-06-03 15:23:24.218	5-Angel Rios 	2	INDI	22038IC04005516S	14403	14403	0	52	3
1075	2022-06-03 15:23:24.22	5-Angel Rios 	3	I.S.C.	22038IC04005516S	0	0	0	52	4
1076	2022-06-03 15:23:24.224	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005516S	1784217	1784217	0	52	5
1077	2022-06-03 15:23:24.23	5-Angel Rios 	5	MULTAS VARIAS	22038IC04005516S	342930	342930	0	52	6
1078	2022-06-03 15:23:24.234	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005516S	1599856	1599856	0	52	7
1079	2022-06-03 15:23:24.238	5-Angel Rios 	7	I.V.A.	22038IC04005516S	39996403	0	39996403	52	8
1080	2022-06-03 15:23:24.244	5-Angel Rios 	8	CANON INFORMATICO	22038IC04005516S	225127	225127	0	52	9
1081	2022-06-03 15:23:24.253	5-Angel Rios 	9	CDAP/ANNP	001-024-0110945	10000	9090	909	52	10
1082	2022-06-03 15:23:24.26	5-Angel Rios 	10	CDAP PAGOS	001-024-0097577	10000	9090	909	52	11
1083	2022-06-03 15:23:24.266	5-Angel Rios 	11	FOTOCOPIAS	002-002-0010268	42000	38181	3818	52	12
1084	2022-06-03 15:23:24.277	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	52	13
1085	2022-06-03 15:23:24.281	5-Angel Rios 	13	TASA PORTUARIA	002-007-0002985	1152658	1047870	104787	52	14
1086	2022-06-03 15:23:24.287	5-Angel Rios 	14	VISACION DCTOS	453788	880510	880510	0	52	15
1087	2022-06-03 15:23:24.291	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001994	4257583	3870530	387053	52	16
1088	2022-06-03 15:23:24.295	5-Angel Rios 	16	GASTO ADMIN.	001-001-0001995	660000	660000	0	52	17
1089	2022-06-03 15:23:24.3	5-Angel Rios 	17	CDAP PAGOS	001-024-0094880	10000	10000	0	52	19
1090	2022-06-03 15:23:24.304	5-Angel Rios 	17	GASTO ADMIN.	588	4000000	4000000	0	52	18
1091	2022-06-03 15:23:24.308	5-Angel Rios 	19	MOPC	4002259	44026	40023	4002	52	20
1092	2022-06-03 15:23:24.312	5-Angel Rios 	20	LICENCIA PREVIA MIC	457866	616357	560324	56032	52	20
1093	2022-06-03 15:23:24.319	5-Angel Rios 	21	PERMISO INTN	001-002-0020688	140000	140000	0	52	15
1094	2022-06-06 09:41:45.482	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011379Z	0	0	0	53	2
1095	2022-06-06 09:41:45.491	5-Angel Rios 	2	INDI	22005IC04011379Z	31518	31518	0	53	3
1096	2022-06-06 09:41:45.498	5-Angel Rios 	3	I.S.C.	22005IC04011379Z	0	0	0	53	4
1097	2022-06-06 09:41:45.503	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011379Z	4459572	4459572	0	53	5
1098	2022-06-06 09:41:45.509	5-Angel Rios 	5	TASA INTERV ADUANERA 	22005IC04011379Z	342820	342820	0	53	6
1099	2022-06-06 09:41:45.515	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011379Z	3587423	3587423	0	53	7
1100	2022-06-06 09:41:45.522	5-Angel Rios 	7	I.V.A.	22005IC04011379Z	89685566	0	89685566	53	8
1101	2022-06-06 09:41:45.526	5-Angel Rios 	8	CANON INFORMATICO	4018417	220127	220127	0	53	9
1102	2022-06-06 09:41:45.531	5-Angel Rios 	9	CDAP/ANNP	001-025-0118082	6855	6231	623	53	10
1103	2022-06-06 09:41:45.546	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0065867	115757	105233	10523	53	11
1104	2022-06-06 09:41:45.551	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017763	60000	54545	5454	53	12
1105	2022-06-06 09:41:45.556	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	53	13
1106	2022-06-06 09:41:45.56	5-Angel Rios 	13	TASA PORTUARIA	002-007-0009613	4630282	4209347	420934	53	14
1107	2022-06-06 09:41:45.566	5-Angel Rios 	14	VISACION DCTOS	1036957	440255	440255	0	53	15
1108	2022-06-06 09:41:45.571	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001998	12942317	11765742	1176574	53	16
1109	2022-06-06 09:41:45.582	5-Angel Rios 	16	MIPYMES TASA MIC 	1751877	620757	620757	0	53	17
1110	2022-06-06 09:41:45.588	5-Angel Rios 	17	APORTE VUE	1751878	74797	74797	0	53	19
1111	2022-06-06 09:41:45.593	5-Angel Rios 	17	GASTOS ADM.	001-001-0001999	220000	220000	0	53	18
1112	2022-06-06 09:41:45.597	5-Angel Rios 	19	OTROS	x	0	0	0	53	20
1113	2022-06-06 09:41:45.601	5-Angel Rios 	20	OTROS	x	0	0	0	53	20
1114	2022-06-06 09:41:45.607	5-Angel Rios 	21	VISACION DCTOS	x	0	0	0	53	15
1115	2022-06-06 10:14:36.265	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000258U	412053	412053	0	54	2
1116	2022-06-06 10:14:36.272	5-Angel Rios 	2	INDI	22005IM04000258U	14394	14394	0	54	3
1117	2022-06-06 10:14:36.273	5-Angel Rios 	3	I.S.C.	22005IM04000258U	0	0	0	54	4
1118	2022-06-06 10:14:36.273	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IM04000258U	45999	45999	0	54	5
1119	2022-06-06 10:14:36.289	5-Angel Rios 	5	MULTAS VARIAS	22005IM04000258U	0	0	0	54	6
1120	2022-06-06 10:14:36.289	5-Angel Rios 	6	IRACIS GENERAL 700	22005IM04000258U	39511	39511	0	54	7
1121	2022-06-06 10:14:36.305	5-Angel Rios 	7	I.V.A.	22005IM04000258U	987782	0	987782	54	8
1122	2022-06-06 10:14:36.305	5-Angel Rios 	8	CANON INFORMATICO	4010556	44025	44025	0	54	9
1123	2022-06-06 10:14:36.32	5-Angel Rios 	9	CDAP/ANNP	001-025-0117451	6854	6230	623	54	10
1124	2022-06-06 10:14:36.32	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0065237	1342	1220	122	54	11
1125	2022-06-06 10:14:36.32	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017759	80000	72727	7272	54	12
1126	2022-06-06 10:14:36.336	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	54	13
1127	2022-06-06 10:14:36.336	5-Angel Rios 	13	TASA PORTUARIA	002-007-0009323	53679	48799	4879	54	14
1128	2022-06-06 10:14:36.351	5-Angel Rios 	14	VISACION DCTOS	2902411/12	225120	225120	0	54	15
1129	2022-06-06 10:14:36.351	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002001	1056000	960000	96000	54	16
1130	2022-06-06 10:14:36.367	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	54	17
1131	2022-06-06 10:14:36.367	5-Angel Rios 	17	AGILIZACION MIC	x	0	0	0	54	19
1132	2022-06-06 10:14:36.367	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	54	18
1133	2022-06-06 16:12:55.21	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005564V	70924973	70924973	0	55	2
1134	2022-06-06 16:12:55.214	5-Angel Rios 	2	INDI	22038IC04005564V	14399	14399	0	55	3
1135	2022-06-06 16:12:55.226	5-Angel Rios 	3	I.S.C.	22038IC04005564V	0	0	0	55	4
1136	2022-06-06 16:12:55.235	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005564V	2991449	2991449	0	55	5
1137	2022-06-06 16:12:55.243	5-Angel Rios 	5	TASA INTERV. ADUANERA	22038IC04005564V	342820	342820	0	55	6
1138	2022-06-06 16:12:55.247	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005564V	2689705	2689705	0	55	7
1139	2022-06-06 16:12:55.255	5-Angel Rios 	7	I.V.A.	22038IC04005564V	67242621	0	67242621	55	8
1140	2022-06-06 16:12:55.259	5-Angel Rios 	8	CANON INFORMATICO	4017801	225127	225127	0	55	9
1141	2022-06-06 16:12:55.263	5-Angel Rios 	9	CDAP/ANNP	001-024-0112257	10000	9090	909	55	10
1142	2022-06-06 16:12:55.269	5-Angel Rios 	10	CDAP PAGOS 	001-024-0112258	10000	9090	909	55	11
1143	2022-06-06 16:12:55.271	5-Angel Rios 	11	FOTOCOPIAS	001-001-0012805	106400	96727	9672	55	12
1144	2022-06-06 16:12:55.277	5-Angel Rios 	12	MOPC	479827	44026	40023	4002	55	13
1145	2022-06-06 16:12:55.281	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003018	685080	622800	62280	55	14
1146	2022-06-06 16:12:55.284	5-Angel Rios 	14	VISACION DCTOS	479826	880510	880510	0	55	15
1147	2022-06-06 16:12:55.292	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	5054356	4594869	459486	55	16
1148	2022-06-06 16:12:55.297	5-Angel Rios 	16	GASTO ADMIN.	x	660000	660000	0	55	17
1149	2022-06-06 16:12:55.306	5-Angel Rios 	17	TASA INTERV. ANNP	7017814	342819	342819	0	55	19
1150	2022-06-06 16:12:55.311	5-Angel Rios 	17	TASA MIC LPI CONFECCIONES	x	0	0	0	55	18
1151	2022-06-06 16:12:55.317	5-Angel Rios 	19	OTROS	x	0	0	0	55	20
1152	2022-06-06 16:12:55.328	5-Angel Rios 	20	OTROS	x	0	0	0	55	20
1153	2022-06-06 16:12:55.336	5-Angel Rios 	21	VISACION DCTOS	x	0	0	0	55	15
1154	2022-06-06 16:23:07.108	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04005564V	70924973	70924973	0	56	2
1155	2022-06-06 16:23:07.112	5-Angel Rios 	2	INDI	22038IC04005564V	14399	14399	0	56	3
1156	2022-06-06 16:23:07.12	5-Angel Rios 	3	I.S.C.	22038IC04005564V	0	0	0	56	4
1157	2022-06-06 16:23:07.137	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04005564V	2991449	2991449	0	56	5
1158	2022-06-06 16:23:07.143	5-Angel Rios 	5	TASA INTERV. ADUANERA	22038IC04005564V	342820	342820	0	56	6
1159	2022-06-06 16:23:07.147	5-Angel Rios 	6	IRACIS GENERAL 700	22038IC04005564V	2689705	2689705	0	56	7
1160	2022-06-06 16:23:07.151	5-Angel Rios 	7	I.V.A.	22038IC04005564V	67242621	0	67242621	56	8
1161	2022-06-06 16:23:07.159	5-Angel Rios 	8	CANON INFORMATICO	4017801	225127	225127	0	56	9
1162	2022-06-06 16:23:07.163	5-Angel Rios 	9	CDAP/ANNP	001-024-0112257	10000	9090	909	56	10
1163	2022-06-06 16:23:07.168	5-Angel Rios 	10	CDAP PAGOS 	001-024-0112258	10000	9090	909	56	11
1164	2022-06-06 16:23:07.172	5-Angel Rios 	11	FOTOCOPIAS	001-001-0012805	106400	96727	9672	56	12
1165	2022-06-06 16:23:07.179	5-Angel Rios 	12	MOPC	479827	44026	40023	4002	56	13
1166	2022-06-06 16:23:07.184	5-Angel Rios 	13	TASA PORTUARIA	002-007-0003018	685080	622800	62280	56	14
1167	2022-06-06 16:23:07.195	5-Angel Rios 	14	VISACION DCTOS	479826	880510	880510	0	56	15
1168	2022-06-06 16:23:07.199	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002002	5054356	4594869	459486	56	16
1169	2022-06-06 16:23:07.205	5-Angel Rios 	16	GASTO ADMIN.	001-001-0002003	660000	660000	0	56	17
1170	2022-06-06 16:23:07.223	5-Angel Rios 	17	TASA INTERV. ANNP	7017814	342819	342819	0	56	19
1171	2022-06-07 11:30:25.261	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011473L	0	0	0	57	2
1172	2022-06-07 11:30:25.268	5-Angel Rios 	2	INDI	22005IC04011473L	31518	31518	0	57	3
1173	2022-06-07 11:30:25.278	5-Angel Rios 	3	I.S.C.	22005IC04011473L	0	0	0	57	4
1174	2022-06-07 11:30:25.28	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011473L	1777213	1777213	0	57	5
1175	2022-06-07 11:30:25.29	5-Angel Rios 	5	TASA INTERV ADUANERA	22005IC04011473L	341738	341738	0	57	6
1176	2022-06-07 11:30:25.297	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011473L	1430806	1430806	0	57	7
1177	2022-06-07 11:30:25.301	5-Angel Rios 	7	I.V.A.	22005IC04011473L	35770153	0	35770153	57	8
1178	2022-06-07 11:30:25.309	5-Angel Rios 	8	CANON INFORMATICO	4031825	220127	220127	0	57	9
1179	2022-06-07 11:30:25.317	5-Angel Rios 	9	CDAP/ANNP	001-025-0118741	6834	6212	621	57	10
1180	2022-06-07 11:30:25.326	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0066529	49912	45374	4537	57	11
1181	2022-06-07 11:30:25.332	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017767	60000	54545	5454	57	12
1182	2022-06-07 11:30:25.338	5-Angel Rios 	12	APERTURA AG. TRANSP.	002-007-0009897	1996477	1814979	181497	57	13
1183	2022-06-07 11:30:25.343	5-Angel Rios 	13	CDAP PAGOS 	001-024-0112885	10000	9090	909	57	14
1184	2022-06-07 11:30:25.349	5-Angel Rios 	14	VISACION DCTOS	1037587	440255	440255	0	57	15
1185	2022-06-07 11:30:25.351	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002004	7872700	7157000	715700	57	16
1186	2022-06-07 11:30:25.364	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	57	17
1187	2022-06-07 11:30:25.379	5-Angel Rios 	17	MIPYMES MIC	1774578	620757	620757	0	57	19
1188	2022-06-07 11:30:25.398	5-Angel Rios 	17	TASA CONVUE 	1774579	73965	73965	0	57	18
1189	2022-06-07 11:30:25.413	5-Angel Rios 	19	OTROS	x	0	0	0	57	20
1190	2022-06-07 14:43:00.434	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011473L	0	0	0	58	2
1191	2022-06-07 14:43:00.439	5-Angel Rios 	2	INDI	22005IC04011473L	31518	31518	0	58	3
1192	2022-06-07 14:43:00.447	5-Angel Rios 	3	I.S.C.	22005IC04011473L	0	0	0	58	4
1193	2022-06-07 14:43:00.457	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011473L	1777213	1777213	0	58	5
1194	2022-06-07 14:43:00.459	5-Angel Rios 	5	TASA INTERV ADUANERA	22005IC04011473L	341738	341738	0	58	6
1195	2022-06-07 14:43:00.468	5-Angel Rios 	6	IRACIS GENERAL 700	22005IC04011473L	1430806	1430806	0	58	7
1196	2022-06-07 14:43:00.473	5-Angel Rios 	7	I.V.A.	22005IC04011473L	35770153	0	35770153	58	8
1197	2022-06-07 14:43:00.482	5-Angel Rios 	8	CANON INFORMATICO	4031825	220127	220127	0	58	9
1198	2022-06-07 14:43:00.485	5-Angel Rios 	9	CDAP/ANNP	001-025-0118741	6834	6212	621	58	10
1199	2022-06-07 14:43:00.493	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0066529	49912	45374	4537	58	11
1200	2022-06-07 14:43:00.498	5-Angel Rios 	11	FOTOCOPIAS	001-002-0017767	60000	54545	5454	58	12
1201	2022-06-07 14:43:00.505	5-Angel Rios 	12	TASA PORTUARIA 	002-007-0009897	1996477	1814979	181497	58	13
1202	2022-06-07 14:43:00.513	5-Angel Rios 	13	CDAP PAGOS 	001-024-0112885	10000	9090	909	58	14
1203	2022-06-07 14:43:00.523	5-Angel Rios 	14	VISACION DCTOS	1037587	440255	440255	0	58	15
1204	2022-06-07 14:43:00.53	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002004	7872700	7157000	715700	58	16
1205	2022-06-07 14:43:00.535	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	58	17
1206	2022-06-07 14:43:00.542	5-Angel Rios 	17	MIPYMES MIC	1774578	620757	620757	0	58	19
1207	2022-06-07 14:43:00.547	5-Angel Rios 	17	TASA CONVUE 	1774579	73965	73965	0	58	18
1208	2022-06-07 14:43:00.553	5-Angel Rios 	19	OTROS	x	0	0	0	58	20
1209	2022-06-08 14:51:50.572	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011603G	0	0	0	59	2
1210	2022-06-08 14:51:50.58	5-Angel Rios 	2	INDI	22005IC04011603G	26950	26950	0	59	3
1211	2022-06-08 14:51:50.588	5-Angel Rios 	3	I.S.C.	22005IC04011603G	0	0	0	59	4
1212	2022-06-08 14:51:50.596	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011603G	210610	210610	0	59	5
1213	2022-06-08 14:51:50.602	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011603G	170979	170979	0	59	7
1214	2022-06-08 14:51:50.611	5-Angel Rios 	6	I.V.A.	22005IC04011603G	4274465	0	4274465	59	8
1215	2022-06-08 14:51:50.619	5-Angel Rios 	7	CANON INFORMATICO	4072295	220127	220127	0	59	9
1216	2022-06-08 14:51:50.626	5-Angel Rios 	8	CDAP/ANNP	001-025-0119672	6835	6213	621	59	10
1217	2022-06-08 14:51:50.631	5-Angel Rios 	9	SERVICIOS SOFIA / ANNP	001-004-0067459	5876	5341	534	59	11
1218	2022-06-08 14:51:50.635	5-Angel Rios 	10	FOTOCOPIAS	001-002-0017782	70000	63636	6363	59	12
1219	2022-06-08 14:51:50.642	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015041	175000	81818	8182	59	13
1220	2022-06-08 14:51:50.647	5-Angel Rios 	12	TASA PORTUARIA	002-007-0010408	235059	213690	21369	59	14
1221	2022-06-08 14:51:50.651	5-Angel Rios 	13	VISACION DCTOS	2901697/98/99	415800	415800	0	59	15
1222	2022-06-08 14:51:50.656	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1782000	1620000	162000	59	16
1223	2022-06-08 14:51:50.66	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	59	17
1224	2022-06-08 14:52:46.979	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011603G	0	0	0	60	2
1225	2022-06-08 14:52:46.991	5-Angel Rios 	2	INDI	22005IC04011603G	26950	26950	0	60	3
1226	2022-06-08 14:52:46.997	5-Angel Rios 	3	I.S.C.	22005IC04011603G	0	0	0	60	4
1227	2022-06-08 14:52:47.003	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011603G	210610	210610	0	60	5
1228	2022-06-08 14:52:47.012	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011603G	170979	170979	0	60	7
1229	2022-06-08 14:52:47.019	5-Angel Rios 	6	I.V.A.	22005IC04011603G	4274465	0	4274465	60	8
1230	2022-06-08 14:52:47.031	5-Angel Rios 	7	CANON INFORMATICO	4072295	220127	220127	0	60	9
1231	2022-06-08 14:52:47.039	5-Angel Rios 	8	CDAP/ANNP	001-025-0119672	6835	6213	621	60	10
1232	2022-06-08 14:52:47.044	5-Angel Rios 	9	SERVICIOS SOFIA / ANNP	001-004-0067459	5876	5341	534	60	11
1233	2022-06-08 14:52:47.056	5-Angel Rios 	10	FOTOCOPIAS	001-002-0017782	70000	63636	6363	60	12
1234	2022-06-08 14:52:47.06	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015041	175000	81818	8182	60	13
1235	2022-06-08 14:52:47.068	5-Angel Rios 	12	TASA PORTUARIA	002-007-0010408	235059	213690	21369	60	14
1236	2022-06-08 14:52:47.074	5-Angel Rios 	13	VISACION DCTOS	2901697/98/99	415800	415800	0	60	15
1237	2022-06-08 14:52:47.086	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1617000	1470000	147000	60	16
1238	2022-06-08 14:52:47.096	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	60	17
1239	2022-06-08 15:14:21.099	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011603G	0	0	0	61	2
1240	2022-06-08 15:14:21.109	5-Angel Rios 	2	INDI	22005IC04011603G	26950	26950	0	61	3
1241	2022-06-08 15:14:21.114	5-Angel Rios 	3	I.S.C.	22005IC04011603G	0	0	0	61	4
1242	2022-06-08 15:14:21.12	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011603G	210610	210610	0	61	5
1243	2022-06-08 15:14:21.124	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011603G	170979	170979	0	61	7
1244	2022-06-08 15:14:21.128	5-Angel Rios 	6	I.V.A.	22005IC04011603G	4274465	0	4274465	61	8
1245	2022-06-08 15:14:21.132	5-Angel Rios 	7	CANON INFORMATICO	4072295	220127	220127	0	61	9
1246	2022-06-08 15:14:21.14	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015041	175000	81818	8182	61	13
1247	2022-06-08 15:14:21.144	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017782	70000	63636	6363	61	12
1248	2022-06-08 15:14:21.149	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010408	235059	213690	21369	61	14
1249	2022-06-08 15:14:21.155	5-Angel Rios 	11	CDAP/ANNP	001-025-0119672	6835	6213	621	61	10
1250	2022-06-08 15:14:21.159	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067459	5876	5341	534	61	11
1251	2022-06-08 15:14:21.166	5-Angel Rios 	13	VISACION DCTOS	x02901697/98/99	415800	415800	0	61	15
1252	2022-06-08 15:14:21.172	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002005	1507000	1370000	137000	61	16
1253	2022-06-08 15:26:57.159	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011603G	0	0	0	62	2
1254	2022-06-08 15:26:57.165	5-Angel Rios 	2	INDI	22005IC04011603G	26950	26950	0	62	3
1255	2022-06-08 15:26:57.173	5-Angel Rios 	3	I.S.C.	22005IC04011603G	0	0	0	62	4
1256	2022-06-08 15:26:57.177	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011603G	210610	210610	0	62	5
1257	2022-06-08 15:26:57.181	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011603G	170979	170979	0	62	7
1258	2022-06-08 15:26:57.185	5-Angel Rios 	6	I.V.A.	22005IC04011603G	4274465	0	4274465	62	8
1259	2022-06-08 15:26:57.189	5-Angel Rios 	7	CANON INFORMATICO	4072295	220127	220127	0	62	9
1260	2022-06-08 15:26:57.195	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015041	175000	81818	8182	62	13
1261	2022-06-08 15:26:57.197	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017782	70000	63636	6363	62	12
1262	2022-06-08 15:26:57.205	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010408	235059	213690	21369	62	14
1263	2022-06-08 15:26:57.209	5-Angel Rios 	11	CDAP/ANNP	001-025-0119672	6835	6213	621	62	10
1264	2022-06-08 15:26:57.213	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067459	5876	5341	534	62	11
1265	2022-06-08 15:26:57.217	5-Angel Rios 	13	VISACION DCTOS	x02901697/98/99	415800	415800	0	62	15
1266	2022-06-08 15:26:57.226	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002005	1507000	1370000	137000	62	16
1267	2022-06-08 16:16:18.551	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011601E	0	0	0	63	2
1268	2022-06-08 16:16:18.558	5-Angel Rios 	2	INDI	22005IC04011601E	26950	26950	0	63	3
1269	2022-06-08 16:16:18.562	5-Angel Rios 	3	I.S.C.	22005IC04011601E	0	0	0	63	4
1270	2022-06-08 16:16:18.567	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011601E	230558	230558	0	63	5
1271	2022-06-08 16:16:18.575	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011601E	187016	187016	0	63	7
1272	2022-06-08 16:16:18.58	5-Angel Rios 	6	I.V.A.	22005IC04011601E	4675405	0	4675405	63	8
1273	2022-06-08 16:16:18.587	5-Angel Rios 	7	CANON INFORMATICO	4072297	220127	220127	0	63	9
1274	2022-06-08 16:16:18.591	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015042	175000	81818	8182	63	13
1275	2022-06-08 16:16:18.595	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017781	120000	109090	10909	63	12
1276	2022-06-08 16:16:18.6	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010409	284194	258358	25835	63	14
1277	2022-06-08 16:16:18.607	5-Angel Rios 	11	CDAP/ANNP	001-025-0119671	6835	6213	621	63	10
1278	2022-06-08 16:16:18.611	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067460	7105	6459	645	63	11
1279	2022-06-08 16:16:18.615	5-Angel Rios 	13	VISACION DCTOS	02897835/36/37	415800	415800	0	63	15
1280	2022-06-08 16:16:18.622	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002006	782227	711115	71111	63	16
1281	2022-06-08 16:16:18.627	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	594	710000	710000	0	63	21
1282	2022-06-08 16:57:06.78	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000268@	1073060	1073060	0	64	2
1283	2022-06-08 16:57:06.784	5-Angel Rios 	2	INDI	22029IC04000268@	14420	14420	0	64	3
1284	2022-06-08 16:57:06.788	5-Angel Rios 	3	I.S.C.	22029IC04000268@	0	0	0	64	4
1285	2022-06-08 16:57:06.793	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000268@	89422	89422	0	64	5
1286	2022-06-08 16:57:06.797	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000268@	77069	77069	0	64	7
1287	2022-06-08 16:57:06.802	5-Angel Rios 	6	I.V.A.	22029IC04000268@	192672	0	192672	64	8
1288	2022-06-08 16:57:06.806	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	64	9
1289	2022-06-08 16:57:06.813	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	64	13
1290	2022-06-08 16:57:06.817	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	64	12
1291	2022-06-08 16:57:06.821	5-Angel Rios 	10	TASA PORTUARIA	x	540000	490909	49090	64	14
1292	2022-06-08 16:57:06.827	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	64	10
1293	2022-06-08 16:57:06.835	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	64	11
1294	2022-06-08 16:57:06.843	5-Angel Rios 	13	VISACION DCTOS	x	880000	880000	0	64	15
1295	2022-06-08 16:57:06.847	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	990000	900000	90000	64	16
1296	2022-06-08 16:57:06.851	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1000000	1000000	0	64	21
1297	2022-06-08 17:06:16.267	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000268@	1073060	1073060	0	65	2
1298	2022-06-08 17:06:16.271	5-Angel Rios 	2	INDI	22029IC04000268@	14420	14420	0	65	3
1299	2022-06-08 17:06:16.28	5-Angel Rios 	3	I.S.C.	22029IC04000268@	0	0	0	65	4
1300	2022-06-08 17:06:16.285	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000268@	89422	89422	0	65	5
1301	2022-06-08 17:06:16.289	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000268@	77069	77069	0	65	7
1302	2022-06-08 17:06:16.296	5-Angel Rios 	6	I.V.A. TURISMO 	22029IC04000268@	192672	0	192672	65	8
1303	2022-06-08 17:06:16.302	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	65	9
1304	2022-06-08 17:06:16.311	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	65	13
1305	2022-06-08 17:06:16.316	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	65	12
1306	2022-06-08 17:06:16.326	5-Angel Rios 	10	TASA PORTUARIA	x	540000	490909	49090	65	14
1307	2022-06-08 17:06:16.329	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	65	10
1308	2022-06-08 17:06:16.358	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	65	11
1309	2022-06-08 17:06:16.427	5-Angel Rios 	13	VISACION DCTOS	x	880000	880000	0	65	15
1310	2022-06-08 17:06:16.438	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	990000	900000	90000	65	16
1311	2022-06-08 17:06:16.447	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1000000	1000000	0	65	21
1312	2022-06-08 17:15:37.879	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011601E	0	0	0	66	2
1313	2022-06-08 17:15:37.883	5-Angel Rios 	2	INDI	22005IC04011601E	26950	26950	0	66	3
1314	2022-06-08 17:15:37.894	5-Angel Rios 	3	I.S.C.	22005IC04011601E	0	0	0	66	4
1315	2022-06-08 17:15:37.898	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011601E	230558	230558	0	66	5
1316	2022-06-08 17:15:37.903	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011601E	187016	187016	0	66	7
1317	2022-06-08 17:15:37.909	5-Angel Rios 	6	I.V.A.	22005IC04011601E	4675405	0	4675405	66	8
1318	2022-06-08 17:15:37.913	5-Angel Rios 	7	CANON INFORMATICO	4072297	220127	220127	0	66	9
1319	2022-06-08 17:15:37.917	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015042	175000	81818	8182	66	13
1320	2022-06-08 17:15:37.919	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017781	120000	109090	10909	66	12
1321	2022-06-08 17:15:37.927	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010409	284194	258358	25835	66	14
1322	2022-06-08 17:15:37.931	5-Angel Rios 	11	CDAP/ANNP	001-025-0119671	6835	6213	621	66	10
1323	2022-06-08 17:15:37.935	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067460	7105	6459	645	66	11
1324	2022-06-08 17:15:37.94	5-Angel Rios 	13	VISACION DCTOS	02897835/36/37	415800	415800	0	66	15
1325	2022-06-08 17:15:37.943	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002006	782227	711115	71111	66	16
1326	2022-06-08 17:15:37.95	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	594	710000	710000	0	66	21
1327	2022-06-08 17:23:51.25	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011601E	0	0	0	67	2
1328	2022-06-08 17:23:51.254	5-Angel Rios 	2	INDI	22005IC04011601E	26950	26950	0	67	3
1329	2022-06-08 17:23:51.266	5-Angel Rios 	3	I.S.C.	22005IC04011601E	0	0	0	67	4
1330	2022-06-08 17:23:51.27	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011601E	230558	230558	0	67	5
1331	2022-06-08 17:23:51.275	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011601E	187016	187016	0	67	7
1332	2022-06-08 17:23:51.279	5-Angel Rios 	6	I.V.A.	22005IC04011601E	4675405	0	4675405	67	8
1333	2022-06-08 17:23:51.283	5-Angel Rios 	7	CANON INFORMATICO	4072297	220127	220127	0	67	9
1334	2022-06-08 17:23:51.291	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015042	175000	81818	8182	67	13
1335	2022-06-08 17:23:51.296	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017781	120000	109090	10909	67	12
1336	2022-06-08 17:23:51.3	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010409	284194	258358	25835	67	14
1337	2022-06-08 17:23:51.304	5-Angel Rios 	11	CDAP/ANNP	001-025-0119671	6835	6213	621	67	10
1338	2022-06-08 17:23:51.31	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067460	7105	6459	645	67	11
1339	2022-06-08 17:23:51.314	5-Angel Rios 	13	VISACION DCTOS	02897835/36/37	415800	415800	0	67	15
1340	2022-06-08 17:23:51.318	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002006	782227	711115	71111	67	16
1341	2022-06-08 17:23:51.325	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	594	710000	710000	0	67	21
1342	2022-06-10 08:53:16.664	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011601E	0	0	0	68	2
1343	2022-06-10 08:53:16.672	5-Angel Rios 	2	INDI	22005IC04011601E	26950	26950	0	68	3
1344	2022-06-10 08:53:16.679	5-Angel Rios 	3	I.S.C.	22005IC04011601E	0	0	0	68	4
1345	2022-06-10 08:53:16.685	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011601E	230558	230558	0	68	5
1346	2022-06-10 08:53:16.696	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011601E	187016	187016	0	68	7
1347	2022-06-10 08:53:16.702	5-Angel Rios 	6	I.V.A.	22005IC04011601E	4675405	0	4675405	68	8
1348	2022-06-10 08:53:16.706	5-Angel Rios 	7	CANON INFORMATICO	4072297	220127	220127	0	68	9
1349	2022-06-10 08:53:16.713	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015042	175000	159090	15909	68	13
1350	2022-06-10 08:53:16.717	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017781	120000	109090	10909	68	12
1351	2022-06-10 08:53:16.721	5-Angel Rios 	10	TASA PORTUARIA	002-007-0010409	284194	258358	25835	68	14
1352	2022-06-10 08:53:16.727	5-Angel Rios 	11	CDAP/ANNP	001-025-0119671	6835	6213	621	68	10
1353	2022-06-10 08:53:16.734	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0067460	7105	6459	645	68	11
1354	2022-06-10 08:53:16.736	5-Angel Rios 	13	VISACION DCTOS	02897835/36/37	415800	415800	0	68	15
1355	2022-06-10 08:53:16.744	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002006	782227	711115	71111	68	16
1356	2022-06-10 08:53:16.75	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	594	710000	710000	0	68	21
1357	2022-06-10 08:53:16.755	5-Angel Rios 	16	LICENCIA PREVIA CONFECC	1033139	616357	560324	56032	68	20
1358	2022-06-10 08:53:16.762	5-Angel Rios 	17	CDAP PAGOS	001-024-0109625	10000	9090	909	68	20
1359	2022-06-10 09:08:15.332	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000268@	1604073	1604073	0	69	2
1360	2022-06-10 09:08:15.337	5-Angel Rios 	2	INDI	22029IC04000268@	14420	14420	0	69	3
1361	2022-06-10 09:08:15.341	5-Angel Rios 	3	I.S.C.	22029IC04000268@	0	0	0	69	4
1362	2022-06-10 09:08:15.346	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000268@	133673	133673	0	69	5
1363	2022-06-10 09:08:15.35	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000268@	114771	114771	0	69	7
1364	2022-06-10 09:08:15.354	5-Angel Rios 	6	I.V.A. TURISMO 	22029IC04000268@	286927	0	286927	69	8
1365	2022-06-10 09:08:15.359	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	69	9
1366	2022-06-10 09:08:15.367	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	69	13
1367	2022-06-10 09:08:15.371	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	69	12
1368	2022-06-10 09:08:15.375	5-Angel Rios 	10	TASA PORTUARIA APROX	x	560000	509090	50909	69	14
1369	2022-06-10 09:08:15.38	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	69	10
1370	2022-06-10 09:08:15.384	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	69	11
1371	2022-06-10 09:08:15.388	5-Angel Rios 	13	VISACION DCTOS	x	450255	450255	0	69	15
1372	2022-06-10 09:08:15.393	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1200000	1090909	109090	69	16
1373	2022-06-10 09:08:15.397	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1000000	1000000	0	69	21
1374	2022-06-10 09:19:58.085	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000273@	0	0	0	70	2
1375	2022-06-10 09:19:58.09	5-Angel Rios 	2	INDI	22029IC04000273@	14420	14420	0	70	3
1376	2022-06-10 09:19:58.098	5-Angel Rios 	3	I.S.C.	22029IC04000273@	0	0	0	70	4
1377	2022-06-10 09:19:58.102	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000273@	619657	619657	0	70	5
1378	2022-06-10 09:19:58.106	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000273@	499086	499086	0	70	7
1379	2022-06-10 09:19:58.117	5-Angel Rios 	6	I.V.A.	22029IC04000273@	12477141	0	12477141	70	8
1380	2022-06-10 09:19:58.122	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	70	9
1381	2022-06-10 09:19:58.127	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	70	13
1382	2022-06-10 09:19:58.131	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	70	12
1383	2022-06-10 09:19:58.135	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2000000	1818181	181818	70	14
1384	2022-06-10 09:19:58.139	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	70	10
1385	2022-06-10 09:19:58.148	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	70	11
1386	2022-06-10 09:19:58.153	5-Angel Rios 	13	VISACION DCTOS	x	450255	450255	0	70	15
1387	2022-06-10 09:19:58.159	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	2387000	2170000	217000	70	16
1388	2022-06-10 09:19:58.164	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1500000	1500000	0	70	21
1389	2022-06-10 09:20:44.964	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000273@	0	0	0	71	2
1390	2022-06-10 09:20:44.971	5-Angel Rios 	2	INDI	22029IC04000273@	14420	14420	0	71	3
1391	2022-06-10 09:20:44.974	5-Angel Rios 	3	I.S.C.	22029IC04000273@	0	0	0	71	4
1392	2022-06-10 09:20:44.982	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000273@	619657	619657	0	71	5
1393	2022-06-10 09:20:44.986	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000273@	499086	499086	0	71	7
1394	2022-06-10 09:20:44.991	5-Angel Rios 	6	I.V.A.	22029IC04000273@	12477141	0	12477141	71	8
1395	2022-06-10 09:20:44.996	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	71	9
1396	2022-06-10 09:20:45	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	71	13
1397	2022-06-10 09:20:45.005	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	71	12
1398	2022-06-10 09:20:45.013	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2000000	1818181	181818	71	14
1399	2022-06-10 09:20:45.017	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	71	10
1400	2022-06-10 09:20:45.021	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	71	11
1401	2022-06-10 09:20:45.024	5-Angel Rios 	13	VISACION DCTOS	x	450255	450255	0	71	15
1402	2022-06-10 09:20:45.03	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	2387000	2170000	217000	71	16
1403	2022-06-10 09:20:45.034	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1500000	1500000	0	71	21
1404	2022-06-10 09:31:23.781	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000273@	0	0	0	72	2
1405	2022-06-10 09:31:23.787	5-Angel Rios 	2	INDI	22029IC04000273@	14420	14420	0	72	3
1406	2022-06-10 09:31:23.798	5-Angel Rios 	3	I.S.C.	22029IC04000273@	0	0	0	72	4
1407	2022-06-10 09:31:23.808	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000273@	619657	619657	0	72	5
1408	2022-06-10 09:31:23.812	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000273@	499086	499086	0	72	7
1409	2022-06-10 09:31:23.817	5-Angel Rios 	6	I.V.A.	22029IC04000273@	12477141	0	12477141	72	8
1410	2022-06-10 09:31:23.821	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	72	9
1411	2022-06-10 09:31:23.828	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	72	13
1412	2022-06-10 09:31:23.835	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	72	12
1413	2022-06-10 09:31:23.839	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2000000	1818181	181818	72	14
1414	2022-06-10 09:31:23.845	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	72	10
1415	2022-06-10 09:31:23.85	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	72	11
1416	2022-06-10 09:31:23.854	5-Angel Rios 	13	VISACION DCTOS	x	450255	450255	0	72	15
1417	2022-06-10 09:31:23.858	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	2387000	2170000	217000	72	16
1418	2022-06-10 09:31:23.864	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1500000	1500000	0	72	21
1419	2022-06-10 09:34:13.933	5-Angel Rios 	1	DERECHO ADUANERO	22029IC04000273@	0	0	0	73	2
1420	2022-06-10 09:34:13.939	5-Angel Rios 	2	INDI	22029IC04000273@	14420	14420	0	73	3
1421	2022-06-10 09:34:13.945	5-Angel Rios 	3	I.S.C.	22029IC04000273@	0	0	0	73	4
1422	2022-06-10 09:34:13.952	5-Angel Rios 	4	SERVICIO DE VALORACION	22029IC04000273@	619657	619657	0	73	5
1423	2022-06-10 09:34:13.956	5-Angel Rios 	5	IRACIS GENERAL 700	22029IC04000273@	499086	499086	0	73	7
1424	2022-06-10 09:34:13.963	5-Angel Rios 	6	I.V.A.	22029IC04000273@	12477141	0	12477141	73	8
1425	2022-06-10 09:34:13.968	5-Angel Rios 	7	CANON INFORMATICO	x	225127	225127	0	73	9
1426	2022-06-10 09:34:13.975	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	73	13
1427	2022-06-10 09:34:13.986	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	73	12
1428	2022-06-10 09:34:13.994	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2600000	2363636	236363	73	14
1429	2022-06-10 09:34:13.999	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	73	10
1430	2022-06-10 09:34:14.004	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	73	11
1431	2022-06-10 09:34:14.008	5-Angel Rios 	13	VISACION DCTOS	x	450255	450255	0	73	15
1432	2022-06-10 09:34:14.015	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	2387000	2170000	217000	73	16
1433	2022-06-10 09:34:14.026	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1500000	1500000	0	73	21
1434	2022-06-14 11:46:57.857	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04008988C	9676083	9676083	0	74	2
1435	2022-06-14 11:46:57.865	5-Angel Rios 	2	INDI	22002IC04008988C	30818	30818	0	74	3
1436	2022-06-14 11:46:57.871	5-Angel Rios 	3	I.S.C.	22002IC04008988C	33597	33597	0	74	4
1437	2022-06-14 11:46:57.877	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04008988C	434404	434404	0	74	5
1438	2022-06-14 11:46:57.883	5-Angel Rios 	5	MULTAS VARIAS	22002IC04008988C	0	0	0	74	6
1439	2022-06-14 11:46:57.891	5-Angel Rios 	6	IRACIS GENERAL 700	22002IC04008988C	389984	389984	0	74	7
1440	2022-06-14 11:46:57.896	5-Angel Rios 	7	I.V.A.	22002IC04008988C	9749596	0	9749596	74	8
1441	2022-06-14 11:46:57.9	5-Angel Rios 	8	CANON INFORMATICO	22002IC04008988C	220127	220127	0	74	9
1442	2022-06-14 11:46:57.907	5-Angel Rios 	9	CDAP/ANNP	001-021-0035872	20524	18658	1865	74	10
1443	2022-06-14 11:46:57.913	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	74	11
1444	2022-06-14 11:46:57.922	5-Angel Rios 	11	FOTOCOPIAS	004-003-0023052	49000	44545	4454	74	12
1445	2022-06-14 11:46:57.929	5-Angel Rios 	12	APERTURA AG. TRANSP.	x	0	0	0	74	13
1446	2022-06-14 11:46:57.935	5-Angel Rios 	13	TASA PORTUARIA	001-034-0031505	2404270	2185700	218570	74	14
1447	2022-06-14 11:46:57.941	5-Angel Rios 	14	VISACION DCTOS	3662910	675382	675382	0	74	15
1448	2022-06-14 11:46:57.949	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0001970	2810551	2555046	255504	74	16
1449	2022-06-14 11:46:57.955	5-Angel Rios 	16	GASTO ADMIN.	566	720000	654545	65454	74	17
1450	2022-06-14 11:46:57.96	5-Angel Rios 	17	CANJE GUIA AEREA	2189307	2100000	2100000	0	74	18
1451	2022-06-14 13:22:29.696	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011785R	0	0	0	75	2
1452	2022-06-14 13:22:29.701	5-Angel Rios 	2	INDI	22005IC04011785R	26949	26949	0	75	3
1453	2022-06-14 13:22:29.708	5-Angel Rios 	3	I.S.C.	22005IC04011785R	0	0	0	75	4
1454	2022-06-14 13:22:29.712	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011785R	605742	605742	0	75	5
1455	2022-06-14 13:22:29.716	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011785R	488666	488666	0	75	7
1456	2022-06-14 13:22:29.724	5-Angel Rios 	6	I.V.A.	22005IC04011785R	12216617	0	12216617	75	8
1457	2022-06-14 13:22:29.729	5-Angel Rios 	7	CANON INFORMATICO	4086098	220127	220127	0	75	9
1458	2022-06-14 13:22:29.735	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	75	13
1459	2022-06-14 13:22:29.743	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017803	150000	136363	13636	75	12
1460	2022-06-14 13:22:29.748	5-Angel Rios 	10	TASA PORTUARIA	002-007-0011127	669396	608541	60854	75	14
1461	2022-06-14 13:22:29.753	5-Angel Rios 	11	CDAP/ANNP	001-025-0121084	6849	6226	622	75	10
1462	2022-06-14 13:22:29.758	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0068870	16735	15213	1521	75	11
1463	2022-06-14 13:22:29.762	5-Angel Rios 	13	VISACION DCTOS	02911831/32/33/34/35	970000	970000	0	75	15
1464	2022-06-14 13:22:29.766	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002008	2807619	2552380	255238	75	16
1465	2022-06-14 13:22:29.771	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	75	21
1466	2022-06-14 13:48:15.552	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04011790N	0	0	0	76	2
1467	2022-06-14 13:48:15.558	5-Angel Rios 	2	INDI	22005IC04011790N	26950	26950	0	76	3
1468	2022-06-14 13:48:15.563	5-Angel Rios 	3	I.S.C.	22005IC04011790N	0	0	0	76	4
1469	2022-06-14 13:48:15.567	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04011790N	1430872	1430872	0	76	5
1470	2022-06-14 13:48:15.572	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04011790N	1152069	1152069	0	76	7
1471	2022-06-14 13:48:15.577	5-Angel Rios 	6	I.V.A.	22005IC04011790N	28801714	0	28801714	76	8
1472	2022-06-14 13:48:15.581	5-Angel Rios 	7	CANON INFORMATICO	4086075	220127	220127	0	76	9
1473	2022-06-14 13:48:15.586	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	76	13
1474	2022-06-14 13:48:15.591	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017802	70000	63636	6363	76	12
1475	2022-06-14 13:48:15.598	5-Angel Rios 	10	TASA PORTUARIA	002-007-0011128	1548325	1407568	140756	76	14
1476	2022-06-14 13:48:15.602	5-Angel Rios 	11	CDAP/ANNP	001-025-0121085	6849	6226	622	76	10
1477	2022-06-14 13:48:15.608	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0068872	38708	35189	3518	76	11
1478	2022-06-14 13:48:15.612	5-Angel Rios 	13	VISACION DCTOS CONSULADO	02911841/42	230000	230000	0	76	15
1479	2022-06-14 13:48:15.616	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002007	3420720	3109745	310974	76	16
1480	2022-06-14 13:48:15.621	5-Angel Rios 	15	VISACION DCTOS MRE	1043048	440255	440255	0	76	21
1481	2022-06-14 13:48:15.626	5-Angel Rios 	16	CDAP PAGOS	001-024-0116372	10000	9090	909	76	20
1482	2022-06-15 13:43:34.716	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04000278@	56242535	56242535	0	77	2
1483	2022-06-15 13:43:34.726	5-Angel Rios 	2	INDI	22038IC04000278@	14456	14456	0	77	3
1484	2022-06-15 13:43:34.733	5-Angel Rios 	3	I.S.C.	22038IC04000278@	0	0	0	77	4
1485	2022-06-15 13:43:34.739	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04000278@	1757579	1757579	0	77	5
1486	2022-06-15 13:43:34.744	5-Angel Rios 	5	IRACIS GENERAL 700	22038IC04000278@	1638948	1638948	0	77	7
1487	2022-06-15 13:43:34.753	5-Angel Rios 	6	I.V.A.	22038IC04000278@	40973688	0	40973688	77	8
1488	2022-06-15 13:43:34.758	5-Angel Rios 	7	CANON INFORMATICO	x	220127	220127	0	77	9
1489	2022-06-15 13:43:34.763	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	77	13
1490	2022-06-15 13:43:34.769	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	77	12
1491	2022-06-15 13:43:34.774	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2500000	2272727	227272	77	14
1492	2022-06-15 13:43:34.782	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	77	10
1493	2022-06-15 13:43:34.787	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	77	11
1494	2022-06-15 13:43:34.791	5-Angel Rios 	13	VISACION DCTOS	x	890000	890000	0	77	15
1495	2022-06-15 13:43:34.794	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	4240002	3854547	385454	77	16
1496	2022-06-15 13:43:34.801	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	77	21
1497	2022-06-15 13:43:34.802	5-Angel Rios 	16	OTROS	x	0	0	0	77	20
1498	2022-06-15 14:04:18.853	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04000278@	56242535	56242535	0	78	2
1499	2022-06-15 14:04:18.862	5-Angel Rios 	2	INDI	22038IC04000278@	14456	14456	0	78	3
1500	2022-06-15 14:04:18.866	5-Angel Rios 	3	I.S.C.	22038IC04000278@	0	0	0	78	4
1501	2022-06-15 14:04:18.87	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04000278@	1757579	1757579	0	78	5
1502	2022-06-15 14:04:18.874	5-Angel Rios 	5	IRACIS GENERAL 700	22038IC04000278@	1638948	1638948	0	78	7
1503	2022-06-15 14:04:18.878	5-Angel Rios 	6	I.V.A.	22038IC04000278@	40973688	0	40973688	78	8
1504	2022-06-15 14:04:18.898	5-Angel Rios 	7	CANON INFORMATICO	x	220127	220127	0	78	9
1505	2022-06-15 14:04:18.902	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	78	13
1506	2022-06-15 14:04:18.91	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	78	12
1507	2022-06-15 14:04:18.914	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2500000	2272727	227272	78	14
1508	2022-06-15 14:04:18.918	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	78	10
1509	2022-06-15 14:04:18.924	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	78	11
1510	2022-06-15 14:04:18.929	5-Angel Rios 	13	VISACION DCTOS	x	890000	890000	0	78	15
1511	2022-06-15 14:04:18.937	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	4240002	3854547	385454	78	16
1512	2022-06-15 14:04:18.942	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1300000	1300000	0	78	21
1513	2022-06-15 14:04:18.946	5-Angel Rios 	16	OTROS	x	0	0	0	78	20
1514	2022-06-15 14:05:59.858	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04000278@	56242535	56242535	0	79	2
1515	2022-06-15 14:05:59.864	5-Angel Rios 	2	INDI	22038IC04000278@	14456	14456	0	79	3
1516	2022-06-15 14:05:59.875	5-Angel Rios 	3	I.S.C.	22038IC04000278@	0	0	0	79	4
1517	2022-06-15 14:05:59.879	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04000278@	1757579	1757579	0	79	5
1518	2022-06-15 14:05:59.881	5-Angel Rios 	5	IRACIS GENERAL 700	22038IC04000278@	1638948	1638948	0	79	7
1519	2022-06-15 14:05:59.885	5-Angel Rios 	6	I.V.A.	22038IC04000278@	40973688	0	40973688	79	8
1520	2022-06-15 14:05:59.892	5-Angel Rios 	7	CANON INFORMATICO	x	220127	220127	0	79	9
1521	2022-06-15 14:05:59.898	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	79	13
1522	2022-06-15 14:05:59.906	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	79	12
1523	2022-06-15 14:05:59.912	5-Angel Rios 	10	TASA PORTUARIA APROX	x	2500000	2272727	227272	79	14
1524	2022-06-15 14:05:59.918	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	79	10
1525	2022-06-15 14:05:59.922	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	79	11
1526	2022-06-15 14:05:59.925	5-Angel Rios 	13	VISACION DCTOS	x	890000	890000	0	79	15
1527	2022-06-15 14:05:59.937	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	4240002	3854547	385454	79	16
1528	2022-06-15 14:05:59.941	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	1300000	1300000	0	79	21
1529	2022-06-15 14:05:59.949	5-Angel Rios 	16	OTROS	x	0	0	0	79	20
1530	2022-06-17 09:13:11.633	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002422V	0	0	0	80	2
1531	2022-06-17 09:13:11.64	5-Angel Rios 	2	INDI	22023EC01002422V	0	0	0	80	3
1532	2022-06-17 09:13:11.648	5-Angel Rios 	3	I.S.C.	22023EC01002422V	0	0	0	80	4
1533	2022-06-17 09:13:11.655	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002422V	0	0	0	80	5
1534	2022-06-17 09:13:11.661	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002422V	0	0	0	80	7
1535	2022-06-17 09:13:11.667	5-Angel Rios 	6	I.V.A.	22023EC01002422V	0	0	0	80	8
1536	2022-06-17 09:13:11.672	5-Angel Rios 	7	CANON INFORMATICO	22023EC01002422V	220127	220127	0	80	9
1537	2022-06-17 09:13:11.676	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	80	13
1538	2022-06-17 09:13:11.681	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023020	50000	45454	4545	80	12
1539	2022-06-17 09:13:11.685	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124165	66000	60000	6000	80	14
1540	2022-06-17 09:13:11.69	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	80	10
1541	2022-06-17 09:13:11.694	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	80	11
1542	2022-06-17 09:13:11.699	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	80	15
1543	2022-06-17 09:13:11.703	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002010	2916348	2651225	265122	80	16
1544	2022-06-17 09:13:11.708	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	80	21
1545	2022-06-17 09:13:11.712	5-Angel Rios 	16	TASA MIC	1854904	92451	84046	8404	80	20
1546	2022-06-17 09:13:11.716	5-Angel Rios 	17	TASA UIP 	002-002-0007788	88051	80046	8004	80	20
1547	2022-06-17 09:34:20.859	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002423W	0	0	0	81	2
1548	2022-06-17 09:34:20.868	5-Angel Rios 	2	INDI	22023EC01002423W	0	0	0	81	3
1549	2022-06-17 09:34:20.874	5-Angel Rios 	3	I.S.C.	22023EC01002423W	0	0	0	81	4
1550	2022-06-17 09:34:20.878	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002423W	0	0	0	81	5
1551	2022-06-17 09:34:20.886	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002423W	0	0	0	81	7
1552	2022-06-17 09:34:20.896	5-Angel Rios 	6	I.V.A.	22023EC01002423W	0	0	0	81	8
1553	2022-06-17 09:34:20.915	5-Angel Rios 	7	CANON INFORMATICO	22023EC01002423W	220127	220127	0	81	9
1554	2022-06-17 09:34:20.924	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	81	13
1555	2022-06-17 09:34:20.929	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023019	40000	36363	3636	81	12
1556	2022-06-17 09:34:20.933	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124164	66000	60000	6000	81	14
1557	2022-06-17 09:34:20.939	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	81	10
1558	2022-06-17 09:34:20.943	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	81	11
1559	2022-06-17 09:34:20.948	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	81	15
1560	2022-06-17 09:34:20.953	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002011	2750442	2500401	250040	81	16
1561	2022-06-17 09:34:20.958	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	81	21
1562	2022-06-17 09:34:20.963	5-Angel Rios 	16	TASA MIC 	1855083	48426	44023	4402	81	20
1563	2022-06-17 09:34:20.967	5-Angel Rios 	17	TASA UIP	002-002-0007789	44026	40023	4002	81	20
1564	2022-06-17 10:03:37.336	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012075K	0	0	0	82	2
1565	2022-06-17 10:03:37.342	5-Angel Rios 	2	INDI	22005IC04012075K	26950	26950	0	82	3
1566	2022-06-17 10:03:37.347	5-Angel Rios 	3	TASA INT. ADUANERA 	22005IC04012075K	344112	344112	0	82	4
1567	2022-06-17 10:03:37.35	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012075K	1778879	1778879	0	82	5
1568	2022-06-17 10:03:37.356	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012075K	1431866	1431866	0	82	7
1569	2022-06-17 10:03:37.36	5-Angel Rios 	6	I.V.A.	22005IC04012075K	35796657	0	35796657	82	8
1570	2022-06-17 10:03:37.364	5-Angel Rios 	7	CANON INFORMATICO	4141998	220127	220127	0	82	9
1571	2022-06-17 10:03:37.37	5-Angel Rios 	8	CDAP PAGOS 	001-024-0117493	10000	9090	909	82	13
1572	2022-06-17 10:03:37.377	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017812	60000	54545	5454	82	12
1573	2022-06-17 10:03:37.382	5-Angel Rios 	10	TASA PORTUARIA	002-007-0012258	1931632	1756029	175602	82	14
1574	2022-06-17 10:03:37.389	5-Angel Rios 	11	CDAP/ANNP	001-025-0123779	6882	6256	625	82	10
1575	2022-06-17 10:03:37.393	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0071562	48291	43900	4390	82	11
1576	2022-06-17 10:03:37.397	5-Angel Rios 	13	VISACION DCTOS	4142029	440255	440255	0	82	15
1577	2022-06-17 10:03:37.402	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002012	7873060	7157327	715732	82	16
1578	2022-06-17 10:03:37.406	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	82	21
1579	2022-06-17 10:03:37.413	5-Angel Rios 	16	MIPYMES MAT. PRIMA	1751904	620757	564324	56432	82	20
1580	2022-06-17 10:03:37.417	5-Angel Rios 	17	APORTE VUE 	1751905	74797	67997	6799	82	20
1581	2022-06-17 10:06:24.294	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002423W	0	0	0	83	2
1582	2022-06-17 10:06:24.302	5-Angel Rios 	2	INDI	22023EC01002423W	0	0	0	83	3
1583	2022-06-17 10:06:24.308	5-Angel Rios 	3	I.S.C.	22023EC01002423W	0	0	0	83	4
1584	2022-06-17 10:06:24.312	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002423W	0	0	0	83	5
1585	2022-06-17 10:06:24.316	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002423W	0	0	0	83	7
1586	2022-06-17 10:06:24.318	5-Angel Rios 	6	I.V.A.	22023EC01002423W	0	0	0	83	8
1587	2022-06-17 10:06:24.324	5-Angel Rios 	7	CANON INFORMATICO	4133604	220127	220127	0	83	9
1588	2022-06-17 10:06:24.331	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	83	13
1589	2022-06-17 10:06:24.334	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023019	40000	36363	3636	83	12
1590	2022-06-17 10:06:24.341	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124164	66000	60000	6000	83	14
1591	2022-06-17 10:06:24.345	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	83	10
1592	2022-06-17 10:06:24.347	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	83	11
1593	2022-06-17 10:06:24.354	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	83	15
1594	2022-06-17 10:06:24.358	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002011	2750442	2500401	250040	83	16
1595	2022-06-17 10:06:24.362	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	83	21
1596	2022-06-17 10:06:24.366	5-Angel Rios 	16	TASA MIC 	1855083	48426	44023	4402	83	20
1597	2022-06-17 10:06:24.371	5-Angel Rios 	17	TASA UIP	002-002-0007789	44026	40023	4002	83	20
1598	2022-06-17 10:11:05.24	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002422V	0	0	0	84	2
1599	2022-06-17 10:11:05.245	5-Angel Rios 	2	INDI	22023EC01002422V	0	0	0	84	3
1600	2022-06-17 10:11:05.25	5-Angel Rios 	3	I.S.C.	22023EC01002422V	0	0	0	84	4
1601	2022-06-17 10:11:05.256	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002422V	0	0	0	84	5
1602	2022-06-17 10:11:05.26	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002422V	0	0	0	84	7
1603	2022-06-17 10:11:05.264	5-Angel Rios 	6	I.V.A.	22023EC01002422V	0	0	0	84	8
1604	2022-06-17 10:11:05.269	5-Angel Rios 	7	CANON INFORMATICO	4133625	220127	220127	0	84	9
1605	2022-06-17 10:11:05.274	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	84	13
1606	2022-06-17 10:11:05.282	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023020	50000	45454	4545	84	12
1607	2022-06-17 10:11:05.286	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124165	66000	60000	6000	84	14
1608	2022-06-17 10:11:05.297	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	84	10
1609	2022-06-17 10:11:05.302	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	84	11
1610	2022-06-17 10:11:05.308	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	84	15
1611	2022-06-17 10:11:05.313	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002010	2916348	2651225	265122	84	16
1612	2022-06-17 10:11:05.32	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	84	21
1613	2022-06-17 10:11:05.324	5-Angel Rios 	16	TASA MIC	1854904	92451	84046	8404	84	20
1614	2022-06-17 10:11:05.332	5-Angel Rios 	17	TASA UIP 	002-002-0007788	88051	80046	8004	84	20
1615	2022-06-17 10:31:37.172	5-Angel Rios 	1	INDI	22005IC04012075K	26950	26950	0	85	2
1616	2022-06-17 10:31:37.179	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04012075K	1778879	1778879	0	85	3
1617	2022-06-17 10:31:37.187	5-Angel Rios 	3	IRE GENERAL 700	22005IC04012075K	1431866	1431866	0	85	4
1618	2022-06-17 10:31:37.196	5-Angel Rios 	4	I.V.A.	22005IC04012075K	35796657	35796657	0	85	5
1619	2022-06-17 10:31:37.201	5-Angel Rios 	5	TASA INTER. ADUANERA	22005IC04012075K	344112	344112	0	85	7
1620	2022-06-17 10:31:37.209	5-Angel Rios 	6	I.S.C.	22005IC04012075K	0	0	0	85	8
1621	2022-06-17 10:31:37.216	5-Angel Rios 	7	CANON INFORMATICO	4141998	220127	220127	0	85	9
1622	2022-06-17 10:31:37.224	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017812	60000	54545	5454	85	13
1623	2022-06-17 10:31:37.239	5-Angel Rios 	9	TASA PORTUARIA	002-007-0012258	1931632	1756029	175602	85	12
1624	2022-06-17 10:31:37.247	5-Angel Rios 	10	CDAP/ANNP	001-025-0123779	6882	6256	625	85	14
1625	2022-06-17 10:31:37.253	5-Angel Rios 	11	SERVICIOS SOFIA/ANNP	001-004-0071562	48291	43900	4390	85	10
1626	2022-06-17 10:31:37.26	5-Angel Rios 	12	VISACION DCTOS MRE	4142029	440255	400231	40023	85	11
1627	2022-06-17 10:31:37.265	5-Angel Rios 	13	CDAP PAGOS	001-024-0117493	10000	9091	909	85	15
1628	2022-06-17 10:31:37.277	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002013	7873060	7157327	715733	85	16
1629	2022-06-17 10:31:37.288	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	85	21
1630	2022-06-17 10:31:37.297	5-Angel Rios 	16	MIPYMES MAT. PRIMA	1751904	620757	564324	56432	85	20
1631	2022-06-17 10:31:37.311	5-Angel Rios 	17	APORTE VUE 	1751905	74797	67997	6799	85	20
1632	2022-06-17 11:17:57.348	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002423W	0	0	0	86	2
1633	2022-06-17 11:17:57.356	5-Angel Rios 	2	INDI	22023EC01002423W	0	0	0	86	3
1634	2022-06-17 11:17:57.361	5-Angel Rios 	3	I.S.C.	22023EC01002423W	0	0	0	86	4
1635	2022-06-17 11:17:57.365	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002423W	0	0	0	86	5
1636	2022-06-17 11:17:57.372	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002423W	0	0	0	86	7
1637	2022-06-17 11:17:57.376	5-Angel Rios 	6	I.V.A.	22023EC01002423W	0	0	0	86	8
1638	2022-06-17 11:17:57.38	5-Angel Rios 	7	CANON INFORMATICO	4133604	220127	220127	0	86	9
1639	2022-06-17 11:17:57.399	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	86	13
1640	2022-06-17 11:17:57.407	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023019	40000	36363	3636	86	12
1641	2022-06-17 11:17:57.412	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124164	66000	60000	6000	86	14
1642	2022-06-17 11:17:57.417	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	86	10
1643	2022-06-17 11:17:57.425	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	86	11
1644	2022-06-17 11:17:57.429	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	86	15
1645	2022-06-17 11:17:57.433	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002011	2750442	2500401	250040	86	16
1646	2022-06-17 11:17:57.438	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	86	21
1647	2022-06-17 11:17:57.445	5-Angel Rios 	16	TASA MIC 	1855083	48426	48026	400	86	20
1648	2022-06-17 11:17:57.449	5-Angel Rios 	17	TASA UIP	002-002-0007789	44026	44026	0	86	20
1649	2022-06-17 11:20:02.677	5-Angel Rios 	1	DERECHO ADUANERO	22023EC01002422V	0	0	0	87	2
1650	2022-06-17 11:20:02.688	5-Angel Rios 	2	INDI	22023EC01002422V	0	0	0	87	3
1651	2022-06-17 11:20:02.694	5-Angel Rios 	3	I.S.C.	22023EC01002422V	0	0	0	87	4
1652	2022-06-17 11:20:02.7	5-Angel Rios 	4	SERVICIO DE VALORACION	22023EC01002422V	0	0	0	87	5
1653	2022-06-17 11:20:02.71	5-Angel Rios 	5	IRACIS GENERAL 700	22023EC01002422V	0	0	0	87	7
1654	2022-06-17 11:20:02.714	5-Angel Rios 	6	I.V.A.	22023EC01002422V	0	0	0	87	8
1655	2022-06-17 11:20:02.722	5-Angel Rios 	7	CANON INFORMATICO	4133625	220127	220127	0	87	9
1656	2022-06-17 11:20:02.727	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	87	13
1657	2022-06-17 11:20:02.731	5-Angel Rios 	9	FOTOCOPIAS	001-001-0023020	50000	45454	4545	87	12
1658	2022-06-17 11:20:02.736	5-Angel Rios 	10	TASA PORTUARIA	001-001-0124165	66000	60000	6000	87	14
1659	2022-06-17 11:20:02.741	5-Angel Rios 	11	CDAP/ANNP	x	0	0	0	87	10
1660	2022-06-17 11:20:02.746	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	87	11
1661	2022-06-17 11:20:02.754	5-Angel Rios 	13	VISACION DCTOS	x	0	0	0	87	15
1662	2022-06-17 11:20:02.761	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002010	2916348	2651225	265122	87	16
1663	2022-06-17 11:20:02.767	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	87	21
1664	2022-06-17 11:20:02.775	5-Angel Rios 	16	TASA MIC	1854904	92451	92051	400	87	20
1665	2022-06-17 11:20:02.782	5-Angel Rios 	17	TASA UIP 	002-002-0007788	88051	88051	0	87	20
1666	2022-06-17 11:23:25.379	5-Angel Rios 	1	INDI	22005IC04012075K	26950	26950	0	88	2
1667	2022-06-17 11:23:25.384	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04012075K	1778879	1778879	0	88	3
1668	2022-06-17 11:23:25.389	5-Angel Rios 	3	IRE GENERAL 700	22005IC04012075K	1431866	1431866	0	88	4
1669	2022-06-17 11:23:25.394	5-Angel Rios 	4	I.V.A.	22005IC04012075K	35796657	35796657	0	88	5
1670	2022-06-17 11:23:25.398	5-Angel Rios 	5	TASA INTER. ADUANERA	22005IC04012075K	344112	344112	0	88	7
1671	2022-06-17 11:23:25.402	5-Angel Rios 	6	I.S.C.	22005IC04012075K	0	0	0	88	8
1672	2022-06-17 11:23:25.407	5-Angel Rios 	7	CANON INFORMATICO	4141998	220127	220127	0	88	9
1673	2022-06-17 11:23:25.411	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017812	60000	54545	5454	88	13
1674	2022-06-17 11:23:25.417	5-Angel Rios 	9	TASA PORTUARIA	002-007-0012258	1931632	1756029	175602	88	12
1675	2022-06-17 11:23:25.421	5-Angel Rios 	10	CDAP/ANNP	001-025-0123779	6882	6256	625	88	14
1676	2022-06-17 11:23:25.425	5-Angel Rios 	11	SERVICIOS SOFIA/ANNP	001-004-0071562	48291	43900	4390	88	10
1677	2022-06-17 11:23:25.43	5-Angel Rios 	12	VISACION DCTOS MRE	4142029	440255	400231	40023	88	11
1678	2022-06-17 11:23:25.436	5-Angel Rios 	13	CDAP PAGOS	001-024-0117493	10000	9091	909	88	15
1679	2022-06-17 11:23:25.441	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002013	7873060	7157327	715733	88	16
1680	2022-06-17 11:23:25.446	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	88	21
1681	2022-06-17 11:23:25.451	5-Angel Rios 	16	MIPYMES MAT. PRIMA	1751904	620757	620357	400	88	20
1682	2022-06-17 11:23:25.457	5-Angel Rios 	17	APORTE VUE 	1751905	74797	74397	400	88	20
1683	2022-06-20 09:45:22.727	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012187Y	2317883	2317883	0	89	2
1684	2022-06-20 09:45:22.736	5-Angel Rios 	2	INDI	22005IC04012187Y	14415	14415	0	89	3
1685	2022-06-20 09:45:22.742	5-Angel Rios 	3	I.S.C.	22005IC04012187Y	0	0	0	89	4
1686	2022-06-20 09:45:22.756	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012187Y	4092501	4092501	0	89	5
1687	2022-06-20 09:45:22.764	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012187Y	3300522	3300522	0	89	7
1688	2022-06-20 09:45:22.771	5-Angel Rios 	6	I.V.A.	22005IC04012187Y	82513083	0	82513083	89	8
1689	2022-06-20 09:45:22.776	5-Angel Rios 	7	CANON INFORMATICO	4152390	220127	220127	0	89	9
1690	2022-06-20 09:45:22.78	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	89	13
1691	2022-06-20 09:45:22.789	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017827	150000	136363	13636	89	12
1692	2022-06-20 09:45:22.793	5-Angel Rios 	10	TASA PORTUARIA	002-007-0012628	3674352	3340320	334032	89	14
1693	2022-06-20 09:45:22.8	5-Angel Rios 	11	CDAP/ANNP	001-025-0124699	6864	6240	624	89	10
1694	2022-06-20 09:45:22.806	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0072485	91859	83508	8350	89	11
1695	2022-06-20 09:45:22.813	5-Angel Rios 	13	VISACION DCTOS	2914130/31/32/33	770000	770000	0	89	15
1696	2022-06-20 09:45:22.818	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002014	5567843	5061675	506167	89	16
1697	2022-06-20 09:45:22.824	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	89	21
1698	2022-06-20 14:30:49.352	5-Angel Rios 	1	DERECHO ADUANERO	22024IC04003398T	215461192	215461192	0	90	2
1699	2022-06-20 14:30:49.362	5-Angel Rios 	2	INDI	22024IC04003398T	14436	14436	0	90	3
1700	2022-06-20 14:30:49.368	5-Angel Rios 	3	TASA INTERV. ADUANERA	22024IC04003398T	343708	343708	0	90	4
1701	2022-06-20 14:30:49.377	5-Angel Rios 	4	SERVICIO DE VALORACION	22024IC04003398T	5985033	5985033	0	90	5
1702	2022-06-20 14:30:49.382	5-Angel Rios 	5	IRACIS GENERAL 700	22024IC04003398T	5674694	5674694	0	90	7
1703	2022-06-20 14:30:49.386	5-Angel Rios 	6	I.V.A.	22024IC04003398T	141867351	0	141867351	90	8
1704	2022-06-20 14:30:49.392	5-Angel Rios 	7	CANON INFORMATICO	4148239	225127	225127	0	90	9
1705	2022-06-20 14:30:49.397	5-Angel Rios 	8	TASA INTERV. ANNP	4148239	343708	312461	31246	90	13
1706	2022-06-20 14:30:49.402	5-Angel Rios 	9	FOTOCOPIAS	015-002-0004328	42000	38181	3818	90	12
1707	2022-06-20 14:30:49.408	5-Angel Rios 	10	TASA PORTUARIA	001-001-0150101	1910016	1736378	173637	90	14
1708	2022-06-20 14:30:49.415	5-Angel Rios 	11	VISACION DCTOS MRE 	4148278	880510	800463	80046	90	10
1709	2022-06-20 14:30:49.419	5-Angel Rios 	12	CDAP PAGOS 	001-024-0118232	10000	9090	909	90	11
1710	2022-06-20 14:30:49.423	5-Angel Rios 	13	MARINA MERCANTE MOPC 	4148257	44026	44026	0	90	15
1711	2022-06-20 14:30:49.427	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002015	7030122	6391020	639102	90	16
1712	2022-06-20 14:30:49.434	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	001-001-0002016	660000	660000	0	90	21
1713	2022-06-20 14:30:49.439	5-Angel Rios 	16	CDAP PAGOS 	001-024-0118233	10000	10000	0	90	20
1714	2022-06-21 11:12:24.23	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012075K	0	0	0	91	2
1715	2022-06-21 11:12:24.237	5-Angel Rios 	2	INDI	22005IC04012075K	26950	26950	0	91	3
1716	2022-06-21 11:12:24.239	5-Angel Rios 	3	TASA INTER. ADUANERA 	22005IC04012075K	344112	344112	0	91	4
1717	2022-06-21 11:12:24.239	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012075K	1778879	1778879	0	91	5
1718	2022-06-21 11:12:24.255	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012075K	1431866	1431866	0	91	7
1719	2022-06-21 11:12:24.255	5-Angel Rios 	6	I.V.A.	22005IC04012075K	35796657	0	35796657	91	8
1720	2022-06-21 11:12:24.27	5-Angel Rios 	7	CANON INFORMATICO	4141998	220127	220127	0	91	9
1721	2022-06-21 11:12:24.279	5-Angel Rios 	8	CDAP PAGOS	001-024-0117493	10000	9090	909	91	13
1722	2022-06-21 11:12:24.279	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017812	60000	54545	5454	91	12
1723	2022-06-21 11:12:24.279	5-Angel Rios 	10	TASA PORTUARIA	002-007-0012258	1931632	1756029	175602	91	14
1724	2022-06-21 11:12:24.279	5-Angel Rios 	11	CDAP/ANNP	001-025-0123779	6882	6256	625	91	10
1725	2022-06-21 11:12:24.295	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0071562	48291	43900	4390	91	11
1726	2022-06-21 11:12:24.295	5-Angel Rios 	13	VISACION DCTOS MRE	4142029	440255	440255	0	91	15
1727	2022-06-21 11:12:24.295	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002013	7873060	7157327	715732	91	16
1728	2022-06-21 11:12:24.31	5-Angel Rios 	15	MIPYMES MAT. PRIMA.	1751904	620757	620357	400	91	17
1729	2022-06-21 11:12:24.31	5-Angel Rios 	16	APORTE VUE 	1751905	74797	74397	400	91	17
1730	2022-06-29 09:19:11.197	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012892R	0	0	0	92	2
1731	2022-06-29 09:19:11.209	5-Angel Rios 	2	INDI	22005IC04012892R	26950	26950	0	92	3
1732	2022-06-29 09:19:11.216	5-Angel Rios 	3	I.S.C.	22005IC04012892R	0	0	0	92	4
1733	2022-06-29 09:19:11.222	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012892R	1158103	1158103	0	92	5
1734	2022-06-29 09:19:11.229	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012892R	932763	932763	0	92	7
1735	2022-06-29 09:19:11.237	5-Angel Rios 	6	I.V.A.	22005IC04012892R	23319071	0	23319071	92	8
1736	2022-06-29 09:19:11.239	5-Angel Rios 	7	CANON INFORMATICO	4222392	220127	220127	0	92	9
1737	2022-06-29 09:19:11.246	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015403	175000	81818	8182	92	13
1738	2022-06-29 09:19:11.252	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017882	70000	63636	6363	92	12
1739	2022-06-29 09:19:11.254	5-Angel Rios 	10	TASA PORTUARIA	002-007-0015171	1220690	1109718	110971	92	14
1740	2022-06-29 09:19:11.261	5-Angel Rios 	11	CDAP/ANNP	001-025-0129907	6858	6234	623	92	10
1741	2022-06-29 09:19:11.265	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0077690	30517	27742	2774	92	11
1742	2022-06-29 09:19:11.269	5-Angel Rios 	13	VISACION DCTOS CONSUL	2941754/55	235000	235000	0	92	15
1743	2022-06-29 09:19:11.276	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002018	3660913	3328102	332810	92	16
1744	2022-06-29 09:19:11.28	5-Angel Rios 	15	VISACION DCTOS MRE	4222670	440255	440255	0	92	15
1745	2022-06-29 09:19:11.284	5-Angel Rios 	16	CDAP/ANNP	001-024-0124037	10000	9090	909	92	10
1746	2022-06-29 10:36:48.374	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012780N	0	0	0	93	2
1747	2022-06-29 10:36:48.38	5-Angel Rios 	2	INDI	22005IC04012780N	14420	14420	0	93	3
1748	2022-06-29 10:36:48.386	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04012780N	2095393	2095393	0	93	5
1749	2022-06-29 10:36:48.391	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04012780N	1685578	1685578	0	93	7
1750	2022-06-29 10:36:48.396	5-Angel Rios 	5	I.V.A.	22005IC04012780N	42139451	0	42139451	93	8
1751	2022-06-29 10:36:48.401	5-Angel Rios 	6	TASA INTER. ADUAN.	x	343339	343339	0	93	24
1752	2022-06-29 10:36:48.408	5-Angel Rios 	7	CANON INFORMATICO	4216154	220127	220127	0	93	9
1753	2022-06-29 10:36:48.413	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017885	70000	63636	6363	93	12
1754	2022-06-29 10:36:48.42	5-Angel Rios 	9	TASA PORTUARIA	002-007-0014758	2298331	2089391	208939	93	14
1755	2022-06-29 10:36:48.427	5-Angel Rios 	10	CDAP/ANNP	001-025-0129030	6866	6241	624	93	10
1756	2022-06-29 10:36:48.433	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0076815	57458	52234	5223	93	11
1757	2022-06-29 10:36:48.439	5-Angel Rios 	12	VISACION DCTOS MRE	4216195	440255	440255	0	93	15
1758	2022-06-29 10:36:48.444	5-Angel Rios 	13	CDAP/ANNP	001-024-0123243	10000	9090	909	93	10
1759	2022-06-29 10:36:48.45	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002020	9528460	8662236	866223	93	16
1760	2022-06-29 10:36:48.455	5-Angel Rios 	15	MIPYMES MIC	1853451	620757	620357	400	93	22
1761	2022-06-29 10:36:48.463	5-Angel Rios 	16	APORTE VUE	1853452	73188	72788	400	93	23
1762	2022-06-29 11:20:26.18	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012849T	0	0	0	94	2
1763	2022-06-29 11:20:26.191	5-Angel Rios 	2	INDI	22005IC04012849T	14420	14420	0	94	3
1764	2022-06-29 11:20:26.198	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04012849T	2853767	2853767	0	94	5
1765	2022-06-29 11:20:26.2	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04012849T	2295310	2295310	0	94	7
1766	2022-06-29 11:20:26.208	5-Angel Rios 	5	I.V.A.	22005IC04012849T	57382762	0	57382762	94	8
1767	2022-06-29 11:20:26.212	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04012849T	342965	342965	0	94	24
1768	2022-06-29 11:20:26.216	5-Angel Rios 	7	CANON INFORMATICO	4216160	220127	220127	0	94	9
1769	2022-06-29 11:20:26.225	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017883	70000	63636	6363	94	12
1770	2022-06-29 11:20:26.229	5-Angel Rios 	9	TASA PORTUARIA	002-007-0015029	3266203	2969275	296927	94	14
1771	2022-06-29 11:20:26.233	5-Angel Rios 	10	CDAP/ANNP	001-025-0129580	6858	6234	623	94	10
1772	2022-06-29 11:20:26.24	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0077361	80655	73322	7332	94	11
1773	2022-06-29 11:20:26.247	5-Angel Rios 	12	VISACION DCTOS MRE	4216195	440255	440255	0	94	15
1774	2022-06-29 11:20:26.254	5-Angel Rios 	13	CDAP/ANNP	001-024-0123244	10000	9090	909	94	10
1775	2022-06-29 11:20:26.262	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002021	10028986	9117260	911726	94	16
1776	2022-06-29 11:20:26.268	5-Angel Rios 	15	MIPYMES MIC	1853451	620757	620357	400	94	22
1777	2022-06-29 11:20:26.273	5-Angel Rios 	16	APORTE VUE	1853452	73188	72788	400	94	23
1778	2022-06-29 11:20:26.278	5-Angel Rios 	17	MIPYMES MIC	1820501	620757	620357	400	94	22
1779	2022-06-29 11:20:26.283	5-Angel Rios 	18	APORTE VUE	1820502	72866	72466	400	94	23
1780	2022-06-29 11:43:16.824	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012780N	0	0	0	95	2
1781	2022-06-29 11:43:16.828	5-Angel Rios 	2	INDI	22005IC04012780N	14420	14420	0	95	3
1782	2022-06-29 11:43:16.837	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04012780N	2095393	2095393	0	95	5
1783	2022-06-29 11:43:16.841	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04012780N	1685578	1685578	0	95	7
1784	2022-06-29 11:43:16.845	5-Angel Rios 	5	I.V.A.	22005IC04012780N	42139451	0	42139451	95	8
1785	2022-06-29 11:43:16.851	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04012780N	343339	343339	0	95	24
1786	2022-06-29 11:43:16.853	5-Angel Rios 	7	CANON INFORMATICO	4216154	220127	220127	0	95	9
1787	2022-06-29 11:43:16.86	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017885	70000	63636	6363	95	12
1788	2022-06-29 11:43:16.864	5-Angel Rios 	9	TASA PORTUARIA	002-007-0014758	2298331	2089391	208939	95	14
1789	2022-06-29 11:43:16.868	5-Angel Rios 	10	CDAP/ANNP	001-025-0129030	6866	6241	624	95	10
1790	2022-06-29 11:43:16.874	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0076815	57458	52234	5223	95	11
1791	2022-06-29 11:43:16.878	5-Angel Rios 	12	VISACION DCTOS MRE	4216180	440255	440255	0	95	15
1792	2022-06-29 11:43:16.88	5-Angel Rios 	13	CDAP/ANNP	001-024-0123243	10000	9090	909	95	10
1793	2022-06-29 11:43:16.888	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002022	8428460	7662236	766223	95	16
1794	2022-06-29 11:43:16.893	5-Angel Rios 	15	MIPYMES MIC	1853442	620757	620357	400	95	22
1795	2022-06-29 11:43:16.896	5-Angel Rios 	16	APORTE VUE	1853443	73188	72788	400	95	23
1796	2022-06-29 13:19:54.838	5-Angel Rios 	1	INDI	22031IC04004017G	61636	61636	0	96	3
1797	2022-06-29 13:19:54.885	5-Angel Rios 	2	I.S.C.	22031IC04004017G	282360	282360	0	96	4
1798	2022-06-29 13:19:54.913	5-Angel Rios 	3	SERVICIO DE VALORACION	22031IC04004017G	135790	135790	0	96	5
1799	2022-06-29 13:19:54.93	5-Angel Rios 	4	IRACIS GENERAL 700	22031IC04004017G	114073	114073	0	96	7
1800	2022-06-29 13:19:54.946	5-Angel Rios 	5	I.V.A. TURISMO 	22031IC04004017G	285184	0	285184	96	26
1801	2022-06-29 13:19:54.958	5-Angel Rios 	6	TASA INTER. ADUAN.	22031IC04004017G	343339	343339	0	96	24
1802	2022-06-29 13:19:54.98	5-Angel Rios 	7	CDAP PAGOS	001-024-0123480	10000	9090	909	96	27
1803	2022-06-29 13:19:55.001	5-Angel Rios 	8	CANON INFORMATICO	4218489	93051	93051	0	96	9
1804	2022-06-29 13:19:55.062	5-Angel Rios 	9	VISACION DCTOS	4218486	880510	880510	0	96	15
1805	2022-06-29 13:19:55.067	5-Angel Rios 	10	FOTOCOPIAS	022-002-0005003	25550	23227	2322	96	12
1806	2022-06-29 13:19:55.072	5-Angel Rios 	11	TASA PORTUARIA	2332645	2289144	2081040	208104	96	14
1807	2022-06-29 13:19:55.076	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002023	1210000	1100000	110000	96	16
1808	2022-06-29 13:19:55.081	5-Angel Rios 	13	GASTO ADMIN.	0705	1200000	1200000	0	96	17
1809	2022-06-29 13:19:55.087	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	0706	650000	650000	0	96	21
1810	2022-06-29 13:22:52.061	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012849T	0	0	0	97	2
1811	2022-06-29 13:22:52.078	5-Angel Rios 	2	INDI	22005IC04012849T	14420	14420	0	97	3
1812	2022-06-29 13:22:52.085	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04012849T	2853767	2853767	0	97	5
1813	2022-06-29 13:22:52.094	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04012849T	2295310	2295310	0	97	7
1814	2022-06-29 13:22:52.101	5-Angel Rios 	5	I.V.A.	22005IC04012849T	57382762	0	57382762	97	8
1815	2022-06-29 13:22:52.113	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04012849T	342965	342965	0	97	24
1816	2022-06-29 13:22:52.12	5-Angel Rios 	7	CANON INFORMATICO	4216160	220127	220127	0	97	9
1817	2022-06-29 13:22:52.139	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017883	70000	63636	6363	97	12
1818	2022-06-29 13:22:52.157	5-Angel Rios 	9	TASA PORTUARIA	002-007-0015029	3266203	2969275	296927	97	14
1819	2022-06-29 13:22:52.167	5-Angel Rios 	10	CDAP/ANNP	001-025-0129580	6858	6234	623	97	10
1820	2022-06-29 13:22:52.177	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0077361	80655	73322	7332	97	11
1821	2022-06-29 13:22:52.189	5-Angel Rios 	12	VISACION DCTOS MRE	4216195	440255	440255	0	97	15
1822	2022-06-29 13:22:52.197	5-Angel Rios 	13	CDAP/ANNP	001-024-0123244	10000	9090	909	97	10
1823	2022-06-29 13:22:52.214	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002021	10028986	9117260	911726	97	16
1824	2022-06-29 13:22:52.242	5-Angel Rios 	15	MIPYMES MIC	1853451	620757	620357	400	97	22
1825	2022-06-29 13:22:52.249	5-Angel Rios 	16	APORTE VUE	1853452	73188	72788	400	97	23
1826	2022-06-29 13:22:52.269	5-Angel Rios 	17	MIPYMES MIC	1820501	620757	620357	400	97	22
1827	2022-06-29 13:22:52.277	5-Angel Rios 	18	APORTE VUE	1820502	72866	72466	400	97	23
1828	2022-06-29 13:26:43.167	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012780N	0	0	0	98	2
1829	2022-06-29 13:26:43.172	5-Angel Rios 	2	INDI	22005IC04012780N	14420	14420	0	98	3
1830	2022-06-29 13:26:43.178	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04012780N	2095393	2095393	0	98	5
1831	2022-06-29 13:26:43.182	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04012780N	1685578	1685578	0	98	7
1832	2022-06-29 13:26:43.192	5-Angel Rios 	5	I.V.A.	22005IC04012780N	42139451	0	42139451	98	8
1833	2022-06-29 13:26:43.197	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04012780N	343339	343339	0	98	24
1834	2022-06-29 13:26:43.201	5-Angel Rios 	7	CANON INFORMATICO	4216154	220127	220127	0	98	9
1835	2022-06-29 13:26:43.209	5-Angel Rios 	8	FOTOCOPIAS	001-002-0017885	70000	63636	6363	98	12
1836	2022-06-29 13:26:43.213	5-Angel Rios 	9	TASA PORTUARIA	002-007-0014758	2298331	2089391	208939	98	14
1837	2022-06-29 13:26:43.218	5-Angel Rios 	10	CDAP/ANNP	001-025-0129030	6866	6241	624	98	10
1838	2022-06-29 13:26:43.224	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0076815	57458	52234	5223	98	11
1839	2022-06-29 13:26:43.229	5-Angel Rios 	12	VISACION DCTOS MRE	4216180	440255	440255	0	98	15
1840	2022-06-29 13:26:43.23	5-Angel Rios 	13	CDAP/ANNP	001-024-0123243	10000	9090	909	98	10
1841	2022-06-29 13:26:43.238	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002022	8428460	7662236	766223	98	16
1842	2022-06-29 13:26:43.243	5-Angel Rios 	15	MIPYMES MIC	1853442	620757	620357	400	98	22
1843	2022-06-29 13:26:43.247	5-Angel Rios 	16	APORTE VUE	1853443	73188	72788	400	98	23
1844	2022-06-29 13:27:46.894	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012892R	0	0	0	99	2
1845	2022-06-29 13:27:46.901	5-Angel Rios 	2	INDI	22005IC04012892R	26950	26950	0	99	3
1846	2022-06-29 13:27:46.908	5-Angel Rios 	3	I.S.C.	22005IC04012892R	0	0	0	99	4
1847	2022-06-29 13:27:46.912	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012892R	1158103	1158103	0	99	5
1848	2022-06-29 13:27:46.918	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012892R	932763	932763	0	99	7
1849	2022-06-29 13:27:46.923	5-Angel Rios 	6	I.V.A.	22005IC04012892R	23319071	0	23319071	99	8
1850	2022-06-29 13:27:46.927	5-Angel Rios 	7	CANON INFORMATICO	4222392	220127	220127	0	99	9
1851	2022-06-29 13:27:46.931	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015403	175000	81818	8182	99	13
1852	2022-06-29 13:27:46.939	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017882	70000	63636	6363	99	12
1853	2022-06-29 13:27:46.943	5-Angel Rios 	10	TASA PORTUARIA	002-007-0015171	1220690	1109718	110971	99	14
1854	2022-06-29 13:27:46.947	5-Angel Rios 	11	CDAP/ANNP	001-025-0129907	6858	6234	623	99	10
1855	2022-06-29 13:27:46.953	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0077690	30517	27742	2774	99	11
1856	2022-06-29 13:27:46.96	5-Angel Rios 	13	VISACION DCTOS CONSUL	2941754/55	235000	235000	0	99	15
1857	2022-06-29 13:27:46.965	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002018	3660913	3328102	332810	99	16
1858	2022-06-29 13:27:46.97	5-Angel Rios 	15	VISACION DCTOS MRE	4222670	440255	440255	0	99	15
1859	2022-06-29 13:27:46.978	5-Angel Rios 	16	CDAP/ANNP	001-024-0124037	10000	9090	909	99	10
1860	2022-06-29 13:41:43.222	5-Angel Rios 	1	INDI	22031IC04004017G	61636	61636	0	100	3
1861	2022-06-29 13:41:43.231	5-Angel Rios 	2	I.S.C.	22031IC04004017G	282360	282360	0	100	4
1862	2022-06-29 13:41:43.242	5-Angel Rios 	3	SERVICIO DE VALORACION	22031IC04004017G	135790	135790	0	100	5
1863	2022-06-29 13:41:43.25	5-Angel Rios 	4	IRACIS GENERAL 700	22031IC04004017G	114073	114073	0	100	7
1864	2022-06-29 13:41:43.258	5-Angel Rios 	5	I.V.A. TURISMO 	22031IC04004017G	285184	0	285184	100	26
1865	2022-06-29 13:41:43.263	5-Angel Rios 	6	TASA INTER. ADUAN.	22031IC04004017G	343339	343339	0	100	24
1866	2022-06-29 13:41:43.272	5-Angel Rios 	7	CDAP PAGOS	001-024-0123480	10000	9090	909	100	27
1867	2022-06-29 13:41:43.283	5-Angel Rios 	8	CANON INFORMATICO	4218489	93051	93051	0	100	9
1868	2022-06-29 13:41:43.292	5-Angel Rios 	9	VISACION DCTOS	4218486	880510	880510	0	100	15
1869	2022-06-29 13:41:43.3	5-Angel Rios 	10	FOTOCOPIAS	022-002-0005003	25550	23227	2322	100	12
1870	2022-06-29 13:41:43.308	5-Angel Rios 	11	TASA PORTUARIA	2332645	2289144	2081040	208104	100	14
1871	2022-06-29 13:41:43.317	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002023	1210000	1100000	110000	100	16
1872	2022-06-29 13:41:43.324	5-Angel Rios 	13	GASTO ADMIN.	0705	1200000	1200000	0	100	17
1873	2022-06-29 13:41:43.332	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	0706	650000	650000	0	100	21
1874	2022-06-30 14:13:38.444	5-Angel Rios 	1	INDI	22018EC01001795Y	0	0	0	101	3
1875	2022-06-30 14:13:38.452	5-Angel Rios 	2	I.S.C.	22018EC01001795Y	0	0	0	101	4
1876	2022-06-30 14:13:38.459	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01001795Y	0	0	0	101	5
1877	2022-06-30 14:13:38.466	5-Angel Rios 	4	IRACIS GENERAL 700	22018EC01001795Y	0	0	0	101	7
1878	2022-06-30 14:13:38.472	5-Angel Rios 	5	I.V.A. TURISMO 	22018EC01001795Y	0	0	0	101	26
1879	2022-06-30 14:13:38.481	5-Angel Rios 	6	CANON INFORMATICO	4221767	220127	220127	0	101	9
1880	2022-06-30 14:13:38.489	5-Angel Rios 	7	FOTOCOPIAS	001-001-0008953	50000	45454	4545	101	12
1881	2022-06-30 14:13:38.496	5-Angel Rios 	8	TASA MIC	1867242	92451	92051	400	101	29
1882	2022-06-30 14:13:38.501	5-Angel Rios 	9	TASA UIP	002-002-0008139	88051	88051	0	101	30
1883	2022-06-30 14:13:38.508	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002024	3199320	2908472	290847	101	16
1884	2022-07-04 14:00:11.671	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04012892R	0	0	0	102	2
1885	2022-07-04 14:00:11.682	5-Angel Rios 	2	INDI	22005IC04012892R	26950	26950	0	102	3
1886	2022-07-04 14:00:11.689	5-Angel Rios 	3	I.S.C.	22005IC04012892R	0	0	0	102	4
1887	2022-07-04 14:00:11.694	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04012892R	1158103	1158103	0	102	5
1888	2022-07-04 14:00:11.702	5-Angel Rios 	5	IRACIS GENERAL 700	22005IC04012892R	932763	932763	0	102	7
1889	2022-07-04 14:00:11.71	5-Angel Rios 	6	I.V.A.	22005IC04012892R	23319071	0	23319071	102	8
1890	2022-07-04 14:00:11.717	5-Angel Rios 	7	CANON INFORMATICO	4222392	220127	220127	0	102	9
1891	2022-07-04 14:00:11.723	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015403	175000	81818	8182	102	13
1892	2022-07-04 14:00:11.732	5-Angel Rios 	9	FOTOCOPIAS	001-002-0017882	70000	63636	6363	102	12
1893	2022-07-04 14:00:11.74	5-Angel Rios 	10	TASA PORTUARIA	002-007-0015171	1220690	1109718	110971	102	14
1894	2022-07-04 14:00:11.745	5-Angel Rios 	11	CDAP/ANNP	001-025-0129907	6858	6234	623	102	10
1895	2022-07-04 14:00:11.752	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0077690	30517	27742	2774	102	11
1896	2022-07-04 14:00:11.757	5-Angel Rios 	13	VISACION DCTOS CONSUL	2941754/55	235000	235000	0	102	15
1897	2022-07-04 14:00:11.772	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002018	3660913	3328102	332810	102	16
1898	2022-07-04 14:00:11.785	5-Angel Rios 	15	VISACION DCTOS MRE	4222670	440255	440255	0	102	15
1899	2022-07-04 14:00:11.801	5-Angel Rios 	16	CDAP/ANNP	001-024-0124037	10000	9090	909	102	10
1900	2022-07-04 14:40:01.743	5-Angel Rios 	1	INDI	22005IC04013105F	26950	26950	0	103	3
1901	2022-07-04 14:40:01.757	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04013105F	3535217	3535217	0	103	5
1902	2022-07-04 14:40:01.764	5-Angel Rios 	3	IRACIS GENERAL 700	22005IC04013105F	2843963	2843963	0	103	7
1903	2022-07-04 14:40:01.772	5-Angel Rios 	4	I.V.A.	22005IC04013105F	71099067	0	71099067	103	8
1904	2022-07-04 14:40:01.787	5-Angel Rios 	5	CANON INFORMATICO	4234782	220127	220127	0	103	9
1905	2022-07-04 14:40:01.8	5-Angel Rios 	6	APERTURA AG. TRANSP.	001-001-0015450	350000	220000	22000	103	13
1906	2022-07-04 14:40:01.814	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017896	70000	63636	6363	103	12
1907	2022-07-04 14:40:01.821	5-Angel Rios 	8	TASA PORTUARIA	002-007-0016000	3215183	2922893	292289	103	14
1908	2022-07-04 14:40:01.826	5-Angel Rios 	9	CDAP PAGOS	001-025-0131584	6853	6230	623	103	27
1909	2022-07-04 14:40:01.83	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0079367	80380	73072	7307	103	11
1910	2022-07-04 14:40:01.842	5-Angel Rios 	11	VISACION MRE	4235000	440255	440255	0	103	28
1911	2022-07-04 14:40:01.851	5-Angel Rios 	12	CDAP PAGOS	001-024-0125433	10000	9090	909	103	27
1912	2022-07-04 14:40:01.858	5-Angel Rios 	13	VISACION CONSUL	02945158/59/60	588000	588000	0	103	15
1913	2022-07-04 14:40:01.864	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002025	6095243	5541130	554113	103	16
1914	2022-07-04 15:14:56.292	5-Angel Rios 	1	INDI	22002IC04013323E	30818	30818	0	104	3
1915	2022-07-04 15:14:56.3	5-Angel Rios 	2	SERVICIO DE VALORACION	22002IC04013323E	668818	668818	0	104	5
1916	2022-07-04 15:14:56.308	5-Angel Rios 	3	IRACIS GENERAL 700	22002IC04013323E	539614	539614	0	104	7
1917	2022-07-04 15:14:56.313	5-Angel Rios 	4	I.V.A.	22002IC04013323E	13490354	0	13490354	104	8
1918	2022-07-04 15:14:56.319	5-Angel Rios 	5	CANON INFORMATICO	4225929	225127	225127	0	104	9
1919	2022-07-04 15:14:56.323	5-Angel Rios 	6	FOTOCOPIAS	004-003-0027775	42000	38181	3818	104	12
1920	2022-07-04 15:14:56.336	5-Angel Rios 	7	TASA PORTUARIA	001-034-0043675	2208513	2007739	200773	104	14
1921	2022-07-04 15:14:56.339	5-Angel Rios 	8	CDAP DINAC	001-021-0047331	20600	18727	1872	104	31
1922	2022-07-04 15:14:56.363	5-Angel Rios 	9	VISACION MRE	4225947	440255	440255	0	104	28
1923	2022-07-04 15:14:56.376	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002027	3977600	3616000	361600	104	16
1924	2022-07-04 15:15:45.998	5-Angel Rios 	1	INDI	22002IC04013323E	30818	30818	0	105	3
1925	2022-07-04 15:15:46.004	5-Angel Rios 	2	SERVICIO DE VALORACION	22002IC04013323E	668818	668818	0	105	5
1926	2022-07-04 15:15:46.008	5-Angel Rios 	3	IRACIS GENERAL 700	22002IC04013323E	539614	539614	0	105	7
1927	2022-07-04 15:15:46.011	5-Angel Rios 	4	I.V.A.	22002IC04013323E	13490354	0	13490354	105	8
1928	2022-07-04 15:15:46.018	5-Angel Rios 	5	CANON INFORMATICO	4225929	225127	225127	0	105	9
1929	2022-07-04 15:15:46.02	5-Angel Rios 	6	FOTOCOPIAS	004-003-0027775	42000	38181	3818	105	12
1930	2022-07-04 15:15:46.03	5-Angel Rios 	7	TASA PORTUARIA	001-034-0043675	2208513	2007739	200773	105	14
1931	2022-07-04 15:15:46.041	5-Angel Rios 	8	CDAP DINAC	001-021-0047331	20600	18727	1872	105	31
1932	2022-07-04 15:15:46.046	5-Angel Rios 	9	VISACION MRE	4225947	440255	440255	0	105	28
1933	2022-07-04 15:15:46.05	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002027	3977600	3616000	361600	105	16
1934	2022-07-04 15:21:07.519	5-Angel Rios 	1	INDI	22018EC01001795Y	0	0	0	106	3
1935	2022-07-04 15:21:07.526	5-Angel Rios 	2	I.S.C.	22018EC01001795Y	0	0	0	106	4
1936	2022-07-04 15:21:07.531	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01001795Y	0	0	0	106	5
1937	2022-07-04 15:21:07.538	5-Angel Rios 	4	IRACIS GENERAL 700	22018EC01001795Y	0	0	0	106	7
1938	2022-07-04 15:21:07.542	5-Angel Rios 	5	I.V.A. TURISMO 	22018EC01001795Y	0	0	0	106	26
1939	2022-07-04 15:21:07.552	5-Angel Rios 	6	CANON INFORMATICO	4221767	220127	220127	0	106	9
1940	2022-07-04 15:21:07.556	5-Angel Rios 	7	FOTOCOPIAS	001-001-0008953	50000	45454	4545	106	12
1941	2022-07-04 15:21:07.56	5-Angel Rios 	8	TASA MIC CO	1867242	92451	92051	400	106	29
3837	2022-10-19 13:49:23.152	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	235	17
1942	2022-07-04 15:21:07.566	5-Angel Rios 	9	TASA UIP CO	002-002-0008139	88051	88051	0	106	30
1943	2022-07-04 15:21:07.573	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002024	3199320	2908472	290847	106	16
1944	2022-07-04 15:23:16.813	5-Angel Rios 	1	INDI	22018EC01001795Y	0	0	0	107	3
1945	2022-07-04 15:23:16.819	5-Angel Rios 	2	I.S.C.	22018EC01001795Y	0	0	0	107	4
1946	2022-07-04 15:23:16.824	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01001795Y	0	0	0	107	5
1947	2022-07-04 15:23:16.829	5-Angel Rios 	4	IRACIS GENERAL 700	22018EC01001795Y	0	0	0	107	7
1948	2022-07-04 15:23:16.834	5-Angel Rios 	5	I.V.A. TURISMO 	22018EC01001795Y	0	0	0	107	26
1949	2022-07-04 15:23:16.839	5-Angel Rios 	6	CANON INFORMATICO	4221767	220127	220127	0	107	9
1950	2022-07-04 15:23:16.843	5-Angel Rios 	7	FOTOCOPIAS	001-001-0008953	50000	45454	4545	107	12
1951	2022-07-04 15:23:16.849	5-Angel Rios 	8	TASA MIC CO	1867242	92451	92051	400	107	29
1952	2022-07-04 15:23:16.854	5-Angel Rios 	9	TASA UIP CO	002-002-0008139	88051	88051	0	107	30
1953	2022-07-04 15:23:16.858	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002024	3199320	2908472	290847	107	16
1954	2022-07-05 13:59:06.268	5-Angel Rios 	1	INDI	22018EC01001851H	0	0	0	108	3
1955	2022-07-05 13:59:06.276	5-Angel Rios 	2	SERVICIO DE VALORACION	22018EC01001851H	0	0	0	108	5
1956	2022-07-05 13:59:06.282	5-Angel Rios 	3	IRACIS GENERAL 700	22018EC01001851H	0	0	0	108	7
1957	2022-07-05 13:59:06.293	5-Angel Rios 	4	I.V.A.	22018EC01001851H	0	0	0	108	8
1958	2022-07-05 13:59:06.298	5-Angel Rios 	5	CANON INFORMATICO	4244907	245222	245222	0	108	9
1959	2022-07-05 13:59:06.304	5-Angel Rios 	6	FOTOCOPIAS	001-001-0008987	50000	45454	4545	108	12
1960	2022-07-05 13:59:06.309	5-Angel Rios 	7	TASA MIC CO	1870821	102489	102089	400	108	29
1961	2022-07-05 13:59:06.314	5-Angel Rios 	8	TASA UIP CO	002-002-0008249	98089	98089	0	108	30
1962	2022-07-05 13:59:06.319	5-Angel Rios 	9	HON. DESP. S/ LEY 220/93	001-001-0002028	3105111	2822828	282282	108	16
1963	2022-07-05 15:31:50.179	5-Angel Rios 	1	INDI	22005IC04013396R	26950	26950	0	109	3
1964	2022-07-05 15:31:50.186	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04013396R	5156528	5156528	0	109	5
1965	2022-07-05 15:31:50.194	5-Angel Rios 	3	IRACIS GENERAL 700	22005IC04013396R	4147497	4147497	0	109	7
1966	2022-07-05 15:31:50.198	5-Angel Rios 	4	I.V.A.	22005IC04013396R	103687416	0	103687416	109	8
1967	2022-07-05 15:31:50.203	5-Angel Rios 	5	CANON INFORMATICO	4244011	245222	245222	0	109	9
1968	2022-07-05 15:31:50.208	5-Angel Rios 	6	FOTOCOPIAS	001-002-0017924	80000	72727	7272	109	12
1969	2022-07-05 15:31:50.212	5-Angel Rios 	7	TASA PORTUARIA	002-007-0017108	4843501	4403182	440318	109	14
1970	2022-07-05 15:31:50.218	5-Angel Rios 	8	CDAP/ANNP	001-025-0133628	6858	6234	623	109	10
1971	2022-07-05 15:31:50.223	5-Angel Rios 	9	SERVICIOS SOFIA / ANNP	001-004-0081412	121088	110080	11008	109	11
1972	2022-07-05 15:31:50.228	5-Angel Rios 	10	APERTURA AG. TRANSP.	003-001-0001318	480000	436363	43636	109	13
1973	2022-07-05 15:31:50.237	5-Angel Rios 	11	VISACION CONSUL	02951188/89/90/91/92	1110120	1110120	0	109	15
1974	2022-07-05 15:31:50.241	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002029	7187309	6533917	653391	109	16
1975	2022-07-05 16:25:06.671	5-Angel Rios 	1	INDI	22005IC04013396R	26950	26950	0	110	3
1976	2022-07-05 16:25:06.675	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04013396R	5156528	5156528	0	110	5
1977	2022-07-05 16:25:06.68	5-Angel Rios 	3	IRACIS GENERAL 700	22005IC04013396R	4147497	4147497	0	110	7
1978	2022-07-05 16:25:06.685	5-Angel Rios 	4	I.V.A.	22005IC04013396R	103687416	0	103687416	110	8
1979	2022-07-05 16:25:06.689	5-Angel Rios 	5	CANON INFORMATICO	4244011	245222	245222	0	110	9
1980	2022-07-05 16:25:06.695	5-Angel Rios 	6	FOTOCOPIAS	001-002-0017924	80000	72727	7272	110	12
1981	2022-07-05 16:25:06.701	5-Angel Rios 	7	TASA PORTUARIA	002-007-0017108	4843501	4403182	440318	110	14
1982	2022-07-05 16:25:06.708	5-Angel Rios 	8	CDAP/ANNP	001-025-0133628	6858	6234	623	110	10
1983	2022-07-05 16:25:06.714	5-Angel Rios 	9	SERVICIOS SOFIA / ANNP	001-004-0081412	121088	110080	11008	110	11
1984	2022-07-05 16:25:06.72	5-Angel Rios 	10	APERTURA AG. TRANSP.	003-001-0001318	480000	436363	43636	110	13
1985	2022-07-05 16:25:06.729	5-Angel Rios 	11	VISACION CONSUL	02951188/89/90/91/92	1110120	1110120	0	110	15
1986	2022-07-05 16:25:06.734	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002029	7187309	6533917	653391	110	16
1987	2022-07-06 13:26:53.042	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013270X	2806338	2806338	0	111	2
1988	2022-07-06 13:26:53.055	5-Angel Rios 	2	INDI	22005IC04013270X	14420	14420	0	111	3
1989	2022-07-06 13:26:53.064	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013270X	233361	233361	0	111	5
1990	2022-07-06 13:26:53.07	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013270X	200132	200132	0	111	7
1991	2022-07-06 13:26:53.075	5-Angel Rios 	5	I.V.A.	22005IC04013270X	5003292	0	5003292	111	8
1992	2022-07-06 13:26:53.085	5-Angel Rios 	6	CANON INFORMATICO	4252470	245222	245222	0	111	9
1993	2022-07-06 13:26:53.091	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017921	70000	63636	6363	111	12
1994	2022-07-06 13:26:53.097	5-Angel Rios 	8	TASA PORTUARIA	002-007-0016627	260327	236660	23666	111	14
1995	2022-07-06 13:26:53.105	5-Angel Rios 	9	CDAP/ANNP	001-025-0132745	6849	6226	622	111	10
1996	2022-07-06 13:26:53.112	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0080530	6508	5916	591	111	11
1997	2022-07-06 13:26:53.118	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015537	175000	81818	8182	111	13
1998	2022-07-06 13:26:53.122	5-Angel Rios 	12	VISACION CONSUL	02945153	230000	230000	0	111	15
1999	2022-07-06 13:26:53.133	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002030	1859000	1690000	169000	111	16
2000	2022-07-06 13:26:53.159	5-Angel Rios 	14	LICENCIA PREV MIC 	1051806	616357	616357	0	111	32
2001	2022-07-06 13:26:53.169	5-Angel Rios 	15	CDAP PAGOS	001-024-0122431	10000	9090	909	111	27
2002	2022-07-06 13:26:53.175	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002031	4400000	4400000	0	111	21
2003	2022-07-06 13:49:54.881	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013407K	0	0	0	112	2
2004	2022-07-06 13:49:54.888	5-Angel Rios 	2	INDI	22005IC04013407K	26950	26950	0	112	3
2005	2022-07-06 13:49:54.897	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013407K	390196	390196	0	112	5
2006	2022-07-06 13:49:54.902	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013407K	315365	315365	0	112	7
2007	2022-07-06 13:49:54.907	5-Angel Rios 	5	I.V.A.	22005IC04013407K	7884144	0	7884144	112	8
2008	2022-07-06 13:49:54.911	5-Angel Rios 	6	CANON INFORMATICO	4274144	245222	245222	0	112	9
2009	2022-07-06 13:49:54.922	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017922	100000	90909	9090	112	12
2010	2022-07-06 13:49:54.927	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017208	484304	440276	44027	112	14
2011	2022-07-06 13:49:54.931	5-Angel Rios 	9	CDAP/ANNP	001-025-0133802	6858	6234	623	112	10
2012	2022-07-06 13:49:54.936	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0081585	12108	11007	1100	112	11
2013	2022-07-06 13:49:54.94	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015542	175000	81818	8182	112	13
2014	2022-07-06 13:49:54.944	5-Angel Rios 	12	VISACION CONSUL	02952474/75/76	425000	425000	0	112	15
2015	2022-07-06 13:49:54.951	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002032	2216500	2015000	201500	112	16
2016	2022-07-06 13:49:54.958	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	112	32
2017	2022-07-06 13:49:54.964	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	112	27
2018	2022-07-06 13:49:54.969	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002033	1650000	1650000	0	112	21
2019	2022-07-06 14:14:21.57	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013407K	0	0	0	113	2
2020	2022-07-06 14:14:21.586	5-Angel Rios 	2	INDI	22005IC04013407K	26950	26950	0	113	3
2021	2022-07-06 14:14:21.593	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013407K	390196	390196	0	113	5
2022	2022-07-06 14:14:21.598	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013407K	315365	315365	0	113	7
2023	2022-07-06 14:14:21.605	5-Angel Rios 	5	I.V.A.	22005IC04013407K	7884144	0	7884144	113	8
2024	2022-07-06 14:14:21.61	5-Angel Rios 	6	CANON INFORMATICO	4274144	245222	245222	0	113	9
2025	2022-07-06 14:14:21.616	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017922	100000	90909	9090	113	12
2026	2022-07-06 14:14:21.623	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017208	484304	440276	44027	113	14
2027	2022-07-06 14:14:21.629	5-Angel Rios 	9	CDAP/ANNP	001-025-0133802	6858	6234	623	113	10
2028	2022-07-06 14:14:21.638	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0081585	12108	11007	1100	113	11
2029	2022-07-06 14:14:21.642	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015542	175000	81818	8182	113	13
2030	2022-07-06 14:14:21.647	5-Angel Rios 	12	VISACION CONSUL	02952474/75/76	425000	425000	0	113	15
2031	2022-07-06 14:14:21.659	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002032	2216500	2015000	201500	113	16
2032	2022-07-06 14:14:21.665	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	113	32
2033	2022-07-06 14:14:21.67	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	113	27
2034	2022-07-06 14:14:21.675	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002033	1650000	1650000	0	113	21
2035	2022-07-06 14:47:22.252	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013496S	20003316	20003316	0	114	2
2036	2022-07-06 14:47:22.261	5-Angel Rios 	2	INDI	22005IC04013496S	26950	26950	0	114	3
2037	2022-07-06 14:47:22.268	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013496S	2527291	2527291	0	114	5
2038	2022-07-06 14:47:22.274	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013496S	2113604	2113604	0	114	7
2039	2022-07-06 14:47:22.282	5-Angel Rios 	5	I.V.A.	22005IC04013496S	52840076	0	52840076	114	8
2040	2022-07-06 14:47:22.286	5-Angel Rios 	6	CANON INFORMATICO	4274154	245222	245222	0	114	9
2041	2022-07-06 14:47:22.293	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017934	100000	90909	9090	114	12
2042	2022-07-06 14:47:22.299	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017494	2491893	2265357	226535	114	14
2043	2022-07-06 14:47:22.303	5-Angel Rios 	9	CDAP/ANNP	001-025-0134397	6863	6239	623	114	10
2044	2022-07-06 14:47:22.308	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0082181	62297	56633	5663	114	11
2045	2022-07-06 14:47:22.313	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015567	175000	159090	15909	114	13
2046	2022-07-06 14:47:22.318	5-Angel Rios 	12	VISACION CONSUL	02952471/72/73/77	754000	754000	0	114	15
2047	2022-07-06 14:47:22.322	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002034	5452700	4957000	495700	114	16
2048	2022-07-06 14:47:22.327	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	114	32
2049	2022-07-06 14:47:22.333	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	114	27
2050	2022-07-06 14:47:22.338	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	114	21
2051	2022-07-06 14:48:43.149	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013407K	0	0	0	115	2
2052	2022-07-06 14:48:43.154	5-Angel Rios 	2	INDI	22005IC04013407K	26950	26950	0	115	3
2053	2022-07-06 14:48:43.162	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013407K	390196	390196	0	115	5
2054	2022-07-06 14:48:43.167	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013407K	315365	315365	0	115	7
2055	2022-07-06 14:48:43.172	5-Angel Rios 	5	I.V.A.	22005IC04013407K	7884144	0	7884144	115	8
2056	2022-07-06 14:48:43.179	5-Angel Rios 	6	CANON INFORMATICO	4274144	245222	245222	0	115	9
2057	2022-07-06 14:48:43.185	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017922	100000	90909	9090	115	12
2058	2022-07-06 14:48:43.19	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017208	484304	440276	44027	115	14
2059	2022-07-06 14:48:43.199	5-Angel Rios 	9	CDAP/ANNP	001-025-0133802	6858	6234	623	115	10
2060	2022-07-06 14:48:43.203	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0081585	12108	11007	1100	115	11
2061	2022-07-06 14:48:43.208	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015542	175000	81818	8182	115	13
2062	2022-07-06 14:48:43.214	5-Angel Rios 	12	VISACION CONSUL	02952474/75/76	425000	425000	0	115	15
2063	2022-07-06 14:48:43.219	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002032	2216500	2015000	201500	115	16
2064	2022-07-06 14:48:43.225	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	115	32
2065	2022-07-06 14:48:43.234	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	115	27
2066	2022-07-06 14:48:43.239	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002033	1650000	1650000	0	115	21
2067	2022-07-06 14:49:33.736	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013407K	0	0	0	116	2
2068	2022-07-06 14:49:33.815	5-Angel Rios 	2	INDI	22005IC04013407K	26950	26950	0	116	3
2069	2022-07-06 14:49:33.898	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013407K	390196	390196	0	116	5
2070	2022-07-06 14:49:34.035	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013407K	315365	315365	0	116	7
2071	2022-07-06 14:49:34.138	5-Angel Rios 	5	I.V.A.	22005IC04013407K	7884144	0	7884144	116	8
2072	2022-07-06 14:49:34.195	5-Angel Rios 	6	CANON INFORMATICO	4274144	245222	245222	0	116	9
2073	2022-07-06 14:49:34.248	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017922	100000	90909	9090	116	12
2074	2022-07-06 14:49:34.368	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017208	484304	440276	44027	116	14
2075	2022-07-06 14:49:34.425	5-Angel Rios 	9	CDAP/ANNP	001-025-0133802	6858	6234	623	116	10
2076	2022-07-06 14:49:34.514	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0081585	12108	11007	1100	116	11
2077	2022-07-06 14:49:34.602	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015542	175000	81818	8182	116	13
2078	2022-07-06 14:49:34.727	5-Angel Rios 	12	VISACION CONSUL	02952474/75/76	425000	425000	0	116	15
2079	2022-07-06 14:49:34.845	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002032	2216500	2015000	201500	116	16
2080	2022-07-06 14:49:34.904	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	116	32
2081	2022-07-06 14:49:34.993	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	116	27
2082	2022-07-06 14:49:35.12	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002033	1650000	1650000	0	116	21
2083	2022-07-06 14:50:48.447	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013270X	2806338	2806338	0	117	2
2084	2022-07-06 14:50:48.479	5-Angel Rios 	2	INDI	22005IC04013270X	14420	14420	0	117	3
2085	2022-07-06 14:50:48.499	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013270X	233361	233361	0	117	5
2086	2022-07-06 14:50:48.529	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013270X	200132	200132	0	117	7
2087	2022-07-06 14:50:48.587	5-Angel Rios 	5	I.V.A.	22005IC04013270X	5003292	0	5003292	117	8
2088	2022-07-06 14:50:48.652	5-Angel Rios 	6	CANON INFORMATICO	4252470	245222	245222	0	117	9
2089	2022-07-06 14:50:48.689	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017921	70000	63636	6363	117	12
2090	2022-07-06 14:50:48.716	5-Angel Rios 	8	TASA PORTUARIA	002-007-0016627	260327	236660	23666	117	14
2091	2022-07-06 14:50:48.746	5-Angel Rios 	9	CDAP/ANNP	001-025-0132745	6849	6226	622	117	10
2092	2022-07-06 14:50:48.778	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0080530	6508	5916	591	117	11
2093	2022-07-06 14:50:48.821	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015537	175000	81818	8182	117	13
2094	2022-07-06 14:50:48.877	5-Angel Rios 	12	VISACION CONSUL	02945153	230000	230000	0	117	15
2095	2022-07-06 14:50:48.907	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002030	1859000	1690000	169000	117	16
2096	2022-07-06 14:50:48.955	5-Angel Rios 	14	LICENCIA PREV MIC 	1051806	616357	616357	0	117	32
2097	2022-07-06 14:50:48.977	5-Angel Rios 	15	CDAP PAGOS	001-024-0122431	10000	9090	909	117	27
2098	2022-07-06 14:50:49.03	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002031	4400000	4000000	400000	117	21
2099	2022-07-06 14:52:55.291	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013407K	0	0	0	118	2
2100	2022-07-06 14:52:55.332	5-Angel Rios 	2	INDI	22005IC04013407K	26950	26950	0	118	3
2101	2022-07-06 14:52:55.337	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013407K	390196	390196	0	118	5
2102	2022-07-06 14:52:55.343	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013407K	315365	315365	0	118	7
2103	2022-07-06 14:52:55.359	5-Angel Rios 	5	I.V.A.	22005IC04013407K	7884144	0	7884144	118	8
2104	2022-07-06 14:52:55.374	5-Angel Rios 	6	CANON INFORMATICO	4274144	245222	245222	0	118	9
2105	2022-07-06 14:52:55.388	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017922	100000	90909	9090	118	12
2106	2022-07-06 14:52:55.416	5-Angel Rios 	8	TASA PORTUARIA	002-007-0017208	484304	440276	44027	118	14
2107	2022-07-06 14:52:55.447	5-Angel Rios 	9	CDAP/ANNP	001-025-0133802	6858	6234	623	118	10
2108	2022-07-06 14:52:55.496	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0081585	12108	11007	1100	118	11
2109	2022-07-06 14:52:55.516	5-Angel Rios 	11	APERTURA AG. TRANSP.	001-001-0015542	175000	81818	8182	118	13
2110	2022-07-06 14:52:55.522	5-Angel Rios 	12	VISACION CONSUL	02952474/75/76	425000	425000	0	118	15
2111	2022-07-06 14:52:55.532	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002032	2216500	2015000	201500	118	16
2112	2022-07-06 14:52:55.54	5-Angel Rios 	14	LICENCIA PREV MIC 	x	0	0	0	118	32
2113	2022-07-06 14:52:55.552	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	118	27
2114	2022-07-06 14:52:55.558	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002033	1650000	1650000	0	118	21
2115	2022-07-06 15:54:57.772	5-Angel Rios 	1	DERECHO ADUANERO	22002IM04006471U	1588174	1588174	0	119	2
2116	2022-07-06 15:54:57.777	5-Angel Rios 	2	INDI	22002IM04006471U	7202	7202	0	119	3
2117	2022-07-06 15:54:57.782	5-Angel Rios 	3	ARANC CONS FACTURA 	22002IM04006471U	102886	102886	0	119	33
2118	2022-07-06 15:54:57.787	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IM04006471U	132348	132348	0	119	5
2119	2022-07-06 15:54:57.792	5-Angel Rios 	5	IRACIS GENERAL 700	22002IM04006471U	113201	113201	0	119	7
2120	2022-07-06 15:54:57.796	5-Angel Rios 	6	I.V.A.	22002IM04006471U	424503	0	424503	119	8
2121	2022-07-06 15:54:57.804	5-Angel Rios 	7	CANON INFORMATICO	4280828	54044	54044	0	119	9
2122	2022-07-06 15:54:57.818	5-Angel Rios 	8	FOTOCOPIAS	004-003-0026255	42000	38181	3818	119	12
2123	2022-07-06 15:54:57.824	5-Angel Rios 	9	TASA PORTUARIA	001-034-0044877	423658	385143	38514	119	14
2124	2022-07-06 15:54:57.828	5-Angel Rios 	10	CDAP DINAC	001-021-0048465	20597	18724	1872	119	27
2125	2022-07-06 15:54:57.835	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002035	1200000	1090909	109090	119	16
2126	2022-07-06 15:54:57.839	5-Angel Rios 	12	REPOSICION DE GASTOS ADM	x	1280000	1280000	0	119	21
2127	2022-07-06 16:02:26.757	5-Angel Rios 	1	DERECHO ADUANERO	22002IM04006471U	1588174	1588174	0	120	2
2128	2022-07-06 16:02:26.771	5-Angel Rios 	2	INDI	22002IM04006471U	7202	7202	0	120	3
2129	2022-07-06 16:02:26.778	5-Angel Rios 	3	ARANC CONS FACTURA 	22002IM04006471U	102886	102886	0	120	33
2130	2022-07-06 16:02:26.788	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IM04006471U	132348	132348	0	120	5
2131	2022-07-06 16:02:26.793	5-Angel Rios 	5	IRACIS GENERAL 700	22002IM04006471U	113201	113201	0	120	7
2132	2022-07-06 16:02:26.802	5-Angel Rios 	6	I.V.A.	22002IM04006471U	424503	0	424503	120	8
2133	2022-07-06 16:02:26.806	5-Angel Rios 	7	CANON INFORMATICO	4280828	54044	54044	0	120	9
2134	2022-07-06 16:02:26.81	5-Angel Rios 	8	FOTOCOPIAS	004-003-0026255	42000	38181	3818	120	12
2135	2022-07-06 16:02:26.818	5-Angel Rios 	9	TASA PORTUARIA	001-034-0044877	423658	385143	38514	120	14
2136	2022-07-06 16:02:26.823	5-Angel Rios 	10	CDAP DINAC	001-021-0048465	20597	18724	1872	120	27
2137	2022-07-06 16:02:26.832	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002035	1200000	1090909	109090	120	16
2138	2022-07-06 16:02:26.863	5-Angel Rios 	12	REPOSICION DE GASTOS ADM	x	1280000	1280000	0	120	21
2139	2022-07-08 12:53:02.267	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04004226J	33629303	33629303	0	121	2
2140	2022-07-08 12:53:02.274	5-Angel Rios 	2	INDI	22032IC04004226J	68662	68662	0	121	3
2141	2022-07-08 12:53:02.281	5-Angel Rios 	3	I.S.C.	22032IC04004226J	281423	281423	0	121	4
2142	2022-07-08 12:53:02.294	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04004226J	2887139	2887139	0	121	5
2143	2022-07-08 12:53:02.299	5-Angel Rios 	5	IRACIS GENERAL 700	22032IC04004226J	2461104	2461104	0	121	7
2144	2022-07-08 12:53:02.307	5-Angel Rios 	6	I.V.A.	22032IC04004226J	61527589	0	61527589	121	8
2145	2022-07-08 12:53:02.314	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04004226J	343203	343203	0	121	35
2146	2022-07-08 12:53:02.32	5-Angel Rios 	8	CANON INFORMATICO	429881	245222	245222	0	121	9
2147	2022-07-08 12:53:02.324	5-Angel Rios 	9	FOTOCOPIAS	001-001-0030852	98000	89090	8909	121	12
2148	2022-07-08 12:53:02.332	5-Angel Rios 	10	TASA PORTUARIA	001-005-08952	4521237	4110215	411021	121	14
2149	2022-07-08 12:53:02.338	5-Angel Rios 	11	MOPC	4298807	49045	49045	0	121	34
2150	2022-07-08 12:53:02.343	5-Angel Rios 	12	CDAP PAGOS	001-024-0128430	10000	9090	909	121	27
2151	2022-07-08 12:53:02.349	5-Angel Rios 	13	VISACION MRE	1060411	980890	980890	0	121	28
2152	2022-07-08 12:53:02.356	5-Angel Rios 	14	CDAP PAGOS	001-024-0128429	10000	9090	909	121	27
2153	2022-07-08 12:53:02.361	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	5830000	5300000	530000	121	16
2154	2022-07-08 12:53:02.369	5-Angel Rios 	16	GASTO ADMIN.	x	1900000	1900000	0	121	17
2155	2022-07-08 12:53:02.374	5-Angel Rios 	17	TASA INT. ANNP	429881	343203	343203	0	121	36
2156	2022-07-08 12:59:38.657	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04004226J	33629303	33629303	0	122	2
2157	2022-07-08 12:59:38.665	5-Angel Rios 	2	INDI	22032IC04004226J	68662	68662	0	122	3
2158	2022-07-08 12:59:38.67	5-Angel Rios 	3	I.S.C.	22032IC04004226J	281423	281423	0	122	4
2159	2022-07-08 12:59:38.677	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04004226J	2887139	2887139	0	122	5
2160	2022-07-08 12:59:38.686	5-Angel Rios 	5	IRACIS GENERAL 700	22032IC04004226J	2461104	2461104	0	122	7
2161	2022-07-08 12:59:38.694	5-Angel Rios 	6	I.V.A.	22032IC04004226J	61527589	0	61527589	122	8
2162	2022-07-08 12:59:38.701	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04004226J	343203	343203	0	122	35
2163	2022-07-08 12:59:38.707	5-Angel Rios 	8	CANON INFORMATICO	429881	245222	245222	0	122	9
2164	2022-07-08 12:59:38.714	5-Angel Rios 	9	FOTOCOPIAS	001-001-0030852	98000	89090	8909	122	12
2165	2022-07-08 12:59:38.718	5-Angel Rios 	10	TASA PORTUARIA	001-005-08952	4521237	4110215	411021	122	14
2166	2022-07-08 12:59:38.727	5-Angel Rios 	11	MOPC	4298807	49045	49045	0	122	34
2167	2022-07-08 12:59:38.732	5-Angel Rios 	12	CDAP PAGOS	001-024-0128430	10000	9090	909	122	27
2168	2022-07-08 12:59:38.737	5-Angel Rios 	13	VISACION MRE	1060411	980890	980890	0	122	28
2169	2022-07-08 12:59:38.741	5-Angel Rios 	14	CDAP PAGOS	001-024-0128429	10000	9090	909	122	27
2170	2022-07-08 12:59:38.747	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002036	5830000	5300000	530000	122	16
2171	2022-07-08 12:59:38.751	5-Angel Rios 	16	GASTO ADMIN.	716	1900000	1900000	0	122	17
2172	2022-07-08 12:59:38.756	5-Angel Rios 	17	TASA INT. ANNP	429881	343203	343203	0	122	36
2173	2022-07-08 15:27:23.276	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013676S	101657	101657	0	123	2
2174	2022-07-08 15:27:23.282	5-Angel Rios 	2	INDI	22005IC04013676S	26461	26461	0	123	3
2175	2022-07-08 15:27:23.289	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013676S	3606298	3606298	0	123	5
2176	2022-07-08 15:27:23.293	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013676S	2901489	2901489	0	123	7
2177	2022-07-08 15:27:23.297	5-Angel Rios 	5	I.V.A.	22005IC04013676S	72537206	0	72537206	123	8
2178	2022-07-08 15:27:23.303	5-Angel Rios 	6	CANON INFORMATICO	4304021	245222	245222	0	123	9
2179	2022-07-08 15:27:23.307	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017946	100000	90909	9090	123	12
2180	2022-07-08 15:27:23.312	5-Angel Rios 	8	TASA PORTUARIA	002-007-0018188	3232767	2938879	293887	123	14
2181	2022-07-08 15:27:23.317	5-Angel Rios 	9	CDAP PAGOS	001-025-0135720	6862	6238	623	123	27
2182	2022-07-08 15:27:23.326	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0083513	80819	73471	7347	123	11
2183	2022-07-08 15:27:23.334	5-Angel Rios 	11	VISACION CONSUL	02955609/10/11/12/13	1005000	1005000	0	123	15
2184	2022-07-08 15:27:23.344	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002039	5281133	4801030	480103	123	16
2185	2022-07-08 15:27:23.353	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	123	17
2186	2022-07-08 15:54:41.775	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000310J	1339420	1339420	0	124	2
2187	2022-07-08 15:54:41.785	5-Angel Rios 	2	INDI	22005IM04000310J	14413	14413	0	124	3
2188	2022-07-08 15:54:41.79	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000310J	77264	77264	0	124	5
2189	2022-07-08 15:54:41.796	5-Angel Rios 	4	IRACIS GENERAL 700	22005IM04000310J	68358	68358	0	124	7
2190	2022-07-08 15:54:41.8	5-Angel Rios 	5	I.V.A.	22005IM04000310J	1708953	0	1708953	124	8
2191	2022-07-08 15:54:41.807	5-Angel Rios 	6	CANON INFORMATICO	4304066	49044	49044	0	124	9
2192	2022-07-08 15:54:41.812	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017944	80000	72727	7272	124	12
2193	2022-07-08 15:54:41.82	5-Angel Rios 	8	TASA PORTUARIA	002-007-0018191	88069	80062	8006	124	14
2194	2022-07-08 15:54:41.826	5-Angel Rios 	9	CDAP PAGOS	001-025-0135732	6862	6238	623	124	27
2195	2022-07-08 15:54:41.833	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0083516	2202	2001	200	124	11
2196	2022-07-08 15:54:41.84	5-Angel Rios 	11	VISACION CONSUL	02955614/15	243000	243000	0	124	15
2197	2022-07-08 15:54:41.851	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002040	1243000	1130000	113000	124	16
2198	2022-07-08 15:54:41.856	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	124	17
2199	2022-07-08 16:06:52.528	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013669U	0	0	0	125	2
2200	2022-07-08 16:06:52.533	5-Angel Rios 	2	INDI	22005IC04013669U	26460	26460	0	125	3
2201	2022-07-08 16:06:52.538	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013669U	295545	295545	0	125	5
2202	2022-07-08 16:06:52.546	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013669U	239236	239236	0	125	7
2203	2022-07-08 16:06:52.55	5-Angel Rios 	5	I.V.A.	22005IC04013669U	5980904	0	5980904	125	8
2204	2022-07-08 16:06:52.556	5-Angel Rios 	6	CANON INFORMATICO	4304042	245222	245222	0	125	9
2205	2022-07-08 16:06:52.561	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017945	90000	81818	8181	125	12
2206	2022-07-08 16:06:52.565	5-Angel Rios 	8	TASA PORTUARIA	002-007-0018192	328488	298625	29862	125	14
2207	2022-07-08 16:06:52.571	5-Angel Rios 	9	CDAP PAGOS	001-025-0135731	6862	6238	623	125	27
2208	2022-07-08 16:06:52.581	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0083517	8212	7465	746	125	11
2209	2022-07-08 16:06:52.588	5-Angel Rios 	11	VISACION CONSUL	02956181/82	243000	243000	0	125	15
2210	2022-07-08 16:06:52.601	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002041	2049160	1862872	186287	125	16
2211	2022-07-08 16:06:52.608	5-Angel Rios 	14	VISACION CONSUL	106703	490445	490445	0	125	15
2212	2022-07-08 16:06:52.613	5-Angel Rios 	15	CDAP PAGOS	001-024-0129254	10000	9090	909	125	27
2213	2022-07-12 10:07:30.981	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013794T	0	0	0	126	2
2214	2022-07-12 10:07:30.988	5-Angel Rios 	2	INDI	22005IC04013794T	26460	26460	0	126	3
2215	2022-07-12 10:07:30.995	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013794T	1207401	1207401	0	126	5
2216	2022-07-12 10:07:31.001	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013794T	972368	972368	0	126	7
2217	2022-07-12 10:07:31.01	5-Angel Rios 	5	I.V.A.	22005IC04013794T	24309196	0	24309196	126	8
2218	2022-07-12 10:07:31.016	5-Angel Rios 	6	CANON INFORMATICO	4309707	245222	245222	0	126	9
2219	2022-07-12 10:07:31.029	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017953	70000	63636	6363	126	12
2220	2022-07-12 10:07:31.034	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015645	175000	81818	8182	126	13
2221	2022-07-12 10:07:31.041	5-Angel Rios 	9	TASA PORTUARIA	002-007-0018589	1272821	1157110	115711	126	14
2222	2022-07-12 10:07:31.045	5-Angel Rios 	10	CDAP/ANNP	001-025-0136400	6868	6243	624	126	10
2223	2022-07-12 10:07:31.05	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0084189	31821	28928	2892	126	11
2224	2022-07-12 10:07:31.053	5-Angel Rios 	12	VISACION MRE	1062818	490445	490445	0	126	28
2225	2022-07-12 10:07:31.063	5-Angel Rios 	13	CDAP PAGOS	001-024-0129991	10000	9090	909	126	27
2226	2022-07-12 10:07:31.065	5-Angel Rios 	14	VISACION CONSUL	02956500/01/02	473000	473000	0	126	15
2227	2022-07-12 10:07:31.073	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	x	3737141	3397400	339740	126	16
2228	2022-07-12 13:11:58.211	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013794T	0	0	0	127	2
2229	2022-07-12 13:11:58.218	5-Angel Rios 	2	INDI	22005IC04013794T	26460	26460	0	127	3
2230	2022-07-12 13:11:58.226	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013794T	1207401	1207401	0	127	5
2231	2022-07-12 13:11:58.235	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013794T	972368	972368	0	127	7
2232	2022-07-12 13:11:58.242	5-Angel Rios 	5	I.V.A.	22005IC04013794T	24309196	0	24309196	127	8
2233	2022-07-12 13:11:58.248	5-Angel Rios 	6	CANON INFORMATICO	4309707	245222	245222	0	127	9
2234	2022-07-12 13:11:58.258	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017953	70000	63636	6363	127	12
2235	2022-07-12 13:11:58.265	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015645	175000	81818	8182	127	13
2236	2022-07-12 13:11:58.271	5-Angel Rios 	9	TASA PORTUARIA	002-007-0018589	1272821	1157110	115711	127	14
2237	2022-07-12 13:11:58.277	5-Angel Rios 	10	CDAP/ANNP	001-025-0136400	6868	6243	624	127	10
2238	2022-07-12 13:11:58.284	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0084189	31821	28928	2892	127	11
2239	2022-07-12 13:11:58.292	5-Angel Rios 	12	VISACION MRE	1062818	490445	490445	0	127	28
2240	2022-07-12 13:11:58.298	5-Angel Rios 	13	CDAP PAGOS	001-024-0129991	10000	9090	909	127	27
2241	2022-07-12 13:11:58.305	5-Angel Rios 	14	VISACION CONSUL	02956500/01/02	473000	473000	0	127	15
2242	2022-07-12 13:11:58.311	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002042	3737141	3397400	339740	127	16
2243	2022-07-13 10:22:25.479	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04013997B	0	0	0	128	2
2244	2022-07-13 10:22:25.488	5-Angel Rios 	2	INDI	22005IC04013997B	26600	26600	0	128	3
2245	2022-07-13 10:22:25.494	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04013997B	274098	274098	0	128	5
2246	2022-07-13 10:22:25.502	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04013997B	222001	222001	0	128	7
2247	2022-07-13 10:22:25.511	5-Angel Rios 	5	I.V.A.	22005IC04013997B	5550036	0	5550036	128	8
2248	2022-07-13 10:22:25.518	5-Angel Rios 	6	CANON INFORMATICO	4327215	245222	245222	0	128	9
2249	2022-07-13 10:22:25.525	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017957	110000	100000	10000	128	12
2250	2022-07-13 10:22:25.532	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0015757	175000	81818	8182	128	13
2251	2022-07-13 10:22:25.54	5-Angel Rios 	9	TASA PORTUARIA	002-007-0019417	321088	291898	29189	128	14
2252	2022-07-13 10:22:25.568	5-Angel Rios 	10	CDAP/ANNP	001-025-0137898	6867	6242	624	128	10
2253	2022-07-13 10:22:25.58	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0085687	8027	7297	729	128	11
2254	2022-07-13 10:22:25.589	5-Angel Rios 	12	VISACION CONSUL	02957028/29/30	430000	430000	0	128	15
2255	2022-07-13 10:22:25.596	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002043	2011000	1828181	182818	128	16
2256	2022-07-13 10:22:25.607	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	128	17
2257	2022-07-13 14:51:20.551	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04000914@	0	0	0	129	2
2258	2022-07-13 14:51:20.558	5-Angel Rios 	2	INDI	22005IC04000914@	26950	26950	0	129	3
2259	2022-07-13 14:51:20.565	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04000914@	300352	300352	0	129	5
2260	2022-07-13 14:51:20.573	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04000914@	243131	243131	0	129	7
2261	2022-07-13 14:51:20.605	5-Angel Rios 	5	I.V.A.	22005IC04000914@	6078272	0	6078272	129	8
2262	2022-07-13 14:51:20.64	5-Angel Rios 	6	CANON INFORMATICO	x	245222	245222	0	129	9
2263	2022-07-13 14:51:20.75	5-Angel Rios 	7	FOTOCOPIAS	x	80000	72727	7272	129	12
2264	2022-07-13 14:51:20.757	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	129	13
2265	2022-07-13 14:51:20.767	5-Angel Rios 	9	TASA PORTUARIA	x	450000	409090	40909	129	14
2266	2022-07-13 14:51:20.773	5-Angel Rios 	10	CDAP/ANNP	x	0	0	0	129	10
2267	2022-07-13 14:51:20.787	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	129	11
2268	2022-07-13 14:51:20.807	5-Angel Rios 	12	VISACION CONSUL	x	880000	880000	0	129	15
2269	2022-07-13 14:51:20.851	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	1450000	1318181	131818	129	16
2270	2022-07-13 14:51:20.858	5-Angel Rios 	14	GASTO ADMIN.	x	850000	850000	0	129	17
2271	2022-07-14 12:40:47.255	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000909R	0	0	0	130	2
2272	2022-07-14 12:40:47.266	5-Angel Rios 	2	INDI	22009IC04000909R	26460	26460	0	130	3
2273	2022-07-14 12:40:47.276	5-Angel Rios 	3	SERVICIO DE VALORACION	22009IC04000909R	367684	367684	0	130	5
2274	2022-07-14 12:40:47.282	5-Angel Rios 	4	IRACIS GENERAL 700	22009IC04000909R	297236	297236	0	130	7
2275	2022-07-14 12:40:47.288	5-Angel Rios 	5	I.V.A.	22009IC04000909R	7430894	0	7430894	130	8
2276	2022-07-14 12:40:47.296	5-Angel Rios 	6	CANON INFORMATICO	4311585	245222	245222	0	130	9
2277	2022-07-14 12:40:47.301	5-Angel Rios 	7	FOTOCOPIAS	x	80000	72727	7272	130	12
2278	2022-07-14 12:40:47.315	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	350000	318181	31818	130	13
2279	2022-07-14 12:40:47.33	5-Angel Rios 	9	TASA PORTUARIA	4312316	454354	413049	41304	130	14
2280	2022-07-14 12:40:47.336	5-Angel Rios 	10	CDAP/ANNP	x	0	0	0	130	10
2281	2022-07-14 12:40:47.342	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	130	11
2282	2022-07-14 12:40:47.347	5-Angel Rios 	12	VISACION CONSUL	x	814200	814200	0	130	15
2283	2022-07-14 12:40:47.351	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2648800	2408000	240800	130	16
2284	2022-07-14 12:40:47.361	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	130	17
2285	2022-07-14 13:42:43.582	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000909R	0	0	0	131	2
2286	2022-07-14 13:42:43.589	5-Angel Rios 	2	INDI	22009IC04000909R	26460	26460	0	131	3
2287	2022-07-14 13:42:43.598	5-Angel Rios 	3	SERVICIO DE VALORACION	22009IC04000909R	367684	367684	0	131	5
2288	2022-07-14 13:42:43.603	5-Angel Rios 	4	IRACIS GENERAL 700	22009IC04000909R	297236	297236	0	131	7
2289	2022-07-14 13:42:43.607	5-Angel Rios 	5	I.V.A.	22009IC04000909R	7430894	0	7430894	131	8
2290	2022-07-14 13:42:43.612	5-Angel Rios 	6	CANON INFORMATICO	4311585	245222	245222	0	131	9
2291	2022-07-14 13:42:43.617	5-Angel Rios 	7	FOTOCOPIAS	x	80000	72727	7272	131	12
2292	2022-07-14 13:42:43.622	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	350000	318181	31818	131	13
2293	2022-07-14 13:42:43.629	5-Angel Rios 	9	TASA PORTUARIA	4312316	454354	413049	41304	131	14
2294	2022-07-14 13:42:43.634	5-Angel Rios 	10	CDAP/ANNP	x	0	0	0	131	10
2295	2022-07-14 13:42:43.638	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	131	11
2296	2022-07-14 13:42:43.645	5-Angel Rios 	12	VISACION CONSUL	x	688000	688000	0	131	15
2297	2022-07-14 13:42:43.649	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2989800	2718000	271800	131	16
2298	2022-07-14 13:42:43.654	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	131	17
2299	2022-07-14 14:14:31.871	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04014217K	0	0	0	132	2
2300	2022-07-14 14:14:31.953	5-Angel Rios 	2	INDI	22005IC04014217K	26460	26460	0	132	3
2301	2022-07-14 14:14:31.976	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04014217K	3185276	3185276	0	132	5
2302	2022-07-14 14:14:32.002	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04014217K	2562580	2562580	0	132	7
2303	2022-07-14 14:14:32.029	5-Angel Rios 	5	I.V.A.	22005IC04014217K	64064503	0	64064503	132	8
2304	2022-07-14 14:14:32.063	5-Angel Rios 	6	CANON INFORMATICO	4335865	245222	245222	0	132	9
2305	2022-07-14 14:14:32.089	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017966	70000	63636	6363	132	12
2306	2022-07-14 14:14:32.109	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	132	13
2307	2022-07-14 14:14:32.139	5-Angel Rios 	9	TASA PORTUARIA	002-007-0020080	3044234	2767485	276748	132	14
2308	2022-07-14 14:14:32.153	5-Angel Rios 	10	CDAP/ANNP	001-025-0139145	6860	6236	623	132	10
2309	2022-07-14 14:14:32.177	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0086938	76106	69187	6918	132	11
2310	2022-07-14 14:14:32.193	5-Angel Rios 	12	VISACION CONSUL	02959248/49/50/51	810000	810000	0	132	15
2311	2022-07-14 14:14:32.221	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002044	5109500	4645000	464500	132	16
2312	2022-07-14 14:14:32.235	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	132	17
2313	2022-07-18 16:12:42.22	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	133	2
2314	2022-07-18 16:12:42.227	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	133	3
2315	2022-07-18 16:12:42.233	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	133	5
2316	2022-07-18 16:12:42.239	5-Angel Rios 	4	IRACIS GENERAL 700	22017IC04003694U	1637379	1637379	0	133	7
2317	2022-07-18 16:12:42.248	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	133	8
2318	2022-07-18 16:12:42.254	5-Angel Rios 	6	TASA INTER. ADUAN.	x	342991	342991	0	133	24
2319	2022-07-18 16:12:42.259	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	133	9
2320	2022-07-18 16:12:42.264	5-Angel Rios 	8	TASA INT. ANNP	x	342991	342991	0	133	36
2321	2022-07-18 16:12:42.269	5-Angel Rios 	9	FOTOCOPIAS	x	0	0	0	133	12
2322	2022-07-18 16:12:42.274	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	133	14
2323	2022-07-18 16:12:42.283	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	133	27
2324	2022-07-18 16:12:42.288	5-Angel Rios 	12	VISACION MRE	x	980890	980890	0	133	28
2325	2022-07-18 16:12:42.292	5-Angel Rios 	13	CDAP PAGOS	x	10000	9090	909	133	27
2326	2022-07-18 16:12:42.297	5-Angel Rios 	14	TASA MIC DINAVISA	x	0	0	0	133	37
2327	2022-07-18 16:12:42.307	5-Angel Rios 	15	GASTOS BANCARIOS 	x	3400000	3400000	0	133	27
2328	2022-07-18 16:12:42.312	5-Angel Rios 	16	MOPC	x	49045	49045	0	133	34
2329	2022-07-18 16:12:42.317	5-Angel Rios 	17	CDAP PAGOS	x	10000	9090	909	133	27
2330	2022-07-18 16:12:42.321	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	x	4238893	3853539	385353	133	16
2331	2022-07-18 16:12:42.326	5-Angel Rios 	19	GASTO ADMIN.	x	1000000	1000000	0	133	17
2332	2022-07-18 16:12:42.334	5-Angel Rios 	20	MUESTRAS AEROP. 1	x	2750000	2750000	0	133	17
2333	2022-07-18 16:12:42.338	5-Angel Rios 	22	REGISTRO DE FIRMA 	x	1000000	1000000	0	133	21
2334	2022-07-18 16:16:34.138	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	134	2
2335	2022-07-18 16:16:34.144	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	134	3
2336	2022-07-18 16:16:34.151	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	134	5
2337	2022-07-18 16:16:34.157	5-Angel Rios 	4	IRACIS GENERAL 700	22017IC04003694U	1637379	1637379	0	134	7
2338	2022-07-18 16:16:34.161	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	134	8
2339	2022-07-18 16:16:34.168	5-Angel Rios 	6	TASA INTER. ADUAN.	x	342991	342991	0	134	24
2340	2022-07-18 16:16:34.174	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	134	9
2341	2022-07-18 16:16:34.178	5-Angel Rios 	8	TASA INT. ANNP	x	342991	342991	0	134	36
2342	2022-07-18 16:16:34.189	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	134	12
2343	2022-07-18 16:16:34.195	5-Angel Rios 	10	TASA PORTUARIA	x	4948187	4498351	449835	134	14
2344	2022-07-18 16:16:34.201	5-Angel Rios 	11	FLETE INTERNO APROX	x	1345000	1222727	122272	134	27
2345	2022-07-18 16:16:34.205	5-Angel Rios 	12	VISACION MRE	x	980890	980890	0	134	28
2347	2022-07-18 16:16:34.227	5-Angel Rios 	14	TASA MIC DINAVISA	x	608534	553212	55321	134	37
2348	2022-07-18 16:16:34.234	5-Angel Rios 	15	GASTOS BANCARIOS 	x	3700000	3363636	336363	134	27
2349	2022-07-18 16:16:34.24	5-Angel Rios 	16	MOPC	x	49045	49045	0	134	34
2350	2022-07-18 16:16:34.254	5-Angel Rios 	17	CDAP PAGOS	x	10000	9090	909	134	27
2351	2022-07-18 16:16:34.261	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	x	4238893	3853539	385353	134	16
2352	2022-07-18 16:16:34.27	5-Angel Rios 	19	GASTO ADMIN.	x	1000000	1000000	0	134	17
2353	2022-07-18 16:16:34.276	5-Angel Rios 	20	MUESTRAS AEROP. 1	x	2750000	2750000	0	134	17
2354	2022-07-18 16:16:34.288	5-Angel Rios 	22	REGISTRO DE FIRMA 	x	1000000	1000000	0	134	21
2355	2022-07-18 16:18:54.616	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	135	2
2356	2022-07-18 16:18:54.637	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	135	3
2357	2022-07-18 16:18:54.656	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	135	5
2358	2022-07-18 16:18:54.674	5-Angel Rios 	4	IRACIS GENERAL 700	22017IC04003694U	1637379	1637379	0	135	7
2359	2022-07-18 16:18:54.679	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	135	8
2360	2022-07-18 16:18:54.693	5-Angel Rios 	6	TASA INTER. ADUAN.	x	342991	342991	0	135	24
2361	2022-07-18 16:18:54.706	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	135	9
2362	2022-07-18 16:18:54.737	5-Angel Rios 	8	TASA INT. ANNP	x	342991	342991	0	135	36
2363	2022-07-18 16:18:54.749	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	135	12
2364	2022-07-18 16:18:54.758	5-Angel Rios 	10	TASA PORTUARIA	x	4948187	4498351	449835	135	14
2365	2022-07-18 16:18:54.766	5-Angel Rios 	11	FLETE INTERNO APROX	x	1345000	1222727	122272	135	27
2366	2022-07-18 16:18:54.774	5-Angel Rios 	12	VISACION MRE	x	980890	980890	0	135	28
2367	2022-07-18 16:18:54.782	5-Angel Rios 	13	CDAP PAGOS	x	10000	9090	909	135	27
2368	2022-07-18 16:18:54.79	5-Angel Rios 	14	TASA MIC DINAVISA	x	608534	553212	55321	135	37
2369	2022-07-18 16:18:54.8	5-Angel Rios 	15	GASTOS BANCARIOS 	x	3700000	3363636	336363	135	27
2370	2022-07-18 16:18:54.825	5-Angel Rios 	16	MOPC	x	49045	49045	0	135	34
2371	2022-07-18 16:18:54.835	5-Angel Rios 	17	CDAP PAGOS	x	10000	9090	909	135	27
2372	2022-07-18 16:18:54.843	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	x	4238893	3853539	385353	135	16
2373	2022-07-18 16:18:54.853	5-Angel Rios 	19	GASTO ADMIN.	x	1000000	1000000	0	135	17
2374	2022-07-18 16:18:54.862	5-Angel Rios 	20	MUESTRAS AEROP. 1	x	2750000	2750000	0	135	17
2375	2022-07-18 16:18:54.873	5-Angel Rios 	22	REGISTRO DE FIRMA 22-23	x	1000000	1000000	0	135	21
2376	2022-07-18 16:28:09.98	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	136	2
2377	2022-07-18 16:28:09.987	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	136	3
2378	2022-07-18 16:28:09.994	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	136	5
2379	2022-07-18 16:28:10.004	5-Angel Rios 	4	IRACIS GENERAL 700	22017IC04003694U	1637379	1637379	0	136	7
2380	2022-07-18 16:28:10.01	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	136	8
2381	2022-07-18 16:28:10.021	5-Angel Rios 	6	TASA INTER. ADUAN.	x	342991	342991	0	136	24
2382	2022-07-18 16:28:10.049	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	136	9
2383	2022-07-18 16:28:10.054	5-Angel Rios 	8	TASA INT. ANNP	x	342991	342991	0	136	36
2384	2022-07-18 16:28:10.07	5-Angel Rios 	9	FOTOCOPIAS	x	100000	90909	9090	136	12
2385	2022-07-18 16:28:10.077	5-Angel Rios 	10	TASA PORTUARIA	x	4948187	4498351	449835	136	14
2386	2022-07-18 16:28:10.082	5-Angel Rios 	11	FLETE INTERNO APROX	x	1345000	1222727	122272	136	27
2387	2022-07-18 16:28:10.093	5-Angel Rios 	12	VISACION MRE	x	980890	980890	0	136	28
2388	2022-07-18 16:28:10.108	5-Angel Rios 	13	CDAP PAGOS	x	10000	9090	909	136	27
2389	2022-07-18 16:28:10.118	5-Angel Rios 	14	TASA MIC DINAVISA	x	608534	553212	55321	136	37
2390	2022-07-18 16:28:10.135	5-Angel Rios 	15	GASTOS BANCARIOS 	x	3700000	3363636	336363	136	27
2391	2022-07-18 16:28:10.16	5-Angel Rios 	16	MOPC	x	49045	49045	0	136	34
2392	2022-07-18 16:28:10.17	5-Angel Rios 	17	CDAP PAGOS	x	10000	9090	909	136	27
2393	2022-07-18 16:28:10.184	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	x	4238893	3853539	385353	136	16
2394	2022-07-18 16:28:10.205	5-Angel Rios 	19	GASTO ADMIN.	x	1500000	1500000	0	136	17
2395	2022-07-18 16:28:10.237	5-Angel Rios 	20	MUESTRAS AEROP. 1	x	2750000	2750000	0	136	17
2396	2022-07-18 16:28:10.26	5-Angel Rios 	22	REGISTRO DE FIRMA 22-23	x	1000000	1000000	0	136	21
2397	2022-07-19 08:59:42.91	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04014373N	0	0	0	137	2
2398	2022-07-19 08:59:42.917	5-Angel Rios 	2	INDI	22005IC04014373N	26460	26460	0	137	3
2399	2022-07-19 08:59:42.926	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04014373N	640116	640116	0	137	5
2400	2022-07-19 08:59:42.932	5-Angel Rios 	4	IRACIS GENERAL 700	22005IC04014373N	516271	516271	0	137	7
2401	2022-07-19 08:59:42.937	5-Angel Rios 	5	I.V.A.	22005IC04014373N	12906776	0	12906776	137	8
2402	2022-07-19 08:59:42.945	5-Angel Rios 	6	CANON INFORMATICO	4344647	245222	245222	0	137	9
2403	2022-07-19 08:59:42.949	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017977	70000	63636	6363	137	12
2404	2022-07-19 08:59:42.954	5-Angel Rios 	8	TASA PORTUARIA	002-007-0020781	757984	689076	68907	137	14
2405	2022-07-19 08:59:42.96	5-Angel Rios 	9	CDAP PAGOS	001-025-0140335	6867	6242	624	137	27
2406	2022-07-19 08:59:42.965	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0088120	18950	17227	1722	137	11
2407	2022-07-19 08:59:42.969	5-Angel Rios 	11	VISACION MRE	1068008	490445	490445	0	137	28
2408	2022-07-19 08:59:42.974	5-Angel Rios 	12	CDAP PAGOS	001-024-0133739	10000	9090	909	137	27
2409	2022-07-19 08:59:42.983	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002045	3113000	2830000	283000	137	16
2410	2022-07-19 08:59:42.99	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	137	17
2411	2022-07-19 09:23:00.939	5-Angel Rios 	1	INDI	22005IC04014423J	14421	14421	0	138	3
2412	2022-07-19 09:23:00.946	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04014423J	3174489	3174489	0	138	5
2413	2022-07-19 09:23:00.951	5-Angel Rios 	3	IRE GENERAL 700	22005IC04014423J	2553171	2553171	0	138	38
2414	2022-07-19 09:23:00.959	5-Angel Rios 	4	I.V.A.	22005IC04014423J	63829274	0	63829274	138	8
2415	2022-07-19 09:23:00.963	5-Angel Rios 	5	TASA INTER. ADUAN.	x	343350	343350	0	138	24
2416	2022-07-19 09:23:00.969	5-Angel Rios 	6	CANON INFORMATICO	4344730	245222	245222	0	138	9
2417	2022-07-19 09:23:00.976	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017981	70000	63636	6363	138	12
2418	2022-07-19 09:23:00.981	5-Angel Rios 	8	TASA PORTUARIA	002-007-0021012	3509705	3190640	319064	138	14
2419	2022-07-19 09:23:00.99	5-Angel Rios 	9	CDAP PAGOS	001-025-0140805	6866	6241	624	138	27
2420	2022-07-19 09:23:00.998	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0088594	87743	79766	7976	138	11
2421	2022-07-19 09:23:01.003	5-Angel Rios 	11	VISACION MRE	1068004	490445	490445	0	138	28
2422	2022-07-19 09:23:01.008	5-Angel Rios 	12	CDAP PAGOS	001-024-0134011	10000	9090	909	138	27
2423	2022-07-19 09:23:01.018	5-Angel Rios 	13	MIPYMES MIC	1853468	620757	564324	56432	138	22
2424	2022-07-19 09:23:01.024	5-Angel Rios 	14	APORTE VUE	1853469	73188	66534	6653	138	23
2425	2022-07-19 09:23:01.028	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002046	10609500	9645000	964500	138	16
2426	2022-07-19 09:23:01.035	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	138	17
2427	2022-07-19 09:53:56.933	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	139	2
2428	2022-07-19 09:53:56.942	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	139	3
2429	2022-07-19 09:53:56.957	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	139	5
2430	2022-07-19 09:53:56.966	5-Angel Rios 	4	IRE GENERAL 700	22017IC04003694U	1637379	1637379	0	139	38
2431	2022-07-19 09:53:56.972	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	139	8
2432	2022-07-19 09:53:56.98	5-Angel Rios 	6	TASA INTER. ADUAN.	22017IC04003694U	342991	342991	0	139	24
2433	2022-07-19 09:53:56.991	5-Angel Rios 	7	CANON INFORMATICO	4322796	245222	245222	0	139	9
2434	2022-07-19 09:53:57.002	5-Angel Rios 	8	TASA INT. ANNP	4322796	342991	342991	0	139	36
2435	2022-07-19 09:53:57.008	5-Angel Rios 	9	FOTOCOPIAS	006-002-0006247	105000	95454	9545	139	12
2436	2022-07-19 09:53:57.026	5-Angel Rios 	10	TASA PORTUARIA	x	4948187	4498351	449835	139	14
2437	2022-07-19 09:53:57.031	5-Angel Rios 	11	FOTOCOPIAS 	006-002-0006436	30100	27363	2736	139	27
2438	2022-07-19 09:53:57.036	5-Angel Rios 	12	VISACION MRE	1063941	980890	980890	0	139	28
2439	2022-07-19 09:53:57.043	5-Angel Rios 	13	CDAP PAGOS	001-024-0132357	10000	9090	909	139	27
2440	2022-07-19 09:53:57.05	5-Angel Rios 	14	TASA MIC DINAVISA	1068498	196178	178343	17834	139	37
2441	2022-07-19 09:53:57.063	5-Angel Rios 	15	CDAP PAGOS	001-024-0133852	10000	9090	909	139	27
2442	2022-07-19 09:53:57.07	5-Angel Rios 	16	MOPC	1063963	49045	49045	0	139	34
2443	2022-07-19 09:53:57.076	5-Angel Rios 	17	CDAP PAGOS	001-024-0132358	10000	9090	909	139	27
2444	2022-07-19 09:53:57.082	5-Angel Rios 	18	FLETE INTERNO 	001-001-000818	1045000	950000	95000	139	39
2445	2022-07-19 09:53:57.089	5-Angel Rios 	19	GASTOS BANCARIOS	x	3700000	3700000	0	139	40
2446	2022-07-19 09:53:57.096	5-Angel Rios 	20	REGISTRO DE FIRMA 22-23	x	1000000	1000000	0	139	41
2447	2022-07-19 09:53:57.103	5-Angel Rios 	21	MUESTRAS AEROP. 1 	x	2750000	2750000	0	139	42
2448	2022-07-19 09:53:57.11	5-Angel Rios 	22	HON. DESP. S/ LEY 220/93	x	4238893	3853539	385353	139	16
2449	2022-07-19 09:53:57.121	5-Angel Rios 	23	REPOSICION DE GASTOS ADM	x	1500000	1500000	0	139	21
2450	2022-07-19 09:55:11.468	5-Angel Rios 	1	INDI	22005IC04014423J	14421	14421	0	140	3
2451	2022-07-19 09:55:11.481	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04014423J	3174489	3174489	0	140	5
2452	2022-07-19 09:55:11.485	5-Angel Rios 	3	IRE GENERAL 700	22005IC04014423J	2553171	2553171	0	140	38
2453	2022-07-19 09:55:11.49	5-Angel Rios 	4	I.V.A.	22005IC04014423J	63829274	0	63829274	140	8
2454	2022-07-19 09:55:11.501	5-Angel Rios 	5	TASA INTER. ADUAN.	22005IC04014423J	343350	343350	0	140	24
2455	2022-07-19 09:55:11.506	5-Angel Rios 	6	CANON INFORMATICO	4344730	245222	245222	0	140	9
2456	2022-07-19 09:55:11.512	5-Angel Rios 	7	FOTOCOPIAS	001-002-0017981	70000	63636	6363	140	12
2457	2022-07-19 09:55:11.518	5-Angel Rios 	8	TASA PORTUARIA	002-007-0021012	3509705	3190640	319064	140	14
2458	2022-07-19 09:55:11.525	5-Angel Rios 	9	CDAP PAGOS	001-025-0140805	6866	6241	624	140	27
2459	2022-07-19 09:55:11.529	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0088594	87743	79766	7976	140	11
2460	2022-07-19 09:55:11.534	5-Angel Rios 	11	VISACION MRE	1068004	490445	490445	0	140	28
2461	2022-07-19 09:55:11.538	5-Angel Rios 	12	CDAP PAGOS	001-024-0134011	10000	9090	909	140	27
2462	2022-07-19 09:55:11.544	5-Angel Rios 	13	MIPYMES MIC	1853468	620757	564324	56432	140	22
2463	2022-07-19 09:55:11.552	5-Angel Rios 	14	APORTE VUE	1853469	73188	66534	6653	140	23
2464	2022-07-19 09:55:11.561	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002046	10609500	9645000	964500	140	16
2465	2022-07-19 09:55:11.566	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	140	17
2466	2022-07-19 10:25:27.414	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	141	2
2467	2022-07-19 10:25:27.419	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	141	3
2468	2022-07-19 10:25:27.426	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	141	5
2469	2022-07-19 10:25:27.43	5-Angel Rios 	4	IRE GENERAL 700	22017IC04003694U	1637379	1637379	0	141	38
2470	2022-07-19 10:25:27.439	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	141	8
2471	2022-07-19 10:25:27.444	5-Angel Rios 	6	TASA INTER. ADUAN.	22017IC04003694U	342991	342991	0	141	24
2472	2022-07-19 10:25:27.448	5-Angel Rios 	7	CANON INFORMATICO	4322796	245222	245222	0	141	9
2473	2022-07-19 10:25:27.454	5-Angel Rios 	8	TASA INT. ANNP	4322796	342991	342991	0	141	36
2474	2022-07-19 10:25:27.459	5-Angel Rios 	9	FOTOCOPIAS	006-002-0006247	105000	95454	9545	141	12
2475	2022-07-19 10:25:27.464	5-Angel Rios 	10	TASA PORTUARIA	2381636	4948187	4498351	449835	141	14
2476	2022-07-19 10:25:27.474	5-Angel Rios 	11	FOTOCOPIAS 	006-002-0006436	30100	27363	2736	141	27
2477	2022-07-19 10:25:27.478	5-Angel Rios 	12	VISACION MRE	1063941	980890	980890	0	141	28
2478	2022-07-19 10:25:27.484	5-Angel Rios 	13	CDAP PAGOS	001-024-0132357	10000	9090	909	141	27
2479	2022-07-19 10:25:27.489	5-Angel Rios 	14	TASA MIC DINAVISA	4345150	608534	553212	55321	141	37
2480	2022-07-19 10:25:27.494	5-Angel Rios 	15	CDAP PAGOS	001-024-0133852	10000	9090	909	141	27
2481	2022-07-19 10:25:27.498	5-Angel Rios 	16	MOPC	1063963	49045	49045	0	141	34
2482	2022-07-19 10:25:27.504	5-Angel Rios 	17	CDAP PAGOS	001-024-0132358	10000	9090	909	141	27
2483	2022-07-19 10:25:27.508	5-Angel Rios 	18	FLETE INTERNO 	001-001-000818	1045000	950000	95000	141	39
2484	2022-07-19 10:25:27.513	5-Angel Rios 	19	GASTOS BANCARIOS	x	3700000	3700000	0	141	40
2485	2022-07-19 10:25:27.517	5-Angel Rios 	20	REGISTRO DE FIRMA 22-23	001-001-0002048	1000000	1000000	0	141	41
2486	2022-07-19 10:25:27.525	5-Angel Rios 	21	MUESTRAS AEROP. 1 	0000720	2750000	2750000	0	141	42
2487	2022-07-19 10:25:27.53	5-Angel Rios 	22	HON. DESP. S/ LEY 220/93	001-001-0002047	4238893	3853539	385353	141	16
2488	2022-07-19 10:25:27.535	5-Angel Rios 	23	REPOSICION DE GASTOS ADM	0000719	1500000	1500000	0	141	21
2489	2022-07-19 12:18:07.711	5-Angel Rios 	1	DERECHO ADUANERO	22017IC04003694U	56188732	56188732	0	142	2
2490	2022-07-19 12:18:07.722	5-Angel Rios 	2	INDI	22017IC04003694U	14423	14423	0	142	3
2491	2022-07-19 12:18:07.726	5-Angel Rios 	3	SERVICIO DE VALORACION	22017IC04003694U	1755897	1755897	0	142	5
2492	2022-07-19 12:18:07.737	5-Angel Rios 	4	IRE GENERAL 700	22017IC04003694U	1637379	1637379	0	142	38
2493	2022-07-19 12:18:07.744	5-Angel Rios 	5	I.V.A.	22017IC04003694U	40934467	0	40934467	142	8
2494	2022-07-19 12:18:07.751	5-Angel Rios 	6	TASA INTER. ADUAN.	22017IC04003694U	342991	342991	0	142	24
2495	2022-07-19 12:18:07.757	5-Angel Rios 	7	CANON INFORMATICO	4322796	245222	245222	0	142	9
2496	2022-07-19 12:18:07.762	5-Angel Rios 	8	TASA INT. ANNP	4322796	342991	342991	0	142	36
2497	2022-07-19 12:18:07.771	5-Angel Rios 	9	FOTOCOPIAS	006-002-0006247	105000	95454	9545	142	12
2498	2022-07-19 12:18:07.777	5-Angel Rios 	10	TASA PORTUARIA	2381636	4948187	4498351	449835	142	14
2499	2022-07-19 12:18:07.782	5-Angel Rios 	11	FOTOCOPIAS 	006-002-0006436	30100	27363	2736	142	27
2500	2022-07-19 12:18:07.79	5-Angel Rios 	12	VISACION MRE	1063941	980890	980890	0	142	28
2501	2022-07-19 12:18:07.798	5-Angel Rios 	13	CDAP PAGOS	001-024-0132357	10000	9090	909	142	27
2502	2022-07-19 12:18:07.804	5-Angel Rios 	14	TASA MIC DINAVISA	4345150	608534	553212	55321	142	37
2503	2022-07-19 12:18:07.82	5-Angel Rios 	15	CDAP PAGOS	001-024-0133852	10000	9090	909	142	27
2504	2022-07-19 12:18:07.84	5-Angel Rios 	16	MOPC	1063963	49045	49045	0	142	34
2505	2022-07-19 12:18:07.845	5-Angel Rios 	17	CDAP PAGOS	001-024-0132358	10000	9090	909	142	27
2506	2022-07-19 12:18:07.853	5-Angel Rios 	18	FLETE INTERNO 	001-001-000818	1045000	950000	95000	142	39
2507	2022-07-19 12:18:07.86	5-Angel Rios 	19	GASTOS BANCARIOS	x	3700000	3700000	0	142	40
2508	2022-07-19 12:18:07.865	5-Angel Rios 	20	REGISTRO DE FIRMA 22-23	001-001-0002048	1000000	1000000	0	142	41
2509	2022-07-19 12:18:07.875	5-Angel Rios 	21	HON. DESP. S/ LEY 220/93	001-001-0002049	5888893	5353539	535353	142	16
2510	2022-07-19 12:18:07.882	5-Angel Rios 	22	REPOSICION DE GASTOS ADM	0000719	1500000	1500000	0	142	21
2511	2022-07-19 13:32:01.922	5-Angel Rios 	1	I.V.A.	22002IRE4002998K	206104	0	206104	143	8
2512	2022-07-19 13:32:01.928	5-Angel Rios 	2	CANON INFORMATICO	22002IRE4002998K	54045	54045	0	143	9
2513	2022-07-19 13:32:01.933	5-Angel Rios 	3	TASA PORTUARIA	001-034-0047264	180576	164160	16416	143	14
2514	2022-07-19 13:32:01.939	5-Angel Rios 	4	CDAP DINAC	001-021-0050713	20624	18749	1874	143	31
2515	2022-07-19 13:32:01.944	5-Angel Rios 	5	RETIRO DE GUÍA DHL	017-010-0014498	440000	400000	40000	143	43
2516	2022-07-19 13:32:01.949	5-Angel Rios 	6	HON. DESP. S/ LEY 220/93	001-001-0002050	250000	227272	22727	143	16
2517	2022-07-19 13:32:01.957	5-Angel Rios 	7	REPOSICION DE GASTOS ADM	0000724	600000	600000	0	143	21
2518	2022-07-19 13:32:01.964	5-Angel Rios 	8	MUESTRAS AEROP. 1 	0000720	2750000	2750000	0	143	42
2519	2022-07-19 15:51:41.363	5-Angel Rios 	1	DERECHO ADUANERO	22009IC04000909R	0	0	0	144	2
2520	2022-07-19 15:51:41.371	5-Angel Rios 	2	INDI	22009IC04000909R	26460	26460	0	144	3
2521	2022-07-19 15:51:41.376	5-Angel Rios 	3	SERVICIO DE VALORACION	22009IC04000909R	367684	367684	0	144	5
2522	2022-07-19 15:51:41.381	5-Angel Rios 	4	IRACIS GENERAL 700	22009IC04000909R	297236	297236	0	144	7
2523	2022-07-19 15:51:41.388	5-Angel Rios 	5	I.V.A.	22009IC04000909R	7430894	0	7430894	144	8
2524	2022-07-19 15:51:41.393	5-Angel Rios 	6	CANON INFORMATICO	4311585	245222	245222	0	144	9
2525	2022-07-19 15:51:41.4	5-Angel Rios 	7	FOTOCOPIAS	016-003-000493	80500	73181	7318	144	12
2526	2022-07-19 15:51:41.407	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0004545	350000	318181	31818	144	13
2527	2022-07-19 15:51:41.414	5-Angel Rios 	9	TASA PORTUARIA	009-005-0001899	436572	396883	39688	144	14
2528	2022-07-19 15:51:41.421	5-Angel Rios 	10	CDAP/ANNP	001-025-0136464	6868	6243	624	144	10
2529	2022-07-19 15:51:41.425	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0084227	10914	9921	992	144	11
2530	2022-07-19 15:51:41.431	5-Angel Rios 	12	VISACION CONSUL	2759217/18/19/20	688000	688000	0	144	15
2531	2022-07-19 15:51:41.435	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002051	2989800	2718000	271800	144	16
2532	2022-07-19 15:51:41.44	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	144	17
2533	2022-07-28 12:46:32.575	5-Angel Rios 	1	INDI	22005IC04014759V	26460	26460	0	145	3
2534	2022-07-28 12:46:32.583	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04014759V	2576691	2576691	0	145	5
2535	2022-07-28 12:46:32.583	5-Angel Rios 	3	IRE GENERAL 700	22005IC04014759V	2073277	2073277	0	145	38
2536	2022-07-28 12:46:32.595	5-Angel Rios 	4	I.V.A.	22005IC04014759V	51831930	0	51831930	145	8
2537	2022-07-28 12:46:32.595	5-Angel Rios 	5	CANON INFORMATICO	4397407	245222	245222	0	145	9
2538	2022-07-28 12:46:32.611	5-Angel Rios 	6	APERTURA AG. TRANSP.	003-001-0001383	480000	436363	43636	145	13
2539	2022-07-28 12:46:32.611	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018056	80000	72727	7272	145	12
2540	2022-07-28 12:46:32.626	5-Angel Rios 	8	TASA PORTUARIA	002-007-0023962	2887398	2624907	262490	145	14
2541	2022-07-28 12:46:32.626	5-Angel Rios 	9	CDAP PAGOS	001-025-0146445	6887	6260	626	145	27
2542	2022-07-28 12:46:32.626	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0094239	72185	65622	6562	145	11
2543	2022-07-28 12:46:32.642	5-Angel Rios 	11	VISACION CONSUL	02967590/91/92/93/94	1174500	1174500	0	145	15
2544	2022-07-28 12:46:32.642	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	x	4780014	4345467	434546	145	16
2545	2022-07-28 12:46:32.658	5-Angel Rios 	13	GASTO ADMIN.	x	1560000	1560000	0	145	17
2546	2022-07-28 12:46:32.658	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	145	17
2547	2022-07-29 11:26:32.448	5-Angel Rios 	1	DERECHO ADUANERO	22005IC0401568P	489792	489792	0	146	2
2548	2022-07-29 11:26:32.453	5-Angel Rios 	2	INDI	22005IC0401568P	26460	26460	0	146	3
2549	2022-07-29 11:26:32.453	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC0401568P	4521027	4521027	0	146	5
2550	2022-07-29 11:26:32.469	5-Angel Rios 	4	IRE GENERAL 700	22005IC0401568P	3638480	3638480	0	146	38
2551	2022-07-29 11:26:32.469	5-Angel Rios 	5	I.V.A.	22005IC0401568P	90962058	0	90962058	146	8
2552	2022-07-29 11:26:32.469	5-Angel Rios 	6	CANON INFORMATICO	x	245222	245222	0	146	9
2553	2022-07-29 11:26:32.484	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	146	13
2554	2022-07-29 11:26:32.484	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018051	140000	127272	12727	146	12
2555	2022-07-29 11:26:32.5	5-Angel Rios 	9	TASA PORTUARIA	002-007-0023510	4063775	3694340	369434	146	14
2556	2022-07-29 11:26:32.5	5-Angel Rios 	10	CDAP PAGOS	001-025-0145661	6897	6270	627	146	27
2557	2022-07-29 11:26:32.5	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0093458	101594	92358	9235	146	11
2558	2022-07-29 11:26:32.5	5-Angel Rios 	12	VISACION CONSUL	02971237/38/39/40/41	1012500	1012500	0	146	15
2559	2022-07-29 11:26:32.515	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	5858296	5325723	532572	146	16
2560	2022-07-29 11:26:32.515	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	146	17
2561	2022-07-29 11:26:32.515	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	146	17
2562	2022-07-29 12:45:12.165	5-Angel Rios 	1	010 - DERECHO ADUANERO	22005IC04014982T	0	0	0	147	2
2563	2022-07-29 12:45:12.17	5-Angel Rios 	2	011 - INDI	22005IC04014982T	26460	26460	0	147	3
2564	2022-07-29 12:45:12.17	5-Angel Rios 	3	031 - SERVICIO DE VALORACION	22005IC04014982T	2175699	2175699	0	147	5
2565	2022-07-29 12:45:12.185	5-Angel Rios 	4	133 - IRE GENERAL 700	22005IC04014982T	1750880	1750880	0	147	38
2566	2022-07-29 12:45:12.185	5-Angel Rios 	5	415 - I.V.A.	22005IC04014982T	43772002	0	43772002	147	8
2567	2022-07-29 12:45:12.201	5-Angel Rios 	6	CANON INFORMATICO	4410634	245222	245222	0	147	9
2568	2022-07-29 12:45:12.201	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	147	13
2569	2022-07-29 12:45:12.217	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018043	90000	81818	8181	147	12
2570	2022-07-29 12:45:12.232	5-Angel Rios 	9	TASA PORTUARIA	002-007-0023161	2151626	1956023	195602	147	14
2571	2022-07-29 12:45:12.232	5-Angel Rios 	10	CDAP PAGOS	001-025-0145012	6891	6264	626	147	27
2572	2022-07-29 12:45:12.232	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0092808	53791	48900	4890	147	11
2573	2022-07-29 12:45:12.248	5-Angel Rios 	12	VISACION CONSUL	02971052/53/54	607500	607500	0	147	15
2574	2022-07-29 12:45:12.248	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002054	4762568	4329607	432960	147	16
2575	2022-07-29 12:45:12.248	5-Angel Rios 	14	VISACION MRE	1075073	490445	490445	0	147	28
2576	2022-07-29 12:45:12.263	5-Angel Rios 	15	CDAP PAGOS	001-024-0138122	10000	9090	909	147	27
2577	2022-07-29 12:55:20.703	5-Angel Rios 	1	INDI	22005IC04014759V	26460	26460	0	148	3
2578	2022-07-29 12:55:20.706	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04014759V	2576691	2576691	0	148	5
2579	2022-07-29 12:55:20.706	5-Angel Rios 	3	IRE GENERAL 700	22005IC04014759V	2073277	2073277	0	148	38
2580	2022-07-29 12:55:20.706	5-Angel Rios 	4	I.V.A.	22005IC04014759V	51831930	0	51831930	148	8
2581	2022-07-29 12:55:20.721	5-Angel Rios 	5	CANON INFORMATICO	4397407	245222	245222	0	148	9
2582	2022-07-29 12:55:20.721	5-Angel Rios 	6	APERTURA AG. TRANSP.	003-001-0001383	480000	436363	43636	148	13
2583	2022-07-29 12:55:20.721	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018056	80000	72727	7272	148	12
2584	2022-07-29 12:55:20.721	5-Angel Rios 	8	TASA PORTUARIA	002-007-0023962	2887398	2624907	262490	148	14
2585	2022-07-29 12:55:20.737	5-Angel Rios 	9	CDAP PAGOS	001-025-0146445	6887	6260	626	148	27
2586	2022-07-29 12:55:20.737	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0094239	72185	65622	6562	148	11
2587	2022-07-29 12:55:20.737	5-Angel Rios 	11	VISACION CONSUL	02967590/91/92/93/94	1174500	1174500	0	148	15
2588	2022-07-29 12:55:20.753	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002055	4780014	4345467	434546	148	16
2589	2022-07-29 12:55:20.753	5-Angel Rios 	13	GASTO ADMIN.	735	1560000	1560000	0	148	17
2590	2022-07-29 12:55:20.753	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	148	17
2591	2022-07-29 13:16:00.929	5-Angel Rios 	1	DERECHO ADUANERO	22005IC0401568P	489792	489792	0	149	2
2592	2022-07-29 13:16:00.935	5-Angel Rios 	2	INDI	22005IC0401568P	26460	26460	0	149	3
2593	2022-07-29 13:16:00.937	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC0401568P	4521027	4521027	0	149	5
2594	2022-07-29 13:16:00.949	5-Angel Rios 	4	IRE GENERAL 700	22005IC0401568P	3638480	3638480	0	149	38
2595	2022-07-29 13:16:00.953	5-Angel Rios 	5	I.V.A.	22005IC0401568P	90962058	0	90962058	149	8
2596	2022-07-29 13:16:00.958	5-Angel Rios 	6	CANON INFORMATICO	4414710	245222	245222	0	149	9
2597	2022-07-29 13:16:00.963	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	149	13
2598	2022-07-29 13:16:00.965	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018051	140000	127272	12727	149	12
2599	2022-07-29 13:16:00.975	5-Angel Rios 	9	TASA PORTUARIA	002-007-0023510	4063775	3694340	369434	149	14
2600	2022-07-29 13:16:00.977	5-Angel Rios 	10	CDAP PAGOS	001-025-0145661	6897	6270	627	149	27
2601	2022-07-29 13:16:00.989	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0093458	101594	92358	9235	149	11
2602	2022-07-29 13:16:00.994	5-Angel Rios 	12	VISACION CONSUL	02971237/38/39/40/41	1012500	1012500	0	149	15
2603	2022-07-29 13:16:00.998	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002056	5858296	5325723	532572	149	16
2604	2022-07-29 13:16:01.005	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	149	17
2605	2022-07-29 13:16:01.01	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	149	17
2606	2022-07-29 15:24:09.779	5-Angel Rios 	1	010 - DERECHO ADUANERO	22002IC04015355L	47193	47193	0	150	2
2607	2022-07-29 15:24:09.784	5-Angel Rios 	2	011 - INDI	22002IC04015355L	0	0	0	150	3
2608	2022-07-29 15:24:09.793	5-Angel Rios 	3	031 - SERVICIO DE VALORACION	22002IC04015355L	885993	885993	0	150	5
2609	2022-07-29 15:24:09.802	5-Angel Rios 	4	133 - IRE GENERAL 700	22002IC04015355L	714182	714182	0	150	38
2610	2022-07-29 15:24:09.808	5-Angel Rios 	5	415 - I.V.A.	22002IC04015355L	17854534	0	17854534	150	8
2611	2022-07-29 15:24:09.813	5-Angel Rios 	6	CANON INFORMATICO	4409022	250222	250222	0	150	9
2612	2022-07-29 15:24:09.817	5-Angel Rios 	7	FLETE INTERNO 	001-001-0000825	330000	300000	30000	150	39
2613	2022-07-29 15:24:09.823	5-Angel Rios 	8	FOTOCOPIAS	004-003-0030008	63000	57272	5727	150	12
2614	2022-07-29 15:24:09.828	5-Angel Rios 	9	TASA PORTUARIA	001-034-0049482	2541530	2310481	231048	150	14
2615	2022-07-29 15:24:09.832	5-Angel Rios 	10	CDAP PAGOS	001-021-0052793	20685	18804	1880	150	27
2616	2022-07-29 15:24:09.838	5-Angel Rios 	11	RETIRO DE GUÍA DHL	017-010-0015334	450000	409090	40909	150	43
2617	2022-07-29 15:24:09.843	5-Angel Rios 	12	VISACION MRE	x	0	0	0	150	28
2618	2022-07-29 15:24:09.86	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002057	3995017	3631833	363183	150	16
2619	2022-07-29 15:24:09.866	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	150	17
2620	2022-07-29 16:11:06.635	5-Angel Rios 	1	010 - DERECHO ADUANERO	22002IC04015355L	47193	47193	0	151	2
2621	2022-07-29 16:11:06.641	5-Angel Rios 	2	011 - INDI	22002IC04015355L	0	0	0	151	3
2622	2022-07-29 16:11:06.646	5-Angel Rios 	3	031 - SERVICIO DE VALORACION	22002IC04015355L	885993	885993	0	151	5
2623	2022-07-29 16:11:06.652	5-Angel Rios 	4	133 - IRE GENERAL 700	22002IC04015355L	714182	714182	0	151	38
2624	2022-07-29 16:11:06.658	5-Angel Rios 	5	415 - I.V.A.	22002IC04015355L	17854534	0	17854534	151	8
2625	2022-07-29 16:11:06.666	5-Angel Rios 	6	CANON INFORMATICO	4409022	250222	250222	0	151	9
2626	2022-07-29 16:11:06.671	5-Angel Rios 	7	FLETE INTERNO 	001-001-0000825	330000	300000	30000	151	39
2627	2022-07-29 16:11:06.676	5-Angel Rios 	8	FOTOCOPIAS	004-003-0030008	63000	57272	5727	151	12
2628	2022-07-29 16:11:06.68	5-Angel Rios 	9	TASA PORTUARIA	001-034-0049482	2541530	2310481	231048	151	14
2629	2022-07-29 16:11:06.685	5-Angel Rios 	10	CDAP PAGOS	001-021-0052793	20685	18804	1880	151	27
2630	2022-07-29 16:11:06.69	5-Angel Rios 	11	RETIRO DE GUÍA DHL	017-010-0015334	450000	409090	40909	151	43
2631	2022-07-29 16:11:06.694	5-Angel Rios 	12	VISACION MRE	x	0	0	0	151	28
2632	2022-07-29 16:11:06.7	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002057	3698017	3361833	336183	151	16
2633	2022-07-29 16:11:06.705	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	151	17
2634	2022-07-29 16:12:00.78	5-Angel Rios 	1	INDI	22005IC04014759V	26460	26460	0	152	3
2635	2022-07-29 16:12:00.788	5-Angel Rios 	2	SERVICIO DE VALORACION	22005IC04014759V	2576691	2576691	0	152	5
2636	2022-07-29 16:12:00.795	5-Angel Rios 	3	IRE GENERAL 700	22005IC04014759V	2073277	2073277	0	152	38
2637	2022-07-29 16:12:00.805	5-Angel Rios 	4	I.V.A.	22005IC04014759V	51831930	0	51831930	152	8
2638	2022-07-29 16:12:00.811	5-Angel Rios 	5	CANON INFORMATICO	4397407	245222	245222	0	152	9
2639	2022-07-29 16:12:00.818	5-Angel Rios 	6	APERTURA AG. TRANSP.	003-001-0001383	480000	436363	43636	152	13
2640	2022-07-29 16:12:00.824	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018056	80000	72727	7272	152	12
2641	2022-07-29 16:12:00.834	5-Angel Rios 	8	TASA PORTUARIA	002-007-0023962	2887398	2624907	262490	152	14
2642	2022-07-29 16:12:00.84	5-Angel Rios 	9	CDAP PAGOS	001-025-0146445	6887	6260	626	152	27
2643	2022-07-29 16:12:00.847	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0094239	72185	65622	6562	152	11
2644	2022-07-29 16:12:00.854	5-Angel Rios 	11	VISACION CONSUL	02967590/91/92/93/94	1174500	1174500	0	152	15
2645	2022-07-29 16:12:00.86	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002055	4780014	4345467	434546	152	16
2646	2022-07-29 16:12:00.868	5-Angel Rios 	13	GASTO ADMIN.	735	1560000	1560000	0	152	17
2647	2022-07-29 16:12:00.875	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	152	17
2648	2022-07-29 16:17:03.551	5-Angel Rios 	1	DERECHO ADUANERO	22005IC0401568P	489792	489792	0	153	2
2649	2022-07-29 16:17:03.573	5-Angel Rios 	2	INDI	22005IC0401568P	26460	26460	0	153	3
2650	2022-07-29 16:17:03.587	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC0401568P	4521027	4521027	0	153	5
2651	2022-07-29 16:17:03.607	5-Angel Rios 	4	IRE GENERAL 700	22005IC0401568P	3638480	3638480	0	153	38
2652	2022-07-29 16:17:03.625	5-Angel Rios 	5	I.V.A.	22005IC0401568P	90962058	0	90962058	153	8
2653	2022-07-29 16:17:03.642	5-Angel Rios 	6	CANON INFORMATICO	4414710	245222	245222	0	153	9
2654	2022-07-29 16:17:03.665	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	153	13
2655	2022-07-29 16:17:03.676	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018051	140000	127272	12727	153	12
2656	2022-07-29 16:17:03.698	5-Angel Rios 	9	TASA PORTUARIA	002-007-0023510	4063775	3694340	369434	153	14
2657	2022-07-29 16:17:03.71	5-Angel Rios 	10	CDAP PAGOS	001-025-0145661	6897	6270	627	153	27
2658	2022-07-29 16:17:03.722	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0093458	101594	92358	9235	153	11
2659	2022-07-29 16:17:03.737	5-Angel Rios 	12	VISACION CONSUL	02971237/38/39/40/41	1012500	1012500	0	153	15
2660	2022-07-29 16:17:03.743	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002056	5858296	5325723	532572	153	16
2661	2022-07-29 16:17:03.752	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	153	17
2662	2022-07-29 16:17:03.76	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	153	17
2663	2022-08-01 15:49:59.263	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04015268R	0	0	0	154	2
2664	2022-08-01 15:49:59.268	5-Angel Rios 	2	INDI	22005IC04015268R	26460	26460	0	154	3
2665	2022-08-01 15:49:59.284	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04015268R	1712691	1712691	0	154	5
2666	2022-08-01 15:49:59.294	5-Angel Rios 	4	IRE GENERAL 700	22005IC04015268R	1378621	1378621	0	154	38
2667	2022-08-01 15:49:59.294	5-Angel Rios 	5	I.V.A.	22005IC04015268R	34465534	0	34465534	154	8
2668	2022-08-01 15:49:59.309	5-Angel Rios 	6	CANON INFORMATICO	4422708	249222	249222	0	154	9
2669	2022-08-01 15:49:59.34	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	154	13
2670	2022-08-01 15:49:59.387	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018065	60000	54545	5454	154	12
2671	2022-08-01 15:49:59.394	5-Angel Rios 	9	TASA PORTUARIA	002-007-0024193	1846522	1678656	167865	154	14
2672	2022-08-01 15:49:59.409	5-Angel Rios 	10	CDAP PAGOS	001-025-0146799	6908	6280	628	154	27
2673	2022-08-01 15:49:59.409	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0094589	46163	41966	4196	154	11
2674	2022-08-01 15:49:59.425	5-Angel Rios 	12	VISACION MRE	1076450	490445	490445	0	154	28
2675	2022-08-01 15:49:59.425	5-Angel Rios 	13	CDAP PAGOS	001-024-0139384	10000	9090	909	154	27
2676	2022-08-01 15:49:59.441	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002059	4282820	3893472	389347	154	16
2677	2022-08-01 15:49:59.441	5-Angel Rios 	15	VISACION CONSUL	02971771/72/73	607500	607500	0	154	15
2678	2022-08-01 16:35:47.653	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04015268R	0	0	0	155	2
2679	2022-08-01 16:35:47.658	5-Angel Rios 	2	INDI	22005IC04015268R	26460	26460	0	155	3
2680	2022-08-01 16:35:47.673	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04015268R	1712691	1712691	0	155	5
2681	2022-08-01 16:35:47.689	5-Angel Rios 	4	IRE GENERAL 700	22005IC04015268R	1378621	1378621	0	155	38
2682	2022-08-01 16:35:47.689	5-Angel Rios 	5	I.V.A.	22005IC04015268R	34465534	0	34465534	155	8
2683	2022-08-01 16:35:47.705	5-Angel Rios 	6	CANON INFORMATICO	4422708	249222	249222	0	155	9
2684	2022-08-01 16:35:47.705	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	155	13
2685	2022-08-01 16:35:47.728	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018065	60000	54545	5454	155	12
2686	2022-08-01 16:35:47.728	5-Angel Rios 	9	TASA PORTUARIA	002-007-0024193	1846522	1678656	167865	155	14
2687	2022-08-01 16:35:47.744	5-Angel Rios 	10	CDAP PAGOS	001-025-0146799	6908	6280	628	155	27
2688	2022-08-01 16:35:47.759	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0094589	46163	41966	4196	155	11
2689	2022-08-01 16:35:47.775	5-Angel Rios 	12	VISACION MRE	1076450	490445	490445	0	155	28
2690	2022-08-01 16:35:47.791	5-Angel Rios 	13	CDAP PAGOS	001-024-0139384	10000	9090	909	155	27
2691	2022-08-01 16:35:47.791	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002059	4282820	3893472	389347	155	16
2692	2022-08-01 16:35:47.806	5-Angel Rios 	15	VISACION CONSUL	02971771/72/73	607500	607500	0	155	15
2693	2022-08-02 13:47:33.012	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01002272F	0	0	0	156	2
2694	2022-08-02 13:47:33.021	5-Angel Rios 	2	INDI	22018EC01002272F	0	0	0	156	3
2695	2022-08-02 13:47:33.028	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01002272F	0	0	0	156	5
2696	2022-08-02 13:47:33.034	5-Angel Rios 	4	IRE GENERAL 700	22018EC01002272F	0	0	0	156	38
2697	2022-08-02 13:47:33.041	5-Angel Rios 	5	I.V.A.	22018EC01002272F	0	0	0	156	8
2698	2022-08-02 13:47:33.049	5-Angel Rios 	6	CANON INFORMATICO	4424755	249222	249222	0	156	9
2699	2022-08-02 13:47:33.056	5-Angel Rios 	7	FOTOCOPIAS	001-001-0009171	60000	54545	5454	156	12
2700	2022-08-02 13:47:33.061	5-Angel Rios 	8	TASA PORTUARIA	x	0	0	0	156	14
2701	2022-08-02 13:47:33.066	5-Angel Rios 	9	TASA MIC CO	1894297	102489	102089	400	156	29
2702	2022-08-02 13:47:33.072	5-Angel Rios 	10	TASA UIP CO	002-002-0008866	98089	98089	0	156	30
2703	2022-08-02 13:47:33.078	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002060	3108158	2825598	282559	156	16
2704	2022-08-02 13:47:33.084	5-Angel Rios 	12	GASTO ADMIN.	x	0	0	0	156	17
2705	2022-08-03 09:17:53.264	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04015515M	0	0	0	157	2
2706	2022-08-03 09:17:53.27	5-Angel Rios 	2	INDI	22005IC04015515M	26460	26460	0	157	3
2707	2022-08-03 09:17:53.277	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04015515M	3918566	3918566	0	157	5
2708	2022-08-03 09:17:53.283	5-Angel Rios 	4	IRE GENERAL 700	22005IC04015515M	3152145	3152145	0	157	38
2709	2022-08-03 09:17:53.29	5-Angel Rios 	5	I.V.A.	22005IC04015515M	78803622	0	78803622	157	8
2710	2022-08-03 09:17:53.296	5-Angel Rios 	6	CANON INFORMATICO	4443722	245222	245222	0	157	9
2711	2022-08-03 09:17:53.303	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018085	80000	72727	7272	157	12
2712	2022-08-03 09:17:53.308	5-Angel Rios 	8	TASA PORTUARIA	002-007-0025110	3554422	3231292	323129	157	14
2713	2022-08-03 09:17:53.313	5-Angel Rios 	9	CDAP PAGOS	001-025-0148662	6873	6248	624	157	27
2714	2022-08-03 09:17:53.317	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0096461	88861	80782	8078	157	11
2715	2022-08-03 09:17:53.322	5-Angel Rios 	11	VISACION CONSUL	02974528/29/30/31	810000	810000	0	157	15
2716	2022-08-03 09:17:53.328	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002061	5520316	5018469	501846	157	16
2717	2022-08-05 14:59:21.392	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04009498A	69848859	69848859	0	158	2
2718	2022-08-05 14:59:21.403	5-Angel Rios 	2	INDI	22030IC04009498A	43400	43400	0	158	3
2719	2022-08-05 14:59:21.411	5-Angel Rios 	3	I.S.C.	22030IC04009498A	857852	857852	0	158	4
2720	2022-08-05 14:59:21.421	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04009498A	2205019	2205019	0	158	5
2721	2022-08-05 14:59:21.429	5-Angel Rios 	5	IRE GENERAL 700	22030IC04009498A	2058315	2058315	0	158	38
2722	2022-08-05 14:59:21.438	5-Angel Rios 	6	I.V.A.	22030IC04009498A	51457897	0	51457897	158	8
2723	2022-08-05 14:59:21.445	5-Angel Rios 	7	TASA INT. ADUANERA 	22030IC04009498A	343382	343382	0	158	35
2724	2022-08-05 14:59:21.453	5-Angel Rios 	8	CANON INFORMATICO	4464910	245222	245222	0	158	9
2725	2022-08-05 14:59:21.458	5-Angel Rios 	9	FOTOCOPIAS	x	120000	109090	10909	158	12
2726	2022-08-05 14:59:21.467	5-Angel Rios 	10	TASA INT. ANNP	x	0	0	0	158	36
2727	2022-08-05 14:59:21.473	5-Angel Rios 	11	TASA PORTUARIA	2421896	892778	811616	81161	158	14
2728	2022-08-05 14:59:21.482	5-Angel Rios 	12	VISACION MRE	x	0	0	0	158	28
2729	2022-08-05 14:59:21.493	5-Angel Rios 	13	TASA MIC LPI CONFECCIONES	4460679	696623	696623	0	158	18
2730	2022-08-05 14:59:21.504	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	4535313	4123011	412301	158	16
2731	2022-08-05 14:59:21.511	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	660000	660000	0	158	21
2732	2022-08-05 15:02:49.104	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04009498A	69848859	69848859	0	159	2
2733	2022-08-05 15:02:49.11	5-Angel Rios 	2	INDI	22030IC04009498A	43400	43400	0	159	3
2734	2022-08-05 15:02:49.116	5-Angel Rios 	3	I.S.C.	22030IC04009498A	857852	857852	0	159	4
2735	2022-08-05 15:02:49.122	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04009498A	2205019	2205019	0	159	5
2736	2022-08-05 15:02:49.127	5-Angel Rios 	5	IRE GENERAL 700	22030IC04009498A	2058315	2058315	0	159	38
2737	2022-08-05 15:02:49.131	5-Angel Rios 	6	I.V.A.	22030IC04009498A	51457897	0	51457897	159	8
2738	2022-08-05 15:02:49.137	5-Angel Rios 	7	TASA INT. ADUANERA 	22030IC04009498A	343382	343382	0	159	35
2739	2022-08-05 15:02:49.139	5-Angel Rios 	8	CANON INFORMATICO	4464910	245222	245222	0	159	9
2740	2022-08-05 15:02:49.15	5-Angel Rios 	9	FOTOCOPIAS	x	120000	109090	10909	159	12
2741	2022-08-05 15:02:49.152	5-Angel Rios 	10	TASA INT. ANNP	x	0	0	0	159	36
2742	2022-08-05 15:02:49.16	5-Angel Rios 	11	TASA PORTUARIA	2421896	892778	811616	81161	159	14
2743	2022-08-05 15:02:49.165	5-Angel Rios 	12	VISACION MRE	x	0	0	0	159	28
2744	2022-08-05 15:02:49.178	5-Angel Rios 	13	TASA MIC LPI CONFECCIONES	4460679	696623	696623	0	159	18
2745	2022-08-05 15:02:49.182	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	4535313	4123011	412301	159	16
2746	2022-08-05 15:02:49.192	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	660000	660000	0	159	21
2747	2022-08-06 14:51:21.604	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04009498A	69848859	69848859	0	160	2
2748	2022-08-06 14:51:21.609	5-Angel Rios 	2	INDI	22030IC04009498A	43400	43400	0	160	3
2749	2022-08-06 14:51:21.609	5-Angel Rios 	3	I.S.C.	22030IC04009498A	857852	857852	0	160	4
2750	2022-08-06 14:51:21.627	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04009498A	2205019	2205019	0	160	5
2751	2022-08-06 14:51:21.643	5-Angel Rios 	5	IRE GENERAL 700	22030IC04009498A	2058315	2058315	0	160	38
2752	2022-08-06 14:51:21.643	5-Angel Rios 	6	I.V.A.	22030IC04009498A	51457897	0	51457897	160	8
2753	2022-08-06 14:51:21.658	5-Angel Rios 	7	TASA INT. ADUANERA 	22030IC04009498A	343382	343382	0	160	35
2754	2022-08-06 14:51:21.658	5-Angel Rios 	8	CANON INFORMATICO	4464910	245222	245222	0	160	9
2755	2022-08-06 14:51:21.658	5-Angel Rios 	9	FOTOCOPIAS	001-002-0007200	120000	109090	10909	160	12
2756	2022-08-06 14:51:21.674	5-Angel Rios 	10	TASA INT. ANNP	x	0	0	0	160	36
2757	2022-08-06 14:51:21.674	5-Angel Rios 	11	TASA PORTUARIA	2421896	892778	811616	81161	160	14
2758	2022-08-06 14:51:21.689	5-Angel Rios 	12	VISACION MRE	x	0	0	0	160	28
2759	2022-08-06 14:51:21.689	5-Angel Rios 	13	TASA MIC LPI CONFECCIONES	4460679	696623	696623	0	160	18
2760	2022-08-06 14:51:21.705	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002064	4535313	4123011	412301	160	16
2761	2022-08-06 14:51:21.705	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	001-001-0002065	660000	600000	60000	160	21
2762	2022-08-09 09:09:44.179	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04016233H	19013790	19013790	0	161	2
2763	2022-08-09 09:09:44.187	5-Angel Rios 	2	INDI	22002IC04016233H	34332	34332	0	161	3
2764	2022-08-09 09:09:44.187	5-Angel Rios 	3	I.S.C.	22002IC04016233H	46222	46222	0	161	4
2765	2022-08-09 09:09:44.187	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04016233H	850144	850144	0	161	5
2766	2022-08-09 09:09:44.202	5-Angel Rios 	5	IRE GENERAL 700	22002IC04016233H	761854	761854	0	161	38
2767	2022-08-09 09:09:44.202	5-Angel Rios 	6	I.V.A.	22002IC04016233H	19046371	0	19046371	161	8
2768	2022-08-09 09:09:44.218	5-Angel Rios 	7	CANON INFORMATICO	4459968	245222	245222	0	161	9
2769	2022-08-09 09:09:44.218	5-Angel Rios 	8	FOTOCOPIAS	004-003-0000413	60000	54545	5454	161	12
2770	2022-08-09 09:09:44.218	5-Angel Rios 	9	TASA PORTUARIA	001-034-0051398	4683382	4257620	425762	161	14
2771	2022-08-09 09:09:44.233	5-Angel Rios 	10	CDAP DINAC	001-021-0054549	20608	18734	1873	161	31
2772	2022-08-09 09:09:44.233	5-Angel Rios 	11	VISACION MRE	1081608	490445	490445	0	161	28
2773	2022-08-09 09:09:44.233	5-Angel Rios 	12	CDAP PAGOS	001-024-0142942	10000	9090	909	161	27
2774	2022-08-09 09:09:44.249	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002066	4687498	4261361	426136	161	16
2775	2022-08-10 14:12:41.732	5-Angel Rios 	1	DERECHO ADUANERO	22002IC0416590N	0	0	0	162	2
2776	2022-08-10 14:12:41.737	5-Angel Rios 	2	INDI	22002IC0416590N	0	0	0	162	3
2777	2022-08-10 14:12:41.737	5-Angel Rios 	3	I.S.C.	22002IC0416590N	0	0	0	162	4
2778	2022-08-10 14:12:41.753	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC0416590N	208231	208231	0	162	5
2779	2022-08-10 14:12:41.753	5-Angel Rios 	5	IRE GENERAL 700	22002IC0416590N	168242	168242	0	162	38
2780	2022-08-10 14:12:41.769	5-Angel Rios 	6	I.V.A.	22002IC0416590N	4206045	0	4206045	162	8
2781	2022-08-10 14:12:41.769	5-Angel Rios 	7	CANON INFORMATICO	4548983	250222	250222	0	162	9
2782	2022-08-10 14:12:41.784	5-Angel Rios 	8	FOTOCOPIAS	004-003-0000750	48000	43636	4363	162	12
2783	2022-08-10 14:12:41.784	5-Angel Rios 	9	TASA PORTUARIA	001-034-0052023/24	610720	555200	55520	162	14
2784	2022-08-10 14:12:41.784	5-Angel Rios 	10	CDAP DINAC	001-021-0055136	20627	18751	1875	162	31
2785	2022-08-10 14:12:41.8	5-Angel Rios 	11	VISACION MRE	x	0	0	0	162	28
2786	2022-08-10 14:12:41.8	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002067	1925000	1750000	175000	162	16
2787	2022-08-10 14:12:41.816	5-Angel Rios 	13	RETIRO DE GUÍA DHL	017-010-0017257	450000	409090	40909	162	43
2788	2022-08-10 14:12:41.816	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	162	17
2789	2022-08-22 14:18:39.432	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04016964V	0	0	0	163	2
2790	2022-08-22 14:18:39.439	5-Angel Rios 	2	INDI	22005IC04016964V	46200	46200	0	163	3
2791	2022-08-22 14:18:39.446	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04016964V	4284501	4284501	0	163	5
2792	2022-08-22 14:18:39.452	5-Angel Rios 	4	IRE GENERAL 700	22005IC04016964V	3447564	3447564	0	163	38
2793	2022-08-22 14:18:39.46	5-Angel Rios 	5	I.V.A.	22005IC04016964V	86189099	0	86189099	163	8
2794	2022-08-22 14:18:39.47	5-Angel Rios 	6	CANON INFORMATICO	4617035	245222	245222	0	163	9
2795	2022-08-22 14:18:39.475	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018191	80000	72727	7272	163	12
2796	2022-08-22 14:18:39.481	5-Angel Rios 	8	APERTURA AG. TRANSP.	003-001-0001477	480000	436363	43636	163	13
2797	2022-08-22 14:18:39.489	5-Angel Rios 	9	TASA PORTUARIA	002-007-0030328	3903637	3548760	354876	163	14
2798	2022-08-22 14:18:39.494	5-Angel Rios 	10	CDAP PAGOS	001-025-0159161	6883	6257	625	163	27
2799	2022-08-22 14:18:39.503	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0106957	97591	88719	8871	163	11
2800	2022-08-22 14:18:39.508	5-Angel Rios 	12	VISACION MRE	02985914/15/16/17/18/19/20	1480000	1480000	0	163	28
2801	2022-08-22 14:18:39.514	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	6611771	6010700	601070	163	16
2802	2022-08-22 14:18:39.519	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	163	21
2803	2022-08-22 14:18:39.528	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	163	17
2804	2022-08-22 14:31:33.337	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017008L	132432	132432	0	164	2
2805	2022-08-22 14:31:33.342	5-Angel Rios 	2	INDI	22005IC04017008L	26600	26600	0	164	3
2806	2022-08-22 14:31:33.349	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017008L	119549	119549	0	164	5
2807	2022-08-22 14:31:33.353	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017008L	98274	98274	0	164	38
2808	2022-08-22 14:31:33.361	5-Angel Rios 	5	I.V.A.	22005IC04017008L	2456841	0	2456841	164	8
2809	2022-08-22 14:31:33.365	5-Angel Rios 	6	CANON INFORMATICO	4620687	98088	98088	0	164	9
2810	2022-08-22 14:31:33.371	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018190	140000	127272	12727	164	12
2811	2022-08-22 14:31:33.376	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	164	13
2812	2022-08-22 14:31:33.384	5-Angel Rios 	9	TASA PORTUARIA	002-007-0030443	134584	122349	12234	164	14
2813	2022-08-22 14:31:33.393	5-Angel Rios 	10	CDAP PAGOS	001-025-0159439	6883	6257	625	164	27
2814	2022-08-22 14:31:33.403	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0107252	3365	3059	305	164	11
2815	2022-08-22 14:31:33.412	5-Angel Rios 	12	VISACION MRE	02991820/21/22	440000	440000	0	164	28
2816	2022-08-22 14:31:33.418	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002068	1529000	1390000	139000	164	16
2817	2022-08-22 14:31:33.431	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	164	21
2818	2022-08-22 14:43:18.227	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017002F	455820	455820	0	165	2
2819	2022-08-22 14:43:18.233	5-Angel Rios 	2	INDI	22005IC04017002F	26599	26599	0	165	3
2820	2022-08-22 14:43:18.238	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017002F	2673607	2673607	0	165	5
2821	2022-08-22 14:43:18.245	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017002F	2153029	2153029	0	165	38
3907	2022-10-19 14:01:00.503	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	240	17
2822	2022-08-22 14:43:18.246	5-Angel Rios 	5	I.V.A.	22005IC04017002F	53825755	0	53825755	165	8
2823	2022-08-22 14:43:18.256	5-Angel Rios 	6	CANON INFORMATICO	4620692	245222	245222	0	165	9
2824	2022-08-22 14:43:18.268	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018189	130000	118181	11818	165	12
2825	2022-08-22 14:43:18.277	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	165	13
2826	2022-08-22 14:43:18.281	5-Angel Rios 	9	TASA PORTUARIA	002-007-0030444	2556584	2324167	232416	165	14
2827	2022-08-22 14:43:18.288	5-Angel Rios 	10	CDAP PAGOS	001-025-0159457	6883	6257	625	165	27
2828	2022-08-22 14:43:18.296	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0107253	63915	58104	5810	165	11
2829	2022-08-22 14:43:18.301	5-Angel Rios 	12	VISACION MRE	02991779/80/81/82	798000	798000	0	165	28
2830	2022-08-22 14:43:18.305	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002069	5702400	5184000	518400	165	16
2831	2022-08-22 14:43:18.312	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	165	21
2832	2022-08-22 14:57:54.289	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04017190K	0	0	0	166	2
2833	2022-08-22 14:57:54.296	5-Angel Rios 	2	INDI	22002IC04017190K	0	0	0	166	3
2834	2022-08-22 14:57:54.302	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04017190K	362274	362274	0	166	5
2835	2022-08-22 14:57:54.317	5-Angel Rios 	4	IRE GENERAL 700	22002IC04017190K	292505	292505	0	166	38
2836	2022-08-22 14:57:54.322	5-Angel Rios 	5	I.V.A.	22002IC04017190K	7312636	0	7312636	166	8
2837	2022-08-22 14:57:54.326	5-Angel Rios 	6	CANON INFORMATICO	4609373	250222	250222	0	166	9
2838	2022-08-22 14:57:54.332	5-Angel Rios 	7	FOTOCOPIAS	004-003-0001440	48000	43636	4363	166	12
2839	2022-08-22 14:57:54.337	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	166	13
2840	2022-08-22 14:57:54.346	5-Angel Rios 	9	TASA PORTUARIA	001-034-0053555	1043691	948810	94881	166	14
2841	2022-08-22 14:57:54.35	5-Angel Rios 	10	CDAP PAGOS	001-021-0056555	20658	18780	1878	166	27
2842	2022-08-22 14:57:54.355	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	166	11
2843	2022-08-22 14:57:54.36	5-Angel Rios 	12	VISACION MRE	x	0	0	0	166	28
2844	2022-08-22 14:57:54.365	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	0	0	0	166	16
2845	2022-08-22 14:57:54.369	5-Angel Rios 	15	RETIRO DE GUIA DHL	017-010-0018097	450000	450000	0	166	17
2846	2022-08-22 15:34:32.515	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04016964V	0	0	0	167	2
2847	2022-08-22 15:34:32.524	5-Angel Rios 	2	INDI	22005IC04016964V	46200	46200	0	167	3
2848	2022-08-22 15:34:32.531	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04016964V	4284501	4284501	0	167	5
2849	2022-08-22 15:34:32.537	5-Angel Rios 	4	IRE GENERAL 700	22005IC04016964V	3447564	3447564	0	167	38
2850	2022-08-22 15:34:32.541	5-Angel Rios 	5	I.V.A.	22005IC04016964V	86189099	0	86189099	167	8
2851	2022-08-22 15:34:32.546	5-Angel Rios 	6	CANON INFORMATICO	4617035	245222	245222	0	167	9
2852	2022-08-22 15:34:32.551	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018191	80000	72727	7272	167	12
2853	2022-08-22 15:34:32.557	5-Angel Rios 	8	APERTURA AG. TRANSP.	003-001-0001477	480000	436363	43636	167	13
2854	2022-08-22 15:34:32.568	5-Angel Rios 	9	TASA PORTUARIA	002-007-0030328	3903637	3548760	354876	167	14
2855	2022-08-22 15:34:32.574	5-Angel Rios 	10	CDAP PAGOS	001-025-0159161	6883	6257	625	167	27
2856	2022-08-22 15:34:32.579	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0106957	97591	88719	8871	167	11
2857	2022-08-22 15:34:32.583	5-Angel Rios 	12	VISACION MRE	02985914/15/16/17/18/19/20	1480000	1480000	0	167	28
2858	2022-08-22 15:34:32.59	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002070	6611771	6010700	601070	167	16
2859	2022-08-22 16:00:31.017	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04017190K	0	0	0	168	2
2860	2022-08-22 16:00:31.025	5-Angel Rios 	2	INDI	22002IC04017190K	0	0	0	168	3
2861	2022-08-22 16:00:31.03	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04017190K	362274	362274	0	168	5
2862	2022-08-22 16:00:31.036	5-Angel Rios 	4	IRE GENERAL 700	22002IC04017190K	292505	292505	0	168	38
2863	2022-08-22 16:00:31.041	5-Angel Rios 	5	I.V.A.	22002IC04017190K	7312636	0	7312636	168	8
2864	2022-08-22 16:00:31.045	5-Angel Rios 	6	CANON INFORMATICO	4609373	250222	250222	0	168	9
2865	2022-08-22 16:00:31.051	5-Angel Rios 	7	FOTOCOPIAS	004-003-0001440	48000	43636	4363	168	12
2866	2022-08-22 16:00:31.057	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	168	13
2867	2022-08-22 16:00:31.062	5-Angel Rios 	9	TASA PORTUARIA	001-034-0053555	1043691	948810	94881	168	14
2868	2022-08-22 16:00:31.068	5-Angel Rios 	10	CDAP PAGOS	001-021-0056555	20658	18780	1878	168	27
2869	2022-08-22 16:00:31.073	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	168	11
2870	2022-08-22 16:00:31.078	5-Angel Rios 	12	VISACION MRE	x	0	0	0	168	28
2871	2022-08-22 16:00:31.083	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002071	2313000	2102727	210272	168	16
2872	2022-08-22 16:00:31.09	5-Angel Rios 	15	RETIRO DE GUIA DHL	017-010-0018097	450000	450000	0	168	17
2873	2022-08-24 09:56:27.275	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017095R	0	0	0	169	2
2874	2022-08-24 09:56:27.283	5-Angel Rios 	2	INDI	22005IC04017095R	26600	26600	0	169	3
2875	2022-08-24 09:56:27.283	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017095R	169453	169453	0	169	5
2876	2022-08-24 09:56:27.298	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017095R	137867	137867	0	169	38
2877	2022-08-24 09:56:27.298	5-Angel Rios 	5	I.V.A.	22005IC04017095R	3446663	0	3446663	169	8
2878	2022-08-24 09:56:27.311	5-Angel Rios 	6	CANON INFORMATICO	4637892	98089	98089	0	169	9
2879	2022-08-24 09:56:27.311	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018206	70000	63636	6363	169	12
2880	2022-08-24 09:56:27.327	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0016463	175000	81818	8182	169	13
2881	2022-08-24 09:56:27.327	5-Angel Rios 	9	TASA PORTUARIA	002-007-0030837/38	287686	261532	26153	169	14
2882	2022-08-24 09:56:27.327	5-Angel Rios 	10	CDAP PAGOS	001-025-0160372	6885	6259	625	169	27
2883	2022-08-24 09:56:27.343	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0108178	6780	6163	616	169	11
2884	2022-08-24 09:56:27.343	5-Angel Rios 	12	VISACION MRE	x	0	0	0	169	28
2885	2022-08-24 09:56:27.343	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002072	2376000	2160000	216000	169	16
2886	2022-08-24 09:56:27.358	5-Angel Rios 	14	AUROTIZACION DINAVISA 	1094084	588534	588534	0	169	21
2887	2022-08-24 09:56:27.358	5-Angel Rios 	15	CDAP PAGOS 	001-024-0151176	10000	9091	909	169	17
2888	2022-08-25 13:23:34.884	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01002629L	0	0	0	170	2
2889	2022-08-25 13:23:34.891	5-Angel Rios 	2	INDI	22018EC01002629L	0	0	0	170	3
2890	2022-08-25 13:23:34.901	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01002629L	0	0	0	170	5
2891	2022-08-25 13:23:34.908	5-Angel Rios 	4	IRE GENERAL 700	22018EC01002629L	0	0	0	170	38
2892	2022-08-25 13:23:34.914	5-Angel Rios 	5	I.V.A.	22018EC01002629L	0	0	0	170	8
2893	2022-08-25 13:23:34.92	5-Angel Rios 	6	CANON INFORMATICO	4653405	245222	245222	0	170	9
2894	2022-08-25 13:23:34.927	5-Angel Rios 	7	FOTOCOPIAS	001-001-0000182	50000	45454	4545	170	12
2895	2022-08-25 13:23:34.929	5-Angel Rios 	8	TASA MIC CO	1917597	102489	93171	9317	170	29
2896	2022-08-25 13:23:34.939	5-Angel Rios 	9	TASA UIP CO	002-002-0009568	98089	89171	8917	170	30
2897	2022-08-25 13:23:34.948	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002074	2999000	2726363	272636	170	16
2898	2022-08-25 13:23:34.953	5-Angel Rios 	11	GASTO ADMIN.	x	0	0	0	170	17
2899	2022-08-26 11:40:56.458	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017324M	0	0	0	171	2
2900	2022-08-26 11:40:56.465	5-Angel Rios 	2	INDI	22005IC04017324M	26600	26600	0	171	3
2901	2022-08-26 11:40:56.473	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017324M	3151299	3151299	0	171	5
2902	2022-08-26 11:40:56.48	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017324M	2535271	2535271	0	171	38
2903	2022-08-26 11:40:56.486	5-Angel Rios 	5	I.V.A.	22005IC04017324M	63381775	0	63381775	171	8
2904	2022-08-26 11:40:56.493	5-Angel Rios 	6	CANON INFORMATICO	4679401	245222	245222	0	171	9
2905	2022-08-26 11:40:56.498	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018221	80000	72727	7272	171	12
2906	2022-08-26 11:40:56.504	5-Angel Rios 	8	TASA PORTUARIA	002-007-0031647	3007228	2733843	273384	171	14
2907	2022-08-26 11:40:56.509	5-Angel Rios 	9	CDAP PAGOS	001-025-0161901	6886	6260	626	171	27
2908	2022-08-26 11:40:56.514	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0109704	75181	68346	6834	171	11
2909	2022-08-26 11:40:56.522	5-Angel Rios 	11	VISACION CONSUL	02993879/80/81/82	931500	931500	0	171	15
2910	2022-08-26 11:40:56.527	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002076	5364879	4877162	487716	171	16
2911	2022-08-26 11:40:56.532	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	171	17
2912	2022-08-30 14:15:15.722	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017699E	0	0	0	172	2
2913	2022-08-30 14:15:15.729	5-Angel Rios 	2	INDI	22005IC04017699E	26600	26600	0	172	3
2914	2022-08-30 14:15:15.736	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017699E	3383926	3383926	0	172	5
2915	2022-08-30 14:15:15.743	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017699E	2722302	2722302	0	172	38
2916	2022-08-30 14:15:15.749	5-Angel Rios 	5	I.V.A.	22005IC04017699E	68057580	0	68057580	172	8
2917	2022-08-30 14:15:15.761	5-Angel Rios 	6	CANON INFORMATICO	4721479	245222	245222	0	172	9
2918	2022-08-30 14:15:15.763	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018239	90000	81818	8181	172	12
2919	2022-08-30 14:15:15.773	5-Angel Rios 	8	TASA PORTUARIA	002-007-0032844	323279	293890	29389	172	14
2920	2022-08-30 14:15:15.779	5-Angel Rios 	9	CDAP PAGOS	001-025-0164420	6879	6253	625	172	27
2921	2022-08-30 14:15:15.783	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0112230	7342	6674	667	172	11
2922	2022-08-30 14:15:15.789	5-Angel Rios 	11	VISACION CONSUL	2996862/63/64/65	784700	784700	0	172	15
2923	2022-08-30 14:15:15.793	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002077	5559752	5054320	505432	172	16
2924	2022-08-30 14:15:15.798	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	172	17
2925	2022-08-30 14:59:13.423	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017683U	0	0	0	173	2
2926	2022-08-30 14:59:13.47	5-Angel Rios 	2	INDI	22005IC04017683U	26600	26600	0	173	3
2927	2022-08-30 14:59:13.474	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017683U	957141	957141	0	173	5
2928	2022-08-30 14:59:13.483	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017683U	771168	771168	0	173	38
2929	2022-08-30 14:59:13.489	5-Angel Rios 	5	I.V.A.	22005IC04017683U	19279188	0	19279188	173	8
2930	2022-08-30 14:59:13.493	5-Angel Rios 	6	CANON INFORMATICO	4719411	245222	245222	0	173	9
2931	2022-08-30 14:59:13.501	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0016601	175000	81818	8182	173	13
2932	2022-08-30 14:59:13.517	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018241	70000	63636	6363	173	12
2933	2022-08-30 14:59:13.525	5-Angel Rios 	9	TASA PORTUARIA	002-007-0032784	1010376	918523	91852	173	14
2934	2022-08-30 14:59:13.531	5-Angel Rios 	10	CDAP PAGOS	001-025-0164288	6879	6253	625	173	27
2935	2022-08-30 14:59:13.536	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0112094	25259	22962	2296	173	11
2936	2022-08-30 14:59:13.552	5-Angel Rios 	12	VISACION CONSUL	2996856/57	240000	240000	0	173	15
2937	2022-08-30 14:59:13.566	5-Angel Rios 	13	VISACION MRE	1099918	490445	490445	0	173	28
2938	2022-08-30 14:59:13.584	5-Angel Rios 	14	CDAP PAGOS	001-024-0155184	10000	9090	909	173	27
2939	2022-08-30 14:59:13.608	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002078	3461855	3147140	314714	173	16
2940	2022-08-30 14:59:13.616	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	173	17
2941	2022-08-31 09:29:49.473	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017699E	0	0	0	174	2
2942	2022-08-31 09:29:49.481	5-Angel Rios 	2	INDI	22005IC04017699E	26600	26600	0	174	3
2943	2022-08-31 09:29:49.481	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017699E	3383926	3383926	0	174	5
2944	2022-08-31 09:29:49.481	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017699E	2722302	2722302	0	174	38
2945	2022-08-31 09:29:49.496	5-Angel Rios 	5	I.V.A.	22005IC04017699E	68057580	0	68057580	174	8
2946	2022-08-31 09:29:49.496	5-Angel Rios 	6	CANON INFORMATICO	4721479	245222	245222	0	174	9
2947	2022-08-31 09:29:49.512	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018239	90000	81818	8181	174	12
2948	2022-08-31 09:29:49.512	5-Angel Rios 	8	TASA PORTUARIA	002-007-0032844	323279	293890	29389	174	14
2949	2022-08-31 09:29:49.512	5-Angel Rios 	9	CDAP PAGOS	001-025-0164420	6879	6253	625	174	27
2950	2022-08-31 09:29:49.527	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0112230	7342	6674	667	174	11
2951	2022-08-31 09:29:49.527	5-Angel Rios 	11	VISACION CONSUL	2996862/63/64/65	784700	784700	0	174	15
2952	2022-08-31 09:29:49.527	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002077	5559752	5054320	505432	174	16
2953	2022-08-31 09:29:49.527	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	174	17
2954	2022-09-06 13:21:07.354	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01002739N	0	0	0	175	2
2955	2022-09-06 13:21:07.36	5-Angel Rios 	2	INDI	22018EC01002739N	0	0	0	175	3
2956	2022-09-06 13:21:07.372	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01002739N	0	0	0	175	5
2957	2022-09-06 13:21:07.38	5-Angel Rios 	4	IRE GENERAL 700	22018EC01002739N	0	0	0	175	38
2958	2022-09-06 13:21:07.386	5-Angel Rios 	5	I.V.A.	22018EC01002739N	0	0	0	175	8
2959	2022-09-06 13:21:07.393	5-Angel Rios 	6	CANON INFORMATICO	4736242	245222	245222	0	175	9
2960	2022-09-06 13:21:07.399	5-Angel Rios 	7	FOTOCOPIAS	001-001-0009302	50000	45454	4545	175	12
2961	2022-09-06 13:21:07.404	5-Angel Rios 	8	TASA UIP CO	002-002-0000002	98089	89171	8917	175	30
2962	2022-09-06 13:21:07.412	5-Angel Rios 	9	TASA MIC CO	1926259	98089	89171	8917	175	29
2963	2022-09-06 13:21:07.418	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002081	3029000	2753636	275363	175	16
2964	2022-09-06 13:21:07.424	5-Angel Rios 	11	PROG PRODUCCION	001-001-0002080	2424295	2424295	0	175	17
2965	2022-09-06 13:39:54.016	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04017962U	0	0	0	176	2
2966	2022-09-06 13:39:54.036	5-Angel Rios 	2	INDI	22005IC04017962U	26600	26600	0	176	3
2967	2022-09-06 13:39:54.088	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04017962U	3786529	3786529	0	176	5
2968	2022-09-06 13:39:54.117	5-Angel Rios 	4	IRE GENERAL 700	22005IC04017962U	3045996	3045996	0	176	38
2969	2022-09-06 13:39:54.159	5-Angel Rios 	5	I.V.A.	22005IC04017962U	76149889	0	76149889	176	8
2970	2022-09-06 13:39:54.179	5-Angel Rios 	6	CANON INFORMATICO	4746584	245222	245222	0	176	9
2971	2022-09-06 13:39:54.227	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018268	80000	72727	7272	176	12
2972	2022-09-06 13:39:54.234	5-Angel Rios 	8	TASA PORTUARIA	002-007-0033864	3402765	3093422	309342	176	14
2973	2022-09-06 13:39:54.247	5-Angel Rios 	9	CDAP PAGOS	001-025-0166452	6904	6276	627	176	27
2974	2022-09-06 13:39:54.269	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0114256	85069	77335	7733	176	11
2975	2022-09-06 13:39:54.309	5-Angel Rios 	11	VISACION CONSUL	2999186/87/18/17	796500	796500	0	176	15
2976	2022-09-06 13:39:54.343	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002082	5446242	4951129	495112	176	16
2977	2022-09-06 13:39:54.379	5-Angel Rios 	13	REPOSICION DE GASTOS ADM	x	0	0	0	176	21
2978	2022-09-06 13:54:17.057	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000461Z	0	0	0	177	2
2979	2022-09-06 13:54:17.065	5-Angel Rios 	2	INDI	22005IM04000461Z	14499	14499	0	177	3
2980	2022-09-06 13:54:17.078	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000461Z	26775	26775	0	177	5
2981	2022-09-06 13:54:17.085	5-Angel Rios 	4	IRE GENERAL 700	22005IM04000461Z	22413	22413	0	177	38
2982	2022-09-06 13:54:17.098	5-Angel Rios 	5	I.V.A.	22005IM04000461Z	560336	0	560336	177	8
2983	2022-09-06 13:54:17.108	5-Angel Rios 	6	CANON INFORMATICO	4746575	49044	49044	0	177	9
2984	2022-09-06 13:54:17.12	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018269	80000	72727	7272	177	12
2985	2022-09-06 13:54:17.134	5-Angel Rios 	8	TASA PORTUARIA	002-007-0033855	32533	29575	2957	177	14
2986	2022-09-06 13:54:17.14	5-Angel Rios 	9	CDAP PAGOS	001-025-0166443	6904	6276	627	177	27
2987	2022-09-06 13:54:17.165	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0114243	813	739	73	177	11
2988	2022-09-06 13:54:17.182	5-Angel Rios 	11	VISACION CONSUL	2999180/81	239000	239000	0	177	15
2989	2022-09-06 13:54:17.196	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002084	978000	889090	88909	177	16
2990	2022-09-06 13:54:17.211	5-Angel Rios 	13	REPOSICION DE GASTOS ADM	x	0	0	0	177	21
2991	2022-09-06 14:11:27.184	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018041J	0	0	0	178	2
2992	2022-09-06 14:11:27.192	5-Angel Rios 	2	INDI	22005IC04018041J	26600	26600	0	178	3
2993	2022-09-06 14:11:27.206	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018041J	303504	303504	0	178	5
2994	2022-09-06 14:11:27.23	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018041J	245644	245644	0	178	38
2995	2022-09-06 14:11:27.236	5-Angel Rios 	5	I.V.A.	22005IC04018041J	6141095	0	6141095	178	8
2996	2022-09-06 14:11:27.278	5-Angel Rios 	6	CANON INFORMATICO	4738578	245222	245222	0	178	9
2997	2022-09-06 14:11:27.298	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0016732	175000	159090	15909	178	13
2998	2022-09-06 14:11:27.32	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018270	70000	63636	6363	178	12
2999	2022-09-06 14:11:27.345	5-Angel Rios 	9	TASA PORTUARIA	002-007-0034044	343710	312463	31246	178	14
3000	2022-09-06 14:11:27.365	5-Angel Rios 	10	CDAP PAGOS	001-025-0166770	6904	6276	627	178	27
3001	2022-09-06 14:11:27.372	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0114575	8593	7811	781	178	11
3002	2022-09-06 14:11:27.378	5-Angel Rios 	12	VISACION MRE	1102168	490445	490445	0	178	28
3003	2022-09-06 14:11:27.384	5-Angel Rios 	13	CDAP PAGOS	001-024-0157331	10000	9090	909	178	27
3004	2022-09-06 14:11:27.394	5-Angel Rios 	14	VISACION CONSUL	2997698/99	239000	239000	0	178	15
3005	2022-09-06 14:11:27.41	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002085	2064000	1876363	187636	178	16
3006	2022-09-06 14:11:27.428	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	178	21
3007	2022-09-06 15:06:26.447	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04018438Z	15132415	15132415	0	179	2
3008	2022-09-06 15:06:26.454	5-Angel Rios 	2	INDI	22002IC04018438Z	34330	34330	0	179	3
3009	2022-09-06 15:06:26.459	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04018438Z	589413	589413	0	179	5
3010	2022-09-06 15:06:26.465	5-Angel Rios 	4	IRE GENERAL 700	22002IC04018438Z	536515	536515	0	179	38
3011	2022-09-06 15:06:26.47	5-Angel Rios 	5	I.V.A.	22002IC04018438Z	13412891	0	13412891	179	8
3012	2022-09-06 15:06:26.474	5-Angel Rios 	6	CANON INFORMATICO	4780033	250222	250222	0	179	9
3013	2022-09-06 15:06:26.481	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	179	13
3014	2022-09-06 15:06:26.485	5-Angel Rios 	8	FOTOCOPIAS	004-003-0002666	56000	50909	5090	179	12
3015	2022-09-06 15:06:26.494	5-Angel Rios 	9	TASA PORTUARIA	001-034-0056721/22	2098185	1907440	190744	179	14
3016	2022-09-06 15:06:26.499	5-Angel Rios 	10	CDAP PAGOS	001-021-0059560	20773	18884	1888	179	27
3017	2022-09-06 15:06:26.503	5-Angel Rios 	11	VISACION MRE	1103520	490445	490445	0	179	28
3018	2022-09-06 15:06:26.512	5-Angel Rios 	12	CDAP PAGOS	x	0	0	0	179	27
3019	2022-09-06 15:06:26.517	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002086	3616032	3287301	328730	179	16
3020	2022-09-06 15:06:26.522	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	179	21
3021	2022-09-06 15:24:21.38	5-Angel Rios 	1	DERECHO ADUANERO	22002IRE4003771A	142853	142853	0	180	2
3022	2022-09-06 15:24:21.386	5-Angel Rios 	2	INDI	22002IRE4003771A	0	0	0	180	3
3023	2022-09-06 15:24:21.392	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IRE4003771A	5102	5102	0	180	5
3024	2022-09-06 15:24:21.4	5-Angel Rios 	4	IRE GENERAL 700	22002IRE4003771A	0	0	0	180	38
3025	2022-09-06 15:24:21.406	5-Angel Rios 	5	I.V.A.	22002IRE4003771A	116834	0	116834	180	8
3026	2022-09-06 15:24:21.411	5-Angel Rios 	6	CANON INFORMATICO	4735928	54044	54044	0	180	9
3027	2022-09-06 15:24:21.419	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	180	13
3028	2022-09-06 15:24:21.428	5-Angel Rios 	8	FOTOCOPIAS	001-001-0008972	30000	27272	2727	180	12
3029	2022-09-06 15:24:21.433	5-Angel Rios 	9	TASA PORTUARIA	001-034-0056255	151800	138000	13800	180	14
3030	2022-09-06 15:24:21.442	5-Angel Rios 	10	CDAP PAGOS	001-021-0059126	20733	18848	1884	180	27
3031	2022-09-06 15:24:21.447	5-Angel Rios 	11	CANJE GUÍA AEREA	017-010-0019489	450000	450000	0	180	28
3032	2022-09-06 15:24:21.452	5-Angel Rios 	12	CDAP PAGOS	x	0	0	0	180	27
3033	2022-09-06 15:24:21.457	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002087	935000	850000	85000	180	16
3034	2022-09-06 15:24:21.463	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	180	21
3035	2022-09-07 16:24:16.371	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018181Y	223524	223524	0	181	2
3036	2022-09-07 16:24:16.378	5-Angel Rios 	2	INDI	22005IC04018181Y	26601	26601	0	181	3
3037	2022-09-07 16:24:16.386	5-Angel Rios 	3	I.S.C.	22005IC04018181Y	27374	27374	0	181	4
3038	2022-09-07 16:24:16.397	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018181Y	231017	231017	0	181	5
3039	2022-09-07 16:24:16.403	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018181Y	188366	188366	0	181	38
3040	2022-09-07 16:24:16.413	5-Angel Rios 	6	I.V.A.	22005IC04018181Y	4709202	0	4709202	181	8
3041	2022-09-07 16:24:16.419	5-Angel Rios 	7	CANON INFORMATICO	4800803	245222	245222	0	181	9
3042	2022-09-07 16:24:16.424	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	181	13
3043	2022-09-07 16:24:16.43	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018294	100000	90909	9090	181	12
3044	2022-09-07 16:24:16.438	5-Angel Rios 	10	TASA PORTUARIA	002-007-0034705	257199	233817	23381	181	14
3045	2022-09-07 16:24:16.442	5-Angel Rios 	11	CDAP PAGOS	001-025-0168180	6919	6290	629	181	27
3046	2022-09-07 16:24:16.45	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0115989	6430	5845	584	181	11
3047	2022-09-07 16:24:16.456	5-Angel Rios 	13	VISACION MRE	x	0	0	0	181	28
3048	2022-09-07 16:24:16.462	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	181	27
3049	2022-09-07 16:24:16.466	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002088	1432238	1302034	130203	181	16
3050	2022-09-07 16:24:16.472	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	181	21
3051	2022-09-07 16:34:35.722	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018180N	1979353	1979353	0	182	2
3052	2022-09-07 16:34:35.734	5-Angel Rios 	2	INDI	22005IC04018180N	33952	33952	0	182	3
3053	2022-09-07 16:34:35.738	5-Angel Rios 	3	I.S.C.	22005IC04018180N	399873	399873	0	182	4
3054	2022-09-07 16:34:35.745	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018180N	467690	467690	0	182	5
3055	2022-09-07 16:34:35.751	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018180N	387617	387617	0	182	38
3056	2022-09-07 16:34:35.756	5-Angel Rios 	6	I.V.A.	22005IC04018180N	9690421	0	9690421	182	8
3057	2022-09-07 16:34:35.763	5-Angel Rios 	7	CANON INFORMATICO	x	0	0	0	182	9
3058	2022-09-07 16:34:35.768	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	182	13
3059	2022-09-07 16:34:35.774	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018295	180000	163636	16363	182	12
3060	2022-09-07 16:34:35.778	5-Angel Rios 	10	TASA PORTUARIA	002-007-0034704	611041	555491	55549	182	14
3061	2022-09-07 16:34:35.785	5-Angel Rios 	11	CDAP PAGOS	001-025-0168179	6919	6290	629	182	27
3062	2022-09-07 16:34:35.791	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0115988	15276	13887	1388	182	11
3063	2022-09-07 16:34:35.796	5-Angel Rios 	13	VISACION MRE	x	0	0	0	182	28
3064	2022-09-07 16:34:35.802	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	182	27
3065	2022-09-07 16:34:35.807	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002089	2352137	2138306	213830	182	16
3066	2022-09-07 16:34:35.812	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	182	21
3067	2022-09-08 15:55:39.246	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018451Y	0	0	0	183	2
3068	2022-09-08 15:55:39.254	5-Angel Rios 	2	INDI	22005IC04018451Y	34331	34331	0	183	3
3069	2022-09-08 15:55:39.266	5-Angel Rios 	3	TASA INTER. ADUAN.	22005IC04018451Y	345297	345297	0	183	24
3070	2022-09-08 15:55:39.29	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018451Y	1207139	1207139	0	183	5
3071	2022-09-08 15:55:39.304	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018451Y	972639	972639	0	183	38
3072	2022-09-08 15:55:39.312	5-Angel Rios 	6	I.V.A.	22005IC04018451Y	24315978	0	24315978	183	8
3073	2022-09-08 15:55:39.318	5-Angel Rios 	7	CANON INFORMATICO	4889560	245222	245222	0	183	9
3074	2022-09-08 15:55:39.331	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	183	13
3075	2022-09-08 15:55:39.338	5-Angel Rios 	9	FOTOCOPIAS	001-001-0091101	70000	63636	6363	183	12
3076	2022-09-08 15:55:39.344	5-Angel Rios 	10	TASA PORTUARIA	002-007-0035496	1416747	1287951	128795	183	14
3077	2022-09-08 15:55:39.35	5-Angel Rios 	11	CDAP PAGOS	001-024-0160229	10000	9090	909	183	27
3078	2022-09-08 15:55:39.363	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0117708	35419	32199	3219	183	11
3079	2022-09-08 15:55:39.371	5-Angel Rios 	13	VISACION MRE	1106158	490445	490445	0	183	28
3080	2022-09-08 15:55:39.376	5-Angel Rios 	14	CDAP PAGOS	001-024-0160229	10000	9090	909	183	27
3081	2022-09-08 15:55:39.382	5-Angel Rios 	15	MIPYMES MIC	1921915	691023	628202	62820	183	22
3082	2022-09-08 15:55:39.391	5-Angel Rios 	16	APORTE VUE	1921916	73199	66544	6654	183	23
3083	2022-09-08 15:55:39.4	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002091	5914853	5377139	537713	183	16
3084	2022-09-08 15:55:39.406	5-Angel Rios 	18	REPOSICION DE GASTOS ADM	x	0	0	0	183	21
3085	2022-09-08 16:01:54.074	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018451Y	0	0	0	184	2
3086	2022-09-08 16:01:54.08	5-Angel Rios 	2	INDI	22005IC04018451Y	34331	34331	0	184	3
3087	2022-09-08 16:01:54.088	5-Angel Rios 	3	TASA INTER. ADUAN.	22005IC04018451Y	345297	345297	0	184	24
3088	2022-09-08 16:01:54.094	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018451Y	1207139	1207139	0	184	5
3089	2022-09-08 16:01:54.101	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018451Y	972639	972639	0	184	38
3090	2022-09-08 16:01:54.109	5-Angel Rios 	6	I.V.A.	22005IC04018451Y	24315978	0	24315978	184	8
3091	2022-09-08 16:01:54.115	5-Angel Rios 	7	CANON INFORMATICO	4889560	245222	245222	0	184	9
3092	2022-09-08 16:01:54.121	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	184	13
3977	2022-10-19 15:14:23.482	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	245	17
3093	2022-09-08 16:01:54.133	5-Angel Rios 	9	FOTOCOPIAS	001-001-0091101	70000	63636	6363	184	12
3094	2022-09-08 16:01:54.138	5-Angel Rios 	10	TASA PORTUARIA	002-007-0035496	1416747	1287951	128795	184	14
3095	2022-09-08 16:01:54.144	5-Angel Rios 	11	CDAP PAGOS	001-024-0160229	10000	9090	909	184	27
3096	2022-09-08 16:01:54.149	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0117708	35419	32199	3219	184	11
3097	2022-09-08 16:01:54.167	5-Angel Rios 	13	VISACION MRE	1106158	490445	490445	0	184	28
3098	2022-09-08 16:01:54.178	5-Angel Rios 	14	CDAP PAGOS	001-024-0160229	10000	9090	909	184	27
3099	2022-09-08 16:01:54.184	5-Angel Rios 	15	MIPYMES MIC	1921915	691023	628202	62820	184	22
3100	2022-09-08 16:01:54.196	5-Angel Rios 	16	APORTE VUE	1921916	73199	66544	6654	184	23
3101	2022-09-08 16:01:54.202	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002091	5914853	5377139	537713	184	16
3102	2022-09-08 16:01:54.208	5-Angel Rios 	18	REPOSICION DE GASTOS ADM	x	0	0	0	184	21
3103	2022-09-12 11:14:15.924	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018593V	0	0	0	185	2
3104	2022-09-12 11:14:15.933	5-Angel Rios 	2	INDI	22005IC04018593V	26600	26600	0	185	3
3105	2022-09-12 11:14:15.937	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018593V	848013	848013	0	185	5
3106	2022-09-12 11:14:15.945	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018593V	683429	683429	0	185	38
3107	2022-09-12 11:14:15.953	5-Angel Rios 	5	I.V.A.	22005IC04018593V	17085722	0	17085722	185	8
3108	2022-09-12 11:14:15.964	5-Angel Rios 	6	CANON INFORMATICO	4907652	245222	245222	0	185	9
3109	2022-09-12 11:14:15.97	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0016776	175000	159090	15909	185	13
3110	2022-09-12 11:14:15.979	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018305	70000	63636	6363	185	12
3111	2022-09-12 11:14:15.983	5-Angel Rios 	9	TASA PORTUARIA	002-007-0035972	916753	833411	83341	185	14
3112	2022-09-12 11:14:15.993	5-Angel Rios 	10	CDAP PAGOS	001-025-0171050	6922	6292	629	185	27
3113	2022-09-12 11:14:15.996	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0118862	22919	20835	2083	185	11
3114	2022-09-12 11:14:16	5-Angel Rios 	12	VISACION CONSUL	03004291/92	243000	243000	0	185	28
3115	2022-09-12 11:14:16.012	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002092	2880469	2618608	261860	185	16
3116	2022-09-12 11:14:16.024	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	185	21
3117	2022-09-12 15:24:03.614	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018505Y	0	0	0	186	2
3118	2022-09-12 15:24:03.622	5-Angel Rios 	2	INDI	22005IC04018505Y	26600	26600	0	186	3
3119	2022-09-12 15:24:03.628	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018505Y	356809	356809	0	186	5
3120	2022-09-12 15:24:03.632	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018505Y	288500	288500	0	186	38
3121	2022-09-12 15:24:03.641	5-Angel Rios 	5	I.V.A.	22005IC04018505Y	7212512	0	7212512	186	8
3122	2022-09-12 15:24:03.646	5-Angel Rios 	6	CANON INFORMATICO	4903968	245222	245222	0	186	9
3123	2022-09-12 15:24:03.65	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0016775	175000	159090	15909	186	13
3124	2022-09-12 15:24:03.659	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018303	90000	81818	8181	186	12
3125	2022-09-12 15:24:03.664	5-Angel Rios 	9	TASA PORTUARIA	002-007-0035702	423070	384609	38460	186	14
3126	2022-09-12 15:24:03.668	5-Angel Rios 	10	CDAP PAGOS	001-025-0170494	6909	6280	628	186	27
3127	2022-09-12 15:24:03.676	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0118301	10577	9615	961	186	11
3128	2022-09-12 15:24:03.68	5-Angel Rios 	12	VISACION CONSUL	3002587/88/89	446000	446000	0	186	15
3129	2022-09-12 15:24:03.683	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002093	1939036	1762760	176276	186	16
3130	2022-09-12 15:24:03.691	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	186	21
3131	2022-09-12 15:34:36.851	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018596B	76094	76094	0	187	2
3132	2022-09-12 15:34:36.864	5-Angel Rios 	2	INDI	22005IC04018596B	26600	26600	0	187	3
3133	2022-09-12 15:34:36.871	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018596B	404917	404917	0	187	5
3134	2022-09-12 15:34:36.878	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018596B	327484	327484	0	187	38
3135	2022-09-12 15:34:36.883	5-Angel Rios 	5	I.V.A.	22005IC04018596B	8187103	0	8187103	187	8
3136	2022-09-12 15:34:36.89	5-Angel Rios 	6	CANON INFORMATICO	4910425	245222	245222	0	187	9
3137	2022-09-12 15:34:36.895	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	187	13
3138	2022-09-12 15:34:36.899	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018302	90000	81818	8181	187	12
3139	2022-09-12 15:34:36.906	5-Angel Rios 	9	TASA PORTUARIA	002-007-0035993	453109	411917	41191	187	14
3140	2022-09-12 15:34:36.91	5-Angel Rios 	10	CDAP PAGOS	001-025-0171109	6922	6292	629	187	27
3141	2022-09-12 15:34:36.917	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0118916	11328	10298	1029	187	11
3142	2022-09-12 15:34:36.923	5-Angel Rios 	12	VISACION CONSUL	3004018/19/20/21	810000	810000	0	187	15
3143	2022-09-12 15:34:36.928	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002094	2121006	1928187	192818	187	16
3144	2022-09-12 15:34:36.933	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	187	21
3145	2022-09-12 15:45:45.114	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018600K	0	0	0	188	2
3146	2022-09-12 15:45:45.121	5-Angel Rios 	2	INDI	22005IC04018600K	26601	26601	0	188	3
3147	2022-09-12 15:45:45.129	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018600K	3184118	3184118	0	188	5
3148	2022-09-12 15:45:45.142	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018600K	2561659	2561659	0	188	38
3149	2022-09-12 15:45:45.146	5-Angel Rios 	5	I.V.A.	22005IC04018600K	64041452	0	64041452	188	8
3150	2022-09-12 15:45:45.152	5-Angel Rios 	6	CANON INFORMATICO	4910434	245222	245222	0	188	9
3151	2022-09-12 15:45:45.16	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	188	13
3152	2022-09-12 15:45:45.165	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018304	100000	90909	9090	188	12
3153	2022-09-12 15:45:45.17	5-Angel Rios 	9	TASA PORTUARIA	002-007-0035994	3038223	2762020	276202	188	14
3154	2022-09-12 15:45:45.176	5-Angel Rios 	10	CDAP PAGOS	001-025-0171110	6922	6292	629	188	27
3155	2022-09-12 15:45:45.181	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0118919	75956	69050	6905	188	11
3156	2022-09-12 15:45:45.185	5-Angel Rios 	12	VISACION CONSUL	3004412/13/14	446000	446000	0	188	15
3157	2022-09-12 15:45:45.191	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002095	5218400	4744000	474400	188	16
3158	2022-09-12 15:45:45.2	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	188	21
3159	2022-09-13 08:32:10.924	5-Angel Rios 	1	DERECHO ADUANERO	22018EC1002796Z	0	0	0	189	2
3160	2022-09-13 08:32:10.93	5-Angel Rios 	2	INDI	22018EC1002796Z	0	0	0	189	3
3161	2022-09-13 08:32:10.932	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC1002796Z	0	0	0	189	5
3162	2022-09-13 08:32:10.932	5-Angel Rios 	4	IRE GENERAL 700	22018EC1002796Z	0	0	0	189	38
3163	2022-09-13 08:32:10.947	5-Angel Rios 	5	I.V.A.	22018EC1002796Z	0	0	0	189	8
3164	2022-09-13 08:32:10.947	5-Angel Rios 	6	CANON INFORMATICO	4869262	245222	245222	0	189	9
3165	2022-09-13 08:32:10.963	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	189	13
3166	2022-09-13 08:32:10.963	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009336	50000	45454	4545	189	12
3167	2022-09-13 08:32:10.963	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	189	14
3168	2022-09-13 08:32:10.979	5-Angel Rios 	10	TASA MIC CO	1930852	102489	93171	9317	189	27
3169	2022-09-13 08:32:10.979	5-Angel Rios 	11	TASA UIP CO	002-002-0000145	98089	89171	8917	189	11
3170	2022-09-13 08:32:10.979	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	189	15
3171	2022-09-13 08:32:10.994	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002096	3030458	2754961	275496	189	16
3172	2022-09-13 08:32:10.994	5-Angel Rios 	14	REPOSICION DE GASTOS ADM	x	0	0	0	189	21
3173	2022-09-14 11:48:20.404	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018820Y	2851485	2851485	0	190	2
3174	2022-09-14 11:48:20.411	5-Angel Rios 	2	INDI	22005IC04018820Y	14563	14563	0	190	3
3175	2022-09-14 11:48:20.418	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04018820Y	237624	237624	0	190	5
3176	2022-09-14 11:48:20.424	5-Angel Rios 	4	IRE GENERAL 700	22005IC04018820Y	203346	203346	0	190	38
3177	2022-09-14 11:48:20.432	5-Angel Rios 	5	I.V.A.	22005IC04018820Y	5083646	0	5083646	190	8
3178	2022-09-14 11:48:20.44	5-Angel Rios 	6	CANON INFORMATICO	4962607	245222	245222	0	190	9
3179	2022-09-14 11:48:20.445	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0016849	175000	159090	15909	190	13
3180	2022-09-14 11:48:20.451	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018315	70000	63636	6363	190	12
3181	2022-09-14 11:48:20.458	5-Angel Rios 	9	TASA PORTUARIA	002-007-0036826	264466	240423	24042	190	14
3182	2022-09-14 11:48:20.467	5-Angel Rios 	10	CDAP PAGOS	001-025-0172961	6934	6303	630	190	27
3183	2022-09-14 11:48:20.473	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0120771	6612	6010	601	190	11
3184	2022-09-14 11:48:20.479	5-Angel Rios 	12	VISACION CONSUL	3005759/60	243000	243000	0	190	15
3185	2022-09-14 11:48:20.484	5-Angel Rios 	13	LICENCIA PREV MIC 	1110825	686623	686623	0	190	32
3186	2022-09-14 11:48:20.49	5-Angel Rios 	14	CDAP PAGOS	001-024-0162815	10000	9090	909	190	27
3187	2022-09-14 11:48:20.495	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002097	1606000	1460000	146000	190	16
3188	2022-09-14 11:48:20.503	5-Angel Rios 	16	GASTO ADMIN.	749	4500000	4500000	0	190	17
3189	2022-09-16 14:22:05.616	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04007766E	89171108	89171108	0	191	2
3190	2022-09-16 14:22:05.625	5-Angel Rios 	2	INDI	22038IC04007766E	69370	69370	0	191	3
3191	2022-09-16 14:22:05.632	5-Angel Rios 	3	SERVICIO DE VALORACION	22038IC04007766E	3260657	3260657	0	191	5
3192	2022-09-16 14:22:05.641	5-Angel Rios 	4	IRE GENERAL 700	22038IC04007766E	2982495	2982495	0	191	38
3193	2022-09-16 14:22:05.647	5-Angel Rios 	5	I.V.A.	22038IC04007766E	74562354	0	74562354	191	8
3194	2022-09-16 14:22:05.653	5-Angel Rios 	6	TASA INTER. ADUAN.	22038IC04007766E	348613	348613	0	191	24
3195	2022-09-16 14:22:05.66	5-Angel Rios 	7	DECRETO ANNP	4975498	347596	347596	0	191	45
3196	2022-09-16 14:22:05.665	5-Angel Rios 	8	CANON INFORMATICO	4975478	245222	245222	0	191	9
3197	2022-09-16 14:22:05.67	5-Angel Rios 	9	FOTOCOPIAS	002-002-0001094	67600	61454	6145	191	12
3198	2022-09-16 14:22:05.677	5-Angel Rios 	10	TASA PORTUARIA	002-002-0004254	694980	631800	63180	191	14
3199	2022-09-16 14:22:05.683	5-Angel Rios 	11	VISACION MRE	1112609	980890	980890	0	191	28
3200	2022-09-16 14:22:05.689	5-Angel Rios 	12	CDAP PAGOS	001-024-0164171	10000	9090	909	191	27
3201	2022-09-16 14:22:05.694	5-Angel Rios 	13	MOPC	1112632	49045	49045	0	191	34
3202	2022-09-16 14:22:05.699	5-Angel Rios 	14	CDAP PAGOS	001-024-0164173	10000	9090	909	191	27
3203	2022-09-16 14:22:05.706	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002098	5232034	4756394	475639	191	16
3204	2022-09-16 14:22:05.711	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	001-001-0002099	660000	660000	0	191	21
3205	2022-09-20 14:08:53.098	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04019195U	30232	30232	0	192	2
3206	2022-09-20 14:08:53.109	5-Angel Rios 	2	INDI	22005IC04019195U	26600	26600	0	192	3
3207	2022-09-20 14:08:53.116	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04019195U	2958811	2958811	0	192	5
3208	2022-09-20 14:08:53.122	5-Angel Rios 	4	IRE GENERAL 700	22005IC04019195U	2380632	2380632	0	192	38
3209	2022-09-20 14:08:53.128	5-Angel Rios 	5	I.V.A.	22005IC04019195U	59515782	0	59515782	192	8
3210	2022-09-20 14:08:53.138	5-Angel Rios 	6	CANON INFORMATICO	4986888	245222	245222	0	192	9
3211	2022-09-20 14:08:53.145	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018354	90000	81818	8181	192	12
3212	2022-09-20 14:08:53.151	5-Angel Rios 	8	TASA PORTUARIA	002-007-0038178	2829716	2572469	257246	192	14
3213	2022-09-20 14:08:53.156	5-Angel Rios 	9	CDAP PAGOS	001-025-0175563	6983	6348	634	192	27
3214	2022-09-20 14:08:53.163	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0123376	70743	64311	6431	192	11
3215	2022-09-20 14:08:53.169	5-Angel Rios 	11	VISACION CONSUL	03013889/90/91/92	810000	810000	0	192	15
3216	2022-09-20 14:08:53.176	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002100	4981893	4528993	452899	192	16
3217	2022-09-20 14:08:53.182	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	192	17
3218	2022-09-20 14:22:38.472	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000489D	119587	119587	0	193	2
3219	2022-09-20 14:22:38.477	5-Angel Rios 	2	INDI	22005IM04000489D	14666	14666	0	193	3
3220	2022-09-20 14:22:38.484	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000489D	29142	29142	0	193	5
3221	2022-09-20 14:22:38.49	5-Angel Rios 	4	IRE GENERAL 700	22005IM04000489D	24804	24804	0	193	38
3222	2022-09-20 14:22:38.498	5-Angel Rios 	5	I.V.A.	22005IM04000489D	620098	0	620098	193	8
3223	2022-09-20 14:22:38.503	5-Angel Rios 	6	CANON INFORMATICO	4986914	98088	98088	0	193	9
3224	2022-09-20 14:22:38.508	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018355	100000	90909	9090	193	12
3225	2022-09-20 14:22:38.513	5-Angel Rios 	8	TASA PORTUARIA	002-007-0038177	35134	31940	3194	193	14
3226	2022-09-20 14:22:38.519	5-Angel Rios 	9	CDAP PAGOS	001-025-0175564	6983	6348	634	193	27
3227	2022-09-20 14:22:38.525	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0123375	878	798	79	193	11
3228	2022-09-20 14:22:38.53	5-Angel Rios 	11	VISACION CONSUL	03013509/10	243000	243000	0	193	15
3229	2022-09-20 14:22:38.536	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002102	1188000	1080000	108000	193	16
3230	2022-09-20 14:22:38.54	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	193	17
3231	2022-09-20 14:23:44.609	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04019195U	30232	30232	0	194	2
3232	2022-09-20 14:23:44.614	5-Angel Rios 	2	INDI	22005IC04019195U	26600	26600	0	194	3
3233	2022-09-20 14:23:44.621	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04019195U	2958811	2958811	0	194	5
3234	2022-09-20 14:23:44.627	5-Angel Rios 	4	IRE GENERAL 700	22005IC04019195U	2380632	2380632	0	194	38
3235	2022-09-20 14:23:44.632	5-Angel Rios 	5	I.V.A.	22005IC04019195U	59515782	0	59515782	194	8
3236	2022-09-20 14:23:44.636	5-Angel Rios 	6	CANON INFORMATICO	4986888	245222	245222	0	194	9
3237	2022-09-20 14:23:44.641	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018354	90000	81818	8181	194	12
3238	2022-09-20 14:23:44.649	5-Angel Rios 	8	TASA PORTUARIA	002-007-0038178	2829716	2572469	257246	194	14
3239	2022-09-20 14:23:44.656	5-Angel Rios 	9	CDAP PAGOS	001-025-0175563	6983	6348	634	194	27
3240	2022-09-20 14:23:44.661	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0123376	70743	64311	6431	194	11
3241	2022-09-20 14:23:44.668	5-Angel Rios 	11	VISACION CONSUL	03013889/90/91/92	810000	810000	0	194	15
3242	2022-09-20 14:23:44.674	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002100	4981893	4528993	452899	194	16
3243	2022-09-20 14:23:44.679	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	194	17
3244	2022-09-26 08:36:48.501	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04019675A	0	0	0	195	2
3245	2022-09-26 08:36:48.508	5-Angel Rios 	2	INDI	22005IC04019675A	14706	14706	0	195	3
3246	2022-09-26 08:36:48.508	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04019675A	2441011	2441011	0	195	5
3247	2022-09-26 08:36:48.524	5-Angel Rios 	4	IRE GENERAL 700	22005IC04019675A	1963472	1963472	0	195	38
3248	2022-09-26 08:36:48.539	5-Angel Rios 	5	I.V.A.	22005IC04019675A	49086806	0	49086806	195	8
3249	2022-09-26 08:36:48.539	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04019675A	350149	350149	0	195	24
3250	2022-09-26 08:36:48.555	5-Angel Rios 	7	CANON INFORMATICO	5053127	245222	245222	0	195	9
3251	2022-09-26 08:36:48.555	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018392	70000	63636	6363	195	12
3252	2022-09-26 08:36:48.571	5-Angel Rios 	9	TASA PORTUARIA	002-007-0039781	2559755	2327050	232705	195	14
3253	2022-09-26 08:36:48.571	5-Angel Rios 	10	CDAP PAGOS	001-025-0178886	7003	6366	636	195	27
3254	2022-09-26 08:36:48.571	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0126696	63994	58176	5817	195	11
3255	2022-09-26 08:36:48.586	5-Angel Rios 	12	VISACION MRE	1117970	490445	490445	0	195	28
3256	2022-09-26 08:36:48.586	5-Angel Rios 	13	CDAP PAGOS	001-024-0168525	10000	9090	909	195	27
3257	2022-09-26 08:36:48.602	5-Angel Rios 	14	MIPYMES MIC	1907356	691023	628202	62820	195	22
3258	2022-09-26 08:36:48.602	5-Angel Rios 	15	APORTE VUE	1907356	73282	66620	6662	195	23
3259	2022-09-26 08:36:48.602	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002103	8678568	7889607	788960	195	16
3260	2022-09-26 08:36:48.618	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	x	0	0	0	195	21
3261	2022-09-26 08:42:11.908	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04019675A	0	0	0	196	2
3262	2022-09-26 08:42:11.915	5-Angel Rios 	2	INDI	22005IC04019675A	14706	14706	0	196	3
3263	2022-09-26 08:42:11.915	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04019675A	2441011	2441011	0	196	5
3264	2022-09-26 08:42:11.915	5-Angel Rios 	4	IRE GENERAL 700	22005IC04019675A	1963472	1963472	0	196	38
3265	2022-09-26 08:42:11.931	5-Angel Rios 	5	I.V.A.	22005IC04019675A	49086806	0	49086806	196	8
3266	2022-09-26 08:42:11.931	5-Angel Rios 	6	TASA INTER. ADUAN.	22005IC04019675A	350149	350149	0	196	24
3267	2022-09-26 08:42:11.946	5-Angel Rios 	7	CANON INFORMATICO	5053127	245222	245222	0	196	9
3268	2022-09-26 08:42:11.946	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018392	70000	63636	6363	196	12
3269	2022-09-26 08:42:11.962	5-Angel Rios 	9	TASA PORTUARIA	002-007-0039781	2559755	2327050	232705	196	14
3270	2022-09-26 08:42:11.962	5-Angel Rios 	10	CDAP PAGOS	001-025-0178886	7003	6366	636	196	27
3271	2022-09-26 08:42:11.978	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0126696	63994	58176	5817	196	11
3272	2022-09-26 08:42:11.978	5-Angel Rios 	12	VISACION MRE	1117970	490445	490445	0	196	28
3273	2022-09-26 08:42:11.993	5-Angel Rios 	13	CDAP PAGOS	001-024-0168525	10000	9090	909	196	27
3274	2022-09-26 08:42:11.993	5-Angel Rios 	14	MIPYMES MIC	1907356	691023	628202	62820	196	22
3275	2022-09-26 08:42:12.009	5-Angel Rios 	15	APORTE VUE	1907356	73282	66620	6662	196	23
3276	2022-09-26 08:42:12.009	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002103	8678568	7889607	788960	196	16
3277	2022-09-26 08:42:12.024	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	x	0	0	0	196	21
3278	2022-09-27 12:44:53.847	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000503N	0	0	0	197	2
3279	2022-09-27 12:44:53.855	5-Angel Rios 	2	INDI	22005IM04000503N	14720	14720	0	197	3
3280	2022-09-27 12:44:53.863	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000503N	57961	57961	0	197	5
3281	2022-09-27 12:44:53.871	5-Angel Rios 	4	IRE GENERAL 700	22005IM04000503N	47500	47500	0	197	38
3282	2022-09-27 12:44:53.878	5-Angel Rios 	5	I.V.A.	22005IM04000503N	1187512	0	1187512	197	8
3283	2022-09-27 12:44:53.892	5-Angel Rios 	6	CANON INFORMATICO	5065967	49044	49044	0	197	9
3284	2022-09-27 12:44:53.897	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-002-0003199	200000	181818	18181	197	13
3285	2022-09-27 12:44:53.904	5-Angel Rios 	8	FOTOCOPIAS	001-002-00118400	90000	81818	8181	197	12
3286	2022-09-27 12:44:53.909	5-Angel Rios 	9	TASA PORTUARIA	002-007-0040466	116337	105760	10576	197	14
3287	2022-09-27 12:44:53.914	5-Angel Rios 	10	CDAP PAGOS	001-025-0180084	7008	6370	637	197	27
3288	2022-09-27 12:44:53.921	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0127889	2908	2643	264	197	11
3289	2022-09-27 12:44:53.926	5-Angel Rios 	12	VISACION CONSUL	03018516/17/18	486000	486000	0	197	15
3290	2022-09-27 12:44:53.936	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002105	3113000	2830000	283000	197	16
3291	2022-09-27 12:44:53.942	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	x	0	0	0	197	21
3292	2022-09-29 11:16:43.084	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04020237G	10342003	10342003	0	198	2
3293	2022-09-29 11:16:43.093	5-Angel Rios 	2	INDI	22002IC04020237G	34331	34331	0	198	3
3294	2022-09-29 11:16:43.099	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04020237G	323188	323188	0	198	5
3295	2022-09-29 11:16:43.105	5-Angel Rios 	4	IRE GENERAL 700	22002IC04020237G	303309	303309	0	198	38
3296	2022-09-29 11:16:43.111	5-Angel Rios 	5	I.V.A.	22002IC04020237G	7582750	0	7582750	198	8
3297	2022-09-29 11:16:43.12	5-Angel Rios 	6	CANON INFORMATICO	5067977	250222	250222	0	198	9
3298	2022-09-29 11:16:43.125	5-Angel Rios 	7	FOTOCOPIAS	004-003-0004654	112000	101818	10181	198	12
3299	2022-09-29 11:16:43.132	5-Angel Rios 	8	TASA PORTUARIA	001-034-0061197	2780694	2527903	252790	198	14
3300	2022-09-29 11:16:43.147	5-Angel Rios 	9	CDAP PAGOS	001-021-0063705	21058	19143	1914	198	27
3301	2022-09-29 11:16:43.153	5-Angel Rios 	10	VISACION MRE	1119456	490445	490445	0	198	28
3302	2022-09-29 11:16:43.158	5-Angel Rios 	11	CDAP PAGOS	001-024-0170232	10000	9090	909	198	27
3303	2022-09-29 11:16:43.165	5-Angel Rios 	12	TASA DINAVISA	562233	196178	178343	17834	198	37
3304	2022-09-29 11:16:43.17	5-Angel Rios 	13	CDAP PAGOS	001-024-0170407	10000	9090	909	198	27
3305	2022-09-29 11:16:43.177	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002106	1540000	1400000	140000	198	16
3306	2022-09-29 11:16:43.185	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	755	250000	250000	0	198	21
3307	2022-10-05 09:45:25.545	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020280H	0	0	0	199	2
3308	2022-10-05 09:45:25.555	5-Angel Rios 	2	INDI	22005IC04020280H	26950	26950	0	199	3
3309	2022-10-05 09:45:25.564	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020280H	3695918	3695918	0	199	5
3310	2022-10-05 09:45:25.572	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020280H	2973167	2973167	0	199	38
3311	2022-10-05 09:45:25.581	5-Angel Rios 	5	I.V.A.	22005IC04020280H	74329156	0	74329156	199	8
3312	2022-10-05 09:45:25.59	5-Angel Rios 	6	CANON INFORMATICO	5088671	245222	245222	0	199	9
3313	2022-10-05 09:45:25.597	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018432	90000	81818	8181	199	12
3314	2022-10-05 09:45:25.604	5-Angel Rios 	8	TASA PORTUARIA	002-007-0042091	3326285	3023895	302389	199	14
3315	2022-10-05 09:45:25.614	5-Angel Rios 	9	CDAP PAGOS	001-025-0183409	7094	6449	644	199	27
3316	2022-10-05 09:45:25.62	5-Angel Rios 	10	SERVICIOS SGD - DEPOSITO ANNP	001-004-0131214	83157	75597	7560	199	28
3317	2022-10-05 09:45:25.625	5-Angel Rios 	11	VISACION CONSUL	03022232/33/34/35/36	1012500	920454	92045	199	27
3318	2022-10-05 09:45:25.63	5-Angel Rios 	12	TASA DINAVISA	x	0	0	0	199	37
3319	2022-10-05 09:45:25.642	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	199	27
3320	2022-10-05 09:45:25.647	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002108	5395410	4904918	490491	199	16
3321	2022-10-05 09:45:25.653	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	199	21
3322	2022-10-05 10:37:19.838	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020283K	1933085	1933085	0	200	2
3323	2022-10-05 10:37:19.846	5-Angel Rios 	2	INDI	22005IC04020283K	14911	14911	0	200	3
3324	2022-10-05 10:37:19.854	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020283K	162579	162579	0	200	5
3325	2022-10-05 10:37:19.862	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020283K	139358	139358	0	200	38
3326	2022-10-05 10:37:19.873	5-Angel Rios 	5	I.V.A.	22005IC04020283K	3483934	0	3483934	200	8
3327	2022-10-05 10:37:19.878	5-Angel Rios 	6	CANON INFORMATICO	5088695	98089	98089	0	200	9
3328	2022-10-05 10:37:19.885	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018431	70000	63636	6363	200	12
3329	2022-10-05 10:37:19.891	5-Angel Rios 	8	TASA PORTUARIA	002-007-0042090	182533	165939	16593	200	14
3330	2022-10-05 10:37:19.897	5-Angel Rios 	9	CDAP PAGOS	001-025-0183410	7094	6449	644	200	27
3331	2022-10-05 10:37:19.903	5-Angel Rios 	10	SERVICIOS SGD - DEPOSITO ANNP	001-004-0131213	4563	4563	0	200	28
3332	2022-10-05 10:37:19.907	5-Angel Rios 	11	VISACION CONSUL	03022237/38	243000	220909	22090	200	27
3333	2022-10-05 10:37:19.912	5-Angel Rios 	12	TASA DINAVISA	x	0	0	0	200	37
3334	2022-10-05 10:37:19.918	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	200	27
3335	2022-10-05 10:37:19.924	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002109	1584000	1440000	144000	200	16
3336	2022-10-05 10:37:19.93	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	200	21
3337	2022-10-05 10:38:20.567	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020280H	0	0	0	201	2
3338	2022-10-05 10:38:20.593	5-Angel Rios 	2	INDI	22005IC04020280H	26950	26950	0	201	3
3339	2022-10-05 10:38:20.624	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020280H	3695918	3695918	0	201	5
3340	2022-10-05 10:38:20.645	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020280H	2973167	2973167	0	201	38
3341	2022-10-05 10:38:20.689	5-Angel Rios 	5	I.V.A.	22005IC04020280H	74329156	0	74329156	201	8
3342	2022-10-05 10:38:20.695	5-Angel Rios 	6	CANON INFORMATICO	5088671	245222	245222	0	201	9
3343	2022-10-05 10:38:20.707	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018432	90000	81818	8181	201	12
3344	2022-10-05 10:38:20.719	5-Angel Rios 	8	TASA PORTUARIA	002-007-0042091	3326285	3023895	302389	201	14
3345	2022-10-05 10:38:20.73	5-Angel Rios 	9	CDAP PAGOS	001-025-0183409	7094	6449	644	201	27
3346	2022-10-05 10:38:20.739	5-Angel Rios 	10	SERVICIOS SGD - DEPOSITO ANNP	001-004-0131214	83157	75597	7560	201	28
3347	2022-10-05 10:38:20.747	5-Angel Rios 	11	VISACION CONSUL	03022232/33/34/35/36	1012500	920454	92045	201	27
3348	2022-10-05 10:38:20.76	5-Angel Rios 	12	TASA DINAVISA	x	0	0	0	201	37
3349	2022-10-05 10:38:20.768	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	201	27
3350	2022-10-05 10:38:20.777	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002108	5395410	4904918	490491	201	16
3351	2022-10-05 10:38:20.785	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	201	21
3352	2022-10-05 11:43:06.142	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04006239P	79166858	79166858	0	202	2
3353	2022-10-05 11:43:06.154	5-Angel Rios 	2	INDI	22032IC04006239P	69368	69368	0	202	3
3354	2022-10-05 11:43:06.159	5-Angel Rios 	3	I.S.C.	22032IC04006239P	4433782	4433782	0	202	4
3355	2022-10-05 11:43:06.165	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04006239P	4447073	4447073	0	202	5
3356	2022-10-05 11:43:06.17	5-Angel Rios 	5	IRE GENERAL 700	22032IC04006239P	3914091	3914091	0	202	38
3357	2022-10-05 11:43:06.175	5-Angel Rios 	6	I.V.A.	22032IC04006239P	97852272	0	97852272	202	8
3358	2022-10-05 11:43:06.181	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04006239P	354708	354708	0	202	35
3359	2022-10-05 11:43:06.186	5-Angel Rios 	8	TASA INT. ANNP	5095159	354707	354707	0	202	36
3360	2022-10-05 11:43:06.191	5-Angel Rios 	9	CANON INFORMATICO	5095049	245222	245222	0	202	9
3361	2022-10-05 11:43:06.197	5-Angel Rios 	10	FOTOCOPIAS	023-002-0001101	48000	43636	4363	202	12
3362	2022-10-05 11:43:06.202	5-Angel Rios 	11	TASA PORTUARIA	001-004-13567	1366333	1242120	124212	202	14
3363	2022-10-05 11:43:06.207	5-Angel Rios 	12	VISACION MRE	1124408	980890	980890	0	202	28
3364	2022-10-05 11:43:06.212	5-Angel Rios 	13	CDAP PAGOS	001-024-0173478	10000	9090	909	202	27
3365	2022-10-05 11:43:06.224	5-Angel Rios 	14	MOPC	1124599	49045	49045	0	202	34
3366	2022-10-05 11:43:06.23	5-Angel Rios 	15	CDAP PAGOS	001-024-0173480	10000	9090	909	202	27
3367	2022-10-05 11:43:06.234	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	6015068	5468243	546824	202	16
3368	2022-10-05 11:43:06.239	5-Angel Rios 	17	GASTO ADMIN.	x	660000	660000	0	202	17
3369	2022-10-06 11:13:25.449	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04006239P	79166858	79166858	0	203	2
3370	2022-10-06 11:13:25.46	5-Angel Rios 	2	INDI	22032IC04006239P	69368	69368	0	203	3
3371	2022-10-06 11:13:25.467	5-Angel Rios 	3	I.S.C.	22032IC04006239P	4433782	4433782	0	203	4
3372	2022-10-06 11:13:25.473	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04006239P	4447073	4447073	0	203	5
3373	2022-10-06 11:13:25.479	5-Angel Rios 	5	IRE GENERAL 700	22032IC04006239P	3914091	3914091	0	203	38
3374	2022-10-06 11:13:25.49	5-Angel Rios 	6	I.V.A.	22032IC04006239P	97852272	0	97852272	203	8
3375	2022-10-06 11:13:25.497	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04006239P	354708	354708	0	203	35
3376	2022-10-06 11:13:25.502	5-Angel Rios 	8	TASA INT. ANNP	5095159	354707	354707	0	203	36
3377	2022-10-06 11:13:25.51	5-Angel Rios 	9	CANON INFORMATICO	5095049	245222	245222	0	203	9
3378	2022-10-06 11:13:25.514	5-Angel Rios 	10	FOTOCOPIAS	023-002-0001101	48000	43636	4363	203	12
3379	2022-10-06 11:13:25.522	5-Angel Rios 	11	TASA PORTUARIA	001-004-13567	1366333	1242120	124212	203	14
3380	2022-10-06 11:13:25.529	5-Angel Rios 	12	VISACION MRE	1124408	980890	980890	0	203	28
3381	2022-10-06 11:13:25.543	5-Angel Rios 	13	CDAP PAGOS	001-024-0173478	10000	9090	909	203	27
3382	2022-10-06 11:13:25.548	5-Angel Rios 	14	MOPC	1124599	49045	49045	0	203	34
3383	2022-10-06 11:13:25.555	5-Angel Rios 	15	CDAP PAGOS	001-024-0173480	10000	9090	909	203	27
3384	2022-10-06 11:13:25.56	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002110	6015068	5468243	546824	203	16
3385	2022-10-06 11:13:25.565	5-Angel Rios 	17	GASTO ADMIN.	001-001-0002111	660000	600000	60000	203	17
3386	2022-10-06 16:51:44.105	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020555M	106030	106030	0	204	2
3387	2022-10-06 16:51:44.11	5-Angel Rios 	2	INDI	22005IC04020555M	26950	26950	0	204	3
3388	2022-10-06 16:51:44.119	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020555M	151615	151615	0	204	5
3389	2022-10-06 16:51:44.123	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020555M	123971	123971	0	204	38
3390	2022-10-06 16:51:44.129	5-Angel Rios 	5	I.V.A.	22005IC04020555M	3099278	0	3099278	204	8
3391	2022-10-06 16:51:44.138	5-Angel Rios 	6	CANON INFORMATICO	5130607	98089	98089	0	204	9
3392	2022-10-06 16:51:44.145	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017355	175000	159090	15909	204	13
3393	2022-10-06 16:51:44.152	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018458	80000	72727	7272	204	12
3394	2022-10-06 16:51:44.158	5-Angel Rios 	9	TASA PORTUARIA	002-007-0043111	191594	174176	17417	204	14
3395	2022-10-06 16:51:44.164	5-Angel Rios 	10	CDAP PAGOS	001-025-0185504	7073	6430	643	204	27
3396	2022-10-06 16:51:44.175	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0133311	4790	4354	435	204	11
3397	2022-10-06 16:51:44.179	5-Angel Rios 	12	VISACION CONSUL	03022651/52/53/54	688500	688500	0	204	15
3398	2022-10-06 16:51:44.192	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	4884000	4440000	444000	204	16
3399	2022-10-06 16:51:44.197	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	204	17
3400	2022-10-07 14:11:43.607	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020555M	106030	106030	0	205	2
3401	2022-10-07 14:11:43.619	5-Angel Rios 	2	INDI	22005IC04020555M	26950	26950	0	205	3
3402	2022-10-07 14:11:43.625	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020555M	151615	151615	0	205	5
3403	2022-10-07 14:11:43.635	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020555M	123971	123971	0	205	38
3404	2022-10-07 14:11:43.642	5-Angel Rios 	5	I.V.A.	22005IC04020555M	3099278	0	3099278	205	8
3405	2022-10-07 14:11:43.649	5-Angel Rios 	6	CANON INFORMATICO	5130607	98089	98089	0	205	9
3406	2022-10-07 14:11:43.656	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017355	175000	159090	15909	205	13
3407	2022-10-07 14:11:43.664	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018458	80000	72727	7272	205	12
3408	2022-10-07 14:11:43.67	5-Angel Rios 	9	TASA PORTUARIA	002-007-0043111	191594	174176	17417	205	14
3409	2022-10-07 14:11:43.675	5-Angel Rios 	10	CDAP PAGOS	001-025-0185504	7073	6430	643	205	27
3410	2022-10-07 14:11:43.68	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0133311	4790	4354	435	205	11
3411	2022-10-07 14:11:43.685	5-Angel Rios 	12	VISACION CONSUL	03022651/52/53/54	688500	688500	0	205	15
3412	2022-10-07 14:11:43.691	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002113	4884000	4440000	444000	205	16
3413	2022-10-07 14:11:43.696	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	205	17
3414	2022-10-07 14:16:34.841	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003111V	0	0	0	206	2
3415	2022-10-07 14:16:34.865	5-Angel Rios 	2	INDI	22018EC01003111V	0	0	0	206	3
3416	2022-10-07 14:16:34.888	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003111V	0	0	0	206	5
3417	2022-10-07 14:16:34.905	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003111V	0	0	0	206	38
3418	2022-10-07 14:16:34.939	5-Angel Rios 	5	I.V.A.	22018EC01003111V	0	0	0	206	8
3419	2022-10-07 14:16:34.951	5-Angel Rios 	6	CANON INFORMATICO	5068787	245222	245222	0	206	9
3420	2022-10-07 14:16:34.962	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	206	13
3421	2022-10-07 14:16:34.971	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009483	50000	45454	4545	206	12
3422	2022-10-07 14:16:34.98	5-Angel Rios 	9	TASA UIP CO	002-002-0000720	98089	89171	8917	206	30
3423	2022-10-07 14:16:34.988	5-Angel Rios 	10	TASA MIC CO	1950634	102489	93171	9317	206	29
3424	2022-10-07 14:16:34.995	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002107	3451529	3137753	313775	206	16
3425	2022-10-07 14:16:35.022	5-Angel Rios 	12	GASTO ADMIN.	x	0	0	0	206	17
3426	2022-10-10 11:05:39.76	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04012492L	87837558	87837558	0	207	2
3427	2022-10-10 11:05:39.768	5-Angel Rios 	2	INDI	22030IC04012492L	44799	44799	0	207	3
3428	2022-10-10 11:05:39.775	5-Angel Rios 	3	I.S.C.	22030IC04012492L	1561730	1561730	0	207	4
3429	2022-10-10 11:05:39.782	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04012492L	2671036	2671036	0	207	5
3430	2022-10-10 11:05:39.799	5-Angel Rios 	5	IRE GENERAL 700	22030IC04012492L	2507850	2507850	0	207	38
3431	2022-10-10 11:05:39.806	5-Angel Rios 	6	I.V.A.	22030IC04012492L	62696236	0	62696236	207	8
3432	2022-10-10 11:05:39.811	5-Angel Rios 	7	TASA INT. ADUANERA 	22030IC04012492L	353645	353645	0	207	35
3433	2022-10-10 11:05:39.816	5-Angel Rios 	8	CANON INFORMATICO	5168548	245222	245222	0	207	9
3434	2022-10-10 11:05:39.824	5-Angel Rios 	9	FOTOCOPIAS	020-002-0002067	50000	45454	4545	207	12
3435	2022-10-10 11:05:39.829	5-Angel Rios 	10	TASA PORTUARIA	001-004-18271	919462	835874	83587	207	14
3436	2022-10-10 11:05:39.835	5-Angel Rios 	11	TASA MIC LPI LAMPARAS	1127829	686623	686623	0	207	44
3437	2022-10-10 11:05:39.842	5-Angel Rios 	12	CDAP PAGOS	001-024-0175766	10000	9090	909	207	27
3438	2022-10-10 11:05:39.847	5-Angel Rios 	13	RENV. IMPOR. LAMPA	1958012	691023	691023	0	207	44
3439	2022-10-10 11:05:39.854	5-Angel Rios 	14	VISACION MRE	x	490445	490445	0	207	28
3440	2022-10-10 11:05:39.859	5-Angel Rios 	15	CDAP PAGOS	x	10000	9090	909	207	27
3441	2022-10-10 11:05:39.865	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002114	4842884	4402621	440262	207	16
3442	2022-10-10 11:05:39.871	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	001-001-0002115	660000	600000	60000	207	21
3443	2022-10-10 11:05:39.875	5-Angel Rios 	18	GASTO ADMIN.	0000760	1500000	1500000	0	207	17
3444	2022-10-10 11:59:51.426	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020815L	0	0	0	208	2
3445	2022-10-10 11:59:51.432	5-Angel Rios 	2	INDI	22005IC04020815L	26950	26950	0	208	3
3446	2022-10-10 11:59:51.437	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020815L	3103319	3103319	0	208	5
3447	2022-10-10 11:59:51.454	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020815L	2496717	2496717	0	208	38
3448	2022-10-10 11:59:51.458	5-Angel Rios 	5	I.V.A.	22005IC04020815L	62417919	0	62417919	208	8
3449	2022-10-10 11:59:51.463	5-Angel Rios 	6	CANON INFORMATICO	5168393	245222	245222	0	208	9
3450	2022-10-10 11:59:51.47	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018475	80000	72727	7272	208	12
3451	2022-10-10 11:59:51.476	5-Angel Rios 	8	TASA PORTUARIA	002-007-0043780	2960520	2691381	269138	208	14
3452	2022-10-10 11:59:51.48	5-Angel Rios 	9	CDAP PAGOS	001-025-0186788	7072	6429	642	208	27
3453	2022-10-10 11:59:51.485	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0134591	74013	67284	6728	208	11
3454	2022-10-10 11:59:51.49	5-Angel Rios 	11	VISACION CONSUL	03026219/21/23/24	810000	810000	0	208	15
3455	2022-10-10 11:59:51.496	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002116	5062962	4602692	460269	208	16
3456	2022-10-10 11:59:51.504	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	208	17
3457	2022-10-10 12:32:40.472	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000530N	527859	527859	0	209	2
3458	2022-10-10 12:32:40.478	5-Angel Rios 	2	INDI	22005IM04000530N	14852	14852	0	209	3
3459	2022-10-10 12:32:40.486	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000530N	71716	71716	0	209	5
3460	2022-10-10 12:32:40.493	5-Angel Rios 	4	IRE GENERAL 700	22005IM04000530N	60679	60679	0	209	38
3461	2022-10-10 12:32:40.498	5-Angel Rios 	5	I.V.A.	22005IM04000530N	1516995	0	1516995	209	8
3462	2022-10-10 12:32:40.503	5-Angel Rios 	6	CANON INFORMATICO	5168362	49044	49044	0	209	9
3463	2022-10-10 12:32:40.508	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018474	170000	154545	15454	209	12
3464	2022-10-10 12:32:40.515	5-Angel Rios 	8	TASA PORTUARIA	002-007-0043775	81969	74517	7451	209	14
3465	2022-10-10 12:32:40.52	5-Angel Rios 	9	CDAP PAGOS	001-025-0186787	7072	6429	642	209	27
3466	2022-10-10 12:32:40.525	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0134586	2049	1862	186	209	11
3467	2022-10-10 12:32:40.534	5-Angel Rios 	11	VISACION CONSUL	03026220/22	243000	243000	0	209	15
3468	2022-10-10 12:32:40.541	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002117	1738000	1580000	158000	209	16
3469	2022-10-10 12:32:40.545	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	209	17
3470	2022-10-10 13:07:38.261	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02001944F	0	0	0	210	2
3471	2022-10-10 13:07:38.268	5-Angel Rios 	2	INDI	22005IT02001944F	0	0	0	210	3
3472	2022-10-10 13:07:38.275	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02001944F	0	0	0	210	5
3473	2022-10-10 13:07:38.281	5-Angel Rios 	4	IRE GENERAL 700	22005IT02001944F	0	0	0	210	38
3474	2022-10-10 13:07:38.287	5-Angel Rios 	5	TASA INTER. ADUANERA	22005IT02001944F	355224	0	355224	210	8
3475	2022-10-10 13:07:38.291	5-Angel Rios 	6	CANON INFORMATICO	x	0	0	0	210	9
3476	2022-10-10 13:07:38.296	5-Angel Rios 	7	FOTOCOPIAS	001-002-0018478	70000	63636	6363	210	12
3477	2022-10-10 13:07:38.306	5-Angel Rios 	8	TASA PORTUARIA	002-007-0044143	1448357	1316688	131668	210	14
3478	2022-10-10 13:07:38.311	5-Angel Rios 	9	CDAP PAGOS	001-025-0187475	7104	6458	645	210	27
3479	2022-10-10 13:07:38.315	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	001-004-0135282	36209	32917	3291	210	11
3480	2022-10-10 13:07:38.322	5-Angel Rios 	11	VISACION CONSUL	x	0	0	0	210	15
3481	2022-10-10 13:07:38.333	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002119	6715174	6104703	610470	210	16
3482	2022-10-10 13:07:38.34	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	210	17
3483	2022-10-10 13:54:54.38	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003167J	0	0	0	211	2
3484	2022-10-10 13:54:54.385	5-Angel Rios 	2	INDI	22018EC01003167J	0	0	0	211	3
3485	2022-10-10 13:54:54.39	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003167J	0	0	0	211	5
3486	2022-10-10 13:54:54.399	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003167J	0	0	0	211	38
3487	2022-10-10 13:54:54.407	5-Angel Rios 	5	I.V.A.	22018EC01003167J	0	0	0	211	8
3488	2022-10-10 13:54:54.413	5-Angel Rios 	6	CANON INFORMATICO	5122946	245222	245222	0	211	9
3489	2022-10-10 13:54:54.418	5-Angel Rios 	7	FOTOCOPIAS	001-001-0009519	50000	45454	4545	211	12
3490	2022-10-10 13:54:54.423	5-Angel Rios 	8	TASA PORTUARIA	x	0	0	0	211	14
3491	2022-10-10 13:54:54.43	5-Angel Rios 	9	TASA MIC CO	1957266	102489	93171	9317	211	27
3492	2022-10-10 13:54:54.436	5-Angel Rios 	10	TASA UIP CO	002-002-0000888	98089	89171	8917	211	11
3493	2022-10-10 13:54:54.461	5-Angel Rios 	11	VISACION CONSUL	x	0	0	0	211	15
3494	2022-10-10 13:54:54.468	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002122	3270297	2972997	297299	211	16
3495	2022-10-10 13:54:54.477	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	211	17
3496	2022-10-10 15:58:53.549	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018451Y	0	0	0	212	2
3497	2022-10-10 15:58:53.557	5-Angel Rios 	2	INDI	22005IC04018451Y	34331	34331	0	212	3
3498	2022-10-10 15:58:53.562	5-Angel Rios 	3	TASA INTER. ADUAN.	22005IC04018451Y	345297	345297	0	212	24
3499	2022-10-10 15:58:53.566	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018451Y	1207139	1207139	0	212	5
3500	2022-10-10 15:58:53.574	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018451Y	972639	972639	0	212	38
3501	2022-10-10 15:58:53.578	5-Angel Rios 	6	I.V.A.	22005IC04018451Y	24315978	0	24315978	212	8
3502	2022-10-10 15:58:53.583	5-Angel Rios 	7	CANON INFORMATICO	4889560	245222	245222	0	212	9
3503	2022-10-10 15:58:53.588	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	212	13
3504	2022-10-10 15:58:53.593	5-Angel Rios 	9	FOTOCOPIAS	001-001-0091101	70000	63636	6363	212	12
3505	2022-10-10 15:58:53.598	5-Angel Rios 	10	TASA PORTUARIA	002-007-0035496	1416747	1287951	128795	212	14
3506	2022-10-10 15:58:53.603	5-Angel Rios 	11	CDAP PAGOS	001-025-0169902	6905	6277	627	212	27
3507	2022-10-10 15:58:53.609	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0117708	35419	32199	3219	212	11
3508	2022-10-10 15:58:53.619	5-Angel Rios 	13	VISACION MRE	1106158	490445	490445	0	212	28
3509	2022-10-10 15:58:53.625	5-Angel Rios 	14	CDAP PAGOS	001-024-0160229	10000	9090	909	212	27
3510	2022-10-10 15:58:53.63	5-Angel Rios 	15	MIPYMES MIC	1921915	691023	628202	62820	212	22
3511	2022-10-10 15:58:53.637	5-Angel Rios 	16	APORTE VUE	1921916	73199	66544	6654	212	23
3512	2022-10-10 15:58:53.65	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002091	5914853	5377139	537713	212	16
3513	2022-10-10 15:58:53.656	5-Angel Rios 	18	REPOSICION DE GASTOS ADM	x	0	0	0	212	21
3514	2022-10-10 16:03:18.782	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01002739N	0	0	0	213	2
3515	2022-10-10 16:03:18.789	5-Angel Rios 	2	INDI	22018EC01002739N	0	0	0	213	3
3516	2022-10-10 16:03:18.797	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01002739N	0	0	0	213	5
3517	2022-10-10 16:03:18.801	5-Angel Rios 	4	IRE GENERAL 700	22018EC01002739N	0	0	0	213	38
3518	2022-10-10 16:03:18.808	5-Angel Rios 	5	I.V.A.	22018EC01002739N	0	0	0	213	8
3519	2022-10-10 16:03:18.813	5-Angel Rios 	6	CANON INFORMATICO	4736242	245222	245222	0	213	9
3520	2022-10-10 16:03:18.817	5-Angel Rios 	7	FOTOCOPIAS	001-001-0009302	50000	45454	4545	213	12
3521	2022-10-10 16:03:18.824	5-Angel Rios 	8	TASA UIP CO	002-002-0000002	98089	89171	8917	213	30
3522	2022-10-10 16:03:18.832	5-Angel Rios 	9	TASA MIC CO	1926259	98089	89171	8917	213	29
3523	2022-10-10 16:03:18.841	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002081	3029000	2753636	275363	213	16
3524	2022-10-10 16:03:18.846	5-Angel Rios 	11	PROG PRODUCCION	001-001-0002080	2424295	2424295	0	213	17
3525	2022-10-11 11:27:21.254	5-Angel Rios 	1	DERECHO ADUANERO	22030ic04000355@	4457230	4457230	0	214	2
3526	2022-10-11 11:27:21.264	5-Angel Rios 	2	INDI	22030ic04000355@	68662	68662	0	214	3
3527	2022-10-11 11:27:21.27	5-Angel Rios 	3	SERVICIO DE VALORACION	22030ic04000355@	371436	371436	0	214	5
3528	2022-10-11 11:27:21.277	5-Angel Rios 	4	IRE GENERAL 700	22030ic04000355@	320662	320662	0	214	38
3529	2022-10-11 11:27:21.283	5-Angel Rios 	5	I.V.A. TURISMO	22030ic04000355@	1202481	0	1202481	214	8
3530	2022-10-11 11:27:21.293	5-Angel Rios 	6	CANON INFORMATICO	x	245222	245222	0	214	9
3531	2022-10-11 11:27:21.304	5-Angel Rios 	7	FOTOCOPIAS	x	70000	63636	6363	214	12
3532	2022-10-11 11:27:21.309	5-Angel Rios 	8	TASA PORTUARIA	x	1100000	1000000	100000	214	14
3533	2022-10-11 11:27:21.316	5-Angel Rios 	9	CDAP PAGOS	x	0	0	0	214	27
3534	2022-10-11 11:27:21.321	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	214	11
3535	2022-10-11 11:27:21.326	5-Angel Rios 	11	VISACION CONSUL	x	990890	990890	0	214	15
3536	2022-10-11 11:27:21.333	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	x	1478727	1344297	134429	214	16
3537	2022-10-11 11:27:21.34	5-Angel Rios 	13	GASTO ADMIN.	x	660000	660000	0	214	17
3538	2022-10-11 11:33:20.52	5-Angel Rios 	1	DERECHO ADUANERO	22030ic04000355@	4457230	4457230	0	215	2
3539	2022-10-11 11:33:20.528	5-Angel Rios 	2	INDI	22030ic04000355@	68662	68662	0	215	3
3540	2022-10-11 11:33:20.539	5-Angel Rios 	3	SERVICIO DE VALORACION	22030ic04000355@	371436	371436	0	215	5
3541	2022-10-11 11:33:20.544	5-Angel Rios 	4	IRE GENERAL 700	22030ic04000355@	320662	320662	0	215	38
3542	2022-10-11 11:33:20.549	5-Angel Rios 	5	I.V.A. TURISMO	22030ic04000355@	1202481	0	1202481	215	8
3543	2022-10-11 11:33:20.554	5-Angel Rios 	6	CANON INFORMATICO	x	245222	245222	0	215	9
3544	2022-10-11 11:33:20.558	5-Angel Rios 	7	FOTOCOPIAS	x	70000	63636	6363	215	12
3545	2022-10-11 11:33:20.567	5-Angel Rios 	8	TASA PORTUARIA	x	1850000	1681818	168181	215	14
3546	2022-10-11 11:33:20.573	5-Angel Rios 	9	CDAP PAGOS	x	0	0	0	215	27
3547	2022-10-11 11:33:20.58	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	215	11
3548	2022-10-11 11:33:20.584	5-Angel Rios 	11	VISACION CONSUL	x	990890	990890	0	215	15
3549	2022-10-11 11:33:20.59	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	x	1478727	1344297	134429	215	16
3550	2022-10-11 11:33:20.596	5-Angel Rios 	13	GASTO ADMIN.	x	1000000	1000000	0	215	17
3551	2022-10-11 12:08:38.689	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04000355@	4450164	4450164	0	216	2
3552	2022-10-11 12:08:38.694	5-Angel Rios 	2	INDI	22030IC04000355@	68662	68662	0	216	3
3553	2022-10-11 12:08:38.701	5-Angel Rios 	3	SERVICIO DE VALORACION	22030IC04000355@	370847	370847	0	216	5
3554	2022-10-11 12:08:38.706	5-Angel Rios 	4	IRE GENERAL 700	22030IC04000355@	320160	320160	0	216	38
3555	2022-10-11 12:08:38.718	5-Angel Rios 	5	I.V.A.	22030IC04000355@	8003997	0	8003997	216	8
3556	2022-10-11 12:08:38.723	5-Angel Rios 	6	CANON INFORMATICO	x	245222	245222	0	216	9
3557	2022-10-11 12:08:38.727	5-Angel Rios 	7	FOTOCOPIAS	x	70000	63636	6363	216	12
3558	2022-10-11 12:08:38.733	5-Angel Rios 	8	TASA PORTUARIA	x	1850000	1681818	168181	216	14
3559	2022-10-11 12:08:38.741	5-Angel Rios 	9	CDAP PAGOS	x	0	0	0	216	27
3560	2022-10-11 12:08:38.746	5-Angel Rios 	10	SERVICIOS SOFIA / ANNP	x	0	0	0	216	11
3561	2022-10-11 12:08:38.755	5-Angel Rios 	11	VISACION CONSUL	x	990890	990890	0	216	15
3562	2022-10-11 12:08:38.76	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	x	1478727	1344297	134429	216	16
3563	2022-10-11 12:08:38.766	5-Angel Rios 	13	GASTO ADMIN.	x	1000000	1000000	0	216	17
3564	2022-10-14 10:58:47.374	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020978V	0	0	0	217	2
3565	2022-10-14 10:58:47.385	5-Angel Rios 	2	INDI	22005IC04020978V	26949	26949	0	217	3
3566	2022-10-14 10:58:47.394	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020978V	1243989	1243989	0	217	5
3567	2022-10-14 10:58:47.401	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020978V	1001814	1001814	0	217	38
5972	2023-02-16 14:19:17.66	5-Angel Rios 	13	VISACION MRE	x	0	0	0	366	28
3568	2022-10-14 10:58:47.409	5-Angel Rios 	5	I.V.A.	22005IC04020978V	25045371	0	25045371	217	8
3569	2022-10-14 10:58:47.417	5-Angel Rios 	6	CANON INFORMATICO	5168018	245222	245222	0	217	9
3570	2022-10-14 10:58:47.422	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017479	175000	159090	15909	217	13
3571	2022-10-14 10:58:47.434	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018488	70000	63636	6363	217	12
3572	2022-10-14 10:58:47.441	5-Angel Rios 	9	TASA PORTUARIA	002-007-0044401	1445828	1314389	131438	217	14
3573	2022-10-14 10:58:47.446	5-Angel Rios 	10	CDAP PAGOS	001-025-0188044	7120	6472	647	217	27
3574	2022-10-14 10:58:47.453	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0135845	36146	32860	3286	217	11
3575	2022-10-14 10:58:47.459	5-Angel Rios 	12	VISACION MRE	1128028	490445	490445	0	217	28
3576	2022-10-14 10:58:47.464	5-Angel Rios 	13	CDAP PAGOS	001-024-0175996	10000	9090	909	217	27
3577	2022-10-14 10:58:47.471	5-Angel Rios 	14	VISACION CONSUL	03025117/118	243000	243000	0	217	15
3578	2022-10-14 10:58:47.476	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002124	3777388	3433989	343398	217	16
3579	2022-10-14 10:58:47.483	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	766	5000000	5000000	0	217	21
3580	2022-10-14 11:10:08.718	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003244F	0	0	0	218	2
3581	2022-10-14 11:10:08.739	5-Angel Rios 	2	INDI	22018EC01003244F	0	0	0	218	3
3582	2022-10-14 11:10:08.751	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003244F	0	0	0	218	5
3583	2022-10-14 11:10:08.758	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003244F	0	0	0	218	38
3584	2022-10-14 11:10:08.766	5-Angel Rios 	5	I.V.A.	22018EC01003244F	0	0	0	218	8
3585	2022-10-14 11:10:08.775	5-Angel Rios 	6	CANON INFORMATICO	5189308	245222	245222	0	218	9
3586	2022-10-14 11:10:08.783	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	218	13
3587	2022-10-14 11:10:08.792	5-Angel Rios 	8	FOTOCOPIAS	001-001-0000240	50000	45454	4545	218	12
3588	2022-10-14 11:10:08.798	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	218	14
3589	2022-10-14 11:10:08.807	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	218	27
3590	2022-10-14 11:10:08.818	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	218	11
3591	2022-10-14 11:10:08.836	5-Angel Rios 	12	TASA MIC CO	1961940	102489	102489	0	218	28
3592	2022-10-14 11:10:08.858	5-Angel Rios 	13	TASA UIP CO 	002-002-0001078	98089	89171	8917	218	27
3593	2022-10-14 11:10:08.868	5-Angel Rios 	14	VISACION CONSUL	x	0	0	0	218	15
3594	2022-10-14 11:10:08.876	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002125	2898839	2635308	263530	218	16
3595	2022-10-14 11:10:08.882	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	218	21
3596	2022-10-14 15:44:01.439	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021313F	7781294	7781294	0	219	2
3597	2022-10-14 15:44:01.444	5-Angel Rios 	2	INDI	22005IC04021313F	39549	39549	0	219	3
3598	2022-10-14 15:44:01.452	5-Angel Rios 	3	I.S.C.	22005IC04021313F	13660	13660	0	219	4
3599	2022-10-14 15:44:01.459	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04021313F	1856363	1856363	0	219	5
3600	2022-10-14 15:44:01.466	5-Angel Rios 	5	IRE GENERAL 700	22005IC04021313F	1526118	1526118	0	219	38
3601	2022-10-14 15:44:01.473	5-Angel Rios 	6	I.V.A.	22005IC04021313F	38152858	0	38152858	219	8
3602	2022-10-14 15:44:01.478	5-Angel Rios 	7	CANON INFORMATICO	5204971	245222	245222	0	219	9
3603	2022-10-14 15:44:01.485	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017515	175000	159090	15909	219	13
3604	2022-10-14 15:44:01.49	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018494	350000	318181	31818	219	12
3605	2022-10-14 15:44:01.496	5-Angel Rios 	10	TASA PORTUARIA	002-007-0045364	1852784	1684349	168434	219	14
3606	2022-10-14 15:44:01.503	5-Angel Rios 	11	CDAP PAGOS	001-025-0189911	7141	6491	649	219	27
3607	2022-10-14 15:44:01.51	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0137713	46320	42109	4210	219	11
3608	2022-10-14 15:44:01.516	5-Angel Rios 	13	VISACION CONSUL	3045953/54/55/56	648000	648000	0	219	15
3609	2022-10-14 15:44:01.523	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002126	5738420	5216745	521674	219	16
3610	2022-10-14 15:44:01.529	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	219	17
3611	2022-10-14 15:55:13.861	5-Angel Rios 	1	DERECHO ADUANERO	22002IM04009915D	482262	482262	0	220	2
3612	2022-10-14 15:55:13.868	5-Angel Rios 	2	INDI	22002IM04009915D	0	0	0	220	3
3613	2022-10-14 15:55:13.874	5-Angel Rios 	3	I.S.C.	22002IM04009915D	0	0	0	220	4
3614	2022-10-14 15:55:13.881	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IM04009915D	91763	91763	0	220	5
3615	2022-10-14 15:55:13.892	5-Angel Rios 	5	IRE GENERAL 700	22002IM04009915D	76133	76133	0	220	38
3616	2022-10-14 15:55:13.898	5-Angel Rios 	6	I.V.A.	22002IM04009915D	1903328	0	1903328	220	8
3617	2022-10-14 15:55:13.906	5-Angel Rios 	7	CANON INFORMATICO	5173287	54044	54044	0	220	9
3618	2022-10-14 15:55:13.911	5-Angel Rios 	8	RETIRO DE GUIA DHL 	017-010-0023287	450000	409090	40909	220	13
3619	2022-10-14 15:55:13.913	5-Angel Rios 	9	FOTOCOPIAS	x	0	0	0	220	12
3620	2022-10-14 15:55:13.923	5-Angel Rios 	10	TASA PORTUARIA	001-034-0063502	795075	722795	72279	220	14
3621	2022-10-14 15:55:13.929	5-Angel Rios 	11	CDAP PAGOS	001-021-0065862	21386	19441	1944	220	27
3622	2022-10-14 15:55:13.936	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	220	11
3623	2022-10-14 15:55:13.941	5-Angel Rios 	13	VISACION CONSUL	x	0	0	0	220	15
3624	2022-10-14 15:55:13.951	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002127	2773120	2521018	252101	220	16
3625	2022-10-14 15:55:13.957	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	220	17
3626	2022-10-17 08:43:24.024	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04012801P	107388180	107388180	0	221	2
3627	2022-10-17 08:43:24.031	5-Angel Rios 	2	INDI	22030IC04012801P	34331	34331	0	221	3
3628	2022-10-17 08:43:24.038	5-Angel Rios 	3	SERVICIO DE VALORACION	22030IC04012801P	2983005	2983005	0	221	5
3629	2022-10-17 08:43:24.046	5-Angel Rios 	4	IRE GENERAL 700	22030IC04012801P	2829988	2829988	0	221	38
3630	2022-10-17 08:43:24.056	5-Angel Rios 	5	I.V.A.	22030IC04012801P	70749696	0	70749696	221	8
3631	2022-10-17 08:43:24.063	5-Angel Rios 	6	TASA INT. ADUANERA 	22030IC04012801P	357096	357096	0	221	35
3632	2022-10-17 08:43:24.069	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	221	9
3633	2022-10-17 08:43:24.074	5-Angel Rios 	8	FOTOCOPIAS	001-001-0055763	30000	27272	2727	221	12
3634	2022-10-17 08:43:24.079	5-Angel Rios 	9	TASA PORTUARIA	001-004-18654	4602958	4184507	418450	221	14
3635	2022-10-17 08:43:24.084	5-Angel Rios 	10	VISACION MRE	1132779	980890	980890	0	221	28
3636	2022-10-17 08:43:24.089	5-Angel Rios 	11	CDAP PAGOS	001-024-0179457	10000	9090	909	221	27
3637	2022-10-17 08:43:24.097	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002128	5048783	4589802	458980	221	16
3638	2022-10-17 08:43:24.101	5-Angel Rios 	13	GASTO ADMIN.	001-001-0002130	660000	660000	0	221	17
3639	2022-10-17 09:35:27.336	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04012801P	107388180	107388180	0	222	2
3640	2022-10-17 09:35:27.362	5-Angel Rios 	2	INDI	22030IC04012801P	34331	34331	0	222	3
3641	2022-10-17 09:35:27.39	5-Angel Rios 	3	SERVICIO DE VALORACION	22030IC04012801P	2983005	2983005	0	222	5
3642	2022-10-17 09:35:27.428	5-Angel Rios 	4	IRE GENERAL 700	22030IC04012801P	2829988	2829988	0	222	38
3643	2022-10-17 09:35:27.472	5-Angel Rios 	5	I.V.A.	22030IC04012801P	70749696	0	70749696	222	8
3644	2022-10-17 09:35:27.5	5-Angel Rios 	6	TASA INT. ADUANERA 	22030IC04012801P	357096	357096	0	222	35
3645	2022-10-17 09:35:27.522	5-Angel Rios 	7	CANON INFORMATICO	001-020-0470768	245222	245222	0	222	9
3646	2022-10-17 09:35:27.543	5-Angel Rios 	8	FOTOCOPIAS	001-001-0055763	30000	27272	2727	222	12
3647	2022-10-17 09:35:27.553	5-Angel Rios 	9	TASA PORTUARIA	001-004-18654	4477423	4070384	407038	222	14
3648	2022-10-17 09:35:27.561	5-Angel Rios 	10	VISACION MRE	1132779	980890	980890	0	222	28
3649	2022-10-17 09:35:27.57	5-Angel Rios 	11	CDAP PAGOS	001-024-0179457	10000	9090	909	222	27
3650	2022-10-17 09:35:27.582	5-Angel Rios 	12	HON. DESP. S/ LEY 220/93	001-001-0002128	5048783	4589802	458980	222	16
3651	2022-10-17 09:35:27.59	5-Angel Rios 	13	GASTO ADMIN.	001-001-0002130	660000	660000	0	222	17
3652	2022-10-17 10:38:32.737	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	223	2
3653	2022-10-17 10:38:32.743	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	223	3
3654	2022-10-17 10:38:32.763	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	223	5
3655	2022-10-17 10:38:32.771	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	223	38
3656	2022-10-17 10:38:32.778	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	223	8
3657	2022-10-17 10:38:32.784	5-Angel Rios 	6	TASA INT. ADUANERA 	22002IC04021343F	0	0	0	223	35
3658	2022-10-17 10:38:32.791	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	223	9
3659	2022-10-17 10:38:32.798	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	223	12
3660	2022-10-17 10:38:32.804	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	223	14
3661	2022-10-17 10:38:32.811	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	223	27
3662	2022-10-17 10:38:32.82	5-Angel Rios 	11	VISACION MRE	x	0	0	0	223	28
3663	2022-10-17 10:38:32.826	5-Angel Rios 	12	CDAP PAGOS	x	0	0	0	223	27
3664	2022-10-17 10:38:32.842	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2602870	2366245	236624	223	16
3665	2022-10-17 10:38:32.853	5-Angel Rios 	14	GASTO ADMIN.	x	816000	816000	0	223	17
3666	2022-10-17 15:49:59.556	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	224	2
3667	2022-10-17 15:49:59.56	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	224	3
3668	2022-10-17 15:49:59.566	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	224	5
3669	2022-10-17 15:49:59.569	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	224	38
3670	2022-10-17 15:49:59.579	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	224	8
3671	2022-10-17 15:49:59.583	5-Angel Rios 	6	TASA INT. ADUANERA 	22002IC04021343F	0	0	0	224	35
3672	2022-10-17 15:49:59.586	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	224	9
3673	2022-10-17 15:49:59.594	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	224	12
3674	2022-10-17 15:49:59.603	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	224	14
3675	2022-10-17 15:49:59.613	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	224	27
3676	2022-10-17 15:49:59.616	5-Angel Rios 	11	TASA DINAVISA 	567998	196178	196178	0	224	28
3677	2022-10-17 15:49:59.624	5-Angel Rios 	12	CDAP PAGOS 	001-024-0177385	10000	9090	909	224	27
3678	2022-10-17 15:49:59.629	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2602870	2366245	236624	224	16
3679	2022-10-17 15:49:59.633	5-Angel Rios 	14	GASTO ADMIN.	x	816000	816000	0	224	17
3680	2022-10-17 15:57:47.711	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	225	2
3681	2022-10-17 15:57:47.718	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	225	3
3682	2022-10-17 15:57:47.725	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	225	5
3683	2022-10-17 15:57:47.73	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	225	38
3684	2022-10-17 15:57:47.734	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	225	8
3685	2022-10-17 15:57:47.739	5-Angel Rios 	6	TASA INT. ADUANERA 	22002IC04021343F	0	0	0	225	35
3686	2022-10-17 15:57:47.745	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	225	9
3687	2022-10-17 15:57:47.749	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	225	12
3688	2022-10-17 15:57:47.756	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	225	14
3689	2022-10-17 15:57:47.761	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	225	27
3690	2022-10-17 15:57:47.766	5-Angel Rios 	11	TASA DINAVISA 	567998	196178	196178	0	225	28
3691	2022-10-17 15:57:47.772	5-Angel Rios 	12	CDAP PAGOS 	001-024-0177385	10000	9090	909	225	27
3692	2022-10-17 15:57:47.784	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	225	16
3693	2022-10-17 15:57:47.794	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	225	17
3694	2022-10-17 15:59:23.139	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	226	2
3695	2022-10-17 15:59:23.145	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	226	3
3696	2022-10-17 15:59:23.15	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	226	5
3697	2022-10-17 15:59:23.156	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	226	38
3698	2022-10-17 15:59:23.16	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	226	8
3699	2022-10-17 15:59:23.166	5-Angel Rios 	6	TASA INT. ADUANERA 	22002IC04021343F	0	0	0	226	35
3700	2022-10-17 15:59:23.173	5-Angel Rios 	7	CANON INFORMATICO	22002IC04021343F	245222	245222	0	226	9
3701	2022-10-17 15:59:23.176	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	226	12
3702	2022-10-17 15:59:23.182	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	226	14
3703	2022-10-17 15:59:23.189	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	226	27
3704	2022-10-17 15:59:23.194	5-Angel Rios 	11	TASA DINAVISA 	567998	196178	196178	0	226	28
3705	2022-10-17 15:59:23.198	5-Angel Rios 	12	CDAP PAGOS 	001-024-0177385	10000	9090	909	226	27
3706	2022-10-17 15:59:23.206	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	226	16
3707	2022-10-17 15:59:23.211	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	226	17
3708	2022-10-18 09:56:11.158	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021310C	5330418	5330418	0	227	2
3709	2022-10-18 09:56:11.164	5-Angel Rios 	2	INDI	22005IC04021310C	39269	39269	0	227	3
3710	2022-10-18 09:56:11.171	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021310C	1555331	1555331	0	227	5
3711	2022-10-18 09:56:11.175	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021310C	1274207	1274207	0	227	38
3712	2022-10-18 09:56:11.184	5-Angel Rios 	5	I.V.A.	22005IC04021310C	31855239	0	31855239	227	8
3713	2022-10-18 09:56:11.192	5-Angel Rios 	6	CANON INFORMATICO	5204967	245222	245222	0	227	9
3714	2022-10-18 09:56:11.197	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017516	175000	159090	15909	227	13
3715	2022-10-18 09:56:11.205	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018495	280000	254545	25454	227	12
3716	2022-10-18 09:56:11.213	5-Angel Rios 	9	TASA PORTUARIA	002-007-0045363	1633022	1484565	148456	227	14
3717	2022-10-18 09:56:11.222	5-Angel Rios 	10	CDAP PAGOS	001-025-0189910	7141	6491	649	227	27
3718	2022-10-18 09:56:11.227	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0137712	40826	37114	3711	227	11
3719	2022-10-18 09:56:11.232	5-Angel Rios 	12	VISACION CONSUL	03045558/59/60/61/62	1012500	1012500	0	227	15
3720	2022-10-18 09:56:11.236	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002132	5569541	5063219	506321	227	16
3721	2022-10-18 09:56:11.246	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	227	17
3722	2022-10-18 10:24:53.391	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021442X	3359	3359	0	228	2
3723	2022-10-18 10:24:53.403	5-Angel Rios 	2	INDI	22005IC04021442X	26951	26951	0	228	3
3724	2022-10-18 10:24:53.409	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021442X	2045516	2045516	0	228	5
3725	2022-10-18 10:24:53.417	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021442X	1646255	1646255	0	228	38
3726	2022-10-18 10:24:53.421	5-Angel Rios 	5	I.V.A.	22005IC04021442X	41156397	0	41156397	228	8
3727	2022-10-18 10:24:53.429	5-Angel Rios 	6	CANON INFORMATICO	5215787	245222	245222	0	228	9
3728	2022-10-18 10:24:53.444	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	228	13
3729	2022-10-18 10:24:53.457	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018499	90000	81818	8181	228	12
3730	2022-10-18 10:24:53.463	5-Angel Rios 	9	TASA PORTUARIA	002-007-0045884	1990129	1809208	180920	228	14
3731	2022-10-18 10:24:53.472	5-Angel Rios 	10	CDAP PAGOS	001-025-0190973	7159	6508	650	228	27
3732	2022-10-18 10:24:53.477	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0138782	49753	45230	4523	228	11
3733	2022-10-18 10:24:53.49	5-Angel Rios 	12	VISACION CONSUL	3048645/46/47/48	810000	810000	0	228	15
3734	2022-10-18 10:24:53.497	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002133	4337534	3943212	394321	228	16
3735	2022-10-18 10:24:53.506	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	228	17
3736	2022-10-18 10:46:08.887	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02001996M	0	0	0	229	2
3737	2022-10-18 10:46:08.895	5-Angel Rios 	2	INDI	22005IT02001996M	0	0	0	229	3
3738	2022-10-18 10:46:08.904	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02001996M	0	0	0	229	5
3739	2022-10-18 10:46:08.91	5-Angel Rios 	4	TASA INTER ADUANERA	22005IT02001996M	357971	357971	0	229	38
3740	2022-10-18 10:46:08.916	5-Angel Rios 	5	I.V.A.	22005IT02001996M	0	0	0	229	8
3741	2022-10-18 10:46:08.926	5-Angel Rios 	6	CANON INFORMATICO	x	0	0	0	229	9
3742	2022-10-18 10:46:08.933	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	229	13
3743	2022-10-18 10:46:08.941	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018498	70000	63636	6363	229	12
3744	2022-10-18 10:46:08.953	5-Angel Rios 	9	TASA PORTUARIA	002-007-0045776	2068376	1880341	188034	229	14
3745	2022-10-18 10:46:08.956	5-Angel Rios 	10	CDAP PAGOS	001-025-0190743	7159	6508	650	229	27
3746	2022-10-18 10:46:08.968	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0138544	51709	47008	4700	229	11
3747	2022-10-18 10:46:08.974	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	229	15
3748	2022-10-18 10:46:08.979	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002135	7377812	6707101	670710	229	16
3749	2022-10-18 10:46:08.983	5-Angel Rios 	14	GASTO ADMIN.	766	5000000	5000000	0	229	17
3750	2022-10-18 10:53:38.822	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020978V	0	0	0	230	2
3751	2022-10-18 10:53:38.831	5-Angel Rios 	2	INDI	22005IC04020978V	26949	26949	0	230	3
3752	2022-10-18 10:53:38.839	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020978V	1243989	1243989	0	230	5
3753	2022-10-18 10:53:38.845	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020978V	1001814	1001814	0	230	38
3754	2022-10-18 10:53:38.853	5-Angel Rios 	5	I.V.A.	22005IC04020978V	25045371	0	25045371	230	8
3755	2022-10-18 10:53:38.861	5-Angel Rios 	6	CANON INFORMATICO	5168018	245222	245222	0	230	9
3756	2022-10-18 10:53:38.869	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017479	175000	159090	15909	230	13
3757	2022-10-18 10:53:38.878	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018488	70000	63636	6363	230	12
3758	2022-10-18 10:53:38.886	5-Angel Rios 	9	TASA PORTUARIA	002-007-0044401	1445828	1314389	131438	230	14
3759	2022-10-18 10:53:38.891	5-Angel Rios 	10	CDAP PAGOS	001-025-0188044	7120	6472	647	230	27
3760	2022-10-18 10:53:38.895	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0135845	36146	32860	3286	230	11
3761	2022-10-18 10:53:38.905	5-Angel Rios 	12	VISACION MRE	1128028	490445	490445	0	230	28
3762	2022-10-18 10:53:38.91	5-Angel Rios 	13	CDAP PAGOS	001-024-0175996	10000	9090	909	230	27
3763	2022-10-18 10:53:38.918	5-Angel Rios 	14	VISACION CONSUL	03025117/118	243000	243000	0	230	15
3764	2022-10-18 10:53:38.923	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002124	3777388	3433989	343398	230	16
3765	2022-10-18 10:53:38.929	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	230	21
3766	2022-10-18 13:04:43.521	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04020978V	0	0	0	231	2
3767	2022-10-18 13:04:43.528	5-Angel Rios 	2	INDI	22005IC04020978V	26949	26949	0	231	3
3768	2022-10-18 13:04:43.535	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04020978V	1243989	1243989	0	231	5
3769	2022-10-18 13:04:43.541	5-Angel Rios 	4	IRE GENERAL 700	22005IC04020978V	1001814	1001814	0	231	38
3770	2022-10-18 13:04:43.55	5-Angel Rios 	5	I.V.A.	22005IC04020978V	25045371	0	25045371	231	8
3771	2022-10-18 13:04:43.56	5-Angel Rios 	6	CANON INFORMATICO	5168018	245222	245222	0	231	9
3772	2022-10-18 13:04:43.569	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017479	175000	159090	15909	231	13
3773	2022-10-18 13:04:43.577	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018488	70000	63636	6363	231	12
3774	2022-10-18 13:04:43.585	5-Angel Rios 	9	TASA PORTUARIA	002-007-0044401	1445828	1314389	131438	231	14
3775	2022-10-18 13:04:43.592	5-Angel Rios 	10	CDAP PAGOS	001-025-0188044	7120	6472	647	231	27
3776	2022-10-18 13:04:43.6	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0135845	36146	32860	3286	231	11
3777	2022-10-18 13:04:43.608	5-Angel Rios 	12	VISACION MRE	5168151	490445	490445	0	231	28
3778	2022-10-18 13:04:43.619	5-Angel Rios 	13	CDAP PAGOS	001-024-0175996	10000	9090	909	231	27
3779	2022-10-18 13:04:43.627	5-Angel Rios 	14	VISACION CONSUL	03025117/118	243000	243000	0	231	15
3780	2022-10-18 13:04:43.64	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002124	3777388	3433989	343398	231	16
3781	2022-10-18 13:04:43.647	5-Angel Rios 	16	REPOSICION DE GASTOS ADM	x	0	0	0	231	21
3782	2022-10-18 13:20:55.778	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021310C	5330418	5330418	0	232	2
3783	2022-10-18 13:20:55.783	5-Angel Rios 	2	INDI	22005IC04021310C	39269	39269	0	232	3
3784	2022-10-18 13:20:55.789	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021310C	1555331	1555331	0	232	5
3785	2022-10-18 13:20:55.795	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021310C	1274207	1274207	0	232	38
3786	2022-10-18 13:20:55.797	5-Angel Rios 	5	I.V.A.	22005IC04021310C	31855239	0	31855239	232	8
3787	2022-10-18 13:20:55.804	5-Angel Rios 	6	CANON INFORMATICO	5204967	245222	245222	0	232	9
3788	2022-10-18 13:20:55.811	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017516	175000	159090	15909	232	13
3789	2022-10-18 13:20:55.813	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018495	280000	254545	25454	232	12
3790	2022-10-18 13:20:55.821	5-Angel Rios 	9	TASA PORTUARIA	002-007-0045363	1633022	1484565	148456	232	14
3791	2022-10-18 13:20:55.833	5-Angel Rios 	10	CDAP PAGOS	001-025-0189910	7141	6491	649	232	27
3792	2022-10-18 13:20:55.838	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0137712	40826	37114	3711	232	11
3793	2022-10-18 13:20:55.844	5-Angel Rios 	12	VISACION CONSUL	03045558/59/60/61/62	1012500	1012500	0	232	15
3794	2022-10-18 13:20:55.849	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002132	5569541	5063219	506321	232	16
3795	2022-10-18 13:20:55.854	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	232	17
3796	2022-10-18 13:29:31.072	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021442X	3359	3359	0	233	2
3797	2022-10-18 13:29:31.079	5-Angel Rios 	2	INDI	22005IC04021442X	26951	26951	0	233	3
3798	2022-10-18 13:29:31.088	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021442X	2045516	2045516	0	233	5
3799	2022-10-18 13:29:31.091	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021442X	1646255	1646255	0	233	38
3800	2022-10-18 13:29:31.101	5-Angel Rios 	5	I.V.A.	22005IC04021442X	41156397	0	41156397	233	8
3801	2022-10-18 13:29:31.109	5-Angel Rios 	6	CANON INFORMATICO	5215787	245222	245222	0	233	9
3802	2022-10-18 13:29:31.122	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	233	13
3803	2022-10-18 13:29:31.13	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018499	90000	81818	8181	233	12
3804	2022-10-18 13:29:31.137	5-Angel Rios 	9	TASA PORTUARIA	002-007-0045884	1990129	1809208	180920	233	14
3805	2022-10-18 13:29:31.141	5-Angel Rios 	10	CDAP PAGOS	001-025-0190973	7159	6508	650	233	27
3806	2022-10-18 13:29:31.154	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0138782	49753	45230	4523	233	11
3807	2022-10-18 13:29:31.163	5-Angel Rios 	12	VISACION CONSUL	3048645/46/47/48	810000	810000	0	233	15
3808	2022-10-18 13:29:31.17	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002137	4447534	4043212	404321	233	16
3809	2022-10-18 13:29:31.179	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	233	17
3810	2022-10-19 13:11:58.064	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	234	2
3811	2022-10-19 13:11:58.076	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	234	3
3812	2022-10-19 13:11:58.086	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	234	5
3813	2022-10-19 13:11:58.096	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	234	38
3814	2022-10-19 13:11:58.106	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	234	8
3815	2022-10-19 13:11:58.117	5-Angel Rios 	6	ROYAL SEGUROS	001-004-0030480	373000	339091	33909	234	9
3816	2022-10-19 13:11:58.132	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	234	13
3817	2022-10-19 13:11:58.145	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	234	12
3818	2022-10-19 13:11:58.156	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	234	14
3819	2022-10-19 13:11:58.169	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	234	27
3820	2022-10-19 13:11:58.185	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	234	11
3821	2022-10-19 13:11:58.195	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	234	15
3822	2022-10-19 13:11:58.204	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	234	16
3823	2022-10-19 13:11:58.216	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	234	17
3824	2022-10-19 13:49:23.053	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021655Y	0	0	0	235	2
3825	2022-10-19 13:49:23.061	5-Angel Rios 	2	INDI	22005IC04021655Y	26951	26951	0	235	3
3826	2022-10-19 13:49:23.068	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021655Y	1162997	1162997	0	235	5
3827	2022-10-19 13:49:23.076	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021655Y	936697	936697	0	235	38
3828	2022-10-19 13:49:23.088	5-Angel Rios 	5	I.V.A.	22005IC04021655Y	23417416	0	23417416	235	8
3829	2022-10-19 13:49:23.1	5-Angel Rios 	6	CANON INFORMATICO	5226446	245222	245222	0	235	9
3830	2022-10-19 13:49:23.106	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017624	175000	159090	15909	235	13
3831	2022-10-19 13:49:23.114	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018527	110000	100000	10000	235	12
3832	2022-10-19 13:49:23.121	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046666	1260607	1146006	114600	235	14
3833	2022-10-19 13:49:23.127	5-Angel Rios 	10	CDAP PAGOS	001-025-0192651	7172	6520	652	235	27
3834	2022-10-19 13:49:23.134	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0140451	31515	28650	2865	235	11
3835	2022-10-19 13:49:23.139	5-Angel Rios 	12	VISACION CONSUL	3049251/52/53	445500	445500	0	235	15
3836	2022-10-19 13:49:23.147	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002138	3908296	3552996	355299	235	16
3838	2022-10-19 13:58:41.345	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	236	2
3839	2022-10-19 13:58:41.352	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	236	3
3840	2022-10-19 13:58:41.362	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	236	5
3841	2022-10-19 13:58:41.364	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	236	38
3842	2022-10-19 13:58:41.379	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	236	8
3843	2022-10-19 13:58:41.387	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	236	9
3844	2022-10-19 13:58:41.394	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	236	13
3845	2022-10-19 13:58:41.402	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	236	12
3846	2022-10-19 13:58:41.413	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	236	14
3847	2022-10-19 13:58:41.417	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	236	27
3848	2022-10-19 13:58:41.428	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	236	11
3849	2022-10-19 13:58:41.437	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	236	15
3850	2022-10-19 13:58:41.443	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	236	16
3851	2022-10-19 13:58:41.451	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	236	17
3852	2022-10-19 13:59:25.014	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	237	2
3853	2022-10-19 13:59:25.022	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	237	3
3854	2022-10-19 13:59:25.029	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	237	5
3855	2022-10-19 13:59:25.037	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	237	38
3856	2022-10-19 13:59:25.044	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	237	8
3857	2022-10-19 13:59:25.056	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	237	9
3858	2022-10-19 13:59:25.062	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	237	13
3859	2022-10-19 13:59:25.07	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	237	12
3860	2022-10-19 13:59:25.078	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	237	14
3861	2022-10-19 13:59:25.085	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	237	27
3862	2022-10-19 13:59:25.093	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	237	11
3863	2022-10-19 13:59:25.109	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	237	15
3864	2022-10-19 13:59:25.118	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	237	16
3865	2022-10-19 13:59:25.124	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	237	17
3866	2022-10-19 13:59:56.518	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	238	2
3867	2022-10-19 13:59:56.538	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	238	3
3868	2022-10-19 13:59:56.548	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	238	5
3869	2022-10-19 13:59:56.548	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	238	38
3870	2022-10-19 13:59:56.568	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	238	8
3871	2022-10-19 13:59:56.578	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	238	9
3872	2022-10-19 13:59:56.588	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	238	13
3873	2022-10-19 13:59:56.608	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	238	12
3874	2022-10-19 13:59:56.618	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	238	14
3875	2022-10-19 13:59:56.628	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	238	27
3876	2022-10-19 13:59:56.648	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	238	11
3877	2022-10-19 13:59:56.658	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	238	15
3878	2022-10-19 13:59:56.668	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	238	16
3879	2022-10-19 13:59:56.678	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	238	17
3880	2022-10-19 14:00:32.738	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	239	2
3881	2022-10-19 14:00:32.744	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	239	3
3882	2022-10-19 14:00:32.754	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	239	5
3883	2022-10-19 14:00:32.762	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	239	38
3884	2022-10-19 14:00:32.769	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	239	8
3885	2022-10-19 14:00:32.777	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	239	9
3886	2022-10-19 14:00:32.788	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	239	13
3887	2022-10-19 14:00:32.799	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	239	12
3888	2022-10-19 14:00:32.807	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	239	14
3889	2022-10-19 14:00:32.815	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	239	27
3890	2022-10-19 14:00:32.82	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	239	11
3891	2022-10-19 14:00:32.829	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	239	15
3892	2022-10-19 14:00:32.841	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	239	16
3893	2022-10-19 14:00:32.848	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	239	17
3894	2022-10-19 14:01:00.202	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	240	2
3895	2022-10-19 14:01:00.209	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	240	3
3896	2022-10-19 14:01:00.218	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	240	5
3897	2022-10-19 14:01:00.229	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	240	38
3898	2022-10-19 14:01:00.237	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	240	8
3899	2022-10-19 14:01:00.247	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	240	9
3900	2022-10-19 14:01:00.256	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	240	13
3901	2022-10-19 14:01:00.262	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	240	12
3902	2022-10-19 14:01:00.27	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	240	14
3903	2022-10-19 14:01:00.282	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	240	27
3904	2022-10-19 14:01:00.302	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	240	11
3905	2022-10-19 14:01:00.484	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	240	15
3906	2022-10-19 14:01:00.494	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	240	16
3908	2022-10-19 14:01:22.827	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	241	2
3909	2022-10-19 14:01:22.837	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	241	3
3910	2022-10-19 14:01:22.847	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	241	5
3911	2022-10-19 14:01:22.854	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	241	38
3912	2022-10-19 14:01:22.869	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	241	8
3913	2022-10-19 14:01:22.877	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	241	9
3914	2022-10-19 14:01:22.886	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	241	13
3915	2022-10-19 14:01:22.897	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	241	12
3916	2022-10-19 14:01:22.906	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	241	14
3917	2022-10-19 14:01:22.918	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	241	27
3918	2022-10-19 14:01:22.927	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	241	11
3919	2022-10-19 14:01:22.936	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	241	15
3920	2022-10-19 14:01:22.943	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	241	16
3921	2022-10-19 14:01:22.951	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	241	17
3922	2022-10-19 14:01:48.921	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	242	2
3923	2022-10-19 14:01:48.932	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	242	3
3924	2022-10-19 14:01:48.936	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	242	5
3925	2022-10-19 14:01:48.947	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	242	38
3926	2022-10-19 14:01:48.958	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	242	8
3927	2022-10-19 14:01:48.97	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	242	9
3928	2022-10-19 14:01:48.979	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	242	13
3929	2022-10-19 14:01:48.988	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	242	12
3930	2022-10-19 14:01:48.996	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	242	14
3931	2022-10-19 14:01:49.005	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	242	27
3932	2022-10-19 14:01:49.012	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	242	11
3933	2022-10-19 14:01:49.018	5-Angel Rios 	12	VISACION CONSUL	3049249/50	243000	243000	0	242	15
3934	2022-10-19 14:01:49.028	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	2930280	2663890	266389	242	16
3935	2022-10-19 14:01:49.036	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	242	17
3936	2022-10-19 14:11:01.625	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	243	2
3937	2022-10-19 14:11:01.641	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	243	3
3938	2022-10-19 14:11:01.647	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	243	5
3939	2022-10-19 14:11:01.657	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	243	38
3940	2022-10-19 14:11:01.666	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	243	8
3941	2022-10-19 14:11:01.677	5-Angel Rios 	6	CANON INFORMATICO	X	0	0	0	243	9
3942	2022-10-19 14:11:01.685	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	243	13
3943	2022-10-19 14:11:01.698	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	243	12
3944	2022-10-19 14:11:01.711	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	243	14
3945	2022-10-19 14:11:01.719	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	243	27
3946	2022-10-19 14:11:01.731	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	243	11
3947	2022-10-19 14:11:01.739	5-Angel Rios 	12	VISACION CONSUL	X	0	0	0	243	15
3948	2022-10-19 14:11:01.748	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002139	2930280	2663890	266389	243	16
3949	2022-10-19 14:11:01.754	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	243	17
3950	2022-10-19 15:09:05.771	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	244	2
3951	2022-10-19 15:09:05.781	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	244	3
3952	2022-10-19 15:09:05.789	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	244	5
3953	2022-10-19 15:09:05.797	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	244	38
3954	2022-10-19 15:09:05.802	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	244	8
3955	2022-10-19 15:09:05.812	5-Angel Rios 	6	ROYAL SEGUROS	001-004-0030480	373000	373000	0	244	9
3956	2022-10-19 15:09:05.818	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	244	13
3957	2022-10-19 15:09:05.824	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	244	12
3958	2022-10-19 15:09:05.832	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	244	14
3959	2022-10-19 15:09:05.84	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	244	27
3960	2022-10-19 15:09:05.848	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	244	11
3961	2022-10-19 15:09:05.853	5-Angel Rios 	12	VISACION CONSUL	X	0	0	0	244	15
3962	2022-10-19 15:09:05.858	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002139	2930280	2663890	266389	244	16
3963	2022-10-19 15:09:05.868	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	244	17
3964	2022-10-19 15:14:23.341	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002020Y	0	0	0	245	2
3965	2022-10-19 15:14:23.354	5-Angel Rios 	2	INDI	22005IT02002020Y	0	0	0	245	3
3966	2022-10-19 15:14:23.365	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002020Y	0	0	0	245	5
3967	2022-10-19 15:14:23.374	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002020Y	0	0	0	245	38
3968	2022-10-19 15:14:23.39	5-Angel Rios 	5	I.V.A.	22005IT02002020Y	0	0	0	245	8
3969	2022-10-19 15:14:23.401	5-Angel Rios 	6	ROYAL SEGUROS	001-004-0030480	373000	373000	0	245	9
3970	2022-10-19 15:14:23.41	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017619	175000	159090	15909	245	13
3971	2022-10-19 15:14:23.423	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018519	70000	63636	6363	245	12
3972	2022-10-19 15:14:23.434	5-Angel Rios 	9	TASA PORTUARIA	002-007-0046318	297436	270396	27039	245	14
3973	2022-10-19 15:14:23.441	5-Angel Rios 	10	CDAP PAGOS	001-025-0192050	7146	6496	649	245	27
3974	2022-10-19 15:14:23.451	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0139851	7436	6760	676	245	11
3975	2022-10-19 15:14:23.461	5-Angel Rios 	12	VISACION CONSUL	X	0	0	0	245	15
3976	2022-10-19 15:14:23.47	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002139	2930280	2663890	266389	245	16
3978	2022-10-20 08:39:00.901	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	246	2
3979	2022-10-20 08:39:00.908	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	246	3
3980	2022-10-20 08:39:00.915	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	246	5
3981	2022-10-20 08:39:00.929	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	246	38
3982	2022-10-20 08:39:00.936	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	246	8
3983	2022-10-20 08:39:00.943	5-Angel Rios 	6	CANON INFORMATICO	X	245222	245222	0	246	9
3984	2022-10-20 08:39:00.949	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	246	13
3985	2022-10-20 08:39:00.954	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	246	12
3986	2022-10-20 08:39:00.959	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	246	14
3987	2022-10-20 08:39:00.965	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	246	27
3988	2022-10-20 08:39:00.97	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	246	11
3989	2022-10-20 08:39:00.977	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	246	15
3990	2022-10-20 08:39:00.983	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2000000	1818181	181818	246	16
3991	2022-10-20 08:39:00.99	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	246	17
3992	2022-10-20 08:39:00.995	5-Angel Rios 	15	REGISTRO DE FIRMA 	776	800000	800000	0	246	17
3993	2022-10-20 08:43:05.307	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	247	2
3994	2022-10-20 08:43:05.314	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	247	3
3995	2022-10-20 08:43:05.322	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	247	5
3996	2022-10-20 08:43:05.328	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	247	38
3997	2022-10-20 08:43:05.334	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	247	8
3998	2022-10-20 08:43:05.34	5-Angel Rios 	6	CANON INFORMATICO	X	245222	245222	0	247	9
3999	2022-10-20 08:43:05.346	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	247	13
4000	2022-10-20 08:43:05.355	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	247	12
4001	2022-10-20 08:43:05.363	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	247	14
4002	2022-10-20 08:43:05.369	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	247	27
4003	2022-10-20 08:43:05.375	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	247	11
4004	2022-10-20 08:43:05.38	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	247	15
4005	2022-10-20 08:43:05.386	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2000000	1818181	181818	247	16
4006	2022-10-20 08:43:05.39	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	247	17
4007	2022-10-20 08:43:05.396	5-Angel Rios 	15	REGISTRO DE FIRMA 	776	800000	800000	0	247	17
4008	2022-10-20 08:44:14.484	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	248	2
4009	2022-10-20 08:44:14.489	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	248	3
4010	2022-10-20 08:44:14.494	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	248	5
4011	2022-10-20 08:44:14.5	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	248	38
4012	2022-10-20 08:44:14.505	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	248	8
4013	2022-10-20 08:44:14.51	5-Angel Rios 	6	CANON INFORMATICO	X	245222	245222	0	248	9
4014	2022-10-20 08:44:14.515	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	248	13
4015	2022-10-20 08:44:14.521	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	248	12
4016	2022-10-20 08:44:14.528	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	248	14
4017	2022-10-20 08:44:14.534	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	248	27
4018	2022-10-20 08:44:14.539	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	248	11
4019	2022-10-20 08:44:14.545	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	248	15
4020	2022-10-20 08:44:14.551	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	248	16
4021	2022-10-20 08:44:14.557	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	248	17
4022	2022-10-20 08:44:14.562	5-Angel Rios 	15	REGISTRO DE FIRMA 	776	800000	800000	0	248	17
4023	2022-10-20 08:46:11.474	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	249	2
4024	2022-10-20 08:46:11.483	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	249	3
4025	2022-10-20 08:46:11.493	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	249	5
4026	2022-10-20 08:46:11.504	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	249	38
4027	2022-10-20 08:46:11.512	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	249	8
4028	2022-10-20 08:46:11.518	5-Angel Rios 	6	CANON INFORMATICO	X	245222	245222	0	249	9
4029	2022-10-20 08:46:11.529	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	249	13
4030	2022-10-20 08:46:11.534	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	249	12
4031	2022-10-20 08:46:11.539	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	249	14
4032	2022-10-20 08:46:11.546	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	249	27
4033	2022-10-20 08:46:11.552	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	249	11
4034	2022-10-20 08:46:11.557	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	249	15
4035	2022-10-20 08:46:11.564	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	249	16
4036	2022-10-20 08:46:11.57	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	249	17
4037	2022-10-20 08:46:11.575	5-Angel Rios 	15	REGISTRO DE FIRMA PERIODO 22/23	776	800000	800000	0	249	17
4038	2022-10-20 08:50:03.787	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	250	2
4039	2022-10-20 08:50:03.792	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	250	3
4040	2022-10-20 08:50:03.798	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	250	5
4041	2022-10-20 08:50:03.803	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	250	38
4042	2022-10-20 08:50:03.808	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	250	8
4043	2022-10-20 08:50:03.814	5-Angel Rios 	6	CANON INFORMATICO	001-020-0467818	245222	245222	0	250	9
4044	2022-10-20 08:50:03.819	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	250	13
4045	2022-10-20 08:50:03.824	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	250	12
4046	2022-10-20 08:50:03.829	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	250	14
4047	2022-10-20 08:50:03.839	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	250	27
4048	2022-10-20 08:50:03.845	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	250	11
4049	2022-10-20 08:50:03.852	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	250	15
4050	2022-10-20 08:50:03.857	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	250	16
4051	2022-10-20 08:50:03.862	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	250	17
4052	2022-10-20 08:50:03.868	5-Angel Rios 	15	REGISTRO DE FIRMA PERIODO 22/23	776	800000	800000	0	250	17
4053	2022-10-20 08:56:39.869	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	251	2
4054	2022-10-20 08:56:39.874	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	251	3
4055	2022-10-20 08:56:39.881	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	251	5
4056	2022-10-20 08:56:39.886	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	251	38
4057	2022-10-20 08:56:39.89	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	251	8
4058	2022-10-20 08:56:39.895	5-Angel Rios 	6	CANON INFORMATICO	001-020-0467818	245222	245222	0	251	9
4059	2022-10-20 08:56:39.9	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	251	13
4060	2022-10-20 08:56:39.905	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	251	12
4061	2022-10-20 08:56:39.91	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	251	14
4062	2022-10-20 08:56:39.916	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	251	27
4063	2022-10-20 08:56:39.921	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	251	11
4064	2022-10-20 08:56:39.929	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	251	15
4065	2022-10-20 08:56:39.936	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	251	16
4066	2022-10-20 08:56:39.943	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	251	17
4067	2022-10-20 08:56:39.949	5-Angel Rios 	15	REGISTRO DE FIRMA PERIODO 22/23	776	800000	800000	0	251	17
4068	2022-10-20 11:51:18.534	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	252	2
4069	2022-10-20 11:51:18.541	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	252	3
4070	2022-10-20 11:51:18.546	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	252	5
4071	2022-10-20 11:51:18.555	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	252	38
4072	2022-10-20 11:51:18.56	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	252	8
4073	2022-10-20 11:51:18.565	5-Angel Rios 	6	CANON INFORMATICO	001-020-0467818	245222	245222	0	252	9
4074	2022-10-20 11:51:18.573	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	252	13
4075	2022-10-20 11:51:18.579	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	252	12
4076	2022-10-20 11:51:18.584	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	252	14
4077	2022-10-20 11:51:18.589	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	252	27
4078	2022-10-20 11:51:18.596	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	252	11
4079	2022-10-20 11:51:18.601	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	252	15
4080	2022-10-20 11:51:18.608	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	252	16
4081	2022-10-20 11:51:18.617	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	252	17
4082	2022-10-20 12:11:13.731	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	253	2
4083	2022-10-20 12:11:13.74	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	253	3
4084	2022-10-20 12:11:13.746	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	253	5
4085	2022-10-20 12:11:13.75	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	253	38
4086	2022-10-20 12:11:13.756	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	253	8
4087	2022-10-20 12:11:13.762	5-Angel Rios 	6	CANON INFORMATICO	001-020-0467818	245222	245222	0	253	9
4088	2022-10-20 12:11:13.769	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	253	13
4089	2022-10-20 12:11:13.778	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	253	12
4090	2022-10-20 12:11:13.783	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	253	14
4091	2022-10-20 12:11:13.791	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	253	27
4092	2022-10-20 12:11:13.796	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	253	11
4093	2022-10-20 12:11:13.801	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	253	15
4094	2022-10-20 12:11:13.808	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	253	16
4095	2022-10-20 12:11:13.813	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	253	17
4096	2022-10-20 12:12:45.356	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04021343F	14454879	14454879	0	254	2
4097	2022-10-20 12:12:45.363	5-Angel Rios 	2	INDI	22002IC04021343F	0	0	0	254	3
4098	2022-10-20 12:12:45.371	5-Angel Rios 	3	SERVICIO DE VALORACION	22002IC04021343F	516246	516246	0	254	5
4099	2022-10-20 12:12:45.377	5-Angel Rios 	4	IRE GENERAL 700	22002IC04021343F	473736	473736	0	254	38
4100	2022-10-20 12:12:45.382	5-Angel Rios 	5	I.V.A.	22002IC04021343F	11843400	0	11843400	254	8
4101	2022-10-20 12:12:45.389	5-Angel Rios 	6	CANON INFORMATICO	001-020-0467818	245222	245222	0	254	9
4102	2022-10-20 12:12:45.395	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	254	13
4103	2022-10-20 12:12:45.4	5-Angel Rios 	8	FOTOCOPIAS	004-003-0006214	94400	85818	8581	254	12
4104	2022-10-20 12:12:45.406	5-Angel Rios 	9	TASA PORTUARIA	001-034-0064100	4437132	4033756	403375	254	14
4105	2022-10-20 12:12:45.413	5-Angel Rios 	10	CDAP PAGOS	001-021-0066422	21395	19450	1945	254	27
4106	2022-10-20 12:12:45.422	5-Angel Rios 	11	TASA DINAVISA	567998	196178	178343	17834	254	11
4107	2022-10-20 12:12:45.428	5-Angel Rios 	12	CDAP PAGOS	001-024-0177385	10000	10000	0	254	15
4108	2022-10-20 12:12:45.433	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002131	2602870	2366245	236624	254	16
4109	2022-10-20 12:12:45.444	5-Angel Rios 	14	GASTO ADMIN.	768	816000	816000	0	254	17
4110	2022-10-24 14:13:29.295	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04013106E	0	0	0	255	2
4111	2022-10-24 14:13:29.306	5-Angel Rios 	2	INDI	22030IC04013106E	27790	27790	0	255	3
4112	2022-10-24 14:13:29.314	5-Angel Rios 	3	SERVICIO DE VALORACION	22030IC04013106E	460036	460036	0	255	5
4113	2022-10-24 14:13:29.322	5-Angel Rios 	4	IRE GENERAL 700	22030IC04013106E	371567	371567	0	255	38
4114	2022-10-24 14:13:29.329	5-Angel Rios 	5	I.V.A.	22030IC04013106E	9289193	0	9289193	255	8
4115	2022-10-24 14:13:29.377	5-Angel Rios 	6	CANON INFORMATICO	5264732	245222	245222	0	255	9
4116	2022-10-24 14:13:29.388	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	255	13
4117	2022-10-24 14:13:29.397	5-Angel Rios 	8	FOTOCOPIAS	020-002-0002491	36000	32727	3272	255	12
4118	2022-10-24 14:13:29.407	5-Angel Rios 	9	TASA PORTUARIA	001-004-19015	1486209	1351099	135109	255	14
4119	2022-10-24 14:13:29.417	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	255	27
4120	2022-10-24 14:13:29.426	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	255	11
4121	2022-10-24 14:13:29.434	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	255	15
4122	2022-10-24 14:13:29.445	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002140	1634662	1486056	148605	255	16
4123	2022-10-24 14:13:29.453	5-Angel Rios 	14	GASTO ADMIN.	001-001-0002141	660000	660000	0	255	17
4124	2022-10-24 14:13:29.46	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	255	17
4125	2022-10-25 10:53:38.416	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04021979A	0	0	0	256	2
4126	2022-10-25 10:53:38.426	5-Angel Rios 	2	INDI	22005IC04021979A	34331	34331	0	256	3
4127	2022-10-25 10:53:38.433	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04021979A	1211424	1211424	0	256	5
4128	2022-10-25 10:53:38.442	5-Angel Rios 	4	IRE GENERAL 700	22005IC04021979A	976084	976084	0	256	38
4129	2022-10-25 10:53:38.444	5-Angel Rios 	5	I.V.A.	22005IC04021979A	24402104	0	24402104	256	8
4130	2022-10-25 10:53:38.46	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04021979A	362055	362055	0	256	35
4131	2022-10-25 10:53:38.467	5-Angel Rios 	7	CANON INFORMATICO	5268372	245222	245222	0	256	9
4132	2022-10-25 10:53:38.475	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018549	70000	63636	6363	256	12
4133	2022-10-25 10:53:38.482	5-Angel Rios 	9	TASA PORTUARIA	002-007-0047701	1402872	1275338	127533	256	14
4134	2022-10-25 10:53:38.489	5-Angel Rios 	10	CDAP PAGOS	001-025-0194583	7240	6581	658	256	27
4135	2022-10-25 10:53:38.497	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0142441	35072	31883	3188	256	11
4136	2022-10-25 10:53:38.505	5-Angel Rios 	12	VISACION MRE	1137792	490445	490445	0	256	28
4137	2022-10-25 10:53:38.51	5-Angel Rios 	13	CDAP PAGOS	001-024-0183662	10000	9090	909	256	27
4138	2022-10-25 10:53:38.518	5-Angel Rios 	14	MIPYMES MIC	1967441	691023	628202	62820	256	22
4139	2022-10-25 10:53:38.522	5-Angel Rios 	15	APORTE VUE	1967442	75859	68962	6896	256	23
4140	2022-10-25 10:53:38.531	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002142	5138566	4671423	467142	256	16
4141	2022-10-25 10:53:38.534	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	256	17
4142	2022-10-28 15:54:20.141	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022428N	0	0	0	257	2
4143	2022-10-28 15:54:20.157	5-Angel Rios 	2	INDI	22005IC04022428N	27790	27790	0	257	3
4144	2022-10-28 15:54:20.172	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022428N	340345	340345	0	257	5
4145	2022-10-28 15:54:20.172	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022428N	275337	275337	0	257	38
4146	2022-10-28 15:54:20.188	5-Angel Rios 	5	I.V.A.	22005IC04022428N	6863421	0	6863421	257	8
4147	2022-10-28 15:54:20.188	5-Angel Rios 	6	CANON INFORMATICO	5333917	245222	245222	0	257	9
4148	2022-10-28 15:54:20.207	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017854	175000	159090	15909	257	13
4149	2022-10-28 15:54:20.253	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018578	140000	127272	12727	257	12
4150	2022-10-28 15:54:20.285	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049189/0049525	398272	362065	36206	257	14
4151	2022-10-28 15:54:20.285	5-Angel Rios 	10	CDAP PAGOS	001-025-0197552	7247	6588	658	257	27
4152	2022-10-28 15:54:20.3	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0145356	9544	8676	867	257	11
4153	2022-10-28 15:54:20.307	5-Angel Rios 	12	TASA MADES	3074874128	397356	397356	0	257	46
4154	2022-10-28 15:54:20.307	5-Angel Rios 	13	INS. IMP. SIDERURGICOS	1953808	686623	686623	0	257	47
4155	2022-10-28 15:54:20.322	5-Angel Rios 	14	VISACION MRE	1140317	490445	490445	0	257	28
4156	2022-10-28 15:54:20.322	5-Angel Rios 	15	CDAP PAGOS	x	10000	9090	909	257	27
4157	2022-10-28 15:54:20.338	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002143	1424008	1294552	129455	257	16
4158	2022-10-28 15:54:20.338	5-Angel Rios 	17	GASTOS ADM MADES/MIC INSC	778	1500000	1500000	0	257	21
4159	2022-10-28 15:54:20.354	5-Angel Rios 	18	GASTOS ADM	779	3000000	3000000	0	257	21
4160	2022-10-28 15:54:20.385	5-Angel Rios 	19	REPOSICION DE GASTOS ADM	780	540000	540000	0	257	21
4161	2022-10-28 16:17:41.839	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022428N	0	0	0	258	2
4162	2022-10-28 16:17:41.844	5-Angel Rios 	2	INDI	22005IC04022428N	27790	27790	0	258	3
4163	2022-10-28 16:17:41.844	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022428N	340345	340345	0	258	5
4164	2022-10-28 16:17:41.844	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022428N	275337	275337	0	258	38
4165	2022-10-28 16:17:41.86	5-Angel Rios 	5	I.V.A.	22005IC04022428N	6863421	0	6863421	258	8
4166	2022-10-28 16:17:41.86	5-Angel Rios 	6	CANON INFORMATICO	5333917	245222	245222	0	258	9
4167	2022-10-28 16:17:41.875	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017854	175000	159090	15909	258	13
4168	2022-10-28 16:17:41.875	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018578	140000	127272	12727	258	12
4169	2022-10-28 16:17:41.891	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049189/0049525	398272	362065	36206	258	14
4170	2022-10-28 16:17:41.891	5-Angel Rios 	10	CDAP PAGOS	001-025-0197552	7247	6588	658	258	27
4171	2022-10-28 16:17:41.907	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0145356	9544	8676	867	258	11
4172	2022-10-28 16:17:41.907	5-Angel Rios 	12	TASA MADES	3074874128	397356	397356	0	258	46
4173	2022-10-28 16:17:41.922	5-Angel Rios 	13	INS. IMP. SIDERURGICOS	1953808	686623	686623	0	258	47
4174	2022-10-28 16:17:41.922	5-Angel Rios 	14	VISACION MRE	1140317	490445	490445	0	258	28
4175	2022-10-28 16:17:41.938	5-Angel Rios 	15	CDAP PAGOS	x	10000	9090	909	258	27
4176	2022-10-28 16:17:41.938	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002143	1424008	1294552	129455	258	16
4177	2022-10-28 16:17:41.954	5-Angel Rios 	17	GASTOS ADM MADES/MIC INSC	778	1500000	1500000	0	258	21
4178	2022-10-28 16:17:41.954	5-Angel Rios 	18	GASTOS ADM	779	3000000	3000000	0	258	21
4179	2022-10-28 16:17:41.969	5-Angel Rios 	19	REPOSICION DE GASTOS ADM	780	750000	750000	0	258	21
4180	2022-10-28 16:20:25.03	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022428N	0	0	0	259	2
4181	2022-10-28 16:20:25.036	5-Angel Rios 	2	INDI	22005IC04022428N	27790	27790	0	259	3
4182	2022-10-28 16:20:25.036	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022428N	340345	340345	0	259	5
4183	2022-10-28 16:20:25.052	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022428N	275337	275337	0	259	38
4184	2022-10-28 16:20:25.068	5-Angel Rios 	5	I.V.A.	22005IC04022428N	6883421	0	6883421	259	8
4185	2022-10-28 16:20:25.076	5-Angel Rios 	6	CANON INFORMATICO	5333917	245222	245222	0	259	9
4186	2022-10-28 16:20:25.076	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017854	175000	159090	15909	259	13
4187	2022-10-28 16:20:25.092	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018578	140000	127272	12727	259	12
4188	2022-10-28 16:20:25.107	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049189/0049525	398272	362065	36206	259	14
4189	2022-10-28 16:20:25.123	5-Angel Rios 	10	CDAP PAGOS	001-025-0197552	7247	6588	658	259	27
4190	2022-10-28 16:20:25.123	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0145356	9544	8676	867	259	11
4191	2022-10-28 16:20:25.123	5-Angel Rios 	12	TASA MADES	3074874128	397356	397356	0	259	46
4192	2022-10-28 16:20:25.139	5-Angel Rios 	13	INS. IMP. SIDERURGICOS / MIC	1953808	691023	691023	0	259	47
4193	2022-10-28 16:20:25.154	5-Angel Rios 	14	VISACION MRE	1140317	490445	490445	0	259	28
4194	2022-10-28 16:20:25.17	5-Angel Rios 	15	CDAP PAGOS	x	10000	9090	909	259	27
4195	2022-10-28 16:20:25.176	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002143	1424008	1294552	129455	259	16
4196	2022-10-28 16:20:25.176	5-Angel Rios 	17	GASTOS ADM MADES/MIC INSC	778	1500000	1500000	0	259	21
4197	2022-10-28 16:20:25.192	5-Angel Rios 	18	GASTOS ADM	779	3000000	3000000	0	259	21
4198	2022-10-28 16:20:25.208	5-Angel Rios 	19	REPOSICION DE GASTOS ADM	780	750000	750000	0	259	21
4199	2022-10-28 16:59:01.404	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022428N	0	0	0	260	2
4200	2022-10-28 16:59:01.411	5-Angel Rios 	2	INDI	22005IC04022428N	27790	27790	0	260	3
4201	2022-10-28 16:59:01.411	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022428N	340345	340345	0	260	5
4202	2022-10-28 16:59:01.411	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022428N	275337	275337	0	260	38
4203	2022-10-28 16:59:01.427	5-Angel Rios 	5	I.V.A.	22005IC04022428N	6883421	0	6883421	260	8
4204	2022-10-28 16:59:01.427	5-Angel Rios 	6	CANON INFORMATICO	5333917	245222	245222	0	260	9
4205	2022-10-28 16:59:01.442	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017854	175000	159090	15909	260	13
4206	2022-10-28 16:59:01.442	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018578	140000	127272	12727	260	12
4207	2022-10-28 16:59:01.442	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049189/0049525	398272	362065	36206	260	14
4208	2022-10-28 16:59:01.458	5-Angel Rios 	10	CDAP PAGOS	001-025-0197552	7247	6588	658	260	27
4209	2022-10-28 16:59:01.458	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0145356	9544	8676	867	260	11
4210	2022-10-28 16:59:01.473	5-Angel Rios 	12	TASA MADES	3074874128	397356	397356	0	260	46
4211	2022-10-28 16:59:01.473	5-Angel Rios 	13	INS. IMP. SIDERURGICOS / MIC	1953808	691023	691023	0	260	47
4212	2022-10-28 16:59:01.489	5-Angel Rios 	14	VISACION MRE	1140317	490445	490445	0	260	28
4213	2022-10-28 16:59:01.489	5-Angel Rios 	15	CDAP PAGOS	x	10000	9090	909	260	27
4214	2022-10-28 16:59:01.505	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002143	1424008	1294552	129455	260	16
4215	2022-10-28 16:59:01.505	5-Angel Rios 	17	GASTOS ADM MADES/MIC INSC	778	2300000	2300000	0	260	21
4216	2022-10-28 16:59:01.505	5-Angel Rios 	18	GASTOS ADM	779	3000000	3000000	0	260	21
4217	2022-10-28 16:59:01.52	5-Angel Rios 	19	REPOSICION DE GASTOS ADM	780	1050000	1050000	0	260	21
4218	2022-10-31 13:40:20.257	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022428N	0	0	0	261	2
4219	2022-10-31 13:40:20.264	5-Angel Rios 	2	INDI	22005IC04022428N	27790	27790	0	261	3
4220	2022-10-31 13:40:20.264	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022428N	340345	340345	0	261	5
4221	2022-10-31 13:40:20.28	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022428N	275337	275337	0	261	38
4222	2022-10-31 13:40:20.28	5-Angel Rios 	5	I.V.A.	22005IC04022428N	6883421	0	6883421	261	8
4223	2022-10-31 13:40:20.296	5-Angel Rios 	6	CANON INFORMATICO	5333917	245222	245222	0	261	9
4224	2022-10-31 13:40:20.296	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017854	175000	159090	15909	261	13
4225	2022-10-31 13:40:20.311	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018578	140000	127272	12727	261	12
4226	2022-10-31 13:40:20.311	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049189/0049525	398272	362065	36206	261	14
4227	2022-10-31 13:40:20.327	5-Angel Rios 	10	CDAP PAGOS	001-025-0197552	7247	6588	658	261	27
4228	2022-10-31 13:40:20.327	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0145356	9544	8676	867	261	11
4229	2022-10-31 13:40:20.327	5-Angel Rios 	12	TASA MADES	3074874128	397356	397356	0	261	46
4230	2022-10-31 13:40:20.342	5-Angel Rios 	13	INS. IMP. SIDERURGICOS / MIC	1953808	691023	691023	0	261	47
4231	2022-10-31 13:40:20.342	5-Angel Rios 	14	VISACION MRE	1140317	490445	490445	0	261	28
4232	2022-10-31 13:40:20.358	5-Angel Rios 	15	CDAP PAGOS	001-024-0186019	10000	9090	909	261	27
4233	2022-10-31 13:40:20.358	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002143	1424008	1294552	129455	261	16
4234	2022-10-31 13:40:20.374	5-Angel Rios 	17	GASTOS ADM MADES/MIC INSC	778	2300000	2300000	0	261	21
4235	2022-10-31 13:40:20.374	5-Angel Rios 	18	GASTOS ADM	779	3000000	3000000	0	261	21
4236	2022-10-31 13:40:20.389	5-Angel Rios 	19	REPOSICION DE GASTOS ADM	780	1050000	1050000	0	261	21
4237	2022-10-31 16:43:45.399	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022593Z	135058	135058	0	262	2
4238	2022-10-31 16:43:45.406	5-Angel Rios 	2	INDI	22005IC04022593Z	27789	27789	0	262	3
4239	2022-10-31 16:43:45.413	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022593Z	682773	682773	0	262	5
4240	2022-10-31 16:43:45.419	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022593Z	551191	551191	0	262	38
4241	2022-10-31 16:43:45.43	5-Angel Rios 	5	I.V.A.	22005IC04022593Z	13779738	0	13779738	262	8
4242	2022-10-31 16:43:45.437	5-Angel Rios 	6	CANON INFORMATICO	5343344	245222	245222	0	262	9
4243	2022-10-31 16:43:45.443	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017931	175000	159090	15909	262	13
4244	2022-10-31 16:43:45.451	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018586	170000	154545	15454	262	12
4245	2022-10-31 16:43:45.456	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049804	754131	685573	68557	262	14
4246	2022-10-31 16:43:45.464	5-Angel Rios 	10	CDAP PAGOS	001-025-0198718	7286	6623	662	262	27
4247	2022-10-31 16:43:45.47	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0146526	18853	17139	1713	262	11
4248	2022-10-31 16:43:45.479	5-Angel Rios 	12	LICENCIA PREVIA CONFECCIONES	1139938	686623	686623	0	262	46
4249	2022-10-31 16:43:45.485	5-Angel Rios 	13	CDAP PAGOS 	001-024-0185546	10000	10000	0	262	47
4250	2022-10-31 16:43:45.489	5-Angel Rios 	14	VISACION CONSUL	03053122/23/24	445500	445500	0	262	28
4251	2022-10-31 16:43:45.496	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	262	27
4252	2022-10-31 16:43:45.501	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002144	2456051	2232773	223277	262	16
4253	2022-10-31 16:43:45.506	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	781	710000	710000	0	262	21
4254	2022-10-31 16:43:45.515	5-Angel Rios 	18	OTROS	x	0	0	0	262	21
4255	2022-10-31 16:59:18.731	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022593Z	135058	135058	0	263	2
4256	2022-10-31 16:59:18.738	5-Angel Rios 	2	INDI	22005IC04022593Z	27789	27789	0	263	3
4257	2022-10-31 16:59:18.747	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022593Z	682773	682773	0	263	5
4258	2022-10-31 16:59:18.754	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022593Z	551191	551191	0	263	38
4259	2022-10-31 16:59:18.767	5-Angel Rios 	5	I.V.A.	22005IC04022593Z	13779738	0	13779738	263	8
4260	2022-10-31 16:59:18.774	5-Angel Rios 	6	CANON INFORMATICO	5343344	245222	245222	0	263	9
4261	2022-10-31 16:59:18.78	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017931	175000	159090	15909	263	13
4262	2022-10-31 16:59:18.788	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018586	170000	154545	15454	263	12
4263	2022-10-31 16:59:18.798	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049804	754131	685573	68557	263	14
4264	2022-10-31 16:59:18.805	5-Angel Rios 	10	CDAP PAGOS	001-025-0198718	7286	6623	662	263	27
4265	2022-10-31 16:59:18.81	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0146526	18853	17139	1713	263	11
4266	2022-10-31 16:59:18.815	5-Angel Rios 	12	LICENCIA PREVIA CONFECCIONES	1139938	686623	686623	0	263	46
4267	2022-10-31 16:59:18.822	5-Angel Rios 	13	CDAP PAGOS 	001-024-0185546	10000	10000	0	263	47
4268	2022-10-31 16:59:18.83	5-Angel Rios 	14	VISACION CONSUL	03053122/23/24	445500	445500	0	263	28
4269	2022-10-31 16:59:18.835	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	263	27
4270	2022-10-31 16:59:18.84	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002144	1228025	2232773	223277	263	16
4271	2022-10-31 16:59:18.849	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	781	710000	710000	0	263	21
4272	2022-10-31 16:59:18.853	5-Angel Rios 	18	OTROS	x	0	0	0	263	21
4273	2022-11-01 08:22:54.253	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022341H	0	0	0	264	2
4274	2022-11-01 08:22:54.26	5-Angel Rios 	2	INDI	22005IC04022341H	34331	34331	0	264	3
4275	2022-11-01 08:22:54.26	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022341H	1270638	1270638	0	264	5
4276	2022-11-01 08:22:54.275	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022341H	1023692	1023692	0	264	38
4277	2022-11-01 08:22:54.275	5-Angel Rios 	5	I.V.A.	22005IC04022341H	25592299	0	25592299	264	8
4278	2022-11-01 08:22:54.275	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022341H	362363	362363	0	264	35
4279	2022-11-01 08:22:54.291	5-Angel Rios 	7	CANON INFORMATICO	5333831	245222	245222	0	264	9
4280	2022-11-01 08:22:54.291	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	264	13
4281	2022-11-01 08:22:54.307	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018576	70000	63636	6363	264	12
4282	2022-11-01 08:22:54.307	5-Angel Rios 	10	TASA PORTUARIA	002-007-0048993	1484980	1349981	134998	264	14
4283	2022-11-01 08:22:54.322	5-Angel Rios 	11	CDAP PAGOS	001-025-0197149	7247	6588	658	264	27
4284	2022-11-01 08:22:54.322	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0144955	37125	33750	3375	264	11
4285	2022-11-01 08:22:54.322	5-Angel Rios 	13	VISACION MRE	1139318	490445	490445	0	264	28
4286	2022-11-01 08:22:54.338	5-Angel Rios 	14	CDAP PAGOS	001-024-0185686	10000	9090	909	264	27
4287	2022-11-01 08:22:54.338	5-Angel Rios 	15	TASA IMP. MATERIA PR	1967435	691023	691023	0	264	48
4288	2022-11-01 08:22:54.349	5-Angel Rios 	16	APORTE VUE	1967436	75859	68962	6896	264	23
4289	2022-11-01 08:22:54.349	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	x	5799621	5272382	527238	264	16
4290	2022-11-01 08:22:54.365	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	264	17
4291	2022-11-01 08:40:46.46	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022399U	3599885	3599885	0	265	2
4292	2022-11-01 08:40:46.464	5-Angel Rios 	2	INDI	22005IC04022399U	15219	15219	0	265	3
4293	2022-11-01 08:40:46.472	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022399U	128567	128567	0	265	5
4294	2022-11-01 08:40:46.479	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022399U	118698	118698	0	265	38
4295	2022-11-01 08:40:46.483	5-Angel Rios 	5	I.V.A.	22005IC04022399U	2967455	0	2967455	265	8
4296	2022-11-01 08:40:46.488	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022399U	0	0	0	265	35
4297	2022-11-01 08:40:46.493	5-Angel Rios 	7	CANON INFORMATICO	5336239	98089	98089	0	265	9
4298	2022-11-01 08:40:46.497	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017855	175000	159090	15909	265	13
4299	2022-11-01 08:40:46.503	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018577	80000	72727	7272	265	12
4300	2022-11-01 08:40:46.508	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049139	172004	156367	15636	265	14
4301	2022-11-01 08:40:46.514	5-Angel Rios 	11	CDAP PAGOS	001-025-0197419	7247	6588	658	265	27
4302	2022-11-01 08:40:46.514	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0145229	4300	3909	390	265	11
4303	2022-11-01 08:40:46.514	5-Angel Rios 	13	VISACION MRE	x	0	0	0	265	28
4304	2022-11-01 08:40:46.53	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	265	27
4305	2022-11-01 08:40:46.53	5-Angel Rios 	15	TASA IMP. MATERIA PR	x	0	0	0	265	48
4306	2022-11-01 08:40:46.53	5-Angel Rios 	16	APORTE VUE	x	0	0	0	265	23
4307	2022-11-01 08:40:46.546	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	x	1206849	1097135	109713	265	16
4308	2022-11-01 08:40:46.546	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	265	17
4309	2022-11-01 08:52:30.943	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003410A	0	0	0	266	2
4310	2022-11-01 08:52:30.951	5-Angel Rios 	2	INDI	22018EC01003410A	0	0	0	266	3
4311	2022-11-01 08:52:30.957	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003410A	0	0	0	266	5
4312	2022-11-01 08:52:30.962	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003410A	0	0	0	266	38
4313	2022-11-01 08:52:30.967	5-Angel Rios 	5	I.V.A.	22018EC01003410A	0	0	0	266	8
4314	2022-11-01 08:52:30.978	5-Angel Rios 	6	TASA INT. ADUANERA 	22018EC01003410A	0	0	0	266	35
4315	2022-11-01 08:52:30.987	5-Angel Rios 	7	CANON INFORMATICO	5324995	245222	245222	0	266	9
4316	2022-11-01 08:52:30.992	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	266	13
4317	2022-11-01 08:52:30.997	5-Angel Rios 	9	FOTOCOPIAS	001-001-0009626	50000	45454	4545	266	12
4318	2022-11-01 08:52:31.004	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	266	14
4319	2022-11-01 08:52:31.01	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	266	27
4320	2022-11-01 08:52:31.013	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	266	11
4321	2022-11-01 08:52:31.013	5-Angel Rios 	13	TASA MIC CO	1976138	102489	102489	0	266	28
4322	2022-11-01 08:52:31.013	5-Angel Rios 	14	TASA UIP CO	002-002-0001427	98089	89171	8917	266	27
4323	2022-11-01 08:52:31.028	5-Angel Rios 	15	TASA IMP. MATERIA PR	x	0	0	0	266	48
4324	2022-11-01 08:52:31.028	5-Angel Rios 	16	APORTE VUE	x	0	0	0	266	23
4325	2022-11-01 08:52:31.044	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	x	3302748	3002498	300249	266	16
4326	2022-11-01 08:52:31.044	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	266	17
4327	2022-11-01 10:56:58.57	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022341H	0	0	0	267	2
4328	2022-11-01 10:56:58.58	5-Angel Rios 	2	INDI	22005IC04022341H	34331	34331	0	267	3
4329	2022-11-01 10:56:58.587	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022341H	1270638	1270638	0	267	5
4330	2022-11-01 10:56:58.594	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022341H	1023692	1023692	0	267	38
4331	2022-11-01 10:56:58.601	5-Angel Rios 	5	I.V.A.	22005IC04022341H	25592299	0	25592299	267	8
4332	2022-11-01 10:56:58.608	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022341H	362363	362363	0	267	35
4333	2022-11-01 10:56:58.618	5-Angel Rios 	7	CANON INFORMATICO	5333831	245222	245222	0	267	9
4334	2022-11-01 10:56:58.624	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	267	13
4335	2022-11-01 10:56:58.632	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018576	70000	63636	6363	267	12
4336	2022-11-01 10:56:58.641	5-Angel Rios 	10	TASA PORTUARIA	002-007-0048993	1484980	1349981	134998	267	14
4337	2022-11-01 10:56:58.649	5-Angel Rios 	11	CDAP PAGOS	001-025-0197149	7247	6588	658	267	27
4338	2022-11-01 10:56:58.657	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0144955	37125	33750	3375	267	11
4339	2022-11-01 10:56:58.665	5-Angel Rios 	13	VISACION MRE	1139318	490445	490445	0	267	28
4340	2022-11-01 10:56:58.67	5-Angel Rios 	14	CDAP PAGOS	001-024-0185686	10000	9090	909	267	27
4341	2022-11-01 10:56:58.68	5-Angel Rios 	15	TASA IMP. MATERIA PR	1967435	691023	691023	0	267	48
4342	2022-11-01 10:56:58.692	5-Angel Rios 	16	APORTE VUE	1967436	75859	68962	6896	267	23
4343	2022-11-01 10:56:58.703	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002146	5799621	5272382	527238	267	16
4344	2022-11-01 10:56:58.709	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	267	17
4345	2022-11-01 10:57:54.658	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022399U	3599885	3599885	0	268	2
4346	2022-11-01 10:57:54.674	5-Angel Rios 	2	INDI	22005IC04022399U	15219	15219	0	268	3
4347	2022-11-01 10:57:54.682	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022399U	128567	128567	0	268	5
4348	2022-11-01 10:57:54.687	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022399U	118698	118698	0	268	38
4349	2022-11-01 10:57:54.693	5-Angel Rios 	5	I.V.A.	22005IC04022399U	2967455	0	2967455	268	8
4350	2022-11-01 10:57:54.702	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022399U	0	0	0	268	35
4351	2022-11-01 10:57:54.709	5-Angel Rios 	7	CANON INFORMATICO	5336239	98089	98089	0	268	9
4352	2022-11-01 10:57:54.717	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017855	175000	159090	15909	268	13
4353	2022-11-01 10:57:54.723	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018577	80000	72727	7272	268	12
4354	2022-11-01 10:57:54.732	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049139	172004	156367	15636	268	14
4355	2022-11-01 10:57:54.739	5-Angel Rios 	11	CDAP PAGOS	001-025-0197419	7247	6588	658	268	27
4356	2022-11-01 10:57:54.746	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0145229	4300	3909	390	268	11
4357	2022-11-01 10:57:54.758	5-Angel Rios 	13	VISACION MRE	x	0	0	0	268	28
4358	2022-11-01 10:57:54.765	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	268	27
4359	2022-11-01 10:57:54.774	5-Angel Rios 	15	TASA IMP. MATERIA PR	x	0	0	0	268	48
4360	2022-11-01 10:57:54.781	5-Angel Rios 	16	APORTE VUE	x	0	0	0	268	23
4361	2022-11-01 10:57:54.787	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002147	1206849	1097135	109713	268	16
4362	2022-11-01 10:57:54.793	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	268	17
4363	2022-11-01 10:58:57.386	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003410A	0	0	0	269	2
4364	2022-11-01 10:58:57.391	5-Angel Rios 	2	INDI	22018EC01003410A	0	0	0	269	3
4365	2022-11-01 10:58:57.395	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003410A	0	0	0	269	5
4366	2022-11-01 10:58:57.406	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003410A	0	0	0	269	38
4367	2022-11-01 10:58:57.408	5-Angel Rios 	5	I.V.A.	22018EC01003410A	0	0	0	269	8
4368	2022-11-01 10:58:57.417	5-Angel Rios 	6	TASA INT. ADUANERA 	22018EC01003410A	0	0	0	269	35
4369	2022-11-01 10:58:57.423	5-Angel Rios 	7	CANON INFORMATICO	5324995	245222	245222	0	269	9
4370	2022-11-01 10:58:57.431	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	269	13
4371	2022-11-01 10:58:57.44	5-Angel Rios 	9	FOTOCOPIAS	001-001-0009626	50000	45454	4545	269	12
4372	2022-11-01 10:58:57.448	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	269	14
4373	2022-11-01 10:58:57.455	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	269	27
4374	2022-11-01 10:58:57.459	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	269	11
4375	2022-11-01 10:58:57.467	5-Angel Rios 	13	TASA MIC CO	1976138	102489	102489	0	269	28
4376	2022-11-01 10:58:57.47	5-Angel Rios 	14	TASA UIP CO	002-002-0001427	98089	89171	8917	269	27
4377	2022-11-01 10:58:57.478	5-Angel Rios 	15	TASA IMP. MATERIA PR	x	0	0	0	269	48
4378	2022-11-01 10:58:57.483	5-Angel Rios 	16	APORTE VUE	x	0	0	0	269	23
4379	2022-11-01 10:58:57.488	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002145	3302748	3002498	300249	269	16
4380	2022-11-01 10:58:57.492	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	269	17
4381	2022-11-01 15:07:47.132	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022651L	11105	11105	0	270	2
4382	2022-11-01 15:07:47.141	5-Angel Rios 	2	INDI	22005IC04022651L	27790	27790	0	270	3
4383	2022-11-01 15:07:47.148	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022651L	327677	327677	0	270	5
4384	2022-11-01 15:07:47.156	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022651L	265196	265196	0	270	38
4385	2022-11-01 15:07:47.163	5-Angel Rios 	5	I.V.A.	22005IC04022651L	6629877	0	6629877	270	8
4386	2022-11-01 15:07:47.171	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022651L	0	0	0	270	35
4387	2022-11-01 15:07:47.18	5-Angel Rios 	7	CANON INFORMATICO	5343350	245222	245222	0	270	9
4388	2022-11-01 15:07:47.186	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017930	175000	159090	15909	270	13
4389	2022-11-01 15:07:47.198	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018587	150000	136363	13636	270	12
4390	2022-11-01 15:07:47.205	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049999	363524	330476	33047	270	14
4391	2022-11-01 15:07:47.21	5-Angel Rios 	11	CDAP PAGOS	001-025-0199069	7286	6623	662	270	27
4392	2022-11-01 15:07:47.213	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0146875	9088	8261	826	270	11
4393	2022-11-01 15:07:47.221	5-Angel Rios 	13	VISACION CONSUL	03055439/40/41/42	648000	648000	0	270	28
4394	2022-11-01 15:07:47.228	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	2105710	1914281	191428	270	16
4395	2022-11-01 15:07:47.237	5-Angel Rios 	115	GASTO ADMIN.	x	0	0	0	270	17
4396	2022-11-01 15:20:16.295	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002128A	0	0	0	271	2
4397	2022-11-01 15:20:16.307	5-Angel Rios 	2	INDI	22005IT02002128A	0	0	0	271	3
4398	2022-11-01 15:20:16.312	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002128A	0	0	0	271	5
4399	2022-11-01 15:20:16.323	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002128A	0	0	0	271	38
4400	2022-11-01 15:20:16.328	5-Angel Rios 	5	I.V.A.	22005IT02002128A	0	0	0	271	8
4401	2022-11-01 15:20:16.338	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IT02002128A	0	0	0	271	35
4402	2022-11-01 15:20:16.348	5-Angel Rios 	7	ROYAL SEGUROS	001-004-0031305	300000	300000	0	271	9
4403	2022-11-01 15:20:16.356	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017929	175000	159090	15909	271	13
4404	2022-11-01 15:20:16.364	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018588	70000	63636	6363	271	12
4405	2022-11-01 15:20:16.369	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049994	137788	125261	12526	271	14
4406	2022-11-01 15:20:16.378	5-Angel Rios 	11	CDAP PAGOS	001-025-0199066	7286	6623	662	271	27
4407	2022-11-01 15:20:16.381	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0146866	3470	3154	315	271	11
4408	2022-11-01 15:20:16.39	5-Angel Rios 	13	VISACION MRE	x	0	0	0	271	28
4409	2022-11-01 15:20:16.395	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1482892	1348083	134808	271	16
4410	2022-11-01 15:20:16.401	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	271	17
4411	2022-11-01 15:31:57.96	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022651L	11105	11105	0	272	2
4412	2022-11-01 15:31:57.973	5-Angel Rios 	2	INDI	22005IC04022651L	27790	27790	0	272	3
4413	2022-11-01 15:31:57.979	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022651L	327677	327677	0	272	5
4414	2022-11-01 15:31:57.985	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022651L	265196	265196	0	272	38
4415	2022-11-01 15:31:57.991	5-Angel Rios 	5	I.V.A.	22005IC04022651L	6629877	0	6629877	272	8
4416	2022-11-01 15:31:57.998	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IC04022651L	0	0	0	272	35
4417	2022-11-01 15:31:58.012	5-Angel Rios 	7	CANON INFORMATICO	5343350	245222	245222	0	272	9
4418	2022-11-01 15:31:58.02	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017930	175000	159090	15909	272	13
4419	2022-11-01 15:31:58.026	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018587	150000	136363	13636	272	12
4420	2022-11-01 15:31:58.035	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049999	363524	330476	33047	272	14
4421	2022-11-01 15:31:58.044	5-Angel Rios 	11	CDAP PAGOS	001-025-0199069	7286	6623	662	272	27
4422	2022-11-01 15:31:58.057	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0146875	9088	8261	826	272	11
4423	2022-11-01 15:31:58.06	5-Angel Rios 	13	VISACION CONSUL	03055439/40/41/42	648000	648000	0	272	28
4424	2022-11-01 15:31:58.069	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002149	2105710	1914281	191428	272	16
4425	2022-11-01 15:31:58.074	5-Angel Rios 	115	GASTO ADMIN.	x	0	0	0	272	17
4426	2022-11-02 07:28:48.989	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002128A	0	0	0	273	2
4427	2022-11-02 07:28:48.998	5-Angel Rios 	2	INDI	22005IT02002128A	0	0	0	273	3
4428	2022-11-02 07:28:48.999	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT02002128A	0	0	0	273	5
4429	2022-11-02 07:28:49.014	5-Angel Rios 	4	IRE GENERAL 700	22005IT02002128A	0	0	0	273	38
4430	2022-11-02 07:28:49.014	5-Angel Rios 	5	I.V.A.	22005IT02002128A	0	0	0	273	8
4431	2022-11-02 07:28:49.03	5-Angel Rios 	6	TASA INT. ADUANERA 	22005IT02002128A	0	0	0	273	35
4432	2022-11-02 07:28:49.03	5-Angel Rios 	7	ROYAL SEGUROS	001-004-0031305	300000	300000	0	273	9
4433	2022-11-02 07:28:49.045	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0017929	175000	159090	15909	273	13
4434	2022-11-02 07:28:49.045	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018588	70000	63636	6363	273	12
4435	2022-11-02 07:28:49.045	5-Angel Rios 	10	TASA PORTUARIA	002-007-0049994	138788	126170	12617	273	14
4436	2022-11-02 07:28:49.061	5-Angel Rios 	11	CDAP PAGOS	001-025-0199066	7286	6623	662	273	27
4437	2022-11-02 07:28:49.061	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0146866	3470	3154	315	273	11
4438	2022-11-02 07:28:49.077	5-Angel Rios 	13	VISACION MRE	x	0	0	0	273	28
4439	2022-11-02 07:28:49.077	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002148	1482892	1348083	134808	273	16
4440	2022-11-02 07:28:49.092	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	273	17
4441	2022-11-02 15:26:31.851	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04022593Z	135058	135058	0	274	2
4442	2022-11-02 15:26:31.859	5-Angel Rios 	2	INDI	22005IC04022593Z	27789	27789	0	274	3
4443	2022-11-02 15:26:31.864	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04022593Z	682773	682773	0	274	5
4444	2022-11-02 15:26:31.869	5-Angel Rios 	4	IRE GENERAL 700	22005IC04022593Z	551191	551191	0	274	38
4445	2022-11-02 15:26:31.876	5-Angel Rios 	5	I.V.A.	22005IC04022593Z	13779738	0	13779738	274	8
4446	2022-11-02 15:26:31.882	5-Angel Rios 	6	CANON INFORMATICO	5343344	245222	245222	0	274	9
4447	2022-11-02 15:26:31.887	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0017931	175000	159090	15909	274	13
4448	2022-11-02 15:26:31.893	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018586	170000	154545	15454	274	12
4449	2022-11-02 15:26:31.9	5-Angel Rios 	9	TASA PORTUARIA	002-007-0049804	754131	685573	68557	274	14
4450	2022-11-02 15:26:31.905	5-Angel Rios 	10	CDAP PAGOS	001-025-0198718	7286	6623	662	274	27
4451	2022-11-02 15:26:31.911	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0146526	18853	17139	1713	274	11
4452	2022-11-02 15:26:31.918	5-Angel Rios 	12	LICENCIA PREVIA CONFECCIONES	1139938	686623	686623	0	274	46
4453	2022-11-02 15:26:31.923	5-Angel Rios 	13	CDAP PAGOS 	001-024-0185546	10000	10000	0	274	47
5973	2023-02-16 14:19:17.677	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	366	27
4454	2022-11-02 15:26:31.929	5-Angel Rios 	14	VISACION CONSUL	03053122/23/24	445500	445500	0	274	28
4455	2022-11-02 15:26:31.936	5-Angel Rios 	15	CDAP PAGOS	x	0	0	0	274	27
4456	2022-11-02 15:26:31.941	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002144	1228025	1116386	111638	274	16
4457	2022-11-02 15:26:31.951	5-Angel Rios 	17	REPOSICION DE GASTOS ADM	781	710000	710000	0	274	21
4458	2022-11-02 15:26:31.958	5-Angel Rios 	18	OTROS	x	0	0	0	274	21
4459	2022-11-07 10:06:58.138	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003478Y	0	0	0	275	2
4460	2022-11-07 10:06:58.144	5-Angel Rios 	2	INDI	22018EC01003478Y	0	0	0	275	3
4461	2022-11-07 10:06:58.16	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003478Y	0	0	0	275	5
4462	2022-11-07 10:06:58.16	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003478Y	0	0	0	275	38
4463	2022-11-07 10:06:58.175	5-Angel Rios 	5	I.V.A.	22018EC01003478Y	0	0	0	275	8
4464	2022-11-07 10:06:58.191	5-Angel Rios 	6	TASA INT. ADUANERA 	22018EC01003478Y	0	0	0	275	35
4465	2022-11-07 10:06:58.191	5-Angel Rios 	7	CANON INFORMATICO	5346459	245222	245222	0	275	9
4466	2022-11-07 10:06:58.204	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	275	13
4467	2022-11-07 10:06:58.204	5-Angel Rios 	9	FOTOCOPIAS	001-001-0009684	50000	45454	4545	275	12
4468	2022-11-07 10:06:58.22	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	275	14
4469	2022-11-07 10:06:58.22	5-Angel Rios 	11	TASA MIC CO	1981023	102489	93171	9317	275	27
4470	2022-11-07 10:06:58.235	5-Angel Rios 	12	TASA UIP CO 	002-002-0001635	98089	89171	8917	275	11
4471	2022-11-07 10:06:58.235	5-Angel Rios 	13	VISACION MRE	x	0	0	0	275	28
4472	2022-11-07 10:06:58.235	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002150	3240122	2945565	294556	275	16
4473	2022-11-07 10:06:58.251	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	275	17
4474	2022-11-08 16:24:48.935	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04022934M	12032820	12032820	0	276	2
4475	2022-11-08 16:24:48.942	5-Angel Rios 	2	INDI	22002IC04022934M	34331	34331	0	276	3
4476	2022-11-08 16:24:48.95	5-Angel Rios 	3	I.S.C.	22002IC04022934M	4557	4557	0	276	4
4477	2022-11-08 16:24:48.958	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04022934M	513164	513164	0	276	5
4478	2022-11-08 16:24:48.971	5-Angel Rios 	5	IRE GENERAL 700	22002IC04022934M	462834	462834	0	276	38
4479	2022-11-08 16:24:48.98	5-Angel Rios 	6	I.V.A.	22002IC04022934M	11570824	0	11570824	276	8
4480	2022-11-08 16:24:48.987	5-Angel Rios 	7	CANON INFORMATICO	22002IC04022934M	245222	245222	0	276	9
4481	2022-11-08 16:24:48.994	5-Angel Rios 	8	FOTOCOPIAS	004-003-0008071	42000	38181	3818	276	12
4482	2022-11-08 16:24:49.008	5-Angel Rios 	9	VISACION MRE	57745	490445	490445	0	276	28
4483	2022-11-08 16:24:49.013	5-Angel Rios 	10	CDAP PAGOS	001-024-0189094	10000	9090	909	276	27
4484	2022-11-08 16:24:49.024	5-Angel Rios 	11	TASA PORTUARIA	001-034-0068225	2838399	2580362	258036	276	14
4485	2022-11-08 16:24:49.03	5-Angel Rios 	12	CDAP DINAC	001-021-0070294	21834	19849	1984	276	31
4486	2022-11-08 16:24:49.035	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002153	3149481	2863164	286316	276	16
4487	2022-11-08 16:24:49.04	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	276	17
4488	2022-11-10 08:56:23.516	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04022711F	2809101	2809101	0	277	2
4489	2022-11-10 08:56:23.528	5-Angel Rios 	2	INDI	22002IC04022711F	34331	34331	0	277	3
4490	2022-11-10 08:56:23.536	5-Angel Rios 	3	I.S.C.	22002IC04022711F	0	0	0	277	4
4491	2022-11-10 08:56:23.546	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04022711F	192289	192289	0	277	5
4492	2022-11-10 08:56:23.551	5-Angel Rios 	5	IRE GENERAL 700	22002IC04022711F	167936	167936	0	277	38
4493	2022-11-10 08:56:23.559	5-Angel Rios 	6	I.V.A.	22002IC04022711F	4198403	0	4198403	277	8
4494	2022-11-10 08:56:23.564	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	277	9
4495	2022-11-10 08:56:23.568	5-Angel Rios 	8	TASA DINAVISA	575657	196178	178343	17834	277	37
4496	2022-11-10 08:56:23.576	5-Angel Rios 	9	CDAP PAGOS	001-024-0186640	10000	9090	909	277	27
4497	2022-11-10 08:56:23.581	5-Angel Rios 	10	VISACION MRE	1141498	490445	490445	0	277	28
4498	2022-11-10 08:56:23.589	5-Angel Rios 	11	CDAP PAGOS	001-024-0186909	10000	9090	909	277	27
4499	2022-11-10 08:56:23.593	5-Angel Rios 	12	TASA PORTUARIA	001-034-0067596	3349985	3045440	304544	277	14
4500	2022-11-10 08:56:23.606	5-Angel Rios 	13	CDAP DINAC	001-021-0069676	21883	19893	1989	277	31
4501	2022-11-10 08:56:23.608	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	698036	634578	63457	277	16
4502	2022-11-10 08:56:23.613	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	660000	660000	0	277	21
4503	2022-11-10 08:56:23.621	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	277	17
4504	2022-11-10 09:15:48.564	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04022713H	5008387	5008387	0	278	2
4505	2022-11-10 09:15:48.572	5-Angel Rios 	2	INDI	22002IC04022713H	34331	34331	0	278	3
4506	2022-11-10 09:15:48.582	5-Angel Rios 	3	I.S.C.	22002IC04022713H	0	0	0	278	4
4507	2022-11-10 09:15:48.588	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04022713H	183190	183190	0	278	5
4508	2022-11-10 09:15:48.594	5-Angel Rios 	5	IRE GENERAL 700	22002IC04022713H	169416	169416	0	278	38
4509	2022-11-10 09:15:48.602	5-Angel Rios 	6	I.V.A.	22002IC04022713H	4235464	0	4235464	278	8
4510	2022-11-10 09:15:48.609	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	278	9
4511	2022-11-10 09:15:48.616	5-Angel Rios 	8	TASA DINAVISA	575656	196178	178343	17834	278	37
4512	2022-11-10 09:15:48.626	5-Angel Rios 	9	CDAP PAGOS	001-024-0186641	10000	9090	909	278	27
4513	2022-11-10 09:15:48.631	5-Angel Rios 	10	VISACION MRE	1141671	490445	490445	0	278	28
4514	2022-11-10 09:15:48.642	5-Angel Rios 	11	CDAP PAGOS	001-024-0186912	10000	9090	909	278	27
4515	2022-11-10 09:15:48.656	5-Angel Rios 	12	TASA PORTUARIA	001-034-0067594	3191865	2901695	290169	278	14
4516	2022-11-10 09:15:48.663	5-Angel Rios 	13	CDAP DINAC	001-021-0069677	21883	19893	1989	278	31
4517	2022-11-10 09:15:48.672	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	678021	616382	61638	278	16
4518	2022-11-10 09:15:48.679	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	880000	880000	0	278	21
4519	2022-11-10 09:15:48.686	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	278	17
4520	2022-11-11 13:06:07.875	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04023521X	0	0	0	279	2
4521	2022-11-11 13:06:07.879	5-Angel Rios 	2	INDI	22005IC04023521X	27790	27790	0	279	3
4522	2022-11-11 13:06:07.879	5-Angel Rios 	3	I.S.C.	22005IC04023521X	0	0	0	279	4
4523	2022-11-11 13:06:07.895	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04023521X	1270249	1270249	0	279	5
4524	2022-11-11 13:06:07.91	5-Angel Rios 	5	IRE GENERAL 700	22005IC04023521X	1022979	1022979	0	279	38
4525	2022-11-11 13:06:07.926	5-Angel Rios 	6	I.V.A.	22005IC04023521X	25574480	0	25574480	279	8
4526	2022-11-11 13:06:07.926	5-Angel Rios 	7	CANON INFORMATICO	5530415	245222	245222	0	279	9
4527	2022-11-11 13:06:07.941	5-Angel Rios 	8	SERV. APERTURA 	x	0	0	0	279	37
4528	2022-11-11 13:06:07.941	5-Angel Rios 	9	FOTOCOPIAS 	001-002-0018648	80000	72727	7272	279	27
4529	2022-11-11 13:06:07.941	5-Angel Rios 	10	VISACION MRE	1148639	490445	490445	0	279	28
4530	2022-11-11 13:06:07.957	5-Angel Rios 	11	CDAP PAGOS	001-024-0193790	10000	9090	909	279	27
4531	2022-11-11 13:06:07.957	5-Angel Rios 	12	TASA PORTUARIA	002-007-0052846	1396842	1269856	126985	279	14
4532	2022-11-11 13:06:07.965	5-Angel Rios 	13	CDAP PAGOS	001-025-0205010	7143	6493	649	279	31
4533	2022-11-11 13:06:07.965	5-Angel Rios 	14	SERV. SISTEMA SOFIA	001-004-0152812	34921	31746	3174	279	16
4534	2022-11-11 13:06:07.98	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002154	4699364	4699364	0	279	21
4535	2022-11-11 13:06:07.98	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	279	17
4536	2022-11-14 11:17:16.315	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04022711F	2809101	2809101	0	280	2
4537	2022-11-14 11:17:16.333	5-Angel Rios 	2	INDI	22002IC04022711F	34331	34331	0	280	3
4538	2022-11-14 11:17:16.341	5-Angel Rios 	3	I.S.C.	22002IC04022711F	0	0	0	280	4
4539	2022-11-14 11:17:16.349	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04022711F	192289	192289	0	280	5
4540	2022-11-14 11:17:16.357	5-Angel Rios 	5	IRE GENERAL 700	22002IC04022711F	167936	167936	0	280	38
4541	2022-11-14 11:17:16.365	5-Angel Rios 	6	I.V.A.	22002IC04022711F	4198403	0	4198403	280	8
4542	2022-11-14 11:17:16.372	5-Angel Rios 	7	CANON INFORMATICO	5340294	250222	250222	0	280	9
4543	2022-11-14 11:17:16.38	5-Angel Rios 	8	TASA DINAVISA	575657	196178	178343	17834	280	37
4544	2022-11-14 11:17:16.386	5-Angel Rios 	9	CDAP PAGOS	001-024-0186640	10000	9090	909	280	27
4545	2022-11-14 11:17:16.391	5-Angel Rios 	10	VISACION MRE	1141498	490445	490445	0	280	28
4546	2022-11-14 11:17:16.396	5-Angel Rios 	11	CDAP PAGOS	001-024-0186909	10000	9090	909	280	27
4547	2022-11-14 11:17:16.405	5-Angel Rios 	12	TASA PORTUARIA	001-034-0067596	3349985	3045440	304544	280	14
4548	2022-11-14 11:17:16.418	5-Angel Rios 	13	CDAP DINAC	001-021-0069676	21883	19893	1989	280	31
4549	2022-11-14 11:17:16.424	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002151	698036	634578	63457	280	16
4550	2022-11-14 11:17:16.435	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	798	450000	450000	0	280	21
4551	2022-11-14 11:17:16.44	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	280	17
4552	2022-11-14 11:22:13.956	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04022713H	5008387	5008387	0	281	2
4553	2022-11-14 11:22:13.963	5-Angel Rios 	2	INDI	22002IC04022713H	34331	34331	0	281	3
4554	2022-11-14 11:22:13.963	5-Angel Rios 	3	I.S.C.	22002IC04022713H	0	0	0	281	4
4555	2022-11-14 11:22:13.963	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04022713H	183190	183190	0	281	5
4556	2022-11-14 11:22:13.979	5-Angel Rios 	5	IRE GENERAL 700	22002IC04022713H	169416	169416	0	281	38
4557	2022-11-14 11:22:13.979	5-Angel Rios 	6	I.V.A.	22002IC04022713H	4235464	0	4235464	281	8
4558	2022-11-14 11:22:13.995	5-Angel Rios 	7	CANON INFORMATICO	5340690	250222	250222	0	281	9
4559	2022-11-14 11:22:14.007	5-Angel Rios 	8	TASA DINAVISA	575656	196178	178343	17834	281	37
4560	2022-11-14 11:22:14.007	5-Angel Rios 	9	CDAP PAGOS	001-024-0186641	10000	9090	909	281	27
4561	2022-11-14 11:22:14.007	5-Angel Rios 	10	VISACION MRE	1141671	490445	490445	0	281	28
4562	2022-11-14 11:22:14.023	5-Angel Rios 	11	CDAP PAGOS	001-024-0186912	10000	9090	909	281	27
4563	2022-11-14 11:22:14.023	5-Angel Rios 	12	TASA PORTUARIA	001-034-0067594	3191865	2901695	290169	281	14
4564	2022-11-14 11:22:14.038	5-Angel Rios 	13	CDAP DINAC	001-021-0069677	21883	19893	1989	281	31
4565	2022-11-14 11:22:14.038	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002152	678021	616382	61638	281	16
4566	2022-11-14 11:22:14.054	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	799	660000	660000	0	281	21
4567	2022-11-14 11:22:14.054	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	281	17
4568	2022-11-14 15:37:03.263	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000596C	5126	5126	0	282	2
4569	2022-11-14 15:37:03.272	5-Angel Rios 	2	INDI	22005IM04000596C	15161	15161	0	282	3
4570	2022-11-14 15:37:03.281	5-Angel Rios 	3	I.S.C.	22005IM04000596C	0	0	0	282	4
4571	2022-11-14 15:37:03.287	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IM04000596C	35313	35313	0	282	5
4572	2022-11-14 15:37:03.296	5-Angel Rios 	5	IRE GENERAL 700	22005IM04000596C	29339	29339	0	282	38
4573	2022-11-14 15:37:03.307	5-Angel Rios 	6	I.V.A.	22005IM04000596C	733472	0	733472	282	8
4574	2022-11-14 15:37:03.314	5-Angel Rios 	7	CANON INFORMATICO	5485584	49044	49044	0	282	9
4575	2022-11-14 15:37:03.324	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-002-0003262	200000	181818	18181	282	13
4576	2022-11-14 15:37:03.329	5-Angel Rios 	9	FOTOCOPIAS	001-001-0092354	80000	72727	7272	282	12
4577	2022-11-14 15:37:03.335	5-Angel Rios 	10	VISACION CONSUL	3056216/17	243000	243000	0	282	15
4578	2022-11-14 15:37:03.347	5-Angel Rios 	11	TASA PORTUARIA	002-007-0051928	41924	38112	3811	282	14
4579	2022-11-14 15:37:03.352	5-Angel Rios 	12	CDAP PAGOS	001-025-0203124	7219	6562	656	282	27
4580	2022-11-14 15:37:03.36	5-Angel Rios 	13	SERVICIOS SOFIA / ANNP	001-004-0150938	1048	952	95	282	11
4581	2022-11-14 15:37:03.367	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002155	979000	890000	89000	282	16
4582	2022-11-14 15:37:03.372	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	282	21
4583	2022-11-18 14:59:05.395	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002225V	0	0	0	283	2
4584	2022-11-18 14:59:05.403	5-Angel Rios 	2	INDI	22005IT02002225V	0	0	0	283	3
4585	2022-11-18 14:59:05.407	5-Angel Rios 	3	I.S.C.	22005IT02002225V	0	0	0	283	4
4586	2022-11-18 14:59:05.419	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IT02002225V	0	0	0	283	5
4587	2022-11-18 14:59:05.424	5-Angel Rios 	5	IRE GENERAL 700	22005IT02002225V	0	0	0	283	38
4588	2022-11-18 14:59:05.435	5-Angel Rios 	6	I.V.A.	22005IT02002225V	0	0	0	283	8
4589	2022-11-18 14:59:05.445	5-Angel Rios 	7	TASA INT. ADUANERA 	22005IT02002225V	351987	351987	0	283	35
4590	2022-11-18 14:59:05.45	5-Angel Rios 	8	ROYAL SEGUROS 	001-004-0000898	3091500	3091500	0	283	9
4591	2022-11-18 14:59:05.457	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018678	70000	63636	6363	283	12
4592	2022-11-18 14:59:05.465	5-Angel Rios 	10	VISACION CONSUL	x	0	0	0	283	15
4593	2022-11-18 14:59:05.469	5-Angel Rios 	11	TASA PORTUARIA	002-007-0053798	1046000	950909	95090	283	14
4594	2022-11-18 14:59:05.477	5-Angel Rios 	12	CDAP PAGOS	001-025-0206760	7039	6399	639	283	27
4595	2022-11-18 14:59:05.485	5-Angel Rios 	13	SERVICIOS SOFIA / ANNP	001-004-0154575	26150	23772	2377	283	11
4596	2022-11-18 14:59:05.494	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002156	5982415	5438559	543855	283	16
4597	2022-11-18 14:59:05.498	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	283	21
4598	2022-11-18 14:59:05.507	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	283	17
4599	2022-11-18 15:15:04.931	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002254A	0	0	0	284	2
4600	2022-11-18 15:15:04.95	5-Angel Rios 	2	INDI	22005IT02002254A	0	0	0	284	3
4601	2022-11-18 15:15:04.952	5-Angel Rios 	3	I.S.C.	22005IT02002254A	0	0	0	284	4
4602	2022-11-18 15:15:04.96	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IT02002254A	0	0	0	284	5
4603	2022-11-18 15:15:04.97	5-Angel Rios 	5	IRE GENERAL 700	22005IT02002254A	0	0	0	284	38
4604	2022-11-18 15:15:04.978	5-Angel Rios 	6	I.V.A.	22005IT02002254A	0	0	0	284	8
4605	2022-11-18 15:15:04.983	5-Angel Rios 	7	TASA INT. ADUANERA 	22005IT02002254A	356986	356986	0	284	35
4606	2022-11-18 15:15:04.988	5-Angel Rios 	8	ROYAL SEGUROS	001-004-0001117	2066000	2066000	0	284	9
4607	2022-11-18 15:15:04.994	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018683	70000	63636	6363	284	12
4608	2022-11-18 15:15:04.998	5-Angel Rios 	10	VISACION CONSUL	x	0	0	0	284	15
4609	2022-11-18 15:15:05.005	5-Angel Rios 	11	TASA PORTUARIA	002-007-0054410	716905	651731	65173	284	14
4610	2022-11-18 15:15:05.01	5-Angel Rios 	12	CDAP PAGOS	001-025-0208331	7139	6490	649	284	27
4611	2022-11-18 15:15:05.016	5-Angel Rios 	13	SERVICIOS SOFIA / ANNP	001-004-0156139	17923	16293	1629	284	11
4612	2022-11-18 15:15:05.021	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002157	4732740	4302490	430249	284	16
4613	2022-11-18 15:15:05.025	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	X	0	0	0	284	21
4614	2022-11-18 15:15:05.037	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	284	17
4615	2022-11-21 09:53:08.888	5-Angel Rios 	1	DERECHO ADUANERO	@	0	0	0	285	2
4616	2022-11-21 09:53:08.892	5-Angel Rios 	2	INDI	@	0	0	0	285	3
4617	2022-11-21 09:53:08.892	5-Angel Rios 	3	I.S.C.	@	0	0	0	285	4
4618	2022-11-21 09:53:08.907	5-Angel Rios 	4	SERVICIO DE VALORACION	@	0	0	0	285	5
4619	2022-11-21 09:53:08.907	5-Angel Rios 	5	IRE GENERAL 700	@	0	0	0	285	38
4620	2022-11-21 09:53:08.923	5-Angel Rios 	6	I.V.A.	@	0	0	0	285	8
4621	2022-11-21 09:53:08.923	5-Angel Rios 	7	TASA INT. ADUANERA 	@	0	0	0	285	35
4622	2022-11-21 09:53:08.939	5-Angel Rios 	8	CANON INFORMATICO	x	245222	245222	0	285	9
4623	2022-11-21 09:53:08.939	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	285	12
4624	2022-11-21 09:53:08.954	5-Angel Rios 	10	VISACION CONSUL	x	0	0	0	285	15
4625	2022-11-21 09:53:08.954	5-Angel Rios 	11	TASA PORTUARIA	x	0	0	0	285	14
4626	2022-11-21 09:53:08.954	5-Angel Rios 	12	CDAP PAGOS	x	0	0	0	285	27
4627	2022-11-21 09:53:08.97	5-Angel Rios 	13	SERVICIOS SOFIA / ANNP	x	0	0	0	285	11
4628	2022-11-21 09:53:08.976	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1659900	1509000	150900	285	16
4629	2022-11-21 09:53:08.976	5-Angel Rios 	15	TASA MIC CO	x	55500	55500	0	285	21
4630	2022-11-21 09:53:08.992	5-Angel Rios 	16	TASA UIP CO	x	55500	55500	0	285	17
4631	2022-11-21 11:29:05.157	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04023965R	0	0	0	286	2
4632	2022-11-21 11:29:05.163	5-Angel Rios 	2	INDI	22002IC04023965R	0	0	0	286	3
4633	2022-11-21 11:29:05.179	5-Angel Rios 	3	I.S.C.	22002IC04023965R	0	0	0	286	4
4634	2022-11-21 11:29:05.197	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04023965R	196655	196655	0	286	5
4635	2022-11-21 11:29:05.209	5-Angel Rios 	5	IRE GENERAL 700	22002IC04023965R	158962	158962	0	286	38
4636	2022-11-21 11:29:05.213	5-Angel Rios 	6	I.V.A.	22002IC04023965R	3974048	0	3974048	286	8
4637	2022-11-21 11:29:05.225	5-Angel Rios 	7	TASA INT. ADUANERA 	22002IC04023965R	0	0	0	286	35
4638	2022-11-21 11:29:05.229	5-Angel Rios 	8	CANON INFORMATICO	5548593	250222	250222	0	286	9
4639	2022-11-21 11:29:05.239	5-Angel Rios 	9	FOTOCOPIAS	004-003-0009309	42000	38181	3818	286	12
4640	2022-11-21 11:29:05.243	5-Angel Rios 	10	VISACION CONSUL	x	0	0	0	286	15
4641	2022-11-21 11:29:05.255	5-Angel Rios 	11	TASA PORTUARIA	001-034-0071015	1089600	990545	99054	286	14
4642	2022-11-21 11:29:05.259	5-Angel Rios 	12	CDAP PAGOS	001-021-0072974	21440	19490	1949	286	27
4643	2022-11-21 11:29:05.266	5-Angel Rios 	13	RETIRO DE GUIA DHL	017-010-0027503	450000	409090	40909	286	11
4644	2022-11-21 11:29:05.274	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002158	1587641	1443310	144331	286	16
4645	2022-11-21 11:29:05.284	5-Angel Rios 	15	REPOSICION DE GASTOS ADM	x	0	0	0	286	21
4646	2022-11-21 11:29:05.29	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	286	17
4647	2022-11-25 13:29:09.992	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04009378F	55866006	55866006	0	287	2
4648	2022-11-25 13:29:10	5-Angel Rios 	2	INDI	22038IC04009378F	34331	34331	0	287	3
4649	2022-11-25 13:29:10.009	5-Angel Rios 	3	I.S.C.	22038IC04009378F	0	0	0	287	4
4650	2022-11-25 13:29:10.015	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04009378F	2327750	2327750	0	287	5
4651	2022-11-25 13:29:10.022	5-Angel Rios 	5	IRE GENERAL 700	22038IC04009378F	2097074	2097074	0	287	38
4652	2022-11-25 13:29:10.03	5-Angel Rios 	6	I.V.A.	22038IC04009378F	52426858	0	52426858	287	8
4653	2022-11-25 13:29:10.036	5-Angel Rios 	7	TASA INT. ADUANERA 	22038IC04009378F	360479	360479	0	287	35
4654	2022-11-25 13:29:10.044	5-Angel Rios 	8	CANON INFORMATICO	22038IC04009378F	245222	245222	0	287	9
4655	2022-11-25 13:29:10.049	5-Angel Rios 	9	FOTOCOPIAS	002-002-0002633	24000	21818	2181	287	12
4656	2022-11-25 13:29:10.053	5-Angel Rios 	10	MOPC	1157635	49045	49045	0	287	34
4657	2022-11-25 13:29:10.069	5-Angel Rios 	11	CDAP PAGOS	001-024-0201610	10000	9090	909	287	27
4658	2022-11-25 13:29:10.074	5-Angel Rios 	12	VISACION MRE	1155573	1471335	1471335	0	287	28
4659	2022-11-25 13:29:10.079	5-Angel Rios 	13	CDAP PAGOS	001-024-0199558	10000	9090	909	287	27
4660	2022-11-25 13:29:10.082	5-Angel Rios 	14	TASA PORTUARIA	002-008-0002001	1404529	1276844	127684	287	14
5974	2023-02-16 14:19:17.685	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	366	15
4661	2022-11-25 13:29:10.1	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002159	4616315	4196650	419665	287	16
4662	2022-11-25 13:29:10.108	5-Angel Rios 	16	GASTO ADMIN.	001-001-0002160	660000	600000	60000	287	17
4663	2022-11-25 13:29:10.112	5-Angel Rios 	17	GASTO ADMIN.	796	600000	545454	54545	287	17
4664	2022-11-25 13:29:10.116	5-Angel Rios 	18	TASA MIC LPI LAMPARAS	5649421	686623	686623	0	287	44
4665	2022-11-25 13:29:10.124	5-Angel Rios 	19	CDAP PAGOS	001-024-0201695	10000	9090	909	287	27
4666	2022-11-25 14:01:47.746	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04009378F	55866006	55866006	0	288	2
4667	2022-11-25 14:01:47.759	5-Angel Rios 	2	INDI	22038IC04009378F	34331	34331	0	288	3
4668	2022-11-25 14:01:47.765	5-Angel Rios 	3	I.S.C.	22038IC04009378F	0	0	0	288	4
4669	2022-11-25 14:01:47.769	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04009378F	2327750	2327750	0	288	5
4670	2022-11-25 14:01:47.786	5-Angel Rios 	5	IRE GENERAL 700	22038IC04009378F	2097074	2097074	0	288	38
4671	2022-11-25 14:01:47.793	5-Angel Rios 	6	I.V.A.	22038IC04009378F	52426858	0	52426858	288	8
4672	2022-11-25 14:01:47.797	5-Angel Rios 	7	TASA INT. ADUANERA 	22038IC04009378F	360479	360479	0	288	35
4673	2022-11-25 14:01:47.804	5-Angel Rios 	8	CANON INFORMATICO	22038IC04009378F	245222	245222	0	288	9
4674	2022-11-25 14:01:47.81	5-Angel Rios 	9	FOTOCOPIAS	002-002-0002633	24000	21818	2181	288	12
4675	2022-11-25 14:01:47.815	5-Angel Rios 	10	MOPC	1157635	49045	49045	0	288	34
4676	2022-11-25 14:01:47.821	5-Angel Rios 	11	CDAP PAGOS	001-024-0201610	10000	9090	909	288	27
4677	2022-11-25 14:01:47.826	5-Angel Rios 	12	VISACION MRE	1155573	1471335	1471335	0	288	28
4678	2022-11-25 14:01:47.834	5-Angel Rios 	13	CDAP PAGOS	001-024-0199558	10000	9090	909	288	27
4679	2022-11-25 14:01:47.838	5-Angel Rios 	14	TASA PORTUARIA	002-008-0002001	1404529	1276844	127684	288	14
4680	2022-11-25 14:01:47.844	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002159	4616315	4196650	419665	288	16
4681	2022-11-25 14:01:47.852	5-Angel Rios 	16	GASTO ADMIN.	001-001-0002160	660000	600000	60000	288	17
4682	2022-11-25 14:01:47.862	5-Angel Rios 	17	GASTO ADMIN.	796	600000	600000	0	288	17
4683	2022-11-25 14:01:47.868	5-Angel Rios 	18	TASA MIC LPI LAMPARAS	5649421	686623	686623	0	288	44
4684	2022-11-25 14:01:47.874	5-Angel Rios 	19	CDAP PAGOS	001-024-0201695	10000	9090	909	288	27
4685	2022-11-29 09:10:02.644	5-Angel Rios 	1	DERECHO ADUANERO	22038IC04009378F	55866006	55866006	0	289	2
4686	2022-11-29 09:10:02.653	5-Angel Rios 	2	INDI	22038IC04009378F	34331	34331	0	289	3
4687	2022-11-29 09:10:02.66	5-Angel Rios 	3	I.S.C.	22038IC04009378F	0	0	0	289	4
4688	2022-11-29 09:10:02.67	5-Angel Rios 	4	SERVICIO DE VALORACION	22038IC04009378F	2327750	2327750	0	289	5
4689	2022-11-29 09:10:02.677	5-Angel Rios 	5	IRE GENERAL 700	22038IC04009378F	2097074	2097074	0	289	38
4690	2022-11-29 09:10:02.686	5-Angel Rios 	6	I.V.A.	22038IC04009378F	52426858	0	52426858	289	8
4691	2022-11-29 09:10:02.693	5-Angel Rios 	7	TASA INT. ADUANERA 	22038IC04009378F	360479	360479	0	289	35
4692	2022-11-29 09:10:02.699	5-Angel Rios 	8	CANON INFORMATICO	22038IC04009378F	245222	245222	0	289	9
4693	2022-11-29 09:10:02.709	5-Angel Rios 	9	FOTOCOPIAS	002-002-0002633	24000	21818	2181	289	12
4694	2022-11-29 09:10:02.714	5-Angel Rios 	10	MOPC	1157635	49045	49045	0	289	34
4695	2022-11-29 09:10:02.718	5-Angel Rios 	11	CDAP PAGOS	001-024-0201610	10000	9090	909	289	27
4696	2022-11-29 09:10:02.726	5-Angel Rios 	12	VISACION MRE	1155573	1471335	1471335	0	289	28
4697	2022-11-29 09:10:02.73	5-Angel Rios 	13	CDAP PAGOS	001-024-0199558	10000	9090	909	289	27
4698	2022-11-29 09:10:02.746	5-Angel Rios 	14	TASA PORTUARIA	002-008-0002001	1404529	1276844	127684	289	14
4699	2022-11-29 09:10:02.754	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002159	4616315	4196650	419665	289	16
4700	2022-11-29 09:10:02.758	5-Angel Rios 	16	GASTO ADMIN.	001-001-0002160	660000	600000	60000	289	17
4701	2022-11-29 09:10:02.77	5-Angel Rios 	17	GASTO ADMIN.	796	600000	545454	54545	289	17
4702	2022-11-29 09:10:02.775	5-Angel Rios 	18	TASA MIC LPI LAMPARAS	5649421	686623	686623	0	289	44
4703	2022-11-29 09:10:02.78	5-Angel Rios 	19	CDAP PAGOS	001-024-0201695	10000	9090	909	289	27
4704	2022-11-29 09:10:02.788	5-Angel Rios 	20	TASA INTN	001-002-0006772	140000	127272	12727	289	49
4705	2022-11-29 09:41:38.407	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003699T	0	0	0	290	2
4706	2022-11-29 09:41:38.412	5-Angel Rios 	2	INDI	22018EC01003699T	0	0	0	290	3
4707	2022-11-29 09:41:38.416	5-Angel Rios 	3	I.S.C.	22018EC01003699T	0	0	0	290	4
4708	2022-11-29 09:41:38.426	5-Angel Rios 	4	SERVICIO DE VALORACION	22018EC01003699T	0	0	0	290	5
4709	2022-11-29 09:41:38.432	5-Angel Rios 	5	IRE GENERAL 700	22018EC01003699T	0	0	0	290	38
4710	2022-11-29 09:41:38.438	5-Angel Rios 	6	I.V.A.	22018EC01003699T	0	0	0	290	8
4711	2022-11-29 09:41:38.447	5-Angel Rios 	7	CANON INFORMATICO	5571128	245222	245222	0	290	9
4712	2022-11-29 09:41:38.451	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009820	50000	45454	4545	290	12
4713	2022-11-29 09:41:38.454	5-Angel Rios 	9	TASA MIC CO	1996153	54545	49586	4958	290	29
4714	2022-11-29 09:41:38.463	5-Angel Rios 	10	TASA UIP CO	002-002-0002186	49045	44586	4458	290	30
4715	2022-11-29 09:41:38.469	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002161	1659713	1508830	150883	290	16
4716	2022-11-29 09:41:38.48	5-Angel Rios 	12	GASTO ADMIN.	x	0	0	0	290	17
4717	2022-11-29 09:47:12.08	5-Angel Rios 	1	DERECHO ADUANERO	22018EM01001393S	0	0	0	291	2
4718	2022-11-29 09:47:12.091	5-Angel Rios 	2	INDI	22018EM01001393S	0	0	0	291	3
4719	2022-11-29 09:47:12.102	5-Angel Rios 	3	I.S.C.	22018EM01001393S	0	0	0	291	4
4720	2022-11-29 09:47:12.112	5-Angel Rios 	4	SERVICIO DE VALORACION	22018EM01001393S	0	0	0	291	5
4721	2022-11-29 09:47:12.118	5-Angel Rios 	5	IRE GENERAL 700	22018EM01001393S	0	0	0	291	38
4722	2022-11-29 09:47:12.128	5-Angel Rios 	6	I.V.A.	22018EM01001393S	0	0	0	291	8
4723	2022-11-29 09:47:12.134	5-Angel Rios 	7	CANON INFORMATICO	22018EM01001393S	245222	245222	0	291	9
4724	2022-11-29 09:47:12.149	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009821	50000	45454	4545	291	12
4725	2022-11-29 09:47:12.17	5-Angel Rios 	9	TASA MIC CO	2000831	103589	94171	9417	291	29
4726	2022-11-29 09:47:12.182	5-Angel Rios 	10	TASA UIP CO	002-002-0002187	98089	89171	8917	291	30
4727	2022-11-29 09:47:12.195	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	001-001-0002162	3331131	3028300	302830	291	16
4728	2022-11-29 09:47:12.21	5-Angel Rios 	12	GASTO ADMIN.	x	0	0	0	291	17
4729	2022-11-29 12:20:07.325	5-Angel Rios 	1	DERECHO ADUANERO	22013IC04000584L	10553450	10553450	0	292	2
4730	2022-11-29 12:20:07.335	5-Angel Rios 	2	INDI	22013IC04000584L	34331	34331	0	292	3
4731	2022-11-29 12:20:07.34	5-Angel Rios 	3	I.S.C.	22013IC04000584L	0	0	0	292	4
4732	2022-11-29 12:20:07.345	5-Angel Rios 	4	SERVICIO DE VALORACION	22013IC04000584L	876230	876230	0	292	5
4733	2022-11-29 12:20:07.349	5-Angel Rios 	5	IRE GENERAL 700	22013IC04000584L	748802	748802	0	292	38
4734	2022-11-29 12:20:07.355	5-Angel Rios 	6	I.V.A.	22013IC04000584L	45193	0	45193	292	8
4735	2022-11-29 12:20:07.361	5-Angel Rios 	7	I.V.A. TURISMO 	22013IC04000584L	1867485	0	1867485	292	26
4736	2022-11-29 12:20:07.365	5-Angel Rios 	8	CANON INFORMATICO	22013IC04000584L	245222	245222	0	292	9
4737	2022-11-29 12:20:07.373	5-Angel Rios 	9	FOTOCOPIAS	016-002-0000290	150000	136363	13636	292	12
4738	2022-11-29 12:20:07.379	5-Angel Rios 	10	TASA PORTUARIA	002-017-0002072	2816301	2560273	256027	292	14
4739	2022-11-29 12:20:07.385	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	x	2668853	2426230	242623	292	16
4740	2022-11-29 12:20:07.391	5-Angel Rios 	12	GASTO ADMIN.	x	2650000	2409090	240909	292	17
4741	2022-11-29 12:20:07.397	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	292	17
4742	2022-12-01 10:02:56.296	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01002739N	0	0	0	293	2
4743	2022-12-01 10:02:56.303	5-Angel Rios 	2	INDI	22018EC01002739N	0	0	0	293	3
4744	2022-12-01 10:02:56.311	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01002739N	0	0	0	293	5
4745	2022-12-01 10:02:56.318	5-Angel Rios 	4	IRE GENERAL 700	22018EC01002739N	0	0	0	293	38
4746	2022-12-01 10:02:56.328	5-Angel Rios 	5	I.V.A.	22018EC01002739N	0	0	0	293	8
4747	2022-12-01 10:02:56.338	5-Angel Rios 	6	CANON INFORMATICO	4736242	245222	245222	0	293	9
4748	2022-12-01 10:02:56.343	5-Angel Rios 	7	FOTOCOPIAS	001-001-0009302	50000	45454	4545	293	12
4749	2022-12-01 10:02:56.349	5-Angel Rios 	8	TASA UIP CO	002-002-0000002	98089	89171	8917	293	30
4750	2022-12-01 10:02:56.358	5-Angel Rios 	9	TASA MIC CO	1926259	98089	89171	8917	293	29
4751	2022-12-01 10:02:56.361	5-Angel Rios 	10	HON. DESP. S/ LEY 220/93	001-001-0002081	3029000	2753636	275363	293	16
4752	2022-12-01 10:02:56.37	5-Angel Rios 	11	PROG PRODUCCION	0000762	2424295	2203904	220390	293	17
4753	2022-12-01 10:05:55.336	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04018451Y	0	0	0	294	2
4754	2022-12-01 10:05:55.342	5-Angel Rios 	2	INDI	22005IC04018451Y	34331	34331	0	294	3
4755	2022-12-01 10:05:55.35	5-Angel Rios 	3	TASA INTER. ADUAN.	22005IC04018451Y	345297	345297	0	294	24
4756	2022-12-01 10:05:55.355	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04018451Y	1207139	1207139	0	294	5
4757	2022-12-01 10:05:55.363	5-Angel Rios 	5	IRE GENERAL 700	22005IC04018451Y	972639	972639	0	294	38
4758	2022-12-01 10:05:55.368	5-Angel Rios 	6	I.V.A.	22005IC04018451Y	24315978	0	24315978	294	8
4759	2022-12-01 10:05:55.373	5-Angel Rios 	7	CANON INFORMATICO	4889560	245222	245222	0	294	9
4760	2022-12-01 10:05:55.379	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	294	13
4761	2022-12-01 10:05:55.387	5-Angel Rios 	9	FOTOCOPIAS	001-001-0091101	70000	63636	6363	294	12
4762	2022-12-01 10:05:55.392	5-Angel Rios 	10	TASA PORTUARIA	002-007-0035496	1416747	1287951	128795	294	14
4763	2022-12-01 10:05:55.396	5-Angel Rios 	11	CDAP PAGOS	001-025-0169902	6905	6277	627	294	27
4764	2022-12-01 10:05:55.403	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0117708	35419	32199	3219	294	11
4765	2022-12-01 10:05:55.407	5-Angel Rios 	13	VISACION MRE	1106158	490445	490445	0	294	28
4766	2022-12-01 10:05:55.415	5-Angel Rios 	14	CDAP PAGOS	001-024-0160229	10000	9090	909	294	27
4767	2022-12-01 10:05:55.42	5-Angel Rios 	15	MIPYMES MIC	1921915	691023	628202	62820	294	22
4768	2022-12-01 10:05:55.43	5-Angel Rios 	16	APORTE VUE	1921916	73199	66544	6654	294	23
4769	2022-12-01 10:05:55.436	5-Angel Rios 	17	HON. DESP. S/ LEY 220/93	001-001-0002091	5914853	5377139	537713	294	16
4770	2022-12-01 10:05:55.438	5-Angel Rios 	18	REPOSICION DE GASTOS ADM	x	0	0	0	294	21
4771	2022-12-05 11:28:08.507	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04025243L	444645	444645	0	295	2
4772	2022-12-05 11:28:08.517	5-Angel Rios 	2	INDI	22005IC04025243L	22750	22750	0	295	3
4773	2022-12-05 11:28:08.525	5-Angel Rios 	3	I.S.C.	22005IC04025243L	0	0	0	295	4
4774	2022-12-05 11:28:08.531	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04025243L	1342831	1342831	0	295	5
4775	2022-12-05 11:28:08.537	5-Angel Rios 	5	IRE GENERAL 700	22005IC04025243L	1082806	1082806	0	295	38
4776	2022-12-05 11:28:08.549	5-Angel Rios 	6	I.V.A.	22005IC04025243L	27070141	0	27070141	295	8
4777	2022-12-05 11:28:08.553	5-Angel Rios 	7	CANON INFORMATICO	5687146	245222	245222	0	295	9
4778	2022-12-05 11:28:08.561	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0018643	175000	159090	15909	295	13
4779	2022-12-05 11:28:08.569	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018746	80000	72727	7272	295	12
4780	2022-12-05 11:28:08.588	5-Angel Rios 	10	TASA PORTUARIA	002-001-0004648	1406338	1278489	127848	295	14
4781	2022-12-05 11:28:08.594	5-Angel Rios 	11	CDAP PAGOS	001-025-0000764	7218	6561	656	295	27
4782	2022-12-05 11:28:08.604	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0165559	35158	31961	3196	295	11
4783	2022-12-05 11:28:08.61	5-Angel Rios 	13	VISACION CONSUL	03100896/97/98	371000	371000	0	295	15
4784	2022-12-05 11:28:08.619	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002163	5770270	5245700	524570	295	16
4785	2022-12-05 11:28:08.627	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	295	17
4786	2022-12-05 11:49:48.338	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04025243L	444645	444645	0	296	2
4787	2022-12-05 11:49:48.345	5-Angel Rios 	2	INDI	22005IC04025243L	22750	22750	0	296	3
4788	2022-12-05 11:49:48.351	5-Angel Rios 	3	I.S.C.	22005IC04025243L	0	0	0	296	4
4789	2022-12-05 11:49:48.36	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04025243L	1342831	1342831	0	296	5
4790	2022-12-05 11:49:48.368	5-Angel Rios 	5	IRE GENERAL 700	22005IC04025243L	1082806	1082806	0	296	38
4791	2022-12-05 11:49:48.375	5-Angel Rios 	6	I.V.A.	22005IC04025243L	27070141	0	27070141	296	8
4792	2022-12-05 11:49:48.385	5-Angel Rios 	7	CANON INFORMATICO	5687146	245222	245222	0	296	9
4793	2022-12-05 11:49:48.387	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0018643	175000	159090	15909	296	13
4794	2022-12-05 11:49:48.394	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018746	80000	72727	7272	296	12
4795	2022-12-05 11:49:48.398	5-Angel Rios 	10	TASA PORTUARIA	002-001-0004648	1406338	1278489	127848	296	14
4796	2022-12-05 11:49:48.41	5-Angel Rios 	11	CDAP PAGOS	001-025-0000764	7218	6561	656	296	27
4797	2022-12-05 11:49:48.415	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0165559	35158	31961	3196	296	11
4798	2022-12-05 11:49:48.42	5-Angel Rios 	13	VISACION CONSUL	03100896/97/98	371000	371000	0	296	15
4799	2022-12-05 11:49:48.426	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002163	5770270	5245700	524570	296	16
4800	2022-12-05 11:49:48.432	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	296	17
4801	2022-12-05 16:23:03.674	5-Angel Rios 	1	DERECHO ADUANERO	PRESUPUESTO 	0	0	0	297	2
4802	2022-12-05 16:23:03.681	5-Angel Rios 	2	INDI	PRESUPUESTO 	27790	27790	0	297	3
4803	2022-12-05 16:23:03.686	5-Angel Rios 	3	I.S.C.	PRESUPUESTO 	0	0	0	297	4
4804	2022-12-05 16:23:03.693	5-Angel Rios 	4	SERVICIO DE VALORACION	PRESUPUESTO 	297977	297977	0	297	5
4805	2022-12-05 16:23:03.701	5-Angel Rios 	5	IRE GENERAL 700	PRESUPUESTO 	241272	241272	0	297	38
4806	2022-12-05 16:23:03.707	5-Angel Rios 	6	I.V.A.	PRESUPUESTO 	6031807	0	6031807	297	8
4807	2022-12-05 16:23:03.711	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	297	9
4808	2022-12-05 16:23:03.714	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	175000	159090	15909	297	13
4809	2022-12-05 16:23:03.723	5-Angel Rios 	9	FOTOCOPIAS	x	80000	72727	7272	297	12
4810	2022-12-05 16:23:03.727	5-Angel Rios 	10	TASA PORTUARIA	x	390000	354545	35454	297	14
4811	2022-12-05 16:23:03.734	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	297	27
4812	2022-12-05 16:23:03.74	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	297	11
4813	2022-12-05 16:23:03.76	5-Angel Rios 	13	VISACION CONSUL	x	650000	650000	0	297	15
4814	2022-12-05 16:23:03.768	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1851023	1682748	168274	297	16
4815	2022-12-05 16:23:03.776	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	297	17
4816	2022-12-09 09:18:23.371	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002407A	0	0	0	298	2
4817	2022-12-09 09:18:23.379	5-Angel Rios 	2	INDI	22005IT02002407A	0	0	0	298	3
4818	2022-12-09 09:18:23.388	5-Angel Rios 	3	I.S.C.	22005IT02002407A	0	0	0	298	4
4819	2022-12-09 09:18:23.395	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IT02002407A	0	0	0	298	5
4820	2022-12-09 09:18:23.4	5-Angel Rios 	5	IRE GENERAL 700	22005IT02002407A	0	0	0	298	38
4821	2022-12-09 09:18:23.409	5-Angel Rios 	6	I.V.A.	22005IT02002407A	0	0	0	298	8
4822	2022-12-09 09:18:23.412	5-Angel Rios 	7	CANON INFORMATICO	X	0	0	0	298	9
4823	2022-12-09 09:18:23.421	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	298	13
4824	2022-12-09 09:18:23.425	5-Angel Rios 	9	FOTOCOPIAS	001-001-0001726	70000	63636	6363	298	12
4825	2022-12-09 09:18:23.433	5-Angel Rios 	10	TASA PORTUARIA	002-001-0006124	837247	761133	76113	298	14
4826	2022-12-09 09:18:23.443	5-Angel Rios 	11	CDAP PAGOS	001-025-0003629	7149	6499	649	298	27
4827	2022-12-09 09:18:23.448	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0168430	20931	19028	1902	298	11
4828	2022-12-09 09:18:23.457	5-Angel Rios 	13	ROYAL SEGUROS	001-004-0002739	2485500	2259545	225954	298	50
4829	2022-12-09 09:18:23.463	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002164	5139895	4672631	467263	298	16
4830	2022-12-09 09:18:23.471	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	298	17
4831	2022-12-09 09:28:20.871	5-Angel Rios 	1	DERECHO ADUANERO	22005IT02002407A	0	0	0	299	2
4832	2022-12-09 09:28:20.9	5-Angel Rios 	2	INDI	22005IT02002407A	0	0	0	299	3
4833	2022-12-09 09:28:20.902	5-Angel Rios 	3	I.S.C.	22005IT02002407A	0	0	0	299	4
4834	2022-12-09 09:28:20.91	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IT02002407A	0	0	0	299	5
4835	2022-12-09 09:28:20.916	5-Angel Rios 	5	IRE GENERAL 700	22005IT02002407A	0	0	0	299	38
4836	2022-12-09 09:28:20.92	5-Angel Rios 	6	I.V.A.	22005IT02002407A	0	0	0	299	8
4837	2022-12-09 09:28:20.93	5-Angel Rios 	7	CANON INFORMATICO	X	0	0	0	299	9
4838	2022-12-09 09:28:20.932	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	299	13
4839	2022-12-09 09:28:20.94	5-Angel Rios 	9	FOTOCOPIAS	001-001-0001726	70000	63636	6363	299	12
4840	2022-12-09 09:28:20.949	5-Angel Rios 	10	TASA PORTUARIA	002-001-0006124	837247	761133	76113	299	14
4841	2022-12-09 09:28:20.955	5-Angel Rios 	11	CDAP PAGOS	001-025-0003629	7149	6499	649	299	27
4842	2022-12-09 09:28:20.96	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0168430	20931	19028	1902	299	11
4843	2022-12-09 09:28:20.97	5-Angel Rios 	13	ROYAL SEGUROS	001-004-0002739	2485500	2259545	225954	299	50
4844	2022-12-09 09:28:20.975	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002164	5139895	4672631	467263	299	16
4845	2022-12-09 09:28:20.983	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	299	17
4846	2022-12-12 14:17:47.536	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04007922P	34893965	34893965	0	300	2
4847	2022-12-12 14:17:47.555	5-Angel Rios 	2	INDI	22032IC04007922P	34331	34331	0	300	3
4848	2022-12-12 14:17:47.564	5-Angel Rios 	3	I.S.C.	22032IC04007922P	0	0	0	300	4
4849	2022-12-12 14:17:47.571	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04007922P	969276	969276	0	300	5
4850	2022-12-12 14:17:47.582	5-Angel Rios 	5	IRE GENERAL 700	22032IC04007922P	920974	920974	0	300	38
4851	2022-12-12 14:17:47.589	5-Angel Rios 	6	I.V.A.	22032IC04007922P	23024338	0	23024338	300	8
4852	2022-12-12 14:17:47.594	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04007922P	357486	357486	0	300	35
4853	2022-12-12 14:17:47.6	5-Angel Rios 	8	CANON INFORMATICO	5750102	245222	245222	0	300	9
4854	2022-12-12 14:17:47.608	5-Angel Rios 	9	FOTOCOPIAS	023-002-0002238	35200	32000	3200	300	12
4855	2022-12-12 14:17:47.612	5-Angel Rios 	10	VISACION MRE	1165262	980890	980890	0	300	28
4856	2022-12-12 14:17:47.62	5-Angel Rios 	11	CDAP PAGOS	001-024-0003292	10000	9090	909	300	27
4857	2022-12-12 14:17:47.63	5-Angel Rios 	12	MOPC	1165305	49045	49045	0	300	34
4858	2022-12-12 14:17:47.638	5-Angel Rios 	13	CDAP PAGOS	001-024-0003295	10000	9090	909	300	27
4859	2022-12-12 14:17:47.643	5-Angel Rios 	14	TASA PORTUARIA	001-005-17187	6652175	6047431	604743	300	14
4860	2022-12-12 14:17:47.649	5-Angel Rios 	15	REGISTRO DE FIRMA 22-23	801	800000	800000	0	300	41
4861	2022-12-12 14:17:47.658	5-Angel Rios 	16	GASTO ADMIN.	802	3000000	2727272	272727	300	17
4862	2022-12-12 14:17:47.664	5-Angel Rios 	17	GASTO ADMIN.	803	900000	818181	81818	300	17
4863	2022-12-12 14:17:47.673	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	001-001-0002165	2771204	2519276	251927	300	16
4864	2022-12-12 14:38:33.853	5-Angel Rios 	1	DERECHO ADUANERO	22013IC04000584L	10553450	10553450	0	301	2
4865	2022-12-12 14:38:33.863	5-Angel Rios 	2	INDI	22013IC04000584L	34331	34331	0	301	3
4866	2022-12-12 14:38:33.865	5-Angel Rios 	3	I.S.C.	22013IC04000584L	0	0	0	301	4
4867	2022-12-12 14:38:33.874	5-Angel Rios 	4	SERVICIO DE VALORACION	22013IC04000584L	876230	876230	0	301	5
4868	2022-12-12 14:38:33.88	5-Angel Rios 	5	IRE GENERAL 700	22013IC04000584L	748802	748802	0	301	38
4869	2022-12-12 14:38:33.884	5-Angel Rios 	6	I.V.A.	22013IC04000584L	45193	0	45193	301	8
4870	2022-12-12 14:38:33.892	5-Angel Rios 	7	I.V.A. TURISMO 	22013IC04000584L	1867485	0	1867485	301	26
4871	2022-12-12 14:38:33.896	5-Angel Rios 	8	CANON INFORMATICO	22013IC04000584L	245222	245222	0	301	9
4872	2022-12-12 14:38:33.907	5-Angel Rios 	9	FOTOCOPIAS	016-002-0000290	150000	136363	13636	301	12
4873	2022-12-12 14:38:33.92	5-Angel Rios 	10	TASA PORTUARIA	002-017-0002072	2816301	2560273	256027	301	14
4874	2022-12-12 14:38:33.923	5-Angel Rios 	11	HON. DESP. S/ LEY 220/93	x	0	0	0	301	16
4875	2022-12-12 14:38:33.933	5-Angel Rios 	12	GASTO ADMIN.	x	2650000	2409090	240909	301	17
4876	2022-12-12 14:38:33.938	5-Angel Rios 	13	GASTO ADMIN.	x	0	0	0	301	17
4877	2022-12-12 14:55:19.754	5-Angel Rios 	1	DERECHO ADUANERO	22032IC04007922P	34893965	34893965	0	302	2
4878	2022-12-12 14:55:19.782	5-Angel Rios 	2	INDI	22032IC04007922P	34331	34331	0	302	3
4879	2022-12-12 14:55:19.789	5-Angel Rios 	3	I.S.C.	22032IC04007922P	0	0	0	302	4
4880	2022-12-12 14:55:19.797	5-Angel Rios 	4	SERVICIO DE VALORACION	22032IC04007922P	969276	969276	0	302	5
4881	2022-12-12 14:55:19.805	5-Angel Rios 	5	IRE GENERAL 700	22032IC04007922P	920974	920974	0	302	38
4882	2022-12-12 14:55:19.821	5-Angel Rios 	6	I.V.A.	22032IC04007922P	23024338	0	23024338	302	8
4883	2022-12-12 14:55:19.827	5-Angel Rios 	7	TASA INT. ADUANERA 	22032IC04007922P	357486	357486	0	302	35
4884	2022-12-12 14:55:19.837	5-Angel Rios 	8	CANON INFORMATICO	5750102	245222	245222	0	302	9
4885	2022-12-12 14:55:19.84	5-Angel Rios 	9	FOTOCOPIAS	023-002-0002238	35200	32000	3200	302	12
4886	2022-12-12 14:55:19.848	5-Angel Rios 	10	VISACION MRE	1165262	980890	980890	0	302	28
4887	2022-12-12 14:55:19.854	5-Angel Rios 	11	CDAP PAGOS	001-024-0003292	10000	9090	909	302	27
4888	2022-12-12 14:55:19.858	5-Angel Rios 	12	MOPC	1165305	49045	49045	0	302	34
4889	2022-12-12 14:55:19.868	5-Angel Rios 	13	CDAP PAGOS	001-024-0003295	10000	9090	909	302	27
4890	2022-12-12 14:55:19.876	5-Angel Rios 	14	TASA PORTUARIA	001-005-17187	6652175	6047431	604743	302	14
4891	2022-12-12 14:55:19.885	5-Angel Rios 	15	REGISTRO DE FIRMA 22-23	801	800000	800000	0	302	41
4892	2022-12-12 14:55:19.895	5-Angel Rios 	16	GASTO ADMIN.	802	3000000	2727272	272727	302	17
4893	2022-12-12 14:55:19.903	5-Angel Rios 	17	GASTO ADMIN.	803	900000	818181	81818	302	17
4894	2022-12-12 14:55:19.909	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	001-001-0002165	3152000	2865454	286545	302	16
4895	2022-12-13 09:49:12.825	5-Angel Rios 	1	DERECHO ADUANERO	22015IC04000064@	0	0	0	303	2
4896	2022-12-13 09:49:12.843	5-Angel Rios 	2	INDI	22015IC04000064@	68662	68662	0	303	3
4897	2022-12-13 09:49:12.86	5-Angel Rios 	3	I.S.C.	22015IC04000064@	5019229	5019229	0	303	4
4898	2022-12-13 09:49:12.877	5-Angel Rios 	4	SERVICIO DE VALORACION	22015IC04000064@	2491907	2491907	0	303	5
4899	2022-12-13 09:49:12.885	5-Angel Rios 	5	IRE GENERAL 700	22015IC04000064@	2027768	2027768	0	303	38
4900	2022-12-13 09:49:12.894	5-Angel Rios 	6	I.V.A.	22015IC04000064@	50694210	0	50694210	303	8
4901	2022-12-13 09:49:12.901	5-Angel Rios 	8	CANON INFORMATICO	x	245222	245222	0	303	9
4902	2022-12-13 09:49:12.908	5-Angel Rios 	9	FOTOCOPIAS	x	80000	72727	7272	303	12
4903	2022-12-13 09:49:12.916	5-Angel Rios 	10	VISACION MRE	x	980890	980890	0	303	28
4904	2022-12-13 09:49:12.926	5-Angel Rios 	11	CDAP PAGOS	x	10000	9090	909	303	27
4905	2022-12-13 09:49:12.931	5-Angel Rios 	14	TASA PORTUARIA	x	3488670	3171518	317151	303	14
4906	2022-12-13 09:49:12.942	5-Angel Rios 	16	GASTO ADMIN.	x	1800000	1636363	163636	303	17
4907	2022-12-13 09:49:12.947	5-Angel Rios 	18	HON. DESP. S/ LEY 220/93	x	4724659	4295144	429514	303	16
4908	2022-12-13 09:49:57.349	5-Angel Rios 	1	DERECHO ADUANERO	22015IC04000064@	0	0	0	304	2
4909	2022-12-13 09:49:57.358	5-Angel Rios 	2	INDI	22015IC04000064@	68662	68662	0	304	3
4910	2022-12-13 09:49:57.366	5-Angel Rios 	3	I.S.C.	22015IC04000064@	5019229	5019229	0	304	4
4911	2022-12-13 09:49:57.371	5-Angel Rios 	4	SERVICIO DE VALORACION	22015IC04000064@	2491907	2491907	0	304	5
4912	2022-12-13 09:49:57.38	5-Angel Rios 	5	IRE GENERAL 700	22015IC04000064@	2027768	2027768	0	304	38
4913	2022-12-13 09:49:57.384	5-Angel Rios 	6	I.V.A.	22015IC04000064@	50694210	0	50694210	304	8
4914	2022-12-13 09:49:57.393	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	304	9
4915	2022-12-13 09:49:57.398	5-Angel Rios 	8	FOTOCOPIAS	x	80000	72727	7272	304	12
4916	2022-12-13 09:49:57.404	5-Angel Rios 	9	VISACION MRE	x	980890	980890	0	304	28
4917	2022-12-13 09:49:57.416	5-Angel Rios 	10	CDAP PAGOS	x	10000	9090	909	304	27
4918	2022-12-13 09:49:57.423	5-Angel Rios 	11	TASA PORTUARIA	x	3488670	3171518	317151	304	14
4919	2022-12-13 09:49:57.428	5-Angel Rios 	12	GASTO ADMIN.	x	1800000	1636363	163636	304	17
4920	2022-12-13 09:49:57.434	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	4724659	4295144	429514	304	16
4921	2022-12-13 09:51:55.19	5-Angel Rios 	1	DERECHO ADUANERO	22015IC04000064@	0	0	0	305	2
4922	2022-12-13 09:51:55.214	5-Angel Rios 	2	INDI	22015IC04000064@	68662	68662	0	305	3
4923	2022-12-13 09:51:55.227	5-Angel Rios 	3	I.S.C.	22015IC04000064@	5019229	5019229	0	305	4
4924	2022-12-13 09:51:55.236	5-Angel Rios 	4	SERVICIO DE VALORACION	22015IC04000064@	2491907	2491907	0	305	5
4925	2022-12-13 09:51:55.25	5-Angel Rios 	5	IRE GENERAL 700	22015IC04000064@	2027768	2027768	0	305	38
4926	2022-12-13 09:51:55.256	5-Angel Rios 	6	I.V.A.	22015IC04000064@	50694210	0	50694210	305	8
4927	2022-12-13 09:51:55.26	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	305	9
4928	2022-12-13 09:51:55.268	5-Angel Rios 	8	FOTOCOPIAS	x	80000	72727	7272	305	12
4929	2022-12-13 09:51:55.283	5-Angel Rios 	9	VISACION MRE	x	980890	980890	0	305	28
4930	2022-12-13 09:51:55.286	5-Angel Rios 	10	CDAP PAGOS	x	10000	9090	909	305	27
4931	2022-12-13 09:51:55.294	5-Angel Rios 	11	TASA PORTUARIA	x	3488670	3171518	317151	305	14
4932	2022-12-13 09:51:55.3	5-Angel Rios 	12	GASTO ADMIN.	x	1800000	1636363	163636	305	17
4933	2022-12-13 09:51:55.305	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	4724659	4295144	429514	305	16
4934	2022-12-14 13:19:48.448	5-Angel Rios 	1	SERVICIO DE VALORACION	22002IM04012189A	52872	52872	0	306	5
4935	2022-12-14 13:19:48.456	5-Angel Rios 	2	IRE GENERAL 700	22002IM04012189A	42943	42943	0	306	38
4936	2022-12-14 13:19:48.456	5-Angel Rios 	3	I.V.A.	22002IM04012189A	1073577	0	1073577	306	8
4937	2022-12-14 13:19:48.472	5-Angel Rios 	4	CANON INFORMATICO	5776549	54044	54044	0	306	9
4938	2022-12-14 13:19:48.472	5-Angel Rios 	5	FOTOCOPIAS	001-001-0013148	30000	27272	2727	306	12
4939	2022-12-14 13:19:48.488	5-Angel Rios 	6	RETIRO DE GUÍA 	001-001-0040944	330000	330000	0	306	28
4940	2022-12-14 13:19:48.488	5-Angel Rios 	7	CDAP PAGOS	001-021-0001576	21713	19739	1973	306	27
4941	2022-12-14 13:19:48.503	5-Angel Rios 	8	TASA PORTUARIA	001-034-0075400	159212	144738	14473	306	14
4942	2022-12-14 13:19:48.503	5-Angel Rios 	9	HON. DESP. S/ LEY 220/93	001-001-0002166	0	0	0	306	16
4943	2022-12-14 14:20:14.73	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04025979E	836444	836444	0	307	2
4944	2022-12-14 14:20:14.737	5-Angel Rios 	2	INDI	22005IC04025979E	27790	27790	0	307	3
4945	2022-12-14 14:20:14.737	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04025979E	1055818	1055818	0	307	5
4946	2022-12-14 14:20:14.737	5-Angel Rios 	4	IRE GENERAL 700	22005IC04025979E	853922	853922	0	307	38
4947	2022-12-14 14:20:14.752	5-Angel Rios 	5	I.V.A.	22005IC04025979E	21348060	0	21348060	307	8
4948	2022-12-14 14:20:14.752	5-Angel Rios 	6	CANON INFORMATICO	5780108	245222	245222	0	307	9
4949	2022-12-14 14:20:14.752	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0018829	175000	159090	15909	307	13
4950	2022-12-14 14:20:14.768	5-Angel Rios 	8	FOTOCOPIAS	001-003-0000321	140000	127272	12727	307	12
4951	2022-12-14 14:20:14.768	5-Angel Rios 	9	TASA PORTUARIA	002-001-0007156	1122910	1020827	102082	307	14
4952	2022-12-14 14:20:14.781	5-Angel Rios 	10	CDAP PAGOS	001-025-0005837	7219	6562	656	307	27
4953	2022-12-14 14:20:14.781	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0170637	28073	25520	2552	307	11
4954	2022-12-14 14:20:14.781	5-Angel Rios 	12	VISACION MRE	1168238	490445	490445	0	307	28
4955	2022-12-14 14:20:14.797	5-Angel Rios 	13	CDAP PAGOS	001-024-0006704	10000	9090	909	307	27
4956	2022-12-14 14:20:14.797	5-Angel Rios 	14	VISACION CONSUL	3104868/69	248000	248000	0	307	15
4957	2022-12-14 14:20:14.813	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002167	3140440	2854945	285494	307	16
4958	2022-12-14 14:20:14.813	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	307	17
4959	2022-12-14 14:40:13.633	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04026060J	0	0	0	308	2
4960	2022-12-14 14:40:13.641	5-Angel Rios 	2	INDI	22005IC04026060J	40320	40320	0	308	3
4961	2022-12-14 14:40:13.641	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04026060J	1849889	1849889	0	308	5
4962	2022-12-14 14:40:13.641	5-Angel Rios 	4	IRE GENERAL 700	22005IC04026060J	1489774	1489774	0	308	38
4963	2022-12-14 14:40:13.656	5-Angel Rios 	5	I.V.A.	22005IC04026060J	37244362	0	37244362	308	8
4964	2022-12-14 14:40:13.656	5-Angel Rios 	6	CANON INFORMATICO	5784281	245222	245222	0	308	9
4965	2022-12-14 14:40:13.684	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	308	13
4966	2022-12-14 14:40:13.69	5-Angel Rios 	8	FOTOCOPIAS	001-003-0000322	150000	136363	13636	308	12
4967	2022-12-14 14:40:13.695	5-Angel Rios 	9	TASA PORTUARIA	002-001-0007445	1808448	1644043	164404	308	14
4968	2022-12-14 14:40:13.701	5-Angel Rios 	10	CDAP PAGOS	001-025-0006320	7219	6562	656	308	27
4969	2022-12-14 14:40:13.707	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0171121	45211	41100	4110	308	11
4970	2022-12-14 14:40:13.716	5-Angel Rios 	12	VISACION MRE	x	0	0	0	308	28
4971	2022-12-14 14:40:13.724	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	308	27
4972	2022-12-14 14:40:13.732	5-Angel Rios 	14	VISACION CONSUL	3109667/69/70/71	1030000	1030000	0	308	15
4973	2022-12-14 14:40:13.732	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002168	4359786	3963441	396344	308	16
4974	2022-12-14 14:40:13.732	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	308	17
4975	2022-12-14 14:53:56.264	5-Angel Rios 	1	SERVICIO DE VALORACION	22002IM04012189A	52872	52872	0	309	5
4976	2022-12-14 14:53:56.271	5-Angel Rios 	2	IRE GENERAL 700	22002IM04012189A	42943	42943	0	309	38
4977	2022-12-14 14:53:56.273	5-Angel Rios 	3	I.V.A.	22002IM04012189A	1073577	0	1073577	309	8
4978	2022-12-14 14:53:56.273	5-Angel Rios 	4	CANON INFORMATICO	5776549	54044	54044	0	309	9
4979	2022-12-14 14:53:56.288	5-Angel Rios 	5	FOTOCOPIAS	001-001-0013148	30000	27272	2727	309	12
4980	2022-12-14 14:53:56.288	5-Angel Rios 	6	RETIRO DE GUÍA 	001-001-0040944	330000	330000	0	309	28
4981	2022-12-14 14:53:56.288	5-Angel Rios 	7	CDAP PAGOS	001-021-0001576	21713	19739	1973	309	27
4982	2022-12-14 14:53:56.304	5-Angel Rios 	8	TASA PORTUARIA	001-034-0075400	159212	144738	14473	309	14
4983	2022-12-14 14:53:56.319	5-Angel Rios 	9	HON. DESP. S/ LEY 220/93	001-001-0002166	1076919	979017	97901	309	16
4984	2022-12-16 08:52:49.896	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04025979E	836444	836444	0	310	2
4985	2022-12-16 08:52:49.905	5-Angel Rios 	2	INDI	22005IC04025979E	27790	27790	0	310	3
4986	2022-12-16 08:52:49.905	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04025979E	1055818	1055818	0	310	5
4987	2022-12-16 08:52:49.92	5-Angel Rios 	4	IRE GENERAL 700	22005IC04025979E	853922	853922	0	310	38
4988	2022-12-16 08:52:49.92	5-Angel Rios 	5	I.V.A.	22005IC04025979E	21348060	0	21348060	310	8
4989	2022-12-16 08:52:49.936	5-Angel Rios 	6	CANON INFORMATICO	5780108	245222	245222	0	310	9
4990	2022-12-16 08:52:49.951	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0018829	175000	159090	15909	310	13
4991	2022-12-16 08:52:49.951	5-Angel Rios 	8	FOTOCOPIAS	001-003-0000321	140000	127272	12727	310	12
4992	2022-12-16 08:52:49.951	5-Angel Rios 	9	TASA PORTUARIA	002-001-0007156	1122910	1020827	102082	310	14
4993	2022-12-16 08:52:49.967	5-Angel Rios 	10	CDAP PAGOS	001-025-0005837	7219	6562	656	310	27
4994	2022-12-16 08:52:49.967	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0170637	28073	25520	2552	310	11
4995	2022-12-16 08:52:49.983	5-Angel Rios 	12	VISACION MRE	1168238	490445	490445	0	310	28
4996	2022-12-16 08:52:49.983	5-Angel Rios 	13	CDAP PAGOS	001-024-0006704	10000	9090	909	310	27
4997	2022-12-16 08:52:49.998	5-Angel Rios 	14	VISACION CONSUL	3104868/69	248000	248000	0	310	15
4998	2022-12-16 08:52:49.998	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002167	3140440	2854945	285494	310	16
4999	2022-12-16 08:52:49.998	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	310	17
5000	2022-12-20 09:31:05.366	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04026605Y	0	0	0	311	2
5001	2022-12-20 09:31:05.376	5-Angel Rios 	2	INDI	22005IC04026605Y	27789	27789	0	311	3
5002	2022-12-20 09:31:05.384	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04026605Y	388172	388172	0	311	5
5003	2022-12-20 09:31:05.393	5-Angel Rios 	4	IRE GENERAL 700	22005IC04026605Y	313787	313787	0	311	38
5004	2022-12-20 09:31:05.399	5-Angel Rios 	5	I.V.A.	22005IC04026605Y	7844729	0	7844729	311	8
5005	2022-12-20 09:31:05.406	5-Angel Rios 	6	CANON INFORMATICO	5866166	245222	245222	0	311	9
5006	2022-12-20 09:31:05.413	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0018936	175000	159090	15909	311	13
5007	2022-12-20 09:31:05.423	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018775	170000	154545	15454	311	12
5008	2022-12-20 09:31:05.429	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009285	430069	390971	39097	311	14
5009	2022-12-20 09:31:05.435	5-Angel Rios 	10	CDAP PAGOS	001-025-0009590	7207	6551	655	311	27
5010	2022-12-20 09:31:05.44	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174398	10752	9774	977	311	11
5011	2022-12-20 09:31:05.448	5-Angel Rios 	12	LICENCIA PREVIA IMP	1169197	686623	686623	0	311	28
5012	2022-12-20 09:31:05.454	5-Angel Rios 	13	CDAP PAGOS	001-024-0006704	10000	9090	909	311	27
5013	2022-12-20 09:31:05.459	5-Angel Rios 	14	VISACION CONSUL	3108018/19/20	453000	453000	0	311	15
5014	2022-12-20 09:31:05.465	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002169	685538	623216	62321	311	16
5015	2022-12-20 09:31:05.469	5-Angel Rios 	16	GASTO ADMIN.	920	920000	920000	0	311	17
5016	2022-12-20 09:32:30.395	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04026605Y	0	0	0	312	2
5017	2022-12-20 09:32:30.403	5-Angel Rios 	2	INDI	22005IC04026605Y	27789	27789	0	312	3
5018	2022-12-20 09:32:30.408	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04026605Y	388172	388172	0	312	5
5019	2022-12-20 09:32:30.416	5-Angel Rios 	4	IRE GENERAL 700	22005IC04026605Y	313787	313787	0	312	38
5020	2022-12-20 09:32:30.427	5-Angel Rios 	5	I.V.A.	22005IC04026605Y	7844729	0	7844729	312	8
5021	2022-12-20 09:32:30.431	5-Angel Rios 	6	CANON INFORMATICO	5866166	245222	245222	0	312	9
5022	2022-12-20 09:32:30.437	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0018936	175000	159090	15909	312	13
5023	2022-12-20 09:32:30.442	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018775	170000	154545	15454	312	12
5024	2022-12-20 09:32:30.448	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009285	430069	390971	39097	312	14
5025	2022-12-20 09:32:30.456	5-Angel Rios 	10	CDAP PAGOS	001-025-0009590	7207	6551	655	312	27
5026	2022-12-20 09:32:30.463	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174398	10752	9774	977	312	11
5027	2022-12-20 09:32:30.469	5-Angel Rios 	12	LICENCIA PREVIA IMP	1169197	686623	686623	0	312	28
5028	2022-12-20 09:32:30.474	5-Angel Rios 	13	CDAP PAGOS	001-024-0006704	10000	9090	909	312	27
5029	2022-12-20 09:32:30.481	5-Angel Rios 	14	VISACION CONSUL	3108018/19/20	453000	453000	0	312	15
5030	2022-12-20 09:32:30.489	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002169	754092	685538	68553	312	16
5031	2022-12-20 09:32:30.497	5-Angel Rios 	16	GASTO ADMIN.	920	920000	920000	0	312	17
5032	2022-12-20 09:51:50.945	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04026605Y	0	0	0	313	2
5033	2022-12-20 09:51:50.954	5-Angel Rios 	2	INDI	22005IC04026605Y	27789	27789	0	313	3
5034	2022-12-20 09:51:50.961	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IC04026605Y	388172	388172	0	313	5
5035	2022-12-20 09:51:50.965	5-Angel Rios 	4	IRE GENERAL 700	22005IC04026605Y	313787	313787	0	313	38
5036	2022-12-20 09:51:50.97	5-Angel Rios 	5	I.V.A.	22005IC04026605Y	7844729	0	7844729	313	8
5037	2022-12-20 09:51:50.978	5-Angel Rios 	6	CANON INFORMATICO	5866166	245222	245222	0	313	9
5038	2022-12-20 09:51:50.993	5-Angel Rios 	7	APERTURA AG. TRANSP.	001-001-0018936	175000	159090	15909	313	13
5039	2022-12-20 09:51:50.998	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018775	170000	154545	15454	313	12
5040	2022-12-20 09:51:50.999	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009285	430069	390971	39097	313	14
5041	2022-12-20 09:51:51.011	5-Angel Rios 	10	CDAP PAGOS	001-025-0009590	7207	6551	655	313	27
5042	2022-12-20 09:51:51.017	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174398	10752	9774	977	313	11
5043	2022-12-20 09:51:51.022	5-Angel Rios 	12	LICENCIA PREVIA IMP	1169197	686623	686623	0	313	28
5044	2022-12-20 09:51:51.032	5-Angel Rios 	13	CDAP PAGOS	001-024-0006704	10000	9090	909	313	27
5045	2022-12-20 09:51:51.039	5-Angel Rios 	14	VISACION CONSUL	3108018/19/20	453000	453000	0	313	15
5046	2022-12-20 09:51:51.048	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002169	754092	685538	68553	313	16
5047	2022-12-20 09:51:51.054	5-Angel Rios 	16	GASTO ADMIN.	920	720000	720000	0	313	17
5048	2022-12-20 10:52:28.469	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003938P	0	0	0	314	2
5049	2022-12-20 10:52:28.474	5-Angel Rios 	2	INDI	22018EC01003938P	0	0	0	314	3
5050	2022-12-20 10:52:28.478	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003938P	0	0	0	314	5
5051	2022-12-20 10:52:28.486	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003938P	0	0	0	314	38
5052	2022-12-20 10:52:28.491	5-Angel Rios 	5	I.V.A.	22018EC01003938P	0	0	0	314	8
5053	2022-12-20 10:52:28.496	5-Angel Rios 	6	CANON INFORMATICO	5791491	245222	245222	0	314	9
5054	2022-12-20 10:52:28.501	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	314	13
5055	2022-12-20 10:52:28.506	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009870	50000	45454	4545	314	12
5056	2022-12-20 10:52:28.511	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	314	14
5057	2022-12-20 10:52:28.518	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	314	27
5058	2022-12-20 10:52:28.523	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	314	11
5059	2022-12-20 10:52:28.533	5-Angel Rios 	12	TASA MIC CO	218758	54545	54545	0	314	28
5060	2022-12-20 10:52:28.539	5-Angel Rios 	13	TASA UIP CO 	002-002-0002816	49045	49045	0	314	27
5061	2022-12-20 10:52:28.545	5-Angel Rios 	14	VISACION CONSUL	x	0	0	0	314	15
5062	2022-12-20 10:52:28.549	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002170	2227642	2025129	202512	314	16
5063	2022-12-20 10:52:28.558	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	314	17
5064	2022-12-20 10:59:30.038	5-Angel Rios 	1	DERECHO ADUANERO	22018EM01001449U	0	0	0	315	2
5065	2022-12-20 10:59:30.046	5-Angel Rios 	2	INDI	22018EM01001449U	0	0	0	315	3
5066	2022-12-20 10:59:30.054	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EM01001449U	0	0	0	315	5
5067	2022-12-20 10:59:30.058	5-Angel Rios 	4	IRE GENERAL 700	22018EM01001449U	0	0	0	315	38
5068	2022-12-20 10:59:30.065	5-Angel Rios 	5	I.V.A.	22018EM01001449U	0	0	0	315	8
5069	2022-12-20 10:59:30.07	5-Angel Rios 	6	CANON INFORMATICO	x	0	0	0	315	9
5070	2022-12-20 10:59:30.071	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	315	13
5071	2022-12-20 10:59:30.08	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009869	50000	45454	4545	315	12
5072	2022-12-20 10:59:30.085	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	315	14
5073	2022-12-20 10:59:30.09	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	315	27
5074	2022-12-20 10:59:30.095	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	315	11
5075	2022-12-20 10:59:30.1	5-Angel Rios 	12	TASA MIC CO 	2014966	54545	54545	0	315	28
5076	2022-12-20 10:59:30.105	5-Angel Rios 	13	TASA UIP CO 	002-002-0002815	49045	49045	0	315	27
5077	2022-12-20 10:59:30.11	5-Angel Rios 	14	VISACION CONSUL	x	0	0	0	315	15
5078	2022-12-20 10:59:30.119	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002171	2574344	2340312	234031	315	16
5079	2022-12-20 10:59:30.125	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	315	17
5080	2022-12-20 16:22:27.167	5-Angel Rios 	1	DERECHO ADUANERO	22005IM04000690U	38538	38538	0	316	2
5081	2022-12-20 16:22:27.173	5-Angel Rios 	2	INDI	22005IM04000690U	15134	15134	0	316	3
5082	2022-12-20 16:22:27.177	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IM04000690U	62242	62242	0	316	5
5083	2022-12-20 16:22:27.185	5-Angel Rios 	4	IRE GENERAL 700	22005IM04000690U	51123	51123	0	316	38
5084	2022-12-20 16:22:27.19	5-Angel Rios 	5	I.V.A.	22005IM04000690U	1278050	0	1278050	316	8
5085	2022-12-20 16:22:27.196	5-Angel Rios 	6	CANON INFORMATICO	5866985	49044	49044	0	316	9
5086	2022-12-20 16:22:27.202	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	316	13
5087	2022-12-20 16:22:27.207	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018777	120000	109090	10909	316	12
5088	2022-12-20 16:22:27.215	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009494	71546	65041	6504	316	14
5089	2022-12-20 16:22:27.22	5-Angel Rios 	10	CDAP PAGOS	001-025-0010046	7205	6550	655	316	27
5090	2022-12-20 16:22:27.228	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174848	1789	1626	162	316	11
5091	2022-12-20 16:22:27.235	5-Angel Rios 	12	VISACION CONSUL	3113555/6	248000	248000	0	316	28
5092	2022-12-20 16:22:27.236	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	316	27
5093	2022-12-20 16:22:27.249	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002172	994932	904483	90448	316	16
5094	2022-12-20 16:22:27.25	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	316	17
5095	2022-12-22 08:56:48.187	5-Angel Rios 	1	DERECHO ADUANERO	22005IT04026628T	133881	133881	0	317	2
5096	2022-12-22 08:56:48.194	5-Angel Rios 	2	INDI	22005IT04026628T	27789	27789	0	317	3
5097	2022-12-22 08:56:48.194	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT04026628T	4368672	4368672	0	317	5
5098	2022-12-22 08:56:48.21	5-Angel Rios 	4	IRE GENERAL 700	22005IT04026628T	3514648	3514648	0	317	38
5099	2022-12-22 08:56:48.21	5-Angel Rios 	5	I.V.A.	22005IT04026628T	87866191	0	87866191	317	8
5100	2022-12-22 08:56:48.225	5-Angel Rios 	6	CANON INFORMATICO	5866806	245222	245222	0	317	9
5101	2022-12-22 08:56:48.225	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	317	13
5102	2022-12-22 08:56:48.225	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018778	120000	109090	10909	317	12
5103	2022-12-22 08:56:48.241	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009493	3967940	3607218	360721	317	14
5104	2022-12-22 08:56:48.241	5-Angel Rios 	10	CDAP PAGOS	001-025-0010044	7205	6550	655	317	27
5105	2022-12-22 08:56:48.257	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174847	99199	90180	9018	317	11
5106	2022-12-22 08:56:48.257	5-Angel Rios 	12	VISACION CONSUL	3113551/552/553/554	790000	790000	0	317	28
5107	2022-12-22 08:56:48.272	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002173	5772825	5248022	524802	317	16
5108	2022-12-22 08:56:48.272	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	317	17
5109	2022-12-27 07:48:46.751	5-Angel Rios 	1	DERECHO ADUANERO	22005IT04026628T	133881	133881	0	318	2
5110	2022-12-27 07:48:46.76	5-Angel Rios 	2	INDI	22005IT04026628T	27789	27789	0	318	3
5111	2022-12-27 07:48:46.77	5-Angel Rios 	3	SERVICIO DE VALORACION	22005IT04026628T	4368672	4368672	0	318	5
5112	2022-12-27 07:48:46.778	5-Angel Rios 	4	IRE GENERAL 700	22005IT04026628T	3514648	3514648	0	318	38
5113	2022-12-27 07:48:46.788	5-Angel Rios 	5	I.V.A.	22005IT04026628T	87866191	0	87866191	318	8
5114	2022-12-27 07:48:46.795	5-Angel Rios 	6	CANON INFORMATICO	5866806	245222	245222	0	318	9
5115	2022-12-27 07:48:46.801	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	318	13
5116	2022-12-27 07:48:46.808	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018778	120000	109090	10909	318	12
5117	2022-12-27 07:48:46.816	5-Angel Rios 	9	TASA PORTUARIA	002-001-0009493	3967940	3607218	360721	318	14
5118	2022-12-27 07:48:46.825	5-Angel Rios 	10	CDAP PAGOS	001-025-0010044	7205	6550	655	318	27
5119	2022-12-27 07:48:46.831	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0174847	99199	90180	9018	318	11
5120	2022-12-27 07:48:46.833	5-Angel Rios 	12	VISACION CONSUL	3113551/552/553/554	790000	790000	0	318	28
5121	2022-12-27 07:48:46.844	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	001-001-0002173	5772825	5248022	524802	318	16
5122	2022-12-27 07:48:46.845	5-Angel Rios 	14	GASTO ADMIN.	x	0	0	0	318	17
5123	2023-01-02 16:42:25.963	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04027254P	4230688	4230688	0	319	2
5124	2023-01-02 16:42:25.976	5-Angel Rios 	2	INDI	22005IC04027254P	47952	47952	0	319	3
5125	2023-01-02 16:42:25.983	5-Angel Rios 	3	I.S.C.	22005IC04027254P	385623	385623	0	319	4
5126	2023-01-02 16:42:25.989	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04027254P	461338	461338	0	319	5
5127	2023-01-02 16:42:25.996	5-Angel Rios 	5	IRE GENERAL 700	22005IC04027254P	392309	392309	0	319	38
5128	2023-01-02 16:42:26.009	5-Angel Rios 	6	I.V.A.	22005IC04027254P	9807733	0	9807733	319	8
5129	2023-01-02 16:42:26.015	5-Angel Rios 	7	CANON INFORMATICO	5911806	245222	245222	0	319	9
5130	2023-01-02 16:42:26.024	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018831	240000	218181	21818	319	12
5131	2023-01-02 16:42:26.028	5-Angel Rios 	9	TASA PORTUARIA	002-001-0011458	587546	534132	53413	319	14
5132	2023-01-02 16:42:26.032	5-Angel Rios 	10	CDAP PAGOS	001-025-0013641	7320	6654	665	319	27
5133	2023-01-02 16:42:26.04	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0178447	14689	13353	1335	319	11
5134	2023-01-02 16:42:26.044	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	319	15
5135	2023-01-02 16:42:26.057	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	319	27
5136	2023-01-02 16:42:26.059	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002174	3440947	3128133	312813	319	16
5137	2023-01-02 16:42:26.073	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	319	17
5138	2023-01-04 09:40:09.423	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04016462M	0	0	0	320	2
5139	2023-01-04 09:40:09.43	5-Angel Rios 	2	INDI	22030IC04016462M	27790	27790	0	320	3
5140	2023-01-04 09:40:09.442	5-Angel Rios 	3	I.S.C.	22030IC04016462M	0	0	0	320	4
5141	2023-01-04 09:40:09.448	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04016462M	614255	614255	0	320	5
5142	2023-01-04 09:40:09.473	5-Angel Rios 	5	IRE GENERAL 700	22030IC04016462M	495563	495563	0	320	38
5143	2023-01-04 09:40:09.484	5-Angel Rios 	6	I.V.A.	22030IC04016462M	12389058	0	12389058	320	8
5144	2023-01-04 09:40:09.491	5-Angel Rios 	7	CANON INFORMATICO	5920118	245222	245222	0	320	9
5145	2023-01-04 09:40:09.496	5-Angel Rios 	8	FOTOCOPIAS	020-002-0004774	70000	63636	6363	320	12
5146	2023-01-04 09:40:09.502	5-Angel Rios 	9	TASA PORTUARIA	001-004-0001638	1508146	1371041	137104	320	14
5147	2023-01-04 09:40:09.505	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	320	27
5148	2023-01-04 09:40:09.513	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	320	11
5149	2023-01-04 09:40:09.516	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	320	15
5150	2023-01-04 09:40:09.528	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	320	27
5151	2023-01-04 09:40:09.535	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002174	2380683	2164257	216425	320	16
5152	2023-01-04 09:40:09.544	5-Angel Rios 	15	GASTO ADMIN.	001-001-0000285	660000	660000	0	320	17
5153	2023-01-04 14:08:09.256	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000047H	0	0	0	321	2
5154	2023-01-04 14:08:09.262	5-Angel Rios 	2	INDI	23005IC04000047H	28282	28282	0	321	3
5155	2023-01-04 14:08:09.272	5-Angel Rios 	3	I.S.C.	23005IC04000047H	0	0	0	321	4
5156	2023-01-04 14:08:09.284	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000047H	277944	277944	0	321	5
5157	2023-01-04 14:08:09.292	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000047H	225196	225196	0	321	38
5158	2023-01-04 14:08:09.299	5-Angel Rios 	6	I.V.A.	23005IC04000047H	5629911	0	5629911	321	8
5159	2023-01-04 14:08:09.308	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000047H	245222	245222	0	321	9
5160	2023-01-04 14:08:09.312	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018861	80000	72727	7272	321	12
5161	2023-01-04 14:08:09.321	5-Angel Rios 	9	TASA PORTUARIA	002-001-0012415	385818	350743	35074	321	14
5162	2023-01-04 14:08:09.333	5-Angel Rios 	10	CDAP PAGOS	001-025-0015834	7345	6677	667	321	27
5163	2023-01-04 14:08:09.341	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0180640	9645	8768	876	321	11
5164	2023-01-04 14:08:09.35	5-Angel Rios 	12	VISACION MRE	1180341	490445	490445	0	321	28
5165	2023-01-04 14:08:09.355	5-Angel Rios 	13	CDAP PAGOS	001-024-0016445	10000	9090	909	321	27
5166	2023-01-04 14:08:09.361	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	x	1963182	1784710	178471	321	16
5167	2023-01-04 14:08:09.368	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	321	17
5168	2023-01-04 14:08:09.381	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	321	17
5169	2023-01-04 14:43:28.23	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000095K	0	0	0	322	2
5170	2023-01-04 14:43:28.234	5-Angel Rios 	2	INDI	23005IC04000095K	28120	28120	0	322	3
5171	2023-01-04 14:43:28.234	5-Angel Rios 	3	I.S.C.	23005IC04000095K	0	0	0	322	4
5172	2023-01-04 14:43:28.25	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000095K	308221	308221	0	322	5
5173	2023-01-04 14:43:28.25	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000095K	249529	249529	0	322	38
5174	2023-01-04 14:43:28.266	5-Angel Rios 	6	I.V.A.	23005IC04000095K	6238221	0	6238221	322	8
5175	2023-01-04 14:43:28.266	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000095K	245222	245222	0	322	9
5176	2023-01-04 14:43:28.266	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019188	180000	163636	16363	322	13
5177	2023-01-04 14:43:28.281	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018862	80000	72727	7272	322	12
5178	2023-01-04 14:43:28.281	5-Angel Rios 	10	TASA PORTUARIA	002-001-0012542	386123	351020	35102	322	14
5179	2023-01-04 14:43:28.297	5-Angel Rios 	11	CDAP PAGOS	001-025-0016059	7303	6639	663	322	27
5180	2023-01-04 14:43:28.321	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0180864	9653	8775	877	322	11
5181	2023-01-04 14:43:28.352	5-Angel Rios 	13	VISACION MRE	230000286	490445	490445	0	322	28
5182	2023-01-04 14:43:28.384	5-Angel Rios 	14	CDAP PAGOS	001-024-0016841	10000	9090	909	322	27
5183	2023-01-04 14:43:28.399	5-Angel Rios 	15	VISACION CONSUL	3119907/08	248000	248000	0	322	15
5184	2023-01-04 14:43:28.421	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	1927584	1752349	175234	322	16
5185	2023-01-04 14:43:28.437	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	322	17
5186	2023-01-04 15:54:27.803	5-Angel Rios 	1	DERECHO ADUANERO	23005IT02000003Y	0	0	0	323	2
5187	2023-01-04 15:54:27.81	5-Angel Rios 	2	INDI	23005IT02000003Y	0	0	0	323	3
5188	2023-01-04 15:54:27.814	5-Angel Rios 	3	I.S.C.	23005IT02000003Y	0	0	0	323	4
5189	2023-01-04 15:54:27.833	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IT02000003Y	0	0	0	323	5
5190	2023-01-04 15:54:27.838	5-Angel Rios 	5	IRE GENERAL 700	23005IT02000003Y	0	0	0	323	38
5191	2023-01-04 15:54:27.848	5-Angel Rios 	6	I.V.A.	23005IT02000003Y	0	0	0	323	8
5192	2023-01-04 15:54:27.856	5-Angel Rios 	7	CANON INFORMATICO	x	0	0	0	323	9
5193	2023-01-04 15:54:27.864	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019187	180000	163636	16363	323	13
5194	2023-01-04 15:54:27.87	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018860	80000	72727	7272	323	12
5195	2023-01-04 15:54:27.873	5-Angel Rios 	10	TASA PORTUARIA	002-001-0012352	333486	303169	30316	323	14
5196	2023-01-04 15:54:27.885	5-Angel Rios 	11	CDAP PAGOS	001-025-0015646	7345	6677	667	323	27
5197	2023-01-04 15:54:27.887	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0180451	8337	7579	757	323	11
5198	2023-01-04 15:54:27.895	5-Angel Rios 	13	VISACION MRE	x	0	0	0	323	28
5199	2023-01-04 15:54:27.904	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	323	27
5200	2023-01-04 15:54:27.912	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	323	15
5201	2023-01-04 15:54:27.917	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	0	0	0	323	16
5202	2023-01-04 15:54:27.927	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	323	17
5203	2023-01-04 15:56:07.516	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000047H	0	0	0	324	2
5204	2023-01-04 15:56:07.521	5-Angel Rios 	2	INDI	23005IC04000047H	28282	28282	0	324	3
5205	2023-01-04 15:56:07.527	5-Angel Rios 	3	I.S.C.	23005IC04000047H	0	0	0	324	4
5206	2023-01-04 15:56:07.534	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000047H	277944	277944	0	324	5
5207	2023-01-04 15:56:07.542	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000047H	225196	225196	0	324	38
5208	2023-01-04 15:56:07.549	5-Angel Rios 	6	I.V.A.	23005IC04000047H	5629911	0	5629911	324	8
5209	2023-01-04 15:56:07.557	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000047H	245222	245222	0	324	9
5210	2023-01-04 15:56:07.572	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018861	80000	72727	7272	324	12
5211	2023-01-04 15:56:07.578	5-Angel Rios 	9	TASA PORTUARIA	002-001-0012415	385818	350743	35074	324	14
5212	2023-01-04 15:56:07.588	5-Angel Rios 	10	CDAP PAGOS	001-025-0015834	7345	6677	667	324	27
5213	2023-01-04 15:56:07.593	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0180640	9645	8768	876	324	11
5214	2023-01-04 15:56:07.613	5-Angel Rios 	12	VISACION MRE	1180341	490445	490445	0	324	28
5215	2023-01-04 15:56:07.62	5-Angel Rios 	13	CDAP PAGOS	001-024-0016445	10000	9090	909	324	27
5216	2023-01-04 15:56:07.625	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002175	1963182	1784710	178471	324	16
5217	2023-01-04 15:56:07.628	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	324	17
5218	2023-01-04 15:56:07.637	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	324	17
5219	2023-01-05 14:19:22.279	5-Angel Rios 	1	DERECHO ADUANERO	22030IC04016462M	0	0	0	325	2
5220	2023-01-05 14:19:22.287	5-Angel Rios 	2	INDI	22030IC04016462M	27790	27790	0	325	3
5221	2023-01-05 14:19:22.302	5-Angel Rios 	3	I.S.C.	22030IC04016462M	0	0	0	325	4
5222	2023-01-05 14:19:22.318	5-Angel Rios 	4	SERVICIO DE VALORACION	22030IC04016462M	614255	614255	0	325	5
5223	2023-01-05 14:19:22.349	5-Angel Rios 	5	IRE GENERAL 700	22030IC04016462M	495563	495563	0	325	38
5224	2023-01-05 14:19:22.371	5-Angel Rios 	6	I.V.A.	22030IC04016462M	12389058	0	12389058	325	8
5225	2023-01-05 14:19:22.386	5-Angel Rios 	7	CANON INFORMATICO	5920118	245222	245222	0	325	9
5226	2023-01-05 14:19:22.402	5-Angel Rios 	8	FOTOCOPIAS	020-002-0004774	70000	63636	6363	325	12
5227	2023-01-05 14:19:22.433	5-Angel Rios 	9	TASA PORTUARIA	001-004-0001638	1508146	1371041	137104	325	14
5228	2023-01-05 14:19:22.455	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	325	27
5229	2023-01-05 14:19:22.471	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	325	11
5230	2023-01-05 14:19:22.471	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	325	15
5231	2023-01-05 14:19:22.487	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	325	27
5232	2023-01-05 14:19:22.502	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002174	2380683	2164257	216425	325	16
5233	2023-01-05 14:19:22.518	5-Angel Rios 	15	GASTO ADMIN.	001-001-0000285	660000	600000	60000	325	17
5234	2023-01-09 08:53:02.019	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04027254P	4230688	4230688	0	326	2
5235	2023-01-09 08:53:02.035	5-Angel Rios 	2	INDI	22005IC04027254P	47952	47952	0	326	3
5236	2023-01-09 08:53:02.047	5-Angel Rios 	3	I.S.C.	22005IC04027254P	385623	385623	0	326	4
5237	2023-01-09 08:53:02.058	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04027254P	461338	461338	0	326	5
5238	2023-01-09 08:53:02.081	5-Angel Rios 	5	IRE GENERAL 700	22005IC04027254P	392309	392309	0	326	38
5239	2023-01-09 08:53:02.112	5-Angel Rios 	6	I.V.A.	22005IC04027254P	9807733	0	9807733	326	8
5240	2023-01-09 08:53:02.122	5-Angel Rios 	7	CANON INFORMATICO	5911806	245222	245222	0	326	9
5241	2023-01-09 08:53:02.133	5-Angel Rios 	8	FOTOCOPIAS	001-002-0018831	240000	218181	21818	326	12
5242	2023-01-09 08:53:02.144	5-Angel Rios 	9	TASA PORTUARIA	002-001-0011458	587546	534132	53413	326	14
5243	2023-01-09 08:53:02.153	5-Angel Rios 	10	CDAP PAGOS	001-025-0013641	7320	6654	665	326	27
5244	2023-01-09 08:53:02.166	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	001-004-0178447	14689	13353	1335	326	11
5245	2023-01-09 08:53:02.175	5-Angel Rios 	12	VISACION CONSUL	x	0	0	0	326	15
5246	2023-01-09 08:53:02.187	5-Angel Rios 	13	CDAP PAGOS	x	0	0	0	326	27
5247	2023-01-09 08:53:02.202	5-Angel Rios 	14	HON. DESP. S/ LEY 220/93	001-001-0002174	3440947	3128133	312813	326	16
5248	2023-01-09 08:53:02.212	5-Angel Rios 	15	GASTO ADMIN.	x	0	0	0	326	17
5249	2023-01-09 09:25:27.552	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04026356Y	0	0	0	327	2
5250	2023-01-09 09:25:27.563	5-Angel Rios 	2	INDI	22002IC04026356Y	0	0	0	327	3
5251	2023-01-09 09:25:27.573	5-Angel Rios 	3	I.S.C.	22002IC04026356Y	0	0	0	327	4
5252	2023-01-09 09:25:27.579	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04026356Y	472775	472775	0	327	5
5253	2023-01-09 09:25:27.591	5-Angel Rios 	5	IRE GENERAL 700	22002IC04026356Y	381416	381416	0	327	38
5254	2023-01-09 09:25:27.601	5-Angel Rios 	6	I.V.A.	22002IC04026356Y	9535410	0	9535410	327	8
5255	2023-01-09 09:25:27.621	5-Angel Rios 	7	CANON INFORMATICO	5852352	250222	250222	0	327	9
5256	2023-01-09 09:25:27.632	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	327	13
5257	2023-01-09 09:25:27.646	5-Angel Rios 	9	FOTOCOPIAS	004-003-0011962	48000	43636	4363	327	12
5258	2023-01-09 09:25:27.656	5-Angel Rios 	10	TASA PORTUARIA	001-034-0077016	1360135	1236486	123648	327	14
5259	2023-01-09 09:25:27.666	5-Angel Rios 	11	CDAP PAGOS	001-021-0003092	21664	19694	1969	327	27
5260	2023-01-09 09:25:27.676	5-Angel Rios 	12	RETIRO DE GUÍA DHL	017-010-0030977	450000	409090	40909	327	11
5261	2023-01-09 09:25:27.686	5-Angel Rios 	13	VISACION MRE	x	0	0	0	327	28
5262	2023-01-09 09:25:27.693	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	327	27
5263	2023-01-09 09:25:27.704	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	327	15
5264	2023-01-09 09:25:27.721	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002178	2354523	2140475	214047	327	16
5265	2023-01-09 09:25:27.73	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	327	17
5266	2023-01-09 10:01:16.031	5-Angel Rios 	1	DERECHO ADUANERO	23005IT0402000003Y	0	0	0	328	2
5267	2023-01-09 10:01:16.041	5-Angel Rios 	2	INDI	23005IT0402000003Y	0	0	0	328	3
5268	2023-01-09 10:01:16.051	5-Angel Rios 	3	I.S.C.	23005IT0402000003Y	0	0	0	328	4
5269	2023-01-09 10:01:16.062	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IT0402000003Y	0	0	0	328	5
5270	2023-01-09 10:01:16.071	5-Angel Rios 	5	IRE GENERAL 700	23005IT0402000003Y	0	0	0	328	38
5271	2023-01-09 10:01:16.081	5-Angel Rios 	6	I.V.A.	23005IT0402000003Y	0	0	0	328	8
5272	2023-01-09 10:01:16.093	5-Angel Rios 	7	CANON INFORMATICO	x	0	0	0	328	9
5273	2023-01-09 10:01:16.099	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019187	180000	163636	16363	328	13
5274	2023-01-09 10:01:16.105	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018860	80000	72727	7272	328	12
5275	2023-01-09 10:01:16.125	5-Angel Rios 	10	TASA PORTUARIA	002-001-0012352	333486	303169	30316	328	14
5276	2023-01-09 10:01:16.133	5-Angel Rios 	11	CDAP PAGOS	001-025-0015646	7345	6677	667	328	27
5277	2023-01-09 10:01:16.14	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0180451	8337	7579	757	328	11
5278	2023-01-09 10:01:16.145	5-Angel Rios 	13	VISACION MRE	x	0	0	0	328	28
5279	2023-01-09 10:01:16.152	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	328	27
5280	2023-01-09 10:01:16.16	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	328	15
5281	2023-01-09 10:01:16.175	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	2968722	2698838	269883	328	16
5282	2023-01-09 10:01:16.182	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	328	17
5283	2023-01-09 11:23:26.421	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04027294T	0	0	0	329	2
5284	2023-01-09 11:23:26.428	5-Angel Rios 	2	INDI	22005IC04027294T	27790	27790	0	329	3
5285	2023-01-09 11:23:26.433	5-Angel Rios 	3	I.S.C.	22005IC04027294T	0	0	0	329	4
5286	2023-01-09 11:23:26.441	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04027294T	1925005	1925005	0	329	5
5287	2023-01-09 11:23:26.451	5-Angel Rios 	5	IRE GENERAL 700	22005IC04027294T	1549403	1549403	0	329	38
5288	2023-01-09 11:23:26.463	5-Angel Rios 	6	I.V.A.	22005IC04027294T	38735074	0	38735074	329	8
5289	2023-01-09 11:23:26.468	5-Angel Rios 	7	CANON INFORMATICO	22005IC04027294T	245222	245222	0	329	9
5290	2023-01-09 11:23:26.476	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	329	13
5291	2023-01-09 11:23:26.481	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018832	70000	63636	6363	329	12
5292	2023-01-09 11:23:26.488	5-Angel Rios 	10	TASA PORTUARIA	002-001-0011516	1911947	1738133	173813	329	14
5293	2023-01-09 11:23:26.493	5-Angel Rios 	11	CDAP PAGOS	001-025-0013784	7320	6654	665	329	27
5294	2023-01-09 11:23:26.5	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0178593	47799	43453	4345	329	11
5295	2023-01-09 11:23:26.509	5-Angel Rios 	13	VISACION MRE	2200009532	490445	490445	0	329	28
5296	2023-01-09 11:23:26.525	5-Angel Rios 	14	CDAP PAGOS	001-024-0013913	10000	9090	909	329	27
5297	2023-01-09 11:23:26.531	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	329	15
5298	2023-01-09 11:23:26.536	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002179	5054503	4595002	459500	329	16
5299	2023-01-09 11:23:26.543	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	329	17
5300	2023-01-10 10:13:21.97	5-Angel Rios 	1	DERECHO ADUANERO	22018EM01001449U	0	0	0	330	2
5301	2023-01-10 10:13:21.985	5-Angel Rios 	2	INDI	22018EM01001449U	0	0	0	330	3
5302	2023-01-10 10:13:21.994	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EM01001449U	0	0	0	330	5
5303	2023-01-10 10:13:22.003	5-Angel Rios 	4	IRE GENERAL 700	22018EM01001449U	0	0	0	330	38
5304	2023-01-10 10:13:22.014	5-Angel Rios 	5	I.V.A.	22018EM01001449U	0	0	0	330	8
5305	2023-01-10 10:13:22.024	5-Angel Rios 	6	CANON INFORMATICO	x	0	0	0	330	9
5306	2023-01-10 10:13:22.031	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	330	13
5307	2023-01-10 10:13:22.039	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009869	50000	45454	4545	330	12
5308	2023-01-10 10:13:22.047	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	330	14
5309	2023-01-10 10:13:22.054	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	330	27
5310	2023-01-10 10:13:22.067	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	330	11
5311	2023-01-10 10:13:22.078	5-Angel Rios 	12	TASA MIC CO 	2014966	54545	54545	0	330	28
5312	2023-01-10 10:13:22.092	5-Angel Rios 	13	TASA UIP CO 	002-002-0002815	49045	49045	0	330	27
5313	2023-01-10 10:13:22.099	5-Angel Rios 	14	VISACION CONSUL	x	0	0	0	330	15
5314	2023-01-10 10:13:22.107	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002171	2574344	2340312	234031	330	16
5315	2023-01-10 10:13:22.118	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	330	17
5316	2023-01-10 10:14:10.77	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01003938P	0	0	0	331	2
5317	2023-01-10 10:14:10.778	5-Angel Rios 	2	INDI	22018EC01003938P	0	0	0	331	3
5318	2023-01-10 10:14:10.785	5-Angel Rios 	3	SERVICIO DE VALORACION	22018EC01003938P	0	0	0	331	5
5319	2023-01-10 10:14:10.793	5-Angel Rios 	4	IRE GENERAL 700	22018EC01003938P	0	0	0	331	38
5320	2023-01-10 10:14:10.803	5-Angel Rios 	5	I.V.A.	22018EC01003938P	0	0	0	331	8
5321	2023-01-10 10:14:10.814	5-Angel Rios 	6	CANON INFORMATICO	5791491	245222	245222	0	331	9
5322	2023-01-10 10:14:10.824	5-Angel Rios 	7	APERTURA AG. TRANSP.	x	0	0	0	331	13
5323	2023-01-10 10:14:10.835	5-Angel Rios 	8	FOTOCOPIAS	001-001-0009870	50000	45454	4545	331	12
5324	2023-01-10 10:14:10.842	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	331	14
5325	2023-01-10 10:14:10.849	5-Angel Rios 	10	CDAP PAGOS	x	0	0	0	331	27
5326	2023-01-10 10:14:10.859	5-Angel Rios 	11	SERVICIOS SOFIA / ANNP	x	0	0	0	331	11
5327	2023-01-10 10:14:10.867	5-Angel Rios 	12	TASA MIC CO	218758	54545	54545	0	331	28
5328	2023-01-10 10:14:10.877	5-Angel Rios 	13	TASA UIP CO 	002-002-0002816	49045	49045	0	331	27
5329	2023-01-10 10:14:10.886	5-Angel Rios 	14	VISACION CONSUL	x	0	0	0	331	15
5330	2023-01-10 10:14:10.894	5-Angel Rios 	15	HON. DESP. S/ LEY 220/93	001-001-0002170	2227642	2025129	202512	331	16
5331	2023-01-10 10:14:10.902	5-Angel Rios 	16	GASTO ADMIN.	x	0	0	0	331	17
5332	2023-01-10 10:27:03.945	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04027188V	35657414	35657414	0	332	2
5333	2023-01-10 10:27:03.967	5-Angel Rios 	2	INDI	22005IC04027188V	27790	27790	0	332	3
5334	2023-01-10 10:27:03.996	5-Angel Rios 	3	I.S.C.	22005IC04027188V	0	0	0	332	4
5335	2023-01-10 10:27:04.015	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04027188V	2882797	2882797	0	332	5
5336	2023-01-10 10:27:04.048	5-Angel Rios 	5	IRE GENERAL 700	22005IC04027188V	2462099	2462099	0	332	38
5337	2023-01-10 10:27:04.074	5-Angel Rios 	6	I.V.A.	22005IC04027188V	61552427	0	61552427	332	8
5338	2023-01-10 10:27:04.123	5-Angel Rios 	7	CANON INFORMATICO	5909683	245222	245222	0	332	9
5339	2023-01-10 10:27:04.147	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019095	175000	159090	15909	332	13
5340	2023-01-10 10:27:04.179	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018833	120000	109090	10909	332	12
5341	2023-01-10 10:27:04.186	5-Angel Rios 	10	TASA PORTUARIA	002-001-0011265	2723135	2475577	247557	332	14
5342	2023-01-10 10:27:04.199	5-Angel Rios 	11	CDAP PAGOS	001-025-0013202	7317	6651	665	332	27
5343	2023-01-10 10:27:04.207	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0178006	6189	5626	562	332	11
5344	2023-01-10 10:27:04.214	5-Angel Rios 	13	VISACION MRE	x	0	0	0	332	28
5345	2023-01-10 10:27:04.225	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	332	27
5346	2023-01-10 10:27:04.232	5-Angel Rios 	15	VISACION CONSUL	03114354/55/56/80	710000	710000	0	332	15
5417	2023-01-18 09:24:44.638	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000095K	0	0	0	337	2
5347	2023-01-10 10:27:04.237	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002181	5686646	5169678	516967	332	16
5348	2023-01-10 10:27:04.249	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	332	17
5349	2023-01-10 10:27:43.284	5-Angel Rios 	1	DERECHO ADUANERO	22005IC04027188V	35657414	35657414	0	333	2
5350	2023-01-10 10:27:43.292	5-Angel Rios 	2	INDI	22005IC04027188V	27790	27790	0	333	3
5351	2023-01-10 10:27:43.297	5-Angel Rios 	3	I.S.C.	22005IC04027188V	0	0	0	333	4
5352	2023-01-10 10:27:43.303	5-Angel Rios 	4	SERVICIO DE VALORACION	22005IC04027188V	2882797	2882797	0	333	5
5353	2023-01-10 10:27:43.311	5-Angel Rios 	5	IRE GENERAL 700	22005IC04027188V	2462099	2462099	0	333	38
5354	2023-01-10 10:27:43.317	5-Angel Rios 	6	I.V.A.	22005IC04027188V	61552427	0	61552427	333	8
5355	2023-01-10 10:27:43.323	5-Angel Rios 	7	CANON INFORMATICO	5909683	245222	245222	0	333	9
5356	2023-01-10 10:27:43.329	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019095	175000	159090	15909	333	13
5357	2023-01-10 10:27:43.334	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018833	120000	109090	10909	333	12
5358	2023-01-10 10:27:43.341	5-Angel Rios 	10	TASA PORTUARIA	002-001-0011265	2723135	2475577	247557	333	14
5359	2023-01-10 10:27:43.346	5-Angel Rios 	11	CDAP PAGOS	001-025-0013202	7317	6651	665	333	27
5360	2023-01-10 10:27:43.352	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0178006	6189	5626	562	333	11
5361	2023-01-10 10:27:43.359	5-Angel Rios 	13	VISACION MRE	x	0	0	0	333	28
5362	2023-01-10 10:27:43.366	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	333	27
5363	2023-01-10 10:27:43.374	5-Angel Rios 	15	VISACION CONSUL	03114354/55/56/80	710000	710000	0	333	15
5364	2023-01-10 10:27:43.379	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002181	5686646	5169678	516967	333	16
5365	2023-01-10 10:27:43.39	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	333	17
5366	2023-01-10 10:53:36.083	5-Angel Rios 	1	DERECHO ADUANERO	22018EC01004012W	0	0	0	334	2
5367	2023-01-10 10:53:36.09	5-Angel Rios 	2	INDI	22018EC01004012W	0	0	0	334	3
5368	2023-01-10 10:53:36.101	5-Angel Rios 	3	I.S.C.	22018EC01004012W	0	0	0	334	4
5369	2023-01-10 10:53:36.107	5-Angel Rios 	4	SERVICIO DE VALORACION	22018EC01004012W	0	0	0	334	5
5370	2023-01-10 10:53:36.112	5-Angel Rios 	5	IRE GENERAL 700	22018EC01004012W	0	0	0	334	38
5371	2023-01-10 10:53:36.12	5-Angel Rios 	6	I.V.A.	22018EC01004012W	0	0	0	334	8
5372	2023-01-10 10:53:36.128	5-Angel Rios 	7	CANON INFORMATICO	22018EC01004012W	245222	245222	0	334	9
5373	2023-01-10 10:53:36.135	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	334	13
5374	2023-01-10 10:53:36.142	5-Angel Rios 	9	FOTOCOPIAS	001-001-0009890	50000	45454	4545	334	12
5375	2023-01-10 10:53:36.15	5-Angel Rios 	10	TASA UIP CO	002-002-0002907	98089	89171	8917	334	14
5376	2023-01-10 10:53:36.156	5-Angel Rios 	11	TASA MIC CO	2021391	103589	94171	9417	334	27
5377	2023-01-10 10:53:36.162	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	334	11
5378	2023-01-10 10:53:36.171	5-Angel Rios 	13	VISACION MRE	x	0	0	0	334	28
5379	2023-01-10 10:53:36.177	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	334	27
5380	2023-01-10 10:53:36.185	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	334	15
5381	2023-01-10 10:53:36.192	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002183	2989771	2717973	271797	334	16
5382	2023-01-10 10:53:36.201	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	334	17
5383	2023-01-10 11:04:14.532	5-Angel Rios 	1	DERECHO ADUANERO	23005IT0402000003Y	0	0	0	335	2
5384	2023-01-10 11:04:14.539	5-Angel Rios 	2	INDI	23005IT0402000003Y	0	0	0	335	3
5385	2023-01-10 11:04:14.546	5-Angel Rios 	3	I.S.C.	23005IT0402000003Y	0	0	0	335	4
5386	2023-01-10 11:04:14.551	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IT0402000003Y	0	0	0	335	5
5387	2023-01-10 11:04:14.558	5-Angel Rios 	5	IRE GENERAL 700	23005IT0402000003Y	0	0	0	335	38
5388	2023-01-10 11:04:14.566	5-Angel Rios 	6	I.V.A.	23005IT0402000003Y	0	0	0	335	8
5389	2023-01-10 11:04:14.572	5-Angel Rios 	7	CANON INFORMATICO 	x	0	0	0	335	9
5390	2023-01-10 11:04:14.577	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019187	180000	163636	16363	335	13
5391	2023-01-10 11:04:14.582	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018860	80000	72727	7272	335	12
5392	2023-01-10 11:04:14.587	5-Angel Rios 	10	TASA PORTUARIA	002-001-0012352	333486	303169	30316	335	14
5393	2023-01-10 11:04:14.595	5-Angel Rios 	11	CDAP PAGOS	001-025-0015646	7345	6677	667	335	27
5394	2023-01-10 11:04:14.6	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0180451	8337	7579	757	335	11
5395	2023-01-10 11:04:14.606	5-Angel Rios 	13	VISACION MRE	x	0	0	0	335	28
5396	2023-01-10 11:04:14.615	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	335	27
5397	2023-01-10 11:04:14.621	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	335	15
5398	2023-01-10 11:04:14.629	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002184	2968722	2698838	269883	335	16
5399	2023-01-10 11:04:14.636	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	335	17
5400	2023-01-18 09:09:08.174	5-Angel Rios 	1	DERECHO ADUANERO	22002IC04026356Y	0	0	0	336	2
5401	2023-01-18 09:09:08.183	5-Angel Rios 	2	INDI	22002IC04026356Y	0	0	0	336	3
5402	2023-01-18 09:09:08.196	5-Angel Rios 	3	I.S.C.	22002IC04026356Y	0	0	0	336	4
5403	2023-01-18 09:09:08.203	5-Angel Rios 	4	SERVICIO DE VALORACION	22002IC04026356Y	472775	472775	0	336	5
5404	2023-01-18 09:09:08.218	5-Angel Rios 	5	IRE GENERAL 700	22002IC04026356Y	381416	381416	0	336	38
5405	2023-01-18 09:09:08.23	5-Angel Rios 	6	I.V.A.	22002IC04026356Y	9535410	0	9535410	336	8
5406	2023-01-18 09:09:08.251	5-Angel Rios 	7	CANON INFORMATICO	5852352	250222	250222	0	336	9
5407	2023-01-18 09:09:08.257	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	336	13
5408	2023-01-18 09:09:08.268	5-Angel Rios 	9	FOTOCOPIAS	004-003-0011882	48000	43636	4363	336	12
5409	2023-01-18 09:09:08.273	5-Angel Rios 	10	TASA PORTUARIA	001-034-0077016	1360135	1236486	123648	336	14
5410	2023-01-18 09:09:08.279	5-Angel Rios 	11	CDAP PAGOS	001-021-0003092	21644	19676	1967	336	27
5411	2023-01-18 09:09:08.286	5-Angel Rios 	12	GUÍA AEREA	017-010-0030977	450000	409090	40909	336	11
5412	2023-01-18 09:09:08.291	5-Angel Rios 	13	VISACION MRE	x	0	0	0	336	28
5413	2023-01-18 09:09:08.302	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	336	27
5414	2023-01-18 09:09:08.304	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	336	15
5415	2023-01-18 09:09:08.313	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002178	2354523	2140475	214047	336	16
5416	2023-01-18 09:09:08.319	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	336	17
5418	2023-01-18 09:24:44.643	5-Angel Rios 	2	INDI	23005IC04000095K	28120	28120	0	337	3
5419	2023-01-18 09:24:44.65	5-Angel Rios 	3	I.S.C.	23005IC04000095K	0	0	0	337	4
5420	2023-01-18 09:24:44.662	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000095K	308221	308221	0	337	5
5421	2023-01-18 09:24:44.669	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000095K	249529	249529	0	337	38
5422	2023-01-18 09:24:44.682	5-Angel Rios 	6	I.V.A.	23005IC04000095K	6238221	0	6238221	337	8
5423	2023-01-18 09:24:44.691	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000095K	245222	245222	0	337	9
5424	2023-01-18 09:24:44.709	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019188	180000	163636	16363	337	13
5425	2023-01-18 09:24:44.712	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018862	80000	72727	7272	337	12
5426	2023-01-18 09:24:44.721	5-Angel Rios 	10	TASA PORTUARIA	002-001-0012542	386123	351020	35102	337	14
5427	2023-01-18 09:24:44.726	5-Angel Rios 	11	CDAP PAGOS	001-025-0016059	7303	6639	663	337	27
5428	2023-01-18 09:24:44.73	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0180864	9653	8775	877	337	11
5429	2023-01-18 09:24:44.74	5-Angel Rios 	13	VISACION MRE	230000286	490445	490445	0	337	28
5430	2023-01-18 09:24:44.741	5-Angel Rios 	14	CDAP PAGOS	001-024-0016841	10000	9090	909	337	27
5431	2023-01-18 09:24:44.759	5-Angel Rios 	15	VISACION CONSUL	3119907/08	248000	248000	0	337	15
5432	2023-01-18 09:24:44.768	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002185	1927584	1752349	175234	337	16
5433	2023-01-18 09:24:44.774	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	337	17
5434	2023-01-18 10:59:35.71	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04001386@	0	0	0	338	2
5435	2023-01-18 10:59:35.719	5-Angel Rios 	2	INDI	23005IC04001386@	28490	28490	0	338	3
5436	2023-01-18 10:59:35.74	5-Angel Rios 	3	I.S.C.	23005IC04001386@	0	0	0	338	4
5437	2023-01-18 10:59:35.751	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04001386@	202151	202151	0	338	5
5438	2023-01-18 10:59:35.783	5-Angel Rios 	5	IRE GENERAL 700	23005IC04001386@	164271	164271	0	338	38
5439	2023-01-18 10:59:35.823	5-Angel Rios 	6	I.V.A.	23005IC04001386@	4106776	0	4106776	338	8
5440	2023-01-18 10:59:35.831	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	338	9
5441	2023-01-18 10:59:35.84	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	180000	163636	16363	338	13
5442	2023-01-18 10:59:35.852	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	338	12
5443	2023-01-18 10:59:35.899	5-Angel Rios 	10	TASA PORTUARIA	x	210000	190909	19090	338	14
5444	2023-01-18 10:59:35.914	5-Angel Rios 	11	CDAP PAGOS	x	10000	9090	909	338	27
5445	2023-01-18 10:59:35.926	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	7000	6363	636	338	11
5446	2023-01-18 10:59:35.938	5-Angel Rios 	13	VISACION CONSUL	x	700000	700000	0	338	28
5447	2023-01-18 10:59:35.951	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	338	27
5448	2023-01-18 10:59:35.967	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	1313731	1194300	119430	338	16
5449	2023-01-18 10:59:35.987	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	338	17
5450	2023-01-18 11:12:55.087	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04001386@	0	0	0	339	2
5451	2023-01-18 11:12:55.096	5-Angel Rios 	2	INDI	23005IC04001386@	28490	28490	0	339	3
5452	2023-01-18 11:12:55.106	5-Angel Rios 	3	I.S.C.	23005IC04001386@	0	0	0	339	4
5453	2023-01-18 11:12:55.112	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04001386@	202151	202151	0	339	5
5454	2023-01-18 11:12:55.12	5-Angel Rios 	5	IRE GENERAL 700	23005IC04001386@	164271	164271	0	339	38
5455	2023-01-18 11:12:55.128	5-Angel Rios 	6	I.V.A.	23005IC04001386@	4106776	0	4106776	339	8
5456	2023-01-18 11:12:55.149	5-Angel Rios 	7	CANON INFORMATICO	x	245222	245222	0	339	9
5457	2023-01-18 11:12:55.161	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	180000	163636	16363	339	13
5458	2023-01-18 11:12:55.17	5-Angel Rios 	9	FOTOCOPIAS	x	70000	63636	6363	339	12
5459	2023-01-18 11:12:55.177	5-Angel Rios 	10	TASA PORTUARIA	x	210000	190909	19090	339	14
5460	2023-01-18 11:12:55.182	5-Angel Rios 	11	CDAP PAGOS	x	10000	9090	909	339	27
5461	2023-01-18 11:12:55.19	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	7000	6363	636	339	11
5462	2023-01-18 11:12:55.196	5-Angel Rios 	13	VISACION CONSUL	x	700000	700000	0	339	28
5463	2023-01-18 11:12:55.201	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	339	27
5464	2023-01-18 11:12:55.209	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	1313731	1194300	119430	339	16
5465	2023-01-18 11:12:55.216	5-Angel Rios 	17	GASTO ADMIN. / CONTABLE	x	1200000	1200000	0	339	17
5466	2023-01-18 11:26:55.702	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000726L	0	0	0	340	2
5467	2023-01-18 11:26:55.709	5-Angel Rios 	2	INDI	23005IC04000726L	27790	27790	0	340	3
5468	2023-01-18 11:26:55.719	5-Angel Rios 	3	I.S.C.	23005IC04000726L	0	0	0	340	4
5469	2023-01-18 11:26:55.731	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000726L	1770089	1770089	0	340	5
5470	2023-01-18 11:26:55.742	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000726L	1424851	1424851	0	340	38
5471	2023-01-18 11:26:55.751	5-Angel Rios 	6	I.V.A.	23005IC04000726L	35621277	0	35621277	340	8
5472	2023-01-18 11:26:55.755	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000726L	245222	245222	0	340	9
5473	2023-01-18 11:26:55.777	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019391	180000	163636	16363	340	13
5474	2023-01-18 11:26:55.787	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018917	80000	72727	7272	340	12
5475	2023-01-18 11:26:55.795	5-Angel Rios 	10	TASA PORTUARIA	002-001-0014676	1880324	1709385	170938	340	14
5476	2023-01-18 11:26:55.801	5-Angel Rios 	11	CDAP PAGOS	001-025-0020480	7412	6738	673	340	27
5477	2023-01-18 11:26:55.809	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0185298	47008	42734	4273	340	11
5478	2023-01-18 11:26:55.82	5-Angel Rios 	13	VISACION MRE	2300001591	980890	980890	0	340	28
5479	2023-01-18 11:26:55.849	5-Angel Rios 	14	CDAP PAGOS	001-002-0021006	10000	9090	909	340	27
5480	2023-01-18 11:26:55.864	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	340	15
5481	2023-01-18 11:26:55.876	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002186	4810021	4372746	437274	340	16
5482	2023-01-18 11:26:55.901	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	340	17
5483	2023-01-18 11:42:15.539	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000679S	0	0	0	341	2
5484	2023-01-18 11:42:15.549	5-Angel Rios 	2	INDI	23005IC04000679S	27790	27790	0	341	3
5485	2023-01-18 11:42:15.576	5-Angel Rios 	3	I.S.C.	23005IC04000679S	0	0	0	341	4
5486	2023-01-18 11:42:15.6	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000679S	4043283	4043283	0	341	5
5763	2023-02-08 11:40:30.031	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	355	15
5487	2023-01-18 11:42:15.688	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000679S	3252498	3252498	0	341	38
5488	2023-01-18 11:42:15.707	5-Angel Rios 	6	I.V.A.	23005IC04000679S	81312465	0	81312465	341	8
5489	2023-01-18 11:42:15.772	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000679S	245222	245222	0	341	9
5490	2023-01-18 11:42:15.798	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	341	13
5491	2023-01-18 11:42:15.824	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018912	70000	63636	6363	341	12
5492	2023-01-18 11:42:15.857	5-Angel Rios 	10	TASA PORTUARIA	002-001-0014485	3630865	3300786	330078	341	14
5493	2023-01-18 11:42:15.868	5-Angel Rios 	11	CDAP PAGOS	001-025-0020078	7395	6722	672	341	27
5494	2023-01-18 11:42:15.875	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0184889	90772	82520	8252	341	11
5495	2023-01-18 11:42:15.882	5-Angel Rios 	13	VISACION MRE	x	0	0	0	341	28
5496	2023-01-18 11:42:15.9	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	341	27
5497	2023-01-18 11:42:15.906	5-Angel Rios 	15	VISACION CONSUL	03134958/59/60/61	902000	902000	0	341	15
5498	2023-01-18 11:42:15.912	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002187	5590283	5082075	508207	341	16
5499	2023-01-18 11:42:15.92	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	341	17
5500	2023-01-18 11:43:43.711	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04000726L	0	0	0	342	2
5501	2023-01-18 11:43:43.717	5-Angel Rios 	2	INDI	23005IC04000726L	27790	27790	0	342	3
5502	2023-01-18 11:43:43.723	5-Angel Rios 	3	I.S.C.	23005IC04000726L	0	0	0	342	4
5503	2023-01-18 11:43:43.731	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04000726L	1770089	1770089	0	342	5
5504	2023-01-18 11:43:43.736	5-Angel Rios 	5	IRE GENERAL 700	23005IC04000726L	1424851	1424851	0	342	38
5505	2023-01-18 11:43:43.742	5-Angel Rios 	6	I.V.A.	23005IC04000726L	35621277	0	35621277	342	8
5506	2023-01-18 11:43:43.748	5-Angel Rios 	7	CANON INFORMATICO	23005IC04000726L	245222	245222	0	342	9
5507	2023-01-18 11:43:43.754	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019391	180000	163636	16363	342	13
5508	2023-01-18 11:43:43.763	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018917	80000	72727	7272	342	12
5509	2023-01-18 11:43:43.769	5-Angel Rios 	10	TASA PORTUARIA	002-001-0014676	1880324	1709385	170938	342	14
5510	2023-01-18 11:43:43.773	5-Angel Rios 	11	CDAP PAGOS	001-025-0020480	7412	6738	673	342	27
5511	2023-01-18 11:43:43.781	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0185298	47008	42734	4273	342	11
5512	2023-01-18 11:43:43.791	5-Angel Rios 	13	VISACION MRE	2300001591	980890	980890	0	342	28
5513	2023-01-18 11:43:43.801	5-Angel Rios 	14	CDAP PAGOS	001-002-0021006	10000	9090	909	342	27
5514	2023-01-18 11:43:43.806	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	342	15
5515	2023-01-18 11:43:43.812	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002186	4810021	4372746	437274	342	16
5516	2023-01-18 11:43:43.817	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	342	17
5517	2023-01-18 15:20:39.543	5-Angel Rios 	1	DERECHO ADUANERO	23002IC04000811D	0	0	0	343	2
5518	2023-01-18 15:20:39.553	5-Angel Rios 	2	INDI	23002IC04000811D	0	0	0	343	3
5519	2023-01-18 15:20:39.563	5-Angel Rios 	3	I.S.C.	23002IC04000811D	0	0	0	343	4
5520	2023-01-18 15:20:39.574	5-Angel Rios 	4	SERVICIO DE VALORACION	23002IC04000811D	200940	200940	0	343	5
5521	2023-01-18 15:20:39.584	5-Angel Rios 	5	IRE GENERAL 700	23002IC04000811D	162444	162444	0	343	38
5522	2023-01-18 15:20:39.597	5-Angel Rios 	6	I.V.A.	23002IC04000811D	4061089	0	4061089	343	8
5523	2023-01-18 15:20:39.607	5-Angel Rios 	7	CANON INFORMATICO	6105902	250222	250222	0	343	9
5524	2023-01-18 15:20:39.614	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	343	13
5525	2023-01-18 15:20:39.622	5-Angel Rios 	9	FOTOCOPIAS	004-003-0013676	42000	38181	3818	343	12
5526	2023-01-18 15:20:39.627	5-Angel Rios 	10	TASA PORTUARIA	020-001-0780863	582852	529865	52986	343	14
5527	2023-01-18 15:20:39.634	5-Angel Rios 	11	CDAP PAGOS	001-021-0007359	22260	20236	2023	343	27
5528	2023-01-18 15:20:39.639	5-Angel Rios 	12	GUÍA AEREA	001-001-0041448	440000	400000	40000	343	11
5529	2023-01-18 15:20:39.645	5-Angel Rios 	13	VISACION MRE	x	0	0	0	343	28
5530	2023-01-18 15:20:39.651	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	343	27
5531	2023-01-18 15:20:39.659	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	343	15
5532	2023-01-18 15:20:39.669	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002188	2126044	1932767	193276	343	16
5533	2023-01-18 15:20:39.692	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	343	17
5534	2023-01-18 15:54:57.96	5-Angel Rios 	1	DERECHO ADUANERO	23030IM04000026M	2892489	2892489	0	344	2
5535	2023-01-18 15:54:57.974	5-Angel Rios 	2	INDI	23030IM04000026M	15576	15576	0	344	3
5536	2023-01-18 15:54:57.98	5-Angel Rios 	3	ARANC CONS FACTURA 	23030IM04000026M	111255	111255	0	344	33
5537	2023-01-18 15:54:57.986	5-Angel Rios 	3	I.S.C.	23030IM04000026M	0	0	0	344	4
5538	2023-01-18 15:54:57.993	5-Angel Rios 	4	SERVICIO DE VALORACION	23030IM04000026M	80347	80347	0	344	5
5539	2023-01-18 15:54:58.001	5-Angel Rios 	5	IRE GENERAL 700	23030IM04000026M	77121	77121	0	344	38
5540	2023-01-18 15:54:58.013	5-Angel Rios 	6	I.V.A.	23030IM04000026M	1928031	0	1928031	344	8
5541	2023-01-18 15:54:58.02	5-Angel Rios 	7	TASA INT. ADUANERA 	23030IM04000026M	370849	370849	0	344	35
5542	2023-01-18 15:54:58.029	5-Angel Rios 	7	CANON INFORMATICO	6171092	54044	54044	0	344	9
5543	2023-01-18 15:54:58.034	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	344	13
5544	2023-01-18 15:54:58.042	5-Angel Rios 	9	FOTOCOPIAS	020-002-0005302	30000	27272	2727	344	12
5545	2023-01-18 15:54:58.047	5-Angel Rios 	10	TASA PORTUARIA	001-004-0002532	1705904	1550821	155082	344	14
5546	2023-01-18 15:54:58.054	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	344	27
5547	2023-01-18 15:54:58.059	5-Angel Rios 	12	FLETE INTERNO 	001-001-0000910	330000	300000	30000	344	11
5548	2023-01-18 15:54:58.073	5-Angel Rios 	13	VISACION MRE	1186975	490445	490445	0	344	28
5549	2023-01-18 15:54:58.083	5-Angel Rios 	14	CDAP PAGOS	001-024-0022182	10000	9090	909	344	27
5550	2023-01-18 15:54:58.09	5-Angel Rios 	15	FLETE INTERNO 	001-002-0026709	750000	750000	0	344	15
5551	2023-01-18 15:54:58.095	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002189	440000	400000	40000	344	16
5552	2023-01-18 15:54:58.102	5-Angel Rios 	17	GASTO ADMIN.	001-001-0000312	691859	628963	62896	344	17
5553	2023-01-19 10:19:51.984	5-Angel Rios 	1	DERECHO ADUANERO	23015IC04000328K	0	0	0	345	2
5554	2023-01-19 10:19:51.993	5-Angel Rios 	2	INDI	23015IC04000328K	68661	68661	0	345	3
5555	2023-01-19 10:19:52.007	5-Angel Rios 	3	I.S.C.	23015IC04000328K	3147171	3147171	0	345	4
5556	2023-01-19 10:19:52.017	5-Angel Rios 	3	ARANC CONS FACTURA 	23015IC04000328K	0	0	0	345	33
5557	2023-01-19 10:19:52.026	5-Angel Rios 	4	SERVICIO DE VALORACION	23015IC04000328K	1560537	1560537	0	345	5
5558	2023-01-19 10:19:52.035	5-Angel Rios 	5	IRE GENERAL 700	23015IC04000328K	1271457	1271457	0	345	38
5559	2023-01-19 10:19:52.041	5-Angel Rios 	6	I.V.A.	23015IC04000328K	31786437	0	31786437	345	8
5560	2023-01-19 10:19:52.049	5-Angel Rios 	7	TASA INT. ADUANERA 	23015IC04000328K	370630	370630	0	345	35
5561	2023-01-19 10:19:52.055	5-Angel Rios 	7	CANON INFORMATICO	0006109724	250222	250222	0	345	9
5562	2023-01-19 10:19:52.061	5-Angel Rios 	8	FLETE INTERNO	001-002-0026662	750000	681818	68181	345	13
5563	2023-01-19 10:19:52.069	5-Angel Rios 	9	FOTOCOPIAS	006-002-0003210	30000	27272	2727	345	12
5564	2023-01-19 10:19:52.08	5-Angel Rios 	10	TASA PORTUARIA	001-002-0158113	1665755	1514322	151432	345	14
5565	2023-01-19 10:19:52.087	5-Angel Rios 	11	FLETE SANTIAGO - ASUNCION	001-003-0000368	12288150	11171045	1117104	345	27
5566	2023-01-19 10:19:52.093	5-Angel Rios 	12	FLETE PAKSA 	001-001-0000908	275000	250000	25000	345	11
5567	2023-01-19 10:19:52.109	5-Angel Rios 	13	VISACION MRE	1185801	980890	980890	0	345	28
5568	2023-01-19 10:19:52.119	5-Angel Rios 	14	CDAP PAGOS	001-024-0021103	10000	9090	909	345	27
5569	2023-01-19 10:19:52.128	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	345	15
5570	2023-01-19 10:19:52.133	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0000313	4857953	4416320	441632	345	16
5571	2023-01-19 10:19:52.139	5-Angel Rios 	17	GASTO ADMIN.	001-001-0002190	1000000	909091	90909	345	17
5572	2023-01-19 10:19:52.15	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	345	17
5573	2023-01-26 11:08:46.277	5-Angel Rios 	1	DERECHO ADUANERO	23030IC04000797R	8613866	8613866	0	346	2
5574	2023-01-26 11:08:46.284	5-Angel Rios 	2	INDI	23030IC04000797R	68663	68663	0	346	3
5575	2023-01-26 11:08:46.284	5-Angel Rios 	3	I.S.C.	23030IC04000797R	0	0	0	346	4
5576	2023-01-26 11:08:46.294	5-Angel Rios 	3	ARANC CONS FACTURA 	23030IC04000797R	0	0	0	346	33
5577	2023-01-26 11:08:46.305	5-Angel Rios 	4	SERVICIO DE VALORACION	23030IC04000797R	506433	506433	0	346	5
5578	2023-01-26 11:08:46.315	5-Angel Rios 	5	IRE GENERAL 700	23030IC04000797R	445826	445826	0	346	38
5579	2023-01-26 11:08:46.325	5-Angel Rios 	6	I.V.A.	23030IC04000797R	11145649	0	11145649	346	8
5580	2023-01-26 11:08:46.334	5-Angel Rios 	7	TASA INT. ADUANERA 	23030IC04000797R	371640	371640	0	346	35
5581	2023-01-26 11:08:46.334	5-Angel Rios 	7	CANON INFORMATICO	x	250000	250000	0	346	9
5582	2023-01-26 11:08:46.345	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	346	13
5583	2023-01-26 11:08:46.354	5-Angel Rios 	9	FOTOCOPIAS	x	0	0	0	346	12
5584	2023-01-26 11:08:46.37	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	346	14
5585	2023-01-26 11:08:46.375	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	346	27
5586	2023-01-26 11:08:46.385	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	346	11
5587	2023-01-26 11:08:46.385	5-Angel Rios 	13	VISACION MRE	x	0	0	0	346	28
5588	2023-01-26 11:08:46.395	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	346	27
5589	2023-01-26 11:08:46.404	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	346	15
5590	2023-01-26 11:08:46.404	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	0	0	0	346	16
5591	2023-01-26 11:08:46.415	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	346	17
5592	2023-01-26 11:08:46.425	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	346	17
5593	2023-01-26 11:08:46.435	5-Angel Rios 	19	GASTO ADMIN.	x	0	0	0	346	17
5594	2023-01-27 08:41:25.423	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04001270G	0	0	0	347	2
5595	2023-01-27 08:41:25.439	5-Angel Rios 	2	INDI	23005IC04001270G	28630	28630	0	347	3
5596	2023-01-27 08:41:25.448	5-Angel Rios 	3	I.S.C.	23005IC04001270G	0	0	0	347	4
5597	2023-01-27 08:41:25.479	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04001270G	0	0	0	347	33
5598	2023-01-27 08:41:25.495	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04001270G	3909009	3909009	0	347	5
5599	2023-01-27 08:41:25.495	5-Angel Rios 	5	IRE GENERAL 700	23005IC04001270G	3144593	3144593	0	347	38
5600	2023-01-27 08:41:25.526	5-Angel Rios 	6	I.V.A.	23005IC04001270G	78614832	0	78614832	347	8
5601	2023-01-27 08:41:25.526	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04001270G	0	0	0	347	35
5602	2023-01-27 08:41:25.548	5-Angel Rios 	7	CANON INFORMATICO	6228744	245222	245222	0	347	9
5603	2023-01-27 08:41:25.564	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	347	13
5604	2023-01-27 08:41:25.564	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018992	70000	63636	6363	347	12
5605	2023-01-27 08:41:25.58	5-Angel Rios 	10	TASA PORTUARIA	002-001-0016661	3566352	3242138	324213	347	14
5606	2023-01-27 08:41:25.58	5-Angel Rios 	11	CDAP PAGOS	001-025-0024480	7438	6761	676	347	27
5607	2023-01-27 08:41:25.595	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0189292	88909	80826	8082	347	11
5608	2023-01-27 08:41:25.595	5-Angel Rios 	13	VISACION MRE	x	0	0	0	347	28
5609	2023-01-27 08:41:25.611	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	347	27
5610	2023-01-27 08:41:25.611	5-Angel Rios 	15	VISACION CONSUL	03149344/45/46/47	840000	840000	0	347	15
5611	2023-01-27 08:41:25.626	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002191	5514954	5013594	501359	347	16
5612	2023-01-27 08:41:25.626	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	347	17
5613	2023-01-27 08:41:25.642	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	347	17
5614	2023-01-27 08:41:25.649	5-Angel Rios 	19	GASTO ADMIN.	x	0	0	0	347	17
5615	2023-01-27 08:59:02.509	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04001533X	291396	291396	0	348	2
5616	2023-01-27 08:59:02.515	5-Angel Rios 	2	INDI	23005IC04001533X	28489	28489	0	348	3
5617	2023-01-27 08:59:02.518	5-Angel Rios 	3	I.S.C.	23005IC04001533X	10921	10921	0	348	4
5618	2023-01-27 08:59:02.518	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04001533X	0	0	0	348	33
5619	2023-01-27 08:59:02.534	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04001533X	283904	283904	0	348	5
5620	2023-01-27 08:59:02.534	5-Angel Rios 	5	IRE GENERAL 700	23005IC04001533X	231210	231210	0	348	38
5621	2023-01-27 08:59:02.55	5-Angel Rios 	6	I.V.A.	23005IC04001533X	5780252	0	5780252	348	8
5622	2023-01-27 08:59:02.55	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04001533X	0	0	0	348	35
5623	2023-01-27 08:59:02.55	5-Angel Rios 	7	CANON INFORMATICO	6243253	245222	245222	0	348	9
5624	2023-01-27 08:59:02.565	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	348	13
5625	2023-01-27 08:59:02.575	5-Angel Rios 	9	FOTOCOPIAS	001-002-0018993	100000	90909	9090	348	12
5626	2023-01-27 08:59:02.575	5-Angel Rios 	10	TASA PORTUARIA	002-001-0017669	359374	326703	32670	348	14
5627	2023-01-27 08:59:02.575	5-Angel Rios 	11	CDAP PAGOS	001-025-0026473	7379	6708	670	348	27
5628	2023-01-27 08:59:02.59	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0191283	8984	8167	816	348	11
5629	2023-01-27 08:59:02.59	5-Angel Rios 	13	VISACION MRE	2300003432	490445	490445	0	348	28
5630	2023-01-27 08:59:02.606	5-Angel Rios 	14	CDAP PAGOS	001-024-0027168	10000	9090	909	348	27
5631	2023-01-27 08:59:02.606	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	348	15
5632	2023-01-27 08:59:02.621	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002192	2028671	1844246	184424	348	16
5633	2023-01-30 11:38:27.874	5-Angel Rios 	1	DERECHO ADUANERO	23030IC04000797R	8613866	8613866	0	349	2
5634	2023-01-30 11:38:27.887	5-Angel Rios 	2	INDI	23030IC04000797R	68663	68663	0	349	3
5635	2023-01-30 11:38:27.892	5-Angel Rios 	3	I.S.C.	23030IC04000797R	0	0	0	349	4
5636	2023-01-30 11:38:27.902	5-Angel Rios 	3	ARANC CONS FACTURA 	23030IC04000797R	0	0	0	349	33
5637	2023-01-30 11:38:27.91	5-Angel Rios 	4	SERVICIO DE VALORACION	23030IC04000797R	506433	506433	0	349	5
5638	2023-01-30 11:38:27.919	5-Angel Rios 	5	IRE GENERAL 700	23030IC04000797R	445826	445826	0	349	38
5639	2023-01-30 11:38:27.922	5-Angel Rios 	6	I.V.A.	23030IC04000797R	11145649	0	11145649	349	8
5640	2023-01-30 11:38:27.93	5-Angel Rios 	7	TASA INT. ADUANERA 	23030IC04000797R	371640	371640	0	349	35
5641	2023-01-30 11:38:27.941	5-Angel Rios 	7	CANON INFORMATICO	23030IC04000797R	250000	250000	0	349	9
5642	2023-01-30 11:38:27.951	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	349	13
5643	2023-01-30 11:38:27.959	5-Angel Rios 	9	FOTOCOPIAS	001-001-0057126	48000	43636	4363	349	12
5644	2023-01-30 11:38:27.963	5-Angel Rios 	10	TASA PORTUARIA	001-004-0002684	3344756	3040687	304068	349	14
5645	2023-01-30 11:38:27.972	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	349	27
5646	2023-01-30 11:38:27.98	5-Angel Rios 	12	FLETE INTERNO 	001-001-0000913	330000	300000	30000	349	11
5647	2023-01-30 11:38:27.986	5-Angel Rios 	13	VISACION MRE	1188319	980890	980890	0	349	28
5648	2023-01-30 11:38:27.992	5-Angel Rios 	14	CDAP PAGOS	001-024-0023350	10000	9090	909	349	27
5649	2023-01-30 11:38:28.002	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	349	15
5650	2023-01-30 11:38:28.01	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002193	2945764	2677967	267796	349	16
5651	2023-01-30 11:38:28.017	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	349	17
5652	2023-01-30 14:11:02.04	5-Angel Rios 	1	DERECHO ADUANERO	23038IC04000560N	84340101	84340101	0	350	2
5653	2023-01-30 14:11:02.045	5-Angel Rios 	2	INDI	23038IC04000560N	68662	68662	0	350	3
5654	2023-01-30 14:11:02.053	5-Angel Rios 	3	I.S.C.	23038IC04000560N	4804323	4804323	0	350	4
5655	2023-01-30 14:11:02.059	5-Angel Rios 	3	ARANC CONS FACTURA 	23038IC04000560N	0	0	0	350	33
5656	2023-01-30 14:11:02.064	5-Angel Rios 	4	SERVICIO DE VALORACION	23038IC04000560N	4962951	4962951	0	350	5
5657	2023-01-30 14:11:02.068	5-Angel Rios 	5	IRE GENERAL 700	23038IC04000560N	4350988	4350988	0	350	38
5658	2023-01-30 14:11:02.077	5-Angel Rios 	6	I.V.A.	23038IC04000560N	108774703	0	108774703	350	8
5659	2023-01-30 14:11:02.084	5-Angel Rios 	7	TASA INT. ADUANERA 	23038IC04000560N	369629	369629	0	350	35
5660	2023-01-30 14:11:02.089	5-Angel Rios 	7	CANON INFORMATICO	6250512	245222	245222	0	350	9
5661	2023-01-30 14:11:02.097	5-Angel Rios 	8	TASA INT. ADUANERA	6250512	369629	369629	0	350	13
5662	2023-01-30 14:11:02.107	5-Angel Rios 	9	FOTOCOPIAS	002-002-0003806	54800	49818	4981	350	12
5663	2023-01-30 14:11:02.115	5-Angel Rios 	10	TASA PORTUARIA	002-008-0000173	1477080	1342800	134280	350	14
5664	2023-01-30 14:11:02.139	5-Angel Rios 	11	MOPC	1193200	49045	44586	4458	350	27
5665	2023-01-30 14:11:02.143	5-Angel Rios 	12	CDAP PAGOS 	001-024-0027915	10000	9090	909	350	11
5666	2023-01-30 14:11:02.153	5-Angel Rios 	13	VISACION MRE	1193039	980890	980890	0	350	28
5667	2023-01-30 14:11:02.16	5-Angel Rios 	14	CDAP PAGOS	001-024-0027913	10000	9090	909	350	27
5668	2023-01-30 14:11:02.164	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	350	15
5669	2023-01-30 14:11:02.177	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002194	6355547	5777770	577777	350	16
5670	2023-01-30 14:11:02.181	5-Angel Rios 	17	GASTO ADMIN.	001-001-0000317	660000	600000	60000	350	17
5671	2023-01-30 14:37:18.09	5-Angel Rios 	1	DERECHO ADUANERO	23038IC04000560N	84340101	84340101	0	351	2
5672	2023-01-30 14:37:18.096	5-Angel Rios 	2	INDI	23038IC04000560N	68662	68662	0	351	3
5673	2023-01-30 14:37:18.101	5-Angel Rios 	3	I.S.C.	23038IC04000560N	4804323	4804323	0	351	4
5674	2023-01-30 14:37:18.108	5-Angel Rios 	3	ARANC CONS FACTURA 	23038IC04000560N	0	0	0	351	33
5675	2023-01-30 14:37:18.114	5-Angel Rios 	4	SERVICIO DE VALORACION	23038IC04000560N	4962951	4962951	0	351	5
5676	2023-01-30 14:37:18.122	5-Angel Rios 	5	IRE GENERAL 700	23038IC04000560N	4350988	4350988	0	351	38
5677	2023-01-30 14:37:18.129	5-Angel Rios 	6	I.V.A.	23038IC04000560N	108774703	0	108774703	351	8
5678	2023-01-30 14:37:18.133	5-Angel Rios 	7	TASA INT. ADUANERA 	23038IC04000560N	369629	369629	0	351	35
5679	2023-01-30 14:37:18.142	5-Angel Rios 	7	CANON INFORMATICO	6250512	245222	245222	0	351	9
5680	2023-01-30 14:37:18.165	5-Angel Rios 	8	TASA INT. ADUANERA	6250512	369629	369629	0	351	13
5681	2023-01-30 14:37:18.187	5-Angel Rios 	9	FOTOCOPIAS	002-002-0003806	54800	49818	4981	351	12
5682	2023-01-30 14:37:18.193	5-Angel Rios 	10	TASA PORTUARIA	002-008-0000173	1477080	1342800	134280	351	14
5683	2023-01-30 14:37:18.201	5-Angel Rios 	11	MOPC	1193200	49045	44586	4458	351	27
5684	2023-01-30 14:37:18.211	5-Angel Rios 	12	CDAP PAGOS 	001-024-0027915	10000	9090	909	351	11
5685	2023-01-30 14:37:18.215	5-Angel Rios 	13	VISACION MRE	1193039	980890	980890	0	351	28
5686	2023-01-30 14:37:18.224	5-Angel Rios 	14	CDAP PAGOS	001-024-0027913	10000	9090	909	351	27
5687	2023-01-30 14:37:18.23	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	351	15
5688	2023-01-30 14:37:18.235	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002194	6355547	5777770	577777	351	16
5689	2023-01-30 14:37:18.241	5-Angel Rios 	17	GASTO ADMIN.	001-001-0000317	660000	600000	60000	351	17
5690	2023-02-08 10:28:37.931	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002151F	39293	39293	0	352	2
5691	2023-02-08 10:28:37.94	5-Angel Rios 	2	INDI	23005IC04002151F	28491	28491	0	352	3
5692	2023-02-08 10:28:37.953	5-Angel Rios 	3	I.S.C.	23005IC04002151F	0	0	0	352	4
5693	2023-02-08 10:28:37.967	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002151F	0	0	0	352	33
5694	2023-02-08 10:28:37.979	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002151F	265612	265612	0	352	5
5695	2023-02-08 10:28:37.997	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002151F	215451	215451	0	352	38
5696	2023-02-08 10:28:38.025	5-Angel Rios 	6	I.V.A.	23005IC04002151F	5386264	0	5386264	352	8
5697	2023-02-08 10:28:38.04	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002151F	0	0	0	352	35
5698	2023-02-08 10:28:38.064	5-Angel Rios 	7	CANON INFORMATICO	6375963	245222	245222	0	352	9
5699	2023-02-08 10:28:38.08	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-002-0003395	200000	181818	18181	352	13
5700	2023-02-08 10:28:38.095	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094439	110000	100000	10000	352	12
5701	2023-02-08 10:28:38.106	5-Angel Rios 	10	TASA PORTUARIA	002-001-0019807	339252	308410	30841	352	14
5702	2023-02-08 10:28:38.12	5-Angel Rios 	11	CDAP PAGOS	001-025-0030547	7319	6653	665	352	27
5703	2023-02-08 10:28:38.141	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0195365	8481	7710	771	352	11
5704	2023-02-08 10:28:38.149	5-Angel Rios 	13	VISACION MRE	2300004653	490445	490445	0	352	28
5705	2023-02-08 10:28:38.17	5-Angel Rios 	14	CDAP PAGOS	001-024-0031022	10000	9090	909	352	27
5706	2023-02-08 10:28:38.178	5-Angel Rios 	15	VISACION CONSUL	3154296/97	260000	260000	0	352	15
5707	2023-02-08 10:28:38.186	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002901	2187900	1989000	198900	352	16
5708	2023-02-08 10:28:38.197	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	352	17
5709	2023-02-08 10:48:56.103	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002211C	0	0	0	353	2
5710	2023-02-08 10:48:56.111	5-Angel Rios 	2	INDI	23005IC04002211C	28490	28490	0	353	3
5711	2023-02-08 10:48:56.116	5-Angel Rios 	3	I.S.C.	23005IC04002211C	0	0	0	353	4
5712	2023-02-08 10:48:56.123	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002211C	0	0	0	353	33
5713	2023-02-08 10:48:56.128	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002211C	386530	386530	0	353	5
5714	2023-02-08 10:48:56.133	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002211C	312512	312512	0	353	38
5715	2023-02-08 10:48:56.14	5-Angel Rios 	6	I.V.A.	23005IC04002211C	7812798	0	7812798	353	8
5716	2023-02-08 10:48:56.148	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002211C	0	0	0	353	35
5717	2023-02-08 10:48:56.155	5-Angel Rios 	7	CANON INFORMATICO	6382015	245222	245222	0	353	9
5718	2023-02-08 10:48:56.161	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019762	180000	163636	16363	353	13
5719	2023-02-08 10:48:56.167	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094440	70000	63636	6363	353	12
5720	2023-02-08 10:48:56.174	5-Angel Rios 	10	TASA PORTUARIA	002-001-0019996	428263	389330	38933	353	14
5721	2023-02-08 10:48:56.181	5-Angel Rios 	11	CDAP PAGOS	001-025-0030885	7319	6653	665	353	27
5722	2023-02-08 10:48:56.189	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0195705	10707	9733	973	353	11
5723	2023-02-08 10:48:56.199	5-Angel Rios 	13	VISACION MRE	2300004927	490445	490445	0	353	28
5724	2023-02-08 10:48:56.207	5-Angel Rios 	14	CDAP PAGOS	001-024-0031494	10000	9090	909	353	27
5725	2023-02-08 10:48:56.212	5-Angel Rios 	15	VISACION CONSUL	3154436/37	260000	260000	0	353	15
5726	2023-02-08 10:48:56.219	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002902	2478498	2253180	225318	353	16
5727	2023-02-08 10:48:56.226	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	353	17
5728	2023-02-08 11:16:50.349	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002142F	0	0	0	354	2
5729	2023-02-08 11:16:50.36	5-Angel Rios 	2	INDI	23005IC04002142F	28490	28490	0	354	3
5730	2023-02-08 11:16:50.368	5-Angel Rios 	3	I.S.C.	23005IC04002142F	0	0	0	354	4
5731	2023-02-08 11:16:50.376	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002142F	0	0	0	354	33
5732	2023-02-08 11:16:50.385	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002142F	2471928	2471928	0	354	5
5733	2023-02-08 11:16:50.395	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002142F	1989172	1989172	0	354	38
5734	2023-02-08 11:16:50.403	5-Angel Rios 	6	I.V.A.	23005IC04002142F	49729305	0	49729305	354	8
5735	2023-02-08 11:16:50.412	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002142F	0	0	0	354	35
5736	2023-02-08 11:16:50.429	5-Angel Rios 	7	CANON INFORMATICO	6379067	245222	245222	0	354	9
5737	2023-02-08 11:16:50.441	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	354	13
5738	2023-02-08 11:16:50.454	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094437	70000	63636	6363	354	12
5739	2023-02-08 11:16:50.462	5-Angel Rios 	10	TASA PORTUARIA	002-001-0019731	2409901	2190819	219081	354	14
5740	2023-02-08 11:16:50.471	5-Angel Rios 	11	CDAP PAGOS	001-025-0030440	7311	6646	664	354	27
5741	2023-02-08 11:16:50.479	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0195257	60248	54770	5477	354	11
5742	2023-02-08 11:16:50.488	5-Angel Rios 	13	VISACION MRE	1196554	490445	490445	0	354	28
5743	2023-02-08 11:16:50.497	5-Angel Rios 	14	CDAP PAGOS	001-024-0030963	10000	9090	909	354	27
5744	2023-02-08 11:16:50.507	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	354	15
5745	2023-02-08 11:16:50.515	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002903	5745473	5223157	522315	354	16
5746	2023-02-08 11:16:50.526	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	354	17
5747	2023-02-08 11:40:29.927	5-Angel Rios 	1	DERECHO ADUANERO	23005IM04000055Z	0	0	0	355	2
5748	2023-02-08 11:40:29.935	5-Angel Rios 	2	INDI	23005IM04000055Z	0	0	0	355	3
5749	2023-02-08 11:40:29.941	5-Angel Rios 	3	I.S.C.	23005IM04000055Z	0	0	0	355	4
5750	2023-02-08 11:40:29.947	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IM04000055Z	0	0	0	355	33
5751	2023-02-08 11:40:29.955	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IM04000055Z	0	0	0	355	5
5752	2023-02-08 11:40:29.96	5-Angel Rios 	5	IRE GENERAL 700	23005IM04000055Z	1	1	0	355	38
5753	2023-02-08 11:40:29.965	5-Angel Rios 	6	I.V.A.	23005IM04000055Z	0	0	0	355	8
5754	2023-02-08 11:40:29.972	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IM04000055Z	0	0	0	355	35
5755	2023-02-08 11:40:29.978	5-Angel Rios 	7	CANON INFORMATICO	x	0	0	0	355	9
5756	2023-02-08 11:40:29.986	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	355	13
5757	2023-02-08 11:40:29.992	5-Angel Rios 	9	FOTOCOPIAS	x	0	0	0	355	12
5758	2023-02-08 11:40:29.997	5-Angel Rios 	10	TASA PORTUARIA	x	0	0	0	355	14
5759	2023-02-08 11:40:30.002	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	355	27
5760	2023-02-08 11:40:30.008	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	355	11
5761	2023-02-08 11:40:30.013	5-Angel Rios 	13	VISACION MRE	x	0	0	0	355	28
5762	2023-02-08 11:40:30.025	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	355	27
5764	2023-02-08 11:40:30.039	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	0	0	0	355	16
5765	2023-02-08 11:40:30.044	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	355	17
5766	2023-02-08 11:40:30.05	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	355	17
5767	2023-02-08 11:40:30.059	5-Angel Rios 	19	GASTO ADMIN.	x	0	0	0	355	17
5768	2023-02-08 11:41:35.971	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002142F	0	0	0	356	2
5769	2023-02-08 11:41:35.985	5-Angel Rios 	2	INDI	23005IC04002142F	28490	28490	0	356	3
5770	2023-02-08 11:41:36.003	5-Angel Rios 	3	I.S.C.	23005IC04002142F	0	0	0	356	4
5771	2023-02-08 11:41:36.01	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002142F	0	0	0	356	33
5772	2023-02-08 11:41:36.036	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002142F	2471928	2471928	0	356	5
5773	2023-02-08 11:41:36.046	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002142F	1989172	1989172	0	356	38
5774	2023-02-08 11:41:36.052	5-Angel Rios 	6	I.V.A.	23005IC04002142F	49729305	0	49729305	356	8
5775	2023-02-08 11:41:36.058	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002142F	0	0	0	356	35
5776	2023-02-08 11:41:36.072	5-Angel Rios 	7	CANON INFORMATICO	6379067	245222	245222	0	356	9
5777	2023-02-08 11:41:36.078	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	356	13
5778	2023-02-08 11:41:36.09	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094437	70000	63636	6363	356	12
5779	2023-02-08 11:41:36.096	5-Angel Rios 	10	TASA PORTUARIA	002-001-0019731	2409901	2190819	219081	356	14
5780	2023-02-08 11:41:36.104	5-Angel Rios 	11	CDAP PAGOS	001-025-0030440	7311	6646	664	356	27
5781	2023-02-08 11:41:36.111	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0195257	60248	54770	5477	356	11
5782	2023-02-08 11:41:36.118	5-Angel Rios 	13	VISACION MRE	1196554	490445	490445	0	356	28
5783	2023-02-08 11:41:36.127	5-Angel Rios 	14	CDAP PAGOS	001-024-0030963	10000	9090	909	356	27
5784	2023-02-08 11:41:36.137	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	356	15
5785	2023-02-08 11:41:36.146	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002903	5745473	5223157	522315	356	16
5786	2023-02-08 11:41:36.154	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	356	17
5787	2023-02-08 15:05:31.04	5-Angel Rios 	1	DERECHO ADUANERO	23005IM04000055Z	0	0	0	357	2
5788	2023-02-08 15:05:31.045	5-Angel Rios 	2	INDI	23005IM04000055Z	15353	15353	0	357	3
5789	2023-02-08 15:05:31.052	5-Angel Rios 	3	I.S.C.	23005IM04000055Z	0	0	0	357	4
5790	2023-02-08 15:05:31.058	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IM04000055Z	0	0	0	357	33
5791	2023-02-08 15:05:31.067	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IM04000055Z	13938	13938	0	357	5
5792	2023-02-08 15:05:31.076	5-Angel Rios 	5	IRE GENERAL 700	23005IM04000055Z	112145	112145	0	357	38
5793	2023-02-08 15:05:31.085	5-Angel Rios 	6	I.V.A.	23005IM04000055Z	303626	0	303626	357	8
5794	2023-02-08 15:05:31.091	5-Angel Rios 	7	CANON INFORMATICO	6398851	49044	49044	0	357	9
5795	2023-02-08 15:05:31.096	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019781	180000	163636	16363	357	13
5796	2023-02-08 15:05:31.111	5-Angel Rios 	9	FOTOCOPIAS	001-002-0019072	70000	63636	6363	357	12
5797	2023-02-08 15:05:31.121	5-Angel Rios 	10	TASA PORTUARIA	002-001-0020391	18412	16738	1673	357	14
5798	2023-02-08 15:05:31.13	5-Angel Rios 	11	CDAP PAGOS	001-025-0031785	7311	6646	664	357	27
5799	2023-02-08 15:05:31.137	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0196606	460	418	41	357	11
5800	2023-02-08 15:05:31.146	5-Angel Rios 	13	VISACION MRE	x	0	0	0	357	28
5801	2023-02-08 15:05:31.161	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	357	27
5802	2023-02-08 15:05:31.186	5-Angel Rios 	15	VISACION CONSUL	3167336/37	260000	260000	0	357	15
5803	2023-02-08 15:05:31.187	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002905	1133000	1030000	103000	357	16
5804	2023-02-08 15:05:31.204	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	357	17
5805	2023-02-08 15:18:12.667	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002430F	0	0	0	358	2
5806	2023-02-08 15:18:12.675	5-Angel Rios 	2	INDI	23005IC04002430F	28139	28139	0	358	3
5807	2023-02-08 15:18:12.684	5-Angel Rios 	3	I.S.C.	23005IC04002430F	0	0	0	358	4
5808	2023-02-08 15:18:12.689	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002430F	0	0	0	358	33
5809	2023-02-08 15:18:12.697	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002430F	3325469	3325469	0	358	5
5810	2023-02-08 15:18:12.706	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002430F	2675395	2675395	0	358	38
5811	2023-02-08 15:18:12.712	5-Angel Rios 	6	I.V.A.	23005IC04002430F	66884907	0	66884907	358	8
5812	2023-02-08 15:18:12.724	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002430F	0	0	0	358	35
5813	2023-02-08 15:18:12.731	5-Angel Rios 	7	CANON INFORMATICO	6408024	245222	245222	0	358	9
5814	2023-02-08 15:18:12.737	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	358	13
5815	2023-02-08 15:18:12.745	5-Angel Rios 	9	FOTOCOPIAS	001-002-0019071	80000	72727	7272	358	12
5816	2023-02-08 15:18:12.751	5-Angel Rios 	10	TASA PORTUARIA	002-001-0020771	3197488	2906807	290680	358	14
5817	2023-02-08 15:18:12.757	5-Angel Rios 	11	CDAP PAGOS	001-025-0032541	7297	6633	663	358	27
5818	2023-02-08 15:18:12.764	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0197363	79937	72670	7267	358	11
5819	2023-02-08 15:18:12.775	5-Angel Rios 	13	VISACION MRE	x	0	0	0	358	28
5820	2023-02-08 15:18:12.783	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	358	27
5821	2023-02-08 15:18:12.79	5-Angel Rios 	15	VISACION CONSUL	3168653/54/55/56	850000	850000	0	358	15
5822	2023-02-08 15:18:12.798	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002906	5187587	4715988	471598	358	16
5823	2023-02-08 15:18:12.806	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	358	17
5824	2023-02-08 15:18:12.814	5-Angel Rios 	18	GASTO ADMIN.	x	0	0	0	358	17
5825	2023-02-08 15:18:12.822	5-Angel Rios 	19	GASTO ADMIN.	x	0	0	0	358	17
5826	2023-02-08 15:45:52.575	5-Angel Rios 	1	DERECHO ADUANERO	23005IM04000055Z	0	0	0	359	2
5827	2023-02-08 15:45:52.58	5-Angel Rios 	2	INDI	23005IM04000055Z	15353	15353	0	359	3
5828	2023-02-08 15:45:52.591	5-Angel Rios 	3	I.S.C.	23005IM04000055Z	0	0	0	359	4
5829	2023-02-08 15:45:52.615	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IM04000055Z	0	0	0	359	33
5830	2023-02-08 15:45:52.635	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IM04000055Z	13938	13938	0	359	5
5831	2023-02-08 15:45:52.644	5-Angel Rios 	5	IRE GENERAL 700	23005IM04000055Z	12145	12145	0	359	38
5832	2023-02-08 15:45:52.652	5-Angel Rios 	6	I.V.A.	23005IM04000055Z	303626	0	303626	359	8
5833	2023-02-08 15:45:52.656	5-Angel Rios 	7	CANON INFORMATICO	6398851	49044	49044	0	359	9
5834	2023-02-08 15:45:52.661	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-001-0019781	180000	163636	16363	359	13
5835	2023-02-08 15:45:52.675	5-Angel Rios 	9	FOTOCOPIAS	001-002-0019072	70000	63636	6363	359	12
5836	2023-02-08 15:45:52.68	5-Angel Rios 	10	TASA PORTUARIA	002-001-0020391	18412	16738	1673	359	14
5837	2023-02-08 15:45:52.687	5-Angel Rios 	11	CDAP PAGOS	001-025-0031785	7311	6646	664	359	27
5838	2023-02-08 15:45:52.693	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0196606	460	418	41	359	11
5839	2023-02-08 15:45:52.698	5-Angel Rios 	13	VISACION MRE	x	0	0	0	359	28
5840	2023-02-08 15:45:52.706	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	359	27
5841	2023-02-08 15:45:52.714	5-Angel Rios 	15	VISACION CONSUL	3167336/37	260000	260000	0	359	15
5842	2023-02-08 15:45:52.722	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002907	777999	707271	70727	359	16
5843	2023-02-08 15:45:52.726	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	359	17
5844	2023-02-14 13:32:45.739	5-Angel Rios 	1	DERECHO ADUANERO	23030IC04001776P	0	0	0	360	2
5845	2023-02-14 13:32:45.756	5-Angel Rios 	2	INDI	23030IC04001776P	27997	27997	0	360	3
5846	2023-02-14 13:32:45.764	5-Angel Rios 	3	I.S.C.	23030IC04001776P	0	0	0	360	4
5847	2023-02-14 13:32:45.772	5-Angel Rios 	3	ARANC CONS FACTURA 	23030IC04001776P	0	0	0	360	33
5848	2023-02-14 13:32:45.785	5-Angel Rios 	4	SERVICIO DE VALORACION	23030IC04001776P	437555	437555	0	360	5
5849	2023-02-14 13:32:45.792	5-Angel Rios 	5	IRE GENERAL 700	23030IC04001776P	353506	353506	0	360	38
5850	2023-02-14 13:32:45.799	5-Angel Rios 	6	I.V.A.	23030IC04001776P	8837648	0	8837648	360	8
5851	2023-02-14 13:32:45.806	5-Angel Rios 	7	TASA INT. ADUANERA 	23030IC04001776P	0	0	0	360	35
5852	2023-02-14 13:32:45.813	5-Angel Rios 	7	CANON INFORMATICO	001-020-0088814	245222	245222	0	360	9
5853	2023-02-14 13:32:45.818	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	360	13
5854	2023-02-14 13:32:45.824	5-Angel Rios 	9	FOTOCOPIAS	020-002-0006030	40000	36363	3636	360	12
5855	2023-02-14 13:32:45.833	5-Angel Rios 	10	TASA PORTUARIA	001-004-0003823	1246841	1133491	113349	360	14
5856	2023-02-14 13:32:45.841	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	360	27
5857	2023-02-14 13:32:45.847	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	360	11
5858	2023-02-14 13:32:45.854	5-Angel Rios 	13	VISACION MRE	x	0	0	0	360	28
5859	2023-02-14 13:32:45.859	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	360	27
5860	2023-02-14 13:32:45.865	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	360	15
5861	2023-02-14 13:32:45.869	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	x	1595097	1450088	145008	360	16
5862	2023-02-14 13:32:45.876	5-Angel Rios 	17	GASTO ADMIN.	x	660000	660000	0	360	17
5863	2023-02-14 13:56:26.121	5-Angel Rios 	1	DERECHO ADUANERO	23030IC04001776P	0	0	0	361	2
5864	2023-02-14 13:56:26.127	5-Angel Rios 	2	INDI	23030IC04001776P	27997	27997	0	361	3
5865	2023-02-14 13:56:26.145	5-Angel Rios 	3	I.S.C.	23030IC04001776P	0	0	0	361	4
5866	2023-02-14 13:56:26.152	5-Angel Rios 	3	ARANC CONS FACTURA 	23030IC04001776P	0	0	0	361	33
5867	2023-02-14 13:56:26.157	5-Angel Rios 	4	SERVICIO DE VALORACION	23030IC04001776P	437555	437555	0	361	5
5868	2023-02-14 13:56:26.166	5-Angel Rios 	5	IRE GENERAL 700	23030IC04001776P	353506	353506	0	361	38
5869	2023-02-14 13:56:26.172	5-Angel Rios 	6	I.V.A.	23030IC04001776P	8837648	0	8837648	361	8
5870	2023-02-14 13:56:26.178	5-Angel Rios 	7	TASA INT. ADUANERA 	23030IC04001776P	0	0	0	361	35
5871	2023-02-14 13:56:26.185	5-Angel Rios 	7	CANON INFORMATICO	001-020-0088814	245222	245222	0	361	9
5872	2023-02-14 13:56:26.194	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	361	13
5873	2023-02-14 13:56:26.204	5-Angel Rios 	9	FOTOCOPIAS	020-002-0006030	40000	36363	3636	361	12
5874	2023-02-14 13:56:26.211	5-Angel Rios 	10	TASA PORTUARIA	001-004-0003823	1246841	1133491	113349	361	14
5875	2023-02-14 13:56:26.216	5-Angel Rios 	11	CDAP PAGOS	x	0	0	0	361	27
5876	2023-02-14 13:56:26.222	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	x	0	0	0	361	11
5877	2023-02-14 13:56:26.228	5-Angel Rios 	13	VISACION MRE	x	0	0	0	361	28
5878	2023-02-14 13:56:26.233	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	361	27
5879	2023-02-14 13:56:26.247	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	361	15
5880	2023-02-14 13:56:26.256	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002908	1595097	1450088	145008	361	16
5881	2023-02-14 13:56:26.265	5-Angel Rios 	17	GASTO ADMIN.	001-001-0000322	660000	660000	0	361	17
5882	2023-02-16 10:40:28.005	5-Angel Rios 	1	DERECHO ADUANERO	23005IM04000064Z	189510	189510	0	362	2
5883	2023-02-16 10:40:28.023	5-Angel Rios 	2	INDI	23005IM04000064Z	15295	15295	0	362	3
5884	2023-02-16 10:40:28.036	5-Angel Rios 	3	I.S.C.	23005IM04000064Z	0	0	0	362	4
5885	2023-02-16 10:40:28.046	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IM04000064Z	0	0	0	362	33
5886	2023-02-16 10:40:28.05	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IM04000064Z	26855	26855	0	362	5
5887	2023-02-16 10:40:28.062	5-Angel Rios 	5	IRE GENERAL 700	23005IM04000064Z	23284	23284	0	362	38
5888	2023-02-16 10:40:28.069	5-Angel Rios 	6	I.V.A.	23005IM04000064Z	582114	0	582114	362	8
5889	2023-02-16 10:40:28.078	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IM04000064Z	0	0	0	362	35
5890	2023-02-16 10:40:28.084	5-Angel Rios 	7	CANON INFORMATICO	6487289	49044	49044	0	362	9
5891	2023-02-16 10:40:28.09	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	362	13
5892	2023-02-16 10:40:28.098	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094608	80000	72727	7272	362	12
5893	2023-02-16 10:40:28.106	5-Angel Rios 	10	TASA PORTUARIA	002-001-0021965	32621	29655	2965	362	14
5894	2023-02-16 10:40:28.112	5-Angel Rios 	11	CDAP PAGOS	001-025-0034773	7284	6621	662	362	27
5895	2023-02-16 10:40:28.124	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0199592	816	741	74	362	11
5896	2023-02-16 10:40:28.128	5-Angel Rios 	13	VISACION MRE	x	0	0	0	362	28
5897	2023-02-16 10:40:28.136	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	362	27
5898	2023-02-16 10:40:28.141	5-Angel Rios 	15	VISACION CONSUL	3181742/43	260000	260000	0	362	15
5899	2023-02-16 10:40:28.148	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002909	968000	880000	88000	362	16
5900	2023-02-16 10:40:28.156	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	362	17
5901	2023-02-16 10:58:34.148	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002726N	913696	913696	0	363	2
5902	2023-02-16 10:58:34.155	5-Angel Rios 	2	INDI	23005IC04002726N	27860	27860	0	363	3
5903	2023-02-16 10:58:34.163	5-Angel Rios 	3	I.S.C.	23005IC04002726N	0	0	0	363	4
5904	2023-02-16 10:58:34.171	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002726N	0	0	0	363	33
5905	2023-02-16 10:58:34.179	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002726N	5244231	5244231	0	363	5
5906	2023-02-16 10:58:34.185	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002726N	4221720	4221720	0	363	38
5907	2023-02-16 10:58:34.191	5-Angel Rios 	6	I.V.A.	23005IC04002726N	105543038	0	105543038	363	8
5908	2023-02-16 10:58:34.196	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002726N	0	0	0	363	35
5909	2023-02-16 10:58:34.202	5-Angel Rios 	7	CANON INFORMATICO	6487402	245222	245222	0	363	9
5910	2023-02-16 10:58:34.208	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	363	13
5911	2023-02-16 10:58:34.214	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094607	140000	127272	12727	363	12
5912	2023-02-16 10:58:34.218	5-Angel Rios 	10	TASA PORTUARIA	002-001-0021966	4746925	4315386	431538	363	14
5913	2023-02-16 10:58:34.226	5-Angel Rios 	11	CDAP PAGOS	001-025-0034772	7284	6621	662	363	27
5914	2023-02-16 10:58:34.231	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0199593	118673	107884	10788	363	11
5915	2023-02-16 10:58:34.239	5-Angel Rios 	13	VISACION MRE	x	0	0	0	363	28
5916	2023-02-16 10:58:34.246	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	363	27
5917	2023-02-16 10:58:34.253	5-Angel Rios 	15	VISACION CONSUL	3181738/39/40/41	850000	850000	0	363	15
5918	2023-02-16 10:58:34.261	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002910	6924015	6294559	629455	363	16
5919	2023-02-16 10:58:34.265	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	363	17
5920	2023-02-16 11:15:56.98	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002603H	0	0	0	364	2
5921	2023-02-16 11:15:56.988	5-Angel Rios 	2	INDI	23005IC04002603H	40318	40318	0	364	3
5922	2023-02-16 11:15:56.996	5-Angel Rios 	3	I.S.C.	23005IC04002603H	0	0	0	364	4
5923	2023-02-16 11:15:57.015	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002603H	0	0	0	364	33
5924	2023-02-16 11:15:57.023	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002603H	1770668	1770668	0	364	5
5925	2023-02-16 11:15:57.049	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002603H	1426082	1426082	0	364	38
5926	2023-02-16 11:15:57.055	5-Angel Rios 	6	I.V.A.	23005IC04002603H	35652057	0	35652057	364	8
5927	2023-02-16 11:15:57.063	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002603H	0	0	0	364	35
5928	2023-02-16 11:15:57.069	5-Angel Rios 	7	CANON INFORMATICO	6448172	245222	245222	0	364	9
5929	2023-02-16 11:15:57.074	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	364	13
5930	2023-02-16 11:15:57.078	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094566	130000	118181	11818	364	12
5931	2023-02-16 11:15:57.086	5-Angel Rios 	10	TASA PORTUARIA	002-001-0021478	1926073	1750975	175097	364	14
5932	2023-02-16 11:15:57.09	5-Angel Rios 	11	CDAP PAGOS	001-025-0033780	7282	6620	662	364	27
5933	2023-02-16 11:15:57.097	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0198602	48152	43774	4377	364	11
5934	2023-02-16 11:15:57.101	5-Angel Rios 	13	VISACION MRE	x	0	0	0	364	28
5935	2023-02-16 11:15:57.11	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	364	27
5936	2023-02-16 11:15:57.115	5-Angel Rios 	15	VISACION CONSUL	3169493/94/95/9697	1040000	1040000	0	364	15
5937	2023-02-16 11:15:57.123	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002911	4315344	3923040	392304	364	16
5938	2023-02-16 11:15:57.127	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	364	17
5939	2023-02-16 11:41:17.808	5-Angel Rios 	1	DERECHO ADUANERO	23005IC04002879W	0	0	0	365	2
5940	2023-02-16 11:41:17.812	5-Angel Rios 	2	INDI	23005IC04002879W	27860	27860	0	365	3
5941	2023-02-16 11:41:17.826	5-Angel Rios 	3	I.S.C.	23005IC04002879W	0	0	0	365	4
5942	2023-02-16 11:41:17.834	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IC04002879W	0	0	0	365	33
5943	2023-02-16 11:41:17.838	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IC04002879W	1537232	1537232	0	365	5
5944	2023-02-16 11:41:17.846	5-Angel Rios 	5	IRE GENERAL 700	23005IC04002879W	1237638	1237638	0	365	38
5945	2023-02-16 11:41:17.848	5-Angel Rios 	6	I.V.A.	23005IC04002879W	30940955	0	30940955	365	8
5946	2023-02-16 11:41:17.856	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IC04002879W	0	0	0	365	35
5947	2023-02-16 11:41:17.864	5-Angel Rios 	7	CANON INFORMATICO	6509492	245222	245222	0	365	9
5948	2023-02-16 11:41:17.868	5-Angel Rios 	8	APERTURA AG. TRANSP.	x	0	0	0	365	13
5949	2023-02-16 11:41:17.876	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094630	80000	72727	7272	365	12
5950	2023-02-16 11:41:17.88	5-Angel Rios 	10	TASA PORTUARIA	002-001-0022467	1677204	1524730	152473	365	14
5951	2023-02-16 11:41:17.888	5-Angel Rios 	11	CDAP PAGOS	001-025-0035752	7282	6620	662	365	27
5952	2023-02-16 11:41:17.899	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0200574	41930	38118	3811	365	11
5953	2023-02-16 11:41:17.902	5-Angel Rios 	13	VISACION MRE	x	0	0	0	365	28
5954	2023-02-16 11:41:17.91	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	365	27
5955	2023-02-16 11:41:17.918	5-Angel Rios 	15	VISACION CONSUL	3183175/76/77/78/79	1040000	1040000	0	365	15
5956	2023-02-16 11:41:17.923	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002912	4231137	3846488	384648	365	16
5957	2023-02-16 11:41:17.931	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	365	17
5958	2023-02-16 14:19:17.558	5-Angel Rios 	1	DERECHO ADUANERO	23005IT02000322S	0	0	0	366	2
5959	2023-02-16 14:19:17.566	5-Angel Rios 	2	INDI	23005IT02000322S	0	0	0	366	3
5960	2023-02-16 14:19:17.579	5-Angel Rios 	3	I.S.C.	23005IT02000322S	0	0	0	366	4
5961	2023-02-16 14:19:17.587	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IT02000322S	0	0	0	366	33
5962	2023-02-16 14:19:17.587	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IT02000322S	0	0	0	366	5
5963	2023-02-16 14:19:17.595	5-Angel Rios 	5	IRE GENERAL 700	23005IT02000322S	0	0	0	366	38
5964	2023-02-16 14:19:17.595	5-Angel Rios 	6	I.V.A.	23005IT02000322S	0	0	0	366	8
5965	2023-02-16 14:19:17.61	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IT02000322S	364162	364162	0	366	35
5966	2023-02-16 14:19:17.619	5-Angel Rios 	7	ROYAL SEGUROS A COBRAR 	001-004-0008243	4095000	4095000	0	366	9
5967	2023-02-16 14:19:17.619	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-004-0002815	450000	409090	40909	366	13
5968	2023-02-16 14:19:17.627	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094661	70000	63636	6363	366	12
5969	2023-02-16 14:19:17.635	5-Angel Rios 	10	TASA PORTUARIA	002-001-0022941	1281454	1164958	116495	366	14
5970	2023-02-16 14:19:17.643	5-Angel Rios 	11	CDAP PAGOS	001-025-0036656	7282	6620	662	366	27
5971	2023-02-16 14:19:17.65	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0201476	32036	29123	2912	366	11
5975	2023-02-16 14:19:17.693	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002913	5448589	4953262	495326	366	16
5976	2023-02-16 14:19:17.709	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	366	17
5977	2023-02-16 14:35:33.942	5-Angel Rios 	1	DERECHO ADUANERO	23005IT02000322S	0	0	0	367	2
5978	2023-02-16 14:35:33.951	5-Angel Rios 	2	INDI	23005IT02000322S	0	0	0	367	3
5979	2023-02-16 14:35:33.956	5-Angel Rios 	3	ARANC CONS FACTURA 	23005IT02000322S	0	0	0	367	33
5980	2023-02-16 14:35:33.965	5-Angel Rios 	3	I.S.C.	23005IT02000322S	0	0	0	367	4
5981	2023-02-16 14:35:33.973	5-Angel Rios 	4	SERVICIO DE VALORACION	23005IT02000322S	0	0	0	367	5
5982	2023-02-16 14:35:33.981	5-Angel Rios 	5	IRE GENERAL 700	23005IT02000322S	0	0	0	367	38
5983	2023-02-16 14:35:33.997	5-Angel Rios 	6	I.V.A.	23005IT02000322S	0	0	0	367	8
5984	2023-02-16 14:35:34.005	5-Angel Rios 	7	ROYAL SEGUROS A COBRAR 	001-004-0008243	4095000	4095000	0	367	9
5985	2023-02-16 14:35:34.013	5-Angel Rios 	7	TASA INT. ADUANERA 	23005IT02000322S	364162	364162	0	367	35
5986	2023-02-16 14:35:34.013	5-Angel Rios 	8	APERTURA AG. TRANSP.	001-004-0002815	450000	409090	40909	367	13
5987	2023-02-16 14:35:34.026	5-Angel Rios 	9	FOTOCOPIAS	001-001-0094661	70000	63636	6363	367	12
5988	2023-02-16 14:35:34.031	5-Angel Rios 	10	TASA PORTUARIA	002-001-0022941	1281454	1164958	116495	367	14
5989	2023-02-16 14:35:34.036	5-Angel Rios 	11	CDAP PAGOS	001-025-0036656	7282	6620	662	367	27
5990	2023-02-16 14:35:34.046	5-Angel Rios 	12	SERVICIOS SOFIA / ANNP	001-004-0201476	32036	29123	2912	367	11
5991	2023-02-16 14:35:34.051	5-Angel Rios 	13	VISACION MRE	x	0	0	0	367	28
5992	2023-02-16 14:35:34.056	5-Angel Rios 	14	CDAP PAGOS	x	0	0	0	367	27
5993	2023-02-16 14:35:34.066	5-Angel Rios 	15	VISACION CONSUL	x	0	0	0	367	15
5994	2023-02-16 14:35:34.076	5-Angel Rios 	16	HON. DESP. S/ LEY 220/93	001-001-0002913	5448589	4953262	495326	367	16
5995	2023-02-16 14:35:34.085	5-Angel Rios 	17	GASTO ADMIN.	x	0	0	0	367	17
\.


--
-- TOC entry 3611 (class 0 OID 24656)
-- Dependencies: 229
-- Data for Name: item_mercaderia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_mercaderia (iditem_mercaderia, fecha_creacion, creado_por, nombre, fk_idliquidacion_proforma, fk_idmercaderia) FROM stdin;
\.


--
-- TOC entry 3612 (class 0 OID 24661)
-- Dependencies: 230
-- Data for Name: item_pais_ciudad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_pais_ciudad (iditem_pais_ciudad, fk_idtercero_pais, fk_idtercero_ciudad) FROM stdin;
\.


--
-- TOC entry 3613 (class 0 OID 24664)
-- Dependencies: 231
-- Data for Name: item_pre_liquidacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_pre_liquidacion (iditem_pre_liquidacion, fecha_creado, creado_por, orden, descripcion, comprobante_nro, total_guarani, sin_iva, solo_iva, fk_idpre_liquidacion, fk_idcomprobante_liquidacion) FROM stdin;
1	2022-08-09 10:23:31.505	5-Angel Rios 	1	DERECHO ADUANERO	x	22222	22222	0	1	2
2	2022-08-09 10:23:31.511	5-Angel Rios 	2	INDI	x	0	0	0	1	3
3	2022-08-09 10:23:31.518	5-Angel Rios 	3	I.S.C.	x	0	0	0	1	4
4	2022-08-09 10:23:31.524	5-Angel Rios 	4	SERVICIO DE VALORACION	x	0	0	0	1	5
5	2022-08-09 10:23:31.53	5-Angel Rios 	5	IRE GENERAL 700	x	0	0	0	1	38
6	2022-08-09 10:23:31.535	5-Angel Rios 	6	I.V.A.	x	0	0	0	1	8
7	2022-08-09 10:23:31.539	5-Angel Rios 	7	CANON INFORMATICO	x	0	0	0	1	9
8	2022-08-09 10:23:31.546	5-Angel Rios 	8	FOTOCOPIAS	x	0	0	0	1	12
9	2022-08-09 10:23:31.559	5-Angel Rios 	9	TASA PORTUARIA	x	0	0	0	1	14
10	2022-08-09 10:23:31.565	5-Angel Rios 	10	CDAP DINAC	x	0	0	0	1	31
11	2022-08-09 10:23:31.57	5-Angel Rios 	11	VISACION MRE	x	0	0	0	1	28
12	2022-08-09 10:23:31.575	5-Angel Rios 	12	CDAP PAGOS	x	0	0	0	1	27
13	2022-08-09 10:23:31.582	5-Angel Rios 	13	HON. DESP. S/ LEY 220/93	x	0	0	0	1	16
\.


--
-- TOC entry 3614 (class 0 OID 24669)
-- Dependencies: 232
-- Data for Name: item_tipo_registro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_tipo_registro (iditem_tipo_registro, fecha_creacion, creado_por, estado, fecha_estado, nro_habilitacion, fecha_habilitacion, fecha_vencimiento, imagen, fk_idtipo_registro, fk_idtipo_dependencia, fk_idtipo_institucion, fk_idtercero) FROM stdin;
1	2021-11-13 21:03:48.524	Digno	ACTIVO	2021-11-13	45234235	2021-11-13	2021-11-13	SIN-IMAGEN	1	1	1	0
2	2021-11-13 21:13:59.871	Digno	ACTIVO	2021-11-13	123123123	2021-11-13	2021-11-13	SIN-IMAGEN	1	1	1	2
3	2021-11-13 21:19:02.133	Digno	ACTIVO	2021-11-13	1212312	2021-11-13	2021-11-13	SIN-IMAGEN	1	1	1	2
4	2021-11-13 21:21:03.25	Digno	ACTIVO	2021-11-13	56456456	2021-11-13	2021-11-13	SIN-IMAGEN	1	1	1	2
5	2021-11-13 21:22:15.135	Digno	ACTIVO	2021-11-13	756756757	2021-11-13	2021-11-13	SIN-IMAGEN	1	1	1	2
6	2021-11-13 21:40:47.4	Digno	DESABILITADO	2021-11-20	2312312	2021-11-20	2021-11-20	SIN-IMAGEN	1	1	1	2
7	2021-11-13 21:44:31.34	Digno	ACTIVO	2021-11-30	12515	2021-11-30	2021-11-30	SIN-IMAGEN	1	1	1	0
8	2021-11-13 21:51:02.341	Digno	ACTIVO	2021-11-15	55545	2021-11-15	2021-11-15	SIN-IMAGEN	1	1	1	2
\.


--
-- TOC entry 3615 (class 0 OID 24674)
-- Dependencies: 233
-- Data for Name: item_usuario_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_usuario_rol (iditem_usuario_rol, estado, fk_idusuario_rol, fk_idusuario_evento) FROM stdin;
258	t	3	25
259	t	3	24
260	t	3	23
261	t	3	22
262	t	3	21
263	t	3	20
264	t	3	19
265	t	3	18
266	t	3	17
267	t	3	16
268	t	3	15
269	t	3	14
270	t	3	13
271	t	3	12
272	t	3	11
273	t	3	10
274	t	3	9
275	t	3	8
276	t	3	7
277	t	3	6
278	t	3	5
279	t	3	4
280	t	3	3
281	t	3	2
282	t	3	1
283	t	2	25
284	t	2	24
285	t	2	23
286	t	2	22
287	t	2	21
288	t	2	20
289	t	2	19
290	t	2	18
291	t	2	17
292	t	2	16
293	t	2	15
294	t	2	14
295	t	2	13
296	t	2	12
297	t	2	11
298	t	2	10
299	t	2	9
300	t	2	8
301	t	2	7
302	t	2	6
303	t	2	5
304	t	2	4
305	t	2	3
306	t	2	2
307	t	2	1
234	f	4	24
235	f	4	23
236	f	4	22
237	f	4	21
238	f	4	20
239	f	4	19
240	f	4	18
241	f	4	17
242	f	4	16
243	f	4	15
244	f	4	14
245	f	4	13
246	f	4	12
247	f	4	11
248	f	4	10
249	f	4	9
250	f	4	8
251	f	4	7
252	f	4	6
253	f	4	5
254	f	4	4
255	f	4	3
256	f	4	2
257	f	4	1
\.


--
-- TOC entry 3616 (class 0 OID 24677)
-- Dependencies: 234
-- Data for Name: liquidacion_final; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.liquidacion_final (idliquidacion_final, fecha_creado, fecha_pagado, creado_por, fecha_despacho, despacho_numero, tipo_liquidacion, estado, observacion, contenedor_nro, contenedor_tipo, via_transporte, transporte_condicion, monto_imponible, monto_ajuste_incluir, monto_factura, monto_flete, monto_seguro, monto_cif, monto_total_despacho, monto_adelanto, monto_pagar, tasa_cambio_aduana, tasa_cambio_mercado, monto_pagado, tipo_tasa_cambio, factura_numero, monto_letra, fk_idtipo_comprobante, fk_idtercero_ciudad, fk_idaduana, fk_iddespacho_zona, fk_idtransporte_empresa, fk_idtercero_importador, fk_idtercero_exportador, fk_idmoneda_cambio, fk_idregimen, fk_idincoterms, otro_nombre, otro_monto, fk_idtercero_despachante, fk_idrecibo_pago_tercero) FROM stdin;
1	2022-01-18 16:52:14.943	2022-02-17 13:37:36.378342	3-ADMINISTRADOR	2021-12-22	21002IC04027163K	IMPORTACION	PAGADO	NINGUNA	X	COMPLETA o FCL	TERRESTRE	COMPLETA o FCL	51338495	0	7255.20	207.28	72.55	7535.03	464300	0	464300	6813.31	7000.00	464300	DOLAR	PU29941	CUATROCIENTOS SESENTA Y CUATRO MIL TRECIENTOS 	31	1	2	2	2	2	4	1	1	1	otro	0	1	1
2	2022-02-04 16:33:38.057	2022-02-17 13:37:36.378342	1-Digno Alfredo	2022-02-04	32423sdfs	IMPORTACION	PAGADO	NINGUNA	324234	L.C.L.	TERRESTRE	L.C.L.	79528500	0	11500.00	60.00	50.00	11610.00	500000	0	500000	6850.00	6800.00	435700	GUARANÍ	123121	QUINIENTOS MIL 	31	7	21	2	2	2	11	4	2	2	OTROS	50000	1	1
4	2022-02-17 13:37:18.881	2022-02-17 13:37:18.881	5-Angel Rios 	2022-01-17	22005IC04000981N	IMPORTACION	ANULADO	NINGUNA	X	F.C.L.	TERRESTRE	F.C.L.	828777589	0	116094.66	1500.00	1160.95	118755.61	91287196	0	91287196	6978.85	6800.00	0	GUARANÍ	01/2022	NOVENTA Y UN MILLONES DOSCIENTOS OCHENTA Y SIETE MIL CIENTO NOVENTA Y SEIS 	47	4	5	2	3	3	21	4	2	2	OTROS	0	16	0
5	2022-04-27 14:14:19.835	2022-04-27 14:14:19.835	5-Angel Rios 	2022-04-26	22005IC04008320X	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	633619382	0	91200.00	775.00	912.00	92887.00	78656671	0	78656671	6821.40	6800.00	0	GUARANÍ	08/2022	SETENTA Y OCHO MILLONES SEISCIENTOS CINCUENTA Y SEIS MIL SEISCIENTOS SETENTA Y UN 	47	4	5	2	3	3	24	4	2	2	OTROS	0	16	0
3	2022-02-17 12:03:16.645	2022-02-17 12:03:16.646	1-Digno Alfredo	2022-02-17	6467456	IMPORTACION	ANULADO	NINGUNA	6756757	L.C.L.	TERRESTRE	L.C.L.	28427500	0	4100.00	50.00	0.00	4150.00	10	0	10	6850.00	6800.00	0	GUARANÍ	45645645	DIEZ 	31	7	36	2	2	2	6	4	2	2	OTROS	0	18	0
6	2022-04-27 15:11:58.144	2022-04-27 15:11:58.144	5-Angel Rios 	2022-04-26	22005IM04000192R	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	11779876	0	942.48	775.00	9.42	1726.90	4474051	0	4474051	6821.40	6800.00	0	GUARANÍ	09/2022	CUATRO MILLON CUATROCIENTOS SETENTA Y CUATRO MIL CINCUENTA Y UN 	47	4	5	2	3	3	24	4	19	2	OTROS	0	16	0
7	2022-04-27 15:41:47.373	2022-04-27 15:41:47.373	5-Angel Rios 	2022-04-26	22005IC04008254Y	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	208274941	0	28458.00	1900.00	174.58	30532.58	18880559	0	18880559	6821.40	6800.00	0	GUARANÍ	MP-009-022	DIECIOCHO MILLONES OCHOCIENTOS OCHENTA MIL QUINIENTOS CINCUENTA Y NUEVE 	1	4	5	2	1	8	26	4	2	2	OTROS	0	16	0
9	2022-04-28 14:09:37.276	2022-04-28 14:09:37.276	5-Angel Rios 	2022-04-27	22005IC04008374R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	71535713	100	9292.97	970.00	92.93	10355.90	12333922	9365811	2968111	6841.66	6800.00	0	GUARANÍ	9292.97	DOS MILLON NOVECIENTOS SESENTA Y OCHO MIL CIENTO ONCE 	50	4	5	2	4	8	27	4	2	3	OTROS	0	16	0
10	2022-04-28 14:10:35.357	2022-04-28 14:10:35.357	5-Angel Rios 	2022-04-27	22005IC04008374R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	71622915	100	9292.97	970.00	92.93	10355.90	12333922	9365811	2968111	6850.00	6800.00	0	GUARANÍ	WDSJ00645-22	DOS MILLON NOVECIENTOS SESENTA Y OCHO MIL CIENTO ONCE 	50	4	5	2	4	8	27	4	2	3	OTROS	0	16	0
8	2022-04-27 15:43:45.838	2022-04-27 15:43:45.838	5-Angel Rios 	2022-04-26	22005IC04008254Y	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	209148173	0	28458.00	1900.00	174.58	30532.58	18880559	14662188	4218371	6850.00	6800.00	0	GUARANÍ	MP-009-022	CUATRO MILLON DOSCIENTOS DIECIOCHO MIL TRECIENTOS SETENTA Y UN 	1	4	5	2	1	8	26	4	2	2	OTROS	0	16	0
11	2022-04-28 14:14:44.489	2022-04-28 14:14:44.489	5-Angel Rios 	2022-04-26	22005IC04008254Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	133798173	0	17458.00	1900.00	174.58	19532.58	18880559	14662188	4218371	6850.00	6800.00	0	GUARANÍ	MP-009-022	CUATRO MILLON DOSCIENTOS DIECIOCHO MIL TRECIENTOS SETENTA Y UN 	1	4	5	2	1	8	26	4	2	2	OTROS	0	16	0
12	2022-05-03 16:00:54.53	2022-05-03 16:00:54.53	5-Angel Rios 	2022-04-28	22009IC04000587T	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	116834924	0	16400.14	500.00	164.00	17064.14	21066909	0	21066909	6846.81	6800.00	0	GUARANÍ	KV223287/KV241972	VEINTE Y UN MILLONES SESENTA Y SEIS MIL NOVECIENTOS NUEVE 	51	4	9	2	5	8	28	4	2	3	OTROS	0	16	0
13	2022-05-03 16:05:23.266	2022-05-03 16:05:23.266	5-Angel Rios 	2022-04-28	22009IC04000587T	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	116889359	0	16400.14	500.00	164.00	17064.14	21683266	12913596	8769670	6850.00	6800.00	0	GUARANÍ	KV223287/KV241972	OCHO MILLON SETECIENTOS SESENTA Y NUEVE MIL SEISCIENTOS SETENTA 	51	4	9	2	5	8	28	4	2	3	OTROS	0	16	0
14	2022-05-03 16:08:33.284	2022-05-03 16:08:33.284	5-Angel Rios 	2022-04-28	22009IC04000587T	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	116834924	0	16400.14	500.00	164.00	17064.14	21683266	12913596	8769670	6846.81	6800.00	0	GUARANÍ	KV223287/KV241972	OCHO MILLON SETECIENTOS SESENTA Y NUEVE MIL SEISCIENTOS SETENTA 	51	4	9	2	5	8	28	4	2	3	OTROS	0	16	0
15	2022-05-04 12:37:17.359	2022-05-04 12:37:17.359	5-Angel Rios 	2022-05-03	22005IC04008737U	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	662928003	0	94612.00	1550.00	946.12	97108.12	82375784	0	82375784	6826.70	6800.00	0	GUARANÍ	10/2022	OCHENTA Y DOS MILLONES TRECIENTOS SETENTA Y CINCO MIL SETECIENTOS OCHENTA Y CUATRO 	47	4	5	2	3	3	24	4	2	2	OTROS	0	16	0
16	2022-05-04 12:59:55.342	2022-05-04 12:59:55.342	5-Angel Rios 	2022-05-03	22005IC04008739W	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	311346741	0	43621.00	1550.00	436.21	45607.21	41390090	0	41390090	6826.70	6800.00	0	GUARANÍ	011PY/2022	CUARENTA Y UN MILLONES TRECIENTOS NOVENTA MIL NOVENTA 	47	4	5	2	3	3	29	4	2	3	OTROS	0	16	0
17	2022-05-06 11:47:16.741	2022-05-06 11:47:16.741	5-Angel Rios 	2022-05-04	22030IC04005329M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	76766281	0	10802.09	400.00	30.00	11232.09	12109730	8475587	3634143	6834.55	6800.00	0	GUARANÍ	002200004077	TRES MILLON SEISCIENTOS TREINTA Y CUATRO MIL CIENTO CUARENTA Y TRES 	50	1	30	3	6	22	30	4	2	3	OTROS	0	17	0
18	2022-05-06 13:40:39.777	2022-05-06 13:40:39.777	5-Angel Rios 	2022-05-04	22002IC04008988C	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	86880800	0	11349.36	1248.00	114.64	12712.00	26259209	0	26259209	6834.55	6800.00	0	GUARANÍ	202203281111-1	VEINTE Y SEIS MILLONES DOSCIENTOS CINCUENTA Y NUEVE MIL DOSCIENTOS NUEVE 	53	4	2	3	7	3	32	4	2	5	OTROS	0	17	0
19	2022-05-06 13:47:58.052	2022-05-06 13:47:58.052	5-Angel Rios 	2022-05-04	22002IC04008988C	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	87077200	0	11349.36	1248.00	114.64	12712.00	26259209	0	26259209	6850.00	6800.00	0	GUARANÍ	202203281111-1	VEINTE Y SEIS MILLONES DOSCIENTOS CINCUENTA Y NUEVE MIL DOSCIENTOS NUEVE 	53	4	2	3	7	3	32	4	2	5	OTROS	0	17	0
20	2022-05-06 13:50:01.563	2022-05-06 13:50:01.563	5-Angel Rios 	2022-05-04	22002IC04008988C	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	87077200	0	11349.36	1248.00	114.64	12712.00	26979209	0	26979209	6850.00	6800.00	0	GUARANÍ	202203281111-1	VEINTE Y SEIS MILLONES NOVECIENTOS SETENTA Y NUEVE MIL DOSCIENTOS NUEVE 	53	4	2	3	7	3	32	4	2	5	OTROS	0	17	0
22	2022-05-06 14:17:51.296	2022-05-06 14:17:51.296	5-Angel Rios 	2022-05-04	22005IC04008880T	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	179714492	0	24500.00	1550.00	245.00	26295.00	25233643	0	25233643	6834.55	6800.00	0	GUARANÍ	004/22	VEINTE Y CINCO MILLONES DOSCIENTOS TREINTA Y TRES MIL SEISCIENTOS CUARENTA Y TRES 	47	4	5	2	3	34	35	4	2	2	OTROS	0	16	0
23	2022-05-10 15:42:13.48	2022-05-10 15:42:13.48	5-Angel Rios 	2022-05-06	22023EC01001717E	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	271690808	0	39739.47	0.00	0.00	39739.47	3257319	0	3257319	6836.80	6800.00	0	GUARANÍ	001-002-0000218	TRES MILLON DOSCIENTOS CINCUENTA Y SIETE MIL TRECIENTOS DIECINUEVE	54	13	23	2	1	15	36	4	3	3	OTROS	0	16	0
24	2022-05-12 14:39:38.236	2022-05-12 14:39:38.236	5-Angel Rios 	2022-05-06	22023EC01001719G	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	446132923	0	65254.64	0.00	0.00	65254.64	3606748	0	3606748	6836.80	6800.00	0	GUARANÍ	001-002-0000220	TRES MILLON SEISCIENTOS SEIS MIL SETECIENTOS CUARENTA Y OCHO 	1	13	23	2	1	15	37	4	3	3	OTROS	0	16	0
25	2022-05-13 16:37:42.864	2022-05-13 16:37:42.864	5-Angel Rios 	2022-05-12	22005IC04009558W	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	233738360	0	32091.72	1550.00	320.92	33962.64	31522584	0	31522584	6882.22	6800.00	0	GUARANÍ	013PY/2022	TREINTA Y UN MILLONES QUINIENTOS VEINTE Y DOS MIL QUINIENTOS OCHENTA Y CUATRO 	47	4	5	2	3	3	29	4	2	3	OTROS	0	16	0
26	2022-05-17 12:53:03.413	2022-05-17 12:53:03.414	1-Digno Alfredo	2022-02-17	6467456	IMPORTACIÓN	ANULADO	NINGUNA	6756757	L.C.L.	TERRESTRE	L.C.L.	28275237	0	4100.00	50.00	0.00	4150.00	10	0	10	6813.31	7000.00	0	DOLAR	45645645	DIEZ 	31	7	36	2	2	2	6	1	2	2	OTROS	0	16	0
27	2022-05-17 13:34:33.7	2022-05-17 13:34:33.702	1-Digno Alfredo	2022-02-17	6467456	IMPORTACIÓN	ANULADO	NINGUNA	6756757	L.C.L.	TERRESTRE	L.C.L.	28275237	0	4100.00	50.00	0.00	4150.00	115604850	0	115604850	6813.31	7000.00	0	DOLAR	45645645	CIENTO QUINCE MILLONES SEISCIENTOS CUATRO MIL OCHOCIENTOS CINCUENTA 	31	7	36	2	2	2	6	1	2	2	OTROS	0	16	0
28	2022-05-17 13:37:19.719	2022-05-17 13:37:19.72	1-Digno Alfredo	2022-05-17	ytryrtyru	IMPORTACIÓN	ANULADO	NINGUNA	745747	L.C.L.	TERRESTRE	L.C.L.	78353065	0	11000.00	500.00	0.00	11500.00	50	0	50	6813.31	7000.00	0	DOLAR	656858	CINCUENTA 	47	8	20	3	6	21	38	1	79	4	OTROS	0	18	0
29	2022-05-17 13:48:26.196	2022-05-17 13:48:26.197	5-Angel Rios 	2022-05-17	22005IC04009800M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	76153240	0	9921.20	1050.00	99.21	11070.41	12158822	8733754	3425068	6878.99	7000.00	0	DOLAR	20226018	TRES MILLON CUATROCIENTOS VEINTE Y CINCO MIL SESENTA Y OCHO 	55	4	5	2	4	8	38	1	2	2	OTROS	0	16	0
30	2022-05-18 09:42:33.324	2022-05-18 09:42:33.324	5-Angel Rios 	2022-05-18	22029IC04001134K	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	106779711	580	12100.00	2835.00	54.55	14989.55	17407877	12197047	5210830	6858.24	7000.00	0	DOLAR	DTS20220206	CINCO MILLON DOSCIENTOS DIEZ MIL OCHOCIENTOS TREINTA 	57	1	31	3	8	39	40	1	2	10	OTROS	0	17	0
31	2022-05-18 11:08:01.94	2022-05-18 11:08:01.94	5-Angel Rios 	2022-05-18	22029IC04001134K	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	106779711	580	12100.00	2835.00	54.55	14989.55	20552684	12197047	8355637	6858.24	7000.00	0	DOLAR	DTS20220206	OCHO MILLON TRECIENTOS CINCUENTA Y CINCO MIL SEISCIENTOS TREINTA Y SIETE 	57	1	29	3	8	39	40	1	2	10	OTROS	0	17	0
32	2022-05-18 11:19:41.18	2022-05-18 11:19:41.18	5-Angel Rios 	2022-05-18	22029IC04001134K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	106080171	580	12100.00	2835.00	54.55	14989.55	20552684	12197047	8355637	6813.31	7000.00	0	DOLAR	DTS20220206	OCHO MILLON TRECIENTOS CINCUENTA Y CINCO MIL SEISCIENTOS TREINTA Y SIETE 	57	1	29	3	8	39	40	1	2	10	OTROS	0	17	0
33	2022-05-24 09:46:23.45	2022-05-24 09:46:23.451	5-Angel Rios 	2022-05-20	22005IC04010324F	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	560083212	0	80283.42	775.00	802.83	81861.25	71196509	0	71196509	6841.86	7000.00	0	DOLAR	11/2022	SETENTA Y UN MILLONES CIENTO NOVENTA Y SEIS MIL QUINIENTOS NUEVE 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
34	2022-05-24 09:57:10.13	2022-05-24 09:57:10.13	5-Angel Rios 	2022-05-20	22005IC04010326H	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	550228539	0	78090.00	1550.00	780.90	80420.90	69005861	0	69005861	6841.86	7000.00	0	DOLAR	13/2022	SESENTA Y NUEVE MILLONES CINCO MIL OCHOCIENTOS SESENTA Y UN 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
35	2022-05-24 11:33:52.646	2022-05-24 11:33:52.646	5-Angel Rios 	2022-05-24	22005IM04000231L	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	7644205	0	338.88	775.00	3.39	1117.27	2775452	0	2775452	6841.86	7000.00	0	DOLAR	12/2022	DOS MILLON SETECIENTOS SETENTA Y CINCO MIL CUATROCIENTOS CINCUENTA Y DOS 	47	4	5	2	3	3	41	1	19	2	OTROS	0	16	0
36	2022-05-24 12:06:25.129	2022-05-24 12:06:25.129	5-Angel Rios 	2022-05-20	22005IC04010303C	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	525914347	0	69600.38	6570.78	696.00	76867.16	72986347	57635348	15350999	6841.86	7000.00	0	DOLAR	ZU22012701J	QUINCE MILLONES TRECIENTOS CINCUENTA MIL NOVECIENTOS NOVENTA Y NUEVE 	54	1	5	3	9	15	42	1	2	12	OTROS	0	16	0
37	2022-05-24 12:09:31.785	2022-05-24 12:09:31.785	5-Angel Rios 	2022-05-20	22005IC04010303C	IMPORTACIÓN	EMITIDO	CRT CON CUATRO CARRETAS.-----	-	F.C.L.	TERRESTRE	F.C.L.	523719790	0	69600.38	6570.78	696.00	76867.16	72986347	57635348	15350999	6813.31	7000.00	0	DOLAR	ZU22012701J	QUINCE MILLONES TRECIENTOS CINCUENTA MIL NOVECIENTOS NOVENTA Y NUEVE 	54	1	5	3	9	15	42	1	2	12	DERECHO ADUANERO MP LIBERADO 	94664583	16	0
39	2022-05-26 12:03:22.353	2022-05-26 12:03:22.353	5-Angel Rios 	2022-05-25	22005IC04010686Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	242713861	0	33384.96	1650.00	333.85	35368.81	32016764	26597004	5419760	6862.37	7000.00	0	DOLAR	HSBR220511-4	CINCO MILLON CUATROCIENTOS DIECINUEVEMIL SETECIENTOS SESENTA 	54	1	5	2	9	15	44	1	2	2	OTROS	0	16	0
40	2022-05-26 13:42:50.719	2022-05-26 13:42:50.719	5-Angel Rios 	2022-05-25	22023EC01002080V	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	451011168	0	65858.16	0.00	0.00	65858.16	3559798	0	3559798	6848.22	7000.00	0	DOLAR	001-002-0000222	TRES MILLON QUINIENTOS CINCUENTA Y NUEVE MIL SETECIENTOS NOVENTA Y OCHO 	1	13	23	2	1	15	46	1	3	3	OTROS	0	16	0
41	2022-05-30 15:11:43.701	2022-05-30 15:11:43.701	5-Angel Rios 	2022-05-26	22038IC04005438V	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	230720632	0	20452.50	13120.00	60.00	33632.50	49191767	43646921	5544846	6860.05	7000.00	0	DOLAR	QE20210924-04B	CINCO MILLON QUINIENTOS CUARENTA Y CUATRO MIL OCHOCIENTOS CUARENTA Y SEIS 	58	1	19	3	11	22	47	1	2	10	OTROS	0	17	0
42	2022-05-30 15:14:34.503	2022-05-30 15:14:34.503	5-Angel Rios 	2022-05-26	22038IC04005438V	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	229148649	0	20452.50	13120.00	60.00	33632.50	49181767	43646921	5534846	6813.31	7000.00	0	DOLAR	QE20210924-04B	CINCO MILLON QUINIENTOS TREINTA Y CUATRO MIL OCHOCIENTOS CUARENTA Y SEIS 	58	1	19	3	11	22	47	1	2	10	OTROS	0	17	0
38	2022-05-26 11:08:32.245	2022-05-26 11:08:32.245	5-Angel Rios 	2022-05-23	22005IC04010454J	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	169570396	254	23951.26	590.00	15.11	24556.37	23206735	0	23206735	6834.76	7000.00	0	DOLAR	JME0422E22	VEINTE Y TRES MILLONES DOSCIENTOS SEIS MIL SETECIENTOS TREINTA Y CINCO 	47	4	5	2	10	3	43	1	2	3	OTROS	0	16	0
43	2022-05-31 09:57:56.199	2022-05-31 09:57:56.199	5-Angel Rios 	2022-05-30	22005IC04010983Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	342014770	0	47760.91	1550.00	477.61	49788.52	44967611	0	44967611	6869.35	7000.00	0	DOLAR	016PY72022	CUARENTA Y CUATRO MILLONES NOVECIENTOS SESENTA Y SIETE MIL SEISCIENTOS ONCE 	47	4	5	2	3	3	48	1	2	3	OTROS	0	16	0
44	2022-05-31 10:21:45.486	2022-05-31 10:21:45.486	5-Angel Rios 	2022-05-30	22005IC04011037H	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	129198795	0	17600.80	1050.00	176.01	18826.81	19492836	14219647	5273189	6862.49	7000.00	0	DOLAR	20226064	CINCO MILLON DOSCIENTOS SETENTA Y TRES MIL CIENTO OCHENTA Y NUEVE 	59	4	5	2	4	8	38	1	2	2	OTROS	0	16	0
45	2022-05-31 10:24:19.201	2022-05-31 10:24:19.201	5-Angel Rios 	2022-05-30	22005IC04011037H	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	128272893	0	17600.80	1050.00	176.01	18826.81	19492836	14219647	5273189	6813.31	7000.00	0	DOLAR	20226064	CINCO MILLON DOSCIENTOS SETENTA Y TRES MIL CIENTO OCHENTA Y NUEVE 	59	4	5	2	4	8	38	1	2	2	OTROS	0	16	0
46	2022-05-31 10:54:13.242	2022-05-31 10:54:13.242	5-Angel Rios 	2022-05-30	22005IC04011020W	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	34068283	0	4543.98	375.00	45.44	4964.42	6974301	3804681	3169620	6862.49	7000.00	0	DOLAR	008.2022	TRES MILLON CIENTO SESENTA Y NUEVE MIL SEISCIENTOS VEINTE 	60	1	5	2	10	15	49	1	2	3	OTROS	0	16	0
47	2022-06-02 14:40:54.972	2022-06-02 14:40:54.973	5-Angel Rios 	2022-05-31	22002IM04005115Y	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	12047985	0	1345.00	392.10	13.45	1750.55	2805148	353998	2451150	6882.40	7000.00	0	DOLAR	ES2022050501	DOS MILLON CUATROCIENTOS CINCUENTA Y UN MIL CIENTO CINCUENTA 	61	1	2	3	12	50	51	1	19	3	OTROS	0	17	0
48	2022-06-02 14:51:35.557	2022-06-02 14:51:35.557	5-Angel Rios 	2022-05-31	22002IM04005115Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	11927040	0	1345.00	392.10	13.45	1750.55	3759508	353998	3405510	6813.31	7000.00	0	DOLAR	ES2022050501	TRES MILLON CUATROCIENTOS CINCO MIL QUINIENTOS DIEZ 	61	1	2	3	12	50	51	1	19	3	OTROS	0	17	0
49	2022-06-02 16:46:59.743	2022-06-02 16:46:59.743	5-Angel Rios 	2022-06-01	22038IC04005516S	IMPORTACIÓN	ANULADO	NINGUNA	1X40	F.C.L.	FLUVIAL	F.C.L.	356843453	0	36385.00	15560.00	83.69	52028.69	92902262	84854001	8048261	6858.59	7000.00	0	DOLAR	PR220118	OCHO MILLON CUARENTA Y OCHO MIL DOSCIENTOS SESENTA Y UN 	62	1	19	3	11	22	52	1	2	10	OTROS	0	17	0
50	2022-06-03 12:18:33.721	2022-06-03 12:18:33.721	5-Angel Rios 	2022-06-01	22038IC04005516S	IMPORTACIÓN	EMITIDO	NINGUNA	1X40	F.C.L.	FLUVIAL	F.C.L.	354487594	0	36385.00	15560.00	83.69	52028.69	92902262	84854001	8048261	6813.31	7000.00	0	DOLAR	PR220118	OCHO MILLON CUARENTA Y OCHO MIL DOSCIENTOS SESENTA Y UN 	62	1	19	3	11	22	52	1	2	10	OTROS	0	17	0
51	2022-06-03 14:58:44.979	2022-06-03 14:58:44.979	5-Angel Rios 	2022-06-02	22005IC04011310B	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	375022304	0	53402.57	775.00	534.03	54711.60	48787259	0	48787259	6854.53	7000.00	0	DOLAR	14/2022	CUARENTA Y OCHO MILLONES SETECIENTOS OCHENTA Y SIETE MIL DOSCIENTOS CINCUENTA Y NUEVE 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
52	2022-06-03 15:23:23.332	2022-06-03 15:23:23.332	5-Angel Rios 	2022-06-01	22038IC04005516S	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	356843453	0	36385.00	15560.00	83.69	52028.69	96902262	84854001	12048261	6858.59	7000.00	0	DOLAR	PR220118	DOCE MILLONES CUARENTA Y OCHO MIL DOSCIENTOS SESENTA Y UN 	62	1	19	3	11	22	52	1	2	10	OTROS	0	17	0
53	2022-06-06 09:41:44.423	2022-06-06 09:41:44.423	5-Angel Rios 	2022-06-03	22005IC04011379Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	891914316	0	113475.60	15474.76	1134.76	130085.12	117438046	97764079	19673967	6856.39	7000.00	0	DOLAR	WK20211109001D	DIECINUEVEMILLONES SEISCIENTOS SETENTA Y TRES MIL NOVECIENTOS SESENTA Y SIETE 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP LIBERADO 	124868004	16	0
54	2022-06-06 10:14:35.246	2022-06-06 10:14:35.246	5-Angel Rios 	2022-06-02	22005IM04000258U	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	9199739	0	561.52	775.00	5.62	1342.14	2966759	0	2966759	6854.53	7000.00	0	DOLAR	15/2022	DOS MILLON NOVECIENTOS SESENTA Y SEIS MIL SETECIENTOS CINCUENTA Y NUEVE 	47	4	5	2	3	3	41	1	19	2	OTROS	0	16	0
55	2022-06-06 16:12:52.971	2022-06-06 16:12:52.971	5-Angel Rios 	2022-06-03	22038IC04005564V	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	598289688	0	74389.07	12700.00	171.09	87260.16	152224285	144205967	8018318	6856.39	7000.00	0	DOLAR	74389.07	OCHO MILLON DIECIOCHO MIL TRECIENTOS DIECIOCHO 	63	1	19	3	13	22	54	1	2	10	OTROS	0	17	0
56	2022-06-06 16:23:06.161	2022-06-06 16:23:06.161	5-Angel Rios 	2022-06-03	22038IC04005564V	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	594530521	0	74389.07	12700.00	171.09	87260.16	152224285	144205967	8018318	6813.31	7000.00	0	DOLAR	74389.07	OCHO MILLON DIECIOCHO MIL TRECIENTOS DIECIOCHO 	63	1	19	3	13	22	54	1	2	10	OTROS	0	17	0
57	2022-06-07 11:30:24.194	2022-06-07 11:30:24.194	5-Angel Rios 	2022-06-06	22005IC04011473L	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	355442541	0	45360.00	6191.60	453.60	52005.20	50702455	39351428	11351027	6834.75	7000.00	0	DOLAR	WK20211109001F	ONCE MILLONES TRECIENTOS CINCUENTA Y UN MIL VEINTE Y SIETE 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP LIBERADO 	49761956	16	0
58	2022-06-07 14:42:59.556	2022-06-07 14:42:59.556	5-Angel Rios 	2022-06-06	22005IC04011473L	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	354327549	0	45360.00	6191.60	453.60	52005.20	50702455	39351428	11351027	6813.31	7000.00	0	DOLAR	WK20211109001F	ONCE MILLONES TRECIENTOS CINCUENTA Y UN MIL VEINTE Y SIETE 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP LIBERADO 	49761956	16	0
59	2022-06-08 14:51:49.244	2022-06-08 14:51:49.244	5-Angel Rios 	2022-06-07	22005IC04011603G	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	42122087	0	5665.00	440.00	56.65	6161.65	7593701	0	7593701	6836.17	7000.00	0	DOLAR	247/22	SIETE MILLON QUINIENTOS NOVENTA Y TRES MIL SETECIENTOS UN 	47	4	5	2	10	3	55	1	2	2	OTROS	0	16	0
60	2022-06-08 14:52:45.957	2022-06-08 14:52:45.957	5-Angel Rios 	2022-06-07	22005IC04011603G	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	41981232	0	5665.00	440.00	56.65	6161.65	7428701	0	7428701	6813.31	7000.00	0	DOLAR	247/22	SIETE MILLON CUATROCIENTOS VEINTE Y OCHO MIL SETECIENTOS UN 	47	4	5	2	10	3	55	1	2	2	OTROS	0	16	0
61	2022-06-08 15:14:20.161	2022-06-08 15:14:20.161	5-Angel Rios 	2022-06-07	22005IC04011603G	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	42122087	0	5665.00	440.00	56.65	6161.65	7318701	0	7318701	6836.17	7000.00	0	DOLAR	247/22	SIETE MILLON TRECIENTOS DIECIOCHO MIL SETECIENTOS UN 	47	4	5	2	10	3	55	1	2	2	OTROS	0	16	0
62	2022-06-08 15:26:56.229	2022-06-08 15:26:56.229	5-Angel Rios 	2022-06-07	22005IC04011603G	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	41981232	0	5665.00	440.00	56.65	6161.65	7318701	0	7318701	6813.31	7000.00	0	DOLAR	247/22	SIETE MILLON TRECIENTOS DIECIOCHO MIL SETECIENTOS UN 	47	4	5	2	10	3	55	1	2	2	OTROS	0	16	0
64	2022-06-08 16:57:05.838	2022-06-08 16:57:05.838	5-Angel Rios 	2022-06-08	22029IC04000268@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	17884341	0	2100.00	500.00	21.00	2621.00	5151770	0	5151770	6823.48	7000.00	0	DOLAR	SIN DEFINIR 	CINCO MILLON CIENTO CINCUENTA Y UN MIL SETECIENTOS SETENTA 	65	6	29	3	1	39	58	1	2	2	OTROS	0	17	0
65	2022-06-08 17:06:15.564	2022-06-08 17:06:15.564	5-Angel Rios 	2022-06-08	22029IC04000268@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	17857686	0	2100.00	500.00	21.00	2621.00	5151770	0	5151770	6813.31	7000.00	0	DOLAR	SIN DEFINIR 	CINCO MILLON CIENTO CINCUENTA Y UN MIL SETECIENTOS SETENTA 	65	6	29	3	1	39	58	1	2	2	OTROS	0	17	0
63	2022-06-08 16:16:17.538	2022-06-08 16:16:17.538	5-Angel Rios 	2022-06-07	22005IC04011601E	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	46111539	0	6242.80	440.00	62.43	6745.23	7841217	0	7841217	6836.17	7000.00	0	DOLAR	K-017	SIETE MILLON OCHOCIENTOS CUARENTA Y UN MIL DOSCIENTOS DIECISIETE 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
66	2022-06-08 17:15:35.343	2022-06-08 17:15:35.343	5-Angel Rios 	2022-06-07	22005IC04011601E	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	45957343	0	6242.80	440.00	62.43	6745.23	7841217	355558	7485659	6813.31	7000.00	0	DOLAR	K-017	SIETE MILLON CUATROCIENTOS OCHENTA Y CINCO MIL SEISCIENTOS CINCUENTA Y NUEVE 	64	14	5	2	10	56	57	1	2	3	OTROS	0	17	0
67	2022-06-08 17:23:50.366	2022-06-08 17:23:50.366	5-Angel Rios 	2022-06-07	22005IC04011601E	IMPORTACIÓN	ANULADO	TOTAL DE MONTO ADELANTO EN CONCEPTO DE DESCUENTO ESPECIAL\nPUNTO NÚMERO 14.	-	L.C.L.	TERRESTRE	L.C.L.	46111539	0	6242.80	440.00	62.43	6745.23	7841217	355558	7485659	6836.17	7000.00	0	DOLAR	K-017	SIETE MILLON CUATROCIENTOS OCHENTA Y CINCO MIL SEISCIENTOS CINCUENTA Y NUEVE 	64	14	5	2	10	56	57	1	2	3	OTROS	0	17	0
68	2022-06-10 08:53:15.435	2022-06-10 08:53:15.435	5-Angel Rios 	2022-06-07	22005IC04011601E	IMPORTACIÓN	EMITIDO	TOTAL DE MONTO ADELANTO EN CONCEPTO DE DESCUENTO ESPECIAL\nPUNTO NÚMERO 14.	-	L.C.L.	TERRESTRE	L.C.L.	45957343	0	6242.80	440.00	62.43	6745.23	8467574	355558	8112016	6813.31	7000.00	0	DOLAR	K-017	OCHO MILLON CIENTO DOCE MIL DIECISEIS 	64	14	5	2	10	56	57	1	2	3	OTROS	0	17	0
69	2022-06-10 09:08:14.62	2022-06-10 09:08:14.62	5-Angel Rios 	2022-06-10	22029IC04000268@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	26734553	0	2265.00	1608.05	22.65	3895.70	5659246	0	5659246	6862.58	7000.00	0	DOLAR	-	CINCO MILLON SEISCIENTOS CINCUENTA Y NUEVE MIL DOSCIENTOS CUARENTA Y SEIS 	65	1	29	3	1	50	58	1	2	2	OTROS	0	17	0
70	2022-06-10 09:19:55.201	2022-06-10 09:19:55.201	5-Angel Rios 	2022-06-10	22029IC04000273@	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	123931332	0	15900.00	2000.00	159.00	18059.00	20272686	0	20272686	6862.58	7000.00	0	DOLAR	-	VEINTE MILLONES DOSCIENTOS SETENTA Y DOS MIL SEISCIENTOS OCHENTA Y SEIS 	66	1	29	3	1	50	58	1	2	10	OTROS	0	17	0
71	2022-06-10 09:20:44.413	2022-06-10 09:20:44.414	5-Angel Rios 	2022-06-10	22029IC04000273@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	123041565	0	15900.00	2000.00	159.00	18059.00	20272686	0	20272686	6813.31	7000.00	0	DOLAR	-	VEINTE MILLONES DOSCIENTOS SETENTA Y DOS MIL SEISCIENTOS OCHENTA Y SEIS 	66	1	29	3	1	50	58	1	2	10	OTROS	0	17	0
72	2022-06-10 09:31:23.18	2022-06-10 09:31:23.18	5-Angel Rios 	2022-06-10	22029IC04000273@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	123041565	0	15900.00	2000.00	159.00	18059.00	20272686	0	20272686	6813.31	7000.00	0	DOLAR	-	VEINTE MILLONES DOSCIENTOS SETENTA Y DOS MIL SEISCIENTOS OCHENTA Y SEIS 	66	1	29	3	1	50	58	1	2	10	OTROS	0	17	0
73	2022-06-10 09:34:13.398	2022-06-10 09:34:13.398	5-Angel Rios 	2022-06-10	22029IC04000273@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	AEREO	F.C.L.	123041565	0	15900.00	2000.00	159.00	18059.00	20872686	0	20872686	6813.31	7000.00	0	DOLAR	-	VEINTE MILLONES OCHOCIENTOS SETENTA Y DOS MIL SEISCIENTOS OCHENTA Y SEIS 	66	1	29	3	1	50	58	1	2	10	OTROS	0	17	0
21	2022-05-06 13:53:05.148	2022-05-06 13:53:05.148	5-Angel Rios 	2022-05-04	22002IC04008988C	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	87077200	0	11349.36	1248.00	114.64	12712.00	26979209	0	26979209	6850.00	6800.00	0	GUARANÍ	202203281111-1	VEINTE Y SEIS MILLONES NOVECIENTOS SETENTA Y NUEVE MIL DOSCIENTOS NUEVE 	53	4	2	3	7	3	32	4	2	5	OTROS	0	17	0
74	2022-06-14 11:46:56.723	2022-06-14 11:46:56.723	5-Angel Rios 	2022-05-04	22002IC04008988C	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	86610797	0	11349.36	1248.00	114.64	12712.00	29314336	0	29314336	6813.31	7000.00	0	DOLAR	202203281111-1	VEINTE Y NUEVE MILLONES TRECIENTOS CATORCE MIL TRECIENTOS TREINTA Y SEIS 	53	4	2	3	7	3	32	1	2	5	OTROS	0	17	0
75	2022-06-14 13:22:28.704	2022-06-14 13:22:28.704	5-Angel Rios 	2022-06-09	22005IC04011785R	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	121148463	94	16650.91	775.00	166.51	17592.42	18178700	0	18178700	6849.72	7000.00	0	DOLAR	599/2022	DIECIOCHO MILLONES CIENTO SETENTA Y OCHO MIL SETECIENTOS 	47	4	5	2	3	34	59	1	2	4	OTROS	0	16	0
76	2022-06-14 13:48:14.631	2022-06-14 13:48:14.631	5-Angel Rios 	2022-06-09	22005IC04011790N	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	286174315	0	40598.00	775.00	405.98	41778.98	37396589	0	37396589	6849.72	7000.00	0	DOLAR	018PY/2022	TREINTA Y SIETE MILLONES TRECIENTOS NOVENTA Y SEIS MIL QUINIENTOS OCHENTA Y NUEVE 	47	4	5	2	3	3	48	1	2	3	OTROS	0	16	0
77	2022-06-15 13:43:34.171	2022-06-15 13:43:34.171	5-Angel Rios 	2022-06-15	22038IC04000278@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351515845	0	43236.00	7828.00	11.79	51075.79	108577335	0	108577335	6882.24	7000.00	0	DOLAR	01	CIENTO OCHO MILLONES QUINIENTOS SETENTA Y SIETE MIL TRECIENTOS TREINTA Y CINCO 	67	15	19	3	1	20	60	1	2	2	OTROS	0	17	0
78	2022-06-15 14:04:18.271	2022-06-15 14:04:18.271	5-Angel Rios 	2022-06-15	22038IC04000278@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	347995191	0	43236.00	7828.00	11.79	51075.79	109877335	0	109877335	6813.31	7000.00	0	DOLAR	01	CIENTO NUEVE MILLONES OCHOCIENTOS SETENTA Y SIETE MIL TRECIENTOS TREINTA Y CINCO 	67	15	19	3	1	20	60	1	2	2	OTROS	0	17	0
79	2022-06-15 14:05:59.266	2022-06-15 14:05:59.266	5-Angel Rios 	2022-06-15	22038IC04000278@	PROFORMA	PROFORMA	CANJE DE DOCUMENTOS A DETERMINAR 	-	F.C.L.	FLUVIAL	F.C.L.	347995191	0	43236.00	7828.00	11.79	51075.79	109877335	0	109877335	6813.31	7000.00	0	DOLAR	01	CIENTO NUEVE MILLONES OCHOCIENTOS SETENTA Y SIETE MIL TRECIENTOS TREINTA Y CINCO 	67	15	19	3	1	20	60	1	2	2	OTROS	0	17	0
81	2022-06-17 09:34:19.989	2022-06-17 09:34:19.989	5-Angel Rios 	2022-06-14	22023EC01002423W	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	140267802	0	20391.20	0.00	0.00	20391.20	3169021	0	3169021	6878.84	7000.00	0	DOLAR	001-002-0000225	TRES MILLON CIENTO SESENTA Y NUEVE MIL VEINTE Y UN 	54	13	23	2	14	15	62	1	3	3	OTROS	0	16	0
80	2022-06-17 09:13:10.542	2022-06-17 09:13:10.542	5-Angel Rios 	2022-06-14	22023EC01002422V	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	240816701	0	35008.33	0.00	0.00	35008.33	3432977	0	3432977	6878.84	7000.00	0	DOLAR	001-002-0000223/224	TRES MILLON CUATROCIENTOS TREINTA Y DOS MIL NOVECIENTOS SETENTA Y SIETE 	54	13	23	2	14	15	46	1	3	3	OTROS	0	16	0
83	2022-06-17 10:06:23.316	2022-06-17 10:06:23.316	5-Angel Rios 	2022-06-14	22023EC01002423W	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	140337132	0	20391.20	0.00	0.00	20391.20	3169021	0	3169021	6882.24	7000.00	0	DOLAR	001-002-0000225	TRES MILLON CIENTO SESENTA Y NUEVE MIL VEINTE Y UN 	54	13	23	2	14	15	62	1	3	3	OTROS	0	16	0
84	2022-06-17 10:11:04.07	2022-06-17 10:11:04.07	5-Angel Rios 	2022-06-14	22023EC01002422V	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	240935729	0	35008.33	0.00	0.00	35008.33	3432977	0	3432977	6882.24	7000.00	0	DOLAR	001-002-0000223/224	TRES MILLON CUATROCIENTOS TREINTA Y DOS MIL NOVECIENTOS SETENTA Y SIETE 	54	13	23	2	14	15	46	1	3	3	OTROS	0	16	0
126	2022-07-12 10:07:29.978	2022-07-12 10:07:29.978	5-Angel Rios 	2022-07-08	22005IC04013794T	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	241480103	0	34171.20	640.00	341.71	35152.91	33027743	26515425	6512318	6869.42	7000.00	0	DOLAR	HSBR220620-1	SEIS MILLON QUINIENTOS DOCE MIL TRECIENTOS DIECIOCHO 	54	1	5	2	10	15	44	1	2	2	OTROS	0	16	0
127	2022-07-12 13:11:57.115	2022-07-12 13:11:57.115	5-Angel Rios 	2022-07-08	22005IC04013794T	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	241480103	0	34171.20	640.00	341.71	35152.91	33027743	26515425	6512318	6869.42	7000.00	0	DOLAR	HSBR220620-1	SEIS MILLON QUINIENTOS DOCE MIL TRECIENTOS DIECIOCHO 	54	1	5	2	10	15	44	1	2	2	OTROS	0	16	0
82	2022-06-17 10:03:36.547	2022-06-17 10:03:36.547	5-Angel Rios 	2022-06-15	22005IC04012075K	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	355775745	0	45057.60	6186.58	450.58	51694.76	50664265	0	50664265	6882.24	7000.00	0	DOLAR	WK20211109001E	CINCUENTA MILLONES SEISCIENTOS SESENTA Y CUATRO MIL DOSCIENTOS SESENTA Y CINCO 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP 	49808604	16	0
86	2022-06-17 11:17:56.286	2022-06-17 11:17:56.286	5-Angel Rios 	2022-06-14	22023EC01002423W	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	140267802	0	20391.20	0.00	0.00	20391.20	3169021	0	3169021	6878.84	7000.00	0	DOLAR	001-002-0000225	TRES MILLON CIENTO SESENTA Y NUEVE MIL VEINTE Y UN 	54	13	23	2	14	15	62	1	3	3	OTROS	0	16	0
85	2022-06-17 10:31:36.257	2022-06-17 10:31:36.257	5-Angel Rios 	2022-06-15	22005IC04012075K	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	355775745	0	45057.60	6186.58	450.58	51694.76	50664265	39378464	11285801	6882.24	7000.00	0	DOLAR	WK20211109001E	ONCE MILLONES DOSCIENTOS OCHENTA Y CINCO MIL OCHOCIENTOS UN 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP 	49808604	16	0
87	2022-06-17 11:20:01.689	2022-06-17 11:20:01.689	5-Angel Rios 	2022-06-14	22023EC01002422V	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	240816701	0	35008.33	0.00	0.00	35008.33	3432977	0	3432977	6878.84	7000.00	0	DOLAR	001-002-0000223/224	TRES MILLON CUATROCIENTOS TREINTA Y DOS MIL NOVECIENTOS SETENTA Y SIETE 	54	13	23	2	14	15	46	1	3	3	OTROS	0	16	0
88	2022-06-17 11:23:24.143	2022-06-17 11:23:24.143	5-Angel Rios 	2022-06-15	22005IC04012075K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	355775745	0	45057.60	6186.58	450.58	51694.76	50664265	39378464	11285801	6882.24	7000.00	0	DOLAR	WK20211109001E	ONCE MILLONES DOSCIENTOS OCHENTA Y CINCO MIL OCHOCIENTOS UN 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP 	49808604	16	0
89	2022-06-20 09:45:21.877	2022-06-20 09:45:21.89	5-Angel Rios 	2022-06-17	22005IC04012187Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	818500097	0	116527.64	1550.00	1165.28	119242.92	102719449	0	102719449	6864.14	7000.00	0	DOLAR	16/2022	CIENTO DOS MILLONES SETECIENTOS DIECINUEVEMIL CUATROCIENTOS CUARENTA Y NUEVE 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
90	2022-06-20 14:30:48.515	2022-06-20 14:30:48.515	5-Angel Rios 	2022-06-16	22024IC04003398T	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	1197006623	0	165714.40	8219.73	197.20	174131.33	380501923	369346414	11155509	6874.16	7000.00	0	DOLAR	A-1000326	ONCE MILLONES CIENTO CINCUENTA Y CINCO MIL QUINIENTOS NUEVE 	68	1	37	3	15	22	63	1	2	10	OTROS	0	17	0
91	2022-06-21 11:12:23.408	2022-06-21 11:12:23.408	5-Angel Rios 	2022-06-15	22005IC04012075K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	355775745	0	45057.60	6186.58	450.58	51694.76	50664265	39378464	11285801	6882.24	7000.00	0	DOLAR	WK20211109001E	ONCE MILLONES DOSCIENTOS OCHENTA Y CINCO MIL OCHOCIENTOS UN 	54	1	5	3	9	15	53	1	2	12	DERECHO ADUANERO MP 	49808604	16	0
93	2022-06-29 10:36:47.547	2022-06-29 10:36:47.548	5-Angel Rios 	2022-06-29	22005IC04012780N	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	419078698	0	53827.20	6664.49	538.27	61029.96	59603623	46278181	13325442	6866.77	7000.00	0	DOLAR	SR001496-2022	TRECE MILLONES TRECIENTOS VEINTE Y CINCO MIL CUATROCIENTOS CUARENTA Y DOS 	54	1	5	2	9	15	64	1	2	11	DERECHO ADUANERO MP 	75434166	16	0
94	2022-06-29 11:20:25.246	2022-06-29 11:20:25.246	5-Angel Rios 	2022-06-29	22005IC04012849T	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	570753436	0	72576.00	9906.94	725.76	83208.70	78399876	62889224	15510652	6859.30	7000.00	0	DOLAR	SR001496-2022	QUINCE MILLONES QUINIENTOS DIEZ MIL SEISCIENTOS CINCUENTA Y DOS 	54	1	5	2	9	15	64	1	2	11	DERECHO ADUANERO MP 	102735618	16	0
97	2022-06-29 13:22:45.138	2022-06-29 13:22:45.138	5-Angel Rios 	2022-06-29	22005IC04012849T	IMPORTACIÓN	EMITIDO	SALDO A FAVOR DEL IMPORTADOR EN SISTEMA SOFIA DE 68.449 Gs	-	F.C.L.	TERRESTRE	F.C.L.	570753436	0	72576.00	9906.94	725.76	83208.70	78399876	62889224	15510652	6859.30	7000.00	0	DOLAR	SR001496-2022	QUINCE MILLONES QUINIENTOS DIEZ MIL SEISCIENTOS CINCUENTA Y DOS 	54	1	5	2	9	15	64	1	2	11	DERECHO ADUANERO MP 	102735618	17	0
95	2022-06-29 11:43:15.908	2022-06-29 11:43:15.908	5-Angel Rios 	2022-06-29	22005IC04012780N	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	419078698	0	53827.20	6664.49	538.27	61029.96	58503623	46278181	12225442	6866.77	7000.00	0	DOLAR	SR001496-2022	DOCE MILLONES DOSCIENTOS VEINTE Y CINCO MIL CUATROCIENTOS CUARENTA Y DOS 	54	1	5	2	9	15	64	1	2	11	DERECHO ADUANERO MP 	75434166	16	0
98	2022-06-29 13:26:42.304	2022-06-29 13:26:42.304	5-Angel Rios 	2022-06-29	22005IC04012780N	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	419078698	0	53827.20	6664.49	538.27	61029.96	58503623	46278181	12225442	6866.77	7000.00	0	DOLAR	SR001497-2022	DOCE MILLONES DOSCIENTOS VEINTE Y CINCO MIL CUATROCIENTOS CUARENTA Y DOS 	54	1	5	2	9	15	64	1	2	11	DERECHO ADUANERO MP 	75434166	17	0
92	2022-06-29 09:19:10.05	2022-06-29 09:19:10.061	5-Angel Rios 	2022-06-29	22005IC04012892R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	231620658	0	32804.35	635.00	328.04	33767.39	31506247	25436887	6069360	6859.30	7000.00	0	DOLAR	HSBR2206071	SEIS MILLON SESENTA Y NUEVE MIL TRECIENTOS SESENTA 	54	1	5	2	10	15	44	1	2	2	OTROS	0	16	0
96	2022-06-29 13:19:53.308	2022-06-29 13:19:53.308	5-Angel Rios 	2022-06-29	22031IC04004017G	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	27158075	0	3610.00	295.00	50.00	3955.00	7580637	0	7580637	6866.77	7000.00	0	DOLAR	20220330008	SIETE MILLON QUINIENTOS OCHENTA MIL SEISCIENTOS TREINTA Y SIETE 	69	1	31	2	1	50	65	1	2	2	OTROS	0	17	0
100	2022-06-29 13:41:42.09	2022-06-29 13:41:42.09	5-Angel Rios 	2022-06-28	22031IC04004017G	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	27158075	0	3610.00	295.00	50.00	3955.00	7580637	1222382	6358255	6866.77	7000.00	0	DOLAR	20220330008	SEIS MILLON TRECIENTOS CINCUENTA Y OCHO MIL DOSCIENTOS CINCUENTA Y CINCO 	69	1	31	2	1	50	65	1	2	2	OTROS	0	17	0
99	2022-06-29 13:27:45.39	2022-06-29 13:27:45.39	5-Angel Rios 	2022-06-29	22005IC04012892R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	23275862	0	32804.35	635.00	328.04	33767.39	31506247	25436887	6069360	689.30	7000.00	0	DOLAR	HSBR220607-1	SEIS MILLON SESENTA Y NUEVE MIL TRECIENTOS SESENTA 	54	1	5	2	10	15	44	1	2	2	OTROS	0	17	0
102	2022-07-04 14:00:08.694	2022-07-04 14:00:08.697	5-Angel Rios 	2022-06-29	22005IC04012892R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	231620658	0	32804.35	635.00	328.04	33767.39	31506247	25436887	6069360	6859.30	7000.00	0	DOLAR	HSBR220607-1	SEIS MILLON SESENTA Y NUEVE MIL TRECIENTOS SESENTA 	54	1	5	2	10	15	44	1	2	2	OTROS	0	16	0
103	2022-07-04 14:40:00.916	2022-07-04 14:40:00.916	5-Angel Rios 	2022-06-30	22005IC04013105F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	707043498	0	101050.00	1100.00	1010.50	103160.50	88581238	77505197	11076041	6853.82	7000.00	0	DOLAR	585/2022	ONCE MILLONES SETENTA Y SEIS MIL CUARENTA Y UN 	54	1	5	2	16	15	66	1	2	4	OTROS	0	16	0
101	2022-06-30 14:13:37.517	2022-06-30 14:13:37.518	5-Angel Rios 	2022-06-28	22018EC01001795Y	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	478981695	0	69829.53	0.00	0.00	69829.53	3649949	0	3649949	6859.30	7000.00	0	DOLAR	0000206/207/208	TRES MILLON SEISCIENTOS CUARENTA Y NUEVE MIL NOVECIENTOS CUARENTA Y NUEVE 	1	13	18	2	14	15	46	1	3	3	OTROS	0	16	0
104	2022-07-04 15:14:55.179	2022-07-04 15:14:55.179	5-Angel Rios 	2022-06-29	22002IC04013323E	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	133763652	0	12370.00	7006.00	123.70	19499.70	21643699	0	21643699	6859.78	7000.00	0	DOLAR	XD 20220414	VEINTE Y UN MILLONES SEISCIENTOS CUARENTA Y TRES MIL SEISCIENTOS NOVENTA Y NUEVE 	70	1	2	3	17	15	67	1	2	3	OTROS	0	17	0
105	2022-07-04 15:15:45.247	2022-07-04 15:15:45.247	5-Angel Rios 	2022-06-29	22002IC04013323E	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	133763652	0	12370.00	7006.00	123.70	19499.70	21643699	14729604	6914095	6859.78	7000.00	0	DOLAR	XD 20220414	SEIS MILLON NOVECIENTOS CATORCE MIL NOVENTA Y CINCO 	70	1	2	3	17	15	67	1	2	3	OTROS	0	17	0
106	2022-07-04 15:21:06.783	2022-07-04 15:21:06.783	5-Angel Rios 	2022-06-28	22018EC01001795Y	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	478981695	0	69829.53	0.00	0.00	69829.53	3649949	0	3649949	6859.30	7000.00	0	DOLAR	0000226/227/228	TRES MILLON SEISCIENTOS CUARENTA Y NUEVE MIL NOVECIENTOS CUARENTA Y NUEVE 	1	13	18	2	14	15	46	1	3	3	OTROS	0	17	0
107	2022-07-04 15:23:16.159	2022-07-04 15:23:16.159	5-Angel Rios 	2022-06-28	22018EC01001795Y	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	478981695	0	69829.53	0.00	0.00	69829.53	3649949	0	3649949	6859.30	7000.00	0	DOLAR	0000226/227/228	TRES MILLON SEISCIENTOS CUARENTA Y NUEVE MIL NOVECIENTOS CUARENTA Y NUEVE 	71	13	18	2	18	15	46	1	3	3	OTROS	0	17	0
108	2022-07-05 13:59:05.365	2022-07-05 13:59:05.366	5-Angel Rios 	2022-07-01	22018EC01001851H	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	455218884	0	66477.05	0.00	0.00	66477.05	3600911	0	3600911	6847.76	7000.00	0	DOLAR	001-002-0000229	TRES MILLON SEISCIENTOS MIL NOVECIENTOS ONCE 	71	1	18	2	18	15	62	1	3	3	OTROS	0	16	0
109	2022-07-05 15:31:49.39	2022-07-05 15:31:49.39	5-Angel Rios 	2022-07-05	22005IC04013396R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	1031305679	0	146560.74	2329.70	1465.61	150356.05	127092489	0	127092489	6859.09	7000.00	0	DOLAR	03/2022	CIENTO VEINTE Y SIETE MILLONES NOVENTA Y DOS MIL CUATROCIENTOS OCHENTA Y NUEVE 	72	16	5	2	19	68	69	1	2	3	OTROS	0	16	0
110	2022-07-05 16:25:05.539	2022-07-05 16:25:05.539	5-Angel Rios 	2022-07-05	22005IC04013396R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	1031305679	0	146560.74	2329.70	1465.61	150356.05	127092489	112831820	14260669	6859.09	7000.00	0	DOLAR	03/2022	CATORCE MILLONES DOSCIENTOS SESENTA MIL SEISCIENTOS SESENTA Y NUEVE 	72	16	5	2	19	68	69	1	2	3	OTROS	0	16	0
112	2022-07-06 13:49:54.102	2022-07-06 13:49:54.102	5-Angel Rios 	2022-07-05	22005IC04013407K	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	78039296	0	10438.12	835.00	104.38	11377.50	13931647	0	13931647	6859.09	7000.00	0	DOLAR	0009.2022	TRECE MILLONES NOVECIENTOS TREINTA Y UN MIL SEISCIENTOS CUARENTA Y SIETE 	60	1	5	2	10	15	49	1	2	3	OTROS	0	16	0
114	2022-07-06 14:47:21.448	2022-07-06 14:47:21.448	5-Angel Rios 	2022-07-06	22005IC04013496S	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	505458209	115	70072.75	2750.00	700.73	73523.48	86799212	77455277	9343935	6864.05	7000.00	0	DOLAR	2022-011	NUEVE MILLON TRECIENTOS CUARENTA Y TRES MIL NOVECIENTOS TREINTA Y CINCO 	60	1	5	2	10	15	72	1	2	3	OTROS	0	16	0
113	2022-07-06 14:14:20.489	2022-07-06 14:14:20.489	5-Angel Rios 	2022-07-05	22005IC04013407K	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	78039296	0	10438.12	835.00	104.38	11377.50	13931647	8616655	5314992	6859.09	7000.00	0	DOLAR	0009.2022	CINCO MILLON TRECIENTOS CATORCE MIL NOVECIENTOS NOVENTA Y DOS 	60	1	5	2	10	15	49	1	2	3	OTROS	0	16	0
115	2022-07-06 14:48:41.304	2022-07-06 14:48:41.304	5-Angel Rios 	2022-07-05	22005IC04013407K	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77925635	0	10438.12	835.00	104.38	11377.50	13931647	8616655	5314992	6849.10	7000.00	0	DOLAR	0009.2022	CINCO MILLON TRECIENTOS CATORCE MIL NOVECIENTOS NOVENTA Y DOS 	60	1	5	3	10	15	49	1	2	3	OTROS	0	16	0
111	2022-07-06 13:26:52.241	2022-07-06 13:26:52.241	5-Angel Rios 	2022-07-04	22005IC04013270X	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	46772298	0	6236.60	530.00	62.37	6828.97	16136806	0	16136806	6849.10	7000.00	0	DOLAR	000934305	DIECISEIS MILLONES CIENTO TREINTA Y SEIS MIL OCHOCIENTOS SEIS 	73	1	5	2	10	15	70	1	2	2	OTROS	0	16	0
117	2022-07-06 14:50:47.549	2022-07-06 14:50:47.549	5-Angel Rios 	2022-07-04	22005IC04013270X	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	46772298	0	6236.60	530.00	62.37	6828.97	16136806	0	16136806	6849.10	7000.00	0	DOLAR	000934305	DIECISEIS MILLONES CIENTO TREINTA Y SEIS MIL OCHOCIENTOS SEIS 	73	1	5	3	10	15	70	1	2	2	OTROS	0	16	0
116	2022-07-06 14:49:32.772	2022-07-06 14:49:32.772	5-Angel Rios 	2022-07-05	22005IC04013407K	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77925635	0	10438.12	835.00	104.38	11377.50	13931647	8616655	5314992	6849.10	7000.00	0	DOLAR	0009.2022	CINCO MILLON TRECIENTOS CATORCE MIL NOVECIENTOS NOVENTA Y DOS 	60	1	5	2	10	15	49	1	2	3	OTROS	0	16	0
118	2022-07-06 14:52:54.434	2022-07-06 14:52:54.434	5-Angel Rios 	2022-07-05	22005IC04013407K	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	78039296	0	10438.12	835.00	104.38	11377.50	13931647	8616655	5314992	6859.09	7000.00	0	DOLAR	0009.2022	CINCO MILLON TRECIENTOS CATORCE MIL NOVECIENTOS NOVENTA Y DOS 	60	1	5	2	10	15	49	1	2	3	OTROS	0	16	0
119	2022-07-06 15:54:56.92	2022-07-06 15:54:56.92	5-Angel Rios 	2022-07-05	22002IM04006471U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	26469571	130	2100.00	1608.05	21.00	3729.05	5388613	2368314	3020299	6859.09	7000.00	0	DOLAR	WV-1887	TRES MILLON VEINTE MIL DOSCIENTOS NOVENTA Y NUEVE 	74	1	2	3	12	50	73	1	19	3	OTROS	0	16	0
120	2022-07-06 16:02:26.056	2022-07-06 16:02:26.056	5-Angel Rios 	2022-07-05	22002IM04006471U	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	26469571	130	2100.00	1608.05	21.00	3729.05	5388613	2368314	3020299	6859.09	7000.00	0	DOLAR	WV-1887	TRES MILLON VEINTE MIL DOSCIENTOS NOVENTA Y NUEVE 	74	2	2	3	12	50	73	1	19	3	OTROS	0	16	0
121	2022-07-08 12:53:01.391	2022-07-08 12:53:01.391	5-Angel Rios 	2022-07-06	22032IC04004226J	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	577428459	0	70232.03	13700.00	191.55	84123.58	115186020	101198423	13987597	6864.05	7000.00	0	DOLAR	BG2203199	TRECE MILLONES NOVECIENTOS OCHENTA Y SIETE MIL QUINIENTOS NOVENTA Y SIETE 	50	6	32	3	20	12	74	1	2	10	OTROS	0	17	0
122	2022-07-08 12:59:37.406	2022-07-08 12:59:37.406	5-Angel Rios 	2022-07-06	22032IC04004226J	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	577428459	0	70232.03	13700.00	191.55	84123.58	115186020	101198423	13987597	6864.05	7000.00	0	DOLAR	BG2203199	TRECE MILLONES NOVECIENTOS OCHENTA Y SIETE MIL QUINIENTOS NOVENTA Y SIETE 	50	6	32	3	20	12	74	1	2	10	OTROS	0	17	0
123	2022-07-08 15:27:22.307	2022-07-08 15:27:22.307	5-Angel Rios 	2022-07-07	22005IC04013676S	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	721259627	0	103529.16	533.00	1035.29	105097.45	89124914	0	89124914	6862.77	7000.00	0	DOLAR	17/2022	OCHENTA Y NUEVE MILLONES CIENTO VEINTE Y CUATRO MIL NOVECIENTOS CATORCE 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
124	2022-07-08 15:54:40.958	2022-07-08 15:54:40.958	5-Angel Rios 	2022-07-07	22005IM04000310J	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	15452556	0	1701.63	533.00	17.02	2251.65	4920585	0	4920585	6862.77	7000.00	0	DOLAR	18/2022	CUATRO MILLON NOVECIENTOS VEINTE MIL QUINIENTOS OCHENTA Y CINCO 	47	4	5	2	3	3	41	1	19	2	OTROS	0	16	0
125	2022-07-08 16:06:51.668	2022-07-08 16:06:51.668	5-Angel Rios 	2022-07-07	22005IC04013669U	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	59109038	0	8000.00	533.00	80.00	8613.00	10013534	0	10013534	6862.77	7000.00	0	DOLAR	001/2022	DIEZ MILLONES TRECE MIL QUINIENTOS TREINTA Y CUATRO 	47	4	5	2	3	3	75	1	2	3	OTROS	0	16	0
128	2022-07-13 10:22:24.491	2022-07-13 10:22:24.492	5-Angel Rios 	2022-07-12	22005IC04013997B	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	54819659	0	7556.08	350.00	75.56	7981.64	9379939	0	9379939	6868.22	7000.00	0	DOLAR	0004/22	NUEVE MILLON TRECIENTOS SETENTA Y NUEVE MIL NOVECIENTOS TREINTA Y NUEVE 	75	4	5	2	10	3	76	1	2	3	OTROS	0	16	0
129	2022-07-13 14:51:19.907	2022-07-13 14:51:19.908	5-Angel Rios 	2022-07-13	22005IC04000914@	PROFORMA	PROFORMA	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	60070415	0	7185.00	1500.00	71.85	8756.85	10603927	0	10603927	6859.82	7000.00	0	DOLAR	-	DIEZ MILLONES SEISCIENTOS TRES MIL NOVECIENTOS VEINTE Y SIETE 	76	6	5	2	1	77	58	1	2	3	OTROS	0	16	0
130	2022-07-14 12:40:45.85	2022-07-14 12:40:45.85	5-Angel Rios 	2022-07-08	22009IC04000909R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	73536798	0	10103.91	500.00	101.04	10704.95	12714850	8122274	4592576	6869.42	7000.00	0	DOLAR	KV239489	CUATRO MILLON QUINIENTOS NOVENTA Y DOS MIL QUINIENTOS SETENTA Y SEIS 	77	4	9	3	5	8	78	1	2	3	OTROS	0	16	0
132	2022-07-14 14:14:30.948	2022-07-14 14:14:30.948	5-Angel Rios 	2022-07-13	22005IC04014217K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	637055294	0	90364.00	1600.00	903.64	92867.64	79200741	69838819	9361922	6859.82	7000.00	0	DOLAR	19/2022	NUEVE MILLON TRECIENTOS SESENTA Y UN MIL NOVECIENTOS VEINTE Y DOS 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
133	2022-07-18 16:12:41.368	2022-07-18 16:12:41.37	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	114900930	0	114900930	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO CATORCE MILLONES NOVECIENTOS MIL NOVECIENTOS TREINTA 	78	2	17	3	21	20	60	1	2	2	OTROS	0	17	0
134	2022-07-18 16:16:33.146	2022-07-18 16:16:33.146	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	122202651	0	122202651	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y DOS MILLONES DOSCIENTOS DOS MIL SEISCIENTOS CINCUENTA Y UN 	78	2	17	3	21	20	60	1	2	2	OTROS	0	17	0
135	2022-07-18 16:18:53.848	2022-07-18 16:18:53.848	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	122202651	0	122202651	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y DOS MILLONES DOSCIENTOS DOS MIL SEISCIENTOS CINCUENTA Y UN 	78	2	17	3	21	20	60	1	2	2	OTROS	0	17	0
137	2022-07-19 08:59:41.703	2022-07-19 08:59:41.705	5-Angel Rios 	2022-07-15	22005IC04014373N	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	128023180	0	17219.13	1250.00	172.19	18641.32	18802091	14089623	4712468	6867.71	7000.00	0	DOLAR	602/2022	CUATRO MILLON SETECIENTOS DOCE MIL CUATROCIENTOS SESENTA Y OCHO 	54	1	5	2	18	15	66	1	2	3	OTROS	0	18	0
136	2022-07-18 16:28:09.127	2022-07-18 16:28:09.127	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	122702651	0	122702651	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y DOS MILLONES SETECIENTOS DOS MIL SEISCIENTOS CINCUENTA Y UN 	78	2	17	3	21	20	60	1	2	2	OTROS	0	17	0
138	2022-07-19 09:23:00.005	2022-07-19 09:23:00.005	5-Angel Rios 	2022-07-18	22005IC04014423J	IMPORTACIÓN	ANULADO	SALDO EN SISTEMA SOFÍA DE 7.229 Gs A FAVOR DEL IMPORTADOR 	-	F.C.L.	TERRESTRE	F.C.L.	634897824	0	81648.00	9991.88	816.48	92456.36	85638131	69914705	15723426	6867.00	7000.00	0	DOLAR	SR001495-2022	QUINCE MILLONES SETECIENTOS VEINTE Y TRES MIL CUATROCIENTOS VEINTE Y SEIS 	54	1	5	2	18	15	64	1	2	11	DERECHO ADUANERO MP 	114281608	18	0
140	2022-07-19 09:55:10.195	2022-07-19 09:55:10.195	5-Angel Rios 	2022-07-18	22005IC04014423J	IMPORTACIÓN	EMITIDO	SALDO EN SISTEMA SOFÍA DE 7.229 Gs A FAVOR DEL IMPORTADOR 	3 x 40"	F.C.L.	TERRESTRE	F.C.L.	634897824	0	81648.00	9991.88	816.48	92456.36	85638131	69914705	15723426	6867.00	7000.00	0	DOLAR	SR001495-2022	QUINCE MILLONES SETECIENTOS VEINTE Y TRES MIL CUATROCIENTOS VEINTE Y SEIS 	54	1	5	3	18	15	64	1	2	11	DERECHO ADUANERO MP 	114281608	18	0
139	2022-07-19 09:53:56.141	2022-07-19 09:53:56.141	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	122035395	0	122035395	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y DOS MILLONES TREINTA Y CINCO MIL TRECIENTOS NOVENTA Y CINCO 	78	2	17	3	21	20	60	1	2	2	OTROS	0	18	0
141	2022-07-19 10:25:26.385	2022-07-19 10:25:26.385	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	122447751	0	122447751	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y DOS MILLONES CUATROCIENTOS CUARENTA Y SIETE MIL SETECIENTOS CINCUENTA Y UN 	78	2	17	3	21	20	60	1	2	2	OTROS	0	18	0
142	2022-07-19 12:18:06.576	2022-07-19 12:18:06.576	5-Angel Rios 	2022-07-13	22017IC04003694U	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	351179567	50	43186.00	7828.00	129.70	51143.70	121347751	0	121347751	6859.82	7000.00	0	DOLAR	ASU-21001-1	CIENTO VEINTE Y UN MILLONES TRECIENTOS CUARENTA Y SIETE MIL SETECIENTOS CINCUENTA Y UN 	78	2	17	3	21	20	60	1	2	2	OTROS	0	18	0
143	2022-07-19 13:32:00.869	2022-07-19 13:32:00.869	5-Angel Rios 	2022-07-19	22002IRE4002998K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	2057600	0	100.00	249.20	0.50	349.70	4501349	0	4501349	5883.90	7000.00	0	DOLAR	CW-20220707	CUATRO MILLON QUINIENTOS UN MIL TRECIENTOS CUARENTA Y NUEVE 	79	2	2	3	1	20	79	1	27	1	OTROS	0	17	0
131	2022-07-14 13:42:42.676	2022-07-14 13:42:42.676	5-Angel Rios 	2022-07-08	22009IC04000909R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	73536798	0	10103.91	500.00	101.04	10704.95	12929650	8122274	4807376	6869.42	7000.00	0	DOLAR	KV239489	CUATRO MILLON OCHOCIENTOS SIETE MIL TRECIENTOS SETENTA Y SEIS 	77	4	9	2	5	8	78	1	2	3	OTROS	0	16	0
144	2022-07-19 15:51:40.344	2022-07-19 15:51:40.344	5-Angel Rios 	2022-07-08	22009IC04000909R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	73536798	0	10103.91	500.00	101.04	10704.95	12930150	8122274	4807876	6869.42	7000.00	0	DOLAR	KV239489	CUATRO MILLON OCHOCIENTOS SIETE MIL OCHOCIENTOS SETENTA Y SEIS 	77	4	9	2	5	8	78	1	2	3	OTROS	0	17	0
147	2022-07-29 12:45:11.114	2022-07-29 12:45:11.114	5-Angel Rios 	2022-07-25	22005IC04014982T	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	435139859	0	60732.47	1800.00	607.32	63139.79	56143084	47725041	8418043	6891.69	7000.00	0	DOLAR	021PY/2022	OCHO MILLON CUATROCIENTOS DIECIOCHO MIL CUARENTA Y TRES 	47	4	5	2	22	3	48	1	2	3	OTROS	0	18	0
145	2022-07-28 12:46:31.57	2022-07-28 12:46:31.57	5-Angel Rios 	2022-07-21	22005IC04014759V	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	515338147	0	72172.02	2221.65	721.72	75115.39	67794564	0	67794564	6860.62	7000.00	0	DOLAR	04/2022	SESENTA Y SIETE MILLONES SETECIENTOS NOVENTA Y CUATRO MIL QUINIENTOS SESENTA Y CUATRO 	80	1	5	2	19	68	69	1	2	3	OTROS	0	18	0
148	2022-07-29 12:55:18.613	2022-07-29 12:55:18.613	5-Angel Rios 	2022-07-21	22005IC04014759V	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	515338147	0	72172.02	2221.65	721.72	75115.39	67794564	56508358	11286206	6860.62	7000.00	0	DOLAR	04/2022	ONCE MILLONES DOSCIENTOS OCHENTA Y SEIS MIL DOSCIENTOS SEIS 	80	1	5	2	19	68	69	1	2	3	OTROS	0	18	0
146	2022-07-29 11:26:31.398	2022-07-29 11:26:31.398	5-Angel Rios 	2022-07-26	22005IC0401568P	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	904205283	0	128008.05	1800.00	1280.08	131088.13	111066101	0	111066101	6897.69	7000.00	0	DOLAR	20/2022	CIENTO ONCE MILLONES SESENTA Y SEIS MIL CIENTO UN 	47	4	5	2	22	3	41	1	2	2	OTROS	0	18	0
150	2022-07-29 15:24:08.855	2022-07-29 15:24:08.855	5-Angel Rios 	2022-07-25	22002IC04015355L	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	177198651	0	24951.15	506.21	254.57	25711.93	27152356	19501902	7650454	6891.69	7000.00	0	DOLAR	PL57319/44/21	SIETE MILLON SEISCIENTOS CINCUENTA MIL CUATROCIENTOS CINCUENTA Y CUATRO 	46	4	2	3	2	34	80	1	2	3	OTROS	0	17	0
151	2022-07-29 16:11:05.747	2022-07-29 16:11:05.747	5-Angel Rios 	2022-07-25	22002IC04015355L	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	177198651	0	24951.15	506.21	254.57	25711.93	26855356	19501902	7353454	6891.69	7000.00	0	DOLAR	PL57319/44/21	SIETE MILLON TRECIENTOS CINCUENTA Y TRES MIL CUATROCIENTOS CINCUENTA Y CUATRO 	46	4	2	3	2	34	80	1	2	3	OTROS	0	17	0
152	2022-07-29 16:11:59.93	2022-07-29 16:11:59.93	5-Angel Rios 	2022-07-21	22005IC04014759V	IMPORTACIÓN	EMITIDO	NINGUNA	2 CARRETAS	F.C.L.	TERRESTRE	F.C.L.	515338147	0	72172.02	2221.65	721.72	75115.39	67794564	56508358	11286206	6860.62	7000.00	0	DOLAR	04/2022	ONCE MILLONES DOSCIENTOS OCHENTA Y SEIS MIL DOSCIENTOS SEIS 	80	1	5	2	19	68	69	1	2	3	OTROS	0	17	0
149	2022-07-29 13:16:00.091	2022-07-29 13:16:00.091	5-Angel Rios 	2022-07-26	22005IC0401568P	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	904205283	0	128008.05	1800.00	1280.08	131088.13	111066101	99637817	11428284	6897.69	7000.00	0	DOLAR	20/2022	ONCE MILLONES CUATROCIENTOS VEINTE Y OCHO MIL DOSCIENTOS OCHENTA Y CUATRO 	47	4	5	2	22	3	41	1	2	2	OTROS	0	18	0
153	2022-07-29 16:17:02.345	2022-07-29 16:17:02.345	5-Angel Rios 	2022-07-26	22005IC0401568P	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	904205283	0	128008.05	1800.00	1280.08	131088.13	111066101	99637817	11428284	6897.69	7000.00	0	DOLAR	20/2022	ONCE MILLONES CUATROCIENTOS VEINTE Y OCHO MIL DOSCIENTOS OCHENTA Y CUATRO 	47	4	5	2	22	3	41	1	2	2	OTROS	0	17	0
154	2022-08-01 15:49:57.505	2022-08-01 15:49:57.505	5-Angel Rios 	2022-07-28	22005IC04015268R	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	342538190	0	47506.00	1600.00	475.06	49581.06	45182886	37583306	7599580	6908.65	7000.00	0	DOLAR	2022-01	SIETE MILLON QUINIENTOS NOVENTA Y NUEVE MIL QUINIENTOS OCHENTA 	47	4	5	2	3	3	81	1	2	3	OTROS	0	18	0
155	2022-08-01 16:35:46.857	2022-08-01 16:35:46.857	5-Angel Rios 	2022-07-28	22005IC04015268R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	342538190	0	47506.00	1600.00	475.06	49581.06	45182886	37583306	7599580	6908.65	7000.00	0	DOLAR	2022-01	SIETE MILLON QUINIENTOS NOVENTA Y NUEVE MIL QUINIENTOS OCHENTA 	47	4	5	2	3	3	81	1	2	3	OTROS	0	18	0
156	2022-08-02 13:47:32.042	2022-08-02 13:47:32.045	5-Angel Rios 	2022-07-29	22018EC01002272F	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	443732296	0	64324.69	0.00	0.00	64324.69	3617958	0	3617958	6898.32	7000.00	0	DOLAR	001-002-0000230	TRES MILLON SEISCIENTOS DIECISIETE MIL NOVECIENTOS CINCUENTA Y OCHO 	81	13	18	2	18	15	62	1	3	3	OTROS	0	16	0
157	2022-08-03 09:17:52.204	2022-08-03 09:17:52.205	5-Angel Rios 	2022-08-01	22005IC04015515M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	783713194	0	111315.00	1600.00	1113.15	114028.15	96206487	85900793	10305694	6872.98	7000.00	0	DOLAR	21/2022	DIEZ MILLONES TRECIENTOS CINCO MIL SEISCIENTOS NOVENTA Y CUATRO 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
158	2022-08-05 14:59:20.227	2022-08-05 14:59:20.227	5-Angel Rios 	2022-08-04	22030IC04009498A	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	441003830	0	60806.78	2800.00	608.07	64214.85	133964660	0	133964660	6867.63	7000.00	0	DOLAR	2634/35/36/47/48	CIENTO TREINTA Y TRES MILLONES NOVECIENTOS SESENTA Y CUATRO MIL SEISCIENTOS SESENTA 	82	1	30	2	23	22	82	1	2	3	OTROS	0	16	0
159	2022-08-05 15:02:48.076	2022-08-05 15:02:48.076	5-Angel Rios 	2022-08-04	22030IC04009498A	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	441003830	0	60806.78	2800.00	608.07	64214.85	133964660	126814724	7149936	6867.63	7000.00	0	DOLAR	2634/35/36/47/48	SIETE MILLON CIENTO CUARENTA Y NUEVE MIL NOVECIENTOS TREINTA Y SEIS 	82	1	30	2	23	22	82	1	2	3	OTROS	0	16	0
160	2022-08-06 14:51:20.788	2022-08-06 14:51:20.825	5-Angel Rios 	2022-08-04	22030IC04009498A	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	441003830	0	60806.78	2800.00	608.07	64214.85	133964660	126814724	7149936	6867.63	7000.00	0	DOLAR	2634/35/36/47/48	SIETE MILLON CIENTO CUARENTA Y NUEVE MIL NOVECIENTOS TREINTA Y SEIS 	82	1	30	2	23	22	82	1	2	3	OTROS	0	16	0
162	2022-08-10 14:12:40.27	2022-08-10 14:12:40.27	5-Angel Rios 	2022-08-09	22002IC0416590N	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	41646145	0	5717.85	287.90	57.18	6062.93	7887087	4582518	3304569	6868.98	7000.00	0	DOLAR	QN10161	TRES MILLON TRECIENTOS CUATRO MIL QUINIENTOS SESENTA Y NUEVE 	53	4	2	3	24	34	80	1	2	2	OTROS	0	17	0
164	2022-08-22 14:31:32.081	2022-08-22 14:31:32.081	5-Angel Rios 	2022-08-19	22005IC04017008L	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	23909815	0	2646.84	800.00	26.47	3473.31	5185616	2833696	2351920	6883.87	7000.00	0	DOLAR	23/2022	DOS MILLON TRECIENTOS CINCUENTA Y UN MIL NOVECIENTOS VEINTE 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
165	2022-08-22 14:43:17.162	2022-08-22 14:43:17.162	5-Angel Rios 	2022-08-19	22005IC04017002F	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	534721537	0	76116.30	800.00	761.16	77677.46	68637814	59134810	9503004	6883.87	7000.00	0	DOLAR	22/2022	NUEVE MILLON QUINIENTOS TRES MIL CUATRO 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
163	2022-08-22 14:18:38.571	2022-08-22 14:18:38.572	5-Angel Rios 	2022-08-19	22005IC04016964V	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	856900283	0	120913.90	2356.40	1209.14	124479.44	106872468	93967364	12905104	6883.87	7000.00	0	DOLAR	06/2022 07/2022	DOCE MILLONES NOVECIENTOS CINCO MIL CIENTO CUATRO 	72	17	5	2	19	68	69	1	2	3	OTROS	0	16	0
167	2022-08-22 15:34:31.343	2022-08-22 15:34:31.343	5-Angel Rios 	2022-08-19	22005IC04016964V	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	856900283	0	120913.90	2356.40	1209.14	124479.44	106872468	93967364	12905104	6883.87	7000.00	0	DOLAR	06/2022 07/2022	DOCE MILLONES NOVECIENTOS CINCO MIL CIENTO CUATRO 	72	17	5	2	19	68	69	1	2	3	OTROS	0	17	0
166	2022-08-22 14:57:53.272	2022-08-22 14:57:53.272	5-Angel Rios 	2022-08-17	22002IC04017190K	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	72454642	0	10334.00	99.31	103.34	10536.65	9779986	7967415	1812571	6876.44	7000.00	0	DOLAR	QQ14471/QP19351	UN MILLON OCHOCIENTOS DOCE MIL QUINIENTOS SETENTA Y UN 	83	18	2	3	2	34	80	1	2	2	OTROS	0	17	0
168	2022-08-22 16:00:29.931	2022-08-22 16:00:29.931	5-Angel Rios 	2022-08-17	22002IC04017190K	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	72454642	0	10334.00	99.31	103.34	10536.65	12092986	7967415	4125571	6876.44	7000.00	0	DOLAR	QQ14471/QP19351	CUATRO MILLON CIENTO VEINTE Y CINCO MIL QUINIENTOS SETENTA Y UN 	83	18	2	3	2	34	80	1	2	2	OTROS	0	17	0
169	2022-08-24 09:56:26.324	2022-08-24 09:56:26.324	5-Angel Rios 	2022-08-22	22005IC04017095R	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	33890579	0	4200.00	680.00	42.00	4922.00	7399557	3780583	3618974	6885.53	7000.00	0	DOLAR	010/2022	TRES MILLON SEISCIENTOS DIECIOCHO MIL NOVECIENTOS SETENTA Y CUATRO 	60	1	5	2	10	15	84	1	2	3	OTROS	0	16	0
170	2022-08-25 13:23:34.003	2022-08-25 13:23:34.003	5-Angel Rios 	2022-08-23	22018EC01002629L	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	343928187	0	49902.74	0.00	0.00	49902.74	3494800	0	3494800	6891.97	7000.00	0	DOLAR	001-002-0000231	TRES MILLON CUATROCIENTOS NOVENTA Y CUATRO MIL OCHOCIENTOS 	54	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
171	2022-08-26 11:40:55.545	2022-08-26 11:40:55.545	5-Angel Rios 	2022-08-24	22005IC04017324M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	630259853	0	89033.00	1600.00	890.33	91523.33	78805841	69094945	9710896	6886.33	7000.00	0	DOLAR	24/2022	NUEVE MILLON SETECIENTOS DIEZ MIL OCHOCIENTOS NOVENTA Y SEIS 	47	4	5	2	3	3	41	1	2	2	OTROS	0	16	0
173	2022-08-30 14:59:12.294	2022-08-30 14:59:12.294	5-Angel Rios 	2022-08-29	22005IC04017683U	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	191428139	0	26949.60	605.00	269.50	27824.10	26769133	21034097	5735036	6879.94	7000.00	0	DOLAR	HSBR220818-2	CINCO MILLON SETECIENTOS TREINTA Y CINCO MIL TREINTA Y SEIS 	54	1	5	2	10	15	44	1	2	2	OTROS	0	16	0
172	2022-08-30 14:15:14.787	2022-08-30 14:15:14.791	5-Angel Rios 	2022-08-29	22005IC04017699E	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	676785271	0	95614.66	1800.00	956.15	98370.81	81207582	0	81207582	6879.94	7000.00	0	DOLAR	25/2022	OCHENTA Y UN MILLONES DOSCIENTOS SIETE MIL QUINIENTOS OCHENTA Y DOS 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
174	2022-08-31 09:29:48.243	2022-08-31 09:29:48.243	5-Angel Rios 	2022-08-29	22005IC04017699E	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	676785271	0	95614.66	1800.00	956.15	98370.81	81207582	74190408	7017174	6879.94	7000.00	0	DOLAR	25/2022	SIETE MILLON DIECISIETE MIL CIENTO SETENTA Y CUATRO 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
161	2022-08-09 09:09:43.031	2022-08-09 09:09:43.031	5-Angel Rios 	2022-08-04	22002IC04016233H	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	170028784	0	20800.00	3750.00	208.00	24758.00	49949868	39752713	10197155	6867.63	7000.00	0	DOLAR	220706007	DIEZ MILLONES CIENTO NOVENTA Y SIETE MIL CIENTO CINCUENTA Y CINCO 	53	4	2	3	17	34	83	1	2	13	OTROS	0	16	0
176	2022-09-06 13:39:52.865	2022-09-06 13:39:52.865	5-Angel Rios 	2022-09-01	22005IC04017962U	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	757305758	0	107118.10	1500.00	1071.18	109689.28	93071716	83009014	10062702	6904.10	7000.00	0	DOLAR	26/2022	DIEZ MILLONES SESENTA Y DOS MIL SETECIENTOS DOS 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
177	2022-09-06 13:54:16.03	2022-09-06 13:54:16.03	5-Angel Rios 	2022-09-01	22005IM04000461Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	5354958	0	470.91	300.00	4.71	775.62	2010317	624023	1386294	6904.10	7000.00	0	DOLAR	27/2022	UN MILLON TRECIENTOS OCHENTA Y SEIS MIL DOSCIENTOS NOVENTA Y CUATRO 	47	4	5	2	22	3	41	1	19	2	OTROS	0	16	0
178	2022-09-06 14:11:26.287	2022-09-06 14:11:26.287	5-Angel Rios 	2022-09-01	22005IC04018041J	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	60700847	0	8200.00	510.00	82.00	8792.00	10369717	6716843	3652874	6904.10	7000.00	0	DOLAR	F007/2022	TRES MILLON SEISCIENTOS CINCUENTA Y DOS MIL OCHOCIENTOS SETENTA Y CUATRO 	60	4	5	2	10	15	86	1	2	2	OTROS	0	16	0
179	2022-09-06 15:06:25.629	2022-09-06 15:06:25.629	5-Angel Rios 	2022-09-02	22002IC04018438Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	117882310	0	14100.00	2800.00	141.00	17041.00	36237221	29705564	6531657	6917.57	7000.00	0	DOLAR	220818007	SEIS MILLON QUINIENTOS TREINTA Y UN MIL SEISCIENTOS CINCUENTA Y SIETE 	53	4	2	3	27	34	83	1	2	13	OTROS	0	17	0
180	2022-09-06 15:24:20.679	2022-09-06 15:24:20.679	5-Angel Rios 	2022-08-31	22002IRE4003771A	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	1020381	0	148.01	0.00	0.00	148.01	1906366	0	1906366	6894.00	7000.00	0	DOLAR	QL305211	UN MILLON NOVECIENTOS SEIS MIL TRECIENTOS SESENTA Y SEIS 	50	4	2	3	2	34	80	1	27	2	OTROS	0	17	0
181	2022-09-07 16:24:15.485	2022-09-07 16:24:15.485	5-Angel Rios 	2022-09-02	22005IC04018181Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	46203488	0	5919.95	700.00	59.20	6679.15	7454092	5406084	2048008	6917.57	7000.00	0	DOLAR	0257.22	DOS MILLON CUARENTA Y OCHO MIL OCHO 	85	4	5	2	4	8	87	1	2	2	OTROS	0	16	0
182	2022-09-07 16:34:34.807	2022-09-07 16:34:34.807	5-Angel Rios 	2022-09-02	22005IC04018180N	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	93538344	101	12594.90	700.00	125.95	13420.85	16124279	12958906	3165373	6917.57	7000.00	0	DOLAR	WDST01531-22	TRES MILLON CIENTO SESENTA Y CINCO MIL TRECIENTOS SETENTA Y TRES 	85	4	5	2	4	8	88	1	2	3	OTROS	0	16	0
183	2022-09-08 15:55:38.07	2022-09-08 15:55:38.088	5-Angel Rios 	2022-09-07	22005IC04018451Y	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	241427860	0	31430.88	3214.31	314.31	34959.50	35832292	0	35832292	6905.93	7000.00	0	DOLAR	WKRY2228	TREINTA Y CINCO MILLONES OCHOCIENTOS TREINTA Y DOS MIL DOSCIENTOS NOVENTA Y DOS 	54	1	5	2	18	15	53	1	2	12	OTROS	0	16	0
185	2022-09-12 11:14:14.85	2022-09-12 11:14:14.85	5-Angel Rios 	2022-09-09	22005IC04018593V	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	169602609	100	22663.00	1510.00	226.63	24399.63	23204049	18643764	4560285	6922.66	7000.00	0	DOLAR	20220508	CUATRO MILLON QUINIENTOS SESENTA MIL DOSCIENTOS OCHENTA Y CINCO 	75	4	5	2	10	34	89	1	2	3	OTROS	0	16	0
186	2022-09-12 15:24:02.731	2022-09-12 15:24:02.735	5-Angel Rios 	2022-09-08	22005IC04018505Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	71361710	0	9858.63	370.00	98.59	10327.22	11220235	7884421	3335814	6910.06	7000.00	0	DOLAR	01 PU	TRES MILLON TRECIENTOS TREINTA Y CINCO MIL OCHOCIENTOS CATORCE 	47	4	5	2	10	3	90	1	2	3	OTROS	0	16	0
187	2022-09-12 15:34:34.894	2022-09-12 15:34:34.894	5-Angel Rios 	2022-09-09	22005IC04018596B	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	80983423	0	10691.40	900.00	106.91	11698.31	12759785	9022198	3737587	6922.66	7000.00	0	DOLAR	026PY/2022	TRES MILLON SETECIENTOS TREINTA Y SIETE MIL QUINIENTOS OCHENTA Y SIETE 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
188	2022-09-12 15:45:41.429	2022-09-12 15:45:41.429	5-Angel Rios 	2022-09-09	22005IC04018600K	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	636823801	0	90139.80	950.00	901.40	91991.20	78944553	69813830	9130723	6922.66	7000.00	0	DOLAR	28/2022	NUEVE MILLON CIENTO TREINTA MIL SETECIENTOS VEINTE Y TRES 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
189	2022-09-13 08:32:09.963	2022-09-13 08:32:09.963	5-Angel Rios 	2022-09-05	22018EC1002796Z	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	363308087	0	52503.90	0.00	0.00	52503.90	3526258	0	3526258	6919.64	7000.00	0	DOLAR	001-002-0000302	TRES MILLON QUINIENTOS VEINTE Y SEIS MIL DOSCIENTOS CINCUENTA Y OCHO 	54	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
190	2022-09-14 11:48:19.638	2022-09-14 11:48:19.639	5-Angel Rios 	2022-09-13	22005IC04018820Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	47524743	0	6260.52	530.00	62.61	6853.13	16204521	8390664	7813857	6934.75	7000.00	0	DOLAR	38652	SIETE MILLON OCHOCIENTOS TRECE MIL OCHOCIENTOS CINCUENTA Y SIETE 	73	1	5	2	10	15	70	1	2	2	OTROS	0	16	0
175	2022-09-06 13:21:06.567	2022-09-06 13:21:06.568	5-Angel Rios 	2022-08-31	22018EC01002739N	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	395260215	0	57329.87	0.00	0.00	57329.87	5944695	0	5944695	6894.49	7000.00	0	DOLAR	001-002-000301	CINCO MILLON NOVECIENTOS CUARENTA Y CUATRO MIL SEISCIENTOS NOVENTA Y CINCO 	54	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
193	2022-09-20 14:22:37.415	2022-09-20 14:22:37.416	5-Angel Rios 	2022-09-16	22005IM04000489D	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	5828074	0	727.26	100.00	7.27	834.53	2480380	808297	1672083	6983.66	7000.00	0	DOLAR	31/2022	UN MILLON SEISCIENTOS SETENTA Y DOS MIL OCHENTA Y TRES 	47	4	5	2	22	3	41	1	19	2	OTROS	0	16	0
192	2022-09-20 14:08:52.238	2022-09-20 14:08:52.249	5-Angel Rios 	2022-09-16	22005IC04019195U	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	591762176	0	82213.12	1700.00	822.13	84735.25	73946614	64912057	9034557	6983.66	7000.00	0	DOLAR	30/2022	NUEVE MILLON TREINTA Y CUATRO MIL QUINIENTOS CINCUENTA Y SIETE 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
194	2022-09-20 14:23:43.541	2022-09-20 14:23:43.541	5-Angel Rios 	2022-09-16	22005IC04019195U	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	591762176	0	82213.12	1700.00	822.13	84735.25	73946614	64912057	9034557	6983.66	7000.00	0	DOLAR	30/2022	NUEVE MILLON TREINTA Y CUATRO MIL QUINIENTOS CINCUENTA Y SIETE 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
195	2022-09-26 08:36:47.405	2022-09-26 08:36:47.405	5-Angel Rios 	2022-09-22	22005IC04019675A	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	488202249	0	62464.32	6624.64	624.64	69713.60	66745436	53856144	12889292	7002.97	7000.00	0	DOLAR	WKRY2227	DOCE MILLONES OCHOCIENTOS OCHENTA Y NUEVE MIL DOSCIENTOS NOVENTA Y DOS 	54	1	5	2	18	15	53	1	2	12	OTROS	0	16	0
196	2022-09-26 08:42:10.883	2022-09-26 08:42:10.883	5-Angel Rios 	2022-09-22	22005IC04019675A	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	488202249	0	62464.32	6624.64	624.64	69713.60	66745436	53856144	12889292	7002.97	7000.00	0	DOLAR	WKRY2227	DOCE MILLONES OCHOCIENTOS OCHENTA Y NUEVE MIL DOSCIENTOS NOVENTA Y DOS 	54	1	5	2	18	15	53	1	2	12	DERECHO ADUANERO MP	68348315	16	0
197	2022-09-27 12:44:52.902	2022-09-27 12:44:52.903	5-Angel Rios 	2022-09-26	22005IM04000503N	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	11592158	0	1399.52	253.49	0.82	1653.83	5371990	1307693	4064297	7009.28	7000.00	0	DOLAR	JMA0335E22	CUATRO MILLON SESENTA Y CUATRO MIL DOSCIENTOS NOVENTA Y SIETE 	47	4	5	2	28	3	43	1	19	5	OTROS	0	16	0
198	2022-09-29 11:16:42.207	2022-09-29 11:16:42.207	5-Angel Rios 	2022-09-27	22002IC04020237G	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	64637527	0	8406.63	1127.95	49.23	9583.81	24246178	0	24246178	6744.45	7000.00	0	DOLAR	2633/2022	VEINTE Y CUATRO MILLONES DOSCIENTOS CUARENTA Y SEIS MIL CIENTO SETENTA Y OCHO 	86	2	2	3	29	20	91	1	2	2	OTROS	0	17	0
200	2022-10-05 10:37:19.215	2022-10-05 10:37:19.215	5-Angel Rios 	2022-09-30	22005IC04020283K	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	32515753	0	4142.04	400.00	41.42	4583.46	7923146	5733867	2189279	7094.15	7000.00	0	DOLAR	33/2022	DOS MILLON CIENTO OCHENTA Y NUEVE MIL DOSCIENTOS SETENTA Y NUEVE 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
199	2022-10-05 09:45:24.507	2022-10-05 09:45:24.508	5-Angel Rios 	2022-09-30	22005IC04020280H	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	739183685	0	101778.45	1400.00	1017.78	104196.23	91184859	81025191	10159668	7094.15	7000.00	0	DOLAR	33/2022	DIEZ MILLONES CIENTO CINCUENTA Y NUEVE MIL SEISCIENTOS SESENTA Y OCHO 	47	1	5	2	22	3	41	1	2	2	OTROS	0	16	0
201	2022-10-05 10:38:19.656	2022-10-05 10:38:19.656	5-Angel Rios 	2022-09-30	22005IC04020280H	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	739183685	0	101778.45	1400.00	1017.78	104196.23	91184859	81025191	10159668	7094.15	7000.00	0	DOLAR	32/2022	DIEZ MILLONES CIENTO CINCUENTA Y NUEVE MIL SEISCIENTOS SESENTA Y OCHO 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
202	2022-10-05 11:43:00.627	2022-10-05 11:43:04.781	5-Angel Rios 	2022-10-05	22032IC04006239P	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	889414655	0	102737.81	22407.94	227.22	125372.97	199977417	190238152	9739265	7094.15	7000.00	0	DOLAR	SMT20220322	NUEVE MILLON SETECIENTOS TREINTA Y NUEVE MIL DOSCIENTOS SESENTA Y CINCO 	63	1	32	3	30	22	92	1	2	10	OTROS	0	16	0
203	2022-10-06 11:13:24.672	2022-10-06 11:13:24.672	5-Angel Rios 	2022-09-30	22032IC04006239P	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	889414655	0	102737.81	22407.94	227.22	125372.97	199977417	190238152	9739265	7094.15	7000.00	0	DOLAR	SMT20220322	NUEVE MILLON SETECIENTOS TREINTA Y NUEVE MIL DOSCIENTOS SESENTA Y CINCO 	63	1	32	3	30	22	92	1	2	10	OTROS	0	16	0
204	2022-10-06 16:51:42.961	2022-10-06 16:51:42.961	5-Angel Rios 	2022-10-05	22005IC04020555M	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	30323181	0	3878.00	370.00	38.78	4286.78	9636890	0	9636890	7073.65	7000.00	0	DOLAR	0013.2022	NUEVE MILLON SEISCIENTOS TREINTA Y SEIS MIL OCHOCIENTOS NOVENTA 	47	4	5	2	10	15	71	1	2	3	OTROS	0	16	0
205	2022-10-07 14:11:42.942	2022-10-07 14:11:42.943	5-Angel Rios 	2022-10-05	22005IC04020555M	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	30323181	0	3878.00	370.00	38.78	4286.78	9636890	0	9636890	7073.65	7000.00	0	DOLAR	0013.2022	NUEVE MILLON SEISCIENTOS TREINTA Y SEIS MIL OCHOCIENTOS NOVENTA 	47	4	5	2	10	15	71	1	2	3	OTROS	0	16	0
206	2022-10-07 14:16:33.971	2022-10-07 14:16:33.971	5-Angel Rios 	2022-09-30	22018EC01003111V	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	485169254	0	69218.13	0.00	0.00	69218.13	3947329	0	3947329	7009.28	7000.00	0	DOLAR	001-002-0000303	TRES MILLON NOVECIENTOS CUARENTA Y SIETE MIL TRECIENTOS VEINTE Y NUEVE 	81	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
207	2022-10-10 11:05:38.727	2022-10-10 11:05:38.728	5-Angel Rios 	2022-10-06	22030IC04012492L	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	534207248	0	72008.76	2800.00	720.09	75528.85	167778513	157672854	10105659	7072.89	7000.00	0	DOLAR	2753/2754/2760/2761/2783	DIEZ MILLONES CIENTO CINCO MIL SEISCIENTOS CINCUENTA Y NUEVE 	82	1	30	2	23	22	82	1	2	10	OTROS	0	16	0
208	2022-10-10 11:59:50.487	2022-10-10 11:59:50.487	5-Angel Rios 	2022-10-06	22005IC04020815L	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	620663921	0	85398.53	1500.00	853.99	87752.52	77284694	68044905	9239789	7072.89	7000.00	0	DOLAR	34/2022	NUEVE MILLON DOSCIENTOS TREINTA Y NUEVE MIL SETECIENTOS OCHENTA Y NUEVE 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
209	2022-10-10 12:32:39.311	2022-10-10 12:32:39.311	5-Angel Rios 	2022-10-06	22005IM04000530N	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	14343326	0	1710.82	300.00	17.11	2027.93	4483235	2192101	2291134	7072.89	7000.00	0	DOLAR	35/2022	DOS MILLON DOSCIENTOS NOVENTA Y UN MIL CIENTO TREINTA Y CUATRO 	47	4	5	2	22	3	41	1	19	2	OTROS	0	16	0
210	2022-10-10 13:07:37.325	2022-10-10 13:07:37.325	5-Angel Rios 	2022-10-07	22005IT02001944F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	574901385	0	76110.86	4049.11	761.11	80921.08	8632068	0	8632068	7104.47	7000.00	0	DOLAR	BTS STANA002	OCHO MILLON SEISCIENTOS TREINTA Y DOS MIL SESENTA Y OCHO 	54	1	5	2	18	15	93	1	46	11	OTROS	0	16	0
211	2022-10-10 13:54:53.479	2022-10-10 13:54:53.479	5-Angel Rios 	2022-10-04	22018EC01003167J	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	508664341	0	71729.85	0.00	0.00	71729.85	3766097	0	3766097	7091.39	7000.00	0	DOLAR	001-002-0000304	TRES MILLON SETECIENTOS SESENTA Y SEIS MIL NOVENTA Y SIETE 	81	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
184	2022-09-08 16:01:52.906	2022-09-08 16:01:52.906	5-Angel Rios 	2022-09-07	22005IC04018451Y	IMPORTACIÓN	ANULADO	SALDO A FAVOR  EN SISTEMA  DE 25.844 GS	-	F.C.L.	TERRESTRE	F.C.L.	241427860	0	31430.88	3214.31	314.31	34959.50	35832292	26530087	9302205	6905.93	7000.00	0	DOLAR	WKRY2228	NUEVE MILLON TRECIENTOS DOS MIL DOSCIENTOS CINCO 	54	1	5	2	18	15	53	1	2	12	DERECHO ADUANERO MP 	33799900	16	0
212	2022-10-10 15:58:52.699	2022-10-10 15:58:52.699	5-Angel Rios 	2022-09-07	22005IC04018451Y	IMPORTACIÓN	EMITIDO	SALDO A FAVOR  EN SISTEMA  DE 25.844 GS	-	F.C.L.	TERRESTRE	F.C.L.	241427860	0	31430.88	3214.31	314.31	34959.50	35829197	26530087	9299110	6905.93	7000.00	0	DOLAR	WKRY2228	NUEVE MILLON DOSCIENTOS NOVENTA Y NUEVE MIL CIENTO DIEZ 	54	1	5	2	18	15	53	1	2	12	DERECHO ADUANERO MP 	33799900	16	0
214	2022-10-11 11:27:20.774	2022-10-11 11:27:20.774	5-Angel Rios 	2022-10-11	22030ic04000355@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	74287172	0	9250.00	1088.85	92.50	10431.35	10965310	0	10965310	7121.53	7000.00	0	DOLAR	-	DIEZ MILLONES NOVECIENTOS SESENTA Y CINCO MIL TRECIENTOS DIEZ 	87	1	30	3	1	94	95	1	2	10	OTROS	0	17	0
215	2022-10-11 11:33:19.935	2022-10-11 11:33:19.936	5-Angel Rios 	2022-10-11	22030ic04000355@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	74287172	0	9250.00	1088.85	92.50	10431.35	12055310	0	12055310	7121.53	7000.00	0	DOLAR	-	DOCE MILLONES CINCUENTA Y CINCO MIL TRECIENTOS DIEZ 	87	1	30	3	1	94	95	1	2	10	OTROS	0	17	0
216	2022-10-11 12:08:38.008	2022-10-11 12:08:38.008	5-Angel Rios 	2022-10-11	22030IC04000355@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	74169402	0	9250.00	1088.85	92.50	10431.35	18848669	0	18848669	7110.24	7000.00	0	DOLAR	-	DIECIOCHO MILLONES OCHOCIENTOS CUARENTA Y OCHO MIL SEISCIENTOS SESENTA Y NUEVE 	87	1	30	3	1	94	95	1	2	10	OTROS	0	17	0
191	2022-09-16 14:22:04.749	2022-09-16 14:22:04.75	5-Angel Rios 	2022-09-15	22038IC04007766E	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	652131415	0	83626.16	9070.00	836.26	93532.42	178691964	170394597	8297367	6972.25	7000.00	0	DOLAR	2022CX-U29	OCHO MILLON DOSCIENTOS NOVENTA Y SIETE MIL TRECIENTOS SESENTA Y SIETE 	63	1	19	3	20	22	54	1	2	10	OTROS	0	16	0
218	2022-10-14 11:10:07.738	2022-10-14 11:10:07.738	5-Angel Rios 	2022-10-10	22018EC01003244F	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	MARITIMO	F.C.L.	316871694	0	44494.89	0.00	0.00	44494.89	3394639	0	3394639	7121.53	7000.00	0	DOLAR	001-002-0000305	TRES MILLON TRECIENTOS NOVENTA Y CUATRO MIL SEISCIENTOS TREINTA Y NUEVE 	81	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
219	2022-10-14 15:44:00.567	2022-10-14 15:44:00.567	5-Angel Rios 	2022-10-13	22005IC04021313F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	371272711	249	50036.13	1675.00	24.90	51736.03	58432729	49250435	9182294	7141.92	7000.00	0	DOLAR	JME0992E22	NUEVE MILLON CIENTO OCHENTA Y DOS MIL DOSCIENTOS NOVENTA Y CUATRO 	47	4	5	2	10	3	43	1	2	3	OTROS	0	16	0
220	2022-10-14 15:55:12.887	2022-10-14 15:55:12.887	5-Angel Rios 	2022-10-07	22002IM04009915D	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	18352693	0	2384.50	174.91	23.85	2583.26	6647111	2553486	4093625	7104.47	7000.00	0	DOLAR	QS26971	CUATRO MILLON NOVENTA Y TRES MIL SEISCIENTOS VEINTE Y CINCO 	88	4	2	3	24	34	80	1	19	2	OTROS	0	17	0
221	2022-10-17 08:43:23.26	2022-10-17 08:43:23.261	5-Angel Rios 	2022-10-13	22030IC04012801P	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	596601001	0	79508.00	3680.10	347.00	83535.10	195920149	184342296	11577853	7141.92	7000.00	0	DOLAR	1000340	ONCE MILLONES QUINIENTOS SETENTA Y SIETE MIL OCHOCIENTOS CINCUENTA Y TRES 	89	1	30	2	31	22	63	1	2	10	OTROS	0	16	0
222	2022-10-17 09:35:26.649	2022-10-17 09:35:26.649	5-Angel Rios 	2022-10-13	22030IC04012801P	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	596601001	0	79508.00	3680.10	347.00	83535.10	195794614	184342296	11452318	7141.92	7000.00	0	DOLAR	1000340	ONCE MILLONES CUATROCIENTOS CINCUENTA Y DOS MIL TRECIENTOS DIECIOCHO 	89	1	30	2	31	22	63	1	2	10	OTROS	0	16	0
223	2022-10-17 10:38:31.916	2022-10-17 10:38:31.916	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35505280	27233220	8272060	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON DOSCIENTOS SETENTA Y DOS MIL SESENTA 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
224	2022-10-17 15:49:58.821	2022-10-17 15:49:58.821	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
225	2022-10-17 15:57:46.748	2022-10-17 15:57:46.748	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
229	2022-10-18 10:46:07.783	2022-10-18 10:46:07.783	5-Angel Rios 	2022-10-14	22005IT02001996M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	775700605	0	96526.08	10855.51	965.26	108346.85	14933027	0	14933027	7159.42	7000.00	0	DOLAR	SR001697-2022	CATORCE MILLONES NOVECIENTOS TREINTA Y TRES MIL VEINTE Y SIETE 	54	1	5	3	18	15	64	1	46	11	OTROS	0	16	0
217	2022-10-14 10:58:46.186	2022-10-14 10:58:46.187	5-Angel Rios 	2022-10-10	22005IC04020978V	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	248797772	0	32600.00	2010.00	326.00	34936.00	38818272	27252848	11565424	7121.53	7000.00	0	DOLAR	EXP_F008/2022	ONCE MILLONES QUINIENTOS SESENTA Y CINCO MIL CUATROCIENTOS VEINTE Y CUATRO 	47	4	5	2	10	15	86	1	2	2	OTROS	0	16	0
230	2022-10-18 10:53:37.846	2022-10-18 10:53:37.846	5-Angel Rios 	2022-10-10	22005IC04020978V	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	238029798	0	32600.00	2010.00	326.00	34936.00	33818272	27252848	6565424	6813.31	7000.00	0	DOLAR	EXP_F008/2022	SEIS MILLON QUINIENTOS SESENTA Y CINCO MIL CUATROCIENTOS VEINTE Y CUATRO 	47	4	5	2	10	15	86	1	2	2	OTROS	0	16	0
231	2022-10-18 13:04:42.372	2022-10-18 13:04:42.372	5-Angel Rios 	2022-10-10	22005IC04020978V	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	248797772	0	32600.00	2010.00	326.00	34936.00	33818272	27252848	6565424	7121.53	7000.00	0	DOLAR	EXP_F008/2022	SEIS MILLON QUINIENTOS SESENTA Y CINCO MIL CUATROCIENTOS VEINTE Y CUATRO 	47	4	5	2	10	15	86	1	2	2	OTROS	0	16	0
227	2022-10-18 09:56:10.066	2022-10-18 09:56:10.067	5-Angel Rios 	2022-10-18	22005IC04021310C	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	311066326	250	41899.20	1385.00	20.87	43305.07	49017716	40054464	8963252	7141.92	7000.00	0	DOLAR	JME1039E22	OCHO MILLON NOVECIENTOS SESENTA Y TRES MIL DOSCIENTOS CINCUENTA Y DOS 	47	4	5	2	10	3	43	1	2	3	OTROS	0	16	0
232	2022-10-18 13:20:54.606	2022-10-18 13:20:54.606	5-Angel Rios 	2022-10-13	22005IC04021310C	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	311066826	250	41899.20	1385.00	20.87	43305.07	49017716	40054464	8963252	7141.92	7000.00	0	DOLAR	JME1039E22	OCHO MILLON NOVECIENTOS SESENTA Y TRES MIL DOSCIENTOS CINCUENTA Y DOS 	47	4	5	2	10	3	43	1	2	3	OTROS	0	16	0
226	2022-10-17 15:59:21.38	2022-10-17 15:59:21.38	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
213	2022-10-10 16:03:17.814	2022-10-10 16:03:17.814	5-Angel Rios 	2022-08-31	22018EC01002739N	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	395260215	0	57329.87	0.00	0.00	57329.87	5944695	0	5944695	6894.49	7000.00	0	DOLAR	001-002-000301	CINCO MILLON NOVECIENTOS CUARENTA Y CUATRO MIL SEISCIENTOS NOVENTA Y CINCO 	54	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
228	2022-10-18 10:24:52.24	2022-10-18 10:24:52.24	5-Angel Rios 	2022-10-14	22005IC04021442X	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	409103148	0	54794.00	1800.00	547.94	57141.94	52408275	44878478	7529797	7159.42	7000.00	0	DOLAR	029PY/2022	SIETE MILLON QUINIENTOS VEINTE Y NUEVE MIL SETECIENTOS NOVENTA Y SIETE 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
233	2022-10-18 13:29:30.253	2022-10-18 13:29:30.253	5-Angel Rios 	2022-10-14	22005IC04021442X	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	409103148	0	54794.00	1800.00	547.94	57141.94	52518275	44878478	7639797	7159.42	7000.00	0	DOLAR	029PY/2022	SIETE MILLON SEISCIENTOS TREINTA Y NUEVE MIL SETECIENTOS NOVENTA Y SIETE 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
235	2022-10-19 13:49:21.185	2022-10-19 13:49:21.186	5-Angel Rios 	2022-10-18	22005IC04021655Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	232599192	0	30286.40	1840.00	302.86	32429.26	31727373	25544061	6183312	7172.51	7000.00	0	DOLAR	0011.2022	SEIS MILLON CIENTO OCHENTA Y TRES MIL TRECIENTOS DOCE 	60	1	5	2	10	15	71	1	2	3	OTROS	0	16	0
234	2022-10-19 13:11:57.128	2022-10-19 13:11:57.129	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	4103298	0	4103298	7145.87	7000.00	0	DOLAR	HSBR221003-1	CUATRO MILLON CIENTO TRES MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	OTROS	0	16	0
236	2022-10-19 13:58:40.652	2022-10-19 13:58:40.652	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	GARANTIZADO\n011 - INDI\t\t\t\t       15.006\n031 - SERVICIO VALORACION\t\t\t     533.891\n415 - I.V.A.\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
237	2022-10-19 13:59:24.198	2022-10-19 13:59:24.198	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	GARANTIZADO\n011 - INDI\t\t\t\t       15.006\n031 - SERVICIO VALORACION\t\t\t     533.891\n415 - I.V.A.\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
238	2022-10-19 13:59:55.629	2022-10-19 13:59:55.629	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       15.006\n031 - SERVICIO VALORACION\t\t\t     533.891\n415 - I.V.A.\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	101808839	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	6813.31	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
239	2022-10-19 14:00:31.854	2022-10-19 14:00:31.854	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       \t15.006\n031 - SERVICIO VALORACION\t\t\t     533.891\n415 - I.V.A.\t\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
240	2022-10-19 14:00:58.775	2022-10-19 14:00:58.775	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       \t15.006\n031 - SERVICIO VALORACION\t\t\t        533.891\n415 - I.V.A.\t\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	101808839	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	6813.31	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
241	2022-10-19 14:01:21.896	2022-10-19 14:01:21.896	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       \t15.006\n031 - SERVICIO VALORACION\t\t\t  533.891\n415 - I.V.A.\t\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	101808839	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	6813.31	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
242	2022-10-19 14:01:48.094	2022-10-19 14:01:48.094	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       \t15.006\n031 - SERVICIO VALORACION\t\t\t533.891\n415 - I.V.A.\t\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3730298	0	3730298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON SETECIENTOS TREINTA MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
243	2022-10-19 14:11:00.256	2022-10-19 14:11:00.256	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	011 - INDI\t\t\t\t       \t15.006\n031 - SERVICIO VALORACION\t\t\t533.891\n415 - I.V.A.\t\t\t\t\t10.754.144	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3487298	0	3487298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON CUATROCIENTOS OCHENTA Y SIETE MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	SEGURO A PAGARSE	373	16	0
244	2022-10-19 15:09:04.459	2022-10-19 15:09:04.459	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	ANULADO	DESPACHO GARANTIZADO\t\t\t11.303.041 Gs\nPOLIZA N° 1511022015 - CAPITAL ASEGUR\t\t12.000.000 Gs\nVIGENCIA HASTA 26-11-2023	-	L.C.L.	TERRESTRE	L.C.L.	106778163	0	14364.00	435.00	143.64	14942.64	3860298	373000	3487298	7145.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON CUATROCIENTOS OCHENTA Y SIETE MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	OTRO	0	16	0
245	2022-10-19 15:14:22.385	2022-10-19 15:14:22.385	5-Angel Rios 	2022-10-17	22005IT02002020Y	IMPORTACIÓN	EMITIDO	***EL TOTAL ADELANTO CORRESPONDE POLIZA A PAGAR/CREDITO*** \nDESPACHO GARANTIZADO\t\t\t11.303.041 Gs\nPOLIZA N° 1511022015 - CPT ASG 12.000.000 Gs /// VIGENCIA HASTA 26-11-2023\n	-	L.C.L.	TERRESTRE	L.C.L.	106733335	0	14364.00	435.00	143.64	14942.64	3860298	373000	3487298	7142.87	7000.00	0	DOLAR	HSBR221003-1	TRES MILLON CUATROCIENTOS OCHENTA Y SIETE MIL DOSCIENTOS NOVENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	OTRO	0	16	0
246	2022-10-20 08:38:59.879	2022-10-20 08:38:59.879	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35908588	27233220	8675368	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON SEISCIENTOS SETENTA Y CINCO MIL TRECIENTOS SESENTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
247	2022-10-20 08:43:04.225	2022-10-20 08:43:04.225	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 1.000.000 Gs\nTOTAL A PAGAR: 7.675.368 Gs\nSON GUARANÍES: SIETE MILLONES, SEISCIENTOS SETENTA Y CINCO MIL, TRESCIENTOS SESENTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35908588	27233220	8675368	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON SEISCIENTOS SETENTA Y CINCO MIL TRECIENTOS SESENTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
264	2022-11-01 08:22:53.387	2022-11-01 08:22:53.395	5-Angel Rios 	2022-10-26	22005IC04022341H	IMPORTACIÓN	ANULADO	NINGUNA	FFAU1520519	F.C.L.	TERRESTRE	F.C.L.	254127575	0	31464.00	3286.74	314.64	35065.38	37194845	28283323	8911522	7247.25	7000.00	0	DOLAR	WKRY2237	OCHO MILLON NOVECIENTOS ONCE MIL QUINIENTOS VEINTE Y DOS 	54	1	5	2	18	15	53	1	2	12	OTROS	0	16	0
267	2022-11-01 10:56:57.899	2022-11-01 10:56:57.9	5-Angel Rios 	2022-10-26	22005IC04022341H	IMPORTACIÓN	EMITIDO	NINGUNA	FFAU1520519	F.C.L.	TERRESTRE	F.C.L.	254127575	0	31464.00	3286.74	314.64	35065.38	37194845	28283323	8911522	7247.25	7000.00	0	DOLAR	WKRY2237	OCHO MILLON NOVECIENTOS ONCE MIL QUINIENTOS VEINTE Y DOS 	54	1	5	2	18	15	53	1	2	12	OTROS	0	16	0
248	2022-10-20 08:44:13.774	2022-10-20 08:44:13.774	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 1.000.000 Gs\nTOTAL A PAGAR: 7.675.368 Gs\nSON GUARANÍES: SIETE MILLONES, SEISCIENTOS SETENTA Y CINCO MIL, TRESCIENTOS SESENTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	36511458	27233220	9278238	7124.61	7000.00	0	DOLAR	IN2022071201	NUEVE MILLON DOSCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
249	2022-10-20 08:46:10.624	2022-10-20 08:46:10.624	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 1.000.000 Gs\nTOTAL A PAGAR: 7.675.368 Gs\nSON GUARANÍES: SIETE MILLONES, SEISCIENTOS SETENTA Y CINCO MIL, TRESCIENTOS SESENTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	36511458	27233220	9278238	7124.61	7000.00	0	DOLAR	IN2022071201	NUEVE MILLON DOSCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
250	2022-10-20 08:50:02.982	2022-10-20 08:50:02.982	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 1.100.000 Gs\nTOTAL A PAGAR: 8.178.238Gs\nSON GUARANÍES: SIETE MILLONES, SEISCIENTOS SETENTA Y CINCO MIL, TRESCIENTOS SESENTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	36511458	27233220	9278238	7124.61	7000.00	0	DOLAR	IN2022071201	NUEVE MILLON DOSCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
251	2022-10-20 08:56:39.045	2022-10-20 08:56:39.045	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 500.000 Gs\nTOTAL A PAGAR: 8.778.238 Gs\nSON GUARANÍES: SIETE MILLONES, SETECIENTOS SETENTA Y OCHO MIL, DOSCIENTOS TREINTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	36511458	27233220	9278238	7124.61	7000.00	0	DOLAR	IN2022071201	NUEVE MILLON DOSCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
252	2022-10-20 11:51:17.482	2022-10-20 11:51:17.482	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 500.000 Gs\nTOTAL A PAGAR: 7.978.238 Gs\nSON GUARANÍES: OCHO MILLONES, NOVECIENTOS SETENTA Y OCHO MIL, DOSCIENTOS TREINTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249570	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.64	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
253	2022-10-20 12:11:13.015	2022-10-20 12:11:13.015	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	ANULADO	TOTAL DESCUENTO CONCEBIDO: 500.000 Gs\nTOTAL A PAGAR: 7.978.238 Gs\nSON GUARANÍES: OCHO MILLONES, NOVECIENTOS SETENTA Y OCHO MIL, DOSCIENTOS TREINTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
279	2022-11-11 13:06:07.027	2022-11-11 13:06:07.027	5-Angel Rios 	2022-11-10	22005IC04023521X	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	254049759	0	33446.02	1780.00	334.46	35560.48	34859435	27895498	6963937	7144.16	7000.00	0	DOLAR	MP-036-022	SEIS MILLON NOVECIENTOS SESENTA Y TRES MIL NOVECIENTOS TREINTA Y SIETE 	55	4	5	2	4	8	104	1	2	2	OTROS	0	16	0
254	2022-10-20 12:12:44.245	2022-10-20 12:12:44.245	5-Angel Rios 	2022-10-12	22002IC04021343F	IMPORTACIÓN	EMITIDO	TOTAL DESCUENTO CONCEBIDO: 500.000 Gs\nTOTAL A PAGAR: 7.978.238 Gs\nSON GUARANÍES: SIETE MILLONES, NOVECIENTOS SETENTA Y OCHO MIL, DOSCIENTOS TREINTA Y OCHO	-	F.C.L.	AEREO	F.C.L.	103249136	0	13362.28	996.00	133.62	14491.90	35711458	27233220	8478238	7124.61	7000.00	0	DOLAR	IN2022071201	OCHO MILLON CUATROCIENTOS SETENTA Y OCHO MIL DOSCIENTOS TREINTA Y OCHO 	86	2	2	3	17	96	97	1	2	3	OTROS	0	17	0
255	2022-10-24 14:13:28.33	2022-10-24 14:13:28.358	5-Angel Rios 	2022-10-21	22030IC04013106E	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	92007100	0	12449.88	225.00	31.37	12706.25	14210679	10148586	4062093	7241.09	7000.00	0	DOLAR	00011-00000134	CUATRO MILLON SESENTA Y DOS MIL NOVENTA Y TRES 	90	1	30	2	6	22	98	1	2	3	OTROS	0	16	0
256	2022-10-25 10:53:37.413	2022-10-25 10:53:37.413	5-Angel Rios 	2022-10-21	22005IC04021979A	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	242284844	0	29874.24	3286.74	298.74	33459.72	35152297	26985998	8166299	7241.09	7000.00	0	DOLAR	WKRY2233	OCHO MILLON CIENTO SESENTA Y SEIS MIL DOSCIENTOS NOVENTA Y NUEVE 	54	1	5	3	18	15	53	1	2	12	OTROS	0	16	0
257	2022-10-28 15:54:19.235	2022-10-28 15:54:19.235	5-Angel Rios 	2022-10-26	22005IC04022428N	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	68069071	0	8309.31	1000.00	83.09	9392.40	16530610	7506893	9023717	7247.25	7000.00	0	DOLAR	377527	NUEVE MILLON VEINTE Y TRES MIL SETECIENTOS DIECISIETE 	91	4	5	2	10	99	100	1	2	2	OTROS	0	16	0
258	2022-10-28 16:17:41.113	2022-10-28 16:17:41.113	5-Angel Rios 	2022-10-26	22005IC04022428N	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	68069071	0	8309.31	1000.00	83.09	9392.40	16740610	7506893	9233717	7247.25	7000.00	0	DOLAR	377527	NUEVE MILLON DOSCIENTOS TREINTA Y TRES MIL SETECIENTOS DIECISIETE 	91	4	5	2	10	99	100	1	2	2	OTROS	0	16	0
259	2022-10-28 16:20:24.12	2022-10-28 16:20:24.12	5-Angel Rios 	2022-10-26	22005IC04022428N	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	68069071	0	8309.31	1000.00	83.09	9392.40	16765010	7506893	9258117	7247.25	7000.00	0	DOLAR	377527	NUEVE MILLON DOSCIENTOS CINCUENTA Y OCHO MIL CIENTO DIECISIETE 	91	4	5	2	10	99	100	1	2	2	OTROS	0	16	0
260	2022-10-28 16:59:00.361	2022-10-28 16:59:00.377	5-Angel Rios 	2022-10-26	22005IC04022428N	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	68069071	0	8309.31	1000.00	83.09	9392.40	17865010	7506893	10358117	7247.25	7000.00	0	DOLAR	377527	DIEZ MILLONES TRECIENTOS CINCUENTA Y OCHO MIL CIENTO DIECISIETE 	91	4	5	2	10	99	100	1	2	2	OTROS	0	16	0
261	2022-10-31 13:40:19.419	2022-10-31 13:40:19.419	5-Angel Rios 	2022-10-26	22005IC04022428N	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	68069071	0	8309.31	1000.00	83.09	9392.40	17865010	7506893	10358117	7247.25	7000.00	0	DOLAR	377527	DIEZ MILLONES TRECIENTOS CINCUENTA Y OCHO MIL CIENTO DIECISIETE 	91	4	5	2	10	99	100	1	2	2	OTROS	0	16	0
262	2022-10-31 16:43:44.759	2022-10-31 16:43:44.76	5-Angel Rios 	2022-10-28	22005IC04022593Z	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	136554757	0	18058.81	500.00	180.59	18739.40	20855215	15176549	5678666	7287.04	7000.00	0	DOLAR	K-18	CINCO MILLON SEISCIENTOS SETENTA Y OCHO MIL SEISCIENTOS SESENTA Y SEIS 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
265	2022-11-01 08:40:45.773	2022-11-01 08:40:45.773	5-Angel Rios 	2022-10-26	22005IC04022399U	IMPORTACIÓN	ANULADO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	25713460	0	3003.00	515.00	30.03	3548.03	8573313	6829824	1743489	7247.25	7000.00	0	DOLAR	20221017	UN MILLON SETECIENTOS CUARENTA Y TRES MIL CUATROCIENTOS OCHENTA Y NUEVE 	60	1	5	2	10	15	101	1	2	2	OTROS	0	16	0
266	2022-11-01 08:52:30.029	2022-11-01 08:52:30.029	5-Angel Rios 	2022-10-26	22018EC01003410A	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	361507359	0	49980.21	0.00	0.00	49980.21	3798548	0	3798548	7233.01	7000.00	0	DOLAR	001-002-0000306	TRES MILLON SETECIENTOS NOVENTA Y OCHO MIL QUINIENTOS CUARENTA Y OCHO 	81	13	18	2	18	15	102	1	3	3	OTROS	0	16	0
263	2022-10-31 16:59:17.725	2022-10-31 16:59:17.726	5-Angel Rios 	2022-10-28	22005IC04022593Z	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	136554757	0	18058.81	500.00	180.59	18739.40	19627189	15176549	4450640	7287.04	7000.00	0	DOLAR	K-18	CUATRO MILLON CUATROCIENTOS CINCUENTA MIL SEISCIENTOS CUARENTA 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
268	2022-11-01 10:57:53.745	2022-11-01 10:57:53.745	5-Angel Rios 	2022-10-26	22005IC04022399U	IMPORTACIÓN	EMITIDO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	25713460	0	3003.00	515.00	30.03	3548.03	8573313	6829824	1743489	7247.25	7000.00	0	DOLAR	20221017	UN MILLON SETECIENTOS CUARENTA Y TRES MIL CUATROCIENTOS OCHENTA Y NUEVE 	60	1	5	2	10	15	101	1	2	2	OTROS	0	16	0
269	2022-11-01 10:58:56.453	2022-11-01 10:58:56.453	5-Angel Rios 	2022-10-26	22018EC01003410A	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	361507359	0	49980.21	0.00	0.00	49980.21	3798548	0	3798548	7233.01	7000.00	0	DOLAR	001-002-0000306	TRES MILLON SETECIENTOS NOVENTA Y OCHO MIL QUINIENTOS CUARENTA Y OCHO 	81	13	18	2	18	15	102	1	3	3	OTROS	0	16	0
270	2022-11-01 15:07:46.237	2022-11-01 15:07:46.238	5-Angel Rios 	2022-10-28	22005IC04022651L	IMPORTACIÓN	ANULADO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	65535193	0	8408.27	501.04	84.08	8993.39	10965475	7261645	3703830	7287.04	7000.00	0	DOLAR	602/2022	TRES MILLON SETECIENTOS TRES MIL OCHOCIENTOS TREINTA 	75	4	5	2	10	34	59	1	2	4	OTROS	0	16	0
272	2022-11-01 15:31:57.14	2022-11-01 15:31:57.14	5-Angel Rios 	2022-10-28	22005IC04022651L	IMPORTACIÓN	EMITIDO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	65535193	0	8408.27	501.04	84.08	8993.39	10965475	7261645	3703830	7287.04	7000.00	0	DOLAR	602/2022	TRES MILLON SETECIENTOS TRES MIL OCHOCIENTOS TREINTA 	75	4	5	2	10	34	59	1	2	4	OTROS	0	16	0
271	2022-11-01 15:20:15.66	2022-11-01 15:20:15.66	5-Angel Rios 	2022-10-28	22005IT02002128A	IMPORTACIÓN	ANULADO	EL TOTAL MONTO ADELANTO ES EL VALOR DE LA POLIZA ROYAL SEGUROS A PAGAR\nCAPITAL MAXIMO ASEGURADO 6.000.000 Gs / VALIDEZ HASTA 07/12/2023\n	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	49808449	0	6391.30	380.00	63.91	6835.21	2176436	300000	1876436	7287.04	7000.00	0	DOLAR	HSBR221021-2	UN MILLON OCHOCIENTOS SETENTA Y SEIS MIL CUATROCIENTOS TREINTA Y SEIS 	54	1	5	2	10	15	44	1	46	2	OTROS	0	16	0
273	2022-11-02 07:28:48.237	2022-11-02 07:28:48.243	5-Angel Rios 	2022-10-28	22005IT02002128A	IMPORTACIÓN	EMITIDO	EL TOTAL MONTO ADELANTO ES EL VALOR DE LA POLIZA ROYAL SEGUROS A PAGAR\nCAPITAL MAXIMO ASEGURADO 6.000.000 Gs / VALIDEZ HASTA 07/12/2023\n	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	49808449	0	6391.30	380.00	63.91	6835.21	2177436	300000	1877436	7287.04	7000.00	0	DOLAR	HSBR221021-2	UN MILLON OCHOCIENTOS SETENTA Y SIETE MIL CUATROCIENTOS TREINTA Y SEIS 	54	1	5	2	10	15	44	1	46	2	OTROS	0	16	0
274	2022-11-02 15:26:30.534	2022-11-02 15:26:30.534	5-Angel Rios 	2022-10-28	22005IC04022593Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	136554757	0	18058.81	500.00	180.59	18739.40	19627189	15176549	4450640	7287.04	7000.00	0	DOLAR	K-18	CUATRO MILLON CUATROCIENTOS CINCUENTA MIL SEISCIENTOS CUARENTA 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
275	2022-11-07 10:06:57.256	2022-11-07 10:06:57.272	5-Angel Rios 	2022-10-28	22018EC01003478Y	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	523710237	0	71868.72	0.00	0.00	71868.72	3735922	0	3735922	7287.04	7000.00	0	DOLAR	001-002-0000307	TRES MILLON SETECIENTOS TREINTA Y CINCO MIL NOVECIENTOS VEINTE Y DOS 	81	13	18	2	18	15	62	1	3	3	OTROS	0	16	0
276	2022-11-08 16:24:47.818	2022-11-08 16:24:47.833	5-Angel Rios 	2022-11-01	22002IC04022934M	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	102632919	0	11600.00	2400.00	116.00	14116.00	31415911	24618530	6797381	7270.68	7000.00	0	DOLAR	202209263119-1	SEIS MILLON SETECIENTOS NOVENTA Y SIETE MIL TRECIENTOS OCHENTA Y UN 	92	4	2	3	17	34	83	1	2	13	OTROS	0	17	0
277	2022-11-10 08:56:22.484	2022-11-10 08:56:22.484	5-Angel Rios 	2022-10-28	22002IC04022711F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	38457864	0	4600.00	631.57	46.00	5277.57	13083809	0	13083809	7287.04	7000.00	0	DOLAR	MT22L-6-0818	TRECE MILLONES OCHENTA Y TRES MIL OCHOCIENTOS NUEVE 	86	2	2	3	7	20	103	1	2	2	OTROS	0	17	0
280	2022-11-14 11:17:15.615	2022-11-14 11:17:15.626	5-Angel Rios 	2022-10-28	22002IC04022711F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	38457864	0	4600.00	631.57	46.00	5277.57	12878809	0	12878809	7287.04	7000.00	0	DOLAR	MT22L-6-0818	DOCE MILLONES OCHOCIENTOS SETENTA Y OCHO MIL OCHOCIENTOS NUEVE 	86	2	2	3	7	20	103	1	2	2	OTROS	0	17	0
278	2022-11-10 09:15:47.72	2022-11-10 09:15:47.72	5-Angel Rios 	2022-10-28	22002IC04022713H	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	36638290	0	3519.00	1473.68	35.19	5027.87	15354402	0	15354402	7287.04	7000.00	0	DOLAR	MT0336-22	QUINCE MILLONES TRECIENTOS CINCUENTA Y CUATRO MIL CUATROCIENTOS DOS 	86	2	2	3	7	20	103	1	2	2	OTROS	0	17	0
281	2022-11-14 11:22:13.103	2022-11-14 11:22:13.103	5-Angel Rios 	2022-10-28	22002IC04022713H	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	36638290	0	3519.00	1473.68	35.19	5027.87	15139402	0	15139402	7287.04	7000.00	0	DOLAR	MT0336-22	QUINCE MILLONES CIENTO TREINTA Y NUEVE MIL CUATROCIENTOS DOS 	86	2	2	3	7	20	103	1	2	2	OTROS	0	17	0
282	2022-11-14 15:37:02.171	2022-11-14 15:37:02.171	5-Angel Rios 	2022-11-07	22005IM04000596C	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	7062548	0	706.02	271.76	0.48	978.26	2419646	818411	1601235	7219.50	7000.00	0	DOLAR	JMA0350E22	UN MILLON SEISCIENTOS UN MIL DOSCIENTOS TREINTA Y CINCO 	47	4	5	2	28	3	43	1	19	5	OTROS	0	16	0
283	2022-11-18 14:59:04.401	2022-11-18 14:59:04.415	5-Angel Rios 	2022-11-14	22005IT02002225V	IMPORTACIÓN	EMITIDO	EL VALOR DEL TOTAL ADELANTO CORRESPONDE A LA FACTURA A PAGAR DE ROYAL SEGUROS\nVIGENCIA DE SEGURO HASTA 24/12/2023 /// TOTAL MONTO ASEGURADO 99.500.000 GS\nPOLIZA NRO. 1511022450\n	-	F.C.L.	TERRESTRE	F.C.L.	349519782	0	45714.24	3478.15	457.14	49649.53	10575091	3091500	7483591	7039.74	7000.00	0	DOLAR	SR001716-2022	SIETE MILLON CUATROCIENTOS OCHENTA Y TRES MIL QUINIENTOS NOVENTA Y UN 	54	1	5	2	18	15	64	1	46	11	OTROS	0	16	0
284	2022-11-18 15:15:04.219	2022-11-18 15:15:04.219	5-Angel Rios 	2022-11-16	22005IT02002254A	IMPORTACIÓN	EMITIDO	TOTAL MONTO ADELANTO CORRESPONDE A FACTURAR DE ROYAL SEGUROS A PAGAR\nVIGENCIA DE SEGUROS HASTA 26/12/2023 /// CAPITAL MONTO ASEGURADO 66.500.000 GS\nPOLIZA NRO. 1511022508\n	-	F.C.L.	TERRESTRE	F.C.L.	232498128	0	28959.84	3314.60	289.60	32564.04	7967693	2066000	5901693	7139.72	7000.00	0	DOLAR	SR001759-2022	CINCO MILLON NOVECIENTOS UN MIL SEISCIENTOS NOVENTA Y TRES 	54	1	5	2	18	15	64	1	46	11	OTROS	0	16	0
285	2022-11-21 09:53:08.111	2022-11-21 09:53:08.111	5-Angel Rios 	2022-11-21	@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	88763871	0	13028.01	0.00	0.00	13028.01	2086122	0	2086122	6813.31	7000.00	0	DOLAR	001-002-0000310	DOS MILLON OCHENTA Y SEIS MIL CIENTO VEINTE Y DOS 	54	13	18	2	18	15	62	1	3	3	OTROS	0	16	0
286	2022-11-21 11:29:04.372	2022-11-21 11:29:04.372	5-Angel Rios 	2022-11-15	22002IC04023965R	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	39330981	0	5203.82	287.90	52.04	5543.76	7770568	4329665	3440903	7094.64	7000.00	0	DOLAR	QS93191	TRES MILLON CUATROCIENTOS CUARENTA MIL NOVECIENTOS TRES 	53	4	2	3	2	34	80	1	2	2	OTROS	0	17	0
287	2022-11-25 13:29:09.259	2022-11-25 13:29:09.259	5-Angel Rios 	2022-11-24	22038IC04009378F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	465550052	0	45630.00	18840.00	103.90	64573.90	122899567	113112498	9787069	7209.57	7000.00	0	DOLAR	202207MLY20	NUEVE MILLON SETECIENTOS OCHENTA Y SIETE MIL SESENTA Y NUEVE 	93	1	19	3	32	22	105	1	2	10	OTROS	0	16	0
288	2022-11-25 14:01:46.987	2022-11-25 14:01:46.987	5-Angel Rios 	2022-11-24	22038IC04009378F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	465550052	0	45630.00	18840.00	103.90	64573.90	122899567	113112498	9787069	7209.57	7000.00	0	DOLAR	202207MLY20	NUEVE MILLON SETECIENTOS OCHENTA Y SIETE MIL SESENTA Y NUEVE 	93	1	19	3	32	22	105	1	2	10	OTROS	0	16	0
289	2022-11-29 09:10:02.039	2022-11-29 09:10:02.04	5-Angel Rios 	2022-11-24	22038IC04009378F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	FLUVIAL	F.C.L.	465550052	0	45630.00	18840.00	103.90	64573.90	123039567	113112498	9927069	7209.57	7000.00	0	DOLAR	202207MLY20	NUEVE MILLON NOVECIENTOS VEINTE Y SIETE MIL SESENTA Y NUEVE 	93	1	19	3	32	22	105	1	2	10	OTROS	0	16	0
290	2022-11-29 09:41:37.572	2022-11-29 09:41:37.572	5-Angel Rios 	2022-11-18	22018EC01003699T	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	93457342	0	13028.01	0.00	0.00	13028.01	2058525	0	2058525	7173.57	7000.00	0	DOLAR	001-002-0000310	DOS MILLON CINCUENTA Y OCHO MIL QUINIENTOS VEINTE Y CINCO 	81	13	18	2	18	15	62	1	3	3	OTROS	0	16	0
291	2022-11-29 09:47:11.196	2022-11-29 09:47:11.196	5-Angel Rios 	2022-11-24	22018EM01001393S	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	368867051	0	51163.53	0.00	0.00	51163.53	3828031	0	3828031	7209.57	7000.00	0	DOLAR	001-002-0000309	TRES MILLON OCHOCIENTOS VEINTE Y OCHO MIL TREINTA Y UN 	81	13	18	2	18	15	62	1	50	3	OTROS	0	16	0
293	2022-12-01 10:02:55.67	2022-12-01 10:02:55.67	5-Angel Rios 	2022-08-31	22018EC01002739N	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	395260215	0	57329.87	0.00	0.00	57329.87	5944695	0	5944695	6894.49	7000.00	0	DOLAR	001-002-000301	CINCO MILLON NOVECIENTOS CUARENTA Y CUATRO MIL SEISCIENTOS NOVENTA Y CINCO 	54	13	18	2	18	15	46	1	3	3	OTROS	0	16	0
294	2022-12-01 10:05:54.523	2022-12-01 10:05:54.523	5-Angel Rios 	2022-09-07	22005IC04018451Y	IMPORTACIÓN	EMITIDO	SALDO A FAVOR  EN SISTEMA  DE 25.844 GS	-	F.C.L.	TERRESTRE	F.C.L.	241427860	0	31430.88	3214.31	314.31	34959.50	35829197	26530087	9299110	6905.93	7000.00	0	DOLAR	WKRY2228	NUEVE MILLON DOSCIENTOS NOVENTA Y NUEVE MIL CIENTO DIEZ 	54	1	5	2	18	15	53	1	2	12	DERECHO ADUANERO MP 	33799900	16	0
295	2022-12-05 11:28:07.813	2022-12-05 11:28:07.813	5-Angel Rios 	2022-12-01	22005IC04025243L	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	268566174	100	36022.00	720.00	360.22	37102.22	38053379	29963173	8090206	7219.09	7000.00	0	DOLAR	20221811/20223009	OCHO MILLON NOVENTA MIL DOSCIENTOS SEIS 	47	4	5	2	10	108	89	1	2	3	OTROS	0	16	0
296	2022-12-05 11:49:47.565	2022-12-05 11:49:47.565	5-Angel Rios 	2022-12-01	22005IC04025243L	IMPORTACIÓN	EMITIDO	NINGUNA	CAMION CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	268566174	100	36022.00	720.00	360.22	37102.22	38053379	29963173	8090206	7219.09	7000.00	0	DOLAR	20221811/3009	OCHO MILLON NOVENTA MIL DOSCIENTOS SEIS 	47	4	5	3	10	108	89	1	2	3	OTROS	0	16	0
297	2022-12-05 16:23:03.017	2022-12-05 16:23:03.017	5-Angel Rios 	2022-12-05	PRESUPUESTO 	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	59595300	0	8000.00	200.00	80.00	8280.00	9990091	0	9990091	7197.50	7000.00	0	DOLAR	18140	NUEVE MILLON NOVECIENTOS NOVENTA MIL NOVENTA Y UN 	83	4	5	3	1	108	58	1	2	10	OTROS	0	16	0
298	2022-12-09 09:18:22.561	2022-12-09 09:18:22.571	5-Angel Rios 	2022-12-07	22005IT02002407A	IMPORTACIÓN	ANULADO	EL TOTAL ADELANTO ES EL VALOR DE LA POLIZA DE SEGUROS // VENCIMIENTO POLIZA 16/01/21\nCAPITAL MAXIMO ASEGURADO 80.000.000 Gs // POLIZA N° 1511022862	-	F.C.L.	TERRESTRE	F.C.L.	290877278	0	38053.20	2250.00	380.53	40683.73	8560722	2485500	6075222	7149.72	7000.00	0	DOLAR	001/22	SEIS MILLON SETENTA Y CINCO MIL DOSCIENTOS VEINTE Y DOS 	54	1	5	2	18	15	46	1	46	4	OTROS	0	16	0
299	2022-12-09 09:28:20.052	2022-12-09 09:28:20.052	5-Angel Rios 	2022-12-07	22005IT02002407A	IMPORTACIÓN	EMITIDO	EL TOTAL ADELANTO ES EL VALOR DE LA POLIZA DE SEGUROS // VENCIMIENTO POLIZA 16/01/24\nCAPITAL MAXIMO ASEGURADO 80.000.000 Gs // POLIZA N° 1511022862	-	F.C.L.	TERRESTRE	F.C.L.	290877278	0	38053.20	2250.00	380.53	40683.73	8560722	2485500	6075222	7149.72	7000.00	0	DOLAR	001/22	SEIS MILLON SETENTA Y CINCO MIL DOSCIENTOS VEINTE Y DOS 	54	1	5	2	18	15	46	1	46	4	OTROS	0	16	0
292	2022-11-29 12:20:05.937	2022-11-29 12:20:05.937	5-Angel Rios 	2022-11-28	22013IC04000584L	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	175246010	788	21910.72	1292.27	219.11	23422.10	22655867	0	22655867	7238.43	7000.00	0	DOLAR	0000016	VEINTE Y DOS MILLONES SEISCIENTOS CINCUENTA Y CINCO MIL OCHOCIENTOS SESENTA Y SIETE 	94	1	13	3	12	94	106	1	2	10	OTROS	0	107	0
301	2022-12-12 14:38:33.048	2022-12-12 14:38:33.048	5-Angel Rios 	2022-11-28	22013IC04000584L	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	175243114	788	21910.72	1292.27	219.11	23422.10	19987014	0	19987014	7238.43	7000.00	0	DOLAR	0000016	DIECINUEVEMILLONES NOVECIENTOS OCHENTA Y SIETE MIL CATORCE 	94	1	13	3	12	94	106	1	2	10	OTROS	0	107	0
300	2022-12-12 14:17:46.641	2022-12-12 14:17:46.642	5-Angel Rios 	2022-12-07	22032IC04007922P	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	193855363	0	19320.50	7600.00	193.20	27113.70	75654106	60200370	15453736	7149.72	7000.00	0	DOLAR	OK20220801	QUINCE MILLONES CUATROCIENTOS CINCUENTA Y TRES MIL SETECIENTOS TREINTA Y SEIS 	95	4	32	3	33	109	110	1	2	10	OTROS	0	16	0
302	2022-12-12 14:55:18.89	2022-12-12 14:55:18.891	5-Angel Rios 	2022-12-07	22032IC04007922P	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	193855363	0	19320.50	7600.00	193.20	27113.70	76034902	60200370	15834532	7149.72	7000.00	0	DOLAR	OK20220801	QUINCE MILLONES OCHOCIENTOS TREINTA Y CUATRO MIL QUINIENTOS TREINTA Y DOS 	95	4	32	3	33	109	110	1	2	10	OTROS	0	16	0
303	2022-12-13 09:49:12.284	2022-12-13 09:49:12.284	5-Angel Rios 	2022-12-13	22015IC04000064@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	498381413	0	67500.00	1500.00	30.00	69030.00	71631217	0	71631217	7219.78	7000.00	0	DOLAR	IMP22-01	SETENTA Y UN MILLONES SEISCIENTOS TREINTA Y UN MIL DOSCIENTOS DIECISIETE 	96	1	15	3	1	111	112	1	2	2	OTROS	0	17	0
304	2022-12-13 09:49:56.701	2022-12-13 09:49:56.702	5-Angel Rios 	2022-12-13	22015IC04000064@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	470322789	0	67500.00	1500.00	30.00	69030.00	71631217	0	71631217	6813.31	7000.00	0	DOLAR	IMP22-01	SETENTA Y UN MILLONES SEISCIENTOS TREINTA Y UN MIL DOSCIENTOS DIECISIETE 	96	1	15	3	1	111	112	1	2	2	OTROS	0	17	0
305	2022-12-13 09:51:54.311	2022-12-13 09:51:54.312	5-Angel Rios 	2022-12-13	22015IC04000064@	PROFORMA	PROFORMA	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	498381413	0	67500.00	1500.00	30.00	69030.00	71631217	0	71631217	7219.78	7000.00	0	DOLAR	IMP22-01	SETENTA Y UN MILLONES SEISCIENTOS TREINTA Y UN MIL DOSCIENTOS DIECISIETE 	96	1	15	3	1	111	112	1	2	2	OTROS	0	17	0
307	2022-12-14 14:20:13.805	2022-12-14 14:20:13.805	5-Angel Rios 	2022-12-12	22005IC04025979E	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	211163540	250	28266.37	675.00	14.12	28955.49	29729343	0	29729343	7230.39	7000.00	0	DOLAR	JME1277E22	VEINTE Y NUEVE MILLONES SETECIENTOS VEINTE Y NUEVE MIL TRECIENTOS CUARENTA Y TRES 	47	4	5	2	10	3	43	1	2	5	OTROS	0	16	0
308	2022-12-14 14:40:12.807	2022-12-14 14:40:12.807	5-Angel Rios 	2022-12-13	22005IC04026060J	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	369977410	0	48955.42	1800.00	489.55	51244.97	48270231	40624345	7645886	7219.78	7000.00	0	DOLAR	032PY/2022	SIETE MILLON SEISCIENTOS CUARENTA Y CINCO MIL OCHOCIENTOS OCHENTA Y SEIS 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
306	2022-12-14 13:19:47.669	2022-12-14 13:19:47.669	5-Angel Rios 	2022-12-12	22002IM04012189A	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	10574445	0	1250.00	200.00	12.50	1462.50	1764361	1169392	594969	7230.39	7000.00	0	DOLAR	988865282	QUINIENTOS NOVENTA Y CUATRO MIL NOVECIENTOS SESENTA Y NUEVE 	53	4	2	3	12	108	113	1	19	1	OTROS	0	17	0
309	2022-12-14 14:53:55.277	2022-12-14 14:53:55.277	5-Angel Rios 	2022-12-12	22002IM04012189A	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	10574445	0	1250.00	200.00	12.50	1462.50	2841280	1169392	1671888	7230.39	7000.00	0	DOLAR	988865282	UN MILLON SEISCIENTOS SETENTA Y UN MIL OCHOCIENTOS OCHENTA Y OCHO 	53	4	2	3	12	108	113	1	19	1	OTROS	0	17	0
310	2022-12-16 08:52:48.989	2022-12-16 08:52:49	5-Angel Rios 	2022-12-12	22005IC04025979E	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	211167083	250	28266.37	675.00	14.12	28955.49	29729343	24122034	5607309	7230.39	7000.00	0	DOLAR	JME1277E22	CINCO MILLON SEISCIENTOS SIETE MIL TRECIENTOS NUEVE 	47	4	5	2	10	3	43	1	2	5	OTROS	0	16	0
311	2022-12-20 09:31:04.644	2022-12-20 09:31:04.65	5-Angel Rios 	2022-12-19	22005IC04026605Y	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77634329	0	10209.37	460.00	102.09	10771.46	12367888	8574477	3793411	7207.41	7000.00	0	DOLAR	-	TRES MILLON SETECIENTOS NOVENTA Y TRES MIL CUATROCIENTOS ONCE 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
312	2022-12-20 09:32:29.644	2022-12-20 09:32:29.644	5-Angel Rios 	2022-12-19	22005IC04026605Y	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77634329	0	10209.37	460.00	102.09	10771.46	12436442	8574477	3861965	7207.41	7000.00	0	DOLAR	k019	TRES MILLON OCHOCIENTOS SESENTA Y UN MIL NOVECIENTOS SESENTA Y CINCO 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
313	2022-12-20 09:51:49.92	2022-12-20 09:51:49.92	5-Angel Rios 	2022-12-19	22005IC04026605Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77634329	0	10209.37	460.00	102.09	10771.46	12236442	8574477	3661965	7207.41	7000.00	0	DOLAR	k019	TRES MILLON SEISCIENTOS SESENTA Y UN MIL NOVECIENTOS SESENTA Y CINCO 	64	14	5	2	10	56	57	1	2	3	OTROS	0	16	0
316	2022-12-20 16:22:26.083	2022-12-20 16:22:26.083	5-Angel Rios 	2022-12-19	22005IM04000690U	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	12448350	0	818.97	900.00	8.19	1727.16	2937603	1445087	1492516	7207.41	7000.00	0	DOLAR	38/2022	UN MILLON CUATROCIENTOS NOVENTA Y DOS MIL QUINIENTOS DIECISEIS 	47	4	5	2	22	3	41	1	19	2	OTROS	0	16	0
317	2022-12-22 08:56:46.674	2022-12-22 08:56:46.674	5-Angel Rios 	2022-12-19	22005IT04026628T	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	873734566	0	119135.90	900.00	1191.36	121227.26	106913572	95911181	11002391	7207.41	7000.00	0	DOLAR	37/2022	ONCE MILLONES DOS MIL TRECIENTOS NOVENTA Y UN 	47	4	5	2	22	3	41	1	48	3	OTROS	0	16	0
318	2022-12-27 07:48:45.889	2022-12-27 07:48:45.889	5-Angel Rios 	2022-12-19	22005IT04026628T	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	873734566	0	119135.90	900.00	1191.36	121227.26	106913572	95911181	11002391	7207.41	7000.00	0	DOLAR	37/2022	ONCE MILLONES DOS MIL TRECIENTOS NOVENTA Y UN 	47	4	5	2	22	3	41	1	48	3	OTROS	0	16	0
323	2023-01-04 15:54:26.707	2023-01-04 15:54:26.707	5-Angel Rios 	2023-01-02	23005IT02000003Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	109767587	0	14364.00	435.00	143.64	14942.64	609168	0	609168	7345.93	7000.00	0	DOLAR	HSBR221114-2	SEISCIENTOS NUEVE MIL CIENTO SESENTA Y OCHO 	54	1	5	2	10	15	44	1	46	2	OTROS	0	16	0
321	2023-01-04 14:08:08.463	2023-01-04 14:08:08.463	5-Angel Rios 	2023-01-02	23005IC04000047H	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	55588856	0	5730.00	1780.00	57.30	7567.30	9352990	6161333	3191657	7345.93	7000.00	0	DOLAR	MP-043/22	TRES MILLON CIENTO NOVENTA Y UN MIL SEISCIENTOS CINCUENTA Y SIETE 	55	4	5	2	4	8	104	1	2	2	OTROS	0	16	0
324	2023-01-04 15:56:06.47	2023-01-04 15:56:06.47	5-Angel Rios 	2023-01-02	23005IC04000047H	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	55588856	0	5730.00	1780.00	57.30	7567.30	9352990	6161333	3191657	7345.93	7000.00	0	DOLAR	MP-043/22	TRES MILLON CIENTO NOVENTA Y UN MIL SEISCIENTOS CINCUENTA Y SIETE 	55	4	5	2	4	8	104	1	2	2	OTROS	0	16	0
320	2023-01-04 09:40:08.604	2023-01-04 09:40:08.612	5-Angel Rios 	2022-12-28	22030IC04016462M	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	122851519	0	16261.08	412.50	45.53	16719.11	18390717	13526666	4864051	7347.97	7000.00	0	DOLAR	002-200-004352	CUATRO MILLON OCHOCIENTOS SESENTA Y CUATRO MIL CINCUENTA Y UN 	63	1	30	3	6	22	114	1	2	3	OTROS	0	16	0
325	2023-01-05 14:19:21.411	2023-01-05 14:19:21.412	5-Angel Rios 	2022-12-28	22030IC04016462M	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	122851519	0	16261.08	412.50	45.53	16719.11	18390717	13526666	4864051	7347.97	7000.00	0	DOLAR	002-200-004352	CUATRO MILLON OCHOCIENTOS SESENTA Y CUATRO MIL CINCUENTA Y UN 	63	1	30	2	6	22	114	1	2	3	OTROS	0	16	0
319	2023-01-02 16:42:25.277	2023-01-02 16:42:25.288	5-Angel Rios 	2022-12-26	22005IC04027254P	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	92266688	101	11493.15	900.00	114.93	12508.08	19861367	15325643	4535724	7317.77	7000.00	0	DOLAR	-	CUATRO MILLON QUINIENTOS TREINTA Y CINCO MIL SETECIENTOS VEINTE Y CUATRO 	97	4	5	2	4	8	88	1	2	3	OTROS	0	16	0
326	2023-01-09 08:53:01.198	2023-01-09 08:53:01.199	5-Angel Rios 	2022-12-26	22005IC04027254P	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	92270347	101	11493.15	900.00	114.93	12508.08	19861367	15325643	4535724	7317.77	7000.00	0	DOLAR	WDSJ02290-22	CUATRO MILLON QUINIENTOS TREINTA Y CINCO MIL SETECIENTOS VEINTE Y CUATRO 	97	4	5	2	4	8	88	1	2	3	OTROS	0	17	0
327	2023-01-09 09:25:26.261	2023-01-09 09:25:26.261	5-Angel Rios 	2022-12-16	22002IC04026356Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	94555065	0	12670.40	244.78	126.70	13041.88	14874145	10389601	4484544	7250.11	7000.00	0	DOLAR	QY71552/QL305212	CUATRO MILLON CUATROCIENTOS OCHENTA Y CUATRO MIL QUINIENTOS CUARENTA Y CUATRO 	83	4	2	3	2	34	80	1	2	2	OTROS	0	17	0
314	2022-12-20 10:52:27.747	2022-12-20 10:52:27.747	5-Angel Rios 	2022-12-13	22018EC01003938P	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	196051649	0	27272.16	0.00	0.00	27272.16	2626454	0	2626454	7188.71	7000.00	0	DOLAR	001-002-0000311	DOS MILLON SEISCIENTOS VEINTE Y SEIS MIL CUATROCIENTOS CINCUENTA Y CUATRO 	54	1	18	2	18	15	62	1	3	3	OTROS	0	16	0
328	2023-01-09 10:01:15.286	2023-01-09 10:01:15.286	5-Angel Rios 	2023-01-09	23005IT0402000003Y	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	109767587	0	14364.00	435.00	143.64	14942.64	3577890	0	3577890	7345.93	7000.00	0	DOLAR	HSBR221114-2	TRES MILLON QUINIENTOS SETENTA Y SIETE MIL OCHOCIENTOS NOVENTA 	54	1	5	2	10	15	44	1	48	2	OTROS	0	16	0
322	2023-01-04 14:43:27.552	2023-01-04 14:43:27.552	5-Angel Rios 	2023-01-03	23005IC04000095K	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	61644156	0	8000.00	360.00	80.00	8440.00	10408421	6824091	3584330	7303.81	7000.00	0	DOLAR	18140	TRES MILLON QUINIENTOS OCHENTA Y CUATRO MIL TRECIENTOS TREINTA 	83	4	5	2	10	108	115	1	2	2	OTROS	0	16	0
329	2023-01-09 11:23:25.496	2023-01-09 11:23:25.496	5-Angel Rios 	2022-12-22	22005IC04027294T	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	385000944	0	49792.87	2300.00	497.93	52590.80	50074508	42237272	7837236	7320.69	7000.00	0	DOLAR	002/22	SIETE MILLON OCHOCIENTOS TREINTA Y SIETE MIL DOSCIENTOS TREINTA Y SEIS 	54	1	5	2	18	15	46	1	2	4	OTROS	0	16	0
315	2022-12-20 10:59:29.048	2022-12-20 10:59:29.048	5-Angel Rios 	2022-12-13	22018EM01001449U	EXPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	196125179	0	27164.98	0.00	0.00	27164.98	2727934	0	2727934	7219.78	7000.00	0	DOLAR	001-004-0000301	DOS MILLON SETECIENTOS VEINTE Y SIETE MIL NOVECIENTOS TREINTA Y CUATRO 	54	1	18	2	18	15	62	1	50	3	OTROS	0	16	0
330	2023-01-10 10:13:21.09	2023-01-10 10:13:21.091	5-Angel Rios 	2022-12-13	22018EM01001449U	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	196125179	0	27164.98	0.00	0.00	27164.98	2727934	0	2727934	7219.78	7000.00	0	DOLAR	001-004-0000301	DOS MILLON SETECIENTOS VEINTE Y SIETE MIL NOVECIENTOS TREINTA Y CUATRO 	54	1	18	2	18	15	62	1	50	3	OTROS	0	16	0
331	2023-01-10 10:14:09.918	2023-01-10 10:14:09.918	5-Angel Rios 	2022-12-13	22018EC01003938P	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	196051649	0	27272.16	0.00	0.00	27272.16	2626454	0	2626454	7188.71	7000.00	0	DOLAR	001-002-0000311	DOS MILLON SEISCIENTOS VEINTE Y SEIS MIL CUATROCIENTOS CINCUENTA Y CUATRO 	54	1	18	2	18	15	62	1	3	3	OTROS	0	16	0
332	2023-01-10 10:27:02.734	2023-01-10 10:27:02.734	5-Angel Rios 	2022-12-26	22005IC04027188V	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	576559268	115	76573.20	1335.00	765.73	78673.93	112256036	0	112256036	7317.77	7000.00	0	DOLAR	2022-023	CIENTO DOCE MILLONES DOSCIENTOS CINCUENTA Y SEIS MIL TREINTA Y SEIS 	60	1	5	2	10	15	72	1	2	3	OTROS	0	16	0
333	2023-01-10 10:27:42.046	2023-01-10 10:27:42.046	5-Angel Rios 	2022-12-26	22005IC04027188V	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	576559268	115	76573.20	1335.00	765.73	78673.93	112256036	102582527	9673509	7317.77	7000.00	0	DOLAR	2022-023	NUEVE MILLON SEISCIENTOS SETENTA Y TRES MIL QUINIENTOS NUEVE 	60	1	5	2	10	15	72	1	2	3	OTROS	0	16	0
334	2023-01-10 10:53:34.814	2023-01-10 10:53:34.814	5-Angel Rios 	2022-12-21	22018EC01004012W	EXPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	371982409	0	51228.92	0.00	0.00	51228.92	3486671	0	3486671	7261.18	7000.00	0	DOLAR	001-002-0000312	TRES MILLON CUATROCIENTOS OCHENTA Y SEIS MIL SEISCIENTOS SETENTA Y UN 	81	13	18	2	18	15	62	1	3	3	OTROS	0	16	0
335	2023-01-10 11:04:13.63	2023-01-10 11:04:13.63	5-Angel Rios 	2023-01-02	23005IT0402000003Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	109767587	0	14364.00	435.00	143.64	14942.64	3577890	0	3577890	7345.93	7000.00	0	DOLAR	HSBR221114-2	TRES MILLON QUINIENTOS SETENTA Y SIETE MIL OCHOCIENTOS NOVENTA 	54	1	5	2	10	15	44	1	48	2	OTROS	0	16	0
336	2023-01-18 09:09:07.465	2023-01-18 09:09:07.465	5-Angel Rios 	2022-12-16	22002IC04026356Y	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	94555065	0	12670.40	244.78	126.70	13041.88	14874125	10389601	4484524	7250.11	7000.00	0	DOLAR	QY1552/QL305212	CUATRO MILLON CUATROCIENTOS OCHENTA Y CUATRO MIL QUINIENTOS VEINTE Y CUATRO 	47	4	2	3	2	34	80	1	2	2	OTROS	0	17	0
337	2023-01-18 09:24:43.635	2023-01-18 09:24:43.635	5-Angel Rios 	2023-01-03	23005IC04000095K	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	61644156	0	8000.00	360.00	80.00	8440.00	10408421	6824091	3584330	7303.81	7000.00	0	DOLAR	18140	TRES MILLON QUINIENTOS OCHENTA Y CUATRO MIL TRECIENTOS TREINTA 	83	4	5	2	10	108	115	1	2	2	OTROS	0	17	0
338	2023-01-18 10:59:35.063	2023-01-18 10:59:35.063	5-Angel Rios 	2023-01-18	23005IC04001386@	PROFORMA	PROFORMA	NINGUNA	CONSOLIDADO 	L.C.L.	TERRESTRE	L.C.L.	40430119	280	4743.32	375.00	47.43	5165.75	7237641	0	7237641	7424.16	7000.00	0	DOLAR	01-22	SIETE MILLON DOSCIENTOS TREINTA Y SIETE MIL SEISCIENTOS CUARENTA Y UN 	50	1	5	2	10	111	116	1	2	3	OTROS	0	16	0
339	2023-01-18 11:12:54.376	2023-01-18 11:12:54.376	5-Angel Rios 	2023-01-18	23005IC04001386@	PROFORMA	PROFORMA	NINGUNA	CONSOLIDADO 	L.C.L.	TERRESTRE	L.C.L.	40430119	280	4743.32	375.00	47.43	5165.75	8437641	0	8437641	7424.16	7000.00	0	DOLAR	01-22	OCHO MILLON CUATROCIENTOS TREINTA Y SIETE MIL SEISCIENTOS CUARENTA Y UN 	50	1	5	2	10	111	116	1	2	3	OTROS	0	16	0
341	2023-01-18 11:42:14.766	2023-01-18 11:42:14.766	5-Angel Rios 	2023-01-12	23005IC04000679S	IMPORTACIÓN	EMITIDO	NINGUNA	CARRETA	F.C.L.	TERRESTRE	F.C.L.	808656574	0	106466.00	1800.00	1064.66	109330.66	99172573	88636036	10536537	7396.43	7000.00	0	DOLAR	01/2023	DIEZ MILLONES QUINIENTOS TREINTA Y SEIS MIL QUINIENTOS TREINTA Y SIETE 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
340	2023-01-18 11:26:54.829	2023-01-18 11:26:54.829	5-Angel Rios 	2023-01-17	23005IC04000726L	IMPORTACIÓN	ANULADO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	354017886	100	46400.00	795.00	464.00	47659.00	47084884	38844007	8240877	7412.59	7000.00	0	DOLAR	20221312	OCHO MILLON DOSCIENTOS CUARENTA MIL OCHOCIENTOS SETENTA Y SIETE 	47	4	5	2	10	108	89	1	2	3	OTROS	0	16	0
342	2023-01-18 11:43:43.011	2023-01-18 11:43:43.011	5-Angel Rios 	2023-01-17	23005IC04000726L	IMPORTACIÓN	EMITIDO	NINGUNA	CONSOLIDADO	L.C.L.	TERRESTRE	L.C.L.	354017886	100	46400.00	795.00	464.00	47659.00	47084884	38844007	8240877	7412.59	7000.00	0	DOLAR	20221312	OCHO MILLON DOSCIENTOS CUARENTA MIL OCHOCIENTOS SETENTA Y SIETE 	47	4	5	3	10	108	89	1	2	3	OTROS	0	16	0
343	2023-01-18 15:20:38.811	2023-01-18 15:20:38.811	5-Angel Rios 	2023-01-12	23002IC04000811D	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	AEREO	F.C.L.	40188059	0	4871.75	512.97	48.72	5433.44	7887851	4424473	3463378	7396.43	7000.00	0	DOLAR	C44153	TRES MILLON CUATROCIENTOS SESENTA Y TRES MIL TRECIENTOS SETENTA Y OCHO 	53	4	2	3	12	108	117	1	2	3	OTROS	0	17	0
344	2023-01-18 15:54:57.307	2023-01-18 15:54:57.307	5-Angel Rios 	2023-01-17	23030IM04000026M	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	16069385	0	1393.81	758.82	13.94	2166.57	9977920	5475668	4502252	7416.97	7000.00	0	DOLAR	OK20220801B	CUATRO MILLON QUINIENTOS DOS MIL DOSCIENTOS CINCUENTA Y DOS 	95	4	30	3	31	109	110	1	19	10	OTROS	0	17	0
345	2023-01-19 10:19:50.879	2023-01-19 10:19:50.881	5-Angel Rios 	2023-01-13	23015IC04000328K	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	312107102	0	40500.00	1200.00	405.00	42105.00	60312863	0	60312863	7412.59	7000.00	0	DOLAR	0000000195	SESENTA MILLONES TRECIENTOS DOCE MIL OCHOCIENTOS SESENTA Y TRES 	96	1	15	1	34	50	112	1	2	2	OTROS	0	17	0
347	2023-01-27 08:41:24.334	2023-01-27 08:41:24.345	5-Angel Rios 	2023-01-23	23005IC04001270G	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	781801679	0	102270.46	1800.00	1022.70	105093.16	96029939	85697064	10332875	7439.13	7000.00	0	DOLAR	02/2023	DIEZ MILLONES TRECIENTOS TREINTA Y DOS MIL OCHOCIENTOS SETENTA Y CINCO 	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
348	2023-01-27 08:59:01.716	2023-01-27 08:59:01.716	5-Angel Rios 	2023-01-26	23005IC04001533X	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	56780809	0	6776.20	850.00	67.76	7693.96	9876247	6626172	3250075	7379.92	7000.00	0	DOLAR	0539.22	TRES MILLON DOSCIENTOS CINCUENTA MIL SETENTA Y CINCO 	47	4	5	2	4	8	87	1	2	2	OTROS	0	16	0
346	2023-01-26 11:08:45.229	2023-01-26 11:08:45.235	5-Angel Rios 	2023-01-19	23030IC04000797R	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	101286629	0	13000.00	600.00	27.00	13627.00	21402077	21152077	250000	7432.79	7000.00	0	DOLAR	20220919	DOSCIENTOS CINCUENTA MIL 	98	4	30	3	35	3	118	1	2	12	OTROS	0	16	0
349	2023-01-30 11:38:27.089	2023-01-30 11:38:27.09	5-Angel Rios 	2023-01-19	23030IC04000797R	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	AEREO	L.C.L.	101286629	0	13000.00	600.00	27.00	13627.00	29061487	21152077	7909410	7432.79	7000.00	0	DOLAR	20220919	SIETE MILLON NOVECIENTOS NUEVE MIL CUATROCIENTOS DIEZ 	98	4	30	3	35	3	118	1	2	12	OTROS	0	16	0
350	2023-01-30 14:11:01.294	2023-01-30 14:11:01.294	5-Angel Rios 	2023-01-27	23038IC04000560N	IMPORTACIÓN	ANULADO	NINGUNA	2X20	F.C.L.	FLUVIAL	F.C.L.	992590110	0	119185.26	14800.00	283.17	134268.43	217883570	207671357	10212213	7392.58	7000.00	0	DOLAR	SMT20220727	DIEZ MILLONES DOSCIENTOS DOCE MIL DOSCIENTOS TRECE 	63	1	39	3	32	22	92	1	2	10	OTROS	0	16	0
351	2023-01-30 14:37:17.337	2023-01-30 14:37:17.337	5-Angel Rios 	2023-01-27	23038IC04000560N	IMPORTACIÓN	EMITIDO	NINGUNA	2x40	F.C.L.	FLUVIAL	F.C.L.	992590110	0	119185.26	14800.00	283.17	134268.43	217883570	207671357	10212213	7392.58	7000.00	0	DOLAR	SMT20220727	DIEZ MILLONES DOSCIENTOS DOCE MIL DOSCIENTOS TRECE 	63	1	39	3	32	22	92	1	2	10	OTROS	0	16	0
352	2023-02-08 10:28:37.017	2023-02-08 10:28:37.018	5-Angel Rios 	2023-02-03	23005IC04002151F	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	53122224	0	6502.91	750.00	3.59	7256.50	9793730	5927719	3866011	7320.64	7000.00	0	DOLAR	JMA0604E22	TRES MILLON OCHOCIENTOS SESENTA Y SEIS MIL ONCE 	47	4	5	2	28	3	43	1	2	5	OTROS	0	16	0
353	2023-02-08 10:48:55.356	2023-02-08 10:48:55.356	5-Angel Rios 	2023-02-03	23005IC04002211C	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	77305958	0	10000.00	460.00	100.00	10560.00	12720784	8540330	4180454	7320.64	7000.00	0	DOLAR	18530	CUATRO MILLON CIENTO OCHENTA MIL CUATROCIENTOS CINCUENTA Y CUATRO 	83	4	5	2	10	108	115	1	2	2	OTROS	0	16	0
354	2023-02-08 11:16:49.473	2023-02-08 11:16:49.473	5-Angel Rios 	2023-02-02	23005IC04002142F	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	494385627	0	64572.64	2400.00	645.73	67618.37	63257495	54218895	9038600	7311.41	7000.00	0	DOLAR	001/23	NUEVE MILLON TREINTA Y OCHO MIL SEISCIENTOS 	54	1	5	2	18	94	46	1	2	4	OTROS	0	16	0
356	2023-02-08 11:41:35.254	2023-02-08 11:41:35.254	5-Angel Rios 	2023-02-02	23005IC04002142F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	494385627	0	64572.64	2400.00	645.73	67618.37	63257495	54218895	9038600	7311.41	7000.00	0	DOLAR	001/23	NUEVE MILLON TREINTA Y OCHO MIL SEISCIENTOS 	54	1	5	2	18	120	46	1	2	4	OTROS	0	16	0
355	2023-02-08 11:40:29.158	2023-02-08 11:40:29.158	5-Angel Rios 	2023-02-06	23005IM04000055Z	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	2787639	0	130.00	250.00	1.30	381.30	1	0	1	7310.88	7000.00	0	DOLAR	1	UN 	47	4	5	2	10	3	75	1	19	3	OTROS	0	16	0
358	2023-02-08 15:18:11.825	2023-02-08 15:18:11.825	5-Angel Rios 	2023-02-07	23005IC04002430F	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	665093460	0	88247.56	2000.00	882.48	91130.04	82561441	72913910	9647531	7298.29	7000.00	0	DOLAR	202212010	NUEVE MILLON SEISCIENTOS CUARENTA Y SIETE MIL QUINIENTOS TREINTA Y UN 	47	4	5	2	22	3	121	1	2	2	OTROS	0	16	0
357	2023-02-08 15:05:30.196	2023-02-08 15:05:30.196	5-Angel Rios 	2023-02-06	23005IM04000055Z	IMPORTACIÓN	ANULADO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	2787639	0	130.00	250.00	1.30	381.30	2163289	345062	1818227	7310.88	7000.00	0	DOLAR	001/2023	UN MILLON OCHOCIENTOS DIECIOCHO MIL DOSCIENTOS VEINTE Y SIETE 	47	4	5	2	10	3	75	1	19	3	OTROS	0	16	0
359	2023-02-08 15:45:51.899	2023-02-08 15:45:51.899	5-Angel Rios 	2023-02-06	23005IM04000055Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	2787639	0	130.00	250.00	1.30	381.30	1708288	345062	1363226	7310.88	7000.00	0	DOLAR	001/2023	UN MILLON TRECIENTOS SESENTA Y TRES MIL DOSCIENTOS VEINTE Y SEIS 	47	4	5	2	10	3	75	1	19	3	OTROS	0	16	0
360	2023-02-14 13:32:45.059	2023-02-14 13:32:45.059	5-Angel Rios 	2023-02-09	23030IC04001776P	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	87510980	0	11533.57	450.00	32.30	12015.87	13443866	9656706	3787160	7282.95	7000.00	0	DOLAR	0005-00000607	TRES MILLON SETECIENTOS OCHENTA Y SIETE MIL CIENTO SESENTA 	63	1	30	2	36	22	122	1	2	3	OTROS	0	16	0
361	2023-02-14 13:56:25.078	2023-02-14 13:56:25.078	5-Angel Rios 	2023-02-09	23030IC04001776P	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	87510980	0	11533.57	450.00	32.30	12015.87	13443866	9656706	3787160	7282.95	7000.00	0	DOLAR	0005-00000607	TRES MILLON SETECIENTOS OCHENTA Y SIETE MIL CIENTO SESENTA 	63	1	30	2	36	22	122	1	2	3	OTROS	0	16	0
362	2023-02-16 10:40:27.242	2023-02-16 10:40:27.251	5-Angel Rios 	2023-02-10	23005IM04000064Z	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	5370975	0	433.11	300.00	4.33	737.44	2234823	837058	1397765	7283.27	7000.00	0	DOLAR	04/2023	UN MILLON TRECIENTOS NOVENTA Y SIETE MIL SETECIENTOS SESENTA Y CINCO 	47	4	5	2	22	3	41	1	19	2	OTROS	0	16	0
363	2023-02-16 10:58:33.107	2023-02-16 10:58:33.107	5-Angel Rios 	2023-02-16	23005IC04002726N	IMPORTACIÓN	EMITIDO	NINGUNA	-	L.C.L.	TERRESTRE	L.C.L.	1048846597	0	141096.68	1500.00	1410.97	144007.65	128982664	115950545	13032119	7283.27	7000.00	0	DOLAR	03/2022	TRECE MILLONES TREINTA Y DOS MIL CIENTO DIECINUEVE	47	4	5	2	22	3	41	1	2	2	OTROS	0	16	0
364	2023-02-16 11:15:56.321	2023-02-16 11:15:56.321	5-Angel Rios 	2023-02-10	23005IC04002603H	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	354133589	0	46361.41	1800.00	463.61	48625.02	46601198	38889125	7712073	7282.95	7000.00	0	DOLAR	004PY/2023	SIETE MILLON SETECIENTOS DOCE MIL SETENTA Y TRES 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
365	2023-02-16 11:41:16.99	2023-02-16 11:41:16.99	5-Angel Rios 	2023-02-14	23005IC04002879W	IMPORTACIÓN	EMITIDO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	307446450	0	40310.00	1500.00	403.10	42213.10	41066460	33743685	7322775	7283.20	7000.00	0	DOLAR	005PY/2023	SIETE MILLON TRECIENTOS VEINTE Y DOS MIL SETECIENTOS SETENTA Y CINCO 	47	4	5	2	22	3	48	1	2	3	OTROS	0	16	0
366	2023-02-16 14:19:16.813	2023-02-16 14:19:16.813	5-Angel Rios 	2023-02-15	23005IT02000322S	IMPORTACIÓN	ANULADO	NINGUNA	-	F.C.L.	TERRESTRE	F.C.L.	504419504	0	65903.54	2694.99	659.04	69257.57	11748523	4095000	7653523	7283.24	7000.00	0	DOLAR	-	SIETE MILLON SEISCIENTOS CINCUENTA Y TRES MIL QUINIENTOS VEINTE Y TRES 	54	1	5	2	37	15	123	1	46	10	OTROS	0	16	0
367	2023-02-16 14:35:32.937	2023-02-16 14:35:32.937	5-Angel Rios 	2023-02-15	23005IT02000322S	IMPORTACIÓN	EMITIDO	EL MONTO ADELANTO EN SISTEMA ES LA DEUDA A PAGAR POR LA FACTURA DE ROYAL SEGUROS 	-	F.C.L.	TERRESTRE	F.C.L.	504419504	0	65903.54	2694.99	659.04	69257.57	11748523	4095000	7653523	7283.24	7000.00	0	DOLAR	-	SIETE MILLON SEISCIENTOS CINCUENTA Y TRES MIL QUINIENTOS VEINTE Y TRES 	54	1	5	3	37	15	123	1	46	10	OTROS	0	16	0
\.


--
-- TOC entry 3617 (class 0 OID 24686)
-- Dependencies: 235
-- Data for Name: liquidacion_proforma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.liquidacion_proforma (idliquidacion_proforma, fecha_creacion, creado_por, fecha_despacho, contenedor_nro, contenedor_tipo, via_transporte, monto_factura, monto_sin_comprobante, monto_con_comprobante, tasa_cambio_aduana, tasa_cambio_mercado, estado, fk_idtercero_importador, fk_idtercero_despachante, fk_idtercero_colaborador, fk_idtercero_transportadora, fk_idaduana, observacion, tipo_tasa_cambio, tipo_liquidacion, fk_idhonorario_despacho, monto_boleto_despachante, monto_honorario_despachante, fk_idmoneda_cambio) FROM stdin;
4	2021-11-24 22:15:38.855	Digno	2021-11-24	522222	CONSOLIDADA o LCL	TERRESTRE	11000	20000	22000	6850.00	6900.00	EMITIDO	2	2	2	2	2	NINGUNA	DOLAR	IMPORTACION	1	15000	2085000	1
5	2021-11-24 23:01:54.232	Digno	2021-11-24	dfgdfh65165	COMPLETA o FCL	MARITIMO	95000	12000	11000	6850.00	6900.00	EMITIDO	2	2	2	2	2	NINGUNA	DOLAR	EXPORTACION	1	13000	2085000	1
6	2021-11-25 00:21:14.001	Digno	2021-11-25	x	COMPLETA o FCL	TERRESTRE	15000	150000	220000	1120.00	1200.00	EMITIDO	2	2	2	2	2	NINGUNA	REAL	IMPORTACION	1	95000	2085000	3
7	2021-11-25 20:37:39.146	Digno	2021-11-25	x	CONSOLIDADA o LCL	TERRESTRE	15000	50000	11000	6850.00	6900.00	EMITIDO	2	2	2	2	2	NINGUNA	DOLAR	EXPORTACION	1	20000	2085000	1
1	2021-11-22 22:47:06.042	Digno	2021-11-22	dfgsgs	COMPLETA o FCL	MARITIMO	5400	162000	94000	6700.00	6866.00	ANULADO	2	1	2	2	2	NINGUNA	dolar_guarani	IMPORTACION	1	\N	\N	\N
2	2021-11-23 21:54:52.11	Digno	2021-11-23	34534544	COMPLETA o FCL	AEREO	1555000	40000	40000	6700.00	6866.00	ANULADO	2	2	2	2	2	NINGUNA	dolar_guarani	EXPORTACION	1	\N	\N	\N
3	2021-11-23 23:55:07.511	Digno	2021-11-23	324234	CONSOLIDADA o LCL	MARITIMO	15000	10000	10000	6700.00	6866.00	ANULADO	2	2	1	2	2	NINGUNA	dolar_guarani	EXPORTACION	1	10000	2085000	1
8	2021-11-25 20:46:06.322	Digno	2021-11-25	524522	COMPLETA o FCL	MARITIMO	8200	160000	130000	6850.00	6900.00	ANULADO	2	2	2	2	2	este mercaderia viene de china \nposiblemente lleno de coronavirus 	DOLAR	IMPORTACION	1	103000	2085000	1
9	2021-11-27 22:16:05.083	Digno	2021-11-27	524522	CONSOLIDADA o LCL	TERRESTRE	6450	160000	130000	6850.00	6900.00	EMITIDO	2	2	1	2	2	esto es modificado	DOLAR	EXPORTACION	1	103000	2085000	1
10	2021-12-01 00:42:11.585	1-Digno Alfredo	2021-12-01	weqw2342	COMPLETA o FCL	MARITIMO	15000	15000	15000	6850.00	6900.00	EMITIDO	2	2	2	2	2	NINGUNA	DOLAR	EXPORTACION	1	15000	2085000	1
\.


--
-- TOC entry 3618 (class 0 OID 24691)
-- Dependencies: 236
-- Data for Name: moneda_cambio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.moneda_cambio (idmoneda_cambio, moneda, guarani_unidad_aduana, guarani_unidad_mercado, sigla, eliminado) FROM stdin;
2	EURO	7140.00	7250.00	EUR	f
3	REAL	1120.00	1200.00	BRL	f
4	GUARANÍ	6850.00	6800.00	GS	f
1	DOLAR	6813.31	7000.00	USD	f
\.


--
-- TOC entry 3619 (class 0 OID 24697)
-- Dependencies: 237
-- Data for Name: pre_item_liquidacion_final; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pre_item_liquidacion_final (idpre_item_liquidacion_final, orden, fk_idcomprobante_liquidacion, eliminado) FROM stdin;
56	14	21	t
54	12	16	t
55	13	17	t
53	11	14	t
52	10	12	t
51	9	15	t
50	8	9	t
49	7	27	t
20	6	24	t
47	2	4	t
83	6	9	t
68	9	10	t
3	3	4	t
18	18	19	t
21	9	20	t
22	17	19	t
24	21	15	t
5	5	6	t
71	12	15	t
75	15	27	t
74	14	32	t
23	18	20	t
17	16	18	t
25	8	20	t
13	12	14	t
19	16	20	t
15	14	16	t
16	15	17	t
12	11	13	t
14	13	15	t
11	10	12	t
10	9	11	t
9	8	10	t
35	17	20	t
34	16	20	t
36	16	20	t
33	15	21	t
38	16	17	t
37	15	17	t
39	16	15	t
26	17	13	t
58	6	13	t
64	13	15	t
61	10	11	t
63	12	27	t
69	11	27	t
42	16	22	t
40	14	15	t
118	22	21	t
117	21	17	t
1	1	2	t
77	3	4	t
46	18	23	t
44	16	23	t
45	17	22	t
43	15	22	t
41	13	10	t
32	14	16	t
31	12	15	t
30	11	11	t
29	10	10	t
28	9	14	t
27	8	12	t
8	7	9	t
7	5	8	t
82	17	36	t
62	9	28	t
81	16	17	t
65	9	16	t
60	8	30	t
59	7	29	t
128	14	23	t
80	15	16	t
79	14	27	t
76	13	28	t
72	12	27	t
70	11	34	t
67	10	14	t
116	20	17	t
66	9	12	t
115	19	17	t
114	18	16	t
113	17	27	t
112	16	34	t
106	10	14	t
111	15	27	t
109	13	27	t
110	14	37	t
108	12	28	t
107	11	27	t
57	8	9	t
78	7	35	t
127	13	22	t
124	12	27	t
123	11	28	t
105	9	12	t
91	15	27	t
90	14	15	t
89	12	16	t
88	11	15	t
87	10	11	t
86	9	27	t
85	8	14	t
99	15	16	t
98	14	15	t
97	13	27	t
104	8	36	t
101	14	17	t
100	13	16	t
96	12	15	t
95	11	11	t
94	10	10	t
93	9	14	t
92	8	13	t
84	7	12	t
102	6	24	t
126	14	17	t
125	13	16	t
122	10	11	t
130	16	17	t
129	15	16	t
121	9	27	t
120	8	14	t
119	7	12	t
103	6	9	t
48	5	24	t
6	4	8	t
4	3	38	t
2	2	5	t
73	1	3	t
152	22	16	t
153	23	21	t
151	21	42	t
149	19	40	t
150	20	41	t
147	17	27	t
146	16	34	t
148	18	39	t
145	15	27	t
144	14	37	t
142	12	28	t
143	13	27	t
141	11	27	t
140	10	14	t
139	9	12	t
138	8	36	t
137	7	9	t
136	6	24	t
134	4	38	t
132	2	3	t
131	1	2	t
133	3	5	t
154	2	9	t
160	8	42	t
158	6	16	t
159	7	21	t
157	5	43	t
156	4	31	t
155	3	14	t
135	1	8	t
175	1	2	f
161	2	3	f
220	11	11	t
212	10	27	t
211	9	14	t
210	8	12	t
176	7	13	t
167	6	9	t
202	14	27	t
201	13	28	t
200	12	15	t
199	11	11	t
198	10	27	t
197	9	14	t
203	7	13	t
173	14	16	t
174	15	15	t
172	13	27	t
171	12	28	t
170	11	11	t
169	10	27	t
168	9	14	t
166	7	13	t
209	13	21	t
208	12	28	t
207	11	15	t
206	10	11	t
205	9	27	t
204	8	14	t
213	11	11	t
165	7	35	t
177	10	36	t
180	13	18	t
216	14	15	t
226	7	45	t
225	6	24	t
231	12	27	t
232	13	34	t
256	15	21	t
183	12	27	t
255	14	16	t
181	3	4	t
186	14	17	t
185	13	43	t
184	12	16	t
182	11	28	t
179	10	31	t
178	9	14	t
254	13	27	t
253	12	37	t
251	10	28	t
252	11	27	t
250	9	27	t
193	14	21	t
191	12	28	t
190	11	11	t
189	10	27	t
188	9	14	t
187	8	13	t
249	8	14	t
238	7	12	t
237	6	9	t
196	11	17	t
195	10	16	t
194	9	30	t
192	8	29	t
236	13	17	t
235	12	16	t
234	11	15	t
233	10	11	t
230	9	27	t
229	8	14	t
228	7	12	t
227	6	9	t
218	16	23	t
215	14	27	t
217	15	22	t
219	3	24	t
258	4	5	t
257	3	4	t
164	5	8	t
163	4	38	t
162	3	5	t
224	16	17	t
223	15	16	t
222	14	27	t
221	13	32	t
214	12	15	t
244	13	27	t
245	14	22	t
246	15	23	t
248	17	21	t
247	16	16	t
243	12	15	t
242	11	11	t
240	9	14	t
241	10	27	t
239	8	12	t
271	17	17	t
270	16	16	t
269	15	27	t
268	14	34	t
267	13	27	t
266	12	28	t
265	11	14	t
264	10	12	t
262	8	36	t
263	9	9	t
261	7	35	t
260	6	8	t
259	5	38	t
277	8	12	t
281	12	15	t
280	11	11	t
276	7	13	t
283	12	17	t
279	10	29	t
282	11	16	t
278	9	30	t
275	6	9	t
273	4	38	t
274	5	8	t
272	3	5	t
298	17	21	t
297	16	17	t
296	15	16	t
295	14	27	t
294	13	28	t
304	18	17	t
303	17	21	t
301	15	27	t
302	16	16	t
293	12	27	t
290	9	12	t
299	13	44	t
292	11	44	t
291	10	14	t
300	14	28	t
289	8	9	t
287	6	8	t
288	7	35	t
286	5	38	t
284	3	4	t
285	4	5	t
315	13	17	t
314	12	16	t
312	10	11	t
311	9	27	t
313	11	15	t
310	8	14	t
309	7	12	t
325	16	21	t
324	15	16	t
323	14	15	t
322	13	27	t
320	11	11	t
321	12	28	t
316	7	13	t
319	10	27	t
318	9	14	t
317	8	12	t
308	6	9	t
307	5	8	t
305	3	5	t
306	4	38	t
337	14	16	t
338	15	17	t
336	13	15	t
335	12	11	t
334	11	27	t
333	10	14	t
332	9	12	t
331	8	13	t
330	7	9	t
326	3	4	t
412	13	16	t
411	12	31	t
410	11	14	t
346	13	17	t
345	12	16	t
344	11	27	t
343	10	28	t
351	14	17	t
350	13	16	t
349	12	27	t
348	11	28	t
347	10	27	t
342	9	14	t
341	8	12	t
340	7	9	t
339	6	35	t
361	15	17	t
360	14	17	t
359	13	16	t
358	12	15	t
356	10	27	t
357	11	11	t
355	9	14	t
354	8	12	t
353	7	13	t
352	6	9	t
373	17	17	t
372	16	16	t
370	14	22	t
371	15	23	t
369	13	27	t
368	12	28	t
367	11	11	t
366	10	27	t
364	8	12	t
365	9	14	t
363	7	9	t
362	6	35	t
380	12	29	t
387	18	21	t
388	19	21	t
386	17	21	t
385	16	16	t
384	15	27	t
383	14	28	t
382	13	47	t
381	12	46	t
379	11	11	t
378	10	27	t
377	9	14	t
376	8	12	t
375	7	13	t
374	6	9	t
401	18	17	t
399	16	23	t
400	17	16	t
398	15	48	t
397	14	27	t
396	13	28	t
395	12	11	t
394	11	27	t
393	10	14	t
392	9	12	t
391	8	13	t
390	7	9	t
389	6	35	t
329	5	8	t
327	3	5	t
328	4	38	t
413	14	17	t
409	10	27	t
408	9	28	t
407	8	12	t
417	11	14	t
435	20	49	t
434	19	27	t
433	18	44	t
432	17	17	t
416	10	15	t
431	16	17	t
422	16	17	t
421	15	21	t
420	14	16	t
419	13	11	t
418	12	27	t
414	8	9	t
415	9	12	t
430	15	16	t
429	14	14	t
427	12	28	t
426	11	27	t
428	13	27	t
425	10	34	t
424	9	12	t
423	8	9	t
406	7	35	t
440	12	17	t
439	11	16	t
437	9	29	t
438	10	30	t
436	8	12	t
441	7	9	t
448	13	17	t
447	12	17	t
446	11	16	t
445	10	14	t
444	9	12	t
443	8	9	t
442	7	26	t
457	15	17	t
456	14	16	t
455	13	50	t
454	12	11	t
453	11	27	t
452	10	14	t
451	9	12	t
450	8	13	t
449	7	9	t
469	18	16	t
468	17	17	t
467	16	17	t
466	15	41	t
465	14	14	t
464	13	27	t
463	12	34	t
462	11	27	t
461	10	28	t
460	9	12	t
459	8	9	t
458	7	35	t
402	3	4	t
480	16	17	t
479	15	16	t
478	14	15	t
477	13	27	t
476	12	28	t
474	10	27	t
475	11	11	t
473	9	14	t
472	8	12	t
471	7	13	t
470	6	9	t
404	4	38	t
405	5	8	t
403	3	5	t
481	3	4	f
482	4	5	f
483	5	38	f
484	6	8	f
485	7	9	f
494	16	17	t
495	16	17	t
493	15	16	t
491	13	27	t
492	14	15	t
490	12	28	t
489	11	11	t
488	10	27	t
487	9	14	t
486	8	12	t
496	8	13	f
497	9	12	f
498	10	14	f
499	11	27	f
500	12	11	f
501	13	28	f
502	14	27	f
503	15	15	f
504	16	16	f
505	17	17	f
506	3	33	f
507	7	35	f
508	18	17	f
509	19	17	f
\.


--
-- TOC entry 3620 (class 0 OID 24701)
-- Dependencies: 238
-- Data for Name: pre_liquidacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pre_liquidacion (idpre_liquidacion, fecha_creado, creado_por, fecha_factura, fecha_llegada, numero_factura, numero_invoice, monto_factura, descripcion, observacion, tipo_liquidacion, estado, fk_idtercero_importador, fk_idtercero_exportador) FROM stdin;
1	2022-08-09 10:23:30.709	5-Angel Rios 	2022-08-09	2022-08-09	2222	2222	2200000.00		NINGUNA	IMPORTACIÓN	EMITIDO	15	74
\.


--
-- TOC entry 3621 (class 0 OID 24706)
-- Dependencies: 239
-- Data for Name: recibo_pago_tercero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recibo_pago_tercero (idrecibo_pago_tercero, fecha_emision, descripcion, monto_recibo_pago, monto_letra, estado, fk_idtercero, fk_idusuario) FROM stdin;
1	2022-02-17 13:41:57.871	DESPACHO ADUANERO PARA: DIGNO ALFREDO TALAVERA ROJAS	900000	NOVECIENTOS MIL 	EMITIDO	2	5
\.


--
-- TOC entry 3622 (class 0 OID 24711)
-- Dependencies: 240
-- Data for Name: regimen; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.regimen (idregimen, nombre, sigla, descripcion, eliminado) FROM stdin;
1	SIN DEFINIR	SIN	ninguna	f
2	Importación a consumo con Documento de Transporte	IC04	Este régimen aduanero es utilizado para las mercaderías que\n    ingresan a nuestro país en forma definitiva para su uso, \nconsumo o    comercialización. Dependiendo del tipo de \nmercadería, se realizarán los    controles para el pago \nde los tributos. Destinación relacionada IFC1: Ingreso \nde Fracciones    Complementarias – para los casos la \nmercadería ingresa en Fracciones.	f
3	EXPORTACION A CONSUMO	EC01	NINGUNA	f
4	DESPACHO SIMPLIFICADO    DE EXPORTACIONES MENORES	DSEM	Es el régimen aduanero que está destinado a las operaciones de    exportaciones de menor cuantía y las exportaciones ocasionales hasta un valor    FOB de $2500, se puede realizar una vez por mes y por cada persona. Deberá    contar con la intervención del despachante de Aduanas.	f
5	RE-IMPORT DE    EXPORTACIÓN TEMPORAL. PERFECCIONA PASIVO	IC02	Es el régimen aduanero utilizado para volver a ingresar al país    una mercadería que fuera objeto de una exportación en forma temporaria para    la reparación o modificación de la misma. Destinación relacionada es la    Exportación Temporal con perfeccionamiento pasivo. ET02	f
6	REGULARIZACIÓN LEY    608/95	IC03	nn	f
7	IMPORTACIÓN DIRECTO A    CONSUMO C/ DOC. DE TRANSPORTE	IC05	Régimen aduanero utilizado para las mercaderías que ingresan a    nuestro país y necesita un tratamiento o despacho inmediato por ser de    naturaleza peligrosa, perecederas, medicamentos, etc.	f
8	IMPORTACION DEPOSITO    ADUANERO	IC06	Es el régimen aduanero utilizado para el control del stock del    almacenamiento de mercaderías que ingresaron sobre el régimen de traslado a    depósito particular fiscalizado (IDA2 actualmente fuera    de vigencia)	f
9	\tIMPORTACIÓN A CONSUMO    DE IMP.TEMPORAL S/ TRANSFORMACIÓN	IC07	Régimen aduanero utilizado para la Nacionalización de las    mercaderías que ingresaron en forma temporal y la mercadería no sufrió    modificación ni transformación durante la permanencia temporal o uso del    mismo. Regímenes relacionados son: importación temporaria sin transformación IT04y ZF3TImportación de Zona Franca Maquila sin Trasformación	f
10	\tIMPORTACIÓN A CONSUMO    DE IMP. TEMPORAL C/ TRANSFORMACIÓN	IC08	Régimen aduanero destinado a la Nacionalización de las    mercaderías que ingresaron en forma temporal para la transformación o    reparación de la mercadería en el país del cual se compró la mercadería. Está    relacionado con la Importación Temporaria para Transformación con Documento    de transporteIT14	f
11	IMPORTACIÓN A CONSUMO    DESDE DEPOSITO ADUANERO	IC09	Régimen aduanero por el cual se realiza la nacionalización de    las mercaderías que ingresaron bajo el régimen de depósito aduanero, pudiendo    nacionalizar el total de la mercadería ingresada o solo una parte de la    misma. Relacionada al ingreso a depósito aduanero con documento de transporte    IDA3 y Traslado entre    depósitos Aduaneros TDA3	f
12	NACIONALIZACIÓN    MAQUILA / MAT PRIMA / INSUMO / DESPERDICIOS	IC12	Es el régimen aduanero que permite nacionalizar las mercaderías    importadas bajo los regímenes de importación temporal de maquila con    transformación, sin transformación y para la Exportación de Maquila.    Relacionadas a las destinaciones: IT02 Import. Temporal Maquila con transformación, EM01 Exportación Maquila y ET03 Exportación temporal Maquila    s/Transformación.	f
13	NACIONALIZACIÓN DE    DEPÓSITO DE OEC	IC19	Es el régimen utilizado para la Nacionalización de las    mercaderías de un operador económico calificado. La destinación madre es TDA5 (Traslado a Deposito del    Operador Económico Calificado). Esta destinación es utilizada por las    Empresas y Despachantes que hayan sido Certificadas por la DNA para la    utilización de las operaciones bajo la figura OEC. 	f
14	\tIMPORTACIÓN A CONSUMO    DE APROVISIONAMIENTO A BORDO	ICAB	Esta destinación es utilizada para provisión y abastecimiento de    los medios de transportes internacionales que están en tránsito en nuestro    país. La misma debe estar relacionada a un depósito de aprovisionamiento    abordo.  La destinación previa sería    depósito de aprovisionamiento abordo DAAB	f
15	NACIONALIZACION DE    INGR. A DEP P/MANT. Y REPARAC. DE AERONAVES	ICMA	Es el régimen aduanero que permite la importación al país de    partes, piezas y materiales aeronáuticos destinados para utilizar en el    mantenimiento o reparación de aeronaves de transporte internacional en zona    primaria. Régimen asociado IMAR    importación de materiales aeronáuticos reemplazados.	f
16	IMPORTACIÓN    DIPLOMÁTICA CON DOCUMENTO DE TRANSPORTE	ID04	Este es el régimen de franquicia diplomática aplicable a las    mercaderías que ingresan al territorio aduanero destinadas a representantes    diplomáticos y consulares extranjeros reconocidos y acreditados en el país.	f
17	\tIMPORTACIÓN    DIPLOMÁTICA DESDE DEPÓSITO ADUANERO	ID09	Este Régimen está relacionada a la nacionalización de las    mercaderías sometidas sometido a la franquicia Diplomática se relaciona a la    destinación previa de traslado a deposito aduanero TDA3 e ingreso a deposito aduanero IDA3	f
18	NACIONALIZACIÓN    LEASING	ICL1	Régimen destinado a la nacionalización de las mercaderías que    ingresaron con una admisión temporaria de la ley 1295/98 de locación,    arrendamiento o leasing financiero o mercantil. Esta relaciona a la operación    madre IML1importación    temporaria leasing.	f
19	IMPORTACIÓN MENOR A    CONSUMO CON DOCUMENTO DE TRANSPORTE	IM04	Este Régimen aduanero está destinado para la nacionalización de    mercaderías con valor FOB hasta 2.500 Dos mil quinientos dólares americanos.    Habilitado para la atención, registración y trámites como documento aduanero    para la nacionalización de pequeñas importaciones de carácter comercial.	f
20	\tIMPORTACIÓN MENOR S/    DOC DE TRANS. C/ CÓDIGO DE BARRAS	IM05	Este régimen aduanero destinada a una importación de menor    cuantía, hasta USS 2.500 no posee documento de transporte. El código de    barras es un procedimiento que se realiza en zona secundaria en el país, se    expide en forma conjunta entre las aduanas de Brasil y Paraguay. Con el    código de barras el medio de transporte hace el ingreso a zona primaria lado    paraguayo.	f
21	\tDESPACHO SIMPLIFICADO    PARA IMPORTACIONES MENORES	IM06	Régimen aduanero que está establecido para las pequeñas    importaciones, para las operaciones hasta USS 2500 (dos mil quinientos    dólares americanos) y acumulable hasta USS 10.000 (Diez mil dólares    americanos) por mes.	f
22	EXPORT A CONSUMO DE    EXP. C/ RESERVA RETORNO C/ TRANF.	ER04	Es el régimen aduanero utilizado para las exportaciones    definitivas de las mercaderías que retornaron para su reparación. Cuando la    exportación a consignación haya sido enviada con reserva de retorno y no se    ajustaron las condiciones técnicas entre el comprador y el vendedor y son    devueltas para la reparación, esta se exporta definitivamente una vez    reparada, la declaración asociada es ERR1	f
57	\tIMPORTACIÓN MAQUILA    VIRTUAL SIN TRASFORMACIÓN	IVM1	nn	f
58	SUMINISTROS Y    PROVISIONES AEREO	SPA1	nn	f
23	IMPORT A CONSUMO DE    EXP. C/ RESERVA RETORNO C/ TRANF.	IR10	Este régimen aduanero se utiliza para mercaderías que fueron    exportadas para su transformación o modificación y con retorno a nuestro país    y que las mercaderías hayan sido transformadas, está relacionado con las    destinaciones de exportaciones que impliquen reserva de retorno.ERR1	f
24	REMESA EXPRESA -    DOCUMENTO	IRE1	Este régimen aduanero de remesa expresase aplica al envío de    correspondencia, documentos vía aérea, libre de tributos y está relacionado a    una transferencia electrónica de remesa expresa (TERE). El despacho se genera a través de DSWeb y solo intervine Resguardo- DNA	f
25	REMESA EXPRESA - DE 0    A 100 USD	IRE2	Destinación aplicada a los despachos simplificados de remesa    expresa hasta USS 100 cien dólares americanos. Destinada a mercaderías vía    aéreas y sujeto a pago tributo aduanero, se relaciona con la trasmisión    electrónica de remesa expresa (TERE2) se declara en DSWeb, ingreso vía    aérea y sujeto a controles SBR.	f
26	REMESA EXPRESA - DE    100 A 1.000 USD	IRE3	es el régimen aduanero utilizado para los despachos    simplificados con valor desde USS 100 hasta USS 1.000 amparadas como remesa    expresa, está sujeto a pago de tributos y relacionado con la Transferencia    Electrónica de Remesa Expresa.	f
27	REMESA EXPRESA -    HASTA   1.000  USD	IRE4	Régimen simplificado de remesa expresa con intervención del    despachante de aduanas para las mercaderías hasta cuyo valor es de USS1000,    las mismas están afectadas al pago de tributos y sujeto a control total con    gestión SBR	f
28	INGRESO A CONSUMO    DEFINITIVO	TLI3	Régimen utilizado para realizar la importación de las    mercaderías ingresadas a tienda libre y que se desean nacionalizar para su    uso o consumo, debe cumplir con las formalidades exigibles y el pago de    tributos aduanera a la importación. Habilitada solo para las aduanas de    Aeropuerto Silvio Pettirossi y Guaraní.     Está relacionado a ingreso a depósito de tienda libre TLI1	f
29	IMPORTACIÓN DE ZONA    FRANCA	ZF2I	Régimen aduanero que es utilizado para las importaciones de las    mercaderías que ingresaron a Zona Franca estarán sujetas a todos los tributos    aduaneros, está relacionada declaración de ingreso a Zona FrancaZF01	f
30	EXPORTACION A CONSUMO    CON ENVASE	EC15	nn	f
31	IMPORTACION    ANTICIPADA OEA	IC20	Este régimen aduanero es utilizado por el Operador Económico    Autorizado, es la modalidad que permite que la mercadería procedente de    exterior sea declarada antes de la llegada de la misma al territorio nacional    y sean despachadas en zona primaria (Aduana) o en su domicilio. Está    relacionado con la transmisión anticipada de la carga aérea TEMA.	f
32	IMPORTACION DE    FRACCIONES COMPLEMENTARIAS	IFC1	Régimen aduanero relacionado a la cantidad y naturaleza de las    mercaderías que se envían en forma fraccionada, en los distintos medios de    transportes y están amparadas por una misma operación comercial en un mismo    conocimiento de embarque y las mercaderías son expedidas en forma    fraccionada. Está relacionado con una importación a consumo con documento de    transporte IC04.	f
33	DESPACHO MENOR 2.0	IM20	Es el régimen aduanero de Despachos de menor cuantía, donde el    valor FOB es hasta U$S 2.500 por cada Importación, acumulable hasta U$S    10.000 por mes por importador en su versión Móvil. Para las plataformas IO y    ANDROID. Se relaciona a la Transmisión Electrónica de Remesa Expresa TERE    2.0	f
34	\tIMPORTACION DE    ASISTENCIA Y SALVAMENTO	IS01	El régimen aduanero es aplicable a las mercaderías que ingresen    o egresen del territorio aduanero, destinada a la ayuda de poblaciones    víctimas de una catástrofe. Determinan trámites simplificados que faciliten    la gestión. El Despachante de Aduanas tendrá intervención obligatoria.	f
35	INGRESO A ZONA FRANCA    MERCADERIAS NAC.	NZF1	nn	f
36	SISTEMA DE GESTION DE    TRAFICO VECINAL FRONTERIZO	SGVTF	Es el Régimen Aduanero simplificado para la importación de    productos que ingresan al país y son para las personas que residen en las    ciudades fronterizas de nuestro país, hasta un valor máximo de U$S 150 por    mes calendario. Será utilizado solo por las personas registradas para el    efecto y deben residir en la zona de frontera (hasta 20 km paralelos a la    línea divisoria internacional) Su Declaración se realiza en SGTVFWeb	f
37	RE IMPORTACION DE    EXPORTACION DEFINITIVA RECHAZADA	IC13	nn	f
38	APROVISIONAMIENTO A    BORDO	AB01	Régimen Aduanero destinado a las provisiones y suministros a    bordo del medio de transporte como ser: combustible, repuestos, utensilios,    alimentos y demás mercaderías que se encuentran a bordo del mismo para el    consumo de la tripulación y pasajeros, la misma debe estar autorizada por el    Director de Procedimiento Aduanero y tiene un plazo de 30 días.  Está relacionada con un DAAB    e ICAAB	f
39	\tDEPÓSITO DE    APROVISIONAMIENTO ABORDO	DAAB	Régimen destinado al ingreso de las mercaderías, como ser:    combustibles y aceites lubricantes que se encuentran almacenadas en los    depósitos especiales habilitados para el suministro y abastecimiento de los    medios de transporte extranjero para el transporte internacional.    Destinaciones relacionadas ICAB y AB01	f
40	REEXPORTACION MONTADA	ECMA	Es el régimen aduanero que se reconoce como tal cuando: las    piezas, partes, repuestos y los materiales aeronáuticos importados, son    incorporados o montados como mantenimiento o reparación en la aeronave de    servicio internacional.	f
41	RE-IMPORT DE    EXPORTACIÓN TEMPORAL	IC01	nn	f
42	EXPORTACIÓN A CONSUMO    C/DIT SIN TRANSFORMACIÓN	EC02 	Es el Régimen aduanero utilizado para las mercaderías que    ingresaron al país en forma temporal, para ser utilizados y no sufrieron    ninguna modificación. Destinación relacionada previa es Importación    Temporaria sin Transformación, con documento de transporte (IT04)	f
43	EXPORTACIÓN A CONSUMO    C/DIT CON TRANSFORMACIÓN	EC03 	Es el régimen aduanero utilizado para exportar las mercaderías    que ingresaron al país temporalmente	f
44	REEXPORTACION    AERONAVE	ERMA	nn	f
45	EXPORTACIÓN     MAQUILA VIRTUAL	EVM1	nn\n	f
46	IMPORTACIÓN    TEMPORARIA - MAQUILA CON TRANSFORMACIÓN.	IT02	nn	f
47	\tIMPORTACIÓN    TEMPORARIA - MAQUILA SIN TRANSFORMACIÓN.	IT03	nn	f
48	IMPORTACIÓN    TEMPORARIA S/TRANSFORMACIÓN C/DOC. TRANSPORT.	IT04	nn	f
49	\tIMPORTACIÓN    TEMPORARIA P/TRANSFORM.C/DOC.DE TRANSPORTE	IT14	nn	f
50	EXPORTACIÓN MAQUILA	EM01	nn	f
51	EXPORTACIÓN TEMPORAL	ET01	nn	f
52	EXPORTACIÓN TEMPORAL.    PERFECCIONA PASIVO	ET02	nn	f
53	INGR. A PEDOSITO    P/MANT. Y REPARACION DE AERONAVES	IDMA	nn	f
54	IMPORTACION DE    MATERIALES AERONAUTICOS REEMPLAZADOS	IMAR	nn	f
55	IMP.TEMP. MAQUILA    S/TRANSFORM. SOBRE DEP.ALMAC	IT06	nn	f
56	\tIMP.TEMP. MAQUILA    C/TRANSFORM. SOBRE DEP.ALMAC.	IT16	nn	f
59	IMPORTACIÓN    TEMPORARIA LEASING	IML1	nn	f
60	EXPORTACIÓN DE    CONSUMO LEASING	ECL1	nn	f
61	EXPORTACIÓN C/    RESERVA DE RETORNO	ERR1	nn	f
62	IMPORTACIÓN A CONSUMO    DE EXP. C/ RESERVA DE RETORNO	IC10	nn	f
63	DECLARACIÓN DE    INGRESO A ZONA FRANCA	ZF01	nn	f
64	EXPORTACIÓN DE ZONA    FRANCA	ZF2E	nn	f
65	IMP TEMP DE ZONA    FRANCA - MAQUILA CON TRANSFORMACIÓN.	ZF2M	nn	f
66	IMP TEMP DE ZONA    FRANCA - MAQUILA SIN TRANSFORMACIÓN.	ZF3M	nn	f
67	IMP TEMPORAL DESDE    ZONA FRANCA - CON TRANSFORMACIÓN.	ZF2T	nn	f
68	\tIMP TEMPORAL DESDE    ZONA FRANCA - SIN TRANSFORMACIÓN.	ZF3T	nn	f
69	INGRESO A DEPOSITO    ADUANERO C/ DOCUMENTO DE TRANSPORTE	IDA3	nn	f
70	REEXPORTACIÓN DESDE    DEPÓSITO ADUANERO	EC09	nn	f
71	\tTRASLADO ENTRE    DEPÓSITOS ADUANEROS	TDA3	nn	f
72	TRASLADO DE DEPOSITO    DE OEC	TDA5	nn	f
73	INGRESO A DEPOSITO DE    TIENDA LIBRE	TLI1	Es la destinación dadas a las mercaderías que ingresan bajo el    régimen de Tienda libre de impuestos en un depósito habilitado para el    efecto, el mismo tiene un plazo de almacenamiento de 180 días a partir de la    fecha de autorización del régimen. Se relaciona con la Destinación TLI3	f
74	REEXPORTACION TIENDA    LIBRE	TLI2	Cuando las mercaderías ingresadas vuelven a ser remitidas al    exterior por quien tenga la disponibilidad jurídica de las mercaderías. La    destinación relacionada es TLI3.	f
75	COMERCIALIZACION EN    TIENDA LIBRE	TLI4	Régimen utilizado cuando las mercaderías importadas y    almacenadas en el depósito aduanero, sean destinadas para su comercialización    en el establecimiento habilitado en la zona primaria, la operación estará    exenta del tributo aduanero. Su destinación relacionada es TLI1.	f
76	\tIMPORTACION MANUAL    MODALIDAD LEASING	IML2	nn	f
77	IMPORTACION    TEMPORARIA DE LEASING	ITL1	nn	f
78	MOVIMIENTO DE ZONA    FRANCA	MZF1	nn	f
79	MOVIMIENTO DE ZONA    FRANCA	XZF1	nn	f
80	-\t-	-	-	f
\.


--
-- TOC entry 3623 (class 0 OID 24717)
-- Dependencies: 241
-- Data for Name: saldo_credito_tercero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.saldo_credito_tercero (idsaldo_credito_tercero, fecha_emision, descripcion, monto_saldo_credito, monto_letra, estado, fk_idtercero, fk_idusuario) FROM stdin;
1	2022-01-18 14:18:24.425	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	1	1
2	2022-01-18 14:18:27.534	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	2	1
3	2022-01-18 14:18:30.491	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	3	1
4	2022-01-18 14:18:33.246	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	4	1
5	2022-01-18 14:18:35.922	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	5	1
6	2022-01-18 14:18:39.158	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	6	1
7	2022-01-18 14:18:42.433	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	7	1
8	2022-01-18 14:18:45.104	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	8	1
9	2022-01-18 14:18:48.005	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	9	1
10	2022-01-18 14:18:50.741	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	10	1
11	2022-01-18 14:18:53.397	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	11	1
12	2022-01-18 14:18:56.065	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	12	1
13	2022-01-18 14:18:59.363	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	13	1
14	2022-01-18 14:19:02.64	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	14	1
15	2022-01-18 14:19:05.609	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	15	1
16	2022-01-18 14:19:08.314	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	16	1
17	2022-01-18 14:19:12.081	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	17	1
18	2022-01-18 14:19:15.402	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	18	1
19	2022-01-18 14:19:18.215	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	19	1
20	2022-01-18 14:19:21.548	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	20	1
21	2022-02-17 13:28:02.219	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	21	5
22	2022-02-17 13:41:59.289	SALDO DEL CIERRE ANTERIOR	64310	SESENTA Y CUATRO MIL TRECIENTOS DIEZ 	EMITIDO	2	5
23	2022-04-27 12:42:14	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	22	5
24	2022-04-27 12:49:49.737	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	23	5
25	2022-04-27 13:57:32.065	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	24	5
26	2022-04-27 15:19:00.843	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	25	5
27	2022-04-27 15:20:01.619	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	26	5
28	2022-04-28 13:49:27.123	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	27	5
29	2022-05-03 15:33:54.565	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	28	5
30	2022-05-04 12:45:08.852	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	29	5
31	2022-05-06 11:38:41.632	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	30	5
32	2022-05-06 12:14:08.104	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	31	5
33	2022-05-06 12:14:45.935	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	32	5
34	2022-05-06 13:59:34.347	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	33	5
35	2022-05-06 14:00:05.935	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	34	5
36	2022-05-06 14:01:02.769	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	35	5
37	2022-05-10 15:31:57.605	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	36	5
38	2022-05-12 14:35:15.907	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	37	5
39	2022-05-17 11:56:27.831	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	38	5
40	2022-05-18 09:16:17.14	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	39	5
41	2022-05-18 09:40:39.174	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	40	5
42	2022-05-24 09:38:20.398	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	41	5
43	2022-05-24 11:51:22.616	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	42	5
44	2022-05-26 10:53:05.458	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	43	5
45	2022-05-26 11:31:43.579	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	44	5
46	2022-05-26 12:17:20.378	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	45	5
47	2022-05-26 12:17:59.244	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	46	5
48	2022-05-30 10:32:42.84	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	47	5
49	2022-05-31 09:30:52.851	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	48	5
50	2022-05-31 10:32:55.23	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	49	5
51	2022-06-02 14:22:43.566	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	50	5
52	2022-06-02 14:24:23.08	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	51	5
53	2022-06-02 16:31:37.066	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	52	5
54	2022-06-06 08:47:56.718	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	53	5
55	2022-06-06 16:02:59.75	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	54	5
56	2022-06-08 14:25:43.54	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	55	5
57	2022-06-08 15:59:50.702	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	56	5
58	2022-06-08 16:00:25.495	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	57	5
59	2022-06-08 16:48:47.262	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	58	5
60	2022-06-14 13:01:59.756	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	59	5
61	2022-06-15 13:35:54.332	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	60	5
62	2022-06-17 09:19:44.846	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	61	5
63	2022-06-17 09:20:31.209	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	62	5
64	2022-06-20 14:10:09.52	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	63	5
65	2022-06-29 09:37:38.78	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	64	5
66	2022-06-29 12:59:54.713	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	65	5
67	2022-07-04 14:14:56.285	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	66	5
68	2022-07-04 14:54:04.543	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	67	5
69	2022-07-05 14:45:10.809	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	68	5
70	2022-07-05 14:45:44.71	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	69	5
71	2022-07-06 12:35:32.118	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	70	5
72	2022-07-06 13:32:41.557	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	71	5
73	2022-07-06 14:17:24.183	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	72	5
74	2022-07-06 15:26:51.829	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	73	5
75	2022-07-08 11:44:10.6	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	74	5
76	2022-07-08 15:56:15.746	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	75	5
77	2022-07-13 10:14:53.76	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	76	5
78	2022-07-13 14:48:31.362	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	77	5
79	2022-07-14 12:08:01.549	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	78	5
80	2022-07-19 12:47:35.677	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	79	5
81	2022-07-29 15:12:22.86	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	80	5
82	2022-08-01 15:38:21.399	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	81	5
83	2022-08-05 14:34:52.354	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	82	5
84	2022-08-09 08:49:49.412	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	83	5
85	2022-08-24 09:24:59.15	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	84	5
86	2022-08-26 16:34:03.607	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	85	5
87	2022-09-06 13:59:12.104	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	86	5
88	2022-09-07 16:06:02.688	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	87	5
89	2022-09-07 16:25:44.089	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	88	5
90	2022-09-12 11:01:43.857	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	89	5
91	2022-09-12 15:13:51.758	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	90	5
92	2022-09-29 10:58:24.255	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	91	5
93	2022-10-05 11:29:12.165	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	92	5
94	2022-10-10 12:39:42.178	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	93	5
95	2022-10-11 11:23:16.359	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	94	5
96	2022-10-11 11:23:51.975	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	95	5
97	2022-10-17 10:28:28.056	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	96	5
98	2022-10-17 10:29:12.653	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	97	5
99	2022-10-24 10:46:11.41	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	98	5
100	2022-10-28 15:31:19.009	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	99	5
101	2022-10-28 15:32:12.78	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	100	5
102	2022-11-01 08:35:50.449	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	101	5
103	2022-11-01 08:47:34.328	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	102	5
104	2022-11-10 08:42:26.651	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	103	5
105	2022-11-11 12:59:07.518	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	104	5
106	2022-11-25 12:57:23.927	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	105	5
107	2022-11-29 12:08:12.692	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	106	5
108	2022-11-29 12:09:00.235	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	107	5
109	2022-12-05 11:02:48.247	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	108	5
110	2022-12-12 14:06:28.077	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	109	5
111	2022-12-12 14:07:05.49	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	110	5
112	2022-12-13 09:43:25.027	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	111	5
113	2022-12-13 09:43:48.232	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	112	5
114	2022-12-14 13:11:02.055	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	113	5
115	2023-01-03 14:08:24.598	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	114	5
116	2023-01-04 14:11:16.684	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	115	5
117	2023-01-18 10:56:31.908	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	116	5
118	2023-01-18 14:53:34.489	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	117	5
119	2023-01-24 14:29:58.939	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	118	5
120	2023-01-26 11:45:17.223	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	119	5
121	2023-02-08 11:41:25.948	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	120	5
122	2023-02-08 15:09:20.672	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	121	5
123	2023-02-14 13:26:55.32	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	122	5
124	2023-02-16 14:04:04.587	CREDITO DE CLIENTE DE INICIO	0	CERO 	EMITIDO	123	5
\.


--
-- TOC entry 3624 (class 0 OID 24722)
-- Dependencies: 242
-- Data for Name: tercero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tercero (idtercero, fecha_creacion, creado_por, nombre, ruc, telefono, direccion, representante_nombre, representante_cedula, importador, despachante, colaborador, proveedor, transportadora, fk_idtercero_pais, fk_idtercero_ciudad, saldo_credito, fk_idtercero_rubro, exportador, eliminado) FROM stdin;
2	2022-01-18 16:31:24.863	Digno	DIGNO ALFREDO TALAVERA ROJAS	4650586-5	0986799285	Pdte Franco Area 5 M43 L18	DIGNO ALFREDO TALAVERA ROJAS	4650586	t	f	f	f	f	2	2	-64310	1	f	f
21	2022-02-17 13:28:00.558	Digno	TEEJET TECHNOLOGIES 	X	X	X	X	X	t	f	f	f	f	3	10	0	1	f	f
4	2022-04-28 16:28:34.393	Digno	MERCOFER & LUBRIBRAS SACIS	80063998-7	00	AVDA ALBANO BIRNFIELDT, BARRIO SINUELO	SAURO LEITE	SD	t	f	f	f	f	2	4	0	5	f	f
28	2022-05-03 15:33:52.856	Digno	KINGSPAN ISOESTE CONSTRUTIVOS ISOTERMICOS S/A	-	-	-	-	-	t	f	f	f	f	1	10	0	5	f	f
33	2022-05-06 13:59:32.131	Digno	PULVEMAQ SOCIEDAD ANONIMA 	800941756	-	-	ELISE FRANTZ 	-	f	f	f	f	f	2	4	0	2	f	f
7	2022-03-21 13:13:31.455	Digno	CANTERA TRICEMAR S.A.	80027223-4	0673-220384	CAMINO A SAN VICENTE 	SN	XX	t	f	f	f	f	2	4	0	2	f	f
35	2022-05-06 14:01:01.917	Digno	ATRIA AGRO TECNICA EIRELI - EPP	-	-	-	-	-	t	f	f	f	f	1	10	0	3	f	f
20	2021-12-09 12:08:15.517	Digno	DROGUERIA DEL PARAGUAY S.A.	80025218-7	SD	CAMPOS CERVERA 6313 Y R.I. 14	SD	SD	t	f	f	f	f	2	2	-178113489	1	f	f
40	2022-05-18 09:40:38.367	Angel Rios 	TAIAN DONGTAI MACHINE MANUFACTURING CO.,LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
36	2022-05-10 15:31:56.57	Digno	ZANETTE IMPORTACAO E EXPORTACAO LTDA	-	-	-	-	-	t	f	f	f	f	1	10	0	4	f	f
29	2022-05-04 12:45:07.896	Digno	GERALDO N. RECKTENWALD & CIA LTDA	-	-	-	-	-	t	f	f	f	f	1	10	0	2	f	f
9	2021-12-09 11:38:10.354	Digno	INDUSTRIA METAL MUEBLES S.A.	80070490-8	0673-221086	BARRIO SINUELO	SN	XX	t	f	f	f	f	2	4	0	1	f	f
51	2022-06-02 14:24:22.025	Angel Rios 	SHENZHEN HE SHAN ELECTRONIC TECHNOLOGY GP., LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
16	2021-12-09 11:47:05.397	Digno	OLAZAR MENDIETA ALBERTO MATHIAS	39785661	0993285386	PCC	MATHIAS OLAZAR	3978566	f	t	f	f	f	2	8	0	1	f	f
17	2021-12-09 11:55:52.602	Digno	MEZA LUGO FRANCISCO JAVIER	37655019	0983382743	ASUNCION	SD	SD	f	t	f	f	f	2	2	0	1	f	f
46	2022-05-26 12:17:58.348	Angel Rios 	ZANETTE IMPORTACAO E EXPORTACAO LTDA	-	-	-	-	-	f	f	f	f	f	1	10	-34716542	5	t	f
19	2021-12-17 14:46:41.171	Digno	HEIMDAL GROUP CORPORATION	XX	XX	PANAMA	SD	SD	f	f	f	t	f	2	9	0	1	f	f
18	2021-12-17 14:46:55.845	Digno	DIEGO ARSENIO ORUÉ	4123340-9	0981-615389	ASUNCION	SD	SD	f	t	f	f	f	2	2	0	1	f	f
14	2021-12-17 14:47:39.956	Digno	BORILLE Y PERTILLE S.A.	80102905-8	SD	HERNANDARIAS	DAVID	SD	t	f	f	f	f	2	8	0	1	f	f
13	2021-12-17 14:47:53.588	Digno	AMERICAN WATCH S.R.L.	80062488-2	061-510004	AVDA. ADRIÁN JARA Y 3223, EDIF. JEBAI CENTER	JORGE	SD	t	f	f	f	f	2	1	0	1	f	f
30	2022-05-06 11:38:40.794	Digno	INDUSTRIAS SICA S.A.I.C.	-	-	-	-	-	t	f	f	f	f	1	10	0	4	f	f
52	2022-06-02 16:31:36.308	Angel Rios 	HANA INTERNATIONAL HARDWARE MANUFACTURE CO., LIMITED	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
10	2021-12-17 14:49:07.615	Digno	R6 S.A.	80079808-2	0983-632868	RUTA VI - PARQUE INDUSTRIAL 	SN	XX	t	f	f	f	f	2	4	0	1	f	f
11	2022-03-21 13:15:05.332	Digno	REPUESTOS AGRICOLAS ZOTTI S.A.	800	SN	RUTA VI	LAIS	XX	t	f	f	f	f	2	4	0	4	f	f
37	2022-05-12 14:35:14.502	Digno	JUSSA TECIDOS LTDA	-	-	-	-	-	t	f	f	f	f	1	10	0	5	f	f
31	2022-05-06 12:14:07.087	Digno	FJ DYNAMICS INTERNATIONAL LIMITED	-	-	-	-	-	f	f	f	f	f	1	10	0	5	f	f
32	2022-05-06 12:14:45.079	Digno	FJ DINAMICS INTERNATIONAL LIMITED	-	-	-	-	-	t	f	f	f	f	1	10	0	5	f	f
6	2022-03-21 13:16:49.137	Digno	TRUKAO S.A.	80067174-0	0673-220949	RUTA VI - BO. SINUELO	CRISTIAN	XX	t	f	f	f	f	2	4	0	5	f	f
5	2022-03-21 13:19:19.154	Digno	SAN LORENZO S.A.	80026273-5	11	RUTA VI - SANTA RITA - ALTO PARANÁ	ING. BAEZ	XX	t	f	f	f	f	2	4	0	6	f	f
23	2022-04-27 12:49:48.373	Digno	FUZHOU POWER ELECTRICAL APPLIANCES CO., LTD	-	-	CHINA	-	-	t	f	f	f	f	4	10	0	1	f	f
24	2022-04-27 13:57:30.633	Digno	INDUSTRIA DE MAQUINAS E IMPLEMENTOS AGRICOLAS KF LTDA	-	-	BRAISL 	-	-	t	f	f	f	f	3	10	0	6	f	f
56	2022-06-08 15:59:49.928	Angel Rios 	LUZKO PACHKO SANDRA LORENA	-	-	-	-	-	t	f	f	f	f	1	10	-16224621	5	f	f
54	2022-06-06 16:02:58.543	Angel Rios 	FUZHOU POWER ELECTRICAL APPLIANCES CO. LTD.	-	-	--	-	-	f	f	f	f	f	1	10	0	5	t	f
49	2022-05-31 10:32:54.463	Angel Rios 	AKMEY BRASIL INDUSTRIA E COMERCIO DE PROD. QUIMICOS S.A.	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
25	2022-04-27 15:18:59.898	Digno	MIPAL INDUSTRIA DE EVAPORADORES LTDA	-	-	-	-	-	f	f	f	t	f	1	10	0	1	f	f
26	2022-04-27 15:20:00.24	Digno	MIPAL INDUSTRIA DE EVAPORADORES LTDA	-	-	-	-	-	t	f	f	f	f	1	10	0	1	f	f
1	2021-11-15 00:11:33.363	Digno	OCACIONAL	44444401-7	555	local	OCACIONAL	44444401	f	t	t	t	f	1	1	0	1	f	t
38	2022-05-17 11:56:26.619	Angel Rios 	ELGIN HDB REFRIGERACAO LTDA	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
43	2022-05-26 10:53:04.793	Angel Rios 	MAQUINAS AGRICOLAS JACTO S/A	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
27	2022-04-28 13:49:25.929	Digno	ELECTROLUX DO BRASIL S/A	-	-	-	-	-	t	f	f	f	f	3	10	0	5	f	f
44	2022-05-26 11:31:42.706	Angel Rios 	HYOSUNG BRASIL IND. E COM. DE FIBRAS LTDA	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
39	2022-05-18 09:16:16.264	Angel Rios 	IMPORTADORA DEALER 	-	-	-	-	-	t	f	f	f	f	2	10	-8355637	6	f	f
41	2022-05-24 09:38:19.12	Angel Rios 	INDUSTRIA DE MAQUINAS E IMPLEMENTOS AGRICOLAS KF LTDA	-	-	-	-	-	f	f	f	f	f	3	13	0	5	t	f
53	2022-06-06 08:47:55.551	Angel Rios 	WENZHOU WALKER IMPORT AND EXPORT CO., LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
45	2022-05-26 12:17:19.588	Angel Rios 	ZANETTE IMPORTACAO E EXPORTACAO LTDA	-	-	-	-	-	t	f	f	f	f	1	10	0	5	f	f
50	2022-06-02 14:22:42.733	Angel Rios 	IMPORTADORA DEALER SOCIEDAD ANONIMA	800644603	-	-	-	-	t	f	f	f	f	2	1	-73096927	5	f	f
48	2022-05-31 09:30:51.427	Angel Rios 	GERALDO N. RECKTENWALD & CIA LTDA	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
42	2022-05-24 11:51:21.2	Angel Rios 	HUZHOU ZHONGYUE CHEMICAL FIBER CO., LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
47	2022-05-30 10:32:42.163	Angel Rios 	ZHANJIANG WEIKE IMPOR AND EXPORT CO;LTD	-	-	-	-	-	f	f	f	f	f	4	10	0	5	t	f
15	2022-03-21 13:14:31.89	Digno	SANTA ANA TEXTIL S.A.	801061008	SD	KM 10 APRQUE INDUSTRIAL	JOAO	SD	t	f	f	f	f	2	1	-365197427	4	f	f
8	2022-03-21 13:15:49.338	Digno	REFRINAR S.R.L.	80029099-2	0673-220724	AVDA. 4 DE MAYO	SRA ADRIANA	XX	t	f	f	f	f	2	4	-52617059	5	f	f
55	2022-06-08 14:25:42.686	Angel Rios 	MOLBOR INDUSTRIA E COMERCIO DE ARTEFATOS DE BORRACHA LTDA - EPP	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
12	2022-03-21 13:17:11.127	Digno	AUDENIS & CIA S.A.	80083400-3	SD	AVDA LAS RESIDENTAS	LAURI	SD	t	f	f	f	f	2	6	-13987597	6	f	f
22	2022-04-27 12:42:10.831	Digno	ELECTRO PARANA SRL	800159560	-	KM 7 B° CIUDAD NUEVA 	ARMANDO GAVILAN	-	t	f	f	f	f	2	1	-119739102	1	f	f
57	2022-06-08 16:00:24.784	Angel Rios 	KRINDGES INDUSTRIAL S/A	-	-	-	-	--	f	f	f	f	f	1	10	0	5	t	f
58	2022-06-08 16:48:46.432	Angel Rios 	SIN DEFEINIR 	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
59	2022-06-14 13:01:58.723	Angel Rios 	IMASA INDUSTRIA DE MAQUINAS AGRICOLAS LTDA	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
60	2022-06-15 13:35:53.46	Angel Rios 	GREAT GROUP MEDICAL CO., LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
61	2022-06-17 09:19:44.141	Angel Rios 	DUBRASIL IMPORTACAO E EXPORTACAO LTDA	-	-	-	-	-	t	f	f	f	f	1	13	0	5	f	f
82	2022-08-05 14:34:51.573	Angel Rios 	SIGNIFY URUGUAY S.A.	-	-	--	-	-	f	f	f	f	f	1	10	0	5	t	f
83	2022-08-09 08:49:48.097	Angel Rios 	FJ DYNAMICS INTERNATIONAL LIMITED	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
103	2022-11-10 08:42:25.999	Angel Rios 	MICRO-TECH (NANJING) CO., LTD	--	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
63	2022-06-20 14:10:08.744	Angel Rios 	TAFFER COMPANY S.A.	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
64	2022-06-29 09:37:37.712	Angel Rios 	SILVER RING TRADING CO., LIMITED	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
65	2022-06-29 12:59:53.922	Angel Rios 	SHENZHEN W&F TEC.	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
66	2022-07-04 14:14:55.437	Angel Rios 	FIASUL INDUSTRIA DE FIOS LTDA	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
67	2022-07-04 14:54:03.688	Angel Rios 	XIAMEN SINTELLI MACHINERY INDUSTRIAL CO., LTD	-	-	-	-	-	f	f	f	f	f	4	10	0	5	t	f
104	2022-11-11 12:59:06.45	Angel Rios 	MIPAL INDUSTRIA DE EVAPORADORES LTDA	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
69	2022-07-05 14:45:43.953	Angel Rios 	PROJEMARKET REPRESENTACAO E ENGENHARIA LTDA	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
117	2023-01-18 14:53:33.456	Angel Rios 	HEMISPHERE GNSS	-	-	-	--	-	t	f	f	f	f	1	19	0	5	t	f
62	2022-06-17 09:20:30.102	Angel Rios 	DUBRASIL IMPORTACAO E EXPORTACOA LTDA	-	-	-	-	-	f	f	f	f	f	1	10	-28851427	5	t	f
105	2022-11-25 12:57:23.167	Angel Rios 	HEBEI QUNKUN METAL PRODUCTS CO.,LTD	-	-	-	-	-	t	f	f	f	f	1	10	0	5	t	f
70	2022-07-06 12:35:31.035	Angel Rios 	AGRICOPEL COMERCIO E DERIVADOS DE PETROLEO LTDA	-	-	-	-	-	f	f	f	f	f	3	13	0	5	t	f
71	2022-07-06 13:32:40.726	Angel Rios 	AKMEY BRASIL INDUSTRIA E COMERCIO DE PRODUCTOS QUIMICOS S.A.	-	-	-	-	-	f	f	f	f	f	3	13	0	5	t	f
72	2022-07-06 14:17:23.395	Angel Rios 	NS IMPORTACAO E COMERCIO EIRELI	-	-	-	-	-	f	f	f	f	f	3	13	0	5	t	f
73	2022-07-06 15:26:51.138	Angel Rios 	GLOBAL TEC INNOVATION LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
74	2022-07-08 11:44:09.776	Angel Rios 	ZHEJIANG NEW SORL AUTO PARTS CO., LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
115	2023-01-04 14:11:15.893	Angel Rios 	AGRAL IND E COM DE EQUIP ELECTRONICOS LTDA	-	--	-	-	-	t	f	f	f	f	1	19	0	5	t	f
75	2022-07-08 15:56:15.035	Angel Rios 	BENPAR INDUSTRIA AGRICOLA LTDA	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
91	2022-09-29 10:58:23.226	Angel Rios 	EMED SP. ZO.SP.K	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
76	2022-07-13 10:14:52.52	Angel Rios 	COMMERSUL INDUSTRIA E COMERCIO LTDA	-	-	-	-	-	f	f	f	f	f	3	13	0	5	t	f
68	2022-07-05 14:45:09.926	Angel Rios 	IL MONDO S.A.	800510267	-	-	-	-	t	f	f	f	f	2	10	-38451979	5	f	f
77	2022-07-13 14:48:30.57	Angel Rios 	FERRETERIA BERTI S.R.L.	-	-	-	-	-	t	f	f	f	f	2	6	0	6	f	f
78	2022-07-14 12:08:00.693	Angel Rios 	KINGSPAN ISOESTE CONSTRUTIVOS ISOTERMICOS S/A	-	-	-	-	-	t	f	f	f	f	2	10	0	5	t	f
99	2022-10-28 15:31:18.281	Angel Rios 	GRUPO A.F. BERTI S.R.L.	-	-	-	-	-	t	f	f	f	f	2	19	-10358117	5	t	f
79	2022-07-19 12:47:34.942	Angel Rios 	HANGZHOU CHUNWEI TRADE CO.,LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
101	2022-11-01 08:35:49.694	Angel Rios 	AXCHEM BRASIL INDUSTRIA QUIMICA LTDA	-	-	-	-	-	t	f	f	f	f	3	19	0	5	t	f
84	2022-08-24 09:24:58.226	Angel Rios 	BSC QUIMICA LTDA	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
85	2022-08-26 16:34:02.518	Angel Rios 	CASUAL	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
34	2022-05-06 14:00:05.143	Digno	PULVEMAQ SOCIEDAD ANONIMA 	800941756	-	-	-	-	t	f	f	f	f	2	4	-98199052	2	f	f
92	2022-10-05 11:29:11.455	Angel Rios 	HANGZHOU LINAN XINLAN IMPORT & EXPORT CO. LTD	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
102	2022-11-01 08:47:33.568	Angel Rios 	JUSSA TECIDOS LTDA	-	-	--	-	-	t	f	f	f	f	3	19	-3798548	5	t	f
80	2022-07-29 15:12:21.149	Angel Rios 	TEEJET TECHNOLOGIES	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
106	2022-11-29 12:08:12.002	Angel Rios 	THUNDER IMPORT LLC	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
93	2022-10-10 12:39:41.254	Angel Rios 	BTS - BEST SOLUTIONS LIMITED	-	--	-	-	-	t	f	f	f	f	1	10	0	5	t	f
95	2022-10-11 11:23:51.223	Angel Rios 	WUYI HONGTAI STANLES 	-	-	-	-	-	t	f	f	f	f	1	10	0	5	t	f
107	2022-11-29 12:08:59.65	Angel Rios 	MIGUEL ANGEL PANIAGUA	80015612-9	-	-	-	-	f	t	f	f	f	1	19	0	6	f	f
86	2022-09-06 13:59:11.3	Angel Rios 	FORMULA SURFACTANTES LTDA	-	-	-	-	-	t	f	f	f	f	3	10	0	5	t	f
116	2023-01-18 10:56:31.302	Angel Rios 	INFASUL	-	-	-	-	-	t	f	f	f	f	3	19	0	5	t	f
81	2022-08-01 15:38:20.629	Angel Rios 	IMAK INDUSTRIA LTDA	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
87	2022-09-07 16:06:01.756	Angel Rios 	EMICOL ELETRO ELETRONICA S.A.	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
88	2022-09-07 16:25:43.257	Angel Rios 	ELECTROLUX DO BRASIL S.A.	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
97	2022-10-17 10:29:11.96	Angel Rios 	ZEALMAX INNOVATIONS PVT; LTD	-	-	-	-	-	t	f	f	f	f	1	10	0	5	t	f
89	2022-09-12 11:01:43.253	Angel Rios 	AGROSYSTEM IND. IMP E EXP. LTDA	-	-	-	-	-	f	f	f	f	f	3	10	0	5	t	f
90	2022-09-12 15:13:51.025	Angel Rios 	CISA INDUSTRIA COM. E REP. EQUIP. AGRICOLAS EIRELI	-	-	-	-	-	f	f	f	f	f	1	10	0	5	t	f
96	2022-10-17 10:28:27.241	Angel Rios 	H & G SOCIEDAD AMONIMA 	-	-	-	-	-	t	f	f	f	f	1	10	-8478238	5	t	f
98	2022-10-24 10:46:10.261	Angel Rios 	RICARDO J. DE LA FUENTE 	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
110	2022-12-12 14:07:04.636	Angel Rios 	ANJI PUKE HOME SUPPLIES CO LTD	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
100	2022-10-28 15:32:11.728	Angel Rios 	ELECON INDUSTRIA E COMERCIO LTDA	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
114	2023-01-03 14:08:23.537	Angel Rios 	INDUSTRIA SICA S.A.I.C.	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
118	2023-01-24 14:29:58.047	Angel Rios 	N01 (CHINA) POWER EQUIPAMENT CO., LIMITED	-	-	-	-	-	f	f	f	f	f	4	19	0	5	t	f
111	2022-12-13 09:43:24.423	Angel Rios 	ROYAL INTERNATIONAL C.S S.R.L.	80097337-2	-	PCC - HERNANDARIAS - PY	-	-	t	f	f	f	f	2	8	0	6	t	f
112	2022-12-13 09:43:47.432	Angel Rios 	SUMMITAGRO	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
113	2022-12-14 13:11:01.392	Angel Rios 	SMALL CONCEPT  GROUP LIMITED	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
119	2023-01-26 11:45:16.472	Angel Rios 	MR DONG 	-	-	-	-	-	t	f	f	f	f	1	19	0	5	t	f
109	2022-12-12 14:06:27.525	Angel Rios 	SAMUDIO DE CARDOZO GLORIA FATIMA	1759598-3	-	-	-	-	t	f	f	f	f	2	19	-20336784	5	t	f
94	2022-10-11 11:23:15.05	Angel Rios 	NORTE SRL IMPORTACION Y EXPORTACION	-	-	-	-	-	t	f	f	f	f	1	10	0	5	t	f
108	2022-12-05 11:02:47.655	Angel Rios 	SCHWENDLER FRANTZ MATIAS	65967453	-	-	-	-	t	f	f	f	f	2	4	-29231133	5	t	f
120	2023-02-08 11:41:24.949	Angel Rios 	NORTE SRL/SANTA ANA TEXTIL SA	-	-	-	-	-	t	f	f	f	f	2	19	-9038600	5	f	f
121	2023-02-08 15:09:19.836	Angel Rios 	ANTONIOSITECNOLOGIAAGROINDUSTRIAL LTDA	-	-	-	-	-	f	f	f	f	f	2	19	0	5	t	f
122	2023-02-14 13:26:54.31	Angel Rios 	BARBURY COMPANY S.A.	-	-	-	-	-	f	f	f	f	f	1	19	0	5	t	f
3	2022-03-21 13:13:07.103	Digno	PULVIPAR SA	80063717-8	11	Santa Rita Alto Parana Paraguay	ELISE FRANZ	09739895	t	f	f	f	f	2	1	-1036562124	2	f	f
123	2023-02-16 14:04:03.841	Angel Rios 	MALLANA ASIA IMPORT & EXPORT PTE.LTD	-	-	-	-	-	f	f	f	f	f	3	13	0	10	t	f
\.


--
-- TOC entry 3625 (class 0 OID 24731)
-- Dependencies: 243
-- Data for Name: tercero_ciudad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tercero_ciudad (idtercero_ciudad, nombre, sigla, eliminado) FROM stdin;
11	LUQUE-CENTRAL/PY	LUQ.	f
1	CIUDAD DEL ESTE - ALTP PARANÁ / PY.	CDE.	f
2	ASUNCION - CENTRAL / PY.	ASU.	f
4	SANTA RITA-ALTO PARANÁ/PY.	STR.	f
5	P. J. CABALLERO-AMANBAY/PY.	PJC.	f
7	SALTOS DEL GUAIRÁ-CANINDEYÚ/PY.	SDG.	f
6	KATUETÉ-CANINDEYÚ/PY.	KTT.	f
8	HERNANDARIAS-ALTO PARANÁ/PY.	HND.	f
9	CD. PANAMA - PAN	PN.	f
10	OTROS	OTROS	f
12	FERNANDO DE LA MORA-CENTRAL/PY.	FDM.	f
3	ENCARNACIÓN - ITAPÚA / PY.	ENC.	f
13	BRASIL 	-	f
14	JUAN LEON MALLORQUIN	-	f
15	CAMPOS CERVERA / PARAGUAY 	-	f
16	LAMBARÉ/PY	-	f
17	RIO PILCOMAYO CASI YACARE VALI / PARAGUAY	-	f
18	MARGINAL ESTE C8 MARCIAL SAMA / PARAGUAY	-	f
19	-	-	f
\.


--
-- TOC entry 3626 (class 0 OID 24737)
-- Dependencies: 244
-- Data for Name: tercero_pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tercero_pais (idtercero_pais, nombre, sigla, eliminado) FROM stdin;
1	SIN DEFINIR	SIN	f
2	PARAGUAY	PY	f
3	BRASIL	BR	f
4	CHINA	CHI	f
5	ARGENTINA	ARG	f
6	CHILE	CHI	f
7	PANAMÁ	PAN	f
8	KOREA	KR	f
9	JAPÓN	JP	f
10	URUGUAY	UY	f
11	CHINA TAIWAN	TW	f
12	ISRAEL	ISR	f
13	INDIA	IND	f
\.


--
-- TOC entry 3627 (class 0 OID 24743)
-- Dependencies: 245
-- Data for Name: tercero_rubro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tercero_rubro (idtercero_rubro, nombre, sigla, descripcion, eliminado) FROM stdin;
4	INDUSTRIAL	INDUSTRIAL	NN	f
1	ELECTRONICOS	ELECT.	ninguna\n\n	f
2	MAQUINARIA AGRO	MAQUINARIA AGRO	DESTINADOS EXCLUSIVAMENTE AL RUBRO DEL AGRO	f
8	MAQUINARIAS INDUSTRIALES	MAQ. IND.	DESTINADOS AL SECTOR INDUSTRIAL EN SUS DIFERENTES CAMPOS	f
3	PRODUCTOS AGRICOLAS	PROD. AGRIC.	PRODUCTOS AGRICOLAS COMO SEMILLAS, ABONOS, AGROQUIMICOS Y ANEXOS.	f
7	ROYAL DIV. EXPORTAC.	ROYAL EXPORT	EXPORTACIONES VARIAS	f
9	ROYAL DIV. IMPORT.	ROYAL IMPORT	IMPORTACIONES VARIAS	f
10	INDUSTRIA TEXTIL	IND. TEX.	INDUSTRIAS TEXTILES VARIOS	f
6	IMPORTADOR	IMPORT.	NN	f
5	DISTRIBUIDOR	DISTRIB.	DEDICADOS A LA DISTRIBUCION DE DIVERSOS PRODUCTOS.	f
\.


--
-- TOC entry 3628 (class 0 OID 24749)
-- Dependencies: 246
-- Data for Name: tipo_comprobante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_comprobante (idtipo_comprobante, descripcion, con_comprobante, sin_comprobante, boleta_despachante, mercaderia, tipo_factura, eliminado) FROM stdin;
2	IMPUESTO	t	f	f	f	f	f
3	IVA DESPACHANTE	t	f	f	f	f	f
4	CANON INFORMATICO	t	f	f	f	f	f
5	REAJUSTES	t	f	f	f	f	f
6	TASA AERO-PORTUARIA	t	f	f	f	f	f
8	PERMISO MUNICIPAL	t	f	f	f	f	f
10	TRASLADO	t	f	f	f	f	f
11	PERMISO MIC	t	f	f	f	f	f
12	CANJE GUIA AEREA	t	f	f	f	f	f
13	FOTOCOPIAS	t	f	f	f	f	f
16	GASTOS SECCIONES	f	t	f	f	f	f
17	HONORARIOS AUXILIAR	f	t	f	f	f	f
18	CARTA DE COMPROMISO	f	t	f	f	f	f
22	IMPORTADOR	f	t	f	f	f	f
24	PREDECLARACION	f	f	t	f	f	f
27	COMISION	f	f	t	f	f	f
28	GASTOS VARIOS	f	f	t	f	f	f
91	BARRAS METALICAS	f	f	f	t	f	f
7	VISACIÓN	t	f	f	f	f	f
92	APARATO DE NAVEGACION	f	f	f	t	f	f
9	APERTURA DE REGISTRO	t	f	f	f	f	f
15	MOTOTAXI	f	t	f	f	f	f
93	ALAMBRES	f	f	f	t	f	f
94	RELOJES	f	f	f	t	f	f
23	REFUERZO SECCIONES	f	t	f	f	f	f
25	OTROS PERMISOS	f	f	t	f	f	f
95	ASIENTOS	f	f	f	t	f	f
26	VIÁTICO	f	f	t	f	f	f
32	DERECHO ADUANERO	t	t	t	f	f	f
33	INDI	t	t	t	f	f	f
36	IRE GENERAL 700	t	t	t	f	f	f
96	DRONES	f	f	f	t	f	f
37	CDAP/ANNP	t	f	f	f	f	f
38	COMISION SIS SOFIA	t	f	f	f	f	f
40	TASA PORTUARIA	t	f	f	f	f	f
41	VISACION MRE	t	f	f	f	f	f
42	HON. DESP. S/ LEY 220/93	t	f	f	f	f	f
43	IVA S/ HONORARIO DESP.	t	f	f	f	f	f
45	SOLI. MATERIA PRIMA	t	f	f	f	f	f
46	BOQUILLAS	f	f	f	t	f	f
97	ART. VARIOS	f	f	f	t	f	f
20	PAGO SERVIOS DESPACHOS 	f	t	f	f	f	f
98	RULEMANES 	f	f	f	t	f	f
30	FACTURA A CREDITO	f	f	f	f	t	f
29	FACTURA CONTADO	f	f	f	f	t	f
19	AGILIZACIONES VARIAS	f	t	f	f	f	f
21	ALMUERZOS Y VIATICOS 	f	t	f	f	f	f
34	SERVICIO DE VALORACION 0,50%	t	t	t	f	f	f
35	TASA INT. ADUANERA 50,00 $.	t	t	t	f	f	f
44	GASTOS ADMIN VARIOS	t	t	f	f	f	f
1	PRODUCTOS TEXTILES	f	f	f	t	f	f
31	INSUMOS AGRICOLAS	f	f	f	t	f	f
47	IMPLEMENTOS AGRICOLAS	f	f	f	t	f	f
48	MAQUINARIAS INDUSTRIALES	f	f	f	f	f	f
49	MAQUINARIAS INDUSTRIALES	f	f	f	t	f	f
39	SERV. APERTURA / AGT. TRANSP.	t	f	f	f	f	f
14	FLETE INTERNO	t	f	f	f	f	f
50	REPUESTOS	f	f	f	t	f	f
51	PANEL FRIGORIFICOS	f	f	f	t	f	f
52	LICENCIA PREVIA ALAMBRES	t	t	t	f	f	f
53	GPS 	f	f	f	t	f	f
54	HILOS	f	f	f	t	f	f
55	EVAPORADORES 	f	f	f	t	f	f
56	ACCESORIOS	f	f	f	t	f	f
57	MAQ INYECTORAS	f	f	f	t	f	f
58	JARRAS ELECTRICAS	f	f	f	t	f	f
59	CONDENSADOR 	f	f	f	t	f	f
60	QUIMICOS	f	f	f	t	f	f
61	CONTROL REMOTO	f	f	f	t	f	f
62	ALAMBRES/TEJIDOS	f	f	f	t	f	f
63	ARTICULOS DE FERRETERIA 	f	f	f	t	f	f
64	PRENDAS DE VESTIR 	f	f	f	t	f	f
65	ARTICULO DE GIMNASIA	f	f	f	t	f	f
66	KIT DRONE	f	f	f	t	f	f
67	MASCARAS RESPIRATORIAS	f	f	f	t	f	f
68	DISYUNTORES	f	f	f	t	f	f
69	CTL REM/MOUSE	f	f	f	t	f	f
70	CILINDRO	f	f	f	t	f	f
71	TEJIDOS	f	f	f	t	f	f
72	GONDOLAS	f	f	f	t	f	f
73	LUBRICANTES	f	f	f	t	f	f
74	LANZA PELOTAS	f	f	f	t	f	f
75	REP. AGRIC.	f	f	f	t	f	f
76	SIN DEFINIR 	f	f	f	t	f	f
77	PUERTAS	f	f	f	t	f	f
78	APARATOS RESPIRATORIOS	f	f	f	t	f	f
79	MUESTRAS BATAS	f	f	f	t	f	f
80	MUEBLES 	f	f	f	t	f	f
81	TELAS	f	f	f	t	f	f
82	LAMPARAS	f	f	f	t	f	f
83	PULVERIZADOR	f	f	f	t	f	f
84	-	f	f	f	t	f	f
85	ELECTRONICOS 	f	f	f	t	f	f
86	EQUIPO MEDICO	f	f	f	t	f	f
87	VASOS DE ACERO	f	f	f	t	f	f
88	KIT MATRIX 	f	f	f	t	f	f
89	INTERRUPTORES	f	f	f	t	f	f
90	FICHAS VARIAS 	f	f	f	t	f	f
\.


--
-- TOC entry 3629 (class 0 OID 24755)
-- Dependencies: 247
-- Data for Name: tipo_dependencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_dependencia (idtipo_dependencia, nombre) FROM stdin;
1	SIN DEFINIR
2	PROCESO VIA VUI-MIC
\.


--
-- TOC entry 3630 (class 0 OID 24760)
-- Dependencies: 248
-- Data for Name: tipo_institucion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_institucion (idtipo_institucion, nombre) FROM stdin;
2	(MIC) MIN. DE IND. Y COMERCIO
1	(MRE) MIN. REL. EXT.
3	DINAVISA
4	INAN
5	MADES
6	INFONA
7	AFIDI
8	ANNP
9	SENAVE
10	DIMABEL
11	LABORATORIO CENTRAL
12	MOPC
13	SENACSA
14	SEPRELAD
15	MARINA MERCANTE
16	DINAPI
\.


--
-- TOC entry 3631 (class 0 OID 24765)
-- Dependencies: 249
-- Data for Name: tipo_registro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_registro (idtipo_registro, nombre) FROM stdin;
2	REGISTRO DE EXPORTADOR-MIC
1	REGISTRO DE IMPORTADOR-ADUANA
3	REG DE IMP.-MIC-(CONFECCIONES)
4	MSPBS-DINAVISA (ALIMENTOS)
5	MSPBS-DINAVISA (COSMETICOS)
6	REGISTRO IMPORT. ESPECIAL-ADUANA
7	REGISTRO DE IMP.-MIC-(ALAMRES)
8	REG. DE IMPORT-MIC-(ACERO)
9	REG. DE IMP.-MIC-(LUBRICANTES)
\.


--
-- TOC entry 3632 (class 0 OID 24770)
-- Dependencies: 250
-- Data for Name: tipo_tasa_cambio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_tasa_cambio (idtipo_tasa_cambio, fecha_creacion, creado_por, dolar_gua_aduana, dolar_gua_mercado, real_gua_aduana, real_gua_mercado) FROM stdin;
1	2021-11-15 20:52:11.112	Digno	6950.00	6714.00	1150.00	1100.00
2	2021-11-15 21:15:48.819	Digno	7020.00	7010.00	1001.00	1010.00
3	2021-11-16 15:17:49.919	Digno	6700.00	6866.00	1300.00	1250.00
\.


--
-- TOC entry 3633 (class 0 OID 24775)
-- Dependencies: 251
-- Data for Name: transporte_empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transporte_empresa (idtransporte_empresa, nombre, sigla, direccion, telefono, eliminado) FROM stdin;
1	OCACIONAL	OCA	local	555	f
2	DHL	DHL	cde	555	f
3	TRANS FY	X	X	X	f
4	TRANSLIBERDADE LOGISTICA S.A.	TRANSLI S.A.	-	-	f
5	REMPEL & PILATTI LTDA 	REMPEL	-	-	f
6	OVIEDO LOPEZ CELINA	-	-	-	f
7	AEROVIAS CONT AMERICANO AVIANC	-	-	-	f
8	LA PARAGUAYA DE CARGAS S.A.	-	-	-	f
9	ENS S.A.	-	-	-	f
10	BTR	-	-	-	f
11	COMPAÑIA DE NAVEGACION YSYRY 	-	-	-	f
12	TAMPA CARGO S.A.	-	-	-	f
13	CMSP SA	-	-	-	f
14	SANTA ANA TEXTIL S.A.	-	-	-	f
15	TRANSPORTE FLUVIAL PYO	-	-	-	f
16	TRANSBH TRANSPORTES LTDA	-	-	-	f
17	LAN CARGO S.A. SUC. PARAGUAY 	-	-	-	f
18	TENORA LOGISTICA INTERNACIONAL LTDA	-	-	-	f
19	UNITRANSPORT RODOV EIRELLI ME	-	-	-	f
20	NAVIERA DEL MERCOSUR S.A.	-	-	-	f
21	RIO PARANA NAVEGACION	-	-	-	f
22	COOP. MULT. DE TRABAJO Y OTRO 	-	-	-	f
23	TSM S.A.	-	-	-	f
24	AIR EXPRESS S.A.	-	--	-	f
25	-	-	-	-	f
26	COOP. MULT.	-	-	-	f
27	TAM LINHAS AEREAS S.A.	-	-	-	f
28	RODO RIVA TRANSPORTES LTDA	-	--	-	f
29	AIR EUROPA LINEAS AEREAS S.A.U.	-	-	-	f
30	NAVISHIP PARAGUAY S.A.	-	-	-	f
31	AGRO LOGISTICA S.A.	-	--	-	f
32	NAVEMAR SA	-	-	-	f
33	GUARAN FEEDER S.A.	-	--	-	f
34	EMPRESA NUESTRA SEÑORA DE LA A	-	-	-	f
35	LIDER EXPRESS SOCIEDAD ANONIMA	-	-	-	f
36	ANED TRANSPORTES SOCIEDAD DE R	-	-	-	f
37	TRANS UNION SOCIEDAD ANONIMA	-	-	-	f
\.


--
-- TOC entry 3634 (class 0 OID 24781)
-- Dependencies: 252
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (idusuario, usuario, senha, nombre, fk_idusuario_rol) FROM stdin;
1	5	3	Digno Alfredo	2
2	3	3	Admin	3
3	admin	admin	ADMINISTRADOR	3
4	AUGUSTO	123	Augusto Montelos	3
5	angelrios	1836415	Angel Rios 	3
6	YOYI	456	RODRIGO VEGA	1
\.


--
-- TOC entry 3635 (class 0 OID 24786)
-- Dependencies: 253
-- Data for Name: usuario_evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_evento (idusuario_evento, fecha_creado, cod_evento, descripcion, mensaje_error, fk_idusuario_formulario, fk_idusuario_tipo_evento) FROM stdin;
2	2021-11-29	2	btntercero	sin permiso	2	2
1	2021-11-29	1	btnliquidacion_proforma	Sin permiso	2	2
3	2021-11-29	3	jMenu_proforma	xxxx	2	4
4	2021-11-29	4	jMenu_tercero	xxx	2	4
5	2021-11-29	5	jMenu_configuracion	xxx	2	4
6	2021-11-30	6	jMenuItem_nueva_proforma	xxx	2	3
7	2021-11-30	7	jMenuItem_tipo_comprobante	xxx	2	3
8	2021-11-30	8	jMenuItem_tipo_mercaderia	xxx	2	3
9	2021-11-30	9	jMenuItem_local_aduana	xxx	2	3
10	2021-11-30	10	jMenuItem_nuevo_tercero	xxxx	2	3
11	2021-11-30	11	jMenuItem_tipo_registro	xxx	2	3
12	2021-11-30	12	jMenuItem_tipo_dependencia	xx	2	3
13	2021-11-30	13	jMenuItem_tipo_institucion	xxx	2	3
14	2021-11-30	14	jMenuItem_moneda_cambio	xxx	2	3
15	2021-11-30	15	jMenuItem_honorario_despacho	xxx	2	3
16	2021-11-30	16	jMenu_usuario	xxx	2	4
17	2021-11-30	17	jMenuItem_crear_usuario	xx	2	3
18	2021-11-30	18	jMenuItem_rol_usuario	xxx	2	3
19	2021-11-30	19	jMenuItem_evento_rol_usuario	xxx	2	3
20	2021-11-30	20	jMenuItem_usuario_formulario	xxx	2	3
21	2021-11-30	21	jMenuItem_usuario_tipo_evento	xxx	2	3
24	2021-11-30	24	btnimprimir_proforma	NO TIENE PERMISO DE IMPRIMIR	3	2
23	2021-11-30	23	btnrecargar_liquidacion	NO TIENE PERMISO PARA RECARGAR	3	2
22	2021-11-30	22	btnanular_liquidacion	NO TIENE PERMISO PARA ANULAR	3	2
25	2022-05-16	25	Habilita boton de eliminar	NO TIENE PERMISO PARA ELIMINAR	4	5
\.


--
-- TOC entry 3636 (class 0 OID 24791)
-- Dependencies: 254
-- Data for Name: usuario_formulario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_formulario (idusuario_formulario, nombre) FROM stdin;
1	SIN DEFINIR
2	FrmMenuDespacho
3	FrmLiquidacion_proforma
4	TODOS LOS FORMULARIO
\.


--
-- TOC entry 3637 (class 0 OID 24796)
-- Dependencies: 255
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_rol (idusuario_rol, fecha_creacion, nombre, descripcion) FROM stdin;
1	2021-11-28	Sin Definir	no tiene configuracion
4	2022-05-04	PRUEBA	PRUEBA PRUEBA
3	2022-05-16	Administrador	Administra el Sistema
2	2022-05-16	Programador	Encargado a configurar
\.


--
-- TOC entry 3638 (class 0 OID 24801)
-- Dependencies: 256
-- Data for Name: usuario_tipo_evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_tipo_evento (idusuario_tipo_evento, nombre) FROM stdin;
1	SIN DEFINIR
2	Bloquear Boton
3	Bloquear Menu item
4	Bloquear Menu
5	BOTON ELIMINAR
\.


--
-- TOC entry 3639 (class 0 OID 24806)
-- Dependencies: 257
-- Data for Name: vale; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vale (idvale, fecha_creado, creado_por, descripcion, monto_vale, monto_letra, estado, fk_idfuncionario, fk_idusuario) FROM stdin;
2	2022-04-27 17:27:29.324	4-Augusto Montelos	ADELANTO	1000000	UN MILLON 	EMITIDO	3	4
3	2022-04-27 17:28:05.922	4-Augusto Montelos	ADELANTO	2100000	DOS MILLON  CIEN MIL 	EMITIDO	4	4
4	2022-04-27 17:33:44.574	4-Augusto Montelos	ADELANTO	140000	CIENTO CUARENTA MIL 	EMITIDO	1	4
5	2022-04-27 17:35:39.727	4-Augusto Montelos	ADELANTO	5000000	CINCO MILLON 	EMITIDO	1	4
6	2022-04-27 17:35:52.03	4-Augusto Montelos	ADELANTO	5000000	CINCO MILLON 	EMITIDO	1	4
7	2022-04-29 08:40:12.837	4-Augusto Montelos	ADELANTO	1000000	UN MILLON 	EMITIDO	1	4
8	2022-05-04 12:48:41.95	4-Augusto Montelos	ADELANTO EN FECHA 04-05-22	1500000	UN MILLON QUINIENTOS MIL 	EMITIDO	1	4
1	2022-02-17 10:40:25.887	1-Digno Alfredo	ADELANTO	0	CERO 	ANULADO	1	1
9	2022-08-09 10:21:11.898	5-Angel Rios 	VALE SALARIO MES DE AGOSTO	100000	 CIEN MIL 	ANULADO	4	5
\.


--
-- TOC entry 3367 (class 2606 OID 24812)
-- Name: aduana aduana_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aduana
    ADD CONSTRAINT aduana_pkey PRIMARY KEY (idaduana);


--
-- TOC entry 3369 (class 2606 OID 24814)
-- Name: caja_detalle caja_detalle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caja_detalle
    ADD CONSTRAINT caja_detalle_pkey PRIMARY KEY (idcaja_detalle);


--
-- TOC entry 3371 (class 2606 OID 24816)
-- Name: comprobante_liquidacion comprobante_liquidacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comprobante_liquidacion
    ADD CONSTRAINT comprobante_liquidacion_pkey PRIMARY KEY (idcomprobante_liquidacion);


--
-- TOC entry 3373 (class 2606 OID 24818)
-- Name: credito_tercero credito_tercero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credito_tercero
    ADD CONSTRAINT credito_tercero_pkey PRIMARY KEY (idcredito_tercero);


--
-- TOC entry 3375 (class 2606 OID 24820)
-- Name: despacho_zona despacho_zona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.despacho_zona
    ADD CONSTRAINT despacho_zona_pkey PRIMARY KEY (iddespacho_zona);


--
-- TOC entry 3377 (class 2606 OID 24822)
-- Name: encomienta encomienta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.encomienta
    ADD CONSTRAINT encomienta_pkey PRIMARY KEY (idencomienta);


--
-- TOC entry 3379 (class 2606 OID 24824)
-- Name: factura_nro_proforma factura_nro_proforma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_nro_proforma
    ADD CONSTRAINT factura_nro_proforma_pkey PRIMARY KEY (idfactura_nro_proforma);


--
-- TOC entry 3381 (class 2606 OID 24826)
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (idfuncionario);


--
-- TOC entry 3383 (class 2606 OID 24828)
-- Name: gasto gasto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gasto
    ADD CONSTRAINT gasto_pkey PRIMARY KEY (idgasto);


--
-- TOC entry 3385 (class 2606 OID 24830)
-- Name: gasto_tipo gasto_tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gasto_tipo
    ADD CONSTRAINT gasto_tipo_pkey PRIMARY KEY (idgasto_tipo);


--
-- TOC entry 3387 (class 2606 OID 24832)
-- Name: grupo_credito_tercero grupo_credito_tercero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grupo_credito_tercero
    ADD CONSTRAINT grupo_credito_tercero_pkey PRIMARY KEY (idgrupo_credito_tercero);


--
-- TOC entry 3389 (class 2606 OID 24834)
-- Name: honorario_despacho honorario_despacho_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.honorario_despacho
    ADD CONSTRAINT honorario_despacho_pkey PRIMARY KEY (idhonorario_despacho);


--
-- TOC entry 3391 (class 2606 OID 24836)
-- Name: incoterms incoterms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incoterms
    ADD CONSTRAINT incoterms_pkey PRIMARY KEY (idincoterms);


--
-- TOC entry 3395 (class 2606 OID 24838)
-- Name: item_liquidacion_final item_liquidacion_final_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_liquidacion_final
    ADD CONSTRAINT item_liquidacion_final_pkey PRIMARY KEY (iditem_liquidacion_final);


--
-- TOC entry 3397 (class 2606 OID 24840)
-- Name: item_mercaderia item_mercaderia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_mercaderia
    ADD CONSTRAINT item_mercaderia_pkey PRIMARY KEY (iditem_mercaderia);


--
-- TOC entry 3399 (class 2606 OID 24842)
-- Name: item_pais_ciudad item_pais_ciudad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pais_ciudad
    ADD CONSTRAINT item_pais_ciudad_pkey PRIMARY KEY (iditem_pais_ciudad);


--
-- TOC entry 3401 (class 2606 OID 24844)
-- Name: item_pre_liquidacion item_pre_liquidacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pre_liquidacion
    ADD CONSTRAINT item_pre_liquidacion_pkey PRIMARY KEY (iditem_pre_liquidacion);


--
-- TOC entry 3393 (class 2606 OID 24846)
-- Name: item_comprobante item_sin_comprobante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_comprobante
    ADD CONSTRAINT item_sin_comprobante_pkey PRIMARY KEY (iditem_comprobante);


--
-- TOC entry 3403 (class 2606 OID 24848)
-- Name: item_tipo_registro item_tipo_registro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_tipo_registro
    ADD CONSTRAINT item_tipo_registro_pkey PRIMARY KEY (iditem_tipo_registro);


--
-- TOC entry 3405 (class 2606 OID 24850)
-- Name: item_usuario_rol item_usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_usuario_rol
    ADD CONSTRAINT item_usuario_rol_pkey PRIMARY KEY (iditem_usuario_rol);


--
-- TOC entry 3407 (class 2606 OID 24852)
-- Name: liquidacion_final liquidacion_final_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liquidacion_final
    ADD CONSTRAINT liquidacion_final_pkey PRIMARY KEY (idliquidacion_final);


--
-- TOC entry 3409 (class 2606 OID 24854)
-- Name: liquidacion_proforma liquidacion_proforma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liquidacion_proforma
    ADD CONSTRAINT liquidacion_proforma_pkey PRIMARY KEY (idliquidacion_proforma);


--
-- TOC entry 3411 (class 2606 OID 24856)
-- Name: moneda_cambio moneda_cambio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moneda_cambio
    ADD CONSTRAINT moneda_cambio_pkey PRIMARY KEY (idmoneda_cambio);


--
-- TOC entry 3413 (class 2606 OID 24858)
-- Name: pre_item_liquidacion_final pre_item_liquidacion_final_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pre_item_liquidacion_final
    ADD CONSTRAINT pre_item_liquidacion_final_pkey PRIMARY KEY (idpre_item_liquidacion_final);


--
-- TOC entry 3415 (class 2606 OID 24860)
-- Name: pre_liquidacion pre_liquidacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pre_liquidacion
    ADD CONSTRAINT pre_liquidacion_pkey PRIMARY KEY (idpre_liquidacion);


--
-- TOC entry 3417 (class 2606 OID 24862)
-- Name: recibo_pago_tercero recibo_pago_tercero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recibo_pago_tercero
    ADD CONSTRAINT recibo_pago_tercero_pkey PRIMARY KEY (idrecibo_pago_tercero);


--
-- TOC entry 3419 (class 2606 OID 24864)
-- Name: regimen regimen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regimen
    ADD CONSTRAINT regimen_pkey PRIMARY KEY (idregimen);


--
-- TOC entry 3421 (class 2606 OID 24866)
-- Name: saldo_credito_tercero saldo_credito_tercero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saldo_credito_tercero
    ADD CONSTRAINT saldo_credito_tercero_pkey PRIMARY KEY (idsaldo_credito_tercero);


--
-- TOC entry 3425 (class 2606 OID 24868)
-- Name: tercero_ciudad tercero_ciudad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tercero_ciudad
    ADD CONSTRAINT tercero_ciudad_pkey PRIMARY KEY (idtercero_ciudad);


--
-- TOC entry 3427 (class 2606 OID 24870)
-- Name: tercero_pais tercero_pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tercero_pais
    ADD CONSTRAINT tercero_pais_pkey PRIMARY KEY (idtercero_pais);


--
-- TOC entry 3423 (class 2606 OID 24872)
-- Name: tercero tercero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tercero
    ADD CONSTRAINT tercero_pkey PRIMARY KEY (idtercero);


--
-- TOC entry 3429 (class 2606 OID 24874)
-- Name: tercero_rubro tercero_rubro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tercero_rubro
    ADD CONSTRAINT tercero_rubro_pkey PRIMARY KEY (idtercero_rubro);


--
-- TOC entry 3433 (class 2606 OID 24876)
-- Name: tipo_dependencia tipo_dependencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_dependencia
    ADD CONSTRAINT tipo_dependencia_pkey PRIMARY KEY (idtipo_dependencia);


--
-- TOC entry 3431 (class 2606 OID 24878)
-- Name: tipo_comprobante tipo_gasto_liquidacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_comprobante
    ADD CONSTRAINT tipo_gasto_liquidacion_pkey PRIMARY KEY (idtipo_comprobante);


--
-- TOC entry 3435 (class 2606 OID 24880)
-- Name: tipo_institucion tipo_institucion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_institucion
    ADD CONSTRAINT tipo_institucion_pkey PRIMARY KEY (idtipo_institucion);


--
-- TOC entry 3437 (class 2606 OID 24882)
-- Name: tipo_registro tipo_registro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_registro
    ADD CONSTRAINT tipo_registro_pkey PRIMARY KEY (idtipo_registro);


--
-- TOC entry 3439 (class 2606 OID 24885)
-- Name: tipo_tasa_cambio tipo_tasa_cambio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_tasa_cambio
    ADD CONSTRAINT tipo_tasa_cambio_pkey PRIMARY KEY (idtipo_tasa_cambio);


--
-- TOC entry 3441 (class 2606 OID 24888)
-- Name: transporte_empresa transporte_empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transporte_empresa
    ADD CONSTRAINT transporte_empresa_pkey PRIMARY KEY (idtransporte_empresa);


--
-- TOC entry 3445 (class 2606 OID 24890)
-- Name: usuario_evento usuario_evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_evento
    ADD CONSTRAINT usuario_evento_pkey PRIMARY KEY (idusuario_evento);


--
-- TOC entry 3447 (class 2606 OID 24892)
-- Name: usuario_formulario usuario_formulario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_formulario
    ADD CONSTRAINT usuario_formulario_pkey PRIMARY KEY (idusuario_formulario);


--
-- TOC entry 3443 (class 2606 OID 24894)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);


--
-- TOC entry 3449 (class 2606 OID 24896)
-- Name: usuario_rol usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (idusuario_rol);


--
-- TOC entry 3451 (class 2606 OID 24900)
-- Name: usuario_tipo_evento usuario_tipo_evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_tipo_evento
    ADD CONSTRAINT usuario_tipo_evento_pkey PRIMARY KEY (idusuario_tipo_evento);


--
-- TOC entry 3453 (class 2606 OID 24903)
-- Name: vale vale_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vale
    ADD CONSTRAINT vale_pkey PRIMARY KEY (idvale);


-- Completed on 2023-02-20 21:00:00

--
-- PostgreSQL database dump complete
--

