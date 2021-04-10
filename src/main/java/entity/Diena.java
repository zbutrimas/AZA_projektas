package entity;

import org.dom4j.CDATA;
import org.hibernate.dialect.JDataStoreDialect;

import javax.persistence.*;

    @Entity
    @Table(name = "Diena")
    public class Diena {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String menesioDiena;

        public Diena () {}

        public Diena(Long id, String menesioDiena) {
            this.id = id;
            this.menesioDiena = menesioDiena;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMenesioDiena() {
            return menesioDiena;
        }

        public void setMenesioDiena(String menesioDiena) {
            this.menesioDiena = menesioDiena;
        }

        @Override
        public String toString() {
            return menesioDiena + " dieną";
        }
    }
