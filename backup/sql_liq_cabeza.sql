select lf.idliquidacion_final as idlqui,lf.tipo_liquidacion as tipo,lf.fecha_despacho as fec_despacho,
ti.nombre as importa,lf.contenedor_nro as contene_nro,tc.descripcion as merca,tc2.nombre as destino,
lf.via_transporte as via,ad.nombre as aduana,te.nombre as trans_emp,lf.transporte_condicion as trans_condi,
lf.despacho_numero as despacho_nro,dz.nombre as zona,
tex.nombre as exportador,
lf.monto_imponible as m_imponible,lf.monto_ajuste_incluir as m_ajuste,
lf.monto_factura as m_factura,lf.factura_numero as  nro_factura,
lf.monto_flete as m_flete,lf.monto_seguro as m_seguro,
lf.monto_cif as m_cif,(lf.monto_cif+lf.monto_ajuste_incluir) as m_cif_ajuste ,lf.tasa_cambio_aduana as tc_aduana,lf.tipo_tasa_cambio as moneda,
re.sigla as regime,ic.sigla as incotern,lf.monto_total_despacho as m_ttdespacho,
lf.monto_adelanto as m_adelanto,lf.monto_pagar as m_pagar,lf.monto_letra as m_letra,lf.observacion as observa,
lf.otro_nombre as otro_nombre,lf.otro_monto as otro_monto, 
((lf.monto_total_despacho/lf.monto_imponible)*100) as utilidad 
from liquidacion_final lf,tercero ti,tipo_comprobante tc,tercero_ciudad tc2,aduana ad,transporte_empresa te,
despacho_zona dz,tercero tex,regimen re,incoterms ic 
where lf.fk_idtercero_importador=ti.idtercero
and lf.fk_idtercero_exportador=tex.idtercero 
and lf.fk_idtipo_comprobante=tc.idtipo_comprobante
and lf.fk_idtercero_ciudad=tc2.idtercero_ciudad
and lf.fk_idaduana=ad.idaduana 
and lf.fk_idtransporte_empresa=te.idtransporte_empresa
and lf.fk_iddespacho_zona=dz.iddespacho_zona 
and lf.fk_idregimen=re.idregimen 
and lf.fk_idincoterms=ic.idincoterms 
and lf.estado='EMITIDO'
--and lf.idliquidacion_final in(364,365,367) 
order by lf.idliquidacion_final desc;