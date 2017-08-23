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

    public static Cargo byName(final String name) {
        for (final Cargo c : Cargo.values()) {
            if (name.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }
        return Cargo.CONVIDADO;
    }

    public static boolean existCargo(final String name) {
        for (final Cargo c : Cargo.values()) {
            if (name.equalsIgnoreCase(c.getName())) {
                return true;
            }
        }
        return false;
    }

    private int id;

    private String name;

    private boolean op;

    Cargo(final String name, final boolean op, final int id) {
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

    public boolean isAfter(final Cargo c) {
        return this.getId() > c.getId();
    }

    public boolean isAfterOrEq(final Cargo c) {
        return this.getId() >= c.getId();
    }

    public boolean isBefore(final Cargo c) {
        return this.getId() < c.getId();
    }

    public boolean isBeforeOrEq(final Cargo c) {
        return this.getId() <= c.getId();
    }

    public boolean isOp() {
        return this.op;
    }

}
