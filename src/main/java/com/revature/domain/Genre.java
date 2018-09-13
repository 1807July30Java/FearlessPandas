package com.revature.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

@NamedQueries({
		@NamedQuery(name = "getGenres", query = "from Genre"),
		@NamedQuery(name = "getGenreByName",query = "from Genre where name = :name order by genreId asc")
})

@Entity
@Table(name = "Genre")
public class Genre {
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genreSequence")
	  @SequenceGenerator(allocationSize = 1, name = "genreSequence", sequenceName = "SQ_GENRE_PK")
	  @Column(name = "GENRE_ID")
	  private int genreId;
	  @Column(name = "NAME")
	  private String name;
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
