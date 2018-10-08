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
		Actor actor = null;
		
		String sql = "select film.id, title, description, release_year, language.name, "
				+ "rental_duration, rental_rate, length, replacement_cost, rating,special_features,actor.id, actor.first_name, actor.last_name  "
				+ "from film "
				+ "join language "
				+ "on film.language_id = language.id "
				+ "join film_actor on film.id = film_actor.film_id "  
			    + "  join actor on actor.id = film_actor.actor_id "
				+ " WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);

		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film();
			actor = new Actor();
			
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setRelease_year(filmResult.getInt("release_year"));
			film.setLanguage_id(filmResult.getString("language.name"));
			film.setRentalDuration(filmResult.getInt("rental_duration"));
			film.setRentalRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeature(filmResult.getString("special_features"));
			actor.setId(filmResult.getInt("id"));
        	actor.setFirst_name(filmResult.getString("first_name"));
        	actor.setLast_name(filmResult.getString("last_name"));
        film.setActor(actor);
				
            

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

public List<Film> getFilmByKeyword(String keyword) throws SQLException {
	
	List<Film> films = new ArrayList<>();
	Film film = null;
	Actor actor = null;
	
	String sql = "select film.id, title, description, release_year, language.name, rental_duration, rental_rate, length, replacement_cost,"
    + " rating, special_features, actor.id, actor.first_name, actor.last_name "
    + "  from film join language on film.language_id = language.id "
    + "join film_actor on film.id = film_actor.film_id "  
    + "  join actor on actor.id = film_actor.actor_id "
    + "where title like ? or description like ?";
	
	Connection conn = DriverManager.getConnection(URL, user, pass);
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, keyword + "%");
	stmt.setString(2, "%"  + keyword + "%");
	ResultSet rs = stmt.executeQuery();
	while(rs.next()) {
		film = new Film();
		actor = new Actor();
		
		film.setId(rs.getInt("id"));
        film.setTitle(rs.getString("title"));
        film.setDescription(rs.getString("description"));
        film.setRelease_year(rs.getInt("release_year"));
        film.setLanguage_id(rs.getString("language.name"));
        film.setRentalDuration(rs.getInt("rental_duration"));
        film.setRentalRate(rs.getDouble("rental_rate"));
        film.setLength(rs.getInt("length"));
        film.setRating(rs.getString("rating"));
        film.setSpecialFeature(rs.getString("special_features"));
        	actor.setId(rs.getInt("id"));
        	actor.setFirst_name(rs.getString("first_name"));
        	actor.setLast_name(rs.getString("last_name"));
        film.setActor(actor);
        
        films.add(film);
	}
	rs.close();
	stmt.close();
	conn.close();
	return films;
}


	@Override
	public List<Actor> getActorsByFilmId(int filmId) {

		
		return null;
	}

}
