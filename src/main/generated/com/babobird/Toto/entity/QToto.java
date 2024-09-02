package com.babobird.Toto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToto is a Querydsl query type for Toto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToto extends EntityPathBase<Toto> {

    private static final long serialVersionUID = 223701765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QToto toto = new QToto("toto");

    public final QTask task;

    public final StringPath totoNm = createString("totoNm");

    public final NumberPath<Integer> totoNo = createNumber("totoNo", Integer.class);

    public QToto(String variable) {
        this(Toto.class, forVariable(variable), INITS);
    }

    public QToto(Path<? extends Toto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QToto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QToto(PathMetadata metadata, PathInits inits) {
        this(Toto.class, metadata, inits);
    }

    public QToto(Class<? extends Toto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task")) : null;
    }

}

