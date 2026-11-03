# Update Dependency Jakarta

Project ini sudah ditambahkan dependency **Jakarta Persistence API** secara eksplisit di `pom.xml`.

Dependency yang ditambahkan:
- `jakarta.persistence:jakarta.persistence-api`

Dependency ini digunakan oleh entity Spring/JPA seperti:
- `@Entity`
- `@Table`
- `@Id`
- `@Column`
- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@JoinColumn`

Cara menjalankan:

```powershell
cd haydar
.\mvnw.cmd clean
.\mvnw.cmd spring-boot:run
```

Jika password PostgreSQL belum diisi, buka `src/main/resources/application.properties` dan ubah:

```properties
spring.datasource.password=GANTI_DENGAN_PASSWORD_POSTGRES_KAMU
```
