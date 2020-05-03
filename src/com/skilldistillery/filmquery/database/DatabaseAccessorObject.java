package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private String user = "student";
	private String pass = "student";

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT film.title, film.id, film.release_year, film.rating, film.description FROM film WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film();

			film.setTitle(filmResult.getString("title"));
			film.setReleaseYear(filmResult.getInt("release_year"));
			film.setRating(filmResult.getString("rating"));
			film.setDescription(filmResult.getString("description"));
			film.setId(filmResult.getInt("id"));
			film.setLanguage(language(filmId));
			film.setActorsNames(name(filmId));
		}
		filmResult.close();
		stmt.close();
		conn.close();
		return film;

	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	private List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
			sql += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				String rating = rs.getString("rating");
				String language = language(id);
				List<Actor> actorsName = name(id);
				Film film = new Film(id, title, description, releaseYear, language, rating, actorsName);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor Join film_actor on "
					+ "actor.id = film_actor.actor_id JOIN film ON film_actor.film_id = film.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.* FROM film WHERE film.title  LIKE ? OR film.description like ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				String rating = rs.getString("rating");
				String language = language(id);
				List<Actor> actorsName = name(id);
				Film film = new Film(id, title, description, releaseYear, language, rating, actorsName);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;

	}

	@Override
	public String language(int filmId) throws SQLException {
		String filmLanguage = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT language.name FROM language JOIN film on language.id = film.language_id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			filmLanguage = rs.getString("name");
		}
		return filmLanguage;
	}

	@Override
	public List<Actor> name(int filmId) throws SQLException {
		List<Actor> names = new ArrayList<>();
		Actor actor;
		String firstName = null;
		String lastName = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film_actor.film_id = film.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			actor = new Actor(id, firstName, lastName);
			names.add(actor);
		}

		return names;
	}

}
