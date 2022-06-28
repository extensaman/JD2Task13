package by.academy.it.task13.repo;

import by.academy.it.task13.entity.Attachment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends PagingAndSortingRepository<Attachment, Long> {
}
