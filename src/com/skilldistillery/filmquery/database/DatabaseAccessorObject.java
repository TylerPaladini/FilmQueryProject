package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	String user = "student";
	String pass = "student";

	public Film getFilmById(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);

		Film film = null;
		// ...
		String sql = "select id, title, description, release_year, language_id, "
				+ "rental_duration, rental_rate, length, replacement_cost, rating, "
				+ "special_features from film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setRelease_year(filmResult.getInt("release_year"));
			film.setLanguage_id(filmResult.getInt("language_id"));
			film.setRental_duration(filmResult.getString("rental_duration"));
			film.setRental_rate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getString("length"));
			film.setReplacement_cost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecial_features(filmResult.getString("special_features"));

		}
		filmResult.close();
		stmt.close();
		conn.close();

		return film;
	}
@Override
	public Actor getActorById(int actorId) throws SQLException {
		Connection conn = DriverManager.getConnection(URL, user, pass);

		Actor actor = null;

		String sql = "Select id, first_name, last_name From actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirst_name(actorResult.getString("first_name"));
			actor.setLast_name(actorResult.getString("last_name"));

		}
		actorResult.close();
		stmt.close();
		conn.close();

		return actor;

	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		

			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "Select actor.id, actor.first_name, actor.last_name " + 
					"From actor Join film_actor " + 
					"on actor.id = film_actor.actor_id" + 
					"Join film on film_actor.film_id = film.id"
					+ "Where id = ?";
		
		

		
		return null;
	}

}
