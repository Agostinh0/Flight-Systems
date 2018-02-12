package br.ufrpe.flight_systems.negocio.beans;

import java.time.ZoneId;

public enum Cidade {
	REC("Recife"), BSB("Brasília"), GIG("Galeão"), GRU("Guarulhos"), YYZ("Toronto"), YVR("Vancouver"),LGA("Nova Iorque"),
	LAS("Las Vegas"), LIS("Lisboa"), MAD("Madrid"),ORY("Paris"), AMS("Amsterdã"), LHR("Londres"), DUB("Dublin"),
	ARN("Estocolmo"), CPH("Copenhague"), OSL("Oslo"), TXL("Berlim"), CIA("Roma"), PEK("Pequim"), HND("Tóquio"), ICN("Seul");
	
	private String cidade;
	private ZoneId fusoHorario;
	
	Cidade(String cidade){
		this.cidade = cidade;
	}
	
	public String getCidade(){
		return cidade;
	}
	
	public ZoneId getFusoHorario(Cidade cidade){
		
		if(cidade.equals(REC.getCidade()) || cidade.equals(BSB.getCidade()) || cidade.equals(GIG.getCidade()) 
				 ||cidade.equals(GRU.getCidade())){
			 fusoHorario = ZoneId.of("America/Sao_Paulo");
		}else if(cidade.equals(YYZ.getCidade()) || cidade.equals(YVR.getCidade()) || cidade.equals(LGA.getCidade()) 
				 || cidade.equals(LAS.getCidade())){
			 fusoHorario = ZoneId.of("America/New_York");
		}else if(cidade.equals(LIS.getCidade()) || cidade.equals(MAD.getCidade()) || cidade.equals(ORY.getCidade())
				 || cidade.equals(AMS.getCidade()) || cidade.equals(LHR.getCidade()) || cidade.equals(DUB.getCidade())
				 || cidade.equals(TXL.getCidade()) || cidade.equals(ARN.getCidade()) || cidade.equals(CPH.getCidade())
				 || cidade.equals(OSL.getCidade()) || cidade.equals(CIA.getCidade())){
			 fusoHorario = ZoneId.of("Europe/Paris");
		}else if(cidade.equals(PEK.getCidade()) || cidade.equals(HND.getCidade()) || cidade.equals(ICN.getCidade())){
			 fusoHorario = ZoneId.of("Asia/Tokyo");
	}
		return fusoHorario;
	}
}