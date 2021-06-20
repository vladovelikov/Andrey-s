package springdataexercise.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdataexercise.constants.GlobalConstants;
import springdataexercise.entities.Author;
import springdataexercise.repositories.AuthorRepository;
import springdataexercise.services.AuthorService;
import springdataexercise.utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHORS_PATH);
        Arrays.stream(fileContent).forEach(row -> {
            String[] params = row.split(" ");
            Author author = new Author(params[0], params[1]);
            this.authorRepository.saveAndFlush(author);
        });

    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

}
