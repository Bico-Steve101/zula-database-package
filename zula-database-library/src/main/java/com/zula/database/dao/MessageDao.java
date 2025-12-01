package com.zula.database.dao;

import com.zula.database.entity.MessageInbox;
import com.zula.database.entity.MessageOutbox;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(MessageInbox.class)
@RegisterBeanMapper(MessageOutbox.class)
public interface MessageDao {

    @SqlUpdate("INSERT INTO message_inbox(message_id, message_type, source_service, payload, status, created_at) VALUES (:messageId, :messageType, :sourceService, :payload, :status, :createdAt)")
    void insertInbox(@BindBean MessageInbox message);

    @SqlQuery("SELECT id, message_id, message_type, source_service, payload, status, processed_at, created_at, updated_at FROM message_inbox WHERE message_id = :messageId")
    MessageInbox findInboxByMessageId(@Bind("messageId") String messageId);

    @SqlUpdate("INSERT INTO message_outbox(message_id, message_type, target_service, payload, status, created_at) VALUES (:messageId, :messageType, :targetService, :payload, :status, :createdAt)")
    void insertOutbox(@BindBean MessageOutbox message);

    @SqlQuery("SELECT id, message_id, message_type, target_service, payload, status, sent_at, retry_count, created_at, updated_at FROM message_outbox WHERE message_id = :messageId")
    MessageOutbox findOutboxByMessageId(@Bind("messageId") String messageId);

    @SqlQuery("SELECT id, message_id, message_type, target_service, payload, status, sent_at, retry_count, created_at, updated_at FROM message_outbox")
    List<MessageOutbox> listOutbox();
}

