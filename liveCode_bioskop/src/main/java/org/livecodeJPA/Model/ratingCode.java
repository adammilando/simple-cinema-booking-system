package org.livecodeJPA.Model;

public enum ratingCode {
    A("UMUM", 0),
    BO("Kurang dari 13 tahun tidak boleh", 13),
    R("Kurang dari 18 tahun tidak boleh", 18),
    D("Sama dengan atau lebih dari 21 tahun diperbolehkan",21);

    private final String description;
    private final int ageLimit;

    ratingCode(String description, int ageLimit) {
        this.description = description;
        this.ageLimit = ageLimit;
    }

    public String getDescription() {
        return description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }
}
