package it.epicode.PrenotazioneEvento.model;

public class UtenteRequest {

    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private String ruolo;
    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    // toString method
    @Override
    public String toString() {
        return "UtenteRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteRequest that = (UtenteRequest) o;
        return username.equals(that.username) && password.equals(that.password) && email.equals(that.email) && nome.equals(that.nome) && cognome.equals(that.cognome) && ruolo.equals(that.ruolo);
    }

    // Constructor
    public UtenteRequest(String username, String password, String email, String nome, String cognome, String ruolo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }
}
