package fr.emse.test;

class Money implements IMoney {
	private int fAmount;
	
	private String fCurrency;
	
	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
	}
	public int amount() {
		return fAmount;
	}
	public String currency() {
		return fCurrency;
	}
	public Money add(Money m) {
		return new Money(amount() + m.amount(), currency());
	}
	
	public IMoney add(IMoney m) {
		return m.addMoney(this);
	}
	
	@Override
	public boolean equals(Object a) {
		if(a == null || !(a instanceof Money)) {
			return false;
		}
		return amount() == ((Money)a).amount() && currency() == ((Money)a).currency();
	}
	
	
	public IMoney addMoneyBag(IMoney moneyBag) {
		((MoneyBag)moneyBag).appendMoney(this);
		if(((MoneyBag)moneyBag).getfMonies().size() == 1) {
			return ((MoneyBag)moneyBag).getfMonies().get(0);	
		}else {
			return moneyBag;
		}
	}
	@Override
	public IMoney addMoney(IMoney money) {
		if(((Money)money).currency().equals(currency())) {
			return new Money(amount()+((Money)money).amount(), currency());
		}
		return new MoneyBag(this, ((Money)money));
	}
}