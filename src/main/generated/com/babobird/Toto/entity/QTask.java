package com.babobird.Toto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 223688276L;

    public static final QTask task = new QTask("task");

    public final DateTimePath<java.time.LocalDateTime> modifyDt = createDateTime("modifyDt", java.time.LocalDateTime.class);

    public final EnumPath<TaskStatus> status = createEnum("status", TaskStatus.class);

    public final StringPath taskNm = createString("taskNm");

    public final NumberPath<Integer> taskNo = createNumber("taskNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> writeDt = createDateTime("writeDt", java.time.LocalDateTime.class);

    public QTask(String variable) {
        super(Task.class, forVariable(variable));
    }

    public QTask(Path<? extends Task> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTask(PathMetadata metadata) {
        super(Task.class, metadata);
    }

}

