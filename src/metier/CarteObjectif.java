package metier;

public class CarteObjectif 
{
	private Noeud noeud1;
	private Noeud noeud2;
	private int   points;

	public CarteObjectif(Noeud noeud1, Noeud noeud2, int points)
	{
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.points = points;
	}

	public Noeud getNoeud1() { return this.noeud1; }
	public Noeud getNoeud2() { return this.noeud2; }
	public int   getPoints() { return this.points; }

	public void setNoeud1(Noeud noeud1) { this.noeud1 = noeud1; }
	public void setNoeud2(Noeud noeud2) { this.noeud2 = noeud2; }
	public void setPoints(int   points) { this.points = points; }
}