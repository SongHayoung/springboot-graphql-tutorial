package com.sumfi.graphql.resolver.webtoon.mutation;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FileUploadMutation implements GraphQLMutationResolver {
    public UUID uploadFile(DataFetchingEnvironment env) {
        DefaultGraphQLServletContext context = env.getContext();

        context.getFileParts().forEach(
                filePart -> log.info("uploaded file name : {}, file size : {}", filePart.getSubmittedFileName(), filePart.getSize())
        );

        return UUID.randomUUID();
    }
}
