package br.com.introcdc.mapmeelv3;

public enum Cargo {

    ADMIN("Admin",
          true,
          3),
    CONVIDADO("Convidado",
              false,
              2),
    JOGADOR("Jogador",
            false,
            0),
    MEEL("Meel",
         false,
         1);

    public static Cargo byName(String name) {
        for (Cargo c : Cargo.values()) {
            if (name.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }
        return Cargo.CONVIDADO;
    }

    public static boolean existCargo(String name) {
        for (Cargo c : Cargo.values()) {
            if (name.equalsIgnoreCase(c.getName())) {
                return true;
            }
        }
        return false;
    }

    private int id;

    private String name;

    private boolean op;

    Cargo(String name, boolean op, int id) {
        this.name = name;
        this.op = op;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAfter(Cargo c) {
        return this.getId() > c.getId();
    }

    public boolean isAfterOrEq(Cargo c) {
        return this.getId() >= c.getId();
    }

    public boolean isBefore(Cargo c) {
        return this.getId() < c.getId();
    }

    public boolean isBeforeOrEq(Cargo c) {
        return this.getId() <= c.getId();
    }

    public boolean isOp() {
        return this.op;
    }

}
