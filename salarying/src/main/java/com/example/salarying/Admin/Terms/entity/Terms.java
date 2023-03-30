package com.example.salarying.Admin.Terms.entity;



import com.example.salarying.Admin.Terms.dto.TermsDTO;
import com.example.salarying.Admin.User.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="terms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String agreementTitle;

    @Column(name="content", columnDefinition = "TEXT")
    private String agreementContent;

    @Column(name="version")
    private String version;

    @Column(name="status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public void update(String status){
        this.status = status;
    }

    public void modify(Admin admin, TermsDTO.UpdateRequest request){
        this.admin = admin;
        this.agreementTitle = request.getTitle();
        this.agreementContent = request.getContent();
        this.version = request.getVersion();
    }

}
