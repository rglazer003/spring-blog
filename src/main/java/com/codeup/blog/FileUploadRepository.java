package com.codeup.blog;

import com.codeup.blog.models.Upload;
import org.springframework.data.repository.CrudRepository;

public interface FileUploadRepository extends CrudRepository<Upload, Long> {
}
