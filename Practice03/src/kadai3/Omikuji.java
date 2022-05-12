package kadai3;

public abstract class Omikuji {

	protected String unsei;
	protected String unseicd;
	protected String omikujicd;
	protected String negaigoto;
	protected String akinai;
	protected String gakumon;

	public abstract void unseicode();


	public String getUnsei() {
		return unsei;
	}

	public void setUnsei(String unsei) {
		this.unsei = unsei;
	}

	public String getUnseicd() {
		return unseicd;
	}

	public void setUnseicd(String unseicd) {
		this.unseicd = unseicd;
	}

	public String getOmikujicd() {
		return omikujicd;
	}

	public void setOmikujicd(String omikujicd) {
		this.omikujicd = omikujicd;
	}

	public String getNegaigoto() {
		return negaigoto;
	}

	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	public String getAkinai() {
		return akinai;
	}

	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	public String getGakumon() {
		return gakumon;
	}

	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}

}
