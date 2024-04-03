package fr.emse.test;

import java.util.HashSet;
import java.util.Vector;

public class MoneyBag implements IMoney{
	private Vector<Money> fMonies = new Vector<Money>();
	
	MoneyBag(Money m1, Money m2) {
		appendMoney(m1);
		appendMoney(m2);
	}
	
	MoneyBag(Money bag[]) {
		for (int i = 0; i < bag.length; i++)
		appendMoney(bag[i]);
	}
	
	void appendMoney(Money m) {
		if (fMonies.isEmpty()) {
			fMonies.add(m);
		} else {
			int i = 0;
			while ((i < fMonies.size())
					&& (!(fMonies.get(i).currency().equals(m.currency()))))
				i++;
			if (i >= fMonies.size()) {
				fMonies.add(m);
			} else {
				fMonies.set(i, new Money(fMonies.get(i).amount() +
						m.amount(),
						m.currency()));
			}
		}
	}
	
	public Vector<Money> getfMonies(){
		return fMonies;
	}
	
	@Override
	public boolean equals(Object abag) {
		if(abag == null || !(abag instanceof MoneyBag) || ((MoneyBag)abag).getfMonies().size() != getfMonies().size()) {
			return false;
		}else {
			HashSet<Money> intersection = new HashSet<>(getfMonies());
			intersection.retainAll(((MoneyBag)abag).getfMonies());
			return intersection.size() == getfMonies().size();
		}
	}
	
	public IMoney add(IMoney m) {
		return m.addMoney(this);
	}

	@Override
	public IMoney addMoneyBag(IMoney moneyBag) {
		for(int i=0; i<((MoneyBag)moneyBag).getfMonies().size(); i++) {
			this.appendMoney(((MoneyBag)moneyBag).getfMonies().get(i));
		}
		if(this.fMonies.size() == 1) {
			System.out.println(this.fMonies.get(0) instanceof Money);
			return this.fMonies.get(0);
		}else {
			return this;
		}
	}

	public IMoney addMoney(IMoney money) {
		this.appendMoney((Money)money);
		if(this.fMonies.size() == 1) {
			return this.fMonies.get(0);
		}else {
			return this;
		}
	}

}