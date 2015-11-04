package tiles;

public class Tile {
	String[] directions = { "North", "South", "East", "West" };
	String in;
	String out;
	boolean fixed;
	int type;
	int id;

	public Tile(int type, int id) {
		switch (type) {
		case 1:
			createPathTile();
			break;
		case 2:
			createBlockTile();
			break;
		case 3:
			createBlankTile();
			break;
		case 4:
			createInitialTile();
			break;
		case 5:
			createGoalTile();
			break;
		default:
			break;
		}
		this.id = id;
		this.type = type;
	}

	public void createPathTile() {
		int x = (int) (Math.random() * 10) % 4;
		in = directions[x];
		x = (int) (Math.random() * 10) % 4;
		while (directions[x].equals(in)) {
			out = directions[x];
			x = (int) (Math.random() * 10) % 4;
		}
		out = directions[x];
		x = (int) (Math.random() * 10) % 2;
		if (x == 0) {
			fixed = false;
		} else {
			fixed = true;
		}
	}

	public void createGoalTile() {
		int x = (int) (Math.random() * 10) % 4;
		in = directions[x];
		out = "None";
		fixed = true;
	}

	public void createInitialTile() {
		int x = (int) (Math.random() * 10) % 4;
		out = directions[x];
		in = "None";
		fixed = true;
	}

	public void createBlockTile() {
		fixed = false;
	}

	public void createBlankTile() {

	}

	public String getIn() {
		return in;
	}

	public String getOut() {
		return out;
	}

	public boolean isPath() {
		return type == 1;
	}

	public boolean isBlock() {
		return type == 2;
	}

	public boolean isBlank() {
		return type == 3;
	}

	public boolean isInitial() {
		return type == 4;
	}

	public boolean isGoal() {
		return type == 5;
	}

	public boolean isFixed() {
		return fixed;
	}

	public String toString() {
		if (isGoal())
			return "I am a Goal Tile";
		if (isInitial())
			return "I am an Initial Tile";
		if (isPath())
			return "I am Path Tile";
		if (isBlank())
			return "I am a Blank Tile";
		return "I am a Block Tile";
	}

	public int getId() {
		return id;
	}

}
