package com.babobird.Toto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 223688276L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTask task = new QTask("task");

    public final DateTimePath<java.time.LocalDateTime> modifyDt = createDateTime("modifyDt", java.time.LocalDateTime.class);

    public final EnumPath<TaskStatus> status = createEnum("status", TaskStatus.class);

    public final StringPath taskNm = createString("taskNm");

    public final NumberPath<Integer> taskNo = createNumber("taskNo", Integer.class);

    public final QToto toto;

    public final DateTimePath<java.time.LocalDateTime> writeDt = createDateTime("writeDt", java.time.LocalDateTime.class);

    public QTask(String variable) {
        this(Task.class, forVariable(variable), INITS);
    }

    public QTask(Path<? extends Task> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTask(PathMetadata metadata, PathInits inits) {
        this(Task.class, metadata, inits);
    }

    public QTask(Class<? extends Task> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.toto = inits.isInitialized("toto") ? new QToto(forProperty("toto")) : null;
    }

}

