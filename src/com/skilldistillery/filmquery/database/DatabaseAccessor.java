package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId) throws SQLException;

	public Actor findActorById(int actorId) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	public List<Film> findFilmsByKeyword(String keyword) throws SQLException;

	public String language(int filmId) throws SQLException;

	public List<Actor> name(int filmId) throws SQLException;

	public void totalFilmDescription(int filmId) throws SQLException;
}
