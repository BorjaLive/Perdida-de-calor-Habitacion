<?php
	echo "<h1>Calculos</h1>";
	
	//recoger datos----------------------------------------------------------
	//ancho paredes
	$pAncho = array($_POST['P1_ancho'],$_POST['P2_ancho'],$_POST['P3_ancho'],$_POST['P4_ancho'],$_POST['P5_ancho'],$_POST['P6_ancho']);
	//alto paredes 
	$pAlto = array($_POST['P1_alto'],$_POST['P2_alto'],$_POST['P3_alto'],$_POST['P4_alto'],$_POST['P5_alto'],$_POST['P6_alto']);
	//grosor paredes
	$pGrosor = array($_POST['P1_grosor'],$_POST['P2_grosor'],$_POST['P3_grosor'],$_POST['P4_grosor'],$_POST['P5_grosor'],$_POST['P6_grosor']);
	//constante paredes
	$pConstante = array($_POST['P1_constante'],$_POST['P2_constante'],$_POST['P3_constante'],$_POST['P4_constante'],$_POST['P5_constante'],$_POST['P6_constante']);
	//temperatura exterior
	$pTemperatura = array($_POST['P1_temperatura'],$_POST['P2_temperatura'],$_POST['P3_temperatura'],$_POST['P4_temperatura'],$_POST['P5_temperatura'],$_POST['P6_temperatura']);
	//ancho discontinuidad
	$dAncho = array($_POST['D1_ancho'],$_POST['D2_ancho'],$_POST['D3_ancho'],$_POST['D4_ancho'],$_POST['D5_ancho'],$_POST['D6_ancho']);
	//alto discontinuidad
	$dAlto = array($_POST['D1_alto'],$_POST['D2_alto'],$_POST['D3_alto'],$_POST['D4_alto'],$_POST['D5_alto'],$_POST['D6_alto']);
	//grosor discontinuidad
	$dGrosor = array($_POST['D1_grosor'],$_POST['D2_grosor'],$_POST['D3_grosor'],$_POST['D4_grosor'],$_POST['D5_grosor'],$_POST['D6_grosor']);
	//constante discontinuidad
	$dConstante = array($_POST['D1_constante'],$_POST['D2_constante'],$_POST['D3_constante'],$_POST['D4_constante'],$_POST['D5_constante'],$_POST['D6_constante']);
	//cantidad de discontinuidades
	$dCantidad = array($_POST['D1_cantidad'],$_POST['D2_cantidad'],$_POST['D3_cantidad'],$_POST['D4_cantidad'],$_POST['D5_cantidad'],$_POST['D6_cantidad']);
	//pared donde se encuentra la discontinuidad
	$dPosicion = array($_POST['D1_posicion'],$_POST['D2_posicion'],$_POST['D3_posicion'],$_POST['D4_posicion'],$_POST['D5_posicion'],$_POST['D6_posicion']);
	//temperatura interna
	$temeraturaInterna = $_POST['temperaturaInterna'];
	
	//calculos de superficie--------------------------------------------------------------
	$pSuperficie = array(0,0,0,0,0,0);
	$dSuperficie = array(0,0,0,0,0,0);
	for($var=0; $var < 6; $var++){	//superficie de paredes y discontinuidades
		$pSuperficie[$var] = $pAncho[$var]*$pAlto[$var];
		$dSuperficie[$var] = $dAncho[$var]*$dAlto[$var];
	}
	echo "<br>";
	for($var=0; $var < 6; $var++){	//resta de ka duscibtinuidad a la pared en la que se encuentra
		if (empty($dPosicion[$var])) {	//evita errores colocando 0 en las vacias
		    $dPosicion[$var] = 0;
		}else{
			$dPosicion[$var] --;
		}
		$pSuperficie[$dPosicion[$var]] -= $dSuperficie[$var];
		echo "Superficie de la pared ".($var+1) ." es: ".$pSuperficie[$var] ." m2<br>";
	}
	echo "<br>";
	for($var=0; $var < 6; $var++){	// muestra de la superficie de la discontinuidad
		echo "Superficie de la discontinuidad ".($var+1) ." es: ".$dSuperficie[$var] ." m2<br>";
	}
	echo "<br>";
	$pCedidaJ = array(0,0,0,0,0,0);
	$pCedidaW = array(0,0,0,0,0,0);
	$dCedidaJ = array(0,0,0,0,0,0);
	$dCedidaW = array(0,0,0,0,0,0);
	$totalCedidaJ = 0;
	$totalCedidaW = 0;
	for($var=0; $var < 6; $var++){	// energia cedida por paredes y discontinuidades
		if($pGrosor[$var] != 0){
			$pCedidaJ[$var] = (($pConstante[$var]/$pGrosor[$var])*$pSuperficie[$var]*($temeraturaInterna-$pTemperatura[$var]));
			$dCedidaJ[$var] = (($dConstante[$var]/$dGrosor[$var])*$dSuperficie[$var]*($temeraturaInterna-$pTemperatura[$dPosicion[$var]]))*$dCantidad[$var];
			$pCedidaW[$var] = $pCedidaJ[$var]/864;
			$dCedidaW[$var] = $dCedidaJ[$var]/864;
			$totalCedidaJ += $pCedidaJ[$var]+$dCedidaJ[$var];
			$totalCedidaW += $pCedidaW[$var]+$dCedidaW[$var];
		}
		echo "Energia cedida por la pared " .($var+1) ." es " .$pCedidaJ[$var] ." J/m2s = " .$pCedidaW[$var] ."w/h <br>";
	}
	echo "<br>";
	for($var=0; $var < 6; $var++){	// energia cedida por paredes y discontinuidades
		echo "Energia cedida por la discontinuidad " .($var+1) ." es " .$dCedidaJ[$var] ." J/m2s = " .$dCedidaW[$var] ."w/h <br>";
	}
	echo "<br>";
	echo "La nergia cedida por la habitacíon es de " .$totalCedidaJ ." J/m2s = " .$totalCedidaW ."w/h <br>";
	
?>