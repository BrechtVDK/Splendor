
//
////Deze klasse zou nu overbodig zijn door het gebruik van de record OntwikkelingskaartRecord
//
//package domein;
//
//public class OntwikkelingskaartKlasse {

//	private int prestigePunten;
//	private Edelsteenfiche bonus;
//	private Edelsteenfiche[] edelsteenfiches;
//
//	public Ontwikkelingskaart(int prestigePunten, Edelsteenfiche bonus, Edelsteenfiche[] edelsteenfiches) {
//
//		this.prestigePunten = prestigePunten;
//		this.bonus = bonus;
//		this.edelsteenfiches = edelsteenfiches;
//	}
//
//	public int getPrestigePunten() {
//		return prestigePunten;
//	}
//
//	public Edelsteenfiche getBonus() {
//		return bonus;
//	}
//
//	public Edelsteenfiche[] getEdelsteenfiches() {
//		return edelsteenfiches;
//	}
//
//	@Override
//	public String toString() {
//
//		StringBuilder sbFiches = new StringBuilder();
//		for (Edelsteenfiche fiche : edelsteenfiches) {
//			sbFiches.append(",").append(fiche.toString());
//		}
//		return String.format("%d,%s%s", prestigePunten, bonus, sbFiches.toString());
//	}
//
//	public OntwikkelingskaartRecord toRecord() {
//		return new OntwikkelingskaartRecord(this.getPrestigePunten(), this.getBonus(), this.getEdelsteenfiches());
//	}
//}

//}
