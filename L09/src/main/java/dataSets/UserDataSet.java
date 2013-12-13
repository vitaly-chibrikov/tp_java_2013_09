package dataSets;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserDataSet implements Serializable {
	private static final long serialVersionUID = -8706689714326132798L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;

    public UserDataSet() {
    }

    public UserDataSet(long id, String name){
		this.setId(id);
		this.setName(name);
	}
	
	public UserDataSet(String name){
		this.setId(-1);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}

