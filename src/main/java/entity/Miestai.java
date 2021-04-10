package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name = "Miestai")
    public class Miestai {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String miestoPavadinimas;

        private String ligoninėsPavadinimas;

        public Miestai() {}
        public Miestai(String miestoPavadinimas, String ligoninėsPavadinimas) {
            this.miestoPavadinimas = miestoPavadinimas;
            this.ligoninėsPavadinimas = ligoninėsPavadinimas;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMiestoPavadinimas() {
            return miestoPavadinimas;
        }

        public void setMiestoPavadinimas(String miestoPavadinimas) {
            this.miestoPavadinimas = miestoPavadinimas;
        }

        public String getLigoninėsPavadinimas() {
            return ligoninėsPavadinimas;
        }

        public void setLigoninėsPavadinimas(String ligoninėsPavadinimas) {
            this.ligoninėsPavadinimas = ligoninėsPavadinimas;
        }

        @Override
        public String toString() {
            return miestoPavadinimas + " " + ligoninėsPavadinimas;
        }
    }
