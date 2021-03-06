package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
    private int id;
    private String title;
    private String description;
    private Integer releaseYear;
    private int languageId;
    private String language;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;
    private List<Actor> actorsNames;
    private String category;


    public Film() {

    }

    public Film(int id, String title, String description, Integer releaseYear, String language, String rating, List<Actor> actorsName) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.rating = rating;
        this.actorsNames = actorsName;
    }

    public Film(String title, int year, String rating, String description) {
        this.title = title;
        this.releaseYear = year;
        this.rating = rating;
        this.description = description;
    }

    public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
                double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
    }

    public Film(int filmId, String title2, String desc, short releaseYear2, int langId, int rentDur, double rate,
                int length2, double repCost, String rating2, String features) {
        this.id = filmId;
        this.title = title2;
        this.description = desc;
        this.releaseYear = (int) releaseYear2;
        this.languageId = langId;
        this.rentalDuration = rentDur;
        this.rentalRate = rate;
        this.length = length2;
        this.replacementCost = repCost;
        this.rating = rating2;
        this.specialFeatures = features;
    }

    // GETTERS AND SETTERS

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public List<Actor> getActorsNames() {
        return actorsNames;
    }

    public void setActorsNames(List<Actor> actorsNames) {
        this.actorsNames = actorsNames;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + languageId;
        result = prime * result + length;
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
        result = prime * result + rentalDuration;
        long temp;
        temp = Double.doubleToLongBits(rentalRate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(replacementCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id != other.id)
            return false;
        if (languageId != other.languageId)
            return false;
        if (length != other.length)
            return false;
        if (rating == null) {
            if (other.rating != null)
                return false;
        } else if (!rating.equals(other.rating))
            return false;
        if (releaseYear == null) {
            if (other.releaseYear != null)
                return false;
        } else if (!releaseYear.equals(other.releaseYear))
            return false;
        if (rentalDuration != other.rentalDuration)
            return false;
        if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
            return false;
        if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
            return false;
        if (specialFeatures == null) {
            if (other.specialFeatures != null)
                return false;
        } else if (!specialFeatures.equals(other.specialFeatures))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        actorsNames.forEach(actor -> sb.append(actor.toString()));
        return "Film: " + title + "\nDate of Release: " + releaseYear + "\nRating: " + rating + "\nDescription: " + description + "\nLanguage: " + language +
                "\nActors: \n" + sb.toString() + "\n";
    }

    public String filmDescriptionString() {
        return "Film: " + title + "\nID: " + id + "\nDate of Release: " + releaseYear + "\nRating: " + rating + "\nDescription: " + description + "\nLanguage ID: " + languageId +
                "\nRental Duration: " + rentalDuration + " days" + "\nRental Price: $" + rentalRate + "\nLength: " + length + " Min." + "\nReplacement Cost: $" + replacementCost
                + "\nSpecial Features: " + specialFeatures + "\nCategory: " + category + "\n";
    }
}
