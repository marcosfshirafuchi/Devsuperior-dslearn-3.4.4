package com.devsuperior.dslearn.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_lesson")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer position;

    //Um curso tem muitas ofertas ou muitas lições tem uma section
    @ManyToOne
    //Coloca o nome da chave estrangeira
    @JoinColumn(name = "section_id")
    private Section section;

    //Associação muitos para muitos coloca o set para não haver repetição
    //Um usuario pode ter muitos roles
    // Anotação que mapeia um relacionamento muitos-para-muitos com a entidade Role
    @ManyToMany
    // Anotação que configura a tabela de junção para o relacionamento ManyToMany
    @JoinTable(name = "tb_lessons_done", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "lesson_id"), // Coluna na tabela de junção que referencia o ID do usuário
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id"),
                    @JoinColumn(name = "offer_id")
            }
    )// Coluna na tabela de junção que referencia o ID da role
    //Quando o relacionamento é muitos para muitos, usar o Set
    private Set<Enrollment> enrollmentsDone = new HashSet<>();

    public Lesson() {

    }

    public Lesson(Long id, String title, Integer position, Section section) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Enrollment> getEnrollmentsDone() {
        return enrollmentsDone;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Lesson lesson)) return false;

        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
